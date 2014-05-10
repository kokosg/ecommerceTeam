/**
 * 
 */
package com.oj.objects;

/**
 * @author acp13gg
 *
 */
public class EmailSubscriber {

	private int keywordID;
	private String text;
	private int subKeywordID;
	private int subscriberID;
	private String editionSubscriber;
	private String keywordSubscriber;
	private String email;
	private int articleKeywordID;
	private int articleID;
	private String title;
	private String summary;
	private boolean published;
	private boolean reviewed;
	private int pageNo;
	private boolean needsRevision;
	
	/**
	 * 
	 */
	public EmailSubscriber() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param title
	 */
	public EmailSubscriber(String title) {
		this.title = title;
	}

	/**
	 * @param title
	 * @param email
	 */
	public EmailSubscriber(String title, String email) {
		this.title = title;
		this.email = email;
	}
	
	/**
	 * @param keywordID
	 * @param text
	 * @param subKeywordID
	 * @param subscriberID
	 * @param editionSubscriber
	 * @param keywordSubscriber
	 * @param email
	 * @param articleKeywordID
	 * @param articleID
	 * @param title
	 * @param summary
	 * @param published
	 * @param reviewed
	 * @param pageNo
	 * @param needsRevision
	 */
	public EmailSubscriber(int keywordID, String text, int subKeywordID,
			int subscriberID, String editionSubscriber,
			String keywordSubscriber, String email, int articleKeywordID,
			int articleID, String title, String summary, boolean published,
			boolean reviewed, int pageNo, boolean needsRevision) {
		this.keywordID = keywordID;
		this.text = text;
		this.subKeywordID = subKeywordID;
		this.subscriberID = subscriberID;
		this.editionSubscriber = editionSubscriber;
		this.keywordSubscriber = keywordSubscriber;
		this.email = email;
		this.articleKeywordID = articleKeywordID;
		this.articleID = articleID;
		this.title = title;
		this.summary = summary;
		this.published = published;
		this.reviewed = reviewed;
		this.pageNo = pageNo;
		this.needsRevision = needsRevision;
	}

	/**
	 * @return the keywordID
	 */
	public int getKeywordID() {
		return keywordID;
	}

	/**
	 * @param keywordID the keywordID to set
	 */
	public void setKeywordID(int keywordID) {
		this.keywordID = keywordID;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the subKeywordID
	 */
	public int getSubKeywordID() {
		return subKeywordID;
	}

	/**
	 * @param subKeywordID the subKeywordID to set
	 */
	public void setSubKeywordID(int subKeywordID) {
		this.subKeywordID = subKeywordID;
	}

	/**
	 * @return the subscriberID
	 */
	public int getSubscriberID() {
		return subscriberID;
	}

	/**
	 * @param subscriberID the subscriberID to set
	 */
	public void setSubscriberID(int subscriberID) {
		this.subscriberID = subscriberID;
	}

	/**
	 * @return the editionSubscriber
	 */
	public String getEditionSubscriber() {
		return editionSubscriber;
	}

	/**
	 * @param editionSubscriber the editionSubscriber to set
	 */
	public void setEditionSubscriber(String editionSubscriber) {
		this.editionSubscriber = editionSubscriber;
	}

	/**
	 * @return the keywordSubscriber
	 */
	public String getKeywordSubscriber() {
		return keywordSubscriber;
	}

	/**
	 * @param keywordSubscriber the keywordSubscriber to set
	 */
	public void setKeywordSubscriber(String keywordSubscriber) {
		this.keywordSubscriber = keywordSubscriber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the articleKeywordID
	 */
	public int getArticleKeywordID() {
		return articleKeywordID;
	}

	/**
	 * @param articleKeywordID the articleKeywordID to set
	 */
	public void setArticleKeywordID(int articleKeywordID) {
		this.articleKeywordID = articleKeywordID;
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
	 * @return the needsRevision
	 */
	public boolean isNeedsRevision() {
		return needsRevision;
	}

	/**
	 * @param needsRevision the needsRevision to set
	 */
	public void setNeedsRevision(boolean needsRevision) {
		this.needsRevision = needsRevision;
	}
	
}