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

public class UserProfileServlet extends HttpServlet{
	
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		  
		  HttpSession session = req.getSession(false);
		  if(session == null){
			  resp.sendRedirect("user-login");
		  }else{
			  String userId = (String)session.getAttribute("uid");
			  User user = new UserService().getById(userId);
			  
			  PrintWriter out = resp.getWriter();
			  out.println("<html>");
			  out.println("<head><style>table, th, td {border: 1px solid black;} th, td {padding: 5px;} table {border-collapse: collapse;}</style></head>");
			  out.println("<body>");
			  //out.println("<form method='post'>");
			  
			  out.println("<table>");
				out.println("<tr>");
					out.println("<td colspan='2' style='text-align: center;'>PROFILE</td>");
					//out.println("<th colspan='2'>Action</th>");
				out.println("</tr>");
				
				out.println("<tr>");
					out.println("<td>ID</th>");
					out.println("<td>"+userId+"</th>");
				out.println("</tr>");
				
				out.println("<tr>");
					out.println("<td>NAME</th>");
					out.println("<td>"+user.getName()+"</th>");
				out.println("</tr>");
				
				out.println("<tr>");
					out.println("<td>USER TYPE</th>");
					out.println("<td>"+user.getAdminString()+"</th>");
				out.println("</tr>");
				
				out.println("<tr>");
					out.println("<td colspan='2' style='text-align:right;'><a href='user-home'>Go Home</a></td>");
				out.println("</tr>");
				
			  out.println("</table>");
			  
			  
			  //out.println("</form>");
			  out.println("</body>");
			  out.println("</html>");
			  
			  out.close();
		  }
	  }
	  
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
				
	}
		
}