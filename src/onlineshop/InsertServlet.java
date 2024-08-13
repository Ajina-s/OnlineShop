package onlineshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session=request.getSession(false);
		 String Username=(String) session.getAttribute("username");
		 String Password=(String)session.getAttribute("password");
		
        String address = request.getParameter("adress");
        String phone = request.getParameter("phone");
        String license = request.getParameter("license");
        System.out.println(Username);
        try {
    		
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
			
			Statement stmt2=con.createStatement();
			ResultSet rs1=stmt2.executeQuery("select id from seller where username='"+Username+"' AND password='"+Password+"'");
			int id2=0;
			while(rs1.next())
			{
			   id2=rs1.getInt("id");
			}
			System.out.println(id2);
			Statement stmt=con.createStatement();
			stmt.executeUpdate("insert into sellerdetails(Adress,phonenumber,licensenumber,sid)values('"+address+"','"+phone+"','"+license+"','"+id2+"')");
        
        
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
