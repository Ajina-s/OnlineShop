package onlineshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class paynow
 */
@WebServlet("/paynow")
public class paynow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public paynow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session=request.getSession(false);
		String id=session.getAttribute("id").toString();
	//	System.out.println(id);
		HttpSession session3=request.getSession(false);
		String pid=session3.getAttribute("pid").toString();
	//	System.out.println(pid);
		HttpSession sessions=request.getSession(false);
		String price=(String)sessions.getAttribute("Price");
	//	System.out.println(price);
		double  Value=Double.parseDouble(price);
	//	System.out.println(Value);
		
		
		String option=request.getParameter("paymentoption");
		double  totalamt=10000.0;
		double amount=totalamt-Value;
		
		
		
		 try 
		{
			    Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
				Statement stmt=con.createStatement();
				
				int i=stmt.executeUpdate("insert into payment (userid,pdtid,paymentoption,totalamt,payableamt,balanceamt)values('"+id+"','"+pid+"','"+option+"','"+totalamt+"','"+price+"','"+amount+"')");
				if(i==1)
				{
					//System.out.println("payment successful");
				}
				
				 Statement stmt1=con.createStatement();
				 i=stmt1.executeUpdate("delete from cart where pdt_id='"+pid+"'");
				 if(i==1)
				 {
					 //System.out.println("deleted successfully");
					 response.sendRedirect("ViewCarts");
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
