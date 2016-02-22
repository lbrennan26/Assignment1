import java.util.Random;

public class Card {
  private String suit; // declare fields
  private String value;
  private int points;
  int numAces = 0;

  Random rand = new Random();

  public void Card()
  {
    this.value = value;
    this.suit = suit;             //constructor
    this.points = points;

  }
  public void randomValue() //method to get the random value of the card
  {

    points = rand.nextInt(10) + 1;

    if(points == 1)
    {
      value = "A";
      numAces++;
    }
    else if(points == 10)
    {
      String[] tens = {"T","J","Q","K"};
      int t = rand.nextInt(4);

      value = tens[t];
    }
    else if(points == 2)
    {
      value = "2";
    }
    else if(points == 3)
    {
      value = "3";
    }
    else if(points == 4)
    {
      value = "4";
    }
    else if(points == 5)
    {
      value = "5";
    }
    else if(points == 6)
    {
      value = "6";
    }
    else if(points == 7)
    {
      value = "7";
    }
    else if(points == 8)
    {
      value = "8";
    }
    else
    {
      value = "9";
    }

  }
    public void suitValue() // method to get the suit
    {
      String[] suits = {"c","d","h","s"};
      int s = rand.nextInt(4);
      suit = suits[s];
    }
    public String toString() // to string to print out the card
    {
      return value + suit;
    }
    public int getPoints() //aces 
    {
        if(points == 1)
        {
         points = 11;
        }
        else
        {
          points = points;
        }
        return points;
    }
  }
