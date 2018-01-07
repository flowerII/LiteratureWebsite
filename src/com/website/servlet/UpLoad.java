package com.website.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class UpLoad
 */
@WebServlet("/UpLoad")
public class UpLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String saveDirectory=this.getServletContext().getRealPath("")+"\\upload";
		
		File savedir=new File(saveDirectory);
		if(!savedir.exists()){
			savedir.mkdirs();
		}
		System.out.println(saveDirectory);
		int maxPostSize=5*1024*1024;
		MultipartRequest multi;
		multi=new MultipartRequest(request,saveDirectory,maxPostSize,"utf-8");
		@SuppressWarnings("unchecked")
		Enumeration<String> files=multi.getFileNames();
		while(files.hasMoreElements()){
			String name=files.nextElement();
			//System.out.println(name);
			File f=multi.getFile(name);
			if(f!=null){
				String fileName=multi.getFilesystemName(name);
				//System.out.println(fileName);
				String lastFileName="http://localhost:8080/LiteratureWebsite/upload/"+fileName;
				System.out.println(lastFileName);
				out.println(lastFileName);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
