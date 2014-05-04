/**
 * 
 */
package objects;

import java.sql.Date;

/**
 * @author acp13gg
 *
 */
public class Choices {

	private int articleID;
	private String title;
	private int choiceID;
	private Date dateChosen;
	private int authorReviewerID;
	private int authorID;
	private String name;
	private String surname;
	private String email;
	
	/**
	 * 
	 */
	public Choices() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param authorReviewerID
	 * @param authorID
	 * @param name
	 * @param surname
	 * @param email
	 */
	public Choices(int authorReviewerID, int authorID, String name, String surname, String email) {
		this.authorReviewerID = authorReviewerID;
		this.authorID = authorID;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	/**
	 * @param articleID
	 * @param title
	 * @param choiceID
	 * @param dateChosen
	 * @param authorReviewerID
	 * @param authorID
	 * @param name
	 * @param surname
	 * @param email
	 */
	public Choices(int articleID, String title, int choiceID, Date dateChosen, int authorReviewerID, int authorID, String name, String surname, String email) {
		this.articleID = articleID;
		this.title = title;
		this.choiceID = choiceID;
		this.dateChosen = dateChosen;
		this.authorReviewerID = authorReviewerID;
		this.authorID = authorID;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	/**
	 * @return the articleID
	 */
	public int getArticleID() {
		return articleID;
	}

	/**
	 * @param articleID the articleID to set
	 */
	public void setArticleID(int articleID) {
		this.articleID = articleID;
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
	 * @return the choiceID
	 */
	public int getchoiceID() {
		return choiceID;
	}

	/**
	 * @param choiceID the choiceID to set
	 */
	public void setchoiceID(int choiceID) {
		this.choiceID = choiceID;
	}

	/**
	 * @return the dateChosen
	 */
	public Date isDateChosen() {
		return dateChosen;
	}

	/**
	 * @param dateChosen the dateChosen to set
	 */
	public void setDateChosen(Date dateChosen) {
		this.dateChosen = dateChosen;
	}

	/**
	 * @return the authorReviewerID
	 */
	public int getAuthorReviewerID() {
		return authorReviewerID;
	}

	/**
	 * @param authorReviewerID the authorReviewerID to set
	 */
	public void setAuthorReviewerID(int authorReviewerID) {
		this.authorReviewerID = authorReviewerID;
	}

	/**
	 * @return the authorID
	 */
	public int getAuthorID() {
		return authorID;
	}

	/**
	 * @param authorID the authorID to set
	 */
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
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
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
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
