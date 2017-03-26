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

public class AdminHomeServlet extends HttpServlet{
	
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		  
		  HttpSession session = req.getSession(false);
		  Cookie[] cookies = req.getCookies();
		  
		  if(session == null){
			  resp.sendRedirect("user-login");
		  }else if(  ((String)session.getAttribute("utype")).equalsIgnoreCase("user")  ){
			  resp.sendRedirect("user-home");
		  }else if(  ((String)session.getAttribute("utype")).equalsIgnoreCase("admin")  ){
			  PrintWriter out = resp.getWriter();
			  
			  out.println("<html>");
			  out.println("<body><center>");
			  
			  System.out.println((String)session.getAttribute("uname"));
			  
			  out.println("<h1>Welcome "+(String)session.getAttribute("uname")+"!</h1>");
			  //out.println("<br/>");
			  
			  out.println("<a href='user-profile'>Profile</a>");
			  out.println("<br/>");
			  out.println("<a href='user-change-password'>Change Password</a>");
			  out.println("<br/>");
			  out.println("<a href='user-list'>View Users</a>");
			  out.println("<br/>");
			  out.println("<a href='user-logout'>Logout</a>");
			  //out.println("<a href='user-login'>Sign In</a>");
			  
			  out.println("</center></body>");
			  out.println("</html>");
			  
			  out.close();
		  }
	  }
	  
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
				
	}
		
}