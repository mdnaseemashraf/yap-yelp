package util;

import java.text.SimpleDateFormat;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class YapReview implements Comparable<YapReview>
{
	private String businessID;
	private int rating;
	private StringBuffer review;
	private String date;
	private String userID;
	private String userName;
	
	private int use;
	private int cool;
	private int funny;
	
	public YapReview(String businessId, int rating, String review, String date, String userId, String uname, int use, int cool, int funny)
	{			
		SimpleDateFormat inputDate=new SimpleDateFormat("yyyy-MM-dd");
		inputDate.setLenient(false);
		try
		{
			inputDate.parse(date);
	    }
	    catch(ParseException ex)
		{
	    	System.err.println(new DateCheckerException(date));	    	
	    } catch (java.text.ParseException e) 
		{			
			e.printStackTrace();
		}
		
		this.businessID = businessId;
		this.rating = rating;
		this.review = new StringBuffer(review);
		this.date = date;
		this.userID = userId;
		this.userName = uname;
		
		this.use = use;
		this.cool = cool;
		this.funny = funny;
	}
	
	/**
	 * Compare to order the Yelp Reviews by dates and then by User ID.
	 * @return integer.
	 */
	public int checkUserAndDate(YapReview businessReview)
	{
		if(this.date.compareTo(businessReview.date) == 0)
		{
			return this.userID.compareTo(businessReview.userID);
		}
		else
		{
			return this.date.compareTo(businessReview.date);
		}
	}
	
	/**
	 * Weeding out Duplicate reviews by comparing UserID, Date and BusinessID.
	 * For reviews without similar dates and user-ids. Send the reviews further
	 * to be compared and sorted.
	 * Calling checkUserDate(Review object) if condition is successful.
	 * @return integer.
	 */
	public int compareTo(YapReview businessReview)
	{		
		if((date.equals(businessReview.date))&&(userID.equals(businessReview.userID))	//Weeding out duplicate entries. 
				&& (businessID.equals(businessReview.businessID)))
		{
			return 0;
		}		
		else if((!date.equals(businessReview.date))||(!userID.equals(businessReview.userID)))	//If the dates and user-ids, both, don't match. Then order them.
		{
			return checkUserAndDate(businessReview);
		}
		else
		{
			return -1;
		}
	}
	
	public int getFunnyCount()
	{
		return funny;
	}
	public int getCoolCount()
	{
		return cool;
	}
	public int getUsefulCount()
	{
		return use;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getBusinessID()
	{
		return businessID;
	}
		
	public int getRating()
	{
		return rating;
	}	

	public StringBuffer getReview()
	{
		return review;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public String getUserID()
	{
		return userID;
	}
	
	/**
	 * Generate a String representation as required by toString.
	 */
	public String toString() 
	{				
		StringBuilder builder = new StringBuilder();
		builder.append(rating);
		builder.append(" - ");
		builder.append(userID);
		builder.append(": ");
		builder.append(review);
		
		return builder.toString();
	}
		
	@SuppressWarnings("serial")
	class DateCheckerException extends IllegalArgumentException
	{
		private String dateCheck;
		
		public DateCheckerException(String inputDate)
		{
			dateCheck=inputDate;
		}
		public String toString()
		{
			return "The date "+"\""+dateCheck+"\""+" entered is in invalid format."
					+ "Dates must be in this format [yyyy-MM-dd]";
		}
	}

	@SuppressWarnings("serial")
	class RateCheckerException extends IllegalArgumentException
	{
		private int rateCheck;
		private int maxrate;
		private int minrate;
		
		public RateCheckerException(int inRate, int max, int min)
		{
			rateCheck = inRate;
			maxrate = max;
			minrate = min;
		}
		public String toString()
		{
			return "The rate "+"\""+rateCheck+"\""+" entered is invalid. Rates must be between "+
					minrate +" and " + maxrate+".";
		}
	}
}
