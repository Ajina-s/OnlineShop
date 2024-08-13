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
 * Servlet implementation class changeprice
 */
@WebServlet("/changeprice")
public class changeprice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeprice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pdtid=request.getParameter("id1");
		HttpSession session1=request.getSession();
		session1.setAttribute("pid", pdtid);
		PrintWriter p=response.getWriter(); 
	  //  HttpSession session=request.getSession(false);
	    String price="";
	  //  String id=session.getAttribute("id").toString();
	    try 
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
			Statement stmt=con.createStatement();
			ResultSet str=stmt.executeQuery("select * from pdt_details  where id='"+pdtid+"'");
			while(str.next())
			{
				
				 price=str.getString("price");
			}	
				//	System.out.println(Name);
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
		
		p.print(" <link rel=\'stylesheet\' href=''>");
		p.print("</head>");
		p.print("<body>");
	
		p.print("<div class=\'registration-form\'>\r\n" + 
				"");
		//p.print("<h1>Change  Password</h1>");
		p.print("<form action='updateprice'");
		p.print("<p>Price</p>");
		p.print("<input type=\'number\' name=\'price\' value=\'"+price+"\'>");
		p.print("<input type=\'hidden\' name=\'id\' value=\'"+pdtid+"\'>");

		p.print("<button type=\'submit\'>Update</button>");
		//p.print("<a href='Addcart?id1="+str.getInt("id")+"'> <i class='fas fa-shopping-cart'></i> </a>");

		p.print("</form>");
		p.print("</div>\r\n" + 
				"");
		p.print("</body>");
		p.print("</html>");

     
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
