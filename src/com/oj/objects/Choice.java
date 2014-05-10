/**
 * 
 */
package com.oj.objects;


/**
 * @author 
 *
 */
public class Choice {

	private int articleID;
	private int choiceID;
	private int authorReviewerID;
	
	/**
	 * 
	 */
	public Choice() {
	}

	
	/**
	 * 
	 */
	public Choice(int articleID, int choiceID, int authorReviewerID) {
		this.articleID = articleID;
		this.choiceID = choiceID;
		this.authorReviewerID = authorReviewerID;
	}  
	
	public Choice(int articleID) {
		this.articleID = articleID;
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
	 * @return the articleID
	 */
	public int getchoiceID() {
		return choiceID;
	}

	/**
	 * @param articleID the articleID to set
	 */
	public void setchoiceID(int choiceID) {
		this.choiceID = choiceID;
	}
	
	/**
	 * @return the articleID
	 */
	public int getauthorReviewerID() {
		return authorReviewerID;
	}

	/**
	 * @param articleID the articleID to set
	 */
	public void setauthorReviewerID(int authorReviewerID) {
		this.authorReviewerID = authorReviewerID;
	}

}
