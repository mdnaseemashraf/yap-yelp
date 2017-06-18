package database_connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersTable 
{
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/yap";
		
	//  Database credentials
	static final String USER = "root";
	static final String PASS = "password";
	
	public static boolean addUser(String user_name, String email, String password)
	{
		   int rs=0;
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      Class.forName("com.mysql.jdbc.Driver");

		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      stmt = conn.createStatement();
		      
		      String sql;		      
		      sql = "INSERT INTO users VALUES (default, '" + user_name + "', '" + email + "','"+ password +"');";
		      rs = stmt.executeUpdate(sql);		      
		      
		      stmt.close();
		      conn.close();
		   }
		   catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
		   catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		   finally
		   {
		      //finally block used to close resources
		      try
		      {
		         if(stmt!=null)
		            stmt.close();
		      }
		      catch(SQLException se2)
		      {
		      
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		   
		   if(rs==1)
	       {
	    	  return true;
	       }
		   else
		   {
			   return false;
		   }
		}
	
	public static boolean deleteUser(String user_name, String password)
	{
		int rs=0;
		Connection conn = null;
		Statement stmt = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
			String sql;			
			sql = "DELETE FROM users WHERE user_name='" + user_name + "' AND password='" + password + "';";
			rs = stmt.executeUpdate(sql);		      
			
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			//finally block used to close resources
			try
			{
				if(stmt!=null)
					stmt.close();
			}
			catch(SQLException se2)
			{
				
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		if(rs==1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean updatePassword(String user_name, String password_new, String password_old)
	{
		int rs=0;
		Connection conn = null;
		Statement stmt = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
			String sql;			
			sql = "UPDATE users SET password='" + password_new + "' WHERE user_name='" + user_name + "' AND password='" + password_old + "';";
			rs = stmt.executeUpdate(sql);		      
			
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			//finally block used to close resources
			try
			{
				if(stmt!=null)
					stmt.close();
			}
			catch(SQLException se2)
			{
				
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		if(rs==1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
