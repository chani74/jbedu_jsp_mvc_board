package com.jbedu.board.command;

import com.jbedu.board.dao.BoardDao;
import com.jbedu.board.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		BoardDao boardDao = new BoardDao();
		String bnum = request.getParameter("bnum");
		String btitle = request.getParameter("btitle");
		String bname  = request.getParameter("bname");
		String bcontent = request.getParameter("bcontent");
		boardDao.content_modify(bnum,btitle,bname,bcontent);
	}

}
