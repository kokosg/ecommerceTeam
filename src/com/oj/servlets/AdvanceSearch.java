package com.oj.servlets;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import com.oj.models.AdvanceSearchModel;
import com.oj.objects.Article;

/**
 * Servlet implementation class AdvanceSearchServlet
 */
public class AdvanceSearch extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
		
		// get the value of a hidden field in a form to determine if we have posted data
		String flag = request.getParameter("flag");
		
		context.put("apptitle", "E-com Journal - Advance Search");
		Template template=null;
		response.setContentType("text/html");

		//if we haven't post any data then we have just to load the template
		if (flag == null) {
			try {
		          template = getTemplate("/forms/advanceSearch.vm"); 
		       } catch(Exception e ) {
		          System.out.println("Error " + e);
		       }
		       return template;
		//if we have posted data from a form do the following
		} else {
			try {
				
				//create AdvanceSearchModel object
				AdvanceSearchModel model = new AdvanceSearchModel();
				
				//create ArrayList with Article object
		    	ArrayList<Article> arrayResults = new ArrayList<Article>(); 

		    		//get the parameters from the form
					String categoryType = request.getParameter("item");
					String queryName = request.getParameter("queryName");
					String datepickerFrom = request.getParameter("datepickerFrom");
					String datepickerTo = request.getParameter("datepickerTo");
					
					//check if datepickers values are not null and then pass the UK date to dateConvertor method
					if ((datepickerFrom != null) && (datepickerTo != null)){
						datepickerFrom = dateCovertor(datepickerFrom);
						datepickerTo = dateCovertor(datepickerTo);
					}
					
					// the categoryType depends the value call a different method from AdvanceSearchModel
					if (categoryType.equals("article")) {
						arrayResults = model.getArticle(queryName, categoryType);
					} else if (categoryType.equals("author")) {
						
						//split the string which has "," insert all the values in array which and add it on the arraylist of object
						String[] parts = queryName.split(",");
						for( int i = 0; i <= parts.length - 1; i++){
							queryName = parts[i]; 
							arrayResults.add(model.getArticleObject(queryName));
						}
					} else if (categoryType.equals("interval")) {
						arrayResults = model.getAuthorsArticle(datepickerFrom, datepickerTo, categoryType);
					} else if (categoryType.equals("keywords")) {
						arrayResults = model.getArticle(queryName, categoryType);
					}
					
					if (arrayResults.isEmpty()) {
						context.put("empty", "empty");
					} else {
						context.put("searchResults", arrayResults);
					}
					
					template = getTemplate("/forms/advanceSearch.vm");

			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			return template; 
		}
	}
	
	//convert the UK date format to USA
	private String dateCovertor(String dateUK) {
		String[] dateSplitter = dateUK.split("/");
		String day = dateSplitter[0];
		String month = dateSplitter[1];
		String year = dateSplitter[2];
		
		String dateUSA = year + "/" + day + "/" + month;
		
		System.out.println("dateUSA " + dateUSA);
		
		return dateUSA;
	}
}
