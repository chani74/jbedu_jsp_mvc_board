package com.jbedu.board.controller;

import java.io.IOException;
import java.util.List;

import com.jbedu.board.command.BCommand;
import com.jbedu.board.command.BContentCommand;
import com.jbedu.board.command.BDeleteCommand;
import com.jbedu.board.command.BListCommand;
import com.jbedu.board.command.BModifyCommand;
import com.jbedu.board.command.BWriteCommand;
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

		BCommand command = null;
		
		if(com.equals("/list.do")) {
			command = new BListCommand();
			command.execute(request, response);
			
			viewPage = "list.jsp";
		} else if (com.equals("/write_form.do")) {
			viewPage = "write_form.jsp";
		} else if(com.equals("/write.do")) {
			command = new BWriteCommand();
			command.execute(request, response);
		
			viewPage = "list.do";
		} else if (com.equals("/content_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			
			viewPage = "content_view.jsp";
		} else if (com.equals("/delete.do")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "list.do";
		} else if (com.equals("/modify.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			
			viewPage = "content_modify.jsp";
		} else if (com.equals("/modify_ok.do")) {
			command = new BModifyCommand();
			command.execute(request, response);
			
			viewPage = "list.do";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request,response);		
	}
		
}
