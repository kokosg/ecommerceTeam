/**
 * 
 */
package com.oj.objects;

/**
 * @author Master Team 10
 *
 */
public class EmailMessage {

	private int messageID;
	private String name;
	private String title;
	private String email;
	private String message;
	private String answer;
	private boolean published;
	
	/**
	 * 
	 */
	public EmailMessage() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param name
	 * @param title
	 * @param email
	 * @param message
	 */
	public EmailMessage(String name, String title, String email, String message) {
		this.name = name;
		this.title = title;
		this.email = email;
		this.message = message;
	}
	
	/**
	 * @param messageID
	 * @param name
	 * @param title
	 * @param email
	 * @param message
	 * @param answer
	 * @param published
	 */
	public EmailMessage(int messageID, String name, String title, String email, String message, String answer, boolean published) {
		this.messageID = messageID;
		this.name = name;
		this.title = title;
		this.email = email;
		this.message = message;
		this.answer = answer;
		this.published = published;
	}

	/**
	 * @return the messageID
	 */
	public int getMessageID() {
		return messageID;
	}

	/**
	 * @param messageID the messageID to set
	 */
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the published
	 */
	public boolean isPublished() {
		return published;
	}

	/**
	 * @param published the published to set
	 */
	public void setPublished(boolean published) {
		this.published = published;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
