package onlineshop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
 * Servlet implementation class addproduct
 */
@MultipartConfig
@WebServlet("/addproduct")


public class addproduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addproduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 HttpSession session=request.getSession(false);
		 String id=session.getAttribute("id").toString();
		
		    String name = request.getParameter("name");
	        String description = request.getParameter("description");
	        double price = Double.parseDouble(request.getParameter("price"));
	        String quantity=request.getParameter("dropdown");
	        
	       
	       
	        try {
	        	
	        	Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
				
				 Part filePart = request.getPart("image");
				 InputStream imageStream=filePart.getInputStream();
			      String sql="insert into pdt_details (name,description,image,price,refid,Quantity)values(?,?,?,?,?,?)";
			       PreparedStatement statement=con.prepareStatement(sql);
			        statement.setString(1, name);
			        statement.setString(2, description);
			        statement.setBlob(3, imageStream);
			        statement.setDouble(4, price);
			        statement.setString(5, id);
			        statement.setString(6, quantity);
			        statement.executeUpdate();
			        statement.close();
					
			       
				
			        System.out.println("inserted success");

	        } 
	        
	        catch (Exception e) {
				System.out.println(e);
			}
	        
//	        String imageFileName=file.getSubmittedFileName();
	     //   System.out.println("selected image is "+imageFileName);
//	        String uploadPath="C:/Users/user/eclipse-workspace/.metadata/E-commerce/images/"+imageFileName;
//	        try {
//	        FileOutputStream fos=new FileOutputStream(uploadPath);
//	        InputStream is=file.getInputStream();
//	        byte[] data=new byte[is.available()];
//	        		is.read(data);
//	        fos.write(data);
//	        fos.close();
//	        }
//	        
//	        catch (Exception e) {
//				e.printStackTrace();
//			}
//	       
//	        try
//	        {
//	        	
//	        	Class.forName("com.mysql.jdbc.Driver");
//				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
//				Statement stmt=con.createStatement();
//				int i=stmt.executeUpdate("insert into pdt_details (name,description,image,price,refid)values('"+name+"','"+description+"','"+imageFileName+"','"+price+"','"+id+"')");
//				if(i==1)
//				{
//					System.out.println("added");
//				}
//			} 
//	        catch (Exception e) {
//				System.out.println(e);
//			}
//	        
	        
	        
//	        InputStream inputStream=null;
//	        PreparedStatement statement=null;
//	        if(p!=null)
//	        {
//	        	inputStream=p.getInputStream();
//	        }
	        	       
//	        try {
//	        	
//	        	
//				Class.forName("com.mysql.jdbc.Driver");
//				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","admin123");
//				
//				String sql="insert into pdt_details (name,description,image,price,refid)values(?,?,?,?,?)";
//				statement =con.prepareStatement(sql);
//				statement.setString(1, name);
//				statement.setString(2, description);
//				if(inputStream!=null)
//				{
//					statement.setBlob(3, inputStream);	
//					
//				}
//				
//			    statement.setDouble(4, price);
//			    statement.setString(5,id);
//				statement.executeUpdate();
//				System.out.println("added");
//	        }
//				
//	        
//
//	    	catch (Exception e)
//			{
//				System.out.println(e);
//			}
//	}

}
}
