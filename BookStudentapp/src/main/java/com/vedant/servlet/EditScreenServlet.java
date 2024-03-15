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


@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
	private static final String query = "SELECT Book_id,Student_name,Student_id,Book_name,Date_of_issue,Date_of_return FROM BOOKDATA where Book_id=?";
	   @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get printwriter
		   PrintWriter pw = res.getWriter();
		   //set content type
		   res.setContentType("text/html");
		 //get the id of record
		   int id = Integer.parseInt(req.getParameter("id"));
		   
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
			   ps.setInt(1, id);
		    ResultSet rs = ps.executeQuery();
		    rs.next();
		    pw.println("<form action ='editURL?id="+id+"' method ='post'>");
              pw.println("<table align = 'center'>");
              pw.println("<tr>");
              pw.println("<td>Book ID</td>");
              pw.println("<td><input type = 'text' name = 'bookID' value ='" + rs.getInt(1) + "'></td>");
              pw.println("</tr>");
              pw.println("<tr>");
              pw.println("<td>Student Name</td>");
              pw.println("<td><input type = 'text' name = 'StudentName' value ='" + rs.getString(2) + "'></td>");
              pw.println("</tr>");
              pw.println("<tr>");
              pw.println("<td>Student ID</td>");
              pw.println("<td><input type = 'text' name = 'StudentID' value ='" + rs.getString(3) + "'></td>");
              pw.println("</tr>");
              pw.println("<tr>");
              pw.println("<td>Book Name</td>");
              pw.println("<td><input type = 'text' name = 'bookName' value ='" + rs.getString(4) + "'></td>");
              pw.println("</tr>");
              pw.println("<tr>");
              pw.println("<td>Date of Issue</td>");
              pw.println("<td><input type = 'text' name = 'DoI' value ='" + rs.getString(5) + "'></td>");
              pw.println("</tr>");
              pw.println("<tr>");
              pw.println("<td>Date of Return</td>");
              pw.println("<td><input type = 'text' name = 'DoR' value ='" + rs.getString(6)  +"'></td>");
              pw.println("</tr>");
              pw.println("<tr>");
              
              pw.println("<td><input type = 'submit' value ='EDIT' ></td>");
              pw.println("<td><input type = 'reset' value ='CANCEL' ></td>");
              pw.println("</tr>");
              pw.println("</table>");
              pw.println("</form>");
	          
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
