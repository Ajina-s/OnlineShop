package onlineshop;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class sellerreg
 */
@WebServlet("/sellerreg")
public class sellerreg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sellerreg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String username=request.getParameter("username");
		String password =request.getParameter("password");
		HttpSession session1=request.getSession();
		session1.setAttribute("username", username);
		session1.setAttribute("password", password);
		
		String role="seller";
		try {
		
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
				Statement stmt=con.createStatement();
			    int i=stmt.executeUpdate("insert into seller (Name,username,password) values('"+name+"','"+username +"','"+password+"')");
			    if(i==1)
			    {
			    	//System.out.println("inserted successfully");
			    	
					
					//System.out.println(adress);
			    	
	
	System.out.println("inserted successfully");
	Statement stmt2=con.createStatement();
	ResultSet rs1=stmt2.executeQuery("select max(id) from seller");
	int id2=0;
	while(rs1.next())
	{
	   id2=rs1.getInt("max(id)");
	}
	Statement stmt3=con.createStatement();
	stmt3.executeUpdate("insert into login(username,password,role,logid)values('"+username+"','"+password+"','"+role+"','"+id2+"')");

	response.sendRedirect("sellerdetails.html");
}
			  			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
