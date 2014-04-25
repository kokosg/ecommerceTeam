/**
 * 
 */
package objects;

import java.sql.Date;

/**
 * @author 
 *
 */
public class Article {

	private int articleID;
	private String title;
	private String summary;
	private boolean published;
	private boolean reviewed;
	private int pageNo;
	private Date dateSubmitted;
	
	/**
	 * 
	 */
	public Article() {
	}

	
	/**
	 * 
	 */
	public Article(int articleID, String title, String artcileAbstrauct, boolean published, boolean reviewed, int pageNo, Date dateSubmitted) {
		this.articleID = articleID;
		this.title = title;
		this.summary = artcileAbstrauct;
		this.published = published;
		this.reviewed = reviewed;
		this.pageNo = pageNo;
		this.dateSubmitted = dateSubmitted;
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
	 * @return the artcileAbstrauct
	 */
	public String getArtcileAbstrauct() {
		return summary;
	}

	/**
	 * @param artcileAbstrauct the artcileAbstrauct to set
	 */
	public void setArtcileAbstrauct(String artcileAbstrauct) {
		this.summary = artcileAbstrauct;
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

}
