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
 * Servlet implementation class buynow
 */
@WebServlet("/buynow")
public class buynow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buynow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter p=response.getWriter();
		HttpSession session=request.getSession(false);
		String id=session.getAttribute("id").toString();
		
		HttpSession sessions=request.getSession(false);
		String Price=sessions.getAttribute("Price").toString();
		
		//int prices=Integer.parseInt("Price");
		//System.out.println(prices);
//		HttpSession session2=request.getSession();
//		
//		session2.setAttribute("price", prices);
		String pdtid=request.getParameter("id");
		
		//System.out.println(pdtid);
		HttpSession session3=request.getSession();
		session3.setAttribute("pid", pdtid);
		
		String adress="";
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
			Statement stmt1=con.createStatement();
		//	System.out.println("hii");
			ResultSet str1=stmt1.executeQuery("select adress from sellerdetails where sid='"+id+"'" );
		//	System.out.println("hii");
			while(str1.next())
			{   
		          adress=str1.getString("Adress");
			}
			//System.out.println(adress);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		 p.print("<!DOCTYPE html>");
			p.print("<html lang=\'en\'>");
			p.print("<head>");
			p.print("<meta charset=\'UTF-8\'>");
			p.print("<meta name=\'viewport\' content=\'width=device-width, initial-scale=1.0\'>");
			p.print("<title>Document</title>");
			p.print("</head>");
			p.print("<body>");
			p.print("<body>");
			p.print("<form action='paynow'>");
			p.print("<table border='1'>");
			p.print("<tr>");
			p.print("<td>Adress:</td><td>"+adress+"</td></tr>");
			p.print("<tr>");
			p.print("<td>Price:</td><td>"+Price+"</td></tr>");
			p.print("<tr><td>Payment Options:</td><td><input type='radio' name='paymentoption' value='Credit'>Credit");
			p.print("<input type='radio' name='paymentoption' value='gpay'>Gpay");
			p.print("<input type='radio' name='paymentoption' value='cash'>Cash On Delivery");
			p.print("</td></tr>");
			// p.print("<input type=\'hidden\' name=\'id\' value=\'"+pdtid+"\'>");
			p.print("</tbody>");
			p.print(" </table>");
			p.print("<button  type='submit' >Pay Now</button>" );
			p.print("</html>");
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
