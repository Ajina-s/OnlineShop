package onlineshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Username=request.getParameter("username");
		String Password =request.getParameter("password");
	//	System.out.println(Username);
	//	System.out.println(Password);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select logid,role from login where username='"+Username+"' AND password= '"+Password+"'");
			 int id=0;
			 String role="null";
			 while(rs.next())
			 {
				 id = rs.getInt("logid");
				 role=rs.getString("role");
			 }
		//	 System.out.println(id);
		//	 System.out.println(role);
			 if(id>0)
			 {
				HttpSession session=request.getSession();
				session.setAttribute("id", id);
				switch(role)
				{
					case "seller":
						//System.out.println("logined");
						response.sendRedirect("sellerhome");
						break;
					case "user":
						response.sendRedirect("userhome");
						break;
		} 
			 }
			 else
				{
					System.out.println("Login failed");
					response.sendRedirect("login.html");
				}
		}
		catch (Exception e)
		{
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
