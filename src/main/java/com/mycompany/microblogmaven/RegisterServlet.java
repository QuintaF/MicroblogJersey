package com.mycompany.microblogmaven;


import dao.*;
import entity.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import static java.util.Objects.hash;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		    
                double x = Math.random();
                String salt = Hash.generateSalt((int)x*100);
                System.out.println(salt);
                
                int hashedPsw = hash(salt + password);
                System.out.println(hashedPsw);
                
                DAO dao = new DAO();
                Utente utente = new Utente();
		List<Utente> listaUtenti = dao.getUtenteDao().findByName(name);
                if(!listaUtenti.isEmpty()){
                    out.print("<h3 style=\"color:#ff6666\">Errore di registrazione! Utente esistente, cambiare il nome utente!</h3>"); 
                    request.getRequestDispatcher("registration.html").include(request, response);
                }else{
                    utente.setUsername(name);
                    utente.setPassword(hashedPsw);
                    utente.setSalt(salt);
                    dao.getUtenteDao().insertUtente(utente);
                    out.print("<h3 style=\"color: #ccff99\">Registrazione completata correttamente!\nBenvenuto " + name + "</h3>"); 
                    request.getRequestDispatcher("login.html").include(request, response);
                }
                out.close();
	}
        
        

}
