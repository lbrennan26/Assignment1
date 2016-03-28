import java.io.*;
import java.util.*;        //necessary import statements

public class Quiz
{
  public static void main(String[] args) throws IOException
  {
    boolean correct = false; //determines if the user was correct or not
    double numRight = 0.0; //number of ququestions right
    double numWrong = 0.0; // number of questions wrong
    int k = 0; //simple increment variable to print out what number question the user is on
    int input; // to hold user input
    Scanner sc = new Scanner(System.in); // create scanner
    File f = getFile(args); //call get file method to acceot the file as an argument and store it as f
    ArrayList<Question> questions = makeQlist(f); // make the arraylist of type Question based on data drom file
    int size = questions.size(); // to hold the amount of questions
    double[] pcts = new double[size]; //an array for use later when we need to get hardest and easiest question

    for (Question q : questions)    // first for each loop that asks the user the questoion and sets the input
      {

        q.setInput(q.askQuestion(k)); // ask the question and get input
        k++;

      }
      System.out.println("Thank you for your answers!");
      System.out.println("Here are your results:");

    for (Question q : questions) // second for each loop that shows the results
      {


          correct = q.showAnswer(q.getInput()); //shows answer
          System.out.println("");
          if(correct)
          {
            q.setNumCorrect(q.getNumCorrect()+1); //if they got it right update the resepctive fields
            q.setNumTries(q.getNumTries()+1);
            numRight++; // to count how many they got right
          }
          else
          {
            numWrong++; // to count how many they got wrong
            q.setNumTries(q.getNumTries()+1); // update fields
          }

      }

      double pct = (numRight / size) * 100; // get percentage of questions they got right
      System.out.println("Your overall performance was:");
      System.out.println("Right: " + numRight);
      System.out.println("Wrong: " + numWrong);
      System.out.println("Pct: " + pct);

      System.out.println("");
      System.out.println("Here are some cummalitive statistics:");

      for(Question q : questions) // another for each loop to display all the statistics
      {
        q.displayStatistics(); // call the method to display te statistics
      }

      int o = 0; // be able to access the array of doubles for each question

      for(Question q : questions)
      {

        pcts[o] = q.getPercent();     //get percent right and add it to an array so we can sort it
        o++;
      }
      SelectionSort(pcts); //sort the array so we can get the highest percentage right at the first spot in the array and vice versa
      double easiest = pcts[0]; // set easiest to first spot in the array b/c that will be highest percentage right
      double hardest = pcts[size-1]; // set hardest to last because that will be lowest percentage right
      for (Question q  : questions)   //yet another for each loop to see which question corresponds to each percentage
      {
        if (q.getPercent() == hardest) // if percentage of this question matches the lowest percentage print it out as hardest question
        {
          System.out.println("Hardest question:");
          q.displayStatistics(); // print out the question and its statistics
        }
        else if(q.getPercent() == easiest) // if percent of this question matches the highest percentage print it out as easiest question
        {
          System.out.println("Easiest Question:");
          q.displayStatistics(); //print out question and its statistics
        }
        else // if neither of those are true its neither the hardest nor easiest question so do nothing
        {

        }
      }

      PrintWriter out = new PrintWriter(f); // create one PrintWriter in order to write to the file

      for(Question q : questions) // another for each loop to write all the data to the file
      {

        //call the writeFile method and pass in all the fields for each question object

        writeFile(out,f,q.getQuestion(),q.getAnswers(),q.getCorrectAnswer(),q.getNumTries(),q.getNumCorrect());
      }

      out.close(); // close the file






  }

  public static File getFile(String[] args) // method to get the file as an argument
  {
    String fileName; // create a string to hold the name of the file
    if(args.length == 0) // if they dont enter in a file close the program
    {
      System.exit(0);
    }
      fileName = args[0];  // set the file to what they enthered in the command line
      File file = new File(fileName); // create file object with file name user entered

    return file; // return the file object

  }

  //Method to create an arraylist an add all the necessary question data

  private static ArrayList<Question> makeQlist(File f) throws IOException
   {

    String question;
    int correctAnswer;
    int numTries;
    int numCorrect;
    Scanner fs = new Scanner(f);
    ArrayList<Question> que = new ArrayList<Question>(); // create the array list we will return
    do
    {
        question = fs.nextLine(); // get the question
        int i = fs.nextInt(); // get the amount of answers so we can loop through
        fs.nextLine(); // clear whitespace
        String[] answers = new String[i]; // create an array of strings to hold the answers

        for (int j = 0; j<answers.length;j++) //get each answer and add it to the array of strings
        {
          answers[j] = fs.nextLine();
        }
        correctAnswer = fs.nextInt(); // get correct answer
        numTries = fs.nextInt(); // get number of tries
        numCorrect = fs.nextInt(); // get number of times it was answered correctly
        fs.nextLine(); // clear white space again

        que.add(new Question(question, answers, correctAnswer, numTries, numCorrect)); // add each question object to the arraylist with
                                                                                          //necessary fields

    }while(fs.hasNext()); // do this while there is still stuff in the file
  return que; //return the arraylist
}

//method to write the data to the file
public static void writeFile(PrintWriter out,File f, String question, String[] answers, int correctAnswer, int numTries, int numCorrect) throws IOException
  {

  //Write object fields to .txt file

  out.println(question);
  out.println(answers.length);
  for (int j = 0; j<answers.length;j++)
  {
    out.println(answers[j]);
  }
  out.println(correctAnswer);
  out.println(numTries);
  out.println(numCorrect);
  }

  //method to sort the array of percents
  public static double[] SelectionSort ( double[] pcts )
  {
       int i, j, first;
       double temp;
       for ( i = pcts.length - 1; i > 0; i--)
       {
            first = 0;   //initialize to subscript of first element
            for(j = 1; j <= i; j ++)   //locate smallest element between positions 1 and i.
            {
                 if(pcts[j] < pcts[first])
                   first = j;
            }
            temp = pcts[first];   //swap smallest found with element in position i.
            pcts[first] = pcts[i];
            pcts[i] = temp;
        }
      return pcts; // return newly sorted array
  }
}
