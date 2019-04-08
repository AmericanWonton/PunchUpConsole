package com;

public class Comment {
	//Member Variables
	private String mUniqueID;
	private String mAuthor; //Just the name of the author of this comment.
	private Scriptwriter mScriptWriter; //The actual account user of author
	private String mScriptTitle; //Title of the script this was commented on.
	private Script mScript; //The acutal script where the comment is attested to.
	private int mLine; //The line one the script where the comment is.
	private String mComment; //The actual critique.
	
	//Constructor Method
	public Comment(String id, String author, String scriptTitle, int line, String comment) {
		mUniqueID = id;
		mAuthor = author;
		mScriptTitle = scriptTitle;
		mLine = line;
		mComment = comment;
	}
	//Base Constructor
	public Comment() {
		mUniqueID = "";
		mAuthor = "";
		mScriptTitle = "";
		mComment = "";
	}
	
	//Getter or Accessor methods
	public String getID() {
		return mUniqueID;
	}
	public String getAuthor() {
		return mAuthor;
	}
	
	public Scriptwriter getScriptWriter() {
		return mScriptWriter;
	}
	
	public String getScriptTitle() {
		return mScriptTitle;
	}
	public Script getScript() {
		return mScript;
	}
	public int getLine() {
		return mLine;
	}
	public String getComment() {
		return mComment;
	}
	
	//Setter Methods
	public void setID(String id) {
		mUniqueID = id;
	}
	public void setAuthor(String author) {
		mAuthor = author;
	}
	public void setScriptWriter(Scriptwriter writer) {
		mScriptWriter = writer;
	}
	public void setScriptTitle(String title) {
		mScriptTitle = title;
	}
	public void setScript(Script script) {
		mScript = script;
	}
	public void setLine(int line) {
		mLine = line;
	}
	public void setComment(String comment) {
		mComment = comment;
	}
	
	//To String Method
		@Override
		public String toString() {
			return String.format("This comment was posted by %s on line %s" + 
		" on the script: %s. %n %n", mAuthor, mLine, mScriptTitle);
		}
}
