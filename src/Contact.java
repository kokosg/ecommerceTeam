
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ConnectionManager;
import objects.EmailMessage;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

public class Contact extends VelocityViewServlet {
	   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Statement st = null;
		private ConnectionManager conn = null;

	public Template handleRequest( HttpServletRequest request, HttpServletResponse response, Context context ) { 
	      /* get the template */
		
		String flag = request.getParameter("flag"); // get the value of a hidden field in a form to determine if we have posted data
		
	      Template template = null;
	      context.put("apptitle", "Ecom Journal - Contact");
		  response.setContentType("text/html");
	      boolean emailStatus = false;
		  
	      if (flag == null) {
	      
		         template = getTemplate("/forms/contact.vm");

		      return template;
	    	  
	      } else {
	    	  
	      try {
	    	  	    	  
	    	  String name = request.getParameter("name");
			  String title = request.getParameter("title");
			  String email = request.getParameter("email");
			  String messageText = request.getParameter("messageText");

	    	  EmailMessage emailMessage = new EmailMessage(name, title, email, messageText); 
			  
			  emailStatus = sendEmail(emailMessage.getName(), emailMessage.getTitle(), emailMessage.getEmail(), emailMessage.getMessage());
	    	  
	    	  if (emailStatus) {
	  			context.put("status", "message was send");
		    	  
				  String insertQuery = "INSERT INTO Message (name, title, email, message) VALUE ('" + emailMessage.getName() + "', '" + emailMessage.getTitle() + "', '" + emailMessage.getEmail() + "', '" + emailMessage.getMessage() + "')";
				
		    	  st = conn.getInstance().getConnection().createStatement();
				  st.executeUpdate(insertQuery);
				  
				  
					st.close();
					conn.close();
	  			
	    	  } else {
	  			context.put("status", "message was not send");
	    	  }
			  
	         template = getTemplate("/forms/contact.vm");
	      
	      } catch(Exception e ) {
	         System.out.println("Error " + e);
	      }
	      return template;
	      }
	   }
	
	private boolean sendEmail(String name, String title, String email, String messageText) throws ClassNotFoundException, SQLException {
				
		boolean emailStatus = false;
		
		String to = "sirdevious@gmail.com";// change accordingly

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("javaecom@gmail.com","teammaster10");// change accordingly
			}
		});

		// compose message
		try {
			 
			System.out.println("9");
			
			 String selectQuery = "SELECT Author.email from AuthorReviewer INNER JOIN Author ON AuthorReviewer.authorID = Author.authorID where isEditor = 1";
			
			 conn = new ConnectionManager();
			 st = conn.getInstance().getConnection().createStatement();
			 ResultSet rs = st.executeQuery(selectQuery);

			    while (rs.next()) {

			    	String editorEmail = (String)rs.getObject("Author.email");
					
					System.out.println(editorEmail);
					
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(editorEmail));// change accordingly
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject("Automatic Message - Java ecommarce");
					message.setText("Name: " + name + "\n" + "email:" + email + "\n" + "Title: " + title + "\n" + "Message " + messageText);

					// send message
					Transport.send(message);

				}
			  
				rs.close();
				st.close();
				conn.close();
				
			emailStatus = true;
						
		} catch (MessagingException e) {
			
			emailStatus = false;
			throw new RuntimeException(e);
		}
		
		return emailStatus;
		
	}
	
	}
