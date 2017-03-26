package userReg.app;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.PrintWriter;
import java.io.IOException;

import java.util.List;

import userReg.entity.User;
import userReg.core.UserService;

public class UserListServlet extends HttpServlet{
	
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		  
		  HttpSession session = req.getSession(false);
		  if(session == null){
			  resp.sendRedirect("user-login");
		  }else if(  ((String)session.getAttribute("utype")).equalsIgnoreCase("user")  ){
			  resp.sendRedirect("user-home");
		  }else if(  ((String)session.getAttribute("utype")).equalsIgnoreCase("admin")  ){
			  PrintWriter out = resp.getWriter();
			  
			  List<User> UserList = new UserService().getAll();
			  
			  
			  out.println("<html>");
			  out.println("<head><style>table, th, td {border: 1px solid black;} th, td {padding: 5px;} table {border-collapse: collapse;}</style></head>");
			  out.println("<body>");
			  //out.println("<form method='post'>");
			  
			  out.println("<table>");
				out.println("<tr>");
					out.println("<td colspan='3' style='text-align: center;'>USERS</td>");
					//out.println("<th colspan='2'>Action</th>");
				out.println("</tr>");
				
				out.println("<tr>");
					out.println("<th>ID</th>");
					out.println("<th>Name</th>");
					out.println("<th>User Type</th>");
					//out.println("<th colspan='2'>Action</th>");
				out.println("</tr>");
				
				for(User u:UserList){
					out.println("<tr>");
						out.println("<td>"+u.getId()+"</td>");
						out.println("<td>"+u.getName()+"</td>");
						out.println("<td>"+u.getAdminString()+"</td>");
					out.println("</tr>");
				}
				out.println("<tr>");
					out.println("<td colspan='3' style='text-align:right;'><a href='user-home'>Go Home</a></td>");
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