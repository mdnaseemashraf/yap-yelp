package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database_connectors.BusinessTable;

@SuppressWarnings("serial")
public class SubmitBusiness extends HttpServlet
{	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//BLANK	
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException
	{				
		String business_name = request.getParameterValues("business_name")[0];		
		String city = request.getParameterValues("city")[0];		
		String state = request.getParameterValues("state")[0];
		String lat = request.getParameterValues("lat")[0];
		String lon = request.getParameterValues("lon")[0];
		String type = request.getParameterValues("type")[0];
		
		String[] neighborhoods = request.getParameterValues("neighborhood");
		
		String neighborhood = "";
		for(int y=0; y<neighborhoods.length; y++)
		{
			neighborhood = neighborhood +" "+neighborhoods[y];		
		}
				
		BusinessTable.addBusiness(business_name, city, state, lat, lon, type, neighborhood);
		
		PrintWriter out = response.getWriter();
		out.println("<html> " +
					"<head>" +   
			  		"<title>Added New Yap Business</title>" +
			  		"<meta charset=\"utf-8\">" +
			  		"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
			  		"<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">" +
			  		"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script>" +
			  		"<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>" +
			  		"<style>" +
			  		".jumbotron {"+
			  		"background-color: #0487DE;"+
			  		"color: #fff;"+
			  		"}"+
			  		"</style>"+
			  		"</head>"+
			  		"<body>" +
			  		"<h1 align=center>Yap Business Added</h1>"+				
			  		"<a href=\"http://localhost:8080/Yap/AllBusiness\" class=\"btn btn-danger\" >Continue</a>"+
			  		"</body>" +
			  		"</html>"
					);
		
	}
}
