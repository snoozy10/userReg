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

public class UserLoginServlet extends HttpServlet{
	
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		  resp.setContentType("text/html");
		  HttpSession session = req.getSession(false);
		  Cookie[] cookies = req.getCookies();
		  
		  
		  System.out.println("UserLoginServlet 25 doGet");
		  if(session == null && (cookies==null || cookies.length <= 1)){
			  System.out.println("UserLoginServlet 25 doGet COOKIES NULL");
			  PrintWriter out = resp.getWriter();
			  out.println("<html>");
			  out.println("<body>");
			  out.println("<form method='post'>");
			  
			  out.println("<fieldset style='display: inline-block;'>");
			  out.println("<legend>LOGIN</legend>");
			  
			  out.println("User Id:<br/><input type='text' name='id' /><br/>");
			  out.println("Password:<br/><input type='password' name='password' /><br/>");
			  
			  out.println("<input type='checkbox' name='rememberme' value='rememberme' /> Remember Me");
			  out.println("<br/><br/>");
			  
			  out.println("<input type='submit' name='button' value='Login'/>");
			  out.println("<a href='user-registration'>Register</a>");
			  
			  out.println("</fieldset>");
			  out.println("</form>");
			  out.println("</body>");
			  out.println("</html>");
			  
			  out.close();
		  }else if(session == null && cookies.length > 1){
			  System.out.println("UserLoginServlet 52 setting session from cookies");
			  session = req.getSession();
			  for(Cookie c:cookies){
				  if(c.getName().equalsIgnoreCase("uname")){
					  session.setAttribute("uname", c.getValue());
				  }else if(c.getName().equalsIgnoreCase("uid")){
					  session.setAttribute("uid", c.getValue());
				  }else if(c.getName().equalsIgnoreCase("utype")){
					  session.setAttribute("utype", c.getValue());
				  } 
			  }
			  System.out.println("UserLoginServlet 63 DONE setting session from cookies");
			  resp.sendRedirect("user-home");
		  }else{
			  System.out.println("UserLoginServlet 66 session already exists");
			  resp.sendRedirect("user-home");
			  /*RequestDispatcher rd=req.getRequestDispatcher("user-home");  
			  rd.include(req, resp); */
		  }
	  }
	  
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		boolean hasEmptyFields = new UserService().hasEmptyFieldsLogin( req.getParameter("id"), req.getParameter("password") );
		
		if(hasEmptyFields){
			PrintWriter out = resp.getWriter();
			out.println("<script type=\"text/javascript\">"
						+ "alert('Error! One or more fields left empty.');"
						+ "location='user-login'"
						+ "</script>"
			
						+ "<noscript type=\"text/javascript\">"
						+ "<a href='user-login'>Error! One or more fields left empty. Click to continue</a>"
						+ "</noscript>"
						);
			out.close();
		}
		
		else{
		//if( req.getParameter("button").equalsIgnoreCase("sign up") ){
			String js = new UserService().validateLogin(req.getParameter("id"), req.getParameter("password"));
			
			if(!js.equals("")){
				PrintWriter out = resp.getWriter();
				out.println("<script type=\"text/javascript\">"
						+ "alert('"+js+"');"
						+ "location='user-login'"
						+ "</script>"
			
						+ "<noscript type=\"text/javascript\">"
						+ "<a href='user-login'>"+js+". Click to retry.</a>"
						+ "</noscript>"
						);
				out.close();
			}
			else{
				User user = new UserService().getById( req.getParameter("id") );
				
				HttpSession session = req.getSession();
				session.setAttribute("uname", user.getName());
				session.setAttribute("uid", req.getParameter("id"));
				session.setAttribute("utype", user.getAdminString());
				
				if(req.getParameterValues("rememberme")!=null){
					Cookie uname = new Cookie("uname", user.getName());
					Cookie uid = new Cookie("uid", req.getParameter("id"));
					Cookie utype = new Cookie("utype", user.getAdminString());
					
					int age= 7*24*3600;
					
					uname.setMaxAge(age);
					uid.setMaxAge(age);
					utype.setMaxAge(age);
					
					resp.addCookie(uname);
					resp.addCookie(uid);
					resp.addCookie(utype);
				}
				
				System.out.println("I should be here cookie session handled user-login 132");
				resp.sendRedirect("user-home");
			}
		//}
		}
				
	}
		
}