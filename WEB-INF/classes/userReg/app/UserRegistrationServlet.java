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

public class UserRegistrationServlet extends HttpServlet{
	
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		  resp.setContentType("text/html");
		  HttpSession session = req.getSession(false);
		  Cookie[] cookies = req.getCookies();
		  
		  if(session != null){
			  session.invalidate();
		  }
		  if(cookies!=null && cookies.length > 1){
			  for(Cookie c: cookies){
				  c.setMaxAge(0);
				  resp.addCookie(c);
			  }
		  }
		  
		  PrintWriter out = resp.getWriter();
		  out.println("<html>");
		  out.println("<body>");
		  out.println("<form method='post'>");
		  
		  out.println("<fieldset style='display: inline-block;'>");
		  out.println("<legend>REGISTRATION</legend>");
		  out.println("Id:<br/><input type='text' name='id' /><br/>");
		  out.println("Name:<br/><input type='text' name='name' /><br/>");
		  out.println("Password:<br/><input type='password' name='password' /><br/>");
		  out.println("Confirm Password:<br/><input type='password' name='confirmpassword' /><br/>");
		  out.println("User type:<br/><input type='radio' name='admin' value='admin' />Admin");
		  out.println("<br/><input type='radio' name='admin' value='user' />User");
		  out.println("<br/><br/>");
		  
		  out.println("<input type='submit' name='button' value='Sign Up'/>");
		  out.println("<a href='user-login'>Sign In</a>");
		  
		  out.println("</fieldset>");
		  out.println("</form>");
		  out.println("</body>");
		  out.println("</html>");
		  
		  out.close();
	  }
	  
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		boolean hasEmptyFields = new UserService().hasEmptyFieldsRegistration(req.getParameter("id"), req.getParameter("name"), req.getParameter("password"), req.getParameter("confirmpassword"), req.getParameter("admin"));
		
		if(hasEmptyFields){
			PrintWriter out = resp.getWriter();
			out.println("<script type=\"text/javascript\">"
						+ "alert('Error! One or more fields left empty.');"
						+ "location='user-registration'"
						+ "</script>"
			
						+ "<noscript type=\"text/javascript\">"
						+ "<a href='user-registration'>Error! One or more fields left empty. Click to continue</a>"
						+ "</noscript>"
						);
			out.close();
		}
		
		else{
		//if( req.getParameter("button").equalsIgnoreCase("sign up") ){
			boolean admin = (req.getParameter("admin").equalsIgnoreCase("admin") ? true:false);
			
			User user = new User(req.getParameter("id"), req.getParameter("name"), req.getParameter("password"), admin);
			String js = new UserService().validateAdd(user, req.getParameter("confirmpassword"));
			
			if(!js.equals("")){
				PrintWriter out = resp.getWriter();
				out.println("<script type=\"text/javascript\">"
						+ "alert('"+js+"');"
						+ "location='user-registration'"
						+ "</script>"
			
						+ "<noscript type=\"text/javascript\">"
						+ "<a href='user-registration'>"+js+". Click to continue</a>"
						+ "</noscript>"
						);
				out.close();
			}
			else{
				//System.out.println("Done clicked");
				//System.out.println(new UserService().add(user));
				 resp.sendRedirect("user-login");
				 //RequestDispatcher rd=req.getRequestDispatcher("user-login");  
				 //rd.include(req, resp);  
			}
		//}
		}
				
	}
		
}