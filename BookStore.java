import java.util.Scanner;

public class BookStore
{
    public static void main (String[] args)
    {


      Scanner sc = new Scanner(System.in);
      int numBooks = 0;
      int numBookmarks = 0;
      int numPaintings = 0;
      int discount = 0;
      double subtotal;
      double total;
      int choice;
      double bookPrice;
      double paintingPrice;
      double bookmarkPrice;
      double bookmarkPackPrice;
      int paintingPrice1 = 100;
      int bookmarkPacks = 0;
      int singleBookmarks;
      int number;
      double change;
      double money;
      boolean again;
      double tax;

        do{
          do{

            System.out.println("More customers in line? (1=yes and 2=no)!");
            number = sc.nextInt();

         } while( number != 1 && number != 2 );

        if(number == 2)
        {
          System.out.println("No more customers we are closing down!");
        }
        else
        {

          do{
            System.out.println("1 - Buy Books - $5.00 each");
            System.out.println("2 - Buy Bookmarks - $1.00 each, $5.00 for a six-pack");
            System.out.println("3 - Buy Paintings of Books - $100.00 each");
            System.out.println("4 - See current order");
            System.out.println("5 - Checkout");
            System.out.println("Please enter a valid option (1 through 5)");
            choice = sc.nextInt();

              switch(choice)
              {
                case 1: System.out.println("Currently in cart: " + numBooks + " books");
                        System.out.println("How many books would you like to buy?");
                        numBooks = sc.nextInt();
                        break;
                case 2: System.out.println("Currently in cart: " + numBookmarks + " bookmarks");
                        System.out.println("How many bookmarks would you like to buy?");
                        numBookmarks = sc.nextInt();
                        break;
                case 3: System.out.println("Currently in cart: " + numPaintings + " paintings");
                        System.out.println("How many paintings of books would you like to buy?");
                        numPaintings = sc.nextInt();
                        break;
                case 4: System.out.println("You currently have " + numBooks + " books");
                        System.out.println("You currently have " + numBookmarks + " bookmarks");
                        System.out.println("You currently have " + numPaintings + " paintings of books");
                        break;
                case 5: discount++;

                  if (numBookmarks >= 6)
                  {
                   bookmarkPacks = (numBookmarks / 6);
                   singleBookmarks = (numBookmarks % 6);
                  }
                else
                  {
                     singleBookmarks = numBookmarks;
                  }


                bookPrice = numBooks * 5;
                paintingPrice = numPaintings * 100;
                bookmarkPrice = singleBookmarks * 1;
                bookmarkPackPrice = bookmarkPacks * 5;

                if (discount % 3 == 0)
                {
                  System.out.println("You got a discount!");
                  subtotal = (bookPrice + paintingPrice + bookmarkPrice + bookmarkPackPrice) * .80;
                  tax = subtotal * .07;
                  total = subtotal +tax;

                }
                else
                {

                  System.out.println("You did not get a discount, maybe next time!");
                  subtotal = (bookPrice + paintingPrice + bookmarkPrice + bookmarkPackPrice);
                  tax = .07 * subtotal;
                  total = subtotal + tax;


                }



                System.out.println("----------------------------------------------");
                System.out.printf(bookmarkPacks + " Bookmark Pack(s): " + "$%,.2f%n", bookmarkPackPrice); //printf here
                System.out.printf(numBooks + " Book(s): " + "$%,.2f%n", bookPrice);//printf here
                System.out.printf(numPaintings + " Painting(s): " + "$%,.2f%n", paintingPrice); //printf here
                System.out.printf(singleBookmarks + " Single Bookmark(s): " + "$%,.2f%n", bookmarkPrice); //printf here
                System.out.printf("Your subtotal before tax is: $%,.2f%n", subtotal); //printf here
                System.out.printf("Your tax is: $%,.2f%n", tax); //printf here
                System.out.printf("Total is: $%,.2f%n", total);  //printf here
                System.out.println("----------------------------------------------");

                do{

                    System.out.println("Please enter in enough money to purchase");
                    money = sc.nextDouble();

                    if (money > total)
                    {
                      change = money - total;
                      System.out.printf("Thank you your change is: $%,.2f%n", change); //printf here
                      System.out.println("Thanks for shopping at this fine location!");

                    }

                    else
                    {
                      System.out.println("That is not enough money!");
                    }
                  }while (money < total);
                break;
                default: System.out.println("Please enter a valid number");
                          break;
                }

        } while(choice != 5);

      }

       numBooks = 0;
       numBookmarks = 0;
       numPaintings = 0;



    } while (number == 1);

  }
}
