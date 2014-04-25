package objects;
/** Keyword object
 * @author Team:Master10
 */
public class Keyword {

	private int keywordID;
    private String text;
 
	/**
	 * Empty Constructor
	 */
    public Keyword() {
    }
 
	/**Creates a keyword object
	 * @param keywordID
	 * @param text the text of a keyword
	 */
    public Keyword(int keywordID, String text) {
        this.keywordID = keywordID;
    	this.text = text;
    }
 
	/**
	 * @return the keywordID
	 */
	public int getKeywordID() {
		return keywordID;
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
}