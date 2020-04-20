package com.jsp.action.member;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.utils.GetUploadPath;

public class MemberPirctureViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter("picture");
		String savedPath = GetUploadPath.getUploadPath("member.picture.upload");
		
		String filePath = savedPath+fileName ;
		sendFile(response,request,filePath);
		
		return null;
	
	}
	
	private void sendFile(HttpServletResponse response,HttpServletRequest request, String filePath)throws ServletException, IOException {
		//보낸 파일 설정.
		File downloadFile = new File(filePath);
		FileInputStream inStream = new FileInputStream(downloadFile);
		
		ServletContext context =request.getServletContext();
		
		//파일 포맷으로 MIME를 결정한다.
		String mimeType =context.getMimeType(filePath);
		if(mimeType ==null) {
			mimeType = "applicatuion/octet-stream";
		}
		//response 수정.
		response.setContentType(mimeType);
		response.setContentLength((int)downloadFile.length());
		
		String headerKey = "Content-Disposition";
		String headerValue= String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);
		
		OutputStream outStream =response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		
		while((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer,0,bytesRead);
		}
		if(inStream !=null) inStream.close();
		if(outStream !=null) outStream.close();
	}

}
