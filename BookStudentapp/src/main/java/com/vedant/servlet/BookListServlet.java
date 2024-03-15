package com.vedant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
	private static final String query = "SELECT Book_id,Student_name,Student_id,Book_name,Date_of_issue,Date_of_return FROM BOOKDATA";
	   @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get printwriter
		   PrintWriter pw = res.getWriter();
		   //set content type
		   res.setContentType("text/html");
		 
		   
		   //jdbc connection
		   try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnf) {
			// TODO Auto-generated catch block
			cnf.printStackTrace();
		}
		   //generate the connection
		   try(Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","Vedant@12345");
				   PreparedStatement ps = con.prepareStatement(query);) {
			   
		    ResultSet rs = ps.executeQuery();
            pw.println("<table border='1' align = 'center'>");
            pw.println("<tr>");
            pw.println("<th>Book Id</th>");
            pw.println("<th>Student name</th>");
            pw.println("<th>Student Id</th>");
            pw.println("<th>Book name</th>");
            pw.println("<th>Date of Issue</th>");
            pw.println("<th>Date of Return</th>");
            pw.println("<th>EDIT</th>");
            pw.println("<th>DELETE</th>");
            pw.println("</tr>");
            
            
		    
		    while(rs.next()) {
		    	  pw.println("<tr>"); 
		    	  pw.println("<td>"+rs.getInt(1)+"</td>");
		    	  pw.println("<td>"+rs.getString(2)+"</td>");
		    	  pw.println("<td>"+rs.getString(3)+"</td>");
		    	  pw.println("<td>"+rs.getString(4)+"</td>");
		    	  pw.println("<td>"+rs.getString(5)+"</td>");
		    	  pw.println("<td>"+rs.getString(6)+"</td>");
		    	  pw.println("<td><a href = 'editScreen?id=" + rs.getInt(1) + "'>Edit</a></td>");
		    	  pw.println("<td><a href='deleteurl?id=" + rs.getInt(1) + "'>Delete</a></td>");

		    	  
		    	  pw.println("</tr>");
		    	
		    }
	          
		    pw.println("</table>");
	          
		   }catch(SQLException se) {
			   se.printStackTrace();
			   pw.println("<h1>" + se.getMessage()+"</h2>");
		   }catch(Exception e) {
			   e.printStackTrace();
			   pw.println("<h1>" + e.getMessage()+"</h2>");
		   }
		   pw.println("<a href ='home.html'>Home</a>");
	   }   
	   
	   @Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doGet(req,res);
		}

}
