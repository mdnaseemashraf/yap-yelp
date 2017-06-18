package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database_connectors.UsersTable;

@SuppressWarnings("serial")
public class UserRegistration extends HttpServlet
{	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//BLANK	
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException
	{
		String user_name = request.getParameterValues("username")[0];
		String password = request.getParameterValues("password")[0];
		String email = request.getParameterValues("email")[0];
		
		UsersTable.addUser(user_name, email, password);
		
		PrintWriter out = response.getWriter();
		out.println("<html> "+		
			"<title>Welcome to Yap!!</title>" +
			"<meta charset=\"utf-8\">" +
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
			"<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">" +
			"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script>" +
			"<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>" +
			"<style>" +
			".jumbotron {" +
			    "background-color: #0487DE;" +
			    "color: #fff;" +
			"}" +
			"</style>" +
				
				"<body>" +
				"<div class=\"jumbotron text-center\">" +
				"<p>Yap User Registeration Complete for "+ request.getParameterValues("username")[0] +"</p>"+				
				"<form action=\"http://localhost:8080/Yap/AllBusiness\">" +
				"<input type=\"submit\" class=\"btn btn-danger\" value=\"Go to Business Listings\">" +
				"</form>"+
				"</body>" +
				"</html>"
				);
		
	}
}
