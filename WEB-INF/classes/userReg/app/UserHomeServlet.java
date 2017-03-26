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

public class UserHomeServlet extends HttpServlet{
	
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		  resp.setContentType("text/html");
		  HttpSession session = req.getSession(false);
		  Cookie[] cookies = req.getCookies();
		  
		  /*
		  if( (cookies==null || cookies.length <= 1) && session == null){
			  resp.sendRedirect("user-login");
		  }
		  else if(session == null){
			  for(Cookie c: cookies){
				  if(c.getName().equalsIgnoreCase("uname")){
					  session.setAttribute("uname", c.getValue());
				  }else if(c.getName().equalsIgnoreCase("uid")){
					  session.setAttribute("uid", c.getValue());
				  }else if(c.getName().equalsIgnoreCase("utype")){
					  session.setAttribute("utype", c.getValue());
				  }
			  }
			  resp.sendRedirect("user-home");
		  }*/
		  if(session == null){
			  resp.sendRedirect("user-login");
		  }
		  else if( session!=null && ((String)session.getAttribute("utype")).equalsIgnoreCase("admin")  ){
			  resp.sendRedirect("admin-home");
		  }else if( session!=null && ((String)session.getAttribute("utype")).equalsIgnoreCase("user")  ){
			  PrintWriter out = resp.getWriter();
			  
			  out.println("<html>");
			  out.println("<body><center>");
			  
			  System.out.println((String)session.getAttribute("uname"));
			  
			  out.println("<h1>Welcome "+(String)session.getAttribute("uname")+"!</h1>");
			  
			  out.println("<a href='user-profile'>Profile</a>");
			  out.println("<br/>");
			  out.println("<a href='user-change-password'>Change Password</a>");
			  out.println("<br/>");
			  out.println("<a href='user-logout'>Logout</a>");
			  
			  out.println("</center></body>");
			  out.println("</html>");
			  
			  out.close();
		  }
	  }
	  
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
				
	}
		
}