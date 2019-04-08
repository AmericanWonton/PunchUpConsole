package com;

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.awt.Desktop; //Needed to open files.

public class NavigationMenu {
	//Member Variables
	private ProducerDesk mProdDesk; //A producer Desk to send to the Main Class.
	private final String BACK = "back"; //A Menu navigation string
	private String mUsername; //Used to create usernames.
	private String mPassword; //Used to create passwords.
	private boolean mSignedIn; //Is the user signed in?
	
	//Basic Contructor
	public NavigationMenu() {
		// TODO Auto-generated constructor stub
	}
	//In depth Constructor
	
	//Accessor Functions
	public ProducerDesk getProducerDesk() {
		return mProdDesk;
	}
	public String getUsername() {
		return mUsername;
	}
	//Menu Navigation
	public boolean makeAccount(ProducerDesk pd, Scanner scan) {
		mProdDesk = pd;
		boolean finalBack = false;
		boolean[] backers = {false, false};
		
		do {
			mUsername = "";
			Scriptwriter writer = new Scriptwriter();
			mUsername = nameMaker(scan);
			if (mUsername.equalsIgnoreCase(BACK)) { //Dosen't want to create new account.
				backers[1] = true;
				System.out.println("No Username given, going back to Main Menu.");
				mUsername = null;
				mPassword = null;
			}
			else {
				writer.setUserName(mUsername); //Gives this new account it's username.
				System.out.printf("%n Now, enter your password. Enter %s to go back.%n", BACK);
				mPassword = passwordMaker(scan);
				if (mPassword.equalsIgnoreCase(BACK)) {
					backers[1] = true;
					System.out.println("No password given, returning to Main Menu.");
					mPassword = null;
					mUsername = null;
				}
				else {
					writer.setPassword(mPassword);
					String id = pd.randomIDGiver();
					writer.setUniqueID(id);
					System.out.println("Account created! Sign in again to use your account!");
					writer.setUniqueID(pd.randomIDGiver());
					mProdDesk.addScriptwriter(writer);
					mProdDesk.addUser(writer.getUsername());
					backers[1] = true; //Back out to Main Menu
				}
			}
		}while(backers[1] != true);
		
		finalBack = true;
		
		return finalBack;
	}
	//"Signed In" Activities
	public int activities(ProducerDesk pd, Scanner scan) {
		int choice = 0;
		
		try {
			System.out.printf("Press 1 to submit a Script to your profile.%n" +
		"Press 2 to delete a script from your profile.%n" +
					"Press 3 to Look up a Script. Press 4 to sign out.%n");
			choice = Integer.parseInt(scan.nextLine());
		}catch(InputMismatchException e) {
			System.out.println("Invalid input. Please enter a number of 1, 2, 3, or 4.");
		}catch(NumberFormatException f) {
			System.out.println("Invalid input. Please enter a number of 1, 2, 3, or 4.");
		}
		while((choice != 1) && (choice != 2) && (choice != 3) && (choice!= 4)) {
					System.out.printf("Press 1 to submit a Script to your profile.%n" +
							"Press 2 to delete a script from your profile.%n" +
										"Press 3 to Look up a Script. Press 4 to sign out.%n");
					try {
						choice = Integer.parseInt(scan.nextLine());
					}catch(InputMismatchException e) {
						System.out.println("Invalid input. Please enter a number of 1, 2, or 0.");
					}
				}
		
		return choice;
	}
	//SignOut
	public boolean signOut() {
		System.out.printf("Signing you out, %s %n", mUsername);
		mUsername = "";
		mPassword = "";
		mSignedIn = false;
		
		return mSignedIn;
	}
	//Signing In
	public boolean signIn(ProducerDesk pd, Scanner scan, boolean alreadyIn) {
		boolean signedIn = false;
		boolean[] backers = {false, false};
		mProdDesk = pd;
		
		if (alreadyIn == true) {
			signedIn = true;
		}
		else {
			//Do Sign in procedure
			do
			{
				mUsername = "";
				mUsername = nameSignIn(mProdDesk, scan);
				if (mUsername.equalsIgnoreCase(BACK) ) { //Dosen't want to sign in.
					backers[1] = true;
					System.out.println("");
					signedIn = false;
				}
				else {
					//mUsername is the signed in Person.
					System.out.printf("%n Now, enter your password. Enter %s to go back.", BACK);
					mPassword = pWordSignIn(mProdDesk, scan);
					if (mPassword.equalsIgnoreCase(BACK)) {
						backers[1] = true;
						System.out.println(" Password not entered, returning to Main Menu.");
						//Dosen't know password, can't sign in. 
						signedIn = false;
					}
					else {
						//mPassword is the correct password!
						System.out.printf("Signed into account! Welcome, %s! %n ", mUsername);
						backers[1] = true; //Back out to Main Menu
						signedIn = true;
						mSignedIn = true;
						}
					}
			}while(backers[1] != true);
			
		}
		return signedIn;
	}

	//Field Entering
	public String nameMaker(Scanner skan2) {
		String name = "";
		boolean validName = false;
		
		do {
			System.out.println("Please enter a username between 10-20 characetrs " +
		"or enter 'back' to exit.");
			
			name = skan2.nextLine();
			if (name.equalsIgnoreCase(BACK)) {
				System.out.println("No Username entered, going back.");
				validName = true;
			}
			else if ((name.length() < 10 || name.length() > 20)) {
				System.out.println("Please enter a valid username between 10-20 characters.");
				validName = false;
			}
			else {
				validName = true;
			}
		}while(validName == false);
		
		return name;
	}
	public String passwordMaker(Scanner skan3) {
		String password = "";
		boolean validPassword = false;
		
		do {
			System.out.printf("Please enter a password between 10-20 characters. %n");
			password = skan3.nextLine();
			if (password.equalsIgnoreCase(BACK)) {
				System.out.println("No password entered, going back.");
				validPassword = true;
			}
			else if ((password.length() < 10 || password.length() > 20)) {
				System.out.println("Please enter a valid password between 10-20 characters.");
				validPassword = false;
			}
			else {
				validPassword = true;
			}
		}while(validPassword == false);
		
		return password;
	}
	public String nameSignIn(ProducerDesk pd, Scanner skan4) {
		String name = "";
		String nameTester = ""; //Used to test our Strings to see if the user is there.
		boolean validName = false;
		mProdDesk = pd;
		
		System.out.printf("Enter your username. Enter %s to go back.%n", BACK );
		
		do {
			System.out.printf("Username: ");
			name = skan4.nextLine();
			if (name.equalsIgnoreCase(BACK)) {
				validName = true;
				System.out.println("Returning to Main Menu.");
			}
			else {
				for (int j = 0; j < mProdDesk.getScriptwriters().size(); j++) {
					System.out.println(j);
					if (name.contentEquals(mProdDesk.getScriptwriters().get(j).getUsername())) {
						validName = true;
						System.out.println(" ");
						break;
					}
					else {
						System.out.printf("Debug name %s%n",
								mProdDesk.getScriptwriters().get(j).getUsername());
					}
				}
				//If Username not found
				if (validName != true) {
					System.out.printf("%nUsername %s not found. Try again or enter 'back' to" +
				" stop searching.%n",name);
				}
			}
			
		}while((validName == false) && (!name.equalsIgnoreCase(BACK)));
		
		return name;
	}
	public String pWordSignIn(ProducerDesk pd, Scanner skan5) {
		String password = "";
		String passwordTester = "";
		boolean validPWord = false;
		
		do {
			System.out.printf("Password: ");
			password = skan5.nextLine();
			if (password.equalsIgnoreCase(BACK)) {
				System.out.printf("Understood, returning to Main Menu.%n");
				validPWord = true;
			}
			else {
				for (int x = 0; x < pd.getScriptwriters().size(); x++) {
					passwordTester = pd.getScriptwriters().get(x).getPassword();
					if (password.contentEquals(passwordTester)) {
						validPWord = true;
						System.out.println("");
						break;
					}
				}
			}
			if (validPWord != true){
				System.out.printf("%nPassword not found. Enter 'back' to " +
			"stop searching.%n");
			}
			System.out.println("");
		}while(validPWord != true);
		
		return password;
	}
	public void submitScript(ProducerDesk pDesk, Scanner skan6) {
		mProdDesk = pDesk; //Producer Desk Initialization
		Script newScript = new Script();
		String field = "";
		boolean foundWriter = false;
		
		System.out.println("What is the name of this Script?");
		field = skan6.nextLine();
		newScript.setTitle(field);
		System.out.println("Great! What is the Genre of this script?");
		field = skan6.nextLine();
		newScript.setGenre(field);
		System.out.println("Awesome! And what publishing company is this script under?");
		field = skan6.nextLine();
		newScript.setPublishingCompany(field);
		System.out.println("Cool! Please enter the full file path of this file.");
		System.out.printf("Warning! Please enter full file path, followed by name of file, then%n"+
		" the file extension.%n" + "Example: 'D:\\Folder\\OtherFolder\\fileName.txt'%n");
		field = skan6.nextLine();
		newScript.setFilePath(field);
		System.out.println("Okay! Are you sure you want to enter this script?" +
		"Enter 'y' for yes or 'n'for no.");
		field = skan6.nextLine();
		if (field.equalsIgnoreCase("y")) {
			for (int x = 0; x < mProdDesk.getScriptwriters().size(); x++) {
				if (mUsername.contentEquals(mProdDesk.getScriptwriters().get(x).getUsername())) {
					foundWriter = true;
					newScript.setAuthor(mProdDesk.getScriptwriters().get(x).getUsername());
					mProdDesk.getScriptwriters().get(x).setScriptsWriten();
					field = mProdDesk.randomIDGiver();
					newScript.setID(field);
					mProdDesk.addScript(newScript);
					System.out.println("Script added!");
				}
			}
			if (foundWriter = false) {
				System.out.printf("Error, couldn't find %s, script not added.%n", mUsername);
			}
			
		}
		else {
			System.out.println("Either pressed 'n' for no or input not taken. Script discarded.");
		}
		
		
	}
	public void removeScript(ProducerDesk pDesk, Scanner skan7) {
		mProdDesk = pDesk; //Producer Desk Initialization
		int field = 0;
		boolean rightChoice = false; //A setting to exit the 'remove' loop.
		int loopSize = 0; //The numbers people can press to remove a script
		List<Script> authorScripts = new ArrayList<Script>();
		int exit = 0;
		
		System.out.printf("All right %s, which script would you like to remove?%n", mUsername);
		for (int x = 0; x < mProdDesk.getScripts().size(); x++) {
			if (mProdDesk.getScripts().get(x).getAuthor().contentEquals(mUsername)) {
				if (authorScripts.size() == 0) {
					authorScripts.add(mProdDesk.getScripts().get(x));
				}
				else {
					authorScripts.add(mProdDesk.getScripts().get(x));
				}
			}
		}
		System.out.printf("Debug Script Size: %s%n", authorScripts.size());
		if (authorScripts.size() == 0) {
			System.out.printf("You don't have any scripts to remove %s!%n",mUsername );
		}
		else {
			for (int j = 0; j < authorScripts.size(); j++) {
				System.out.printf("%s. %s%n", j+1, authorScripts.get(j).getTitle());
				loopSize++;
			}
			do {
				System.out.printf("Enter a number 1-%s. Or press %s to exit.%n",
						loopSize, loopSize + 1);
				try {
					field = Integer.parseInt(skan7.nextLine());
					if (field == loopSize + 1) { //chooses to exit
						System.out.println("Understood, exiting.");
						rightChoice = true;
					}
					else if ((field > loopSize) || (field <= 0)) { //If user enters wrong number
						System.out.printf("Please enter a number 1-%s. Or, press %s to exit%n", 
								loopSize, loopSize + 1);
						rightChoice = false;
					}
					else {//remove script
						System.out.printf("Okay, removing %s!%n", authorScripts.get(field -1).getTitle());
						mProdDesk.removeScript(authorScripts.get(field -1));
						for (int j = 0; j < mProdDesk.getScriptwriters().size(); j++) {//set Scripts written
							if (mProdDesk.getScriptwriters().get(j).getUsername().equalsIgnoreCase(mUsername)) {
								mProdDesk.getScriptwriters().get(j).removeScriptsWritten();
								break;
							}
						}
						rightChoice = true;
					}
				}catch(InputMismatchException e) {
					System.out.printf("Please enter a number 1-%s or press %s to exit.%n",
							loopSize, loopSize +1);
				}catch(NumberFormatException g) {
					System.out.printf("Please enter a number 1-%s or press %s to exit.%n",
							loopSize, loopSize +1);
				}
			}while(rightChoice != true);
		}
	}
	public void lookUpScript(ProducerDesk pDesk, Scanner skan8) {
		mProdDesk = pDesk; //Producer Desk Initialization
		String input = "";
		int numInput = 0;
		Script elScript = new Script();
		String foundAuthor = "";
		int foundScriptSize = 0;
		List<Script> foundScripts = new ArrayList<Script>();
		boolean[] okayChoices = {false, false, false};
		boolean goodNum = false;
		
		System.out.printf("Okay %s, do you want to look up the Script by title or author?%n", mUsername);
		do {
			try {
				System.out.printf("1.Title 2.Author%n");
				numInput = Integer.parseInt(skan8.nextLine());
				if (numInput != 1 && numInput != 2) {
					System.out.printf("Please enter 1 to look up by title or 2 to look up by author.%n");
					okayChoices[0] = false;
				}
				else {
					okayChoices[0] = true;
				}
			}catch(NumberFormatException e) {
				System.out.printf("Please enter either 1 for title or 2 for author.%n");
			}
		}while(okayChoices[0] == false);
		
		if (numInput == 1) {//By Title
			System.out.println("Please enter title of script. If you can't remember, enter 'back' to exit.");
			System.out.println(mProdDesk.getScripts().get(0).getTitle());
			do {
				input = skan8.nextLine();
				if (input.equalsIgnoreCase(BACK)) {
					System.out.println("Script not found, going back.");
					okayChoices[1] = true;
					break;
				}
				else {
					for (int g = 0; g < mProdDesk.getScripts().size(); g++) {
						if (input.equalsIgnoreCase(mProdDesk.getScripts().get(g).getTitle())) {
							elScript = mProdDesk.getScripts().get(g);
							okayChoices[1] = true;
							System.out.println("Name found.");
							break;
						}
					}
				}
				if (okayChoices[1] != true) {
					System.out.println("Script title not found. Enter it again or type back to go back.");
				}
			}while(okayChoices[1] != true);
			//Script is either found or we are exiting (Yes if, no else,(exit))
			if (!input.equalsIgnoreCase(BACK)) {//Found by title
				//Open Script
				fileScriptOpen(elScript);
				System.out.printf("%s found! What would you like to do?%n", input);
				do {
					try {
						System.out.printf("1. Comment on %s 2. Look at Comments. 3.Exit%n", input);
						numInput = Integer.parseInt(skan8.nextLine());
						while ((numInput != 1) && (numInput != 2) && (numInput != 3)) {
							System.out.printf("Please enter 1 to give %s a comment, 2. to look at" +
						" comments for %s, or 3 to exit.%n", input, input);
							numInput = Integer.parseInt(skan8.nextLine());
						}
					}catch(NumberFormatException e) {
						System.out.printf("Please enter either 1 for comment, 2 for looking at comments,"+
					" or 3 to exit.%n");
						numInput = 0;
					}
					switch (numInput) {
						case 1:
							//Give Comment
							addComment(mProdDesk, skan8, elScript);
							break;
						case 2:
							//Look at Comments
							lookAtComments(elScript);
							break;
						
						case 3:
							//Exit
							System.out.println("Exiting to Main Menu.");
							okayChoices[2] = true;
							break;
						
						default:
							System.out.println("Error, value not found, exiting.");
							okayChoices[2] = true;
							break;
							
					}
						
				}while(okayChoices[2] == false);
			}
			else {//title == BACK
				System.out.printf("Understood, exiting to main menu.%n");
			}
		}
		else {//By Author (numInput ==2)
			System.out.println("Please enter Author of script. If you can't remember, enter 'back' to exit.");
			do {
				input = skan8.nextLine();
				if (input.equalsIgnoreCase(BACK)) {
					System.out.println("Author not found, going back.");
					okayChoices[1] = true;
					break;
				}
				else {
					for (int g = 0; g < mProdDesk.getScripts().size(); g++) {
						if (input.equalsIgnoreCase(mProdDesk.getScripts().get(g).getAuthor())) {
							elScript = mProdDesk.getScripts().get(g);
							foundScripts.add(elScript);
							foundScriptSize++;
							okayChoices[1] = true;
						}
					}
				}
				if (okayChoices[1] != true) {
					System.out.printf("Sorry, we did not find any titles for %s. Enter the author again" +
				"or enter %s to exit.'%n", input, BACK);
				}
				
			}while(okayChoices[1] != true);
			if (!input.equalsIgnoreCase(BACK)) {
				do {
					System.out.printf("We found %s scripts by %s.%n" +
							"Select your choices below, or press %s to exit.%n", 
							foundScriptSize, input, foundScriptSize + 1);
					goodNum = false;
					while (goodNum != true) {
						try {
							for (int x = 0; x < foundScripts.size(); x++) {
								System.out.printf("%s: %s%n", x+1, foundScripts.get(x).getTitle());
							}
							System.out.printf("%s: Exit", foundScriptSize + 1); //Exit Button
							numInput = Integer.parseInt(skan8.nextLine());
							while ((numInput < 1) || (numInput > foundScriptSize+ 1)) {
								System.out.printf("Please enter a number 1 through %s.%n", foundScriptSize +1);
								numInput = Integer.parseInt(skan8.nextLine());
							}
						}catch(NumberFormatException e) {
							System.out.printf("Please enter a number 1 through %s.%n",
									foundScriptSize + 1);
						}catch(IndexOutOfBoundsException j) {
							System.out.printf("Out of range. Please enter a number 1 through %s.%n",
									foundScriptSize +1);
						}
						if ((numInput >=1) && (numInput <= foundScriptSize + 1)) {
							goodNum = true;
						}
						else {
							goodNum = false;
						}
					}
					if (numInput != foundScriptSize + 1) {
						elScript = foundScripts.get(numInput -1);
						//Open Script
						fileScriptOpen(elScript);
						System.out.printf("All right, what would you like to do with " +
						"%s?%n", elScript.getTitle());
						System.out.printf("Press 1 to see comments, press 2 to add" +
						" a comment, or 3 to exit.%n");
						try {
							System.out.printf("1. Comment on %s 2. Look at Comments. 3.Exit%n",
									elScript.getTitle());
							numInput = Integer.parseInt(skan8.nextLine());
							while ((numInput != 1) && (numInput != 2) && (numInput != 3)) {
								System.out.printf("Please enter 1 to give %s a comment, 2. to look at" +
							" comments for %s, or 3 to exit.%n",
							elScript.getTitle(), elScript.getTitle());
								numInput = Integer.parseInt(skan8.nextLine());
							}
						}catch(NumberFormatException e) {
							System.out.printf("Please enter either 1, 2, or 3.");
							skan8.nextLine();
						}
						
						switch (numInput) {
						case 1:
							//Give Comment
							addComment(mProdDesk, skan8, elScript);
							break;
							
						case 2:
							//See Comment
							lookAtComments(elScript);
							break;
							
						case 3:
							//Exit
							System.out.println("Exiting to Main Menu.");
							okayChoices[2] = true;
							break;
							
						default:
							//Error, leaving to Main Menu.
							System.out.println("Error, returning to Main Menu.");
							break;
						}
					}
					else {
						System.out.println("Understood, exiting to Main Menu.");
						okayChoices[2] = true;
					}
				}while(okayChoices[2] != true);
			}
			else {//Author == BACK
				System.out.printf("Understood, exiting to main menu.%n");
			}
		}
	}
	public void addComment(ProducerDesk desk, Scanner skan9, Script comScript) {
		mProdDesk = desk;
		Comment newComment = new Comment();
		int line = 0;
		String comment;
		boolean goodLine = false;
		
		System.out.printf("At what line of %s do you want to comment?%n", 
				comScript.getTitle());
		do {
			try {
				line = Integer.parseInt(skan9.nextLine());
				goodLine = true;
			}catch(NumberFormatException e) {
				System.out.println("Please enter a line number.");
			}
		}while(goodLine ==false);
		
		System.out.printf("What do you want to say on line %s? Press 'enter'"+
		"to submit comment.%n",line);
		goodLine = false;
		comment = skan9.nextLine();
		
		newComment.setID(desk.randomIDGiver());
		newComment.setAuthor(mUsername);
		newComment.setLine(line);
		newComment.setComment(comment);
		newComment.setScriptTitle(comScript.getTitle());
		
		for (int f = 0; f != mProdDesk.getScriptwriters().size(); f++) {//Sets NewComment Scriptwriter ID
			if (mProdDesk.getScriptwriters().get(f).getUsername().equalsIgnoreCase(mUsername)) {
				newComment.setScriptWriter(mProdDesk.getScriptwriters().get(f));
				break;
			}
		}
		
		for (int j = 0; j != mProdDesk.getScripts().size(); j++) {//Add comment to script and producer desk
			if (mProdDesk.getScripts().get(j).getID().contentEquals(comScript.getID())) {
				newComment.setScript(mProdDesk.getScripts().get(j));
				mProdDesk.getScripts().get(j).addComment(newComment);
				mProdDesk.addComment(newComment);
				System.out.printf("Comment added on %s!%n", comScript.getTitle());
				break;
				
			}
		}
		
		
		
	}
	public void lookAtComments(Script comScript) {
		comScript.showComments();
	}
	//Making the Script Visible
	public void fileScriptOpen(Script script) {
		if (Desktop.isDesktopSupported()) {
			try {
				String fileLocation = script.getFilePath();
				System.out.println("Opening file.");
				Desktop.getDesktop().open(new File(fileLocation));
			}catch(Exception e) {
				System.out.println("Error, cannot open file.");
				System.out.println(e);
			}
		}
		else {
			System.out.println("File can't be opened, this Desktop is not supported.");
		}
	}
	

}
