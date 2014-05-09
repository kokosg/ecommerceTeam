
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.AbstractModel;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

public class ResponseAuthorServlet extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	private boolean LOOP = true;
	String articleID="";
	AbstractModel am = new AbstractModel();
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) {
		Template template=null;
		HttpSession session = request.getSession();
		if( session.getAttribute("userID")!=null){
		int authorID =(Integer) session.getAttribute("userID");
		if(LOOP){
			articleID = request.getParameter("artID");
			System.out.println("RESPONSE SERVLET "+articleID);
			LOOP=false;
		}
		AbstractModel ab = new AbstractModel();
		boolean isResponseAccepted =ab.isResponseAccepted(articleID, authorID);
		int i =0 ;
		if(isResponseAccepted){
			i=1;
		}else{
			i=0;
		}
		System.out.println("is accpeted @@@@ : "+isResponseAccepted);
		int rejectCount = ab.getResponseRejectCount(articleID, authorID);
		context.put("isResponseAccepted",i);
		System.out.println("REJECT CONUT "+rejectCount);
		context.put("rejectCount", rejectCount);
		String responseText = am.getResponse(articleID);
		String rejectedResponse = request.getParameter("rejectedResponse");
		System.out.println("Responsetext in servlet"+responseText);
		String value = request.getParameter("submit");
		
		if(value!=null){
			if(value.equals("Accept")){
				ab.setCriticismIsAcceptedbyReviwer(articleID, authorID);
				ab.updateReviewCount(articleID, authorID);
			}
			else{
				ab.rejectedRevision(articleID, authorID, rejectedResponse);
				System.out.println("rejected");
			}
		}
		
		
		
		context.put("articleID",articleID);
		System.out.println("RESPONSE TEXT ========> "+responseText);
		context.put("responseText",responseText);
		}
		template = getTemplate("/pages/responseAuthor.vm");
		return template;
	}
}
//responseFromAuthor
