/**
 * 
 */
package objects;

import java.sql.Date;

/**
 * @author Master Team 10
 *
 */
public class BrowseObject {

	 private int volumeID;
	 private int volumeNo;
	 private int editionNo;
	 private Date endDate;
	 private int startPageNo;
	 private Date datePublished;
	 private int articleID;
	 private String title;
	 private String summary;
	 private int pageNo;
	 private String filePath;
	
	/**
	 * 
	 */
	public BrowseObject() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param volumeID
	 * @param volumeNo
	 * @param editionNo
	 * @param endDate
	 */
	public BrowseObject(int volumeID, int volumeNo, int editionNo, Date endDate) {
		this.volumeID = volumeID;
		this.volumeNo = volumeNo;
		this.editionNo = editionNo;
		this.endDate = endDate;
	}
	
	
	/**
	 * @param editionNo
	 * @param endDate
	 * @param startPageNo
	 * @param datePublished
	 * @param articleID
	 * @param title
	 * @param summary
	 * @param pageNo
	 * @param filePath
	 */
	public BrowseObject(int editionNo, Date endDate,int startPageNo, Date datePublished, int articleID, String title, String summary, int pageNo, String filePath) {
		this.editionNo = editionNo;
		this.endDate = endDate;
		this.startPageNo = startPageNo;
		this.datePublished = datePublished;
		this.articleID = articleID;
		this.title = title;
		this.summary = summary;
		this.pageNo = pageNo;
		this.filePath = filePath;
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
	 * @return the editionNo
	 */
	public int getEditionNo() {
		return editionNo;
	}

	/**
	 * @param editionNo the editionNo to set
	 */
	public void setEditionNo(int editionNo) {
		this.editionNo = editionNo;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startPageNo
	 */
	public int getStartPageNo() {
		return startPageNo;
	}

	/**
	 * @param startPageNo the startPageNo to set
	 */
	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}

	/**
	 * @return the datePublished
	 */
	public Date getDatePublished() {
		return datePublished;
	}

	/**
	 * @param datePublished the datePublished to set
	 */
	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
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
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
