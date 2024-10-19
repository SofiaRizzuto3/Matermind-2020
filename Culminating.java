// The "Culminating" class.
/* Mastermind Culminating
Sofia Rizzuto
November 10th 2020
In this program I am simulating the game "Mastermind".
The code creates a sequence of numbers which correspond with colors.
The user needs to guess what the sequence is in 10 turns or less.
The code has loops, if-structures, methods, arrays, graphics, and more.
Once the sequence of numbers has been craeted, it is run through a method
that gets the users guess and checks if it macthes the sequence.
It then returns a value dictating whether the user is correct and stops or continues the game from there.
It forces you to enter numbers only between 1 and 6 and either when you guess the
correct sequence or run out of options, a message is printed to finish it off.
*/
import java.awt.*;
import hsa.Console;

public class Culminating
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	// instructions on how to play
	c.println ("                      WELCOME TO THE MASTERMIND GAME!!!                      ");
	c.println ("               This is a game of logical thinking and some guesswork           ");
	c.println ("                   Click \"Enter\" to see the board, then wait                   ");
	c.getChar ();
	clear ();   // method that clears scree
	drawing (); // method that draws board
	delay (2000);
	clear ();
	c.println ("For those of you who have never played, here are the instructions: ");
	c.println ("1) As you can see, there are 6 different colors to choose from");
	c.println ("2) The computer has selected a random sequence using only 4 spaces");
	c.println ("3) Colors may repeat (for example: blue, blue, red, red, could be a sequence)");
	c.println ("4) Your job is to figure out what the sequence is in 10 guesses or less");
	c.println ("5) At each individual space you will guess one number, and click enter. Then ");
	c.println ("   after all 4 numbers are entered, it will say how many colors are correct, ");
	c.println ("   and of those, how many are in the correct position");
	c.println ("6) Moreover, if you guess a color and that color is used twice, it will only ");
	c.println ("   say that you guessed one of them. You need to guess the color twice for it ");
	c.println ("   to be counted twice. By using the example above, if you entered red, blue ");
	c.println ("   pink, green, it would say two are correct colors, one is in the correct space");
	c.println ("7) Each color corresponds to a number that is shown at the bottom of the board");
	c.println ("8) If you enter a number outside of the 1-6 range, you will be asked to ");
	c.println ("   re-enter a number");
	c.println ("9) Once you have selected a number that corresponds to a color, you cannot go ");
	c.println ("   back");
	c.println ("10) Ensure not to type in a space");
	c.println ("Click \"Enter\" to play");
	c.getChar ();
	delay (100);
	clear ();
	// invokes drawing method
	drawing ();

	// randomizing the four numbers (sequence)
	int[] random = new int [4];
	for (int count3 = 0 ; count3 < 4 ; count3++)
	{
	    random [count3] = (int) (Math.random () * 6) + 1;
	}
	// assigning four numbers in array to individual values
	// this allows for us to check the guesses multiple times
	int a, b, c, d;
	a = random [0];
	b = random [1];
	c = random [2];
	d = random [3];
	// 1 = red 2 = blue 3 = green 4 = yellow 5 = pink 6 = cyan

	// declaration of varibales
	int y = 10, len = 20, wid = 20, pos1 = 1, space = 0, answer = 0;

	// declaring the guessing array
	int[] guess = new int [4];

	// the loop that allows the user to guess 10 times
	for (int attempt = 0 ; attempt < 10 ; attempt++)
	{
	    random [0] = a;                                                 // resetting the array
	    random [1] = b;
	    random [2] = c;
	    random [3] = d;
	    answer = guesses (random, guess, y, len, wid, pos1, space);     // calls upon the guess method which compares the guess and answer array as well as drawing the circles the user guessed
	    if (answer == 4)                                                // the answer variable tells us if the user inputted the correct sequence
		break;                                                      // if they're right, the loop will stop
	    y += 40;
	    pos1 += 2;

	}
	random [0] = a;                                                     // resetting the array
	random [1] = b;
	random [2] = c;
	random [3] = d;

	// if user wins, the winner method is called and a meesage is printed
	if (answer == 4)
	{
	    delay (1000);
	    winner (random);
	}

	// if user loses, the loser array is called and a message is printed
	else
	{
	    delay (1000);
	    loser (random);
	}

	// Place your program here.  'c' is the output console
    } // main method

    public static void delay (int millisecs)  // Delay Method
    {
	try
	{
	    Thread.currentThread ().sleep (millisecs);
	}


	catch (InterruptedException e)
	{
	}
    }

    public static int guesses (int[] random, int[] guess, int y, int len, int wid, int pos1, int space)  // guess array
    {
	// guesses
	int number = 1;
	int row = 24, column = 18;
	for (int count4 = 0 ; count4 < 4 ; count4++)            // loop to go through the array and assign varibles
	{
	    column += 2;                                        // changes column to move
	    c.setCursor (row, column);
	    c.print ("|Space " + number + ": ");                // at the set column and row, the space where the numbers are imputted is printed
	    column += 10;                                       // changes column to move
	    c.setCursor (row, column);
	    do
	    {
		guess [count4] = c.readInt ();                  // obtains the user guess
		if (guess [count4] < 1 || guess [count4] > 6)   // checks to ensure guess is between 1 and 6 and instructs user to re-enter a value
		{
		    c.setCursor (row, column);
		    c.println ("Re-enter");
		    c.setCursor (row, column);
		}
	    }
	    while (guess [count4] < 1 || guess [count4] > 6);
	    number++;
	}
	c.setColor (Color.white);                               // cleans up space by clearing the bottom of screen where numbers entered is
	c.fillRect (0, 450, 600, 50);

	// goes through both arrays to see if the colors are in the correct spot
	for (int count7 = 0 ; count7 < 4 ; count7++)
	{
	    if (random [count7] == guess [count7])
		space++;                                        // counts how many colors are in the correct spot
	}
	c.setCursor (pos1, 10);
	c.print (space + " = Correct Spot");                    // prints out how many are in the correct spot

	// draw the cricles in the color the user guessed
	int x = 235;
	int count6 = 0;
	for (int count5 = 0 ; count5 < 4 ; count5++)
	{
	    if (guess [count6] == 1)
		c.setColor (Color.red);
	    if (guess [count6] == 2)
		c.setColor (Color.blue);
	    if (guess [count6] == 3)
		c.setColor (Color.green);
	    if (guess [count6] == 4)
		c.setColor (Color.yellow);
	    if (guess [count6] == 5)
		c.setColor (Color.pink);
	    if (guess [count6] == 6)
		c.setColor (Color.cyan);
	    c.fillOval (x, y, len, wid);
	    c.setColor (Color.black);
	    c.drawOval (x, y, len, wid);
	    x += 50;                                            // changes x-value so circle is moved
	    count6 += 1;
	}
	y += 40;
	// going through both arrays to see how many colors are correct
	int type = 0;
	for (int count8 = 0 ; count8 < 4 ; count8++)            // goes through the sequence to check how many colors are correct
	{
	    for (int count9 = 0 ; count9 < 4 ; count9++)        // goes through the guess sequence to check how many colors are correct
	    {
		if (random [count8] == guess [count9])          // changes two values from both arrays if they match up so no doubles are given
		{
		    type++;
		    random [count8] = 0;
		    guess [count9] = 0;
		    break;
		}
	    }
	}
	c.setCursor (pos1, 55);
	c.print (type + " = Correct Color");                    // prints how many of the colors are correct

	// returns number that dictates how many are in the correct spot
	return (space);
    }

    public static void drawing ()
    {
	// filling the color for the rectangle with the legend
	c.setColor (Color.lightGray);
	c.fillRect (170, 400, 300, 40);
	// drawing and filling the rectangle part of the board
	c.setColor (Color.gray);
	c.fillRect (220, 0, 200, 400);
	c.setColor (Color.black);
	c.drawRect (220, 0, 200, 400);
	// drawing the lines going from left to right
	c.setColor (Color.black);
	int x = 220, y = 40, len = 420, wid = 40;
	for (int count = 0 ; count < 10 ; count++)
	{
	    c.drawLine (x, y, len, wid);
	    y += 40;
	    wid += 40;
	}
	// drawing the lines going from top to bottom
	x = 220;
	y = 0;
	len = 220;
	wid = 440;
	for (int count2 = 0 ; count2 < 5 ; count2++)
	{
	    c.drawLine (x, y, len, wid);
	    x += 50;
	    len += 50;
	}
	// 1 = red 2 = blue 3 = green 4 = yellow 5 = pink 6 = cyan
	// drawing the rectangle holding the legend of numbers and colors
	c.drawRect (170, 400, 300, 40);
	// drawing red circle and the number 1
	c.setColor (Color.red);
	c.fillOval (185, 410, 20, 20);
	c.setColor (Color.black);
	c.drawString ("1", 193, 424);
	// drawing blue circle and the number 2
	c.setColor (Color.blue);
	c.fillOval (235, 410, 20, 20);
	c.setColor (Color.black);
	c.drawString ("2", 243, 424);
	// drawing green circle and the number 3
	c.setColor (Color.green);
	c.fillOval (285, 410, 20, 20);
	c.setColor (Color.black);
	c.drawString ("3", 293, 424);
	// drawing yellow circle and the number 4
	c.setColor (Color.yellow);
	c.fillOval (335, 410, 20, 20);
	c.setColor (Color.black);
	c.drawString ("4", 343, 424);
	// drawing pink circle and the number 5
	c.setColor (Color.pink);
	c.fillOval (385, 410, 20, 20);
	c.setColor (Color.black);
	c.drawString ("5", 393, 424);
	// drawing cyan circle and the number 6
	c.setColor (Color.cyan);
	c.fillOval (435, 410, 20, 20);
	c.setColor (Color.black);
	c.drawString ("6", 443, 424);
	// outlines circle
	int z = 185;
	for (int count9 = 0 ; count9 < 6 ; count9++)
	{
	    c.setColor (Color.black);
	    c.drawOval (z, 410, 20, 20);
	    z += 50;
	}
    }

    public static void winner (int[] random)
    {
	// message if user wins
	delay (100);
	clear ();
	c.println ("CONGRATULATIONS!!!");
	c.println ("YOU GUESSED THE SEQUENCE:");
	c.println (random [0] + " " + random [1] + " " + random [2] + " " + random [3] + " ");
	c.println ("HOPE YOU PLAY AGAIN!");
    }

    public static void loser (int[] random)
    {
	// message if user loses
	delay (100);
	clear ();
	c.println ("Sorry, you didn't guess it :{ ");
	c.println ("The sequence was:");
	c.println (random [0] + " " + random [1] + " " + random [2] + " " + random [3] + " ");
	c.println ("HOPE YOU PLAY AGAIN!");
    }

    public static void clear ()
    {
	// method used to clear screen by filling white box
	c.setColor (Color.white);
	c.fillRect (0, 0, 640, 500);
	c.setCursor (2, 2);
    }
} // Culminating class
