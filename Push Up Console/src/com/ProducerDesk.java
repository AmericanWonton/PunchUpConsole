package com;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.Random;


public class ProducerDesk {
	//Member Variables
	private String mUniqueID; //A Unique ID for data identification
	private Map<Integer,String> mUsers = new HashMap<Integer, String>(); //All the Users on the app. (Just Names)
	private Map<Integer, Scriptwriter> mScriptwriters = new HashMap<Integer, Scriptwriter>(); //All of the scriptwriters(Everything)
	private List<Script> mScripts = new ArrayList<Script>(); //All the scripts on our app.
	private List<Comment> mComments = new ArrayList<Comment>(); //All the comments on our app.
	private final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'O', '1', '2', '3', '4', '5', '6', '7',
			'8', '9'}; //Used for Random ID Creation
	
	//Constructor Class
	public ProducerDesk(String id, Map<Integer,String> users, Map<Integer, Scriptwriter> writers,
			List<Script> scripts, List<Comment> comments) {
		mUniqueID = id;
		mUsers = users;
		mScriptwriters = writers;
		mScripts = scripts;
		mComments = comments;
	}
	//Base Constructor Class
	public ProducerDesk() {
		Random r = new Random();
		mUniqueID = "";
		for (int i = 0; i < 10; i++) {
			int randomNumber = r.nextInt(alphabet.length);
			mUniqueID = mUniqueID + alphabet[randomNumber];
		}
		
	}

	//Getters or Accessor Methods
	public String getID() {
		return mUniqueID;
	}
	public Map<Integer,String> getUsers() {
		return mUsers;
	}
	public Map<Integer, Scriptwriter> getScriptwriters() {
		return mScriptwriters;
	}
	public List<Script> getScripts() {
		return mScripts;
	}
	public List<Comment> getComments(){
		return mComments;
	}
	
	//Setter Methods
	public void setID(String id) {
		mUniqueID = id;
	}
	public void setUsers(Map<Integer,String> users) {
		mUsers = users;
	}
	public void setScriptWriters(Map<Integer, Scriptwriter> writers) {
		mScriptwriters = writers;
	}
	public void setScripts(List<Script> scripts) {
		mScripts = scripts;
	}
	public void setComments(List<Comment> comments) {
		mComments = comments;
		for (int g = 0; g < mComments.size(); g++) {
			for (int k = 0; k < mScripts.size(); k++) {
				if (mScripts.get(k).getID().equalsIgnoreCase
						(mComments.get(g).getScript().getID())){
					mScripts.get(k).addComment(mComments.get(g));
				}
			}
		}
		
	}
	//Add a User to the Producer Desk
	public void addUser(String value) {
		int mUsersSize = 0;
		if (mUsers.isEmpty()) {
			System.out.println("New user detected, adding user.");
			mUsers.put(mUsersSize, value);
		}
		else {
			mUsersSize = 0;
			for (int j = 0; j < mUsers.size(); j++) {
				mUsersSize++;
			}
			System.out.println("New user added!");
			mUsers.put(mUsersSize, value);
		}
	}
	//Add a scriptwriter to the Producer Desk
	public void addScriptwriter(Scriptwriter newWriter) {
		int mScriptwriterSize = 0;
		if (mScriptwriters.isEmpty()) {
			System.out.println("Congrats on being our first user!");
			mScriptwriters.put(mScriptwriterSize, newWriter);
			mScriptwriterSize++;
		}
		else {
			mScriptwriterSize = 0;
			for (int x = 0; x < mScriptwriters.size(); x++) {
				mScriptwriterSize++;
			}
			System.out.println("User added!");
			mScriptwriters.put(mScriptwriterSize, newWriter);
		}
	}
	//Add a script to the script list
	public void addScript(Script newScript) {
		mScripts.add(newScript);
	}
	//Add a comment to the Comment list
	public void addComment(Comment newComment) {
		mComments.add(newComment);
	}
	//Remove a Script from the list
	public void removeScript(Script scriptRemoved) {
		int removeIndex = 0;
		for (int j = 0; j < mScripts.size(); j++) {
			if (scriptRemoved.getTitle().contentEquals(mScripts.get(j).getTitle())) {
				removeIndex = j;
				break;
			}
		}
		
		mScripts.remove(removeIndex);
	}
	public String randomIDGiver() {
		String random =  "";
		Random r = new Random();
		
		for (int i = 0; i < 10; i++) {
			int randomNumber = r.nextInt(alphabet.length);
			random = random + alphabet[randomNumber];
		}
		
		return random;
	}
	//To String Method
		@Override
		public String toString() {
			return String.format("Producer desk: %s %n %n", mUniqueID);
		}
}
