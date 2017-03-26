package userReg.app;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

//import javax.servlet.RequestDispatcher;

import java.io.PrintWriter;
import java.io.IOException;

import userReg.entity.User;
import userReg.core.UserService;

public class UserChangePasswordServlet extends HttpServlet{
	
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		  HttpSession session = req.getSession(false);
		  
		  if(session == null){
			  resp.sendRedirect("user-login");
		  }
		  else{
			  PrintWriter out = resp.getWriter();
			  out.println("<html>");
			  out.println("<body>");
			  out.println("<form method='post'>");
			  
			  out.println("<fieldset style='display: inline-block;'>");
			  out.println("<legend>CHANGE PASSWORD</legend>");
			  
			  out.println("Current Password:<br/><input type='password' name='currentpassword' /><br/>");
			  out.println("New Password:<br/><input type='password' name='newpassword' /><br/>");
			  out.println("Retype New Password:<br/><input type='password' name='retypenewpassword' /><br/>");
			  
			  out.println("<br/><br/>");
			  
			  out.println("<input type='submit' name='button' value='Change'/>");
			  out.println("<a href='user-home'>Home</a>");
			  
			  out.println("</fieldset>");
			  out.println("</form>");
			  out.println("</body>");
			  out.println("</html>");
			  
			  out.close();
		  }
	  }
	  
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			if(req.getParameter("button").equalsIgnoreCase("change")){
				//String js = new UserService().hasEmptyFieldsChangePassword( req.getParameter("currentpassword"), req.getParameter("newpassword"), req.getParameter("retypenewpassword") );
				boolean hasEmptyFields = new UserService().hasEmptyFieldsChangePassword( req.getParameter("currentpassword"), req.getParameter("newpassword"), req.getParameter("retypenewpassword") );
				
				if(hasEmptyFields){
					PrintWriter out = resp.getWriter();
					out.println("<script type=\"text/javascript\">"
								+ "alert('Error! One or more fields left empty.');"
								+ "location='user-change-password'"
								+ "</script>"
					
								+ "<noscript type=\"text/javascript\">"
								+ "<a href='user-change-password'>Error! One or more fields left empty. Click to continue</a>"
								+ "</noscript>"
								);
					out.close();
				}
				else{
					String js = new UserService().validateEditPassword( (String)(req.getSession().getAttribute("uid")), req.getParameter("currentpassword"), req.getParameter("newpassword"), req.getParameter("retypenewpassword") );
					
					if(!js.equals("")){
						PrintWriter out = resp.getWriter();
						out.println("<script type=\"text/javascript\">"
									+ "alert('"+js+"');"
									+ "location='user-change-password'"
									+ "</script>"
						
									+ "<noscript type=\"text/javascript\">"
									+ "<a href='user-change-password'>"+js+" Click to continue</a>"
									+ "</noscript>"
									);
						out.close();
					}
				}
			}
	}
		
}