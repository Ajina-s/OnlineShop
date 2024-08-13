package onlineshop;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class addtocart
 */
@WebServlet("/addtocart")
 
public class addtocart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addtocart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		String uid=session.getAttribute("id").toString();
		 String pid=request.getParameter("id");
		 HttpSession session1=request.getSession();
		 session.setAttribute("pdtid",pid);
		 String price=request.getParameter("price");
	//	String image=request.getParameter("image");
		 String quantity=request.getParameter("dropdown");
		 Date date=new Date();
		// System.out.println(date);
		 Date currentTime=new Date();
		 String base64Image="";
		 OutputStream outputstream = null;
		 
		 try 
		{
			   Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select image from pdt_details where id='"+pid+"'");
				while(rs.next())
				{
					byte[] imagedata=rs.getBytes("image");
					response.setContentType("image/jpeg");
					 outputstream.write(imagedata);
					
				}
				//System.out.println(outputstream);
				
		} 
		 
		 catch (Exception e) 
		 {
			System.out.println(e);
		}
		 
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
			Statement stmt=con.createStatement();
			
			int i=stmt.executeUpdate("insert into cart(pdt_id,quantity,time,date,user_id,price,image)values('"+pid+"','"+quantity+"','"+currentTime+"','"+date+"','"+uid+"','"+price+"','"+outputstream+"')");
			if(i==1)
			{
				//System.out.println("inserted successfully");
				Statement stmt1=con.createStatement();
				i=stmt.executeUpdate("insert into orders(pdt_id,user_id,image,price,status) values('"+pid+"','"+uid+"','"+outputstream+"','"+price+"','"+i+"')");
				if(i==1)
				{
					System.out.println("inserted successfully");
					response.sendRedirect("userhome");
				}
				
				
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{  
		
		
		
		
		
	}

}
