package edu.ramapo.collinschumacherbuildup;

import java.util.Vector;
import java.util.*;
import java.lang.*;
import java.util.Collections;

public class Domino
{
    Vector <String> bone_yard = new Vector<String>();
    Vector <String> hand = new Vector<String>();
    Vector <String> stack = new Vector<String>();
    String selected_bone;
    /** *********************************************************************
    Function Name: Domino
    Purpose: To create a Domino object
    Parameters:
                none
    Return Value:
                none
    Algorithm:
                initalize the boneyard and hand
    Assistance Received: none
    ********************************************************************* */
    Domino()
    {
        this.bone_yard = bone_yard;
        this.hand = hand;
        this.selected_bone = selected_bone;
    }
    /** *********************************************************************
    Function Name: setup_bone_yard
    Purpose: To set the bone_yard pile for the player
    Parameters:
                player_color- the color of the player's dominos passed by value
    Return Value:
                none
    Algorithm:
                1.Set the domino color for the player.
                2. Use a triple for loop to generate the TOTAL 28 dominoes 0|0 to 6|6.
                3. Use a built in function within Java called "Collections.shuffle" to assist in shuffling the dominoes randomly

    Assistance Received: I used this site for assistance for vector shuffling
     https://www.geeksforgeeks.org/java-program-to-shuffle-vector-elements/#
    ********************************************************************* */
    public void setup_bone_yard(char player_color)
    {
        bone_yard.clear();
        hand.clear();
        stack.clear();
        String bone = "";
        int counter = 1;
        final int TOTAL = 28;
        final int MAX_PIPS = 6;
        for (int i = 0; i <= MAX_PIPS; i++)
        {
            for (int j = i; j <= MAX_PIPS; j++)
            {
                // after the TOTAL dominoes are created shuffle the pile and return
                if (counter > TOTAL)
                {
                    Collections.shuffle(bone_yard);
                    return;
                }
                for (int k = j; k <= MAX_PIPS; k++)
                {
                    bone += player_color;
                    bone += convert(j);
                    bone += convert(k);
                    add_to_bone_yard(bone);
                    bone = "";
                    counter++;
                }
            }
        }
    }
    /** *********************************************************************
    Function Name: display_bone_yard
    Purpose: To display the bone yard
    Parameters:
                none
    Return Value:
                none
    Algorithm:
                display the boneyard
    Assistance Received: none
    ********************************************************************* */
    public void display_bone_yard()
    {
        System.out.print("Boneyard: ");
        for(int i = 0; i < bone_yard.size(); i++)
        {
            System.out.print(bone_yard.get(i));
            System.out.print(' ');
        }
        System.out.print('\n');
        //System.out.println(domino_value(bone_yard.get(0)));
    }
    /** *********************************************************************
    Function Name: display_stack
    Purpose: To display the stack
    Parameters:
                none
    Return Value:
                none
    Algorithm:
                display the stack
    Assistance Received: none
    ********************************************************************* */
    public void display_stack()
    {
        System.out.print("Stacks: ");
        for(int i = 0; i < stack.size(); i++)
        {
            System.out.print(stack.get(i));
            System.out.print(' ');
        }
        System.out.print('\n');
    }
    /** *********************************************************************
    Function Name: display_hand
    Purpose: To display the hand
    Parameters:
                none
    Return Value:
                none
    Algorithm:
                display the hand
    Assistance Received: none
    ********************************************************************* */
    public void display_hand()
    {
        System.out.print("Hand: ");
        for(int i = 0; i < hand.size(); i++)
        {
            System.out.print(hand.get(i));
            System.out.print(' ');
        }
        System.out.print('\n');
    }
    /** *********************************************************************
    Function Name: convert
    Purpose: To convert a number into a string
    Parameters:
                num- the number to convert into a string passed by value
    Return Value:
                returns the converted string
    Algorithm:
                converts the number to a string using toString
    Assistance Received: none
    ********************************************************************* */
    private String convert(int num)
    {

        return Integer.toString(num);

    }
    /** *********************************************************************
    Function Name: re_convert
    Purpose: To convert a string into a number
    Parameters:
                tile- the domino string to convert into a number passed by value
    Return Value:
                returns the converted number
    Algorithm:
                converts the domino string to a number using parseInt
    Assistance Received: none
    ********************************************************************* */
    private int re_convert(String tile)
    {

        return Integer.parseInt(tile);

    }
    /** *********************************************************************
    Function Name: draw_domino_from_boneyard
    Purpose: To draw a random domino from the bone yard
    Parameters:
                none
    Return Value:
                returns the drawn domino
    Algorithm:
                1. Determine a random number position between 0 and the size of the bone yard.
                2. Determine the specified domino at the position.
                3. Remove the domino from the bone yard.
    Assistance Received: none
    ********************************************************************* */
    public String draw_domino_from_boneyard()
    {
        String tile = bone_yard.get(0);
        bone_yard.removeElement(tile);
        return tile;
    }
    /** *********************************************************************
    Function Name: add_to_hand
    Purpose: To add a specified domino to the hand
    Parameters:
                tile- the specified domino passed by value
    Return Value:
                none
    Algorithm:
                adds the tile to the hand
    Assistance Received: none
    ********************************************************************* */
    public void add_to_hand(String tile)
    {
        hand.add(tile);
    }
    /** *********************************************************************
    Function Name: add_to_stack
    Purpose: To add a specified domino to the stack
    Parameters:
                tile- the specified domino passed by value
    Return Value:
                none
    Algorithm:
                adds the tile to the stack
    Assistance Received: none
    ********************************************************************* */
    public void add_to_stack(String tile)
    {
        stack.add(tile);
    }
    /** *********************************************************************
    Function Name: domino_value
    Purpose: To determine the numerical value of the specified domino
    Parameters:
                tile- the specified domino passed by value
    Return Value:
                returns the specified domino's total pips (DOTS)
    Algorithm:
                1. Erase the first character.
                2. Use re_convert to convert it to an integer e.g: W66 = 66.
                3. Determine 2's result / 10 + determine 2's result % 10
                e.g W66 = 66 = 12.

    Assistance Received: none
    ********************************************************************* */
    public int domino_value(String tile)
    {
        return (((re_convert(tile.substring(1)) / 10)) + (re_convert(tile.substring(1)) % 10));
    }
    /** *********************************************************************
    Function Name: add_to_bone_yard
    Purpose: To add a specified domino to the stack
    Parameters:
                tile- the specified domino passed by value
    Return Value:
                none
    Algorithm:
                adds the tile to the bone yard
    Assistance Received: none
    ********************************************************************* */
    public void add_to_bone_yard(String tile)
    {
        bone_yard.add(tile);
    }
    /** *********************************************************************
    Function Name: draw_domino_from_hand
    Purpose: To remove a specified domino from the hand
    Parameters:
                tile- the specified domino passed by value
    Return Value:
                none
    Algorithm:
                1. Search through the bone yard to determine if tile matches
                the current iteration of the boneyard.
                2. Erase tile at that found position.
    Assistance Received: none
    ********************************************************************* */
    public void draw_domino_from_hand(String tile)
    {
        //hand.remove(hand.get(0) + domino_location(tile, 'H'));
        hand.remove(tile);
    }
    /** *********************************************************************
    Function Name: set_domino
    Purpose: To set the selected domino
    Parameters:
                tile- the specified domino passed by value
    Return Value:
                none
    Algorithm:
                1. selected_bone = tile
    Assistance Received: none
    ********************************************************************* */
    public void set_domino(String tile)
    {
        selected_bone = tile;
    }
    /** *********************************************************************
    Function Name: get_domino
    Purpose: To get the selected domino
    Parameters:
                none
    Return Value:
                returns the selected bone
    Algorithm:
                1. selected_bone = tile
    Assistance Received: none
    ********************************************************************* */
    public String get_domino()
    {
        return selected_bone;
    }
    /** *********************************************************************
    Function Name: determine_legal_placement
    Purpose: To determine if the domino can be placed
    Parameters:
                tile- the specified stack domino passed by value
    Return Value:
                returns true if it can be placed, otherwise, return false
    Algorithm:
                1. Determine if the selected bone is NOT a double tile and that it is greater than or equal to the
                desired stack domino. If so, return true.
                2. Determine if the selected bone is a double tile and if the stack domino is NOT a double tile. If so, return true.
                3. Determine if the selected bone and stack tile are both double tile and that the selected bone is greater than the
                stack tile. If so, return true.
                4. Otherwise return false
    Assistance Received: none
    ********************************************************************* */
    public boolean determine_legal_placement(String tile)
    {
        if (is_double_domino(selected_bone) == false && (domino_value(selected_bone) >= domino_value(tile)))
        {
            return true;
        }
        else if (is_double_domino(selected_bone) == true && is_double_domino(tile) == false)
        {
            return true;
        }
        else if ((is_double_domino(selected_bone) == true && is_double_domino(tile) == true) && (domino_value(selected_bone) > domino_value(tile)))
        {
            return true;
        }
        return false;
    }
    /** *********************************************************************
    Function Name: is_double_domino
    Purpose: To determine if it is a double domino
    Parameters:
                tile- the specified domino passed by value
    Return Value:
                returns true if it's double domino, otherwise return false
    Algorithm:
                1. Erase first character (color).
                2. Creates 2 strings for the other 2 characters (0-6).
                3. Ultilize re_convert function to convert them to numbers and, if they are equal,
                return true.
                4. Otherwise, return false.
    Assistance Received: none
    ********************************************************************* */
    public boolean is_double_domino(String tile)
    {
        tile = tile.substring(1);
        String first_face = "";
        String second_face= "";
        first_face += tile.charAt(0);
        second_face += tile.charAt(1);
        if (re_convert(first_face) == re_convert(second_face))
        {
            return true;
        }
        return false;
    }
    /** *********************************************************************
    Function Name: domino_location
    Purpose: To get the specified domino's location
    Parameters:
                tile- the specified domino passed by value
                pile- the type of pile that we are looking at e.g: S for stack or H for hand passed by value
    Return Value:
                returns the domino location if it exists if it does not exist return -1
    Algorithm:
                1. Determine if the domino string size is equal to 3 and, if its not, return -1.
                2. Determine if the first character accurately represents the color of the bone e.g B for black or W for white.
                If it doesn't, return -1.
                3. Determine if the rest of the characters are a number from 0 to 6. If not, return -1.
                4. If the pile is H (for looking into hand), look for the specifed bone in the hand pile. If
                it is found, return the location.
                5. If the pile is S (for looking into stack), look for the specifed bone in the stack pile. If
                it is found, return the location.
                6. Otherwise, return -1.
    Assistance Received: none
    ********************************************************************* */
    public int domino_location(String tile, char pile)
    {
        int place = 0;
        // tiles have color and 2 numbers ONLY
        if (tile.length() != 3)
        {
            return -1;
        }
        else if (tile.charAt(0)  != 'W' && tile.charAt(0) != 'B')
        {
            return -1;
        }
        for (int i = 1; i < tile.length(); i++)
        {
            if (tile.charAt(i) > '6' || tile.charAt(i) < '0')
            {
                return -1;
            }
        }
        // if we are looking into the hand
        if (pile == 'H')
        {
            for (int i = 0; i < hand.size(); i++)
            {
                if ((hand.get(i).charAt(0) == tile.charAt(0)) && (hand.get(i).charAt(1) == tile.charAt(1)) && (hand.get(i).charAt(2) == tile.charAt(2)))
                {
                    return i;
                }
            }
        }
        // if we are looking into the stack
        else if (pile == 'S')
        {
            for (int i = 0; i < stack.size(); i++)
            {
                if ((stack.get(i).charAt(0) == tile.charAt(0)) && (stack.get(i).charAt(1) == tile.charAt(1)) && (stack.get(i).charAt(2) == tile.charAt(2)))
                {
                    return i;
                }
            }
        }

        return -1;
    }
    /** *********************************************************************
    Function Name: cover
    Purpose: To cover the specified domino
    Parameters:
                tile- the specified domino passed by value
    Return Value:
                none
    Algorithm:
                1. Determine the location of the domino in the stack.
                2. Set the stack at the specified location equal to the selected_bone
                which is "covering".
    Assistance Received: none
    ********************************************************************* */
    public void cover(String tile)
    {
        int pos = 0;
        for (int i = 0; i < stack.size(); i++)
        {
            if ((stack.get(i).charAt(0) == tile.charAt(0)) && (stack.get(i).charAt(1) == tile.charAt(1)) && (stack.get(i).charAt(2) == tile.charAt(2)))
            {
                pos = i;
                break;
            }
        }
        stack.set(pos, selected_bone);
    }
    /** *********************************************************************
    Function Name: get_at_stack_position
    Purpose: To get the domino at the specified stack location
    Parameters:
                pos- the specified location passed by value
    Return Value:
                returns the stack domino at the specified location
    Algorithm:
                1. return the stack domino at the specified location
    Assistance Received: none
    ********************************************************************* */
    public String get_at_stack_position(int pos)
    {
        return stack.get(pos);
    }
    /** *********************************************************************
    Function Name: hand_size
    Purpose: To get the hand size
    Parameters:
                none
    Return Value:
                returns the hand size
    Algorithm:
                1. return the hand size
    Assistance Received: none
    ********************************************************************* */
    public int hand_size()
    {
        return hand.size();
    }
    /** *********************************************************************
    Function Name: stack_size
    Purpose: To get the hand size
    Parameters:
                none
    Return Value:
                returns the stack size
    Algorithm:
                1. return the stack size
    Assistance Received: none
    ********************************************************************* */
    public int stack_size()
    {
        return stack.size();
    }
    /** *********************************************************************
    Function Name: reset_hand
    Purpose: To get the hand size
    Parameters:
                none
    Return Value:
                returns the stack size
    Algorithm:
                1. return the stack size
    Assistance Received: none
    ********************************************************************* */
    public void reset_hand()
    {
        if (hand.size() != 0)
        {
            hand.clear();
        }
    }
    /** *********************************************************************
    Function Name: bone_yard_size
    Purpose: To get the bone yard size
    Parameters:
                none
    Return Value:
                returns the bone yard size
    Algorithm:
                1. return the bone yard size
    Assistance Received: none
    ********************************************************************* */
    public int bone_yard_size()
    {
        return bone_yard.size();
    }
    /** *********************************************************************
    Function Name: get_at_boneyard_position
    Purpose: To get the bone yard size
    Parameters:
                none
    Return Value:
                pos- the position passed by value
    Algorithm:
                1. return the bone_yard.at(pos)
    Assistance Received: none
    ********************************************************************* */
    public String get_at_boneyard_position(int pos)
    {
        return bone_yard.get(pos);
    }
    /** *********************************************************************
    Function Name: get_at_hand_position
    Purpose: To get the domino at the specified hand location
    Parameters:
                pos- the specified location passed by value
    Return Value:
                returns the hand domino at the specified location
    Algorithm:
                1. return the hand domino at the specified location
    Assistance Received: none
    ********************************************************************* */
    public String get_at_hand_position(int pos)
    {
        return hand.get(pos);
    }
}

