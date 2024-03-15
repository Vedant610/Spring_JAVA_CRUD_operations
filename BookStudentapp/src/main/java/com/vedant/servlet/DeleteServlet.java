package com.vedant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
	private static final String query = "delete from bookdata where Book_id = ?";
	   @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get printwriter
		   PrintWriter pw = res.getWriter();
		   //set content type
		   res.setContentType("text/html");
		 //get the id of record
		   int id = Integer.parseInt(req.getParameter("id"));
		   // get the edit data which you want to edit
		
		      
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
			 ps.setInt(1,id);
				int count = ps.executeUpdate();
				if(count==1) {
					pw.println("<h2>Record was Deleted successfully</h2>" );
				}else {
					pw.println("<h2>Error occurs in deleting records");
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
