package com.jbedu.board.command;

import com.jbedu.board.dao.BoardDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BWriteCommand implements BCommand{
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao boardDao = new BoardDao();
		
		String btitle = request.getParameter("btitle");
		String bname = request.getParameter("bname");
		String bcontent = request.getParameter("bcontent");
		
		
		boardDao.board_write(btitle, bname, bcontent);		
	}
}
