package com.vedant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//to add methods on your own just write doget and dopost and press control + space
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	private static final String query = "INSERT INTO BOOKDATA(Book_id,Student_name,Student_id,Book_name,Date_of_issue,Date_of_return) VALUES (?,?,?,?,?,?)";
   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//get printwriter
	   PrintWriter pw = res.getWriter();
	   //set content type
	   res.setContentType("text/html");
	   //GET THE BOOK INFO
	   String bookName = req.getParameter("bookName");
	   String StudentName = req.getParameter("StudentName");
	   int bookID = Integer.parseInt(req.getParameter("bookID"));
	   String StudentID = req.getParameter("StudentID");
	   String DOIString = req.getParameter("DoI");
	   String DORString = req.getParameter("DoR");
	      
	   
	   
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
		   
		   
		   
	    ps.setInt(1,bookID);
		ps.setString(2,StudentName);
		ps.setString(3,StudentID);
		ps.setString(4,bookName);
		ps.setString(5,DOIString);
		ps.setString(6,DORString);
		int count = ps.executeUpdate();
		if(count==1) {
			pw.println("<h2>Record is registered successfully</h2>" );
		}else {
			pw.println("<h2>Record was not registered successfully");
		}
		
          
          
          
	   }catch(SQLException se) {
		   se.printStackTrace();
		   pw.println("<h1>" + se.getMessage()+"</h2>");
	   }catch(Exception e) {
		   e.printStackTrace();
		   pw.println("<h1>" + e.getMessage()+"</h2>");
	   }
	   
	   pw.println("<a href ='home.html'>Home</a>");
	   pw.println("<br>");
	   pw.println("<a href= 'bookList'>Book List</a>");
	   
   }   
   
   @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
