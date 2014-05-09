/**
 * 
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.EmailMessage;
import objects.EmailSubscriber;
import objects.MessageObject;

/**
 * @author acp13gg
 * 
 */
public class EmailSubscriberModel {

	/**
	 * 
	 */
	public EmailSubscriberModel() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<EmailSubscriber> sendEmailSubscriber() throws SQLException {
		ArrayList<EmailSubscriber> arrayResults = new ArrayList<EmailSubscriber>();

		String querySubscriber = "select * from Subscriber";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			Statement st2 = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(querySubscriber);

			while (rs.next()) {
				int subscriberID = rs.getInt("subscriberID");
				boolean editionSubscriber = (Boolean) rs.getObject("editionSubscriber");
				int keywordSubscriber = rs.getInt("keywordSubscriber");
				String email = (String) rs.getObject("email");

				System.out.print("aa " + subscriberID + " " + editionSubscriber + " " + keywordSubscriber + " " + email);

				String queryKeywords = "select DISTINCT Article.title from Keyword INNER JOIN SubKeyword ON Keyword.keywordID = SubKeyword.keywordID INNER JOIN Subscriber ON Subscriber.subscriberID = SubKeyword.subscriberID INNER JOIN ArticleKeyword ON ArticleKeyword.keywordID = Keyword.keywordID INNER JOIN Article ON Article.articleID = ArticleKeyword.articleID WHERE Subscriber.subscriberID = '" + subscriberID + "'";
				EmailSubscriber emailSubscriber = new EmailSubscriber();

				ResultSet rev = st2.executeQuery(queryKeywords);
				while (rev.next()) {
					// int keywordID = rs.getInt("Keyword.keywordID");
					// String text = (String) rs.getObject("Keyword.text");
					// int subKeywordID = rs.getInt("SubKeyword.subKeywordID");
					// int subscriber_ID = rs.getInt("Subscriber.subscriberID");
					// String edition_Subscriber = (String)
					// rs.getObject("Subscriber.editionSubscriber");
					// String keyword_Subscriber = (String)
					// rs.getObject("Subscriber.keywordSubscriber");
					String emailkeywords = (String) rs.getObject("Subscriber.email");
					// int articleKeywordID =
					// rs.getInt("ArticleKeyword.articleKeywordID");
					// int articleID = rs.getInt("Article.articleID");
					String title = (String) rev.getObject("Article.title");
					// String summary = (String)
					// rs.getObject("Article.summary");
					// boolean published = (boolean)
					// rs.getObject("Article.published");
					// boolean reviewed = (boolean)
					// rs.getObject("Article.reviewed");
					// int pageNo = rs.getInt("Article.pageNo");
					// boolean needsRevision = (boolean)
					// rs.getObject("Article.needsRevision");

					System.out.print("title " + title);

					emailSubscriber = new EmailSubscriber(title, emailkeywords);
					arrayResults.add(emailSubscriber);
				}
				
			
				//create ContactModel object
				ContactModel contactModel = new ContactModel();
				
				System.out.print("Subscriber " + " Subscriber Email " + " " + emailSubscriber.getEmail() + " " + emailSubscriber.getTitle() + " telos");
				
				String messageText = "The following article(s) are the result(s) of your keyword(s): " + emailSubscriber.getTitle();
				
				//create EmailMessage object by passing values
		    	EmailMessage emailMessage = new EmailMessage("Subscriber", "Subscriber Email", email, messageText);
		    	  
		    	//call the method sendEmail from contactModel object and passing values in order to trigger the email function
				contactModel.sendContactEmail(emailMessage.getName(), emailMessage.getTitle(), emailMessage.getEmail(), emailMessage.getMessage());
		    	  
				
				rev.close();
			}
			rs.close();
			st.close();
			st2.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return arrayResults;
	}
}
