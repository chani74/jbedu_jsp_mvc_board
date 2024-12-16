package com.jbedu.board.command;

import com.jbedu.board.dao.BoardDao;
import com.jbedu.board.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BContentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		BoardDao boardDao = new BoardDao();
		String bnum = request.getParameter("bnum");
		boardDao.up_hit(bnum);	 // hit 수 증가
		BoardDto bDto = boardDao.content_view(bnum);
		request.setAttribute("boardDto", bDto);
	}

}
