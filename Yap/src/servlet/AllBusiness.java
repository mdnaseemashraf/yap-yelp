package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database_connectors.BusinessTable;
import database_connectors.ReviewsTable;
import util.YapBusiness;

@SuppressWarnings("serial")
public class AllBusiness extends HttpServlet
{	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException	
	{
		ArrayList<YapBusiness> allYapBusiness = BusinessTable.getAllBusinesses();
				
		String generatedcontent = "";
		String list_item_template = "";
		
		ServletContext cntxt = request.getServletContext();
        String fName = "/TemplateRow.txt";
        InputStream ins = cntxt.getResourceAsStream(fName);
        try {
            if (ins != null) {
                InputStreamReader isr = new InputStreamReader(ins);
                BufferedReader reader = new BufferedReader(isr);               
                String word = "";
                while ((word = reader.readLine()) != null) {                    
                    list_item_template = list_item_template + word;
                }
            }
        }finally {
        	ins.close();
        }
        
        String inputform = "";
        fName = "/AddBusinessFormTemplate.txt";
        ins = cntxt.getResourceAsStream(fName);
        try {
            if (ins != null) {
                InputStreamReader isr = new InputStreamReader(ins);
                BufferedReader reader = new BufferedReader(isr);               
                String word = "";
                while ((word = reader.readLine()) != null) {                    
                	inputform = inputform + word;
                }
            }
        }finally {
        	ins.close();
        }
		
		String temp = "";		
		for(int x=0; x<allYapBusiness.size(); x++)
		{
			temp = list_item_template;
			
			String imagelink = "&[imgurl]&";
			temp = temp.replace(imagelink, "http://s3-media2.fl.yelpcdn.com/assets/srv0/yelp_styleguide/904d3ac00f48/assets/img/default_avatars/business_60_square.png");

			String itemtitle = "&[list-item-title]&";
			String title = "<h3><a href=\"" + "http://localhost:8080/Yap/AllReviews?business_id=" + allYapBusiness.get(x).getBusinessID()+"\">"+ allYapBusiness.get(x).getBusinessName() +"</a><h3>";
			temp = temp.replace(itemtitle, title);

			String itemsubtitle = "&[list-item-subtitle]&";
			temp = temp.replace(itemsubtitle, allYapBusiness.get(x).getBusinessType());

			String starrating = "&[X-Star]&";
			String ratingstring = "";
			
			if(ReviewsTable.getAvgRating(allYapBusiness.get(x).getBusinessID()) == 1)
			{
				ratingstring = "<i class=\"star-img stars_1\" title=\"1 star rating\">"+
						"<img alt=\"1 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
						"</i>";
			}
			if(ReviewsTable.getAvgRating(allYapBusiness.get(x).getBusinessID()) == 1.5)
			{
				ratingstring = "<i class=\"star-img stars_1_half\" title=\"1.5 star rating\">"+
						"<img alt=\"1.5 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
						"</i>";
			}
			if(ReviewsTable.getAvgRating(allYapBusiness.get(x).getBusinessID()) == 2)
			{
				ratingstring = "<i class=\"star-img stars_2\" title=\"2 star rating\">"+
						"<img alt=\"2 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
						"</i>";
			}
			if(ReviewsTable.getAvgRating(allYapBusiness.get(x).getBusinessID()) == 2.5)
			{
			ratingstring = "<i class=\"star-img stars_2_half\" title=\"2.5 star rating\">"+
						   "<img alt=\"2.5 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
						   "</i>";
			}
			if(ReviewsTable.getAvgRating(allYapBusiness.get(x).getBusinessID()) == 3)
			{
				ratingstring = "<i class=\"star-img stars_3\" title=\"3 star rating\">"+
						"<img alt=\"3 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
						"</i>";
			}
			if(ReviewsTable.getAvgRating(allYapBusiness.get(x).getBusinessID()) == 3.5)
			{
				ratingstring = "<i class=\"star-img stars_3_half\" title=\"3.5 star rating\">"+
						"<img alt=\"3.5 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
						"</i>";
			}
			if(ReviewsTable.getAvgRating(allYapBusiness.get(x).getBusinessID()) == 4)
			{
				ratingstring = "<i class=\"star-img stars_4\" title=\"4 star rating\">"+
						"<img alt=\"4 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
						"</i>";
			}
			if(ReviewsTable.getAvgRating(allYapBusiness.get(x).getBusinessID()) == 4.5)
			{
				ratingstring = "<i class=\"star-img stars_4_half\" title=\"4.5 star rating\">"+
						"<img alt=\"4.5 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
						"</i>";
			}
			if(ReviewsTable.getAvgRating(allYapBusiness.get(x).getBusinessID()) == 5)
			{
				ratingstring = "<i class=\"star-img stars_5\" title=\"5 star rating\">"+
						"<img alt=\"5 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
						"</i>";
			}
			
			temp = temp.replace(starrating, ratingstring);
			
			String smalldata = "&[small-data]&";
			temp = temp.replace(smalldata, allYapBusiness.get(x).getBusinessHeader());			
			
			String content = "&[rate-review-feedback]&";
			temp = temp.replace(content, " ");			
			
			generatedcontent = generatedcontent + temp;
		}
				
		response.setContentType("text/html");
		PrintWriter outx = response.getWriter();
		outx.println(
				"<html> "+
				
				"<head> "+
			    	"<meta charset=\"ISO-8859-1\">"+
			    	"<title>All Yap Businesses</title>"+
			    	"<link rel=\"stylesheet\" type=\"text/css\" media=\"all\" href=\"http://s3-media4.fl.yelpcdn.com/assets/2/www/css/a0ae6f4e7b85/www-pkg.css\">" +
			    	"<link rel=\"stylesheet\" type=\"text/css\" media=\"all\" href=\"http://s3-media2.fl.yelpcdn.com/assets/2/www/css/6ef718ab674f/homepage.css\">" +
			    	

					"<style>"+
					"header {"+
					    "background-color:white;"+
					    "color:white;"+
					    "text-align:center;"+
					    "padding:5px;"+	 
					"}"+
					"nav {"+
					    "line-height:100px;"+
					    "background-color:#eeeeee;"+
					    "height:300px;"+
					    "width:250px;"+
					    "float:left;"+
					    "padding:5px;"+	      
					"}"+
					"section {"+
					    "width:350px;"+
					    "float:left;"+
					    "padding:5px;"+	 	 
					"}"+
					"footer {"+
					    "background-color:black;"+
					    "color:white;"+
					    "clear:both;"+
					    "text-align:center;"+
					    "padding:5px;"+	 	 
					"}"+
					"</style>"+
			    "</head>"+
			    
				"<body>" +
					"<nav>"+
					inputform +
					"</nav>"+	
					"<section>"+
					"<ul class=\"ylist ylist-bordered search-results\">" +
				    generatedcontent +
					"</ul>" +
					"</section>"+
				"</body>" +
				"</html>"
				);
		
	}
}
