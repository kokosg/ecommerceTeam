//
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import models.AbstractModel;
//import models.ReviewForm;
//import objects.Article;
//import objects.Review;
//
//import org.apache.velocity.Template;
//import org.apache.velocity.context.Context;
//import org.apache.velocity.tools.view.VelocityViewServlet;
//
///**
// * Servlet implementation class ArticleStatusServlet
// */
//public class ArticleStatusServlet extends VelocityViewServlet {
//	private static final long serialVersionUID = 1L;
//	
//	
//	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) {
//		Template template=null;
//		context.put("apptitle", "Ecom Journal - Home");
//		HttpSession session = request.getSession();
//		int authorID =(Integer) session.getAttribute("userID");
//		AbstractModel absModel=new AbstractModel();
//		ArrayList<Article> checkTitle =absModel.getChoiceTitle(authorID);
//		System.out.println("checkTitle: "+ checkTitle);
//		ReviewForm form =new ReviewForm();
//		
//		ArrayList<Integer> status = absModel.getDownloaded(authorID);
//		for(Article result :checkTitle ){
//			for(int reviewDwnld : status){
//				if(reviewDwnld==result.getArticleID()){
//					result.setDownloaded(true);
//				}
//			}
//			System.out.println("Title :"+ result.getTitle() +" Summary: "+result.getSummary()+ " Chosen  :" + result.isChosen() );
//			Review review=form.selectReviewForm(authorID, result.getArticleID());
//			result.setDatereviewSubmitted(review.getDateSubmitted());
//		}
//		for(Article result :checkTitle ){
//			System.out.println("Title :"+ result.getTitle()+ "Chosen  :" + result.isChosen() );
//		}
//	    context.put("artCkeckId", checkTitle);
//		template = getTemplate("/pages/articleStatus.vm");
//		return template;
//		
//	}
//   }


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.AbstractModel;
import models.ReviewForm;
import objects.Article;
import objects.Review;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;
/**
 * Servlet implementation class ArticleStatusServlet
 */
public class ArticleStatusServlet extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	int count =7*24*60*60*2000;
	
	
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) {
		createTimer();
		Template template=null;
		context.put("apptitle", "Ecom Journal - Home");
		HttpSession session = request.getSession();
		int authorID =(Integer) session.getAttribute("userID");
		AbstractModel absModel=new AbstractModel();
		ArrayList<Article> checkTitle =absModel.getChoiceTitle(authorID);
		System.out.println("checkTitle: "+ checkTitle);
		ReviewForm form =new ReviewForm();
		
		ArrayList<Integer> status = absModel.getDownloaded(authorID);
		for(Article result :checkTitle ){
			for(int reviewDwnld : status){
				if(reviewDwnld==result.getArticleID()){
					result.setDownloaded(true);
				}
			}
			System.out.println("Title :"+ result.getTitle() +" Summary: "+result.getSummary()+ " Chosen  :" + result.isChosen() );
			Review review=form.selectReviewForm(authorID, result.getArticleID());
			result.setDatereviewSubmitted(review.getDateSubmitted());
		}
		for(Article result :checkTitle ){
			System.out.println("Title :"+ result.getTitle()+ "Chosen  :" + result.isChosen() );
		}
	    context.put("artCkeckId", checkTitle);
	    
		template = getTemplate("/pages/articleStatus.vm");
		return template;
		
	}


	private void createTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerExample("Test Check Working"),	10000);
	}
	
	 class TimerExample extends TimerTask {

	    private Timer timer;
	    private String textToDisplay;

	    public TimerExample(int executionsPerSecond) {
	        this.textToDisplay = "executionsPerSecond = " + executionsPerSecond;
	        timer = new Timer();
	            //period represents the period between each timer execution (call of run method")
	        long period = (long) (1000.0 / executionsPerSecond);
	        System.out.println(period);
	        System.out.println(1000 / executionsPerSecond);
	         timer.schedule(this, 200, period);
	    }

	    public TimerExample(String textToDisplay) {
	        this.textToDisplay = textToDisplay;
	    }

	    public void functionToRepeat() {
	        System.out.println(textToDisplay);
	        String from = "aman.bhardwaj1988@gmail.com";
	        String to = "shreebha.arora@gmail.com";
	        String subject = "Test";
	        String message = "HI SHREEBHA";
//	        mail4 sendMail = new mail4(from,to,subject,message);
//	        sendMail.send();
	    }

	    public void run() {
	        functionToRepeat();
	    }

	    
	}
   }
