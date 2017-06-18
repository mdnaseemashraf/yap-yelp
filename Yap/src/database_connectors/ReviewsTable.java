package database_connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.YapBusiness;
import util.YapReview;

public class ReviewsTable 
{
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/yap";
		
	//  Database credentials
	static final String USER = "root";
	static final String PASS = "password";
	
	public static boolean addReview(String user_name, String review, int business_id, int rating, String date)
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
		      sql = "INSERT INTO reviews VALUES (default, (SELECT user_id FROM users WHERE user_name='" +
		    		  user_name + "'), '"+
		    		  user_name + "', " +
		    		  business_id + ", \"" +
		    		  review + "\"," +
		    		  rating + ", " +
		    		  date +
		    		  ", 0,0,0);";
		      
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
		
	public static boolean updateReview(int review_id, String user_name, String review)
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
			sql = "UPDATE reviews SET review='" + review + "' WHERE user_name='" + user_name + "' AND review_id='" + review_id + "';";
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

	public static double getAvgRating(String business_id)
	{
		ArrayList <YapReview> yapReviews = getReviews(business_id);
		if(yapReviews.size()>0)
		{
			double val =0;
			
			for(int u=0; u<yapReviews.size(); u++)
			{
				val = val + yapReviews.get(u).getRating();
			}
			 val = val/yapReviews.size();
			 val = Math.round(val * 2) / 2.0;		
			return val;
		}
		else
		{
			return 1;
		}
	}
	
	
	public static ArrayList<YapReview> getReviews(String business_id)
	{
		   ArrayList <YapReview> yapReviews = new ArrayList <YapReview>();
		   ResultSet rs = null;
		   Connection conn = null;
		   Statement stmt = null;
		   try
		   {
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      stmt = conn.createStatement();
		      
		      String sql;		     
		      sql = "SELECT * FROM reviews WHERE business_id="+business_id+";";
		    		  
		      rs = stmt.executeQuery(sql);
		   
		      while (rs.next()) 
		      {
		    	  int rid = rs.getInt("review_id");
		    	  int uid = rs.getInt("user_id");			      
				  String uname = rs.getString("user_name");
				  int bid = rs.getInt("business_id");
				  String review = rs.getString("review");
				  int rating = rs.getInt("rating");
				  String date = rs.getString("date");
				  int use = rs.getInt("useful");
				  int funny = rs.getInt("funny");
				  int cool = rs.getInt("cool");
				  
			      YapReview yReview = new YapReview(Integer.toString(bid), rating, review, date, Integer.toString(uid), uname, use, cool, funny);		      
			      yapReviews.add(yReview);
		   	  }
		   
		      stmt.close();
		      conn.close();
		   }
		   catch(SQLException se){
		      se.printStackTrace();
		   }
		   catch(Exception e){	e.printStackTrace();}
		   finally
		   {
		      try
		      { if(stmt!=null)	stmt.close();}
		      catch(SQLException se2){}
		      
		      try 
		      { if(conn!=null) conn.close();}
		      catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		   
		   return yapReviews;
		}
	
}
