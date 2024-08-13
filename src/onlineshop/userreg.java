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
 * Servlet implementation class userreg
 */
@WebServlet("/userreg")
public class userreg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userreg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		String adress=request.getParameter("adress");
		String Contact=request.getParameter("phone");
		String username=request.getParameter("username");
		String password =request.getParameter("password");
		String role="user";
//		HttpSession session=request.getSession(false);
//    	String id=session.getAttribute("id").toString();
		
		try {
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
			Statement stmt=con.createStatement();
		    int i=stmt.executeUpdate("insert into user (Name,Adress,Contact,usrname,password) values('"+name+"','"+adress+"','"+Contact+"','"+username +"','"+password+"')");
		    if(i==1)
		    {
		    	

System.out.println("inserted successfully");
Statement stmt2=con.createStatement();
ResultSet rs1=stmt2.executeQuery("select max(id) from user");
int id2=0;
while(rs1.next())
{
   id2=rs1.getInt("max(id)");
}
Statement stmt3=con.createStatement();
stmt3.executeUpdate("insert into login(username,password,role,logid)values('"+username+"','"+password+"','"+role+"','"+id2+"')");

	}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		
		}
	}

