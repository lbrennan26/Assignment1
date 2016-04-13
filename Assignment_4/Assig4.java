/*
Lucas Brennan
CS 401
Professor Laboon
Assignment 4
Monday and Wednesday 3-4:15
*/

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.*;				//import statements
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultEditorKit.CopyAction;


public class Assig4 {

	//height and width for windows
	private static final int HEIGHT = 500;
	private static final int WIDTH = 850;
	public static int numBallots;
	//Layouts
	GridBagLayout gridBag = new GridBagLayout();
	//default layout of main window and JPanel for right side of main window
	JPanel loginPanel = new JPanel();
	JLabel blank = new JLabel(""); //blank JLabel to space login GridLayout
	GridLayout loginPane = new GridLayout(4,1, 50,50);
	//Define button characteristics and an ok button which we will probably use multiple times
	private Font buttonFont = new Font("times new roman", Font.BOLD, 20);
	//Create ArrayList of type Ballot
	public static ArrayList<Ballot> Election = new ArrayList<Ballot>();
	//Make cast vote and login buttons
	public static JButton castBtn = new JButton("Cast Your Votes!");
	public static JButton loginBtn = new JButton("Login");
	//MAIN JFRAME
	public static JFrame theWindow = new JFrame("Voting Simulator");
	{
		theWindow.setLocationRelativeTo(null);
	}


	public static void main(String [] args) throws IOException, InterruptedException {

		//Read in file from args argument 0 (file from the command line)
		if(args[0]==null) {
			System.out.println("File not found, I will exit now");
			 System.exit(0);																//if no file is put in exit the program
		}
		String filename = args[0];    // set the file name
		File ballot = new File(filename); // create a reference to the file

		new Assig4(ballot); // pass it into the constructor
	}


	public Assig4 (File ballot) throws IOException, InterruptedException {
		//Take first line of text file, to tell how many ballots we need to create

		Scanner inFile= new Scanner(ballot);
		String temp=inFile.nextLine();
		numBallots=(Integer.parseInt(temp));


		for(int i=0; i<numBallots; i++){

			//Split by : to get id and name
			//Split by , to get candidates
			String str1=inFile.nextLine();
			String[] idName = str1.split(":");
			String[] candidates = idName[2].split(",");

			//Rename to not get confused
			String BallotName = idName[1];				//this is the name of the ballot
			String idNumber = idName[0];				//this is the ID number of ballot
			int numCandid = candidates.length;				//how many candidates there are
			Ballot ballot1 = new Ballot(idNumber, BallotName, candidates, numCandid);

			Election.add(ballot1);
		}
		//FROM HERE, USE GETTERS IF WE NEED TO ACCESS BALLOT OBJECTS

		//Splash window
		JOptionPane.showMessageDialog(null, "Hello! Please begin by signing in!");


		//CREATE MAIN WINDOW
		GridLayout windowLayout = new GridLayout(1,numBallots,100,100);
		JLabel welcome = new JLabel("Welcome! Please Login to Begin");
		theWindow.setLayout(windowLayout);
		theWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		theWindow.setSize(WIDTH, HEIGHT);
		theWindow.setForeground(Color.BLUE);

		theWindow.setVisible(true);

		for(int i=0; i<numBallots; i++) {
			theWindow.add(Election.get(i));
			theWindow.setVisible(true);
		}


		//Make login panel
		loginPanel.setLayout(loginPane);
		theWindow.add(loginPanel);



				loginBtn.addActionListener(new ActionListener(){

					//LOGIN BUTTON IS CLICKED
					public void actionPerformed(ActionEvent arg0) {
					try {
							new Voter();
							theWindow.setVisible(true);
							loginBtn.setEnabled(false);
							castBtn.setEnabled(true);
						} catch (Exception e) {
							e.printStackTrace();			//if we get an Exception print out the trace
						}
					}
				});



		CastListener CListener = new CastListener(); //listener for "cast your vote" button
		 //listener for new profile
		loginBtn.setFont(buttonFont);
		castBtn.setFont(buttonFont);
		castBtn.setEnabled(false);
		//blank filler to make grid layout look nice
		loginPanel.add(loginBtn);
		loginPanel.add(castBtn);
		castBtn.addActionListener(CListener);
		theWindow.setVisible(true);

	}
	public static void refreshMain() {
		theWindow.setVisible(true);    //method to refresh the window
	}
	public JLabel makeJLabel(String textToMake) {

		JLabel newText = new JLabel(textToMake);			//methd to make a JLabel
		return newText;
	}

}




	class CastListener implements ActionListener {				//ActionListener for cast vote button
		public void actionPerformed(ActionEvent e) {
			String votedFor = "";
			for(Ballot aBallot:Assig4.Election) { //for each loop to get the answer choices
				votedFor+=aBallot.getBallotName() + ":" + aBallot.getAnswerChoice() + "\n\n";
			}
			String votedFor2 = "You Voted for:\n" + votedFor;
			int result=JOptionPane.showConfirmDialog(null, votedFor2+ "Are you sure you want to cast your vote(s)?", "", JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION) {
				try {
					writeResults();				//write the results to the file
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else {

			}

		}
		public static void writeResults() throws IOException {  //method to write to file

		boolean exists = doesFileExist(); //first check if file exists

			if(!exists) // if there is no file
			{

			for(int i=0; i<Assig4.numBallots; i++) {
			//Get Ballot Information
				Ballot tempBallot = Assig4.Election.get(i);
				String ballotID = tempBallot.getBallotId();
				String voteChoice = tempBallot.getAnswerChoice();			//create the file
//Define file outputs
				String desiredFilename = ballotID + ".txt";
			//Write to tempfile

			PrintWriter outtie= new PrintWriter("temp.txt");
			//loop for writing OUT


		  int [] quantities = new int [tempBallot.getCandidates().length];

			for(int j = 0; j<quantities.length;j++)
			{
				if (voteChoice.equals(tempBallot.getCandidates()[j]))		//get the correct number of votes for each candidate
					{
						quantities[j]++;
					}
			}



			for(int j=0; j< quantities.length;j++){

				String aString = tempBallot.getCandidates()[j]+ ":" + quantities[j];  	//write the intial votes to it
				outtie.println(aString);
			}

			File ballotFile = new File(desiredFilename);
			File tempFile = new File("temp.txt");								//write this all to the temp file and then copy it to the actual file
			outtie.close();
			Files.copy(tempFile.toPath(), ballotFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			//END FILE I/O
		postVote();
		}

	}
		else			//if the file is alrready there
		{
			for(int i=0; i<Assig4.numBallots; i++) {

		//Get Ballot Information
			Ballot tempBallot = Assig4.Election.get(i);
			String ballotID = tempBallot.getBallotId();
			String voteChoice = tempBallot.getAnswerChoice();
		//Define file outputs
			String desiredFilename = ballotID + ".txt";
			File ballotFile = new File(desiredFilename);
			File tempFile = new File("temp.txt");
		//Write to tempfile, read from old file
			Scanner fileLook = new Scanner(ballotFile);
			int [] quantities = new int [tempBallot.getCandidates().length];

		String str1="";
		//read from old, write to new

		//loop for reading IN
		for(int j=0; j<quantities.length;j++) {
			str1=fileLook.nextLine();
			String [] strings = str1.split(":");											//read in all data from previous file
			quantities[j]=Integer.parseInt(strings[1]);
			String currentChoice = strings[0];
			if (currentChoice.equals(voteChoice)) {
				quantities[j]++;
			}
		}
		fileLook.close();			//close the scanner for reading in


		PrintWriter outtie= new PrintWriter(tempFile);							//write to temp
		//loop for writing OUT
		for(int j=0; j<quantities.length;j++){

			String aString = tempBallot.getCandidates()[j]+ ":" + quantities[j];
			outtie.println(aString);
		}
		outtie.close();
		Files.copy(tempFile.toPath(), ballotFile.toPath(), StandardCopyOption.REPLACE_EXISTING);  //copy to actual file


	postVote();
	}
	JOptionPane.showMessageDialog(null, "Thanks for Voting!");   // Let them know the process is done


		}
	}


		//make it so voter can't vote anymore
		public static void postVote()throws IOException {   //call this method after the voting is done
		//disable buttons
		Assig4.castBtn.setEnabled(false);
		Assig4.loginBtn.setEnabled(true);
		for(Ballot aBallot:Assig4.Election) {
			aBallot.enableButtons(false);
		}
		Assig4.theWindow.setVisible(true);
		//write to voter file
		File f = new File("temptemp.txt");
		PrintWriter pw = new PrintWriter(new FileOutputStream(f, false));
		ArrayList<String> thevoters = Voter.voters; //copy arrayList from voter class
		for (int i =0; i<(thevoters.size()/3); i++) {
			//write to temptemp file
			if(Voter.voterId.trim().equals(thevoters.get(3*i).trim())) {
				thevoters.set(3*i+2, "true");
			}
			pw.println(thevoters.get(3*i)+":"+thevoters.get(3*i+1)+":"+thevoters.get(3*i+2));
		}
		Voter.voterId="";
		pw.close();
		File a = new File("voters.txt");
		Files.copy(f.toPath(), a.toPath(), StandardCopyOption.REPLACE_EXISTING);  //overwrite existing voters file
		}


		public static boolean doesFileExist() //checks if the ballot results files exist
		{
			boolean exists = false;
			Ballot tempBallot = Assig4.Election.get(0);
			String ballotID = tempBallot.getBallotId();
			String desiredFilename = ballotID + ".txt";
			File f = new File(desiredFilename);
			if(f.exists())
			{
				exists = true;
			}
			else
			{

			}
			return exists;

		}
	}
