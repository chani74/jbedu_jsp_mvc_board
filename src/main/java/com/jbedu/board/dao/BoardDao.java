package com.jbedu.board.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.jbedu.board.dto.BoardDto;

public class BoardDao {

	public List<BoardDto> board_list() { // 모든 글 목록 가져오기 메서드
		
		String strSQL = "SELECT * FROM mvc_board ORDER BY bnum DESC " ;
		
		//1. 변수 4개 선언
		String driverName = "com.mysql.jdbc.Driver";	// MySQL JDBC 드라이버 이름
		String url = "jdbc:mysql://localhost:3306/jsp_project"; 
		String username = "root";
		String password = "12345";
		
		Connection conn = null ; //DB 와의 커넥션을 초기값 null 로 해서 생성
		PreparedStatement pstmt = null ;
		ResultSet rs = null;
		List<BoardDto> bDtos = new ArrayList<BoardDto>();
		
		try {
			//2.드라이버 로딩
			Class.forName(driverName); // mysql 드라이버를 불러오기
			//3. 연동
			conn = DriverManager.getConnection(url,username,password); // DB와 프로젝트간의 커넥션 생성
			pstmt = conn.prepareStatement(strSQL);
		
			
			//4. 사용
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bnum = rs.getInt("bnum");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				
				BoardDto bDto = new BoardDto(bnum, bname, btitle, bcontent, bdate, bhit);
				bDtos.add(bDto);
			}
			
			
		} catch(ClassNotFoundException e){
//			out.println(">>연결실패 : 드라이버 복사 필요!");
			e.printStackTrace();
		} catch(SQLException e ) {
//			out.println(">>연결실패 : SQL 명령문 확인 필요"+strSQL);
			e.printStackTrace();
		} catch(Exception e ) {
//			out.println(">>연결실패 : 오류 발생");
			e.printStackTrace();
		} finally {
			//5. 닫기 
			try {
				if(rs!=null)						
					rs.close();				
				if(pstmt!=null)						
					pstmt.close();				
				if(conn!=null)						
					conn.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bDtos;
	}

	public void board_write(String btitle, String bname, String bcontent) { // 모든 글 목록 가져오기 메서드
		
		String strSQL = "INSERT into mvc_board (btitle, bname, bcontent,bhit ) " 
				+ " VALUES (? , ?, ? , 0 ) ";
		
		//1. 변수 4개 선언
		String driverName = "com.mysql.jdbc.Driver";	// MySQL JDBC 드라이버 이름
		String url = "jdbc:mysql://localhost:3306/jsp_project"; 
		String username = "root";
		String password = "12345";
		
		Connection conn = null ; //DB 와의 커넥션을 초기값 null 로 해서 생성
		PreparedStatement pstmt = null ;
		ResultSet rs = null;
		List<BoardDto> bDtos = new ArrayList<BoardDto>();		
		
		try {
			//2.드라이버 로딩
			Class.forName(driverName); // mysql 드라이버를 불러오기
			//3. 연동
			conn = DriverManager.getConnection(url,username,password); // DB와 프로젝트간의 커넥션 생성
			pstmt = conn.prepareStatement(strSQL);
		
			pstmt.setString(1, btitle);
			pstmt.setString(2, bname);
			pstmt.setString(3, bcontent);
						
			//4. 사용
			pstmt.executeUpdate();
			
			
		} catch(ClassNotFoundException e){
//			out.println(">>연결실패 : 드라이버 복사 필요!");
			e.printStackTrace();
		} catch(SQLException e ) {
//			out.println(">>연결실패 : SQL 명령문 확인 필요"+strSQL);
			e.printStackTrace();
		} catch(Exception e ) {
//			out.println(">>연결실패 : 오류 발생");
			e.printStackTrace();
		} finally {
			//5. 닫기 
			try {
				if(pstmt!=null)						
					pstmt.close();				
				if(conn!=null)						
					conn.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public BoardDto content_view(String bnum) { // 모든 글 목록 가져오기 메서드
		
		String strSQL = "SELECT * FROM mvc_board WHERE bnum= ?" ;
		
		//1. 변수 4개 선언
		String driverName = "com.mysql.jdbc.Driver";	// MySQL JDBC 드라이버 이름
		String url = "jdbc:mysql://localhost:3306/jsp_project"; 
		String username = "root";
		String password = "12345";
		
		Connection conn = null ; //DB 와의 커넥션을 초기값 null 로 해서 생성
		PreparedStatement pstmt = null ;
		ResultSet rs = null;
		
		BoardDto bDto = new BoardDto(); 
				
		try {
			//2.드라이버 로딩
			Class.forName(driverName); // mysql 드라이버를 불러오기
			//3. 연동
			conn = DriverManager.getConnection(url,username,password); // DB와 프로젝트간의 커넥션 생성
			pstmt = conn.prepareStatement(strSQL);
		
			pstmt.setString(1, bnum);
			
			//4. 사용
			rs = pstmt.executeQuery();
			
			if  (rs.next()) {
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				
				bDto.setBnum(Integer.parseInt(bnum));
				bDto.setBname(bname);
				bDto.setBtitle(btitle);
				bDto.setBcontent(bcontent);
				bDto.setBdate(bdate);
				bDto.setBhit(bhit);
				
			}
			
			
		} catch(ClassNotFoundException e){
//			out.println(">>연결실패 : 드라이버 복사 필요!");
			e.printStackTrace();
		} catch(SQLException e ) {
//			out.println(">>연결실패 : SQL 명령문 확인 필요"+strSQL);
			e.printStackTrace();
		} catch(Exception e ) {
//			out.println(">>연결실패 : 오류 발생");
			e.printStackTrace();
		} finally {
			//5. 닫기 
			try {
				if(rs!=null)						
					rs.close();				
				if(pstmt!=null)						
					pstmt.close();				
				if(conn!=null)						
					conn.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bDto;
	}	
	public void content_delete(String bnum) { // 모든 글 목록 가져오기 메서드
		System.out.println("dao - conntent delete");
		String strSQL = "DELETE FROM mvc_board WHERE bnum= ?" ;
		
		//1. 변수 4개 선언
		String driverName = "com.mysql.jdbc.Driver";	// MySQL JDBC 드라이버 이름
		String url = "jdbc:mysql://localhost:3306/jsp_project"; 
		String username = "root";
		String password = "12345";
		
		Connection conn = null ; //DB 와의 커넥션을 초기값 null 로 해서 생성
		PreparedStatement pstmt = null ;
		
		BoardDto bDto = new BoardDto(); 
				
		try {
			//2.드라이버 로딩
			Class.forName(driverName); // mysql 드라이버를 불러오기
			//3. 연동
			conn = DriverManager.getConnection(url,username,password); // DB와 프로젝트간의 커넥션 생성
			pstmt = conn.prepareStatement(strSQL);
		
			pstmt.setString(1, bnum);
			
			//4. 사용
			pstmt.executeUpdate();
			
		} catch(ClassNotFoundException e){
//			out.println(">>연결실패 : 드라이버 복사 필요!");
			e.printStackTrace();
		} catch(SQLException e ) {
//			out.println(">>연결실패 : SQL 명령문 확인 필요"+strSQL);
			e.printStackTrace();
		} catch(Exception e ) {
//			out.println(">>연결실패 : 오류 발생");
			e.printStackTrace();
		} finally {
			//5. 닫기 
			try {
						
				if(pstmt!=null)						
					pstmt.close();				
				if(conn!=null)						
					conn.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}	
	
	public void content_modify(String bnum, String btitle, String bname, String bcontent) { // 모든 글 목록 가져오기 메서드
		
		String strSQL = "UPDATE mvc_board SET btitle=?, bname=?, bcontent=? " 
				+ " WHERE bnum = ? ";
		
		//1. 변수 4개 선언
		String driverName = "com.mysql.jdbc.Driver";	// MySQL JDBC 드라이버 이름
		String url = "jdbc:mysql://localhost:3306/jsp_project"; 
		String username = "root";
		String password = "12345";
		
		Connection conn = null ; //DB 와의 커넥션을 초기값 null 로 해서 생성
		PreparedStatement pstmt = null ;
		ResultSet rs = null;
		List<BoardDto> bDtos = new ArrayList<BoardDto>();		
		
		try {
			//2.드라이버 로딩
			Class.forName(driverName); // mysql 드라이버를 불러오기
			//3. 연동
			conn = DriverManager.getConnection(url,username,password); // DB와 프로젝트간의 커넥션 생성
			pstmt = conn.prepareStatement(strSQL);
		
			pstmt.setString(1, btitle);
			pstmt.setString(2, bname);
			pstmt.setString(3, bcontent);
			pstmt.setString(4, bnum);
						
			//4. 사용
			pstmt.executeUpdate();
			
			
		} catch(ClassNotFoundException e){
//			out.println(">>연결실패 : 드라이버 복사 필요!");
			e.printStackTrace();
		} catch(SQLException e ) {
//			out.println(">>연결실패 : SQL 명령문 확인 필요"+strSQL);
			e.printStackTrace();
		} catch(Exception e ) {
//			out.println(">>연결실패 : 오류 발생");
			e.printStackTrace();
		} finally {
			//5. 닫기 
			try {
				if(pstmt!=null)						
					pstmt.close();				
				if(conn!=null)						
					conn.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}	
}
