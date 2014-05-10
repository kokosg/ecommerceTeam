/**
 * 
 */
package com.oj.objects;

/**
 * @author Master Team 10
 *
 */
public class JournalObject {

	private int journalID;
	private String title;
	private String aimsGoals;
	
	/**
	 * 
	 */
	public JournalObject() {
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @param journalID
	 * @param title
	 * @param aimsGoals
	 */
	public JournalObject(int journalID, String title, String aimsGoals) {
		this.journalID = journalID;
		this.title = title;
		this.aimsGoals = aimsGoals;
	}



	/**
	 * @return the journalID
	 */
	public int getJournalID() {
		return journalID;
	}

	/**
	 * @param journalID the journalID to set
	 */
	public void setJournalID(int journalID) {
		this.journalID = journalID;
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
	 * @return the aimsGoals
	 */
	public String getAimsGoals() {
		return aimsGoals;
	}

	/**
	 * @param aimsGoals the aimsGoals to set
	 */
	public void setAimsGoals(String aimsGoals) {
		this.aimsGoals = aimsGoals;
	}
	
}
