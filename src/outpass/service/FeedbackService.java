package outpass.service;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import outpass.model.Feedback;

public class FeedbackService {

	public boolean verifyFeedback(Feedback feedback) {
		 String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	     String DB_URL = "jdbc:mysql://localhost:3306/outpass";

	   //  Database credentials
	     String USER = "root";
	     String PASS = "outpasstejas";
	
	   java.sql.Connection conn = null;
	   Statement stmt = null;
	   String email = feedback.getEmail();
	   String fullname=feedback.getFullname();
	   String message=feedback.getMessage();
	   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      System.out.println("Inserting records into the table...");
		      stmt = (Statement) conn.createStatement();
		      
		      String sql = "INSERT INTO Feedback (fullname,email,message)" +
		    	        "VALUES (?,?,?)";
		    	
		    	java.sql.PreparedStatement preparedStatement = conn.prepareStatement(sql);
		    	preparedStatement.setString(1, fullname);
		    	preparedStatement.setString(2, email);
		    	preparedStatement.setString(3, message);
		    	preparedStatement.executeUpdate(); 
		      
		      System.out.println("Inserted records into the table...");

	   	 }
	   catch(SQLException se)
	   {
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }
	   catch(Exception e)
	   {
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
	   finally
	   {
	      //finally block used to close resources
		      try
		      {
		         if(stmt!=null)
		            conn.close();
		      }
		      catch(SQLException se)
		      {
		      }// do nothing
		      try
		      {
		         if(conn!=null)
		            conn.close();
		      }
		      catch(SQLException se)
		      {
		         se.printStackTrace();
		      }
	   }
	//end JDBCExample
	/*if(user.getUserId().equals("tejas") && user.getPassword().equals("tejas"))
	{
		return true;
	}
	return false;*/
	   return true;
   }
}

