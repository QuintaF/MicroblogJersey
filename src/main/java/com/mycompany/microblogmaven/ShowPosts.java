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


public class ShowPosts extends HttpServlet {
        @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
                
                
                
                DAO dao = new DAO();
                List<Post> listaPost = dao.getPostDao().findAll();
                List<Commento> listaCommenti = dao.getCommentoDao().findAll();
                
                out.println("<style>"
                        + "body{background-color: #262626;}"
                        + "p{color:darkcyan}"
                        +"h1{color:green;}"
                        +"h3{color:green;}"
                        +"h4{color:green;}"
                        +"h5{color:orange;}"
                        + "</style>");
                out.println("<h1 style=\"color:orange;text-align: center\">Tabella Post</h1>");

                for(Post p: listaPost){
                    out.println("<div>");
                    out.println("<h3>"+p.getTitolo()+"</h3>");
                    out.println("<h4>"+p.getSottotitolo()+"</h4>");
                    out.println("<p>"+p.getDataOra().toString()+"</p>");
                    out.println("<p>"+p.getContenuto()+"</p>");
                    out.println("<div>");
                    out.println("<h5>COMMENTI</h5>");
                    
                    HttpSession session=request.getSession(false);
                    if(session!=null){
                        String name=(String)session.getAttribute("name");
                        out.println("<form action=\"NewComment\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"postId\" value=\""+p.getId()+"\" readonly>");
                        out.println("Inserisci commento: <input type=\"text\" name=\"commento\"> <input type=\"submit\" value=\"Invia\">");
                        out.println("");
                        out.println("</form>");
                    }
                    String str = p.getId().toString();
                    for(Commento c: listaCommenti){
                        if(c.getPostId().equals(str)){
                            out.println("<p>Commento: "+c.getCommento()+ "<br>utente: " +c.getUserId()+ "</p>");
                        }
                    }
                    out.println("</div>");
                    out.println("</div>");
                }
                out.println("</table>");
                
                out.println("</div>");
                out.close();
                
	}

}
