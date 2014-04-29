package objects;
/** Subscriber object
 * @author Team:Master10
 */
public class Subscriber {

	private int subscriberID;
    private String email;
    private Boolean editionSubscriber;
    private Boolean keywordSubscriber;
 
	/**
	 * Empty constructor
	 */
    public Subscriber() {
    }
 
	/**Creates a subscriber object
	 * @param subscriberID
	 * @param email of the subscriber
	 * @param editionSubscriber if the subscriber has subscribe for future editions 
	 * @param keywordSubscriber if the subscriber has subscribe for keywords
	 */
    public Subscriber(int subscriberID, String email, Boolean editionSubscriber, Boolean keywordSubscriber) {
        this.subscriberID = subscriberID;
    	this.email = email;
    	this.editionSubscriber = editionSubscriber;
    	this.keywordSubscriber = keywordSubscriber;
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
	 * @return the editionSubscriber
	 */
	public Boolean getEditionSubscriber() {
		return editionSubscriber;
	}

	/**
	 * @param editionSubscriber the editionSubscriber to set
	 */
	public void setEditionSubscriber(Boolean editionSubscriber) {
		this.editionSubscriber = editionSubscriber;
	}

	/**
	 * @return the keywordSubscriber
	 */
	public Boolean getKeywordSubscriber() {
		return keywordSubscriber;
	}

	/**
	 * @param keywordSubscriber the keywordSubscriber to set
	 */
	public void setKeywordSubscriber(Boolean keywordSubscriber) {
		this.keywordSubscriber = keywordSubscriber;
	}
}
