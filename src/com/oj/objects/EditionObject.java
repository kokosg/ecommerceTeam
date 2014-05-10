/**
 * 
 */
package com.oj.objects;

import java.sql.Date;

/**
 * @author acp13gg
 *
 */
public class EditionObject {

	private int editionID;
	private int volumeID;
	private int editionNo;
	private String title;
	private boolean current;
	private Date dateAdded;
	private boolean published;
	
	
	/**
	 * 
	 */
	public EditionObject() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param editionID
	 * @param volumeID
	 * @param editionNo
	 * @param title
	 * @param current
	 * @param dateAdded
	 * @param published
	 */
	public EditionObject(int editionID, int volumeID, int editionNo, String title, boolean current, Date dateAdded, boolean published) {
		this.editionID = editionID;
		this.volumeID = volumeID;
		this.editionNo = editionNo;
		this.title = title;
		this.current = current;
		this.dateAdded = dateAdded;
		this.published = published;
	}



	/**
	 * @return the editionID
	 */
	public int getEditionID() {
		return editionID;
	}


	/**
	 * @param editionID the editionID to set
	 */
	public void setEditionID(int editionID) {
		this.editionID = editionID;
	}


	/**
	 * @return the volumeID
	 */
	public int getVolumeID() {
		return volumeID;
	}


	/**
	 * @param volumeID the volumeID to set
	 */
	public void setVolumeID(int volumeID) {
		this.volumeID = volumeID;
	}


	/**
	 * @return the editionNo
	 */
	public int geteditionNo() {
		return editionNo;
	}


	/**
	 * @param editionNo the editionNo to set
	 */
	public void seteditionNo(int editionNo) {
		this.editionNo = editionNo;
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
	 * @return the current
	 */
	public boolean isCurrent() {
		return current;
	}


	/**
	 * @param current the current to set
	 */
	public void setCurrent(boolean current) {
		this.current = current;
	}


	/**
	 * @return the dateAdded
	 */
	public Date getDateAdded() {
		return dateAdded;
	}


	/**
	 * @param dateAdded the dateAdded to set
	 */
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
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

	
	
}
