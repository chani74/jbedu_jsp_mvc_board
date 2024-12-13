package com.jbedu.board.controller;

import java.io.IOException;
import java.util.List;

import com.jbedu.board.dao.BoardDao;
import com.jbedu.board.dto.BoardDto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.do")  //컨트롤러 기능의 클래스로 서버가 인식하도록 함
public class BoardController extends HttpServlet {

	public BoardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet호출");
		
		request.setCharacterEncoding("utf-8");
		
		String viewPage = "" ;	//글 목록 페이지
			
		String uri = request.getRequestURI()	; 
		String conPath = request.getContextPath();   // jsp_mvc_board/
		String com = uri.substring(conPath.length());  // list.jsp

		System.out.println(uri);
		System.out.println(conPath);
		System.out.println(com);
		
		if(com.equals("/list.do")) {
			BoardDao boardDao = new BoardDao();
			List<BoardDto> bDtos = boardDao.board_list();
			request.setAttribute("boardList", bDtos);
			
			viewPage = "list.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}


		
}
