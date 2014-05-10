package com.oj.objects;

public class Criticism {

	private int criticismID;
	private String criticism;
	private int reviewID;
	private boolean isCorrected;
	private boolean isAccepted;
	
	public Criticism() {
		// TODO Auto-generated constructor stub
	}
 
	/**
	 * @param criticismID
	 * @param criticism
	 * @param reviewID
	 * @param isCorrected
	 * @param isAccepted
	 */
	public Criticism(int criticismID, String criticism, int reviewID,
			boolean isCorrected, boolean isAccepted) {
		this.criticismID = criticismID;
		this.criticism = criticism;
		this.reviewID = reviewID;
		this.isCorrected = isCorrected;
		this.isAccepted = isAccepted;
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
	 * @return the criticism
	 */
	public String getCriticism() {
		return criticism;
	}

	/**
	 * @param criticism the criticism to set
	 */
	public void setCriticism(String criticism) {
		this.criticism = criticism;
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
	 * @return the isCorrected
	 */
	public boolean isCorrected() {
		return isCorrected;
	}

	/**
	 * @param isCorrected the isCorrected to set
	 */
	public void setCorrected(boolean isCorrected) {
		this.isCorrected = isCorrected;
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
	
}
