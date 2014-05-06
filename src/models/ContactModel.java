/**
 * 
 */
package models;

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

/**
 * @author Master Team 10
 *
 */
public class ContactModel {

	/**
	 * 
	 */
	public ContactModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void insertEmail(String name, String title, String email, String message) throws SQLException {
		  
		String insertQuery = "INSERT INTO Message (name, title, email, message) VALUE ('" + name + "', '" + title + "', '" + email + "', '" + message + "')";
			
		try {
			
	      ConnectionManager conn = new ConnectionManager();
    	  Statement st = conn.getInstance().getConnection().createStatement();
		  st.executeUpdate(insertQuery);
		  
	      st.close();
		  conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public boolean sendEmail(String name, String title, String email, String messageText) throws ClassNotFoundException, SQLException {
		
		boolean emailStatus = false;
		
		String to =email;// change accordingly

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
			
			 String selectQuery = "SELECT Author.email from AuthorReviewer INNER JOIN Author ON AuthorReviewer.authorID = Author.authorID where isEditor = 1";
			
			 ConnectionManager conn = new ConnectionManager();
	    	 Statement st = conn.getInstance().getConnection().createStatement();
		     ResultSet rs = st.executeQuery(selectQuery);

			    while (rs.next()) {

			    	String editorEmail = (String)rs.getObject("Author.email");
					
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(editorEmail));// change accordingly
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject("Automatic Message - Java ecommerce");
					message.setText("Name: " + name + "\n" + "email:" + email + "\n" + "Title: " + title + "\n" + "Message: "+"\n" + messageText);

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
	
	public boolean sendRegistrationEmail(String email, String messageText) throws ClassNotFoundException, SQLException {
		
		boolean emailStatus = false;
		
		String to = email;// change accordingly

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
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("javaecom@gmail.com"));// change accordingly
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Automatic Message - Journal Registration");
			message.setText("Message: "+"\n" + messageText);
			// send message
			Transport.send(message);
				
			emailStatus = true;
		} catch (MessagingException e) {
			emailStatus = false;
			throw new RuntimeException(e);
		}
		System.out.println(emailStatus);
		return emailStatus;
	}
}
