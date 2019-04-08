package com;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Script {
	//Member Variables
	private String mUniqueID;//Unique data ID
	private String mFilePath; //Where the script is located
	private String mTitle;//Title of the Script
	private String mGenre; //Genre of this script
	private String mAuthor; //Author of the script
	private String mPublishingCompany;// Publisher of the script
	private List<Comment> mComments = new ArrayList<Comment>(); //Comments for this Script
	
	
	//Construction Method
	public Script(String uniqueID, String filePath, String title, String genre,
			String author, String publishingCompany) {
		mUniqueID = uniqueID;
		mFilePath = filePath;
		mTitle = title;
		mGenre = genre;
		mAuthor = author;
		mPublishingCompany = publishingCompany;
	}
	//Base Construction Method
	public Script() {
		
	}
	
	//Getter or Accessor Methods
	public String getID() {
		return mUniqueID;
	}
	public String getFilePath() {
		return mFilePath;
	}
	public String getTitle() {
		return mTitle;
	}
	public String getGenre() {
		return mGenre;
	}
	public String getAuthor() {
		return mAuthor;
	}
	public String getPublishingCompany() {
		return mPublishingCompany;
	}
	public List<Comment> getComments() {
		return mComments;
	}
	
	//Setter Methods
	public void setID(String id) {
		mUniqueID = id;
	}
	public void setFilePath(String file) {
		mFilePath = file;
	}
	public void setTitle(String title) {
		mTitle = title;
	}
	public void setGenre(String genre) {
		mGenre = genre;
	}
	public void setAuthor(String author) {
		mAuthor = author;
	}
	public void setPublishingCompany(String company) {
		mPublishingCompany = company;
	}
	public void setComments(List<Comment> comments) {
		mComments = comments;
	}
	//Show Comments
	public void showComments() {
		if (mComments.size() == 0) {
			System.out.println("There are no comments on this script.");
		}
		else {
			for (int x = 0; x < mComments.size(); x++) {
				System.out.printf("Comment %s: By %s on Line %s: %n%n %s%n", 
						x+1, mComments.get(x).getAuthor(), mComments.get(x).getLine(),
						mComments.get(x).getComment());
			}
		
		}
	}
	
	//Add Comment
	public void addComment(Comment newComment) {
		mComments.add(newComment);
		
		System.out.println();
	}
	
	//To String Method
		@Override
		public String toString() {
			return String.format("%s is in the %s genre. It was written " +
		"by %s and is published under %s. %n %n",
		mTitle, mGenre, mAuthor, mPublishingCompany);
		}
	
}
