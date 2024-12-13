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
		actionDo(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actionDo 호출");
		request.setCharacterEncoding("utf-8");
		
		String viewPage = "" ;	//글 목록 페이지
			
		String uri = request.getRequestURI()	; 
		String conPath = request.getContextPath();   // jsp_mvc_board/
		String com = uri.substring(conPath.length());  // list.jsp
//
//		System.out.println(uri);
//		System.out.println(conPath);
//		System.out.println(com);
		
		if(com.equals("/list.do")) {
			BoardDao boardDao = new BoardDao();
			List<BoardDto> bDtos = boardDao.board_list();
			request.setAttribute("boardList", bDtos);
			
			viewPage = "list.jsp";
		} else if (com.equals("/write_form.do")) {
			viewPage = "write_form.jsp";
		} else if(com.equals("/write.do")) {
			BoardDao boardDao = new BoardDao();
			
			String btitle = request.getParameter("btitle");
			String bname = request.getParameter("bname");
			String bcontent = request.getParameter("bcontent");
			
			
			boardDao.board_write(btitle, bname, bcontent);
//			request.setAttribute("boardList", bDtos);
			
			viewPage = "list.do";
		} else if (com.equals("/content_view.do")) {
			BoardDao boardDao = new BoardDao();
			String bnum = request.getParameter("bnum");
			boardDao.up_hit(bnum);	 // hit 수 증가
			BoardDto bDto = boardDao.content_view(bnum);
			request.setAttribute("boardDto", bDto);
			
			viewPage = "content_view.jsp";
		} else if (com.equals("/delete.do")) {
			BoardDao boardDao = new BoardDao();
			String bnum = request.getParameter("bnum");
			boardDao.content_delete(bnum);
			viewPage = "list.do";
		} else if (com.equals("/modify.do")) {
			BoardDao boardDao = new BoardDao();
			String bnum = request.getParameter("bnum");
			BoardDto bDto = boardDao.content_view(bnum);
			request.setAttribute("boardDto", bDto);
			
			viewPage = "content_modify.jsp";
		} else if (com.equals("/modify_ok.do")) {
			BoardDao boardDao = new BoardDao();
			String bnum = request.getParameter("bnum");
			String btitle = request.getParameter("btitle");
			String bname  = request.getParameter("bname");
			String bcontent = request.getParameter("bcontent");
			boardDao.content_modify(bnum,btitle,bname,bcontent);
			
			viewPage = "list.do";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request,response);		
	}
		
}
