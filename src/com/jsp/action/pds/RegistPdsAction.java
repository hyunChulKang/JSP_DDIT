package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;
import com.jsp.utils.MakeLogForException;

import oracle.net.aso.f;

public class RegistPdsAction implements Action{

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName=uploadFile(request,response);
		String url="pds/regist_success";
		String writer = request.getParameter("writer");
		System.out.println(writer+"2222222222222222222");
 		String title= request. getParameter("title");
		String content = request.getParameter("content");
		PdsVO vo = new PdsVO();
		vo.setContent(content);
		vo.setWriter(writer);
		vo.setTitle(title);
		
		
		
		return url;
	}

	//업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500;// 500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1; //1MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2; //2MB
	
	private String uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fileName =null;
		try {
			fileName = saveFile(request,response);
		} catch(Exception e) {
			e.printStackTrace(); //개발중에 에러확인하기 위해 
			MakeLogForException.makeLog(request,e);
			throw new IOException("파일 업로드 실패"); //사용자 화면 용(ajax)
		}
		return fileName;
	}
	
	private String saveFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
		
		//request 파일 첨부 여부 확인
		if(!ServletFileUpload.isMultipartContent(request)) 	{
			//return null;
			throw new Exception();
		}
		
		//업로드를 위한  upload 환경설정 적용.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//저장을 위한 threshold memory 적용
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		//임시저장 위치 결정
		
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//업로드 파일의 크기 적용.
		upload.setFileSizeMax(MAX_FILE_SIZE);
		
		//업로드 request 크키 적용.
		upload.setSizeMax(MAX_REQUEST_SIZE);
		//실제 저장경로를 설정
		String uploadPath = GetUploadPath.getUploadPath("pds.upload");
		File file =new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath+"가 이미 존재하거나 실패했습니다.");
		};
		
		//request로 부터 받는 파일을 추출해서 저장.
		List<FileItem> formItems = upload.parseRequest(request); //fileuploadException이 실행되는데 throw함
			System.out.println(formItems.toString());
		String fileName=null;
		
		//파일 개수 확인.
		if(formItems !=null && formItems.size()>0) {
			for(FileItem item : formItems) {		//form items 반복하여 꺼내는 구문
				
				System.out.println(item.getFieldName());
				
				if(item.getFieldName().contains("uploadFile")) {	//파일일 경우 해당 업로드 된 파일 넘어옴 
					//uuid+ 구분자 + 파일명
					String realName=item.getName().substring(item.getName().lastIndexOf("."));
					fileName = MakeFileName.toUUIDFileName(realName, "");
					System.out.println(">>>>> "+fileName+" <<<<<");
					String filePath = uploadPath+ File.separator + fileName;
					File storeFile = new File(filePath);
					//local HDD에 저장
					item.write(storeFile);	//Exception throw됨
				}else {//oldFile 삭제 text 파일 넘어옴
					switch(item.getFieldName()) {
					
					case "oldPicture":
						String oldFileName = item.getString("UTF-8");
						File oldFile = new File(uploadPath + File.separator + oldFileName);
						if(oldFile.exists()) {
							oldFile.delete();
						}
						break;
					}
				}
			}
		}
		return fileName;
	}
}

