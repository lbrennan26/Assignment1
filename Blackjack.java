/*
Lucas Brennan
*/

	//Import Scanner and Random Classes
	import java.io.*;
	import java.util.*;


public class BlackJack { //Begin Assig Class

	public static void main(String[] args) throws IOException {	//Begin Main Method

		//Create Scanner Object
		Scanner sc = new Scanner(System.in);

		//Declare variables
		double betAmount = 0.00, totalGameWinnings = 0.00;
		int numRoundsWonGame = 0, numRoundsPlayedGame = 0;
		char roundChoice = 'O', choice = 'X' , again = 'P';


		//Create Player Object; Prompt user for first name, read in
		Player player = new Player();
		System.out.println("Please enter your name");
		player.setName(sc.nextLine());

		//Create File Object,
		File fileName = new File(player.getName() + ".txt");
		String playerFile = fileName.toString();

		//Check if file exists, if so open and read in data to player object fields
		if (fileName.exists()) {
			//Create file scanner object
			Scanner fs = new Scanner(fileName);
			player.setName(fs.nextLine());
			player.setMoney(fs.nextDouble());
			player.setNumRoundsPlayed(fs.nextInt());
			player.setNumRoundsWon(fs.nextInt());


			fs.close();

			//Welcome back message, call method to display current player object info
			System.out.println("Welcome back, " + player.getName() +"!");
			System.out.println("Welcome to BlackJack!");
			displayCurrentInfo(player.getName(), player.getMoney(), player.getNumRoundsPlayed(), player.getNumRoundsWon());
		}
		//File does not exist, creat one and set player object values accordingly
		else {
			//Prompt user for information to populate player object fields
			System.out.println("Welcome New Player!");
			player.setMoney(100.0);
			writeFile(playerFile, player.getName(), player.getMoney(), player.getNumRoundsPlayed(), player.getNumRoundsWon());
			System.out.println("Welcome to Blackjack!");
			displayCurrentInfo(player.getName(), player.getMoney(), player.getNumRoundsPlayed(), player.getNumRoundsWon());
		}
			do{
		//Prompts user to play a round or quit, reads input, checks for error
		while (roundChoice != 'Y' && roundChoice != 'N') {
			System.out.print("Would you like to play a hand? (Type Y for Yes, Type N for No.) > ");
			roundChoice = sc.next().charAt(0);
			roundChoice = Character.toUpperCase(roundChoice);
		}

		//If choice yes was made, proceed to gamplay
		while (roundChoice == 'Y') {
			//Prompt user for bet, check for error
			do {
				System.out.printf("How much would you like to bet? (<= $%.2f) > ", player.getMoney());
				betAmount = sc.nextDouble();
			} while (betAmount > player.getMoney() || betAmount < 0);



			Card card = new Card();
			Card card1 = new Card();
			card.randomValue();
			card1.randomValue();
			card.suitValue();
			card1.suitValue();
			System.out.print(card.toString());
			System.out.println(card1.toString());
			int total = card.getPoints() + card1.getPoints();
			System.out.println(total);

			if(total == 21)
			{
				System.out.println("BlackJack!");
				System.out.println("You have WON this round!");
				System.out.printf("You have won $%.2f!\n", betAmount * 1.5);
				//Apply changes to player class fields
				player.setMoney(player.getMoney() + (betAmount *1.5));
				System.out.printf("Your updated money value is: $%.2f\n", player.getMoney());
				//Apply round wins and rounds played to variables
				totalGameWinnings = totalGameWinnings + betAmount;
				numRoundsPlayedGame = numRoundsPlayedGame + 1;
				numRoundsWonGame = numRoundsWonGame + 1;
				player.setNumRoundsPlayed(player.getNumRoundsPlayed() + 1);
				player.setNumRoundsWon(player.getNumRoundsWon() + 1);
			}

do{

do {
	System.out.print("Select your choice: [H]it or [S]tay");
	choice = sc.next().charAt(0);
	choice = Character.toUpperCase(choice);
} while (choice != 'H' && choice != 'S');


	Card card3 = new Card();
	card3.randomValue();
	card3.suitValue();
	System.out.println(card3.toString());
	total = card3.getPoints() + total;
	System.out.println(total);


}while(choice == 'H');

if (total > 21)
{
	System.out.println("Player Busted!");

	System.out.println("You have LOST this round!");
	System.out.printf("You have lost $%.2f!\n", betAmount);
	//Apply changes to player class fields
	player.setMoney(player.getMoney() - betAmount);
	System.out.printf("Your updated money value is: $%.2f\n", player.getMoney());
	//Apply round wins and rounds played to variables
	totalGameWinnings = totalGameWinnings - betAmount;
	numRoundsPlayedGame = numRoundsPlayedGame + 1;
	player.setNumRoundsPlayed(player.getNumRoundsPlayed() + 1);

	break;

}
else
{


//simulate the dealer

Card dealer = new Card();
Card dealer1 = new Card();
dealer.randomValue();
dealer1.randomValue();
dealer.suitValue();
dealer1.suitValue();
int dealerTotal = dealer.getPoints() + dealer1.getPoints();


if (dealerTotal == 21)
{
	System.out.println("The dealer got Blackjack! You lose!");
	System.out.printf("You have lost $%.2f!\n", betAmount);
	//Apply changes to player class fields
	player.setMoney(player.getMoney() - betAmount);
	System.out.printf("Your updated money value is: $%.2f\n", player.getMoney());
	//Apply round wins and rounds played to variables
	totalGameWinnings = totalGameWinnings - betAmount;
	numRoundsPlayedGame = numRoundsPlayedGame + 1;
	player.setNumRoundsPlayed(player.getNumRoundsPlayed() + 1);


}



while(dealerTotal < 18)
{
	Card dealer3 = new Card();
	dealer3.randomValue();
	dealer3.suitValue();
	dealerTotal = dealer3.getPoints() + dealerTotal;
}


if(dealerTotal == total)
{
	System.out.println("Push");
	System.out.println("You dont lose or win! You get your money back!");
	numRoundsPlayedGame = numRoundsPlayedGame + 1;
	player.setNumRoundsPlayed(player.getNumRoundsPlayed() + 1);

}
else if (dealerTotal < total)
{
	System.out.println("You win the dealer had a worse score than you!");
	System.out.println("You have WON this round!");
	System.out.printf("You have won $%.2f!\n", betAmount);
	//Apply changes to player class fields
	player.setMoney(player.getMoney() + (betAmount));
	System.out.printf("Your updated money value is: $%.2f\n", player.getMoney());
	//Apply round wins and rounds played to variables
	totalGameWinnings = totalGameWinnings + betAmount;
	numRoundsPlayedGame = numRoundsPlayedGame + 1;
	numRoundsWonGame = numRoundsWonGame + 1;
	player.setNumRoundsPlayed(player.getNumRoundsPlayed() + 1);
	player.setNumRoundsWon(player.getNumRoundsWon() + 1);
}
else if (dealerTotal > total && dealerTotal <= 21)
{
	System.out.println("You LOST the dealers score was: " + dealerTotal);
	System.out.printf("You have lost $%.2f!\n", betAmount);
	//Apply changes to player class fields
	player.setMoney(player.getMoney() - betAmount);
	System.out.printf("Your updated money value is: $%.2f\n", player.getMoney());
	//Apply round wins and rounds played to variables
	totalGameWinnings = totalGameWinnings - betAmount;
	numRoundsPlayedGame = numRoundsPlayedGame + 1;
	player.setNumRoundsPlayed(player.getNumRoundsPlayed() + 1);
}
else
{
	System.out.println("You win the dealer busted! His score was: " + dealerTotal);
	System.out.println("You have WON this round!");
	System.out.printf("You have won $%.2f!\n", betAmount);
	//Apply changes to player class fields
	player.setMoney(player.getMoney() + (betAmount));
	System.out.printf("Your updated money value is: $%.2f\n", player.getMoney());
	//Apply round wins and rounds played to variables
	totalGameWinnings = totalGameWinnings + betAmount;
	numRoundsPlayedGame = numRoundsPlayedGame + 1;
	numRoundsWonGame = numRoundsWonGame + 1;
	player.setNumRoundsPlayed(player.getNumRoundsPlayed() + 1);
	player.setNumRoundsWon(player.getNumRoundsWon() + 1);
}

displayCurrentInfo(player.getName(), player.getMoney(), player.getNumRoundsPlayed(), player.getNumRoundsWon());

do {
	System.out.print("Would you like to play again? (Type Y for Yes, Type N for No.) > ");
  again = sc.next().charAt(0);
	again = Character.toUpperCase(roundChoice);
}while(again != 'Y' && again !='N');


}


}

}while(again == 'Y');


}

	//Diplay Current Info Method
	public static void displayCurrentInfo(String name, double money, int numRoundsPlayed, int numRoundsWon) {

		//Prints player object fields to screen
		System.out.println("Here is your current information:");
		System.out.println("	Name: " + name);
		System.out.printf("	Current Cash Balance: $%.2f\n", money);
		System.out.println("	Total Rounds Played: " + numRoundsPlayed);
		System.out.println("	Total Rounds Won: " + numRoundsWon);
		System.out.println("");
	}



	//Write Player info to file Method
	public static void writeFile(String playerFile, String name,  double money, int numRoundsPlayed, int numRoundsWon) throws IOException
		{

		//Create pritewriter object
		PrintWriter out = new PrintWriter(playerFile);

		//Write player object fields to .txt file

		out.println(name);
		out.println(money);
		out.println(numRoundsPlayed);
		out.println(numRoundsWon);
		out.close();
    }
}
