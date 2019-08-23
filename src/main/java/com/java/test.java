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
import org.apache.hadoop.io.BytesWritable;

import com.java.hdfs.Hadoop;

@WebServlet("/a")
public class test extends HttpServlet {
	
	// 시작 화면 출력
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(viewPath("a"));
		rd.forward(req, res);
	}

	protected String viewPath(String view) {
		// 화면 파일 경로
		//String prefix = "/WEB-INF/views/";
		// 화면 파일 확장자
		String suffix = ".jsp"; 
		return "/WEB-INF/" + view + suffix;
	}
}
