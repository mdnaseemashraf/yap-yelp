package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database_connectors.ReviewsTable;

@SuppressWarnings("serial")
public class AddReview extends HttpServlet
{	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//BLANK	
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException
	{	
		String user_name = request.getParameterValues("user_name")[0];
		String business_id = request.getParameterValues("business_id")[0];
		String rating = request.getParameterValues("rating")[0];
		String review = request.getParameterValues("review")[0];
		
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyyMMdd").format(date);
		
		ReviewsTable.addReview(user_name, review, Integer.parseInt(business_id), Integer.parseInt(rating), modifiedDate);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html> "+
				"<body>" +
				"<h1 align=center>Review Added Successfully!</h1>"+
				"<form action=\"http://localhost:8080/Yap/AllBusiness\">"+
			    "<input type=\"submit\" value=\"Return to List of Businesses\">"+
			    "</form>" +
				"</body>" +
				"</html>"
				);
		
	}
}
