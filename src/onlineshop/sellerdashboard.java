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
 * Servlet implementation class sellerdashboard
 */
@WebServlet("/sellerdashboard")
public class sellerdashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sellerdashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
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
	
		p.print("</div>\r\n" + 
				"");
		
				p.print("</body>");
		p.print("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
