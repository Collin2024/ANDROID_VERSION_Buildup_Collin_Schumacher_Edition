/**************************************************************
 * Name:  Collin Schumacher                                   *
 * Project : Buildup Project Android/Java                     *
 * Class : CMPS 366 Organization of Programming Languages	  *
 * Date : 2/7/2023                                            *
 **************************************************************/
package edu.ramapo.collinschumacherbuildup;

import static android.widget.Toast.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.*;

import android.widget.Button;
import android.view.View;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Vector;

public class Game extends AppCompatActivity{

    @Override

    /** *********************************************************************
    Function Name: onCreate
    Purpose: The GUI constructor for the game
    Parameters:
    savedInstanceState- A mapping from String keys to various Parcelable values. passed by value
    Return Value:
    none
    Algorithm:
       create the game GUI
    Assistance Received: none
    ********************************************************************* */
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /** *********************************************************************
    Function Name: game_type
    Purpose: To play the approiate type of game based on the pressed button
    Parameters:
                view- the specified View passed by value
    Return Value:
    none
    Algorithm:
            1. if the first button is pressed then we serialize
            2. if the second button is pressed then we start a new game
            3. otherwise the third button was pressed and we terminate the app.
    Assistance Received: none
    ********************************************************************* */
    public void game_type( View view)
    {

        if (view.getId() == R.id.one)
        {
            load_game();
        }
        else if(view.getId() == R.id.two)
        {
            new_game();
        }
        else if(view.getId() == R.id.quit)
        {
            finish();
            System.exit(0);
            return;
        }
    }
    /** *********************************************************************
    Function Name: file_selection
    Purpose: To get the selected file from the drop down menu
    Parameters:
            none
    Return Value:
    returns the selected file
    Algorithm:
                1. return the selected file name from the spinner menu
    Assistance Received: Thanks to https://stackoverflow.com/questions/5787809/get-spinner-selected-items-text
     for the help in figuring out how to get the selected text from a drop down menu
    ********************************************************************* */
    private String file_selection()
    {
        TextView file_name = findViewById(R.id.file_name);
        Spinner file_selected = findViewById(R.id.menu);
        file_name.setText(file_selected.getSelectedItem().toString());
        String x = file_name.getText().toString();
        if(x != "")
        {
            return x;
        }
        else
        {

            return "";
        }

    }
    /** *********************************************************************
    Function Name: re_convert
    Purpose: To convert a string into a number
    Parameters:
                line- the string to convert into a number passed by value
    Return Value:
                returns the converted number
    Algorithm:
                converts the string to a number using stringstream
    Assistance Received: none
    ********************************************************************* */
    private int re_convert(String line)
    {
        return Integer.parseInt(line);
    }
    /** *********************************************************************
     Function Name: omit
     Purpose: To remove part of a string (HELPER)
     Parameters:
            line- the line we are altering which is passed by value
            amount- the amount we are chopping off of the string passed by value
     Return Value:
                    returns the updated chopped string
     Algorithm:
     1. create the players and start a new round intention from the round class

     Assistance Received: none
     ********************************************************************* */
    public static String omit(String line, int amount)
    {
        return line.substring(amount);
    }
    /** *********************************************************************
    Function Name: new_game
    Purpose: To play a new game
    Parameters:
                none
    Return Value:
                none
    Algorithm:
                1. create the players and start a new round intention from the round class

    Assistance Received: none
    ********************************************************************* */
    private void new_game()
    {
        Vector<Player> player_list = new Vector<Player>();
        player_list.add(new Computer("Computer", 'W'));
        player_list.add(new Human("Human", 'B'));
        if(player_list.size() != 2)
        {
            Button one = findViewById(R.id.one);
            Button two = findViewById(R.id.two);
            ImageView Error = findViewById(R.id.Error);
            Error.setVisibility(Error.VISIBLE);
            one.setVisibility(one.GONE);
            two.setVisibility(two.GONE);
            TextView message = findViewById(R.id.message);
            String x = "ERROR: Currently there are ";
            x += Integer.toString(player_list.size());
            x += " players in the game there must be 2 players (check MainActivity file!) SYSTEM ABORT!";
            message.setText(x);
            message.setVisibility(message.VISIBLE);
            return;

        }
        Intent intention = new Intent(Game.this, Round.class);
        do
        {
            for(int i = 0; i < player_list.size(); i++)
            {
                player_list.get(i).reset_bone_yard();
            }
            for(int i = 0; i < player_list.size(); i++)
            {
                for(int j = 0; j < 6; j++)
                {
                    player_list.get(i).add_to_stack(player_list.get(i).draw_domino_from_boneyard());
                }
            }
            for(int i = 0; i < player_list.size(); i++)
            {
                for(int j = 0; j < 6; j++)
                {
                    player_list.get(i).add_to_hand(player_list.get(i).draw_domino_from_boneyard());
                }
            }
        }while(player_list.get(1).domino_value(player_list.get(1).get_at_hand_position(0)) == player_list.get(0).domino_value(player_list.get(0).get_at_hand_position(0)));

        intention.putExtra("1st_player_name", "Computer");
        intention.putExtra("2nd_player_name", "Human");
        String Chand = "";
        String Hhand = "";
        String Cboneyard = "";
        String Hboneyard = "";
        String Cstack = "";
        String Hstack = "";
        for(int i = 0; i < player_list.get(0).hand_size(); i++)
        {
            Chand += (player_list.get(0).get_at_hand_position(i));

        }
        for(int i = 0; i < player_list.get(1).hand_size(); i++)
        {
            Hhand += (player_list.get(1).get_at_hand_position(i));

        }
        for(int i = 0; i < player_list.get(0).bone_yard_size(); i++)
        {
            Cboneyard += (player_list.get(0).get_at_boneyard_position(i));

        }
        for(int i = 0; i < player_list.get(1).bone_yard_size(); i++)
        {
            Hboneyard += (player_list.get(1).get_at_boneyard_position(i));

        }
        for(int i = 0; i < player_list.get(0).stack_size(); i++)
        {
            Cstack += (player_list.get(0).get_at_stack_position(i));

        }
        for(int i = 0; i < player_list.get(1).stack_size(); i++)
        {
            Hstack += (player_list.get(1).get_at_stack_position(i));

        }
        intention.putExtra("Chand", Chand);
        intention.putExtra("Hhand", Hhand);
        intention.putExtra("Cboneyard", Cboneyard);
        intention.putExtra("Hboneyard", Hboneyard);
        intention.putExtra("Cstack", Cstack);
        intention.putExtra("Hstack", Hstack);
        intention.putExtra("Hscore", player_list.get(1).get_score());
        intention.putExtra("Cscore", player_list.get(0).get_score());
        intention.putExtra("Hrounds_won", player_list.get(1).get_wins());
        intention.putExtra("Crounds_won", player_list.get(0).get_wins());
        intention.putExtra("hand_count", 1);
        intention.putExtra("turn", 2);
        startActivity(intention);
    }
    /** *********************************************************************
     Function Name: draw_domino_picture
     Purpose: draws the domino face based on its respective value
     Parameters:
     B the specified button to draw the face onto passed by value
     Return Value:
     none
     Algorithm:
     1. get the id for the specified button text e.g B15 = ic_b15.xml
     make this the button's foreground
     2. Set the approiate color based on the first character eg W = white B = black for the background and the text color which equal eachother.
     3. return
     Assistance Received:   none
     ********************************************************************* */
    public void draw_domino_picture(Button B)
    {
        String test = "ic_";
        test += B.getText().toString();
        test = test.toLowerCase();
        int id = getResources().getIdentifier(test, "drawable", getPackageName());
        Drawable drawable = getResources().getDrawable(id);


        B.setForeground(drawable);
        if(B.getText().toString().charAt(0) == 'W')
        {
            B.setBackgroundTintList(ColorStateList.valueOf( Color.WHITE));
            B.setTextColor(Color.WHITE);
        }
        else
        {
            B.setBackgroundTintList(ColorStateList.valueOf( Color.BLACK));
            B.setTextColor(Color.BLACK);
        }

        return;
    }
    /** *********************************************************************
    Function Name: load_game
    Purpose: To load in a game from a serialization file
    Parameters:
                none
    Return Value:
                none
    Algorithm:

                1. Prompt for filename with the drop down menu
                2. Inside the entire file, read each line and, WHERE APPROIATE,
                ultilizing pre formatted serialization files (load_memory), then load
                all information in the approiate spots based on the PREDEFINED provided file layout.
                3. Determine first player and display the round.
                4. Play the round.

    Assistance Received: I used this for assistance for reading a txt file
     https://www.tutorialspoint.com/how-to-read-a-simple-text-file-in-android-app
    ********************************************************************* */
    public void load_game()
    {
        //https://www.youtube.com/watch?v=YaVLVDGZ8wY
        setContentView(R.layout.serialize_activity);
        TextView data = findViewById(R.id.data);
        TextView file_name = findViewById(R.id.file_name);
        Vector<Player> player_list = new Vector<Player>();

        ArrayList<String> filemenu = new ArrayList<>();
        filemenu.add("one");
        filemenu.add("two");
        filemenu.add("three");
        filemenu.add("four");
        filemenu.add("five");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, filemenu);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.menu);
        spinner.setAdapter(arrayAdapter);




        final String[] turner = {""};
        Button f = findViewById(R.id.f);
        Button f2 = findViewById(R.id.f2);

        f.setOnClickListener(View ->
        {
            file_name.setText(spinner.getSelectedItem().toString());
            String x = file_selection();
            if(x == "")
            {
                makeText(getApplicationContext(), "Invalid file please try again", LENGTH_SHORT).show();
            }
            else {
                int id = getResources().getIdentifier(x, "raw", getPackageName());
                if (id == 0)
                {
                    makeText(getApplicationContext(), "Invalid file please try again", LENGTH_SHORT).show();

                    return;
                }
                else {
                    Vector<String> contents = new Vector<String>();
                    int turn = -1;
                    InputStream inputStream = this.getResources().openRawResource(id);
                    BufferedReader file_content_finder = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer files_contents = new StringBuffer();
                    String line = "";
                    if (inputStream != null) {
                        try {
                            while ((line = file_content_finder.readLine()) != null) {
                                files_contents.append(line);
                                files_contents.append('\n');
                                contents.add(line + '\n');

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    data.setText(contents.get(1));
                    int iterator = 0;
                    for(int X = 0; X < contents.size(); X++)
                    {
                        String current_line = contents.get(X);
                        //System.out.println(current_line);
                        if(current_line.indexOf("Computer:") != -1 )
                        {
                            player_list.add(new Computer("Computer", 'W'));

                        }
                        if(current_line.indexOf("Human:") != -1 )
                        {
                            player_list.add(new Human("Human", 'B'));

                        }
                        else if(current_line.indexOf("Stacks: ") != -1)
                        {
                            current_line = current_line.substring(9);
                            for(int i = 0; i < 6; i++)
                            {
                                if(current_line.charAt(0) == ' ')
                                {
                                    current_line = current_line.substring(1);
                                }
                                String domino ="";
                                for(int k = 0; k < 3; k++)
                                {
                                    domino += current_line.charAt(k);
                                }
                                player_list.get(iterator).add_to_stack(domino);
                                current_line = current_line.substring(3);


                            }


                        }
                        else if(current_line.indexOf("Boneyard: ") != -1)
                        {
                            current_line = current_line.substring(11);
                            current_line=current_line.replace(" ", "");
                            String temp = current_line;
                            StringBuffer p = new StringBuffer(current_line);

                            final int THE_SIZE = temp.length() / 3;
                            if(current_line.length() > 0)
                            {
                                for(int i = 0; i < THE_SIZE; i++)
                                {
                                    if(current_line.length() == 0)
                                    {
                                        break;
                                    }
                                    String domino ="";
                                    for(int k = 0; k < 3; k++)
                                    {
                                        domino += current_line.charAt(k);
                                    }

                                    player_list.get(iterator).add_to_bone_yard(domino);

                                    current_line =omit(current_line,3);

                                }
                                String opp = contents.get(9);
                                opp = opp.substring(11);
                                String domino = "";
                                for(int k = 0; k < 3; k++)
                                {
                                    domino += opp.charAt(k);
                                }
                                if(player_list.get(iterator).bone_yard_size() == 22 && (player_list.get(0).domino_value(domino) == player_list.get(0).domino_value(player_list.get(0).get_at_boneyard_position(0))))
                                {

                                    data.setText("Sorry both players will have the same size starting domino. Boneyards, Stacks, and Hands will now reset");
                                    f2.setVisibility(f2.VISIBLE);
                                    Spinner file_selected = findViewById(R.id.menu);
                                    file_selected.setVisibility(file_selected.GONE);
                                    TextView main_title = findViewById(R.id.main_title);
                                    main_title.setVisibility(main_title.GONE);
                                    file_name.setVisibility(file_name.GONE);
                                    Button W1 = findViewById(R.id.W1);
                                    Button W2 = findViewById(R.id.W2);
                                    Button W3 = findViewById(R.id.W3);
                                    Button W4 = findViewById(R.id.W4);
                                    Button W5 = findViewById(R.id.W5);
                                    Button W6 = findViewById(R.id.W6);
                                    Button B1 = findViewById(R.id.B1);
                                    Button B2 = findViewById(R.id.B2);
                                    Button B3 = findViewById(R.id.B3);
                                    Button B4 = findViewById(R.id.B4);
                                    Button B5 = findViewById(R.id.B5);
                                    Button B6 = findViewById(R.id.B6);
                                    Button CB = findViewById(R.id.CB);
                                    Button HB = findViewById(R.id.HB);
                                    TextView CStack = findViewById(R.id.CStack);
                                    TextView HStack = findViewById(R.id.HStack);
                                    TextView first = findViewById(R.id.Player_one_name);
                                    first.setVisibility(first.VISIBLE);
                                    TextView second = findViewById(R.id.Player_two_name);
                                    TextView CBD = findViewById(R.id.Cboneyard);
                                    TextView HBD = findViewById(R.id.Hboneyard);
                                    TextView Cscore = findViewById(R.id.Cbone);
                                    TextView Hscore = findViewById(R.id.Hscore);
                                    second.setVisibility(second.VISIBLE);
                                    B1.setVisibility(B1.VISIBLE);
                                    B2.setVisibility(B2.VISIBLE);
                                    B3.setVisibility(B3.VISIBLE);
                                    B4.setVisibility(B4.VISIBLE);
                                    B5.setVisibility(B5.VISIBLE);
                                    B6.setVisibility(B6.VISIBLE);
                                    W1.setVisibility(W1.VISIBLE);
                                    W2.setVisibility(W2.VISIBLE);
                                    W3.setVisibility(W3.VISIBLE);
                                    W4.setVisibility(W4.VISIBLE);
                                    W5.setVisibility(W5.VISIBLE);
                                    W6.setVisibility(W6.VISIBLE);
                                    CStack.setVisibility(CStack.VISIBLE);
                                    HStack.setVisibility(HStack.VISIBLE);
                                    CBD.setVisibility(CBD.VISIBLE);
                                    HBD.setVisibility(HBD.VISIBLE);
                                    HB.setVisibility(HB.VISIBLE);
                                    CB.setVisibility(CB.VISIBLE);
                                    Cscore.setVisibility(Cscore.VISIBLE);
                                    Hscore.setVisibility(Hscore.VISIBLE);
                                    TextView Chand = findViewById(R.id.Chand);
                                    TextView Hhand = findViewById(R.id.Hhand);
                                    first.setText("Computer: ");
                                    second.setText("Human: ");
                                    Chand.setVisibility(Chand.VISIBLE);
                                    Hhand.setVisibility(Hhand.VISIBLE);
                                    TextView Crounds_won = findViewById(R.id.Crounds_won);
                                    TextView Hrounds_won = findViewById(R.id.Hrounds_won);
                                    Crounds_won.setVisibility(Crounds_won.VISIBLE);
                                    Hrounds_won.setVisibility(Hrounds_won.VISIBLE);
                                    f.setVisibility(f.GONE);
                                    W1.setText(player_list.get(0).get_at_stack_position(0));
                                    draw_domino_picture(W1);
                                    W2.setText(player_list.get(0).get_at_stack_position(1));
                                    draw_domino_picture(W2);
                                    W3.setText(player_list.get(0).get_at_stack_position(2));
                                    draw_domino_picture(W3);
                                    W4.setText(player_list.get(0).get_at_stack_position(3));
                                    draw_domino_picture(W4);
                                    W5.setText(player_list.get(0).get_at_stack_position(4));
                                    draw_domino_picture(W5);
                                    W6.setText(player_list.get(0).get_at_stack_position(5));
                                    draw_domino_picture(W6);
                                    CB.setText(player_list.get(0).get_at_boneyard_position(0));
                                    draw_domino_picture(CB);
                                    String hum = contents.get(8);
                                    hum = hum.substring(9);
                                    domino = "";
                                    for(int k=0; k < 3; k++)
                                    {
                                        domino += hum.charAt(k);
                                    }
                                    B1.setText(domino);
                                    draw_domino_picture(B1);
                                    domino = "";
                                    for(int k=4; k < 7; k++)
                                    {
                                        domino += hum.charAt(k);
                                    }
                                    B2.setText(domino);
                                    draw_domino_picture(B2);
                                    domino = "";
                                    for(int k=8; k < 11; k++)
                                    {
                                        domino += hum.charAt(k);
                                    }
                                    B3.setText(domino);
                                    draw_domino_picture(B3);
                                    domino = "";
                                    for(int k=12; k < 15; k++)
                                    {
                                        domino += hum.charAt(k);
                                    }
                                    B4.setText(domino);
                                    draw_domino_picture(B4);
                                    domino = "";
                                    for(int k=16; k < 19; k++)
                                    {
                                        domino += hum.charAt(k);
                                    }
                                    B5.setText(domino);
                                    draw_domino_picture(B5);
                                    domino = "";
                                    for(int k=20; k < 23; k++)
                                    {
                                        domino += hum.charAt(k);
                                    }
                                    B6.setText(domino);
                                    draw_domino_picture(B6);
                                    domino = "";
                                    for(int k=0; k < 3; k++)
                                    {
                                        domino += opp.charAt(k);
                                    }
                                    HB.setText(domino);
                                    draw_domino_picture(HB);

                                    Hscore.setText("Score: " + 0);
                                    Cscore.setText("Score: " + 0);
                                    Hrounds_won.setText("Rounds Won: " + 0);
                                    Crounds_won.setText("Rounds Won: " + 0);
                                    f2.setOnClickListener(v ->
                                    {
                                        new_game();
                                    });
                                    return;

                                }


                            }

                        }
                        else if(current_line.indexOf("Hand:") != -1 && player_list.get(iterator).bone_yard_size() != 22)
                        {
                            if(current_line.length() > 0)
                            {
                                current_line = omit(current_line,6);
                                current_line=current_line.replace(" ", "");

                                final int THE_SIZE = current_line.length()/3;
                                if(current_line.length() > 0)
                                {
                                    for(int i = 0; i < THE_SIZE; i++)
                                    {
                                        if(current_line.length() == 0)
                                        {
                                            break;
                                        }
                                        String domino ="";
                                        for(int k = 0; k < 3; k++)
                                        {
                                            domino += current_line.charAt(k);
                                        }
                                        player_list.get(iterator).add_to_hand(domino);

                                        current_line = omit(current_line,3);

                                    }

                                }

                            }
                            else if(current_line.length()  == 0)
                            {
                                if(player_list.get(iterator).bone_yard_size() > 4)
                                {
                                    for(int i = 0; i < 6; i++)
                                    {
                                        player_list.get(iterator).add_to_hand(player_list.get(iterator).get_at_boneyard_position(0));
                                        player_list.get(iterator).draw_domino_from_boneyard();
                                    }
                                }
                                else
                                {
                                    for(int i = 0; i < 4; i++)
                                    {
                                        player_list.get(iterator).add_to_hand(player_list.get(iterator).get_at_boneyard_position(0));
                                        player_list.get(iterator).draw_domino_from_boneyard();
                                    }
                                }

                            }
                        }
                        else if(current_line.indexOf("Score: ") != -1)
                        {
                            current_line = omit(current_line,8);
                            current_line=current_line.replace(" ", "");
                            String sc = "";
                            for(int k = 0; k < current_line.length() - 1; k++)
                            {
                                sc += current_line.charAt(k);
                            }
                            player_list.get(iterator).set_score(re_convert(sc));

                        }
                        else if(current_line.indexOf("Rounds Won: ") != -1)
                        {
                            current_line = omit(current_line,13);
                            current_line=current_line.replace(" ", "");
                            String sc = "";
                            for(int k = 0; k < current_line.length() - 1; k++)
                            {
                                sc += current_line.charAt(k);
                            }
                            player_list.get(iterator).set_wins(re_convert(sc));
                            data.setText(sc);

                            iterator++;
                        }
                        else if(current_line.indexOf("Turn:") != -1)
                        {
                            current_line = omit(current_line,6);
                            String sc = "";
                            for(int k = 0; k < current_line.length() - 1; k++)
                            {
                                sc += current_line.charAt(k);
                            }
                            //player_list.get(iterator).set_wins(re_convert(sc));
                            turner[0] = sc;
                            data.setText(turner[0]);


                        }
                    }
                }
                Intent intention = new Intent(Game.this, Round.class);
                intention.putExtra("1st_player_name", "Computer");
                intention.putExtra("2nd_player_name", "Human");
                String Chand = "";
                String Hhand = "";
                String Cboneyard = "";
                String Hboneyard = "";
                String Cstack = "";
                String Hstack = "";
                for(int i = 0; i < player_list.get(0).hand_size(); i++)
                {
                    Chand += (player_list.get(0).get_at_hand_position(i));

                }
                for(int i = 0; i < player_list.get(1).hand_size(); i++)
                {
                    Hhand += (player_list.get(1).get_at_hand_position(i));

                }

                for(int i = 0; i < player_list.get(0).bone_yard_size(); i++)
                {
                    Cboneyard += (player_list.get(0).get_at_boneyard_position(i));

                }
                for(int i = 0; i < player_list.get(1).bone_yard_size(); i++)
                {
                    Hboneyard += (player_list.get(1).get_at_boneyard_position(i));

                }
                for(int i = 0; i < player_list.get(0).stack_size(); i++)
                {
                    Cstack += (player_list.get(0).get_at_stack_position(i));

                }
                for(int i = 0; i < player_list.get(1).stack_size(); i++)
                {
                    Hstack += (player_list.get(1).get_at_stack_position(i));

                }
                intention.putExtra("Chand", Chand);
                intention.putExtra("Hhand", Hhand);
                intention.putExtra("Cboneyard", Cboneyard);
                intention.putExtra("Hboneyard", Hboneyard);
                intention.putExtra("Cstack", Cstack);
                intention.putExtra("Hstack", Hstack);
                intention.putExtra("Hscore", player_list.get(1).get_score());
                intention.putExtra("Cscore", player_list.get(0).get_score());
                intention.putExtra("Hrounds_won", player_list.get(1).get_wins());
                intention.putExtra("Crounds_won", player_list.get(0).get_wins());
                int hand_counter = 0;
                if(player_list.get(0).bone_yard_size() == 16)
                {
                    hand_counter = 1;
                }
                else if(player_list.get(0).bone_yard_size() == 10)
                {
                    hand_counter = 2;
                }
                else if(player_list.get(0).bone_yard_size() == 4)
                {
                    hand_counter = 3;
                }
                else
                {
                    hand_counter = 4;
                }
                intention.putExtra("hand_count", hand_counter);
                int goes_first = 2;
                if(turner[0] == "Computer" || turner[0].length() == 8)
                {
                    goes_first = 0;
                }
                else
                {
                    goes_first = 1;
                }
                intention.putExtra("turn", goes_first);
                startActivity(intention);

            }

        });
    }
}


    