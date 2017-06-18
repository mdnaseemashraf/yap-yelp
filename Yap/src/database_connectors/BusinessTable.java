package database_connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.YapBusiness;

public class BusinessTable 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/yap";
		
	static final String USER = "root";
	static final String PASS = "password";
	
	public static boolean addBusiness(String business_name, String city, String state, String lat, String lon, String type, String negihborhood)
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
		      sql = "INSERT INTO business VALUES (default, '" + business_name + "', '" + city + "','" + state + "', "+ lat + "," + lon + ", '" + type + "', '" + negihborhood + "');";
		    		  
		      rs = stmt.executeUpdate(sql);
		      
		      stmt.close();
		      conn.close();
		   }
		   catch(SQLException se)	{	se.printStackTrace();}
		   catch(Exception e)	{	e.printStackTrace();}
		   finally
		   {
		      try
		      {
		         if(stmt!=null)	stmt.close();
		      }
		      catch(SQLException se2){}		      
		      try
		      {	if(conn!=null)	conn.close();}
		      catch(SQLException se)
		      {	se.printStackTrace();}
		   }
		   
		   if(rs==1) {return true;}
		   else {return false;}
		}
	
	public static ArrayList<YapBusiness> getAllBusinesses()
	{
		   ArrayList <YapBusiness> allYapBusiness = new ArrayList <YapBusiness>();
		   ResultSet rs = null;
		   Connection conn = null;
		   Statement stmt = null;
		   try
		   {
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      stmt = conn.createStatement();
		      
		      String sql;		     
		      sql = "SELECT * FROM business;";
		    		  
		      rs = stmt.executeQuery(sql);
		   
		      while (rs.next()) 
		      {
			      int bid = rs.getInt("business_id");
				  String bname = rs.getString("name");
				  String bcity = rs.getString("city");
				  String bstate = rs.getString("state");
				  String blat = rs.getString("latitude");
				  String blon = rs.getString("longitude");
				  String btype = rs.getString("type");
				  String bneighborhoods = rs.getString("neighborhoods");
				  
			      YapBusiness yBusiness = new YapBusiness(Integer.toString(bid), bname, bcity, bstate, Double.parseDouble(blat), Double.parseDouble(blon), btype, bneighborhoods);		      
			      allYapBusiness.add(yBusiness);
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
		   
		   return allYapBusiness;
		}
	
	public static boolean averageBusinessRating(int business_id)
	{	
		//TODO
		return false;
	}

}
