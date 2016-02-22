/*
Lucas Brennan

CS0401
Assignment 2
Player class for Assignment 2
*/
	//Import IO and UTIL classes
	import java.io.*;
	import java.util.*;

public class Player {	//Begin Player class

	//Declare object fields
	public String name;
	private double money;
	private int numRoundsPlayed;
	private int numRoundsWon;



	//Player class constructor, sets fields to 0
	public void Player() {


		money = 100.0;
		numRoundsPlayed = 0;
		numRoundsWon = 0;

	}


	//FirstName setter method
	public void setName(String name) {

		this.name = name;
	}

	//FirstName getter method
	public String getName() {

		String temp = name;

		return temp;
	}

	//money setter method
	public void setMoney(double money) {

		this.money = money;
	}

	//CashBalance getter method
	public double getMoney() {

		double temp = money;

		return temp;
	}

	//NumRoundsPlayed setter method
	public void setNumRoundsPlayed(int numRoundsPlayed) {

		this.numRoundsPlayed = numRoundsPlayed;
	}

	//NumRoundsPlayed getter method
	public int getNumRoundsPlayed() {

		int temp = numRoundsPlayed;

		return temp;
	}

	//NumRoundsWon setter method
	public void setNumRoundsWon(int numRoundsWon) {

		this.numRoundsWon = numRoundsWon;
	}

	//NumRoundsWon getter method
	public int getNumRoundsWon() {

		int temp = numRoundsWon;

		return temp;
	}
}
