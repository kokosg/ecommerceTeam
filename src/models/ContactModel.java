/**
 * 
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import objects.MessageObject;

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
	
	
	public ArrayList<MessageObject> getUnpublishMessages() throws SQLException {
		ArrayList<MessageObject> arrayResults = new ArrayList<MessageObject>();
		String queryAuthor = "select messageID, name, title, email, message, answer, published from Message where published = 0";
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(queryAuthor);
			while (rs.next()) {
				int messageID = rs.getInt("messageID");
				String name = (String) rs.getObject("name");
				String title = (String) rs.getObject("title");
				String email = (String) rs.getObject("email");
				String message = (String) rs.getObject("message");
				String answer = (String) rs.getObject("answer");
				boolean published = (Boolean) rs.getObject("published");

				
				MessageObject messageObject = new MessageObject(messageID, name, title, email, message, answer, published);
				arrayResults.add(messageObject);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return arrayResults;
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
	
	
	public boolean setAnswer(String messageID, String answer) throws SQLException {
		boolean status = false;
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String updateQuery = "UPDATE Message SET answer = '" + answer + "' WHERE messageID = '" + messageID + "'";
			st.executeUpdate(updateQuery);
			st.close();
			conn.close();
			status = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	public boolean updateMessage(String messageID) throws SQLException {
		
		boolean status = false;
		
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String updateQuery = "UPDATE Message SET published = 1 WHERE messageID = '" + messageID + "'";
			st.executeUpdate(updateQuery);
			st.close();
			conn.close();
			
			status = true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public boolean deleteMessage(String messageID) throws SQLException {
		boolean status = false;
		try {
			ConnectionManager conn = new ConnectionManager();
			Statement st = conn.getInstance().getConnection().createStatement();
			String deleteQuery = "DELETE FROM Message WHERE messageID = '" + messageID + "'";
			st.executeUpdate(deleteQuery);
			st.close();
			conn.close();
			status = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	public boolean sendContactEmail(String name, String title, String email, String messageText) throws ClassNotFoundException, SQLException {
		
		boolean emailStatus = false;
		
		final String websiteEmail = "javaecom@gmail.com";
		final String password = "teammaster10";

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(websiteEmail,password);
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
					
			    	System.out.print("aa " + editorEmail);
			    	
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(websiteEmail));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(editorEmail));
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

	
	public boolean sendEmail(String name, String email, String subject, String messageText) throws ClassNotFoundException, SQLException {
		
		boolean emailStatus = false;
		
		final String websiteEmail = "javaecom@gmail.com";
		final String password = "teammaster10";
		String to = email;
		
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(websiteEmail, password);
			}
		});

		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(websiteEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText("Dear " + name + " \n" + messageText);
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
public boolean sendEmail( ArrayList<String> email, String subject, String messageText) throws ClassNotFoundException, SQLException {
		
		boolean emailStatus = false;
		
		final String websiteEmail = "javaecom@gmail.com";
		final String password = "teammaster10";
		//String to = email;
		
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(websiteEmail, password);
			}
		});

		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(websiteEmail));
			for(String to: email){
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}
			message.setSubject(subject);
			message.setText("Dear " + "Reviewer" + " \n" + messageText);
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
