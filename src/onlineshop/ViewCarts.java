package onlineshop;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewCarts
 */
@WebServlet("/ViewCarts")
public class ViewCarts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCarts() {
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
		    String base64Image="";
		    String price="";
		 //   System.out.println(id);
		    p.print("<!DOCTYPE html>");
			p.print("<html lang=\'en\'>");
			p.print("<head>");
			p.print("<meta charset=\'UTF-8\'>");
			p.print("<meta name=\'viewport\' content=\'width=device-width, initial-scale=1.0\'>");
			p.print("<title>Document</title>");
			
			p.print(" <link rel=\'stylesheet\' href=\'table.css\'>");
			p.print("</head>");
			p.print("<body>");
			p.print("<table class='content-table'>");
			p.print(" <thead>");
			p.print("<tr>");
			p.print(" <th>Image</th>");
			p.print(" <th>Price</th>");
			p.print("<th>Quantity</th>");
			p.print("<th></th>");
			p.print("</tr>");
			p.print("</thead>");
			p.print("<tbody>");
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
				Statement stmt1=con.createStatement();
			//	System.out.println("hii");
				//ResultSet str1=stmt1.executeQuery("select * from cart where user_id='"+id+ "'");
				ResultSet str1=stmt1.executeQuery("select pdt_details.image,cart.quantity,cart.pdt_id,pdt_details.price from cart inner join pdt_details on cart.pdt_id=pdt_details.id  where cart.user_id='"+id+"'");

			//	System.out.println("hii");
				while(str1.next())
				{   
					Blob blob=str1.getBlob("image");
					InputStream input=blob.getBinaryStream();
					ByteArrayOutputStream outputstream1=new ByteArrayOutputStream ();
					byte[] buffer=new byte[4096];
					int bytesRead=-1;
					while((bytesRead=input.read(buffer))!=-1) {
						outputstream1.write(buffer,0,bytesRead);
					}
					byte[] imageBytes=outputstream1.toByteArray();
					base64Image=Base64.getEncoder().encodeToString(imageBytes);
					//System.out.println(base64Image);
					 price=str1.getString("price");
					
					//System.out.println(price);
					String quantity=str1.getString("quantity");
				//	System.out.println(quantity);
					
					p.print("<tr>");
					
					p.print("<td><img src='data:image/jpeg;base64,"+base64Image+"'></td><td>"+price+"</td><td>"+quantity+"</td>");
					//p.print("<td>"+" <a href='buynow' class='btn1' >Buy Now</a>"+"</td>");	
				   // p.print("<td>"+" <a href='Viewprofile?id2="+str1.getInt("id")+"' class='btn1' >Viewprofile</a>"+"</td>");
					p.print("<td>"+"<a href='buynow?id="+str1.getInt("pdt_id")+"'  class='btn1'>Buynow</a>"+"</td>");
					p.print("</tr>");
					input.close();
					outputstream1.close();
					
			}
				HttpSession sessions=request.getSession();
				sessions.setAttribute("Price", price);
				str1.close();
				con.close();
				
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
			p.print("</tbody>");
			p.print(" </table>");
			p.print("</div>\r\n" + 
					"");
			
	}
	
	

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
