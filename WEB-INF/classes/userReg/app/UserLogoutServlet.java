package userReg.app;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;

//import javax.servlet.RequestDispatcher;

import java.io.PrintWriter;
import java.io.IOException;

import userReg.entity.User;
import userReg.core.UserService;

public class UserLogoutServlet extends HttpServlet{
	
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		  resp.setContentType("text/html");
		  HttpSession session = req.getSession(false);
		  Cookie[] cookies = req.getCookies();
		  
		  if(cookies != null){
			  System.out.println("Cookies khali korchhi at user-logout");
			  for(Cookie c: cookies){
				  c.setMaxAge(0);
				  //c.setValue(null);
				  resp.addCookie(c);
			  }
		  }
		  
		  if(session != null){
			  System.out.println("Session invalidate korchhi at user-logout");
			  session.invalidate();
		  }
		  System.out.println("User-Login-e redirect kortesi");
		  resp.sendRedirect("user-login");
		  
	  }
	  
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
	  }
		
}