import java.util.Scanner;

public class Question
{
    //PRODUCE A SINGLE QUIZ QUESTION


    //instance variables
    private int input;
    private String question;
    private String[] answers;
    private int correctAnswer;
    private int numTries;
    private int numCorrect;
    private double percent;

    //contructor that accepts all data from file

    public Question(String question, String[] answers, int correctAnswer ,int numTries, int numCorrect)
    {
      this.question = question;
      this.answers = answers;
      this.correctAnswer = correctAnswer;
      this.numTries = numTries;
      this.numCorrect = numCorrect;
      this.percent = percent;
    }


    // ask a single question
    public int askQuestion(int k) // accepts k to know what question it is
    {
      int input; // will hold user input
      System.out.println("Question " + k + ":"); // print out what number question they are on
      Scanner sc = new Scanner(System.in); // new scanner
      System.out.println(question); //print the question
      System.out.println("Answers:"); // print wach answer using a for loop to iterate through the array of strings
      for(int j = 0; j < answers.length; j++)
      {
        System.out.println(j + ": " + answers[j]);
      }
          do
          {
            System.out.println("Your answer? > ");
             input = sc.nextInt();
          }while(input < 0 || input > (answers.length-1)); // make sure they enter a valid choice

          return input; // return the users input


    }
    public void setInput(int input) //mutator method for input
    {
      this.input = input;
    }

    public int getInput() // accessor method for input
    {
      return input;
    }

    public boolean showAnswer(int input) // show the results of each question
    {

      boolean correct = false; //determine if they were right or not start it at false
      System.out.println("Question: " + question);
      System.out.println("Answer: " + answers[correctAnswer]);
      System.out.println("Player guess : " +  answers[input]);
      if(correctAnswer == input)
      {
        System.out.println("            Result: CORRECT! Great work!");
        correct = true;
      }
      else
      {
        System.out.println("            Result: INCORRECT! Remeber the answer for next time!");
      }
      return correct; //return boolean to determine if they answered correctly

    }
    public void setNumTries(int numTries) //mutator method for number of tries
    {
      this.numTries = numTries;
    }
    public void setNumCorrect(int numCorrect) //mutator method for number of times it is correctly answered
    {
      this.numCorrect = numCorrect;
    }
    public int getNumTries() // accessor for number of tries
    {
      return numTries;
    }
    public int getNumCorrect() //accessor for number of times correctly answered
    {
      return numCorrect;
    }
    public double getPercent() // accesor for the percent right
    {
      double pct;
      pct = ((double) getNumCorrect()/ (double) getNumTries())*100;
      return pct;
    }
    public String getQuestion() // accesor for question
    {
      return question;
    }
    public String[] getAnswers() //accesor for answers
    {
      return answers;
    }
    public int getCorrectAnswer() //accesor for correct answer
    {
      return correctAnswer;
    }


    public void displayStatistics() //display the statistics for a question
    {
      System.out.println("Question: " + question);
      System.out.println("    Times Tried: " + getNumTries());
      System.out.println("    Times Correct: " + getNumCorrect());
      System.out.printf("    Percent Correct: %.2f",getPercent()); //rounds the percent to two decimal places the numbers start to get ugly
      System.out.println("");

    }
}
