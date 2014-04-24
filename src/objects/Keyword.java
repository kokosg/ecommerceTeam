package objects;

public class Keyword {
	private int keywordID;
    private String text;
 
    public Keyword() {
    }
 
    public Keyword(int keywordID, String text) {
        this.keywordID = keywordID;
    	this.text = text;
    }
 
    //getter methods
    public int getKeywordID() {
    	return this.keywordID;
    }
    
    public String getText() {
    	return this.text;
    }
    
    //setter methods
    public void setText(String text) {
    	this.text = text;
    }   
}