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

public class AddPost extends HttpServlet {
        @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
                   
                HttpSession session=request.getSession(false);
		if(session!=null){
                    request.getRequestDispatcher("post.html").include(request, response);      

                    DAO dao = new DAO();
                    Post post = new Post();
                    post.setTitolo(request.getParameter("title"));
                    post.setSottotitolo(request.getParameter("subtitle"));
                    
                    LocalDateTime now = LocalDateTime.now(); 
                    post.setDataOra(Timestamp.valueOf(now));
                    
                    post.setContenuto(request.getParameter("content"));
                    dao.getPostDao().insertPost(post);
                    
                    out.println("Post aggiunto con successo!");
                }else{
                    
                    DAO dao = new DAO();
                    List<Post> listaPost = dao.getPostDao().findAll();
                    
                    out.println("<table style=\"border-collapse:collapse\">");
                    out.println("<tr>");
                    out.println("<th style=\"border:2px solid orange\">Titolo</th>");
                    out.println("<th style=\"border:2px solid orange\">Sottotitolo</th>");
                    out.println("<th style=\"border:2px solid orange\">DataOra</th>");
                    out.println("<th style=\"border:2px solid orange\">Contenuto</th>");
                    out.println("</tr>");
                    for(Post p: listaPost){
                    out.println("<tr>");
                    out.println("<td style=\"border:2px solid orange\">" + p.getTitolo() + "</td>");
                    out.println("<td style=\"border:2px solid orange\">" + p.getSottotitolo() + "</td>");
                    out.println("<td style=\"border:2px solid orange\">" + p.getDataOra().toString() + "</td>");
                    out.println("<td style=\"border:2px solid orange\">" + p.getContenuto() + "</td>");
                    out.println("</tr>");
                    }
                    out.println("</table>");
                    out.close();
                }
	}

}
