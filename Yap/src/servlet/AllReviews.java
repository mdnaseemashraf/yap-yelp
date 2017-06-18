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

import database_connectors.ReviewsTable;
import util.YapReview;

@SuppressWarnings("serial")
public class AllReviews extends HttpServlet
{	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException	
	{			
		String business_id = request.getParameterValues("business_id")[0];
		
		ArrayList<YapReview> yapReviews = ReviewsTable.getReviews(business_id);
		
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
        fName = "/ReviewInputFormTemplate.txt";
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
        
        inputform = inputform.replace("c_businessid", business_id);
        
		String temp = "";		
		for(int x=0; x<yapReviews.size(); x++)
		{
			temp = list_item_template;
			
			String imagelink = "&[imgurl]&";
			temp = temp.replace(imagelink, "http://s3-media3.fl.yelpcdn.com/assets/srv0/yelp_styleguide/20983a63ea50/assets/img/default_avatars/user_60_square.png");

			String itemtitle = "&[list-item-title]&";
			temp = temp.replace(itemtitle, yapReviews.get(x).getUserName());

			String itemsubtitle = "&[list-item-subtitle]&";
			temp = temp.replace(itemsubtitle, yapReviews.get(x).getDate());

			String starrating = "&[X-Star]&";
			int rate = yapReviews.get(x).getRating(); 
			String ratingstring = "";
			
			if(rate==1)
			{
				ratingstring = "<i class=\"star-img stars_1\" title=\"1 star rating\">"+
									  "<img alt=\"1 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
									  "</i>";
			}
			if(rate==2)
			{
				ratingstring = "<i class=\"star-img stars_2\" title=\"2 star rating\">"+
						"<img alt=\"2 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
						"</i>";
			}
			if(rate==3)
			{
				ratingstring = "<i class=\"star-img stars_3\" title=\"3 star rating\">"+
						"<img alt=\"3 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
						"</i>";
			}
			if(rate==4)
			{
				ratingstring = "<i class=\"star-img stars_4\" title=\"4 star rating\">"+
						"<img alt=\"4 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
						"</i>";
			}
			if(rate==5)
			{
				ratingstring = "<i class=\"star-img stars_5\" title=\"5 star rating\">"+
						"<img alt=\"5 star rating\" class=\"offscreen\" height=\"303\" src=\"//s3-media4.fl.yelpcdn.com/assets/srv0/yelp_styleguide/c2252a4cd43e/assets/img/stars/stars_map.png\" width=\"84\">"+
						"</i>";
			}
			
			temp = temp.replace(starrating, ratingstring);
			
			String smalldata = "&[small-data]&";
			temp = temp.replace(smalldata, yapReviews.get(x).getReview());			
			
			String content = "&[rate-review-feedback]&";
			String review_fb = "<div class=\"rateReview voting-feedback\" data-review-id=\"JYHRhOcipTWwUoMH8SDr4g\">"+
							   "<p class=\"voting-intro voting-prompt\">"+
							   "Was this review helpful?"+
							   "</p>"+
							   "<ul class=\"voting-buttons\" data-csrf-token=\"1284fdd90aa0a1aced82173dbac4c4cf6216db9ce164d20f2a02d6853007dba6\">"+
							   "<li class=\"vote-item inline-block\">"+
							   "<a href=\"javascript:;\" rel=\"useful\" class=\"ybtn ybtn-small useful\"><span  class=\"i-wrap ig-wrap-common i-ufc-useful-common-wrap button-content\"><i  class=\"i ig-common i-ufc-useful-common\"></i>     <span class=\"vote-type\">Useful</span>"+
							   "<span class=\"count\"></span></span></a>"+
							   "</li>"+

							   "<li class=\"vote-item inline-block\">"+
							   "<a href=\"javascript:;\" rel=\"funny\" class=\"ybtn ybtn-small funny\"><span  class=\"i-wrap ig-wrap-common i-ufc-funny-common-wrap button-content\"><i  class=\"i ig-common i-ufc-funny-common\"></i>     <span class=\"vote-type\">Funny</span>"+
							   "<span class=\"count\"></span></span></a>"+
							   "</li>"+

							   "<li class=\"vote-item inline-block\">"+
							   "<a href=\"javascript:;\" rel=\"cool\" class=\"ybtn ybtn-small cool\"><span  class=\"i-wrap ig-wrap-common i-ufc-cool-common-wrap button-content\"><i  class=\"i ig-common i-ufc-cool-common\"></i>     <span class=\"vote-type\">Cool</span>"+
							   "<span class=\"count\"></span></span></a>"+
							   "</li>"+
							   "</ul>"+
							   "</div>";
			
			temp = temp.replace(content, review_fb);			
			
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
					    "color:black;"+
					    "text-align:center;"+
					    "padding:5px;"+	 
					"}"+
					"nav {"+
					    "line-height:30px;"+
					    "background-color:#eeeeee;"+
					    "height:300px;"+
					    "width:100px;"+
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
					"<header>"+
					inputform +
					"</header>"+
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
