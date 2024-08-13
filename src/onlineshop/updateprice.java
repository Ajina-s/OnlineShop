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
 * Servlet implementation class updateprice
 */
@WebServlet("/updateprice")
public class updateprice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateprice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pid=request.getParameter("id");
		String price="";
//		 HttpSession session=request.getSession(false);
//		 String id=session.getAttribute("id").toString();
		 price=request.getParameter("price");
		    try 
		     {
				
		    	    Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
					Statement stmt=con.createStatement();
					 int i=stmt.executeUpdate("update pdt_details set price='"+price+"' where id='"+pid+"'");
					if(i==1)
					{
						System.out.println("updated successfully");
						response.sendRedirect("sellerhome");
					
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
