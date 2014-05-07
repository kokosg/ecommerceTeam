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
	 private int startPageNo;
	 private Date datePublished;
	 private int articleID;
	 private String title;
	 private int published;
	 private int reviewed;
	 private int needsRevision;
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
	 */
	public BrowseObject(int volumeID, int volumeNo, int editionNo) {
		this.volumeID = volumeID;
		this.volumeNo = volumeNo;
		this.editionNo = editionNo;
	}
	
	
	/**
	 * @param editionNo
	 * @param startPageNo
	 * @param datePublished
	 * @param articleID
	 * @param title
	 * @param published;
	 * @param reviewed;
	 * @param needsRevision;
	 * @param summary
	 * @param pageNo
	 * @param filePath
	 */
	public BrowseObject(int editionNo, int startPageNo, Date datePublished, int articleID, String title, int published, int reviewed, int needsRevision, String summary, int pageNo, String filePath) {
		this.editionNo = editionNo;
		this.startPageNo = startPageNo;
		this.datePublished = datePublished;
		this.articleID = articleID;
		this.title = title;
		this.published = published;
		this.reviewed = reviewed;
		this.needsRevision = needsRevision;
		this.summary = summary;
		this.pageNo = pageNo;
		this.filePath = filePath;
	}

	/**
	 * @return the published
	 */
	public int getPublished() {
		return published;
	}

	/**
	 * @param published the published to set
	 */
	public void setPublished(int published) {
		this.published = published;
	}

	/**
	 * @return the reviewed
	 */
	public int getReviewed() {
		return reviewed;
	}

	/**
	 * @param reviewed the reviewed to set
	 */
	public void setReviewed(int reviewed) {
		this.reviewed = reviewed;
	}

	/**
	 * @return the needsRevision
	 */
	public int getNeedsRevision() {
		return needsRevision;
	}

	/**
	 * @param needsRevision the needsRevision to set
	 */
	public void setNeedsRevision(int needsRevision) {
		this.needsRevision = needsRevision;
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
