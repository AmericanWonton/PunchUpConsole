package com;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random; //Needed for unique ID generation.
import java.util.Map;
import java.util.List;
import java.awt.Desktop; //Needed to open files.


public class Main {
	
	//Debug Function: See what is in Producer Desk
	public static void pdSeer(ProducerDesk producerDesk) {
		ProducerDesk pd1 = producerDesk;
		
		System.out.printf("The users are: ");
		for (int x = 0; x < pd1.getUsers().size(); x++ ) {
			System.out.printf("%s ", pd1.getUsers().get(x));
		}
		System.out.printf("%n");
		System.out.printf("The scriptwriters are: ");
		for (int x = 0; x < pd1.getScriptwriters().size(); x++ ) {
			System.out.printf("%s ", pd1.getScriptwriters().get(x).getUsername());
		}
		System.out.printf("%n");
		System.out.printf("The scripts are: ");
		for (int x = 0; x < pd1.getScripts().size(); x++ ) {
			System.out.printf("%s ", pd1.getScripts().get(x).getTitle());
		}
		System.out.printf("%n");
		System.out.printf("The comments are: ");
		for (int x = 0; x < pd1.getComments().size(); x++ ) {
			System.out.printf("%s ", pd1.getComments().get(x).getComment());
		}
		System.out.printf("%n");
		
	}
	
	public static ProducerDesk loadPD(ProducerDesk pd) {
		ProducerDesk pd1 = pd;
		String line = "";
		boolean foundPerson = false;
		boolean startMeasure = false;
		boolean[] lineSeperators = {false, false, false, false, false, false};
		boolean[] doneLoops = {false, false, false, false, false};
		int mapKey = 0;
		String mapValue = "";
		Map<Integer, String> laUsers = new HashMap<Integer,String>();
		Map<Integer, Scriptwriter> laScriptwriters = new HashMap<Integer, Scriptwriter>();
		List<Script> laScripts = new ArrayList<Script>();
		List<Comment> laComments = new ArrayList<Comment>();
		
		try {
			File f = new File
			("E:\\Coding 2019 Work\\Java Development\\Push Up Console Version\\Project\\Producer.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			line = br.readLine(); //Should read the first asteriks
			if (line == null) {
				System.out.println("File empty, no ProducerDesk to load.");
			}
			else {
				line = br.readLine(); //Should be the PD Desk
				while (line != null) {
					for (int j = 0; j < line.length(); j++) {//Get the PD uniqueID
						if (startMeasure == true) {
							mapValue = mapValue + line.charAt(j);
						}
						if (line.charAt(j) == ' ') {
							startMeasure = true;
						}
					}
					startMeasure = false;
					pd1.setID(mapValue);
					mapValue = "";
					line = br.readLine();//Skips the **
					line = br.readLine();//Should skip over the mUsers:
					line = br.readLine(); //This is the first user.
					while (!line.equalsIgnoreCase("***DoneUsers***") && 
							(doneLoops[0] != true)) {//Adding Usernames to PD
						for (int q = 2; q < line.length();q++) {
							if (line.charAt(q) != '|') {
								mapValue = mapValue + line.charAt(q);
							}
						}
						laUsers.put(mapKey, mapValue);
						mapValue = "";
						mapKey++;
						line = br.readLine();
						if (line.equalsIgnoreCase("***DoneUsers***")) {
							doneLoops[0] = true;
						}
					}
					pd1.setUsers(laUsers); //Puts the map from this function into PD
					line = br.readLine(); //This should put us at mScriptwriters
					line = br.readLine(); //This is the first Scriptwriter
					mapKey = 0;
					mapValue = "";
					while ((!line.equalsIgnoreCase("***DoneScriptwriters***")) && 
							(doneLoops[1] != true)) {//Scriptwriter loop
						Scriptwriter newGuy = new Scriptwriter();
						for (int j = 0; j < lineSeperators.length; j++) {//set all lineseperators to false
							lineSeperators[j] = false;
						}
						for (int x = 0; x < line.length(); x++) {//read and set
							if (lineSeperators[0] == false) {//UniqueID
								if (line.charAt(x) == '|') {
									newGuy.setUniqueID(mapValue);
									mapValue = "";
									lineSeperators[0] = true;
								}
								else {
									mapValue = mapValue + line.charAt(x);
								}
							}
							else if (lineSeperators[1] == false) {//Username
								if (line.charAt(x) == '|') {
									newGuy.setUserName(mapValue);
									mapValue = "";
									lineSeperators[1] = true;
								}
								else {
									mapValue = mapValue + line.charAt(x);
								}
							}
							else if (lineSeperators[2] == false) {//Password
								if (line.charAt(x) == '|') {
									newGuy.setPassword(mapValue);
									mapValue = "";
									lineSeperators[2] = true;
								}
								else {
									mapValue = mapValue + line.charAt(x);
								}
							}
							else if (lineSeperators[3] == false) {//Number of Scripts Written
								if (line.charAt(x) == '|') {
									newGuy.setAllScriptsWritten(Integer.parseInt(mapValue));
									mapValue = "";
									lineSeperators[3] = true;
								}
								else {
									mapValue = mapValue + line.charAt(x);
								}
							}
							else {
								
							}
						}
						laScriptwriters.put(mapKey, newGuy);
						mapValue = "";
						mapKey++;
						line = br.readLine();//Read next line to set new scriptwriter
						if (line.equalsIgnoreCase("***DoneScriptwriters***")) {
							doneLoops[1] = true;
						}
					}
					pd1.setScriptWriters(laScriptwriters); //Puts scriptwriters into pd1
					mapKey = 0;
					mapValue = "";
					for (int j = 0; j < lineSeperators.length; j++) {//set all lineseperators to false
						lineSeperators[j] = false;
					}
					line = br.readLine(); //This should put it at mScripts
					line = br.readLine(); //This should put it at the first Script
					while ((!line.contains("***DoneScripts***")) &&
							(doneLoops[2] != true)) { //Script loop
						Script newScript = new Script(); //The new scriptwriter to add in
						for (int j = 0; j < lineSeperators.length; j++) {//set all lineseperators to false
							lineSeperators[j] = false;
						}
						for (int j = 0; j < line.length(); j++) {
							if (lineSeperators[0] == false) {//unique ID
								if (line.charAt(j) == '|') {
									newScript.setID(mapValue);
									mapValue = "";
									lineSeperators[0] = true;
								}
								else {
									mapValue = mapValue + line.charAt(j);
								}
							}
							else if(lineSeperators[1] == false) {//File Location
								if (line.charAt(j) == '|') {
									newScript.setFilePath(mapValue);
									mapValue = "";
									lineSeperators[1] = true;
								}
								else {
									mapValue = mapValue + line.charAt(j);
								}
							}
							else if(lineSeperators[2] == false) {//Title
								if (line.charAt(j) == '|') {
									newScript.setTitle(mapValue);;
									mapValue = "";
									lineSeperators[2] = true;
								}
								else {
									mapValue = mapValue + line.charAt(j);
								}
							}
							else if(lineSeperators[3] == false){//Genre
								if (line.charAt(j) == '|') {
									newScript.setGenre(mapValue);;
									mapValue = "";
									lineSeperators[3] = true;
								}
								else {
									mapValue = mapValue + line.charAt(j);
								}
							}
							else if(lineSeperators[4] == false) {//author
								if (line.charAt(j) == '|') {
									newScript.setAuthor(mapValue);;
									mapValue = "";
									lineSeperators[4] = true;
								}
								else {
									mapValue = mapValue + line.charAt(j);
								}
							}
							else if(lineSeperators[5] == false) {//Publishing company
								if (line.charAt(j) == '|') {
									newScript.setPublishingCompany(mapValue);;
									mapValue = "";
									lineSeperators[5] = true;
								}
								else {
									mapValue = mapValue + line.charAt(j);
								}
							}
							else {//End of line character
								
							}
						}
						laScripts.add(newScript);//Put script in map key
						line = br.readLine();
						if (line.equalsIgnoreCase("***DoneScripts***")) {
							doneLoops[2] = true;
						}
					}
					pd1.setScripts(laScripts);//Gives pd1 the scripts
					mapKey = 0;
					mapValue = "";

					for (int j = 0; j < lineSeperators.length; j++) {//set all lineseperators to false
						lineSeperators[j] = false;
					}
					
					line = br.readLine(); //This should put it at mComments
					line = br.readLine(); //This should put it at the first Comment
					
					while (!line.equalsIgnoreCase("***DoneComments***") &&
							(doneLoops[3] != true)) {//Doing comments
						Comment newComment = new Comment();
						foundPerson = false;
						mapValue = "";
						for (int x = 0; x < 6; x++) {
							lineSeperators[x] = false;
						}
						for (int k = 0; k < line.length(); k++) {
							if (lineSeperators[0] == false) {//Unique ID
								if (line.charAt(k) == '|') {
									newComment.setID(mapValue);
									mapValue = "";
									lineSeperators[0] = true;
								}
								else {
									mapValue = mapValue + line.charAt(k);
								}
							}
							else if (lineSeperators[1] == false) {//Author of Comment(Name)
								if (line.charAt(k) == '|') {
									newComment.setAuthor(mapValue);
									mapValue = "";
									lineSeperators[1] = true;
								}
								else {
									mapValue = mapValue + line.charAt(k);
								}
							}
							else if (lineSeperators[2] == false) {//Scriptwriter unique ID
								foundPerson = false;
								if (line.charAt(k) == '|') {
									for (int q = 0; q < pd1.getScriptwriters().size(); q++) {
										if (mapValue.equalsIgnoreCase(pd1.getScriptwriters().
												get(q).getUniqueID())) {
											newComment.setScriptWriter(pd1.getScriptwriters().get(q));
											foundPerson = true;
											break;
										}
									}
									if (foundPerson != true) {
										System.out.printf("Could not find author for comment"
												+ " while reading file: %s%n",
												mapValue);
									}
									mapValue = "";
									lineSeperators[2] = true;
									foundPerson = false;
								}
								else {
									mapValue = mapValue + line.charAt(k);
								}
							}
							else if (lineSeperators[3] == false) {//Script unique ID
								foundPerson = false;
								if (line.charAt(k) == '|') {
									for (int q = 0; q < pd1.getScripts().size(); q++) {
										if (mapValue.equalsIgnoreCase(pd1.getScripts().get(q).getID())) {
											newComment.setScript(pd1.getScripts().get(q));
											newComment.setScriptTitle(pd1.getScripts().get(q).getTitle());
											foundPerson = true;
											break;
										}
									}
									if (foundPerson != true) {
										System.out.printf("Could not find title or script " + 
									"for comment while reading file: %s%n", mapValue);
										for (int j = 0; j < pd1.getScripts().size(); j++) {
											System.out.printf("%s ", pd1.getScripts().get(j).getID());
										}
										System.out.printf("%n");
									}
									mapValue = "";
									lineSeperators[3] = true;
									foundPerson = false;
								}
								else {
									mapValue = mapValue + line.charAt(k);
								}
							}
							else if (lineSeperators[4] == false) {//Line number
								if (line.charAt(k) == '|') {
									newComment.setLine(Integer.parseInt(mapValue));
									mapValue = "";
									lineSeperators[4] = true;
								}
								else {
									mapValue = mapValue + line.charAt(k);
								}
							}
							else if (lineSeperators[5] == false) {//Comment
								if (line.charAt(k) == '|') {
									newComment.setComment(mapValue);
									mapValue = "";
									lineSeperators[5] = true;
								}
								else {
									mapValue = mapValue + line.charAt(k);
								}
							}
							else {
								
							}
						}
						laComments.add(newComment);
						line = br.readLine();
						if (line.equalsIgnoreCase("***DoneComments***")) {
							doneLoops[3] = true;
						}
					}
					pd1.setComments(laComments);
					line = br.readLine();//This should return null, ending the while loop on line 37
				}
			}
			br.close();
			fr.close();
			System.out.println("File loaded successfully!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pd1;
	}

	public static void savePD (String s, ProducerDesk pd) throws IOException {
		String newLine = String.format("%n");
		s = "";
		
		try {
			File f = new File
			("E:\\Coding 2019 Work\\Java Development\\Push Up Console Version\\Project\\Producer.txt");
			FileWriter fw = new FileWriter(f);
			fw.write("**"); //Clears File Entirely
			fw.write(newLine);
			fw.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		try {
			File f = new File
			("E:\\Coding 2019 Work\\Java Development\\Push Up Console Version\\Project\\Producer.txt");
			FileWriter fw = new FileWriter(f, true);
		
			s = "ProducerDesk: " + pd.getID(); //Create PD Line
			fw.write(s);
			fw.write(newLine);
			s = "**"; //Create Asteriks spacing
			fw.write(s);
			fw.write(newLine);
			s = "mUsers"; //Create mUsers spacing
			fw.write(s);
			fw.write(newLine);
			
			//Write Users info from PD
			for (int x = 0; x != pd.getUsers().size(); x++) {
				s = x + ":" + pd.getScriptwriters().get(x).getUsername() + "|";
				fw.write(s);
				fw.write(newLine);
			}
			s = "***DoneUsers***";
			
			fw.write(s); //Write the Done Users Spacing
			fw.write(newLine);
			s = "mScriptwriters";
			fw.write(s); //Writes the mScriptwriters Spacing
			fw.write(newLine);
			//Write Scriptwriters
			for (int q = 0; q != pd.getScriptwriters().size(); q++) {
				s = pd.getScriptwriters().get(q).getUniqueID() + "|" +
						pd.getScriptwriters().get(q).getUsername() + "|" +
						pd.getScriptwriters().get(q).getPassword() + "|" +
						pd.getScriptwriters().get(q).getScriptsWritten() + "|";
				fw.write(s);
				fw.write(newLine);
			}
			s = "***DoneScriptwriters***"; //Write the Done Scriptwriters Spacing
			fw.write(s);
			fw.write(newLine);
			s = "mScripts"; //Write the mScripts Spacing
			fw.write(s);
			fw.write(newLine);
			//Write Scripts
			for (int j = 0; j != pd.getScripts().size(); j++) {
				s = pd.getScripts().get(j).getID() + "|" +
			pd.getScripts().get(j).getFilePath() + "|" +
			pd.getScripts().get(j).getTitle() + "|" +
			pd.getScripts().get(j).getGenre() + "|" +
			pd.getScripts().get(j).getAuthor() + "|" +
			pd.getScripts().get(j).getPublishingCompany() + "|";
				fw.write(s);
				fw.write(newLine);
			}
			s = "***DoneScripts***";
			fw.write(s); //Create the Done Scripts Spacing
			fw.write(newLine);
			s = "mComments"; 
			fw.write(s); //Create the mComments Spacing
			fw.write(newLine);
			//Write Comments
			for (int b = 0; b != pd.getComments().size(); b++) {
				
			s = pd.getComments().get(b).getID() + "|" +
			pd.getComments().get(b).getAuthor() + "|" +
			pd.getComments().get(b).getScriptWriter().getUniqueID() + "|" +
			pd.getComments().get(b).getScript().getID() + "|" +
			pd.getComments().get(b).getLine() + "|" +
			pd.getComments().get(b).getComment()  + "|";
				fw.write(s);
				fw.write(newLine);
			}
			s = "***DoneComments***";
			fw.write(s); //Write the Done Comments Spacing
			fw.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Work saved!");
	}
	
	public static void main(String[] args) throws IOException {
		
		String userNameTest = "Test Username.";
		String passwordTest;
		String uniqueIDTest;
		boolean goodChoice = false;
		boolean signedIn = false; //Is the user signed in?
		boolean exit = false; //Does the user want to exit?
		boolean finalBack = false;
		ProducerDesk producerDesk = new ProducerDesk(); //Producer Desk used to access other classes
		NavigationMenu menu = new NavigationMenu();
		final String alphabet = "123456789ABCDEF"; //Used for random ID Generation
		final int n = alphabet.length(); //Used for random ID generation
		int[] navigator = {0, 0, 0, 0, 0}; //Used to navigate throughout the System.
		Scanner scan = new Scanner(System.in); //Used for user input.
		
		System.out.println("Welcome to the Push Up Console Application.");
		
		producerDesk = loadPD(producerDesk);
	
		do {
			System.out.println("Welcome to Punch Up! What would you like to do?");
			//1 to exit
			//2 to Make Account
			//3 to Sign In
			do {
				try {
					System.out.println("Press 1 to exit the program, 2 to make an account,"+
				" or 3 to sign in!");
					navigator[0] = Integer.parseInt(scan.nextLine());
					while((navigator[0] != 1) && (navigator[0] != 2) && (navigator[0] != 3)) {
						System.out.println("Please enter a 1, 2, or 3.");
						navigator[0] = Integer.parseInt(scan.nextLine());
					}
					goodChoice = true;
				}catch(NumberFormatException e) {
					System.out.println("Invalid input. Please enter a number of 1, 2, or 3.");
				}
			}while(goodChoice == false);
			goodChoice = false;
			
			switch (navigator[0]) {
			case 1:
				System.out.println("Exiting Program. Good bye!");
				exit = true;
				//exit
				break;
			
			case 2:
				System.out.println("Making account!");
				//You are making account.
				while (finalBack != true) {
					finalBack = menu.makeAccount(producerDesk, scan);
					producerDesk = menu.getProducerDesk(); //Created Account, need to add it to PD
				}
				finalBack = false;
				break;
				
			case 3: 
				System.out.println("Signing in...");
				signedIn = false;
				//You are signing in.
				while (finalBack != true) { //Do when not signed in.
					signedIn = menu.signIn(producerDesk, scan, signedIn);
					if (signedIn != true) {
						finalBack = true;
						//Exit to main Menu if not signed in.
					}
					else {
						//Do other menu stuff.
						producerDesk = menu.getProducerDesk(); //Precautionary Producer Update!
						System.out.printf("What would you like to do, %s? %n", menu.getUsername());
						//1. Submit Script 2. Remove Script 3. Look up Script 4. Sign Out
						navigator[1] = 0; //Safetey Initialization.
						do {
							navigator[1] = menu.activities(producerDesk, scan);
							
							switch (navigator[1]) {
							case 1:
								//Submit Script
								System.out.println("Great! Let's submit a script!");
								menu.submitScript(producerDesk, scan);
								producerDesk = menu.getProducerDesk(); //Final Producer Desk Update
								break;
								
								
							case 2:
								//Remove Script
								menu.removeScript(producerDesk, scan);
								producerDesk = menu.getProducerDesk(); //Final Producer Desk Update
								break;
								
							case 3:
								//Look up Script
								menu.lookUpScript(producerDesk, scan);
								producerDesk = menu.getProducerDesk();//Final Producer Desk Update
								break;
								
							case 4:
								//Sign out
								signedIn = menu.signOut();
								producerDesk = menu.getProducerDesk(); //Just in case...maybe not needed.
								finalBack = true; //Needed?
								break;
							default:
								System.out.println("error, invalid input.");
								navigator[1] = 4;
								finalBack = true;
								break;
							}
						}while(navigator[1] != 4);
					}
					
				}
				finalBack = false;
				break;
				
				default:
					System.out.println("Error with choices, exiting app.");
					finalBack = false;
					exit = true;
					break;
			}
			finalBack = false;
				
		}while(exit == false);
		System.out.println("You have exited.");
		
		savePD("Benis", producerDesk);
		
		scan.close();
		
	}
}