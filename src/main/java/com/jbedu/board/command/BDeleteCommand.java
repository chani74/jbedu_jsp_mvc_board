package com.jbedu.board.command;

import com.jbedu.board.dao.BoardDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BDeleteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		BoardDao boardDao = new BoardDao();
		String bnum = request.getParameter("bnum");
		boardDao.content_delete(bnum);
	}

}
