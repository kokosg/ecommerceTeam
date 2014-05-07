import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ContactModel;
import models.ReviewForm;
import objects.Article;
import objects.EmailMessage;
import objects.Review;
import objects.User;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * Servlet implementation class ReviewFormServlet
 */
public class ReviewFormServlet extends VelocityViewServlet {
	private static final long serialVersionUID = 1L;
	Article article=new Article();
	Review reviewart= new Review();
	public boolean LOOP =true;
	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
		Template template=null;
		context.put("apptitle", "Ecom Journal - Home");
		HttpSession session = request.getSession();
		int authorID =(Integer) session.getAttribute("userID");
		String UserRole=(String) session.getAttribute("userRole");
		System.out.println("Inside ReviewFormServlet");
		if (UserRole == "AuthorReviewer"){
			System.out.println("I am author reviewer");
			if(LOOP){
				String articleID= request.getParameter("reviewArt");
				article.setArticleID(Integer.parseInt(articleID));
				LOOP=false;
				System.out.println("I am in LOOP");

			}
			else{	
				System.out.println("outside LOOP");
				int formart=article.getArticleID();
				System.out.println("formart :"+formart);
				String judge =request.getParameter("judge");
				String expertise =request.getParameter("level");
				String reviewSummary =request.getParameter("summary");
				String criticism =request.getParameter("criticism");
				String errors =request.getParameter("errors");
				String comments=request.getParameter("comments");
				ReviewForm form =new ReviewForm();
				int count=form.getReviewCount(authorID, formart);
				if (count<2){
					form.insertReviewForm(authorID, article.getArticleID(), judge, expertise, reviewSummary,comments,criticism, errors);
				}
				reviewart=form.selectReviewForm(authorID, article.getArticleID());

			}

		if(request.getParameter("redirectAck")!=null){
			if(request.getParameter("redirectAck").equalsIgnoreCase("Submit")){

				// get the value of a hidden field in a form to determine if we have posted data
				User user = (User) session.getAttribute("user");
				String name = user.getName();
				String title = "Review Submission";
				String email = user.getEmail();
				StringBuilder sb = new StringBuilder();
				sb.append("Your review has been submitted successfully.");
				sb.append("\n");
				sb.append("Your Submission:");
				String text = "\n1. Are you a?"+"\n"+reviewart.getJudgement()+"\n"+"2. Expertise Level?"+"\n"+reviewart.getExpertise()+"\n"+"3. Summary of the content and novel contribution of the article."+"\n"+reviewart.getSummary()+"\n"+"4. Criticism "+"\n"+reviewart.getCriticism()+"\n"+"5. Secret Comments( **comments will be visible only to the editor)"+"\n"+reviewart.getEditorsComments()+"\n"+"6. List of other errors.(example: typographical or grammatical mistakes)"+"\n"+reviewart.getSmallErrors()+"\n";
				sb.append(text);

				String messageText = sb.toString();

				//create EmailMessage object by passing values
				EmailMessage emailMessage = new EmailMessage(name, title, email, messageText);

				//create ContactModel object
				ContactModel contactModel = new ContactModel();

				boolean emailStatus = false;

				//call the method sendEmail from contactModel object and passing values in order to trigger the email function
				try {
					emailStatus = contactModel.sendEmail(emailMessage.getName(), emailMessage.getEmail(), emailMessage.getTitle(), emailMessage.getMessage());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//check if the emailStatus status is true to display the message and insert into the database the details of the email
				//or if is false to display an error message
				if (emailStatus) {
					context.put("successfully", "email is sent successfully.");
					//contactModel.insertEmail(emailMessage.getName(), emailMessage.getTitle(), emailMessage.getEmail(), emailMessage.getMessage());
				} else {
					context.put("error", "message was not send");
				}
				context.put("reviewart", reviewart);
				LOOP=true;
				template = getTemplate("/pages/acknowledgementReview.vm");
			}
		}

		else{
			template = getTemplate("/forms/reviewForm.vm");
		}
		}
		return template;

	}
}