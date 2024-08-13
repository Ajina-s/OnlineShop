package onlineshop;

import java.io.IOException;
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
 * Servlet implementation class vieworders
 */
@WebServlet("/vieworders")
public class vieworders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public vieworders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		 HttpSession session=request.getSession(false);
		 String id=session.getAttribute("id").toString();
		out.print("<!DOCTYPE html>");
		out.print("<html lang='en'>");
		out.print("<head>");
		out.print("    <meta charset='UTF-8'>");
		out.print("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.print("    <title>Document</title>");
		out.print("    <link rel='stylesheet' href='style.css'>");
		out.print("    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css' integrity='sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==' crossorigin='anonymous' referrerpolicy='no-referrer' />");
		out.print("    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js' integrity='sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==' crossorigin='anonymous' referrerpolicy='no-referrer'></script>");
		out.print("    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>");
		out.print("</head>");
		out.print("<body>");
		out.print("    <h1 class='pheading' >My Orders</h1>");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
			Statement stmt1=con.createStatement();
		//	System.out.println("hii");
			//ResultSet str1=stmt1.executeQuery("select * from cart where user_id='"+id+ "'");
			ResultSet rs=stmt1.executeQuery("select pdt_details.image,orders.quantity,orders.pdt_id,pdt_details.price from orders inner join pdt_details on orders.pdt_id=pdt_details.id  where orders.user_id='"+id+"'");
            out.print("<div class='container'>");
			
			while(rs.next())
			{   
				Blob blob=rs.getBlob("image");
				byte[] imageData=blob.getBytes(1, (int) blob.length());
				String base64Image=Base64.getEncoder().encodeToString(imageData);
				double price=rs.getDouble("price");
				String quantity=rs.getString("quantity");
			//	System.out.println(base64Image);
				out.print("<div class='row'>");
				out.print("<div class='col-md-3'>");
				out.print("<div class='card' >");
				out.print("<img class='card-img-top' src='data:image/jpeg;base64,"+base64Image+"' alt='card-image'></a>");
				out.print("<div class='card-body'>");
				out.print("<p class='card-text'>Price: $:"+price+"</p><br>");
				out.print("<p class='card-text'> Quantity:"+quantity+"</p><br>");
				out.print("</div>");
				out.print("</div>");
				out.print("</div>");
				out.print("</div>");
	}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		out.print("</body>");
		out.print("</html>");
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			}

}
