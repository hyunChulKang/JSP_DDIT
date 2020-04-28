package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
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
		String url="pds/regist_success";
	
		try {
		PdsVO pds =fileUpload(request);
		
			pdsService.regist(pds);
		}catch(Exception e) {
			e.printStackTrace();
			url="pds/regist_fail";
		}
		return url;
	}
	
	//업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500;// 500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; //40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; //50MB
	
	private PdsVO fileUpload(HttpServletRequest request) throws Exception{
		PdsVO pds = new PdsVO();
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		//업로드를 위한 upload 환경설정 적용
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//저장을 위한 threshold memory 적용
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		//임시 저장 위치 결정
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		
		upload.setFileSizeMax(MAX_FILE_SIZE);
		
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		String uploadPath = GetUploadPath.getUploadPath("pds.upload");
		
		File  file = new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 생성을 실패했습니다.");
		}
		String title = null;
		String writer = null;
		String content = null;
		int pno = -1;
		
		
		try {
			List<FileItem> formItems = upload.parseRequest(request);
		
		for(FileItem item : formItems) {
			if(item.isFormField()) {
				// 1.1 필드
				if(item.isFormField()) {
					switch (item.getFieldName()) {
					case "title" :
						title= item.getString("UTF-8");
						break;
					case "content" :
						content= item.getString("UTF-8");
						break;
					case "writer" :
						writer= item.getString("UTF-8");
						break;
					}
				}
				//파라메터 구분 (파라메터 이름)
				//PdsVO 생성
			}else {// 1.2 파일
				//summernote의 files 를 제외함
				if(!item.getFieldName().equals("uploadFile")) continue;
				
				String fileName = new File(item.getName()).getName();
				fileName = MakeFileName.toUUIDFileName(fileName, "$$");
				String filePath = uploadPath + File.separator + fileName;
				File storeFile = new File(filePath);
				try {
					item.write(storeFile);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				AttachVO attach = new AttachVO();
				attach.setFileName(fileName);
				attach.setUploadPath(uploadPath);
				attach.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
				
				attachList.add(attach);
				
				   // 1.5  파일저장
				   // AttachVO 생성
				   // List<AttachVO> 추가
				
			}
		}
			pds.setAttachList(attachList);
			pds.setWriter(writer);
			pds.setContent(content);
			pds.setTitle(title);
		
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw e;
		}
		return pds;
	}
}




/*
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PdsVO pdsVO = new PdsVO();
		pdsVO=uploadFile(request,response);
		String url="pds/regist_success";
		return url;
	}

	//업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500;// 500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; //5MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2; //2MB
	
	private PdsVO uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PdsVO pdsVO = new PdsVO();
		try {
			pdsVO = saveFile(request,response);
			pdsService.regist(pdsVO);
		} catch(Exception e) {
			e.printStackTrace(); //개발중에 에러확인하기 위해 
			MakeLogForException.makeLog(request,e);
			throw new IOException("파일 업로드 실패"); //사용자 화면 용(ajax)
		}
		return pdsVO;
	}
	
	private PdsVO saveFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
		PdsVO pdsVO = new PdsVO();
		List<AttachVO> list = new ArrayList<AttachVO>();
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
		
		//업로드 request 크기 적용.
		upload.setSizeMax(MAX_REQUEST_SIZE);
		//실제 저장경로를 설정
		String uploadPath = GetUploadPath.getUploadPath("pds.upload");
		
		File file =new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath+"가 이미 존재하거나 실패했습니다.");
		};
		
		//request로 부터 받는 파일을 추출해서 저장.
		List<FileItem> formItems = upload.parseRequest(request); //fileuploadException이 실행되는데 throw함
			System.out.println("formItems(reqeust로 받은 formdata) : "+formItems.toString());
		String fileName=null;
		//파일 개수 확인.
		if(formItems !=null && formItems.size()>0) {
			for(FileItem item : formItems) {		//form items 반복하여 꺼내는 구문
				System.out.println("item : " +item.toString());
//				if(item.isFormField()) {
				if(item.getFieldName().equals("uploadFile")) {	//파일일 경우 해당 업로드 된 파일 넘어옴
					AttachVO attachVO = new AttachVO();
					System.out.println("item.getName() : "+ item.getName());
					//uuid+ 구분자 + 파일명
					String realName=item.getName().substring(item.getName().lastIndexOf("."));
					System.out.println("realName : " + realName);
					fileName = MakeFileName.toUUIDFileName(realName, "");
					attachVO.setFileType(realName);
					attachVO.setFileName(item.getName());
					list.add(attachVO);
					pdsVO.setAttachList(list);
					
					System.out.println("pdsVO.getAttachList().size() : " + pdsVO.getAttachList().size());
					System.out.println("fileName :" + fileName);
					String filePath = uploadPath+ File.separator + fileName;
					attachVO.setUploadPath(filePath);
					System.out.println("filePath : " + filePath);
					File storeFile = new File(filePath);
					//local HDD에 저장
					item.write(storeFile);	//Exception throw됨
				}else {//oldFile 삭제 text 파일 넘어옴
					switch(item.getFieldName()) {
					
					case "writer":
						String writer = item.getString("UTF-8");
						System.out.println("writer_____"+writer);
						pdsVO.setWriter(writer);
						break;
					case "title":
						String title = item.getString("UTF-8");
						System.out.println("title_____"+title);
						pdsVO.setTitle(title);
						break;
					case "content":
						String content = item.getString("UTF-8");
						System.out.println("content_____"+content);
						pdsVO.setContent(content);
						break;
					}
				}
			}
		}
		return pdsVO;
	}*/
