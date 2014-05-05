/**
 * 
 */
package objects;

import java.util.Date;


/**
 * @author Master Team 10
 *
 */
public class Article {

	private int articleID;
	private String title;
	private String summary;
	private boolean published;
	private boolean reviewed;
	private int pageNo;
	private boolean selected;
	private boolean chosen;
	private boolean isDownloaded;
	private Date datereviewSubmitted;
	
	/**
	 * 
	 */
	public Article() {
	}

	
	/**
	 * 
	 */
	public Article(int articleID, String title, String Summary, boolean published, boolean reviewed, int pageNo) {
		this.articleID = articleID;
		this.title = title;
		this.summary = Summary;
		this.published = published;
		this.reviewed = reviewed;
		this.pageNo = pageNo;
	}

	public Article(int articleId,String title, String summary) {
		this.articleID = articleId;
		this.title = title;
		this.summary = summary;
		
	}
	
	public Article(int articleID,String title,String summary,boolean chosen) {
		this.articleID = articleID;
		this.title = title;
		this.summary = summary;
		this.chosen=chosen;
		
		
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
	 * @return the Summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param Summary the Summary to set
	 */
	public void setSummary(String Summary) {
		this.summary = Summary;
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
	 * @return the reviewed
	 */
	public boolean isReviewed() {
		return reviewed;
	}

	/**
	 * @param reviewed the reviewed to set
	 */
	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
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


	public void setSelected(boolean selected) {
		this.selected = selected;
		
	}
	public boolean isSelected(){
		return selected;
	}


	public boolean isChosen() {
		return chosen;
	}


	public void setChosen(boolean chosen) {
		this.chosen = chosen;
	}


	public void setDownloaded(boolean isDownloaded){
		this.isDownloaded= isDownloaded;
	}
	/**
	 * @return the isDownloaded
	 */
	public boolean isDownloaded() {
		return isDownloaded;
	}


	public Date getDatereviewSubmitted() {
		return datereviewSubmitted;
	}


	public void setDatereviewSubmitted(Date datereviewSubmitted) {
		this.datereviewSubmitted = datereviewSubmitted;
	}
}
