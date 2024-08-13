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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Addcart
 */
@MultipartConfig
@WebServlet("/Addcart")
public class Addcart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addcart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("hii");
		 String pdtid=request.getParameter("id1");
		// System.out.println(pdtid);
		PrintWriter out=response.getWriter();
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
		
		
		//out.print("<h1>hii</h1>");
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from pdt_details where id='"+pdtid+"'");
			
			out.print("<div class='container'>");
			
			while(rs.next())
			{   
				Blob blob=rs.getBlob("image");
				byte[] imageData=blob.getBytes(1, (int) blob.length());
				String base64Image=Base64.getEncoder().encodeToString(imageData);
				String description=rs.getString("description");
				double price=rs.getDouble("price");
			//	System.out.println(base64Image);
				out.print("<div class='row'>");
				out.print("<div class='col-md-3'>");
				out.print("<div class='card'>");
				out.print("<img class='card-img-top' src='data:image/jpeg;base64,"+base64Image+"' alt='card-image'>");
				out.print("<div class='card-body'>");
				out.print("<p class='card-text'>"+description+"</p>");
				out.print("<p class='card-text'>$:"+price+"</p><br>");
				out.print("<form action='addtocart'>" );
			    out.print("<lable for='dropdown'>Select an option</label>");
			    out.print("<select id='dropdown' name='dropdown'>");
			    out.print("<option value='1'>1</option>");
			    out.print("<option value='2'>2</option>");
			    out.print("<option value='3'>3</option>");
			    out.print("<option value='4'>4</option>");
			    out.print("<option value='5'>5</option>");
			    out.print("</select>");
			   
			    out.print("<br><br>");
			    out.print("<input type=\'hidden\' name=\'id\' value=\'"+pdtid+"\'>");
			    out.print("<input type=\'hidden\' name=\'price\' value=\'"+price+"\'>");
			 //   out.print("<input type=\'hidden\' name=\'image\' value=\'"+base64Image+"\'>");
				//out.print("<div class='d-flex justify-content-around mb-2'>");
				 out.print("<button  type='submit' value='Addto cart' class='btn btn-primary'>Add to Cart</button>" );
				//out.print("<a href='addtocart?id1="+rs.getInt("id")+"' class='btn btn-primary'>AddtoCart </a>");
			 //   out.print("<a href='buynow' class='btn btn-warning'>Buy Now </a>");	
			    out.print("</form>");
				out.print("</div>");
				out.print("</div>");
				out.print("</div>");
				out.print("</div>");
				out.print("</div>");
		}
			out.print("</div>");
			
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


