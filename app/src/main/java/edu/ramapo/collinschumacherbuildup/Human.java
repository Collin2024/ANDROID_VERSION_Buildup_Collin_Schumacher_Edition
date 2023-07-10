package edu.ramapo.collinschumacherbuildup;
import java.util.Vector;
import java.util.*;
import java.lang.*;
import java.util.Collections;
public class Human extends Player
{
    /** *********************************************************************
    Function Name: Human
    Purpose: To create a Human object
    Parameters:
                none
    Return Value:
                none
    Algorithm:
                NOT AVAIABLE
    Assistance Received: none
    ********************************************************************* */
    Human()
    {
        // Nothing to see here
    }
    /** *********************************************************************
    Function Name: Human
    Purpose:
                To create a Human object and setup the board
    Parameters:
                name- Name of the Human passed by value

    Return Value:
                none
    Algorithm:
                sets the name of the Human and creates the board for that player and other private variable initalizations
    Assistance Received: none
    ********************************************************************* */
    Human(String name, char color)
    {
        super(name, color);
    }

    private static int read_int()
    {
        int number = 0;
        Scanner cin = new Scanner(System.in);
        number = cin.nextInt();
        return number;
    }
    /** *********************************************************************
    Function Name: read_string
    Purpose: To in a string used in the original model
    Parameters:
                none
    Return Value:
                returns the read in string
    Algorithm:
                NOT AVAIABLE
    Assistance Received: none
    ********************************************************************* */
    private static String read_string()
    {
        String text = "";
        Scanner cin = new Scanner(System.in);
        text = cin.nextLine();
        text = text.toUpperCase();
        return text;
    }
    /** *********************************************************************
    Function Name: make_move
    Purpose: To make a move (POLYMORPHIC FUNCTION)
    Parameters:
                opponent- the opposing player passed by value
    Return Value:
                none
    Algorithm:
                1. Set opponent as next player.
                2. If player cannot stack on own stack or opponent's stack, human skips turn. Otherwise,
                proceed to step 3.
                3. If both options are avaiable then allow player to make the choice 1, to stack
                on its side, or 2, for opponent's.
                4. ELSE, if the player can only stack on opponent's side or they chose to do so, then proceed
                to 5-6.
                5. Enter an approiate domino from the hand based on optimal placement.
                6. Determine if the selected domino can be legally placed. If it's possible,
                enter the best stack on opponent's side, stack it on their side, and remove the tile from hand.
                Otherwise, repeat steps 5-6.
                7. ELSE, if the player can only stack on their side or they chose to do so, proceed
                to 8-9.
                8. Enter an approiate domino from the hand based on optimal placement.
                9. Determine if the selected domino can be legally placed. If it's possible,
                enter the best stack on opponent's side, stack it on their side, and remove the tile from hand.
                Otherwise, repeat steps 8-9.


    Assistance Received: none
    ********************************************************************* */
    public void make_move(Player opponent)
    {
        String domino;
        String select;
        int selection = 0;
        opponent.set_next(true);
        set_next(false);

        if (can_move(opponent, 1) == false && can_move(opponent, 2) == false)
        {
            System.out.println("You cannot make any moves this means its the " + opponent.get_name() + "'s turn" +"\n");
            return;
        }
        if (can_move(opponent, 1) == true && can_move(opponent, 2) == true)
        {
            do
            {
                System.out.println("Enter 1 to stack a domino on your side or 2 to stack on your opponents side"+"\n");
                selection = read_int();
            } while (selection < 1 || selection > 2);
        }
        else if (can_move(opponent, 2) == true && can_move(opponent, 1) == false)
        {
            System.out.println("Sorry at this time you can only stack on your opponent's stacks ");

            selection = 2;
        }
        else if (can_move(opponent, 1) == true && can_move(opponent, 2) == false)
        {
            System.out.println("Sorry at this time you can only stack on your stacks ");
            selection = 1;
        }
        if (selection == 1)
        {
            do
            {
                do
                {

                    System.out.println("Please enter a tile to draw from your hand");
                    select = read_string();;
                    if (domino_location(select, 'H') == -1)
                    {
                        System.out.println("Invalid entry must be (B00 - B66)");
                    }
                    set_domino(select);
                } while (domino_location(select, 'H') == -1);
                do
                {
                    System.out.println("Please enter a tile to stack it on to your stacks");
                    domino = read_string();

                    if (domino_location(domino, 'S') == -1)
                    {
                        System.out.println("Invalid entry must be a valid domino from the stack");
                    }
                } while (domino_location(domino, 'S') == -1);
            } while (determine_legal_placement(domino) == false);
            cover(domino);
            draw_domino_from_hand(select);
        }
        else
        {
            do
            {
                do
                {
                    System.out.println("Please enter a tile to draw from your hand");
                    select = read_string();
                    if (domino_location(select, 'H') == -1)
                    {
                        System.out.println("Invalid entry must be (B00 - B66) ");
                    }
                    opponent.set_domino(select);
                } while (domino_location(select, 'H') == -1);
                do
                {
                    System.out.println("Please enter a tile to stack it on from the opponent's stack");
                    domino = read_string();
                    if (opponent.domino_location(domino, 'S') == -1)
                    {
                        System.out.println("Invalid entry must be a valid domino from the opponent's stack");
                    }
                } while (opponent.domino_location(domino, 'S') == -1);
            } while (opponent.determine_legal_placement(domino) == false);
            opponent.cover(domino);
            draw_domino_from_hand(select);
        }

    }
    /** *********************************************************************
    Function Name: selection
    Purpose: POLYMORPHIC FUNCTION to determine what the player wants to do
    Parameters:
                none
    Return Value:
                returns the selection (1-4)
    Algorithm:
                1. Display the player's options.
                2. Enter the approiate option (1-4).
                3. If the selection is not in the range, repeat steps 1-2.
                4. Return selection.
    Assistance Received: none
    ********************************************************************* */
    public int selection()
    {
        int choice = 0;
        do
        {
            System.out.println("Select the one of the following options by entering its number" );
            System.out.println("1. Save the game" );
            System.out.println("2. Make a move" );
            System.out.println("3. Ask for help" );
            System.out.println("4. Quit the game" );
            System.out.println("number:	");
            choice = read_int();
        } while (choice < 1 || choice > 4);
        return choice;
    }
    /** *********************************************************************
    Function Name: help_mode
    Purpose: To help the player (POLYMORPHIC FUNCTION since this project ultilizes polymorphic behavior)
    Parameters:
                opponent- the opposing player passed by value
    Return Value:
                none
    Algorithm:
                see computer class for the algorithm
    Assistance Received: none
    ********************************************************************* */
    public void help_mode(Player opponent)
    {
        return;
    }
    /** *********************************************************************
     Function Name: grab_text
     Purpose: (POLYMORPHIC FUNCTION) see computer class
     Parameters:
                none
     Return Value:
     returns the computers thought logic for why it did what it did
     Algorithm:
     return thought_process
     Assistance Received: none
     ********************************************************************* */
    public String grab_text()
    {
        return thought_process;
    }
}