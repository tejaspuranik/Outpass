package outpass.service;

import java.sql.DriverManager;
import java.sql.SQLException;

import outpass.model.User;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class RegisterService 
{

	public boolean verifyLogin(User user)
	{
		  String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		     String DB_URL = "jdbc:mysql://localhost:3306/outpass";

		   //  Database credentials
		     String USER = "root";
		     String PASS = "outpasstejas";
		
		   java.sql.Connection conn = null;
		   Statement stmt = null;
		   String email = user.getEmail();
		   try{
			      //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
	
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			      System.out.println("Connected database successfully...");
			      
			      System.out.println("Inserting records into the table...");
			      stmt = (Statement) conn.createStatement();
			      
			      String sql = "INSERT INTO Marketing_Users (email)" +
			    	        "VALUES (?)";
			    	
			    	java.sql.PreparedStatement preparedStatement = conn.prepareStatement(sql);
			    	preparedStatement.setString(1, email);
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
