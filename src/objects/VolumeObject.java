/**
 * 
 */
package objects;

import java.sql.Date;

/**
 * @author acp13gg
 *
 */
public class VolumeObject {

	private int volumeID;
	private int journalID;
	private int volumeNo;
	private Date date;
	private boolean current;
	
	/**
	 * 
	 */
	public VolumeObject() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param volumeID
	 * @param journalID
	 * @param volumeNo
	 * @param date
	 * @param current
	 */
	public VolumeObject(int volumeID, int journalID, int volumeNo, Date date,
			boolean current) {
		this.volumeID = volumeID;
		this.journalID = journalID;
		this.volumeNo = volumeNo;
		this.date = date;
		this.current = current;
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
	 * @return the volumeNo
	 */
	public int getVolumeNo() {
		return volumeNo;
	}

	/**
	 * @param volumeNo the volumeNo to set
	 */
	public void setVolumeNo(int volumeNo) {
		this.volumeNo = volumeNo;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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
	
}
