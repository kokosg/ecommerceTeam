/**
 * 
 */
package objects;

import java.sql.Date;

/**
 * @author Master Team 10
 *
 */
public class ArticleRevision {

	private int articleRevisionID;
	private int articleID;
	private String filePath;
	private Date dateSubmitted;
	 
	
	/**
	 * 
	 */
	public ArticleRevision() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param articleRevisionID
	 * @param articleID
	 * @param filePath
	 * @param dateSubmitted
	 */
	public ArticleRevision(int articleRevisionID, int articleID,
			String filePath, Date dateSubmitted) {
		this.articleRevisionID = articleRevisionID;
		this.articleID = articleID;
		this.filePath = filePath;
		this.dateSubmitted = dateSubmitted;
	}

	/**
	 * @return the articleRevisionID
	 */
	public int getArticleRevisionID() {
		return articleRevisionID;
	}

	/**
	 * @param articleRevisionID the articleRevisionID to set
	 */
	public void setArticleRevisionID(int articleRevisionID) {
		this.articleRevisionID = articleRevisionID;
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
