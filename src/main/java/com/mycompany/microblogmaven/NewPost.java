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

public class NewPost extends HttpServlet {
        @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
                    
                if(request.getParameter("title").trim().equals("") || request.getParameter("content").trim().equals(""))
                {
                    out.println("Impossibile aggiungere il post!Il titolo e il contenuto non possono rimanere vuoti");
                    request.getRequestDispatcher("post.html").include(request, response);
                    out.print("<a href=\"ShowPosts\" style=\"color: orange;text-decoration:none;\">Visualizza Post</a>");
		
                }else{
                    out.print("<a href=\"ShowPosts\" style=\"color: orange;text-decoration:none;\">Visualizza Post</a>");		
                    request.getRequestDispatcher("post.html").include(request, response);      
                    DAO dao = new DAO();
                    Post post = new Post();
                    post.setTitolo(request.getParameter("title").trim());
                    post.setSottotitolo(request.getParameter("subtitle").trim());
                   
                    LocalDateTime now = LocalDateTime.now(); 
                    post.setDataOra(Timestamp.valueOf(now));
                    
                    post.setContenuto(request.getParameter("content").trim());
                    dao.getPostDao().insertPost(post);
                    
                    out.println("Post aggiunto con successo!");
                }    
        }
}

