import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.EmailSubscriberModel;
import models.SubscriptionModel;
import objects.Keyword;
import objects.Subscriber;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/** Velocity View Servlet implementation class Subscribe 
 * @author Team:Master10
 */
public class Subscribe extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	
	
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
		
		String flag = request.getParameter("flag"); // get the value of a hidden field in a form to determine if we have posted data
		SubscriptionModel model = new SubscriptionModel();
		Template template = null;
		response.setContentType("text/html");
        
		//if we haven't post any data then we have just to load the template
		if (flag == null) {
			context.put("apptitle", "E-com Journal - Subscribe");
			try {
	            ArrayList<Keyword> keywords = new ArrayList<Keyword>(); 
				//return all keywords in the Keywords table
				keywords = model.getAllKeywords();
				context.put("keywords", keywords);
				template = getTemplate("/forms/subscribe.vm");
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
			return template;
		//if we have posted data from a form do the following
		} else {
			context.put("apptitle", "E-com Journal - Home");
			//get form attributes' values
			String email = request.getParameter("email");
			String[] keywords = request.getParameterValues("keywords");
			String futureEditions = request.getParameter("futureEditions");
			//create a subscriber object
			Subscriber subs= new Subscriber();
			subs.setEmail(email);
			if (keywords != null) {
				subs.setKeywordSubscriber(true);
			} else {
				subs.setKeywordSubscriber(false);
			}
			if (futureEditions != null) {
				subs.setEditionSubscriber(true);
			} else {
				subs.setEditionSubscriber(false);
			}
			try {				
				String selectQuery = "SELECT subscriberID from Subscriber where email = '" + subs.getEmail() + "' ";
				int subscriberID = model.getSubscriberID(selectQuery);
				//check if subscriber already does not exists, if so create a new row else update the row
				System.out.println(subscriberID);
				if (subscriberID != 0) {
					subs.setSubscriberID(subscriberID);
					//update the subscribers' data in Subscriber table		
					model.updateSubscriber(subs);
					//update keywords data in SubKeyword table
					model.updateSubKeyword(subs, keywords);
					template = getTemplate("/forms/home.vm"); 
				} else {
					//insert new data into Subscriber table		
					model.insertSubscriber(subs);
					//query Subscriber table to get subscriberID
					subscriberID = model.getSubscriberID(selectQuery);
					subs.setSubscriberID(subscriberID);				
					//insert new data into SubKeyword table
					if (keywords != null) {
						model.insertSubKeyword(subs, keywords);
						
					  //send email to emailSubscriberModel
				      EmailSubscriberModel emailSubscriberModel = new EmailSubscriberModel();
				      emailSubscriberModel.sendEmailSubscriber();
					}
					template = getTemplate("/forms/home.vm"); 
				}
			} catch(Exception e ) {
				System.out.println("Error " + e);
			}
			return template;
		}
	}
}