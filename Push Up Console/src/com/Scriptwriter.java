package com;

import java.util.List;
import java.util.ArrayList;


public class Scriptwriter {
	//Member Variables
	private String mUsername; //The Scriptwriter's Username
	private String mPassword; //The Password of the Scriptwriter's account.
	private String mUniqueID; //A unique ID for data searching purposes. (Length is 10)
	private int mScriptsWritten; //Number of scripts this person has written.
	private List<Comment> mComments = new ArrayList<Comment>(); //The comments this scriptwriter has written.
	
	
	
	//Constructor
	public Scriptwriter(String userName, String password, String uniqueID,
			int scriptsWritten, List<Comment> commentos) {
		mUsername = userName;
		mPassword = password;
		mUniqueID = uniqueID;
		mScriptsWritten = scriptsWritten;
		mComments = commentos;
	}
	//Base Constructor
	public Scriptwriter() {
		mUsername = " ";
		mPassword = " ";
		mUniqueID = " ";
		mScriptsWritten = 0;
		
	}
	
	//Getter or Accessor Methods
	public String getUsername() {
		return mUsername;
	}
	public String getPassword() {
		return mPassword;
	}
	public String getUniqueID() {
		return mUniqueID;
	}
	public int getScriptsWritten() {
		return mScriptsWritten;
	}
	public List<Comment> getComments() {
		return mComments;
	}
	
	//Setter Methods
	public void setUserName(String username) {
		mUsername = username;
	}
	public void setPassword(String password) {
		mPassword = password;
	}
	public void setUniqueID(String id) {
		mUniqueID = id;
	}
	public void setAllScriptsWritten(int num) {
		mScriptsWritten = num;
	}
	public void setScriptsWriten() {
		mScriptsWritten = mScriptsWritten + 1; //This will be called to only increment in ones.
	}
	public void removeScriptsWritten() {
		mScriptsWritten = mScriptsWritten -1;
	}
	public void setComments(List<Comment> comments) {
		mComments = comments;
	}
	public void addComment(Comment comment) {
		if (mComments == null) {
		mComments.add(comment);
		}
		else {
			mComments.add(comment);
		}
	}
	
	
	//To String Method
	@Override
	public String toString() {
		return String.format("%s has %s written scripts. %n %n.", mUsername,mScriptsWritten);
	}
	
	
}
