package com.jsp.action.board.reply;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.ReplyService;

public class RemoveReplyAction implements Action {

	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url=null;
			int rnoParam = Integer.parseInt(request.getParameter("rno"));
			PrintWriter out =response.getWriter();
			
			try {
				replyService.removeReply(rnoParam);
				out.print("SUCCESS");
			} catch (SQLException e) {
				e.printStackTrace();
				out.print("FAIL");
			}
			
		return url;
	}

}
