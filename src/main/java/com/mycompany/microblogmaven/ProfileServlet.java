package com.mycompany.microblogmaven;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class ProfileServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
		
		HttpSession session=request.getSession(false);
		if(session!=null){
		String name=(String)session.getAttribute("name");
		
                out.print("<a href=\"ShowPosts\" style=\"color: orange;text-decoration:none;\">Visualizza Post</a>");
                request.getRequestDispatcher("post.html").include(request, response); 
		}
		else{
			out.print("<h3 style=\"color:#ffff80\">Please login or registrate first</h3>");
                        out.print("<a href=\"ShowPosts\" style=\"color: orange;text-decoration:none;\">Visualizza Post</a>");
			request.getRequestDispatcher("registration.html").include(request, response);                        
                }
		out.close();
	}
}
