package com.mycompany.microblogmaven;



import java.io.IOException;
import java.io.PrintWriter;

import dao.*;
import entity.*; 

import java.sql.Timestamp;
import java.util.List;
import java.time.LocalDateTime;    

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewComment extends HttpServlet {
        @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
                HttpSession session=request.getSession(false);
                
                DAO dao = new DAO();
                Commento commento = new Commento();
                
                commento.setCommento(request.getParameter("commento").trim());
                commento.setPostId(request.getParameter("postId"));
                commento.setUserId((String) session.getAttribute("name"));
                dao.getCommentoDao().insertCommento(commento);
                
                out.println("<p style=\"color: #ccff99\">Commento aggiunto!</p>");
                out.print("<a href=\"ShowPosts\" style=\"color: orange;text-decoration:none;\">Visualizza Post</a>");
			
        }
}

