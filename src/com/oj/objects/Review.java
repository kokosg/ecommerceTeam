/**
 * 
 */
package com.oj.objects;

import java.util.Date;

/**
 * @author Master Team 10
 *
 */
public class Review {

	private int reviewID;
	private int authorReviewerID;
	private int articleID;
	private String judgement;
	private String expertise;
	private String summary;
	private int criticismID;
	private String smallErrors;
	private String editorsComments;
	private boolean isAccepted;
	private Date dateSubmitted;
	private boolean isDownloaded;
	private String criticism;
	 
	/**
	 * 
	 */
	public Review() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param reviewID
	 * @param authorReviewerID
	 * @param articleID
	 * @param judgement
	 * @param expertise
	 * @param summary
	 * @param criticismID
	 * @param smallErrors
	 * @param editorsComments
	 * @param isAccepted
	 * @param dateSubmitted
	 */
	public Review(int reviewID, int authorReviewerID, int articleID,
			String judgement, String expertise, String summary, int criticismID,
			String smallErrors, String editorsComments, boolean isAccepted,
			Date dateSubmitted) {
		super();
		this.reviewID = reviewID;
		this.authorReviewerID = authorReviewerID;
		this.articleID = articleID;
		this.judgement = judgement;
		this.expertise = expertise;
		this.summary = summary;
		this.criticismID = criticismID;
		this.smallErrors = smallErrors;
		this.editorsComments = editorsComments;
		this.isAccepted = isAccepted;
		this.dateSubmitted = dateSubmitted;
	}

	/**
	 * @return the reviewID
	 */
	public int getReviewID() {
		return reviewID;
	}

	/**
	 * @param reviewID the reviewID to set
	 */
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
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
	 * @return the judgement
	 */
	public String getJudgement() {
		return judgement;
	}

	/**
	 * @param judgement2 the judgement to set
	 */
	public void setJudgement(String judgement2) {
		this.judgement = judgement2;
	}

	/**
	 * @return the expertise
	 */
	public String getExpertise() {
		return expertise;
	}

	/**
	 * @param expertise the expertise to set
	 */
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the criticismID
	 */
	public int getCriticismID() {
		return criticismID;
	}

	/**
	 * @param criticismID the criticismID to set
	 */
	public void setCriticismID(int criticismID) {
		this.criticismID = criticismID;
	}

	/**
	 * @return the smallErrors
	 */
	public String getSmallErrors() {
		return smallErrors;
	}

	/**
	 * @param smallErrors the smallErrors to set
	 */
	public void setSmallErrors(String smallErrors) {
		this.smallErrors = smallErrors;
	}

	/**
	 * @return the editorsComments
	 */
	public String getEditorsComments() {
		return editorsComments;
	}

	/**
	 * @param editorsComments the editorsComments to set
	 */
	public void setEditorsComments(String editorsComments) {
		this.editorsComments = editorsComments;
	}

	/**
	 * @return the isAccepted
	 */
	public boolean isAccepted() {
		return isAccepted;
	}

	/**
	 * @param isAccepted the isAccepted to set
	 */
	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	/**
	 * @return the dateSubmitted
	 */
	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	/**
	 * @param dateSubmitted the dateSubmitted to set
	 */
	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	/**
	 * @return the isDownloaded
	 */
	public boolean isDownloaded() {
		return isDownloaded;
	}

	/**
	 * @param isDownloaded the isDownloaded to set
	 */
	public void setDownloaded(boolean isDownloaded) {
		this.isDownloaded = isDownloaded;
	}


	public String getCriticism() {
		return criticism;
	}

	public void setCriticism(String criticism) {
		this.criticism = criticism;
	}

}
