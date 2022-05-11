package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Notice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class NoticeAPI
 */
@WebServlet("/NoticeAPI")
public class NoticeAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeAPI() {
        super();
    }
    
    Notice noticeObj = new Notice() ;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				 
				String output = noticeObj.insertNotice(request.getParameter("title"),
				request.getParameter("description"),
				request.getParameter("branch"),
				request.getParameter("issuingOfficer"));
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 Map paras = getParasMap(request);
		 String output = noticeObj.updateNotice(paras.get("hidNoticeIDSave").toString(),
		 paras.get("title").toString(),
		 paras.get("description").toString(),
		 paras.get("branch").toString(),
		 paras.get("issuingOfficer").toString());
		 response.getWriter().write(output); 

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Map paras = getParasMap(request);
		 String output = noticeObj.deleteNotice(paras.get("ID").toString());
		 response.getWriter().write(output); 

	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { String[] p = param.split("=");
	 map.put(p[0], p[1]);
	 }
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}

	
	
	
}
