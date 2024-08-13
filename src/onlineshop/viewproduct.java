package onlineshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class viewproduct
 */
@MultipartConfig
@WebServlet("/viewproduct")
public class viewproduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewproduct() {
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
		    p.print("<!DOCTYPE html>");
			p.print("<html lang=\'en\'>");
			p.print("<head>");
			p.print("<meta charset=\'UTF-8\'>");
			p.print("<meta name=\'viewport\' content=\'width=device-width, initial-scale=1.0\'>");
			p.print("<title>Document</title>");
			p.print(" <link rel=\'stylesheet\' href=\'dashboard.css\'>");
			
			p.print("</head>");
			p.print("<body>");
			p.print("<div class='side-menu'>\r\n" + 	"");
			p.print("    <div class='brand-name'>\r\n" + "");
			p.print("        <h1></h1>\r\n" + "");
			p.print("    </div>\r\n" + 
					"");
			p.print("    <ul>\r\n" + 
					"");
			p.print("        <li><a href='#'>&nbsp;Dashboard</li>\r\n" + 
					"");
			p.print("        <li><a href='addproduct.html'>&nbsp;Product</li>\r\n" + 
					"");
			p.print("        <li><a href='viewproduct'>&nbsp;View product</li>\r\n" + 
					"");
			p.print("        <li><a href='#'>&nbsp;Help</li>\r\n" + 
					"");
			p.print("        <li> <a href='#'>&nbsp;Settings</li>\r\n" + 
					"");
			p.print("    </ul>\r\n" + 
					"");
			p.print("</div>\r\n" + 
					"");
			p.print("<div class='container'>\r\n" + 
					"");
			p.print("    <div class='header'>\r\n" + 
					"");
			p.print("        <div class='nav'>\r\n" + 
					"");
			p.print("            <div class='search'>\r\n" + 
					"");
			p.print("                <input type='text' placeholder='Search...'>\r\n" + 
					"");
			p.print("                <button type='submit'><img src='search.png'></button>\r\n" + 
					"");
			p.print("            </div>\r\n" + 
					"");
			p.print("            <div class='user'>\r\n" + 
					"");
			//p.print("                <a href='staffreg.html' class='btn' >Add Staff</a>\r\n" + 
			//		"");
			p.print("                <img src='notifications (1).png'>\r\n" + 
					"");
			p.print("                <div class='img-case'>\r\n" + 
					"");
			p.print("                    <img src='user.png'>\r\n" + 
				"");
			//p.print("        <h2>"+name+"</h2>\r\n" + "");
			p.print("                </div>\r\n" + 
					"");
			p.print("            </div>\r\n" + 
					"");
			p.print("        </div>\r\n" + 
					"");
			p.print("    </div>\r\n" + 
					"");
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
				Statement stmt1=con.createStatement();
				ResultSet str1=stmt1.executeQuery("select * from pdt_details where refid='"+id+"'");
				while(str1.next())
				{
					
					 String imageFileName=str1.getString("image");
					 p.print("<div class='card'>");
					 p.print("<img src='C:/Users/user/eclipse-workspace/.metadata/E-commerce/WebContent/images /'"+imageFileName+">");
				}
			}
				catch (Exception e)
				{
					System.out.println(e);
				}

			p.print("</div>\r\n" + 
					"");
			
					p.print("</body>");
			p.print("</html>");
			
}
	
	
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		

					
		
}
}

