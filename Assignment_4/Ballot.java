/*
Lucas Brennan
CS 401
Professor Laboon
Assignment 4
Monday and Wednesday 3-4:15
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Ballot extends JPanel {
	//fields needed
	private String ballotId;
	private String ballotName;
	private String [] candidates;
	private Font buttonFont = new Font("times new roman", Font.BOLD, 20);
	private Font borderFont = new Font("Arial", Font.PLAIN, 25);
	private Color borderColor = Color.RED;
	private Dimension buttonSize = new Dimension(15,10);
	private String answerChoice;



		public Ballot(String BallotId, String BallotName, String [] Candidates, int numCandid) throws IOException {  //constructor for ballots

			//Sets characteristics of ballot
			setBallotId(BallotId);
			setBallotName(BallotName);
			setCandidates(new String [numCandid]);
			for(int i=0; i<Candidates.length; i++) {
				getCandidates()[i]=Candidates[i];
			}

			//Start defining characteristics of how ballot appears in panel form
			GridLayout gridLock = new GridLayout(numCandid, 1, 50, 50);
			setLayout(gridLock);
			Dimension size = getPreferredSize();
			size.width = 750;
			setPreferredSize(size);

			//SET BORDER AROUND VOTING CHOICE TITLE
			String titleText = ballotName;
			TitledBorder titledBorder = BorderFactory.createTitledBorder(null, titleText, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, borderFont, borderColor);
			setBorder(titledBorder);

			//Add buttons for candidates onto ballot panel
			for(int i=0; i<getCandidates().length; i++) {
				String buttonName="";
				buttonName=getCandidates()[i];
				JButton candid = new JButton(buttonName);
				candid.setEnabled(false);
				candid.setFont(buttonFont);
				add(candid);
			}

		}


		public void enableButtons(boolean choice) {
			removeAll();
			Dimension size = getPreferredSize();
			size.width = 750;
			setPreferredSize(size);
			//SET BORDER AROUND VOTING CHOICE TITLE
			String titleText = ballotName;
			TitledBorder titledBorder = BorderFactory.createTitledBorder(null, titleText, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, borderFont, borderColor);
			setBorder(titledBorder);
			setVisible(true);
			//Add buttons for candidates onto ballot panel
			ButtonListener BListener = new ButtonListener();
			for(int i=0; i<getCandidates().length; i++) {
				String buttonName="";
				buttonName=getCandidates()[i];
				JButton candid = new JButton(buttonName);
				candid.setEnabled(choice);
				candid.setFont(buttonFont);
				candid.addActionListener(BListener);
				add(candid);


			}

			setVisible(true);
		}

		public boolean hasElement (String candidate) {
			for (String option: getCandidates()) {					//CHECKS IF THE BUTTON HAS AN ELEMENT
				if(option.equals(candidate)){
					return true;
				}
			}
			return false;
		}
		// SETTERS AND GETTERS FOR BALLOT ATTRIBUTES
		public String getBallotId() {
			return ballotId;
		}
		public void setBallotId(String ballotId) {
			this.ballotId = ballotId;
		}
		public String getBallotName() {
			return ballotName;
		}
		public void setBallotName(String ballotName) {
			this.ballotName = ballotName;
		}
		public int returnCandidSize(){
			int size=getCandidates().length;
			return size;
		}
		public void setAnswerChoice(String vote) {
			answerChoice = vote;
		}
		public String getAnswerChoice(){
			return answerChoice;
		}


		public String [] getCandidates() {
			return candidates;
		}


		public void setCandidates(String [] candidates) {
			this.candidates = candidates;
		}


}

class ButtonListener implements ActionListener {
		 boolean isRed = false;												//tracks if the button is red or black
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();			//action listener for choice buttons
		String vote = source.getText();
		if(isRed)
		{
			source.setForeground(Color.BLACK);				//make buttons go back and forth between red and black
			isRed = false;
		}
		else
		{
			source.setForeground(Color.RED);
			isRed = true;
		}
		for (Ballot aBallot: Assig4.Election) {
			if(aBallot.hasElement(vote)) {							//set the answer choice to the last thing they click
				aBallot.setAnswerChoice(vote);
			}
		}

	}

}
