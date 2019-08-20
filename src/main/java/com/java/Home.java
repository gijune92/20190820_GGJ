package com.java;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.CharSet;

import com.java.hdfs.Hadoop;

@WebServlet("/Home")
public class Home extends HttpServlet {
	
	// 시작 화면 출력
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Home.doGet() >> Start");
		RequestDispatcher rd = req.getRequestDispatcher(viewPath("home"));
		rd.forward(req, res);
		System.out.println("Home.doGet() >> End");
	}

	// 분석 및 결과 출력
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Hadoop hd = new Hadoop();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String strView = null;
		System.out.println("Home.doPost() >> Start");
		// 정제 요청 대상 파일명 변수
		String file_name = req.getParameter("file_name");		
		if(file_name == null || ("").equals(file_name)) {
			// 정제 요청 대상 파일명 값이 없으면 Home 화면 요청
			res.sendRedirect("/Home");
		} else {
			// 정제 요청 대상 파일명 값이 있으면 HDFS 실행 요청 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			try {
				resultMap = hd.run(file_name);
				if(!resultMap.get("result").equals("")) {
					strView = resultMap.get("result").toString();
					
					System.out.println(new String(strView.getBytes("UTF-8"),"UTF-8"));
					
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			res.setContentType("text/plain; charset=UTF-8");
			res.setCharacterEncoding("UTF-8");
			req.setAttribute("file_name", file_name);
			//String bb = new String(strView.getBytes("latin1"), "utf-8");
			req.setAttribute("result",strView);


			/*
			System.out.println("strView : " + strView);

			res.getWriter().write(strView);
			*/
			RequestDispatcher rd = req.getRequestDispatcher(viewPath("result"));
			rd.forward(req, res);
		}
		System.out.println("Home.doPost() >> End");
	}

	// 웹 화면 처리 메소드
	protected String viewPath(String view) {
		// 화면 파일 경로
		String prefix = "/WEB-INF/views/";
		// 화면 파일 확장자
		String suffix = ".jsp"; 
		return prefix + view + suffix;
	}
}
