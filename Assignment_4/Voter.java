/*
Lucas Brennan
CS 401
Professor Laboon
Assignment 4
Monday and Wednesday 3-4:15
*/

import java.util.*;
import java.awt.*;
import java.awt.event.*;			//import statements
import java.io.*;
import javax.*;
import javax.swing.*;
import javax.swing.event.*;

public class Voter {   //voter class

	public static String voterId;
	private String voterName;							//attributes needed for the voters 				
	private boolean hasVoted;
	//set some buttons we will need
	JButton okButton = new JButton("Ok");
	JButton cancelButton = new JButton("Cancel");
	public static ArrayList<String> voters = new ArrayList<String>();	//Arraylist of voters

	public Voter() throws FileNotFoundException  {
		String voterFile="voters.txt";
		File votersFile = new File(voterFile);				//get the voter file
		Scanner voterIn = new Scanner(votersFile);

	//read in all voter information
		//add to string arraylist of voters
		while(voterIn.hasNext()) {
			String str1=voterIn.nextLine();
			String[]voter = str1.split(":");
			for(int i=0; i < voter.length; i++) {
				voters.add(voter[i]);
			}
		}
		voterIn.close();

		//Enter Voter ID then analyze
		boolean exists = true;
		while(exists) {

			voterId=JOptionPane.showInputDialog("Enter your voter id: ");

		if(voterId!=null) {
			boolean voterExists=checkVoter(voters, voterId); //check if voter exists by id
			if(voterExists==true) {//if they exist, get their name, then see if they have voted
				String VoterName=getChaName(voters, voterId);//get name
				boolean hasVoted=false;
				if(voterExists==true){
					hasVoted=hasVoted(voters, voterId); //check if they've voted before
				}
				if(hasVoted==true){//case 1: have voted before (NOT ELIGIBLE TO VOTE)
					JOptionPane.showMessageDialog(null, VoterName+", You have already voted, nice try.");
					exists=true;
				}else if(hasVoted==false) {//case 2:haven't voted (ELIGIBLE TO VOTE)
					JOptionPane.showMessageDialog(null, VoterName+", please pick your choices.");
					for(Ballot aBallot : Assig4.Election) {
					aBallot.enableButtons(true);
					aBallot.setVisible(true);
					exists=false;
					}
				}
			}
		} else{
			JOptionPane.showMessageDialog(null, "Invalid voter ID");   //if they type in an invalid voter id
			exists=true;
			}
		}
	}//end voter constructor




//scans to see if voter exists
	public boolean checkVoter(ArrayList<String> Voters, String UserId) {
		boolean exists = false;

		for(int i=0; i<Voters.size(); i=i+3) {
			if(Integer.parseInt(Voters.get(i))==Integer.parseInt(UserId)) {
				exists=true;
				break;
			}else {
				exists=false;
			}

		}
		return exists;
	}

//fetches name associated with id number
	public String getChaName(ArrayList<String> Voters, String UserId) {
		String name="";
		for(int i=0; i<Voters.size(); i=i+3) {
			if(Integer.parseInt(Voters.get(i))==Integer.parseInt(UserId)) {
				name=Voters.get(i+1);
			}
		}
		return name;
	}

//scans to see if person has voted
	public boolean hasVoted(ArrayList<String> Voters, String UserId) {
		boolean hasVoted = false;
		for(int i=0; i<Voters.size(); i=i+3) {
			if(Integer.parseInt(Voters.get(i))==Integer.parseInt(UserId)) {
				String voteStatus=Voters.get(i+2);
				hasVoted = Boolean.parseBoolean(voteStatus);
			}
		}
		return hasVoted;

	}


	public JLabel makeJLabel(String textToMake) {

		JLabel newText = new JLabel(textToMake);
		return newText;																//method to make a JLabel
	}


	//VARIOUS GETTERS AND SETTERS
	public static String getVoterId() {
		return voterId;
	}





	public void setVoterId(String VoterId) {
		this.voterId = voterId;
	}





	public boolean isHasVoted() {
		return hasVoted;
	}





	public void setHasVoted(boolean hasVoted) {
		this.hasVoted = hasVoted;
	}
}
