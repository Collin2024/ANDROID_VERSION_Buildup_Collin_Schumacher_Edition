package edu.ramapo.collinschumacherbuildup;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.*;
import java.io.Serializable;
import android.widget.Button;
import android.view.View;

import java.util.Vector;

public class Round extends AppCompatActivity implements Serializable, View.OnClickListener
{
    Vector<Player> player_list = new Vector<Player>();
    boolean move_type = false;
    int grab = 1;
    int counter = 1;
    int hand_count = 0;
    boolean next_round = false;
    @Override
    /** *********************************************************************
    Function Name: onCreate
    Purpose: The GUI constructor for the round
    Parameters:
                savedInstanceState- A mapping from String keys to various Parcelable values. passed by value
    Return Value:
                none
    Algorithm:
                1. parse the game contents from ".getSerializableExtra" Game class and executes the round respectively
                2. checks if there are 2 players and if not it displays an error picture
                ELSE
                3. determine and display first player
    Assistance Received: none
    ********************************************************************* */
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_game_activity);
        player_list.add(new Computer(((String) getIntent().getSerializableExtra("1st_player_name")), 'W'));
        player_list.add(new Human(((String) getIntent().getSerializableExtra("2nd_player_name")), 'B'));
        String Hhand = ((String) getIntent().getSerializableExtra("Hhand"));
        String Chand = ((String) getIntent().getSerializableExtra("Chand"));
        String Cboneyard = ((String) getIntent().getSerializableExtra("Cboneyard"));
        String Hboneyard = ((String) getIntent().getSerializableExtra("Hboneyard"));
        String Hstack = ((String) getIntent().getSerializableExtra("Hstack"));
        String Cstack = ((String) getIntent().getSerializableExtra("Cstack"));
        int Human_score = ((int) getIntent().getSerializableExtra("Hscore"));
        int Computer_score = ((int) getIntent().getSerializableExtra("Cscore"));

        String Computer_stack = Cstack;
        String Human_stack = Hstack;
        String Computer_hand = Chand;
        String Human_hand = Hhand;
        String Computer_boneyard = Cboneyard;
        String Human_boneyard = Hboneyard;
        TextView first = findViewById(R.id.Player_one_name);
        TextView second = findViewById(R.id.Player_two_name);
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
        Button WH1 = findViewById(R.id.WH1);
        Button WH2 = findViewById(R.id.WH2);
        Button WH3 = findViewById(R.id.WH3);
        Button WH4 = findViewById(R.id.WH4);
        Button WH5 = findViewById(R.id.WH5);
        Button WH6 = findViewById(R.id.WH6);
        Button BH1 = findViewById(R.id.BH1);
        Button BH2 = findViewById(R.id.BH2);
        Button BH3 = findViewById(R.id.BH3);
        Button BH4 = findViewById(R.id.BH4);
        Button BH5 = findViewById(R.id.BH5);
        Button BH6 = findViewById(R.id.BH6);
        Button prompt = findViewById(R.id.prompt);
        ImageView Error = findViewById(R.id.Error);
        TextView turn = findViewById(R.id.turn);
        Button on_own = findViewById(R.id.on_own);
        Button help = findViewById(R.id.help);
        Button on_opponents = findViewById(R.id.on_opponents);
        TextView Cscore = findViewById(R.id.Cbone);
        TextView Hscore = findViewById(R.id.Hscore);
        TextView Crounds_won = findViewById(R.id.Crounds_won);
        TextView Hrounds_won = findViewById(R.id.Hrounds_won);
        Button CB = findViewById(R.id.CB);
        Button HB = findViewById(R.id.HB);
        TextView CBD = findViewById(R.id.Cboneyard);
        TextView HBD = findViewById(R.id.Hboneyard);
        TextView direction = findViewById(R.id.direction);

        hand_count = ((int) getIntent().getSerializableExtra("hand_count"));

        if(player_list.size() != 2)
        {
            //super.onCreate(savedInstanceState);
            //setContentView(R.layout.new_game_activity);
            // all controls will disappear and throw up a error picture
            prompt.setVisibility(prompt.GONE);
            first.setVisibility(first.GONE);
            second.setVisibility(second.GONE);
            B1.setVisibility(B1.GONE);
            B2.setVisibility(B2.GONE);
            B3.setVisibility(B3.GONE);
            B4.setVisibility(B4.GONE);
            B5.setVisibility(B5.GONE);
            B6.setVisibility(B6.GONE);
            W1.setVisibility(W1.GONE);
            W2.setVisibility(W2.GONE);
            W3.setVisibility(W3.GONE);
            W4.setVisibility(W4.GONE);
            W5.setVisibility(W5.GONE);
            W6.setVisibility(W6.GONE);
            BH1.setVisibility(BH1.GONE);
            BH2.setVisibility(BH2.GONE);
            BH3.setVisibility(BH3.GONE);
            BH4.setVisibility(BH4.GONE);
            BH5.setVisibility(BH5.GONE);
            BH6.setVisibility(BH6.GONE);
            WH1.setVisibility(WH1.GONE);
            WH2.setVisibility(WH2.GONE);
            WH3.setVisibility(WH3.GONE);
            WH4.setVisibility(WH4.GONE);
            WH5.setVisibility(WH5.GONE);
            WH6.setVisibility(WH6.GONE);
            Cscore.setVisibility(Cscore.GONE);
            Hscore.setVisibility(Hscore.GONE);
            Crounds_won.setVisibility(Crounds_won.GONE);
            Hrounds_won.setVisibility(Hrounds_won.GONE);
            HB.setVisibility(HB.GONE);
            CB.setVisibility(CB.GONE);
            HBD.setVisibility(HBD.GONE);
            CBD.setVisibility(CBD.GONE);
            String x = "ERROR: Currently there are ";
            x += Integer.toString(player_list.size());
            x += " players in the game there must be 2 players SYSTEM ABORT!";
            turn.setText(x);

            turn.setTextColor(turn.getContext().getColor(R.color.yellow));
            Error.setVisibility(Error.VISIBLE);
            return;
        }
        // player_list.add(new Human("Human", 'B'));
        if(next_round == false)
        {
        update_view();
        }

        if(hand_count == 1) {
            if (player_list.get(1).domino_value(player_list.get(1).get_at_hand_position(0)) > player_list.get(0).domino_value(player_list.get(0).get_at_hand_position(0))) {
                prompt.setOnClickListener(view -> {
                    direction.setText(player_list.get(1).get_name() + " goes first because it drew the bigger domino it drew " + player_list.get(1).get_at_hand_position(0) +" while " + player_list.get(0).get_name() + " drew " +player_list.get(0).get_at_hand_position(0));
                    direction.setVisibility(direction.VISIBLE);
                    prompt.setVisibility(prompt.GONE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                    Human_move_GUI();

                });
            } else if (player_list.get(1).domino_value(player_list.get(1).get_at_hand_position(0)) < player_list.get(0).domino_value(player_list.get(0).get_at_hand_position(0))) {
                prompt.setOnClickListener(view -> {
                    direction.setText(player_list.get(0).get_name() + " goes first because it drew the bigger domino it drew " + player_list.get(0).get_at_hand_position(0) +" while " + player_list.get(1).get_name() + " drew " +player_list.get(1).get_at_hand_position(0));
                    direction.setVisibility(direction.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(View ->
                    {
                        prompt.setVisibility(prompt.GONE);
                        Computer_move_GUI();
                    });
                });

            }
            else
            {
                return;
            }
        }
    }
    /** *********************************************************************
     Function Name: update_view
     Purpose: updates the GUI based on the parced player contents
     Parameters:
                none
     Return Value:
     none
     Algorithm:
     1. parse the game contents from ".getSerializableExtra" Game class and executes the round respectively
     2. add the contents to the players at their respective objects
     3. if turn is not 2 then determine the hand count and hand size respectively (this is when its loaded in from a file) and update the GUI
     to match its model
     ELSE
     4. its a new game and hand_count is one and all tiles in hands are visible and update the GUI
     to match its model
     Assistance Received: none
     ********************************************************************* */
    public void update_view()
    {
        String Hhand = ((String) getIntent().getSerializableExtra("Hhand"));
        String Chand = ((String) getIntent().getSerializableExtra("Chand"));
        String Cboneyard = ((String) getIntent().getSerializableExtra("Cboneyard"));
        String Hboneyard = ((String) getIntent().getSerializableExtra("Hboneyard"));
        String Hstack = ((String) getIntent().getSerializableExtra("Hstack"));
        String Cstack = ((String) getIntent().getSerializableExtra("Cstack"));
        int Human_score = ((int) getIntent().getSerializableExtra("Hscore"));
        int Computer_score = ((int) getIntent().getSerializableExtra("Cscore"));
        int Human_wins = ((int) getIntent().getSerializableExtra("Hrounds_won"));
        int Computer_wins = ((int) getIntent().getSerializableExtra("Crounds_won"));
        String Computer_stack = Cstack;
        String Human_stack = Hstack;
        String Computer_hand = Chand;
        String Human_hand = Hhand;
        String Computer_boneyard = Cboneyard;
        String Human_boneyard = Hboneyard;
        TextView first = findViewById(R.id.Player_one_name);
        TextView second = findViewById(R.id.Player_two_name);
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
        Button WH1 = findViewById(R.id.WH1);
        Button WH2 = findViewById(R.id.WH2);
        Button WH3 = findViewById(R.id.WH3);
        Button WH4 = findViewById(R.id.WH4);
        Button WH5 = findViewById(R.id.WH5);
        Button WH6 = findViewById(R.id.WH6);
        Button BH1 = findViewById(R.id.BH1);
        Button BH2 = findViewById(R.id.BH2);
        Button BH3 = findViewById(R.id.BH3);
        Button BH4 = findViewById(R.id.BH4);
        Button BH5 = findViewById(R.id.BH5);
        Button BH6 = findViewById(R.id.BH6);
        Button prompt = findViewById(R.id.prompt);
        ImageView Error = findViewById(R.id.Error);
        TextView turn = findViewById(R.id.turn);
        Button on_own = findViewById(R.id.on_own);
        Button help = findViewById(R.id.help);
        Button on_opponents = findViewById(R.id.on_opponents);
        TextView Cscore = findViewById(R.id.Cbone);
        TextView Hscore = findViewById(R.id.Hscore);
        TextView Crounds_won = findViewById(R.id.Crounds_won);
        TextView Hrounds_won = findViewById(R.id.Hrounds_won);
        Button CB = findViewById(R.id.CB);
        Button HB = findViewById(R.id.HB);
        TextView CBD = findViewById(R.id.Cboneyard);
        TextView HBD = findViewById(R.id.Hboneyard);
            for (int i = 0; i < (Cboneyard.length()/3); i++) {
                String bone = "";
                for (int k = 0; k < 3; k++) {
                    bone += Computer_boneyard.charAt(k);
                }
                player_list.get(0).add_to_bone_yard(bone);
                Computer_boneyard = Computer_boneyard.substring(3);

            }
            for (int i = 0; i < (Hboneyard.length()/3); i++) {
                String bone = "";
                for (int k = 0; k < 3; k++) {
                    bone += Human_boneyard.charAt(k);
                }
                player_list.get(1).add_to_bone_yard(bone);
                Human_boneyard = Human_boneyard.substring(3);

            }
            for (int i = 0; i < (Chand.length() / 3); i++) {
                String bone = "";
                for (int k = 0; k < 3; k++) {
                    bone += Computer_hand.charAt(k);
                }
                player_list.get(0).add_to_hand(bone);
                Computer_hand = Computer_hand.substring(3);

            }
            for (int i = 0; i < (Hhand.length() / 3); i++) {
                String bone = "";
                for (int k = 0; k < 3; k++) {
                    bone += Human_hand.charAt(k);
                }
                player_list.get(1).add_to_hand(bone);
                Human_hand = Human_hand.substring(3);

            }
            for (int i = 0; i < (Cstack.length() / 3); i++) {
                String bone = "";
                for (int k = 0; k < 3; k++) {
                    bone += Computer_stack.charAt(k);
                }
                player_list.get(0).add_to_stack(bone);
                Computer_stack = Computer_stack.substring(3);

            }
            for (int i = 0; i < (Hstack.length() / 3); i++) {
                String bone = "";
                for (int k = 0; k < 3; k++) {
                    bone += Human_stack.charAt(k);
                }
                player_list.get(1).add_to_stack(bone);
                Human_stack = Human_stack.substring(3);


            }
            player_list.get(0).set_score(Computer_score);
            player_list.get(1).set_score(Human_score);
        player_list.get(0).set_wins(Computer_wins);
        player_list.get(1).set_wins(Human_wins);

            Cscore.setText("Score: " + player_list.get(0).get_score());
            Hscore.setText("Score: " + player_list.get(1).get_score());
           // player_list.get(0).set_wins(Computer_wins);
            //player_list.get(1).set_wins(Human_wins);
            Crounds_won.setText("Rounds Won: " + player_list.get(0).get_wins());
            Hrounds_won.setText("Rounds Won: " + player_list.get(1).get_wins());
            first.setText(player_list.get(0).get_name());
            second.setText(player_list.get(1).get_name());
            int Turn = ((int) getIntent().getSerializableExtra("turn"));

        if(Turn != 2)
            {
                if(player_list.get(0).bone_yard_size()==0)
                {
                    CB.setVisibility(CB.GONE);
                    HB.setVisibility(HB.GONE);

                }
                else if(player_list.get(0).bone_yard_size()>0)
                {
                    CB.setText(player_list.get(0).get_at_boneyard_position(0));
                    draw_domino_picture(CB);
                    HB.setText(player_list.get(1).get_at_boneyard_position(0));
                    draw_domino_picture(HB);
                }
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
                B1.setText(player_list.get(1).get_at_stack_position(0));
                draw_domino_picture(B1);
                B2.setText(player_list.get(1).get_at_stack_position(1));
                draw_domino_picture(B2);
                B3.setText(player_list.get(1).get_at_stack_position(2));
                draw_domino_picture(B3);
                B4.setText(player_list.get(1).get_at_stack_position(3));
                draw_domino_picture(B4);
                B5.setText(player_list.get(1).get_at_stack_position(4));
                draw_domino_picture(B5);
                B6.setText(player_list.get(1).get_at_stack_position(5));
                draw_domino_picture(B6);
                if(player_list.get(0).hand_size() == 6)
                {
                    WH1.setVisibility(WH1.VISIBLE);
                    WH2.setVisibility(WH2.VISIBLE);
                    WH3.setVisibility(WH3.VISIBLE);
                    WH4.setVisibility(WH4.VISIBLE);
                    WH5.setVisibility(WH5.VISIBLE);
                    WH6.setVisibility(WH6.VISIBLE);
                    WH1.setText(player_list.get(0).get_at_hand_position(0));
                    draw_domino_picture(WH1);
                    WH2.setText(player_list.get(0).get_at_hand_position(1));
                    draw_domino_picture(WH2);
                    WH3.setText(player_list.get(0).get_at_hand_position(2));
                    draw_domino_picture(WH3);
                    WH4.setText(player_list.get(0).get_at_hand_position(3));
                    draw_domino_picture(WH4);
                    WH5.setText(player_list.get(0).get_at_hand_position(4));
                    draw_domino_picture(WH5);
                    WH6.setText(player_list.get(0).get_at_hand_position(5));
                    draw_domino_picture(WH6);
                }
                else if(player_list.get(0).hand_size() == 5)
                {
                    WH1.setVisibility(WH1.VISIBLE);
                    WH2.setVisibility(WH2.VISIBLE);
                    WH3.setVisibility(WH3.VISIBLE);
                    WH4.setVisibility(WH4.VISIBLE);
                    WH5.setVisibility(WH5.VISIBLE);
                    WH6.setVisibility(WH6.GONE);
                    WH1.setText(player_list.get(0).get_at_hand_position(0));
                    draw_domino_picture(WH1);
                    WH2.setText(player_list.get(0).get_at_hand_position(1));
                    draw_domino_picture(WH2);
                    WH3.setText(player_list.get(0).get_at_hand_position(2));
                    draw_domino_picture(WH3);
                    WH4.setText(player_list.get(0).get_at_hand_position(3));
                    draw_domino_picture(WH4);
                    WH5.setText(player_list.get(0).get_at_hand_position(4));
                    draw_domino_picture(WH5);
                }
                else if(player_list.get(0).hand_size() == 4)
                {
                    WH1.setVisibility(WH1.VISIBLE);
                    WH2.setVisibility(WH2.VISIBLE);
                    WH3.setVisibility(WH3.VISIBLE);
                    WH4.setVisibility(WH4.VISIBLE);
                    WH5.setVisibility(WH5.GONE);
                    WH6.setVisibility(WH6.GONE);
                    WH1.setText(player_list.get(0).get_at_hand_position(0));
                    draw_domino_picture(WH1);
                    WH2.setText(player_list.get(0).get_at_hand_position(1));
                    draw_domino_picture(WH2);
                    WH3.setText(player_list.get(0).get_at_hand_position(2));
                    draw_domino_picture(WH3);
                    WH4.setText(player_list.get(0).get_at_hand_position(3));
                    draw_domino_picture(WH4);

                }
                else if(player_list.get(0).hand_size() == 3)
                {
                    WH1.setVisibility(WH1.VISIBLE);
                    WH2.setVisibility(WH2.VISIBLE);
                    WH3.setVisibility(WH3.VISIBLE);
                    WH4.setVisibility(WH4.GONE);
                    WH5.setVisibility(WH5.GONE);
                    WH6.setVisibility(WH6.GONE);
                    WH1.setText(player_list.get(0).get_at_hand_position(0));
                    draw_domino_picture(WH1);
                    WH2.setText(player_list.get(0).get_at_hand_position(1));
                    draw_domino_picture(WH2);
                    WH3.setText(player_list.get(0).get_at_hand_position(2));
                    draw_domino_picture(WH3);


                }
                else if(player_list.get(0).hand_size() == 2)
                {
                    WH1.setVisibility(WH1.VISIBLE);
                    WH2.setVisibility(WH2.VISIBLE);
                    WH3.setVisibility(WH3.GONE);
                    WH4.setVisibility(WH4.GONE);
                    WH5.setVisibility(WH5.GONE);
                    WH6.setVisibility(WH6.GONE);
                    WH1.setText(player_list.get(0).get_at_hand_position(0));
                    draw_domino_picture(WH1);
                    WH2.setText(player_list.get(0).get_at_hand_position(1));
                    draw_domino_picture(WH2);



                }
                else if(player_list.get(0).hand_size() == 1)
                {
                    WH1.setVisibility(WH1.VISIBLE);
                    WH2.setVisibility(WH2.GONE);
                    WH3.setVisibility(WH3.GONE);
                    WH4.setVisibility(WH4.GONE);
                    WH5.setVisibility(WH5.GONE);
                    WH6.setVisibility(WH6.GONE);
                    WH1.setText(player_list.get(0).get_at_hand_position(0));
                    draw_domino_picture(WH1);

                }
                if(player_list.get(1).hand_size() == 6)
                {
                    BH1.setVisibility(BH1.VISIBLE);
                    BH2.setVisibility(BH2.VISIBLE);
                    BH3.setVisibility(BH3.VISIBLE);
                    BH4.setVisibility(BH4.VISIBLE);
                    BH5.setVisibility(BH5.VISIBLE);
                    BH6.setVisibility(BH6.VISIBLE);
                    BH1.setText(player_list.get(1).get_at_hand_position(0));
                    draw_domino_picture(BH1);
                    BH2.setText(player_list.get(1).get_at_hand_position(1));
                    draw_domino_picture(BH2);
                    BH3.setText(player_list.get(1).get_at_hand_position(2));
                    draw_domino_picture(BH3);
                    BH4.setText(player_list.get(1).get_at_hand_position(3));
                    draw_domino_picture(BH4);
                    BH5.setText(player_list.get(1).get_at_hand_position(4));
                    draw_domino_picture(BH5);
                    BH6.setText(player_list.get(1).get_at_hand_position(5));
                    draw_domino_picture(BH6);
                }
                else if(player_list.get(1).hand_size() == 5)
                {
                    BH1.setVisibility(BH1.VISIBLE);
                    BH2.setVisibility(BH2.VISIBLE);
                    BH3.setVisibility(BH3.VISIBLE);
                    BH4.setVisibility(BH4.VISIBLE);
                    BH5.setVisibility(BH5.VISIBLE);
                    BH6.setVisibility(BH6.GONE);
                    BH1.setText(player_list.get(1).get_at_hand_position(0));
                    draw_domino_picture(BH1);
                    BH2.setText(player_list.get(1).get_at_hand_position(1));
                    draw_domino_picture(BH2);
                    BH3.setText(player_list.get(1).get_at_hand_position(2));
                    draw_domino_picture(BH3);
                    BH4.setText(player_list.get(1).get_at_hand_position(3));
                    draw_domino_picture(BH4);
                    BH5.setText(player_list.get(1).get_at_hand_position(4));
                    draw_domino_picture(BH5);
                }
                else if(player_list.get(1).hand_size() == 4)
                {
                    BH1.setVisibility(BH1.VISIBLE);
                    BH2.setVisibility(BH2.VISIBLE);
                    BH3.setVisibility(BH3.VISIBLE);
                    BH4.setVisibility(BH4.VISIBLE);
                    BH5.setVisibility(BH5.GONE);
                    BH6.setVisibility(BH6.GONE);
                    BH1.setText(player_list.get(1).get_at_hand_position(0));
                    draw_domino_picture(BH1);
                    BH2.setText(player_list.get(1).get_at_hand_position(1));
                    draw_domino_picture(BH2);
                    BH3.setText(player_list.get(1).get_at_hand_position(2));
                    draw_domino_picture(BH3);
                    BH4.setText(player_list.get(1).get_at_hand_position(3));
                    draw_domino_picture(BH4);

                }
                else if(player_list.get(1).hand_size() == 3)
                {
                    BH1.setVisibility(BH1.VISIBLE);
                    BH2.setVisibility(BH2.VISIBLE);
                    BH3.setVisibility(BH3.VISIBLE);
                    BH4.setVisibility(BH4.GONE);
                    BH5.setVisibility(BH5.GONE);
                    BH6.setVisibility(BH6.GONE);
                    BH1.setText(player_list.get(1).get_at_hand_position(0));
                    draw_domino_picture(BH1);
                    BH2.setText(player_list.get(1).get_at_hand_position(1));
                    draw_domino_picture(BH2);
                    BH3.setText(player_list.get(1).get_at_hand_position(2));
                    draw_domino_picture(BH3);


                }
                else if(player_list.get(1).hand_size() == 2)
                {
                    BH1.setVisibility(BH1.VISIBLE);
                    BH2.setVisibility(BH2.VISIBLE);
                    BH3.setVisibility(BH3.GONE);
                    BH4.setVisibility(BH4.GONE);
                    BH5.setVisibility(BH5.GONE);
                    BH6.setVisibility(BH6.GONE);
                    BH1.setText(player_list.get(1).get_at_hand_position(0));
                    draw_domino_picture(BH1);
                    BH2.setText(player_list.get(1).get_at_hand_position(1));
                    draw_domino_picture(BH2);



                }
                else if(player_list.get(1).hand_size() == 1)
                {
                    BH1.setVisibility(BH1.VISIBLE);
                    BH2.setVisibility(BH2.GONE);
                    BH3.setVisibility(BH3.GONE);
                    BH4.setVisibility(BH4.GONE);
                    BH5.setVisibility(BH5.GONE);
                    BH6.setVisibility(BH6.GONE);
                    BH1.setText(player_list.get(1).get_at_hand_position(0));
                    draw_domino_picture(BH1);

                }
                if(Turn == 0)
                {

                    prompt.setOnClickListener(view -> {
                        TextView direction = findViewById(R.id.direction);
                        direction.setText( player_list.get(0).get_name() + " goes first because it was the first player loaded in the serialization file");

                        turn.setText("Turn: " + player_list.get(0).get_name());
                        prompt.setOnClickListener(View ->
                        {
                            prompt.setVisibility(prompt.GONE);
                            Computer_move_GUI();
                        });

                    });
                }
                else
                {
                    prompt.setOnClickListener(view -> {
                        TextView direction = findViewById(R.id.direction);
                        direction.setText( player_list.get(1).get_name() + " goes first because it was the first player loaded in the serialization file");
                        prompt.setVisibility(prompt.GONE);
                        turn.setText("Turn: " + player_list.get(1).get_name());
                        prompt.setOnClickListener(this::onClick);
                        Human_move_GUI();

                    });
                }
            return;
            }
            else {
            CB.setText(player_list.get(0).get_at_boneyard_position(0));
            draw_domino_picture(CB);
            HB.setText(player_list.get(1).get_at_boneyard_position(0));
            draw_domino_picture(HB);
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
            B1.setText(player_list.get(1).get_at_stack_position(0));
            draw_domino_picture(B1);
            B2.setText(player_list.get(1).get_at_stack_position(1));
            draw_domino_picture(B2);
            B3.setText(player_list.get(1).get_at_stack_position(2));
            draw_domino_picture(B3);
            B4.setText(player_list.get(1).get_at_stack_position(3));
            draw_domino_picture(B4);
            B5.setText(player_list.get(1).get_at_stack_position(4));
            draw_domino_picture(B5);
            B6.setText(player_list.get(1).get_at_stack_position(5));
            draw_domino_picture(B6);
            WH1.setText(player_list.get(0).get_at_hand_position(0));
            draw_domino_picture(WH1);
            WH2.setText(player_list.get(0).get_at_hand_position(1));
            draw_domino_picture(WH2);
            WH3.setText(player_list.get(0).get_at_hand_position(2));
            draw_domino_picture(WH3);
            WH4.setText(player_list.get(0).get_at_hand_position(3));
            draw_domino_picture(WH4);
            WH5.setText(player_list.get(0).get_at_hand_position(4));
            draw_domino_picture(WH5);
            WH6.setText(player_list.get(0).get_at_hand_position(5));
            draw_domino_picture(WH6);
            BH1.setText(player_list.get(1).get_at_hand_position(0));
            draw_domino_picture(BH1);
            BH2.setText(player_list.get(1).get_at_hand_position(1));
            draw_domino_picture(BH2);
            BH3.setText(player_list.get(1).get_at_hand_position(2));
            draw_domino_picture(BH3);
            BH4.setText(player_list.get(1).get_at_hand_position(3));
            draw_domino_picture(BH4);
            BH5.setText(player_list.get(1).get_at_hand_position(4));
            draw_domino_picture(BH5);
            BH6.setText(player_list.get(1).get_at_hand_position(5));
            draw_domino_picture(BH6);
            BH1.setVisibility(BH1.VISIBLE);
            BH2.setVisibility(BH2.VISIBLE);
            BH3.setVisibility(BH3.VISIBLE);
            BH4.setVisibility(BH4.VISIBLE);
            BH5.setVisibility(BH5.VISIBLE);
            BH6.setVisibility(BH6.VISIBLE);
            WH1.setVisibility(WH1.VISIBLE);
            WH2.setVisibility(WH2.VISIBLE);
            WH3.setVisibility(WH3.VISIBLE);
            WH4.setVisibility(WH4.VISIBLE);
            WH5.setVisibility(WH5.VISIBLE);
            WH6.setVisibility(WH6.VISIBLE);
        }
    }
    @Override
    /** *********************************************************************
     Function Name: onClick
     Purpose: determine the approiate response for a specified clicked button
     Parameters:
                 v the specified view passed by value
     Return Value:
     none
     Algorithm:
     1. parse the game contents from ".getSerializableExtra" Game class and executes the round respectively
     2. add the contents to the players at their respective objects
     3. if turn is not 2 then determine the hand count and hand size respectively (this is when its loaded in from a file) and update the GUI
     to match its model
     ELSE
     4. its a new game and hand_count is one and all tiles in hands are visible and update the GUI
     to match its model
     Assistance Received: none
     ********************************************************************* */
    public void onClick(View v)
    {
        Button prompt = findViewById(R.id.prompt);
        Button BH1 = findViewById(R.id.BH1);
        Button BH2 = findViewById(R.id.BH2);
        Button BH3 = findViewById(R.id.BH3);
        Button BH4 = findViewById(R.id.BH4);
        Button BH5 = findViewById(R.id.BH5);
        Button BH6 = findViewById(R.id.BH6);
        Button B1 = findViewById(R.id.B1);
        Button B2 = findViewById(R.id.B2);
        Button B3 = findViewById(R.id.B3);
        Button B4 = findViewById(R.id.B4);
        Button B5 = findViewById(R.id.B5);
        Button B6 = findViewById(R.id.B6);
        Button W1 = findViewById(R.id.W1);
        Button W2 = findViewById(R.id.W2);
        Button W3 = findViewById(R.id.W3);
        Button W4 = findViewById(R.id.W4);
        Button W5 = findViewById(R.id.W5);
        Button W6 = findViewById(R.id.W6);
        TextView turn = findViewById(R.id.turn);
        TextView direction = findViewById(R.id.direction);
        if (v.getId() == R.id.BH1) {

            player_list.get(grab).set_domino(BH1.getText().toString());

            BH1.setClickable(false);
            BH2.setClickable(false);
            BH3.setClickable(false);
            BH4.setClickable(false);
            BH5.setClickable(false);
            BH6.setClickable(false);
            if (move_type == true) {
                direction.setText("Please choose a stack on your opponent's side to stack " + player_list.get(grab).get_domino() + " onto ");
                W1.setClickable(true);
                W2.setClickable(true);
                W3.setClickable(true);
                W4.setClickable(true);
                W5.setClickable(true);
                W6.setClickable(true);
                W1.setOnClickListener(this::onClick);
                W2.setOnClickListener(this::onClick);
                W3.setOnClickListener(this::onClick);
                W4.setOnClickListener(this::onClick);
                W5.setOnClickListener(this::onClick);
                W6.setOnClickListener(this::onClick);
            } else {
                direction.setText("Please choose a stack on your side to stack " + player_list.get(grab).get_domino() + " onto ");
                B1.setClickable(true);
                B2.setClickable(true);
                B3.setClickable(true);
                B4.setClickable(true);
                B5.setClickable(true);
                B6.setClickable(true);
                B1.setOnClickListener(this::onClick);
                B2.setOnClickListener(this::onClick);
                B3.setOnClickListener(this::onClick);
                B4.setOnClickListener(this::onClick);
                B5.setOnClickListener(this::onClick);
                B6.setOnClickListener(this::onClick);
            }


        } else if (v.getId() == R.id.BH2) {

            player_list.get(grab).set_domino(BH2.getText().toString());

            BH1.setClickable(false);
            BH2.setClickable(false);
            BH3.setClickable(false);
            BH4.setClickable(false);
            BH5.setClickable(false);
            BH6.setClickable(false);
            if (move_type == true) {
                direction.setText("Please choose a stack on your opponent's side to stack " + player_list.get(grab).get_domino() + " onto ");
                W1.setClickable(true);
                W2.setClickable(true);
                W3.setClickable(true);
                W4.setClickable(true);
                W5.setClickable(true);
                W6.setClickable(true);
                W1.setOnClickListener(this::onClick);
                W2.setOnClickListener(this::onClick);
                W3.setOnClickListener(this::onClick);
                W4.setOnClickListener(this::onClick);
                W5.setOnClickListener(this::onClick);
                W6.setOnClickListener(this::onClick);
            } else {
                direction.setText("Please choose a stack on your side to stack " + player_list.get(grab).get_domino() + " onto ");
                B1.setClickable(true);
                B2.setClickable(true);
                B3.setClickable(true);
                B4.setClickable(true);
                B5.setClickable(true);
                B6.setClickable(true);
                B1.setOnClickListener(this::onClick);
                B2.setOnClickListener(this::onClick);
                B3.setOnClickListener(this::onClick);
                B4.setOnClickListener(this::onClick);
                B5.setOnClickListener(this::onClick);
                B6.setOnClickListener(this::onClick);
            }
        } else if (v.getId() == R.id.BH3) {

            player_list.get(grab).set_domino(BH3.getText().toString());
            BH1.setClickable(false);
            BH2.setClickable(false);
            BH3.setClickable(false);
            BH4.setClickable(false);
            BH5.setClickable(false);
            BH6.setClickable(false);
            if (move_type == true) {
                direction.setText("Please choose a stack on your opponent's side to stack " + player_list.get(grab).get_domino() + " onto ");
                W1.setClickable(true);
                W2.setClickable(true);
                W3.setClickable(true);
                W4.setClickable(true);
                W5.setClickable(true);
                W6.setClickable(true);
                W1.setOnClickListener(this::onClick);
                W2.setOnClickListener(this::onClick);
                W3.setOnClickListener(this::onClick);
                W4.setOnClickListener(this::onClick);
                W5.setOnClickListener(this::onClick);
                W6.setOnClickListener(this::onClick);
            } else {
                direction.setText("Please choose a stack on your side to stack " + player_list.get(grab).get_domino() + " onto ");
                B1.setClickable(true);
                B2.setClickable(true);
                B3.setClickable(true);
                B4.setClickable(true);
                B5.setClickable(true);
                B6.setClickable(true);
                B1.setOnClickListener(this::onClick);
                B2.setOnClickListener(this::onClick);
                B3.setOnClickListener(this::onClick);
                B4.setOnClickListener(this::onClick);
                B5.setOnClickListener(this::onClick);
                B6.setOnClickListener(this::onClick);
            }
        } else if (v.getId() == R.id.BH4) {

            player_list.get(grab).set_domino(BH4.getText().toString());

            BH1.setClickable(false);
            BH2.setClickable(false);
            BH3.setClickable(false);
            BH4.setClickable(false);
            BH5.setClickable(false);
            BH6.setClickable(false);
            if (move_type == true) {
                direction.setText("Please choose a stack on your opponent's side to stack " + player_list.get(grab).get_domino() + " onto ");
                W1.setClickable(true);
                W2.setClickable(true);
                W3.setClickable(true);
                W4.setClickable(true);
                W5.setClickable(true);
                W6.setClickable(true);
                W1.setOnClickListener(this::onClick);
                W2.setOnClickListener(this::onClick);
                W3.setOnClickListener(this::onClick);
                W4.setOnClickListener(this::onClick);
                W5.setOnClickListener(this::onClick);
                W6.setOnClickListener(this::onClick);
            } else {
                direction.setText("Please choose a stack on your side to stack " + player_list.get(grab).get_domino() + " onto ");
                B1.setClickable(true);
                B2.setClickable(true);
                B3.setClickable(true);
                B4.setClickable(true);
                B5.setClickable(true);
                B6.setClickable(true);
                B1.setOnClickListener(this::onClick);
                B2.setOnClickListener(this::onClick);
                B3.setOnClickListener(this::onClick);
                B4.setOnClickListener(this::onClick);
                B5.setOnClickListener(this::onClick);
                B6.setOnClickListener(this::onClick);
            }
        } else if (v.getId() == R.id.BH5) {

            player_list.get(grab).set_domino(BH5.getText().toString());

            BH1.setClickable(false);
            BH2.setClickable(false);
            BH3.setClickable(false);
            BH4.setClickable(false);
            BH5.setClickable(false);
            BH6.setClickable(false);
            if (move_type == true) {
                direction.setText("Please choose a stack on your opponent's side to stack " + player_list.get(grab).get_domino() + " onto ");
                W1.setClickable(true);
                W2.setClickable(true);
                W3.setClickable(true);
                W4.setClickable(true);
                W5.setClickable(true);
                W6.setClickable(true);
                W1.setOnClickListener(this::onClick);
                W2.setOnClickListener(this::onClick);
                W3.setOnClickListener(this::onClick);
                W4.setOnClickListener(this::onClick);
                W5.setOnClickListener(this::onClick);
                W6.setOnClickListener(this::onClick);
            } else {
                direction.setText("Please choose a stack on your side to stack " + player_list.get(grab).get_domino() + " onto ");
                B1.setClickable(true);
                B2.setClickable(true);
                B3.setClickable(true);
                B4.setClickable(true);
                B5.setClickable(true);
                B6.setClickable(true);
                B1.setOnClickListener(this::onClick);
                B2.setOnClickListener(this::onClick);
                B3.setOnClickListener(this::onClick);
                B4.setOnClickListener(this::onClick);
                B5.setOnClickListener(this::onClick);
                B6.setOnClickListener(this::onClick);
            }
        } else if (v.getId() == R.id.BH6) {

            player_list.get(grab).set_domino(BH6.getText().toString());

            BH1.setClickable(false);
            BH2.setClickable(false);
            BH3.setClickable(false);
            BH4.setClickable(false);
            BH5.setClickable(false);
            BH6.setClickable(false);
            if (move_type == true) {
                direction.setText("Please choose a stack on your opponent's side to stack " + player_list.get(grab).get_domino() + " onto ");
                W1.setClickable(true);
                W2.setClickable(true);
                W3.setClickable(true);
                W4.setClickable(true);
                W5.setClickable(true);
                W6.setClickable(true);
                W1.setOnClickListener(this::onClick);
                W2.setOnClickListener(this::onClick);
                W3.setOnClickListener(this::onClick);
                W4.setOnClickListener(this::onClick);
                W5.setOnClickListener(this::onClick);
                W6.setOnClickListener(this::onClick);
            } else {
                direction.setText("Please choose a stack on your side to stack " + player_list.get(grab).get_domino() + " onto ");
                B1.setClickable(true);
                B2.setClickable(true);
                B3.setClickable(true);
                B4.setClickable(true);
                B5.setClickable(true);
                B6.setClickable(true);
                B1.setOnClickListener(this::onClick);
                B2.setOnClickListener(this::onClick);
                B3.setOnClickListener(this::onClick);
                B4.setOnClickListener(this::onClick);
                B5.setOnClickListener(this::onClick);
                B6.setOnClickListener(this::onClick);
            }
        } else if (v.getId() == R.id.B1) {
            if (player_list.get(1).determine_legal_placement(B1.getText().toString()) == true) {
                if (player_list.get(1).get_domino() == BH1.getText().toString()) {
                    BH1.setVisibility(BH1.GONE);
                    player_list.get(1).cover(B1.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B1.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B1);
                    Human_change_GUI(BH6, B1);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH2.getText().toString()) {
                    BH2.setVisibility(BH2.GONE);
                    player_list.get(1).cover(B1.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    Human_change_GUI(BH2, B1);
                    B1.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B1);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH3.getText().toString()) {
                    BH3.setVisibility(BH3.GONE);
                    player_list.get(1).cover(B1.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B1.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B1);
                    Human_change_GUI(BH3, B1);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH4.getText().toString()) {
                    BH4.setVisibility(BH4.GONE);
                    player_list.get(1).cover(B1.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B1.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B1);
                    Human_change_GUI(BH4, B1);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH5.getText().toString()) {
                    BH5.setVisibility(BH5.GONE);
                    player_list.get(1).cover(B1.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B1.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B1);
                    Human_change_GUI(BH5, B1);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH6.getText().toString()) {
                    BH6.setVisibility(BH6.GONE);
                    player_list.get(1).cover(B1.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B1.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B1);
                    Human_change_GUI(BH6, B1);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                }

            } else {
                direction.setText("invalid entry please try again play from the hand again");
                BH1.setClickable(true);
                BH2.setClickable(true);
                BH3.setClickable(true);
                BH4.setClickable(true);
                BH5.setClickable(true);
                BH6.setClickable(true);
                BH1.setOnClickListener(this::onClick);
                BH2.setOnClickListener(this::onClick);
                BH3.setOnClickListener(this::onClick);
                BH4.setOnClickListener(this::onClick);
                BH5.setOnClickListener(this::onClick);
                BH6.setOnClickListener(this::onClick);
            }
        } else if (v.getId() == R.id.B2) {
            if (player_list.get(1).determine_legal_placement(B2.getText().toString()) == true) {
                if (player_list.get(1).get_domino() == BH1.getText().toString()) {
                    BH1.setVisibility(BH1.GONE);
                    player_list.get(1).cover(B2.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B2.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B2);
                    Human_change_GUI(BH1, B2);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH2.getText().toString()) {
                    BH2.setVisibility(BH2.GONE);
                    player_list.get(1).cover(B2.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B2.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B2);
                    Human_change_GUI(BH2, B2);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH3.getText().toString()) {
                    BH3.setVisibility(BH3.GONE);
                    player_list.get(1).cover(B2.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B2.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B2);
                    Human_change_GUI(BH3, B2);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH4.getText().toString()) {
                    BH4.setVisibility(BH4.GONE);
                    player_list.get(1).cover(B2.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B2.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B2);
                    Human_change_GUI(BH4, B2);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH5.getText().toString()) {
                    BH5.setVisibility(BH5.GONE);
                    player_list.get(1).cover(B2.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B2.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B2);
                    Human_change_GUI(BH5, B2);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH6.getText().toString()) {
                    BH6.setVisibility(BH6.GONE);
                    player_list.get(1).cover(B2.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B2.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B2);
                    Human_change_GUI(BH6, B2);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                }

            } else {
                direction.setText("invalid entry please try again play from the hand again");
                BH1.setClickable(true);
                BH2.setClickable(true);
                BH3.setClickable(true);
                BH4.setClickable(true);
                BH5.setClickable(true);
                BH6.setClickable(true);
                BH1.setOnClickListener(this::onClick);
                BH2.setOnClickListener(this::onClick);
                BH3.setOnClickListener(this::onClick);
                BH4.setOnClickListener(this::onClick);
                BH5.setOnClickListener(this::onClick);
                BH6.setOnClickListener(this::onClick);
            }
        } else if (v.getId() == R.id.B3) {
            if (player_list.get(1).determine_legal_placement(B3.getText().toString()) == true) {
                if (player_list.get(1).get_domino() == BH1.getText().toString()) {
                    BH1.setVisibility(BH1.GONE);
                    player_list.get(1).cover(B3.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B3.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B3);
                    Human_change_GUI(BH1, B3);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH2.getText().toString()) {
                    BH2.setVisibility(BH2.GONE);
                    player_list.get(1).cover(B3.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B3.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B3);
                    Human_change_GUI(BH2, B3);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH3.getText().toString()) {
                    BH3.setVisibility(BH3.GONE);
                    player_list.get(1).cover(B3.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B3.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B3);
                    Human_change_GUI(BH3, B3);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH4.getText().toString()) {
                    BH4.setVisibility(BH4.GONE);
                    player_list.get(1).cover(B3.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B3.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B3);
                    Human_change_GUI(BH4, B3);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH5.getText().toString()) {
                    BH5.setVisibility(BH5.GONE);
                    player_list.get(1).cover(B3.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B3.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B3);
                    Human_change_GUI(BH5, B3);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH6.getText().toString()) {
                    BH6.setVisibility(BH6.GONE);
                    player_list.get(1).cover(B3.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B3.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B3);
                    Human_change_GUI(BH6, B3);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                }

            } else {
                direction.setText("invalid entry please try again play from the hand again");
                BH1.setClickable(true);
                BH2.setClickable(true);
                BH3.setClickable(true);
                BH4.setClickable(true);
                BH5.setClickable(true);
                BH6.setClickable(true);
                BH1.setOnClickListener(this::onClick);
                BH2.setOnClickListener(this::onClick);
                BH3.setOnClickListener(this::onClick);
                BH4.setOnClickListener(this::onClick);
                BH5.setOnClickListener(this::onClick);
                BH6.setOnClickListener(this::onClick);
            }

        } else if (v.getId() == R.id.B4) {
            if (player_list.get(1).determine_legal_placement(B4.getText().toString()) == true) {
                if (player_list.get(1).get_domino() == BH1.getText().toString()) {
                    BH1.setVisibility(BH1.GONE);
                    player_list.get(1).cover(B4.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B4.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B4);
                    Human_change_GUI(BH1, B4);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH2.getText().toString()) {
                    BH2.setVisibility(BH2.GONE);
                    player_list.get(1).cover(B4.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B4.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B4);
                    Human_change_GUI(BH2, B4);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH3.getText().toString()) {
                    BH3.setVisibility(BH3.GONE);
                    player_list.get(1).cover(B4.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B4.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B4);
                    Human_change_GUI(BH3, B4);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH4.getText().toString()) {
                    BH4.setVisibility(BH4.GONE);
                    player_list.get(1).cover(B4.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B4.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B4);
                    Human_change_GUI(BH4, B4);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH5.getText().toString()) {
                    BH5.setVisibility(BH5.GONE);
                    player_list.get(1).cover(B4.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B4.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B4);
                    Human_change_GUI(BH5, B4);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH6.getText().toString()) {
                    BH6.setVisibility(BH6.GONE);
                    player_list.get(1).cover(B4.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B4.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B4);
                    Human_change_GUI(BH6, B4);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }

            } else {
                direction.setText("invalid entry please try again play from the hand again");
                BH1.setClickable(true);
                BH2.setClickable(true);
                BH3.setClickable(true);
                BH4.setClickable(true);
                BH5.setClickable(true);
                BH6.setClickable(true);
                BH1.setOnClickListener(this::onClick);
                BH2.setOnClickListener(this::onClick);
                BH3.setOnClickListener(this::onClick);
                BH4.setOnClickListener(this::onClick);
                BH5.setOnClickListener(this::onClick);
                BH6.setOnClickListener(this::onClick);
            }
        } else if (v.getId() == R.id.B5) {
            if (player_list.get(1).determine_legal_placement(B5.getText().toString()) == true) {
                if (player_list.get(1).get_domino() == BH1.getText().toString()) {
                    BH1.setVisibility(BH1.GONE);
                    player_list.get(1).cover(B5.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B5.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B5);
                    Human_change_GUI(BH1, B5);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH2.getText().toString()) {
                    BH2.setVisibility(BH2.GONE);
                    player_list.get(1).cover(B5.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B5.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B5);
                    Human_change_GUI(BH2, B5);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH3.getText().toString()) {
                    BH3.setVisibility(BH3.GONE);
                    player_list.get(1).cover(B5.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B5.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B5);
                    Human_change_GUI(BH3, B5);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH4.getText().toString()) {
                    BH4.setVisibility(BH4.GONE);
                    player_list.get(1).cover(B5.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B5.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B5);
                    Human_change_GUI(BH4, B5);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH5.getText().toString()) {
                    BH5.setVisibility(BH5.GONE);
                    player_list.get(1).cover(B5.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B5.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B5);
                    Human_change_GUI(BH5, B5);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH6.getText().toString()) {
                    BH6.setVisibility(BH6.GONE);
                    player_list.get(1).cover(B5.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B5.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B5);
                    Human_change_GUI(BH6, B5);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }

            } else {
                direction.setText("invalid entry please try again play from the hand again");
                BH1.setClickable(true);
                BH2.setClickable(true);
                BH3.setClickable(true);
                BH4.setClickable(true);
                BH5.setClickable(true);
                BH6.setClickable(true);
                BH1.setOnClickListener(this::onClick);
                BH2.setOnClickListener(this::onClick);
                BH3.setOnClickListener(this::onClick);
                BH4.setOnClickListener(this::onClick);
                BH5.setOnClickListener(this::onClick);
                BH6.setOnClickListener(this::onClick);
            }
        } else if (v.getId() == R.id.B6) {
            if (player_list.get(1).determine_legal_placement(B6.getText().toString()) == true) {
                if (player_list.get(1).get_domino() == BH1.getText().toString()) {
                    BH1.setVisibility(BH1.GONE);
                    player_list.get(1).cover(B6.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B6.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B6);
                    Human_change_GUI(BH1, B6);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH2.getText().toString()) {
                    BH2.setVisibility(BH2.GONE);
                    player_list.get(1).cover(B6.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B6.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B6);
                    Human_change_GUI(BH2, B6);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH3.getText().toString()) {
                    BH3.setVisibility(BH3.GONE);
                    player_list.get(1).cover(B6.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B6.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B6);
                    Human_change_GUI(BH3, B6);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH4.getText().toString()) {
                    BH4.setVisibility(BH4.GONE);
                    player_list.get(1).cover(B6.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B6.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B6);
                    Human_change_GUI(BH4, B6);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                } else if (player_list.get(1).get_domino() == BH5.getText().toString()) {
                    BH5.setVisibility(BH5.GONE);
                    player_list.get(1).cover(B6.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B6.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B6);
                    Human_change_GUI(BH5, B6);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                } else if (player_list.get(1).get_domino() == BH6.getText().toString()) {
                    BH6.setVisibility(BH6.GONE);
                    player_list.get(1).cover(B6.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(1).get_domino());
                    B6.setText(player_list.get(1).get_domino());
                    draw_domino_picture(B6);
                    Human_change_GUI(BH6, B6);
                    B1.setClickable(false);
                    B2.setClickable(false);
                    B3.setClickable(false);
                    B4.setClickable(false);
                    B5.setClickable(false);
                    B6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }

            }
            else {
                direction.setText("invalid entry please try again play from the hand again");
                BH1.setClickable(true);
                BH2.setClickable(true);
                BH3.setClickable(true);
                BH4.setClickable(true);
                BH5.setClickable(true);
                BH6.setClickable(true);
                BH1.setOnClickListener(this::onClick);
                BH2.setOnClickListener(this::onClick);
                BH3.setOnClickListener(this::onClick);
                BH4.setOnClickListener(this::onClick);
                BH5.setOnClickListener(this::onClick);
                BH6.setOnClickListener(this::onClick);

            }
        }
        else if (v.getId() == R.id.W1) {


            if (player_list.get(0).determine_legal_placement(W1.getText().toString()) == true)
            {
                if (player_list.get(0).get_domino() == BH1.getText().toString())
                {
                    BH1.setVisibility(BH1.GONE);
                    player_list.get(0).cover(W1.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W1.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W1);
                    Human_change_GUI(BH1, W1);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH2.getText().toString())
                {
                    BH2.setVisibility(BH2.GONE);
                    player_list.get(0).cover(W1.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W1.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W1);
                    Human_change_GUI(BH2, W1);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH3.getText().toString())
                {
                    BH3.setVisibility(BH3.GONE);
                    player_list.get(0).cover(W1.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W1.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W1);
                    Human_change_GUI(BH3, W1);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH4.getText().toString())
                {
                    BH4.setVisibility(BH4.GONE);
                    player_list.get(0).cover(W1.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W1.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W1);
                    Human_change_GUI(BH4, W1);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH5.getText().toString())
                {
                    BH5.setVisibility(BH5.GONE);
                    player_list.get(0).cover(W1.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W1.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W1);
                    Human_change_GUI(BH5, W1);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH6.getText().toString())
                {
                    BH6.setVisibility(BH6.GONE);
                    player_list.get(0).cover(W1.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W1.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W1);
                    Human_change_GUI(BH6, W1);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else
                {
                    direction.setText("invalid entry please try again play from the hand again");
                    BH1.setClickable(true);
                    BH2.setClickable(true);
                    BH3.setClickable(true);
                    BH4.setClickable(true);
                    BH5.setClickable(true);
                    BH6.setClickable(true);
                    W1.setOnClickListener(this::onClick);
                    W2.setOnClickListener(this::onClick);
                    W3.setOnClickListener(this::onClick);
                    W4.setOnClickListener(this::onClick);
                    W5.setOnClickListener(this::onClick);
                    W6.setOnClickListener(this::onClick);
                }
            }
            else
            {
                direction.setText("invalid entry please try again play from the hand again");
                BH1.setClickable(true);
                BH2.setClickable(true);
                BH3.setClickable(true);
                BH4.setClickable(true);
                BH5.setClickable(true);
                BH6.setClickable(true);
                BH1.setOnClickListener(this::onClick);
                BH2.setOnClickListener(this::onClick);
                BH3.setOnClickListener(this::onClick);
                BH4.setOnClickListener(this::onClick);
                BH5.setOnClickListener(this::onClick);
                BH6.setOnClickListener(this::onClick);

            }
        }
        else if (v.getId() == R.id.W2) {


            if (player_list.get(0).determine_legal_placement(W2.getText().toString()) == true)
            {
                if (player_list.get(0).get_domino() == BH1.getText().toString())
                {
                    BH1.setVisibility(BH1.GONE);
                    player_list.get(0).cover(W2.getText().toString());
                    W2.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W2);
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    Human_change_GUI(BH1, W2);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH2.getText().toString())
                {
                    BH2.setVisibility(BH2.GONE);
                    player_list.get(0).cover(W2.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W2.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W2);
                    Human_change_GUI(BH2, W2);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH3.getText().toString())
                {
                    BH3.setVisibility(BH3.GONE);
                    player_list.get(0).cover(W2.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W2.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W2);
                    Human_change_GUI(BH3, W2);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH4.getText().toString())
                {
                    BH4.setVisibility(BH4.GONE);
                    player_list.get(0).cover(W2.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W2.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W2);
                    Human_change_GUI(BH4, W2);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH5.getText().toString())
                {
                    BH5.setVisibility(BH5.GONE);
                    player_list.get(0).cover(W2.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W2.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W2);
                    Human_change_GUI(BH5, W2);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH6.getText().toString())
                {
                    BH6.setVisibility(BH6.GONE);
                    player_list.get(0).cover(W2.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W2.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W2);
                    Human_change_GUI(BH6, W2);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else
                {
                    direction.setText("invalid entry please try again play from the hand again");
                    BH1.setClickable(true);
                    BH2.setClickable(true);
                    BH3.setClickable(true);
                    BH4.setClickable(true);
                    BH5.setClickable(true);
                    BH6.setClickable(true);
                    W1.setOnClickListener(this::onClick);
                    W2.setOnClickListener(this::onClick);
                    W3.setOnClickListener(this::onClick);
                    W4.setOnClickListener(this::onClick);
                    W5.setOnClickListener(this::onClick);
                    W6.setOnClickListener(this::onClick);
                }
            }
            else
            {
                direction.setText("invalid entry please try again play from the hand again");
                BH1.setClickable(true);
                BH2.setClickable(true);
                BH3.setClickable(true);
                BH4.setClickable(true);
                BH5.setClickable(true);
                BH6.setClickable(true);
                BH1.setOnClickListener(this::onClick);
                BH2.setOnClickListener(this::onClick);
                BH3.setOnClickListener(this::onClick);
                BH4.setOnClickListener(this::onClick);
                BH5.setOnClickListener(this::onClick);
                BH6.setOnClickListener(this::onClick);

            }
        }
        else if (v.getId() == R.id.W3) {


            if (player_list.get(0).determine_legal_placement(W3.getText().toString()) == true)
            {
                if (player_list.get(0).get_domino() == BH1.getText().toString())
                {
                    BH1.setVisibility(BH1.GONE);
                    player_list.get(0).cover(W3.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W3.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W3);
                    Human_change_GUI(BH1, W3);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH2.getText().toString())
                {
                    BH2.setVisibility(BH2.GONE);
                    player_list.get(0).cover(W3.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W3.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W3);
                    Human_change_GUI(BH2, W3);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH3.getText().toString())
                {
                    BH3.setVisibility(BH3.GONE);
                    player_list.get(0).cover(W3.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W3.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W3);
                    Human_change_GUI(BH3, W3);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH4.getText().toString())
                {
                    BH4.setVisibility(BH4.GONE);
                    player_list.get(0).cover(W3.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W3.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W3);
                    Human_change_GUI(BH4, W3);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH5.getText().toString())
                {
                    BH5.setVisibility(BH5.GONE);
                    player_list.get(0).cover(W3.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W3.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W3);
                    Human_change_GUI(BH5, W3);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH6.getText().toString())
                {
                    BH6.setVisibility(BH6.GONE);
                    player_list.get(0).cover(W3.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W3.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W3);
                    Human_change_GUI(BH6, W3);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else
                {
                    direction.setText("invalid entry please try again play from the hand again");
                    BH1.setClickable(true);
                    BH2.setClickable(true);
                    BH3.setClickable(true);
                    BH4.setClickable(true);
                    BH5.setClickable(true);
                    BH6.setClickable(true);
                    W1.setOnClickListener(this::onClick);
                    W2.setOnClickListener(this::onClick);
                    W3.setOnClickListener(this::onClick);
                    W4.setOnClickListener(this::onClick);
                    W5.setOnClickListener(this::onClick);
                    W6.setOnClickListener(this::onClick);
                }
            }
            else
            {
                direction.setText("invalid entry please try again play from the hand again");
                BH1.setClickable(true);
                BH2.setClickable(true);
                BH3.setClickable(true);
                BH4.setClickable(true);
                BH5.setClickable(true);
                BH6.setClickable(true);
                BH1.setOnClickListener(this::onClick);
                BH2.setOnClickListener(this::onClick);
                BH3.setOnClickListener(this::onClick);
                BH4.setOnClickListener(this::onClick);
                BH5.setOnClickListener(this::onClick);
                BH6.setOnClickListener(this::onClick);

            }
        }
        else if (v.getId() == R.id.W4) {


            if (player_list.get(0).determine_legal_placement(W4.getText().toString()) == true)
            {
                if (player_list.get(0).get_domino() == BH1.getText().toString())
                {
                    BH1.setVisibility(BH1.GONE);
                    player_list.get(0).cover(W4.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W4.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W4);
                    Human_change_GUI(BH1, W4);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH2.getText().toString())
                {
                    BH2.setVisibility(BH2.GONE);
                    player_list.get(0).cover(W4.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W4.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W4);
                    Human_change_GUI(BH2, W4);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH3.getText().toString())
                {
                    BH3.setVisibility(BH3.GONE);
                    player_list.get(0).cover(W4.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W4.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W4);
                    Human_change_GUI(BH3, W4);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH4.getText().toString())
                {
                    BH4.setVisibility(BH4.GONE);
                    player_list.get(0).cover(W4.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W4.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W4);
                    Human_change_GUI(BH4, W4);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                }
                else if (player_list.get(0).get_domino() == BH5.getText().toString())
                {
                    BH5.setVisibility(BH5.GONE);
                    player_list.get(0).cover(W4.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W4.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W4);
                    Human_change_GUI(BH5, W4);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else if (player_list.get(0).get_domino() == BH6.getText().toString())
                {
                    BH6.setVisibility(BH6.GONE);
                    player_list.get(0).cover(W4.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W4.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W4);
                    Human_change_GUI(BH6, W4);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);

                }
                else
                {
                    direction.setText("invalid entry please try again play from the hand again");
                    BH1.setClickable(true);
                    BH2.setClickable(true);
                    BH3.setClickable(true);
                    BH4.setClickable(true);
                    BH5.setClickable(true);
                    BH6.setClickable(true);
                    W1.setOnClickListener(this::onClick);
                    W2.setOnClickListener(this::onClick);
                    W3.setOnClickListener(this::onClick);
                    W4.setOnClickListener(this::onClick);
                    W5.setOnClickListener(this::onClick);
                    W6.setOnClickListener(this::onClick);
                }
            }
            else
            {
                direction.setText("invalid entry please try again play from the hand again");
                BH1.setClickable(true);
                BH2.setClickable(true);
                BH3.setClickable(true);
                BH4.setClickable(true);
                BH5.setClickable(true);
                BH6.setClickable(true);
                BH1.setOnClickListener(this::onClick);
                BH2.setOnClickListener(this::onClick);
                BH3.setOnClickListener(this::onClick);
                BH4.setOnClickListener(this::onClick);
                BH5.setOnClickListener(this::onClick);
                BH6.setOnClickListener(this::onClick);

            }
        }
        else if (v.getId() == R.id.W5) {


            if (player_list.get(0).determine_legal_placement(W5.getText().toString()) == true)
            {
                if (player_list.get(0).get_domino() == BH1.getText().toString())
                {
                    BH1.setVisibility(BH1.GONE);
                    player_list.get(0).cover(W5.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W5.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W5);
                    Human_change_GUI(BH1, W5);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                }
                else if (player_list.get(0).get_domino() == BH2.getText().toString())
                {
                    BH2.setVisibility(BH2.GONE);
                    player_list.get(0).cover(W5.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W5.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W5);
                    Human_change_GUI(BH2, W5);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                }
                else if (player_list.get(0).get_domino() == BH3.getText().toString()) {
                    BH3.setVisibility(BH3.GONE);
                    player_list.get(0).cover(W5.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W5.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W5);
                    Human_change_GUI(BH3, W5);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                }
                else if (player_list.get(0).get_domino() == BH4.getText().toString())
                {
                    BH4.setVisibility(BH4.GONE);
                    player_list.get(0).cover(W5.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W5.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W5);
                    Human_change_GUI(BH4, W5);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                }
                else if (player_list.get(0).get_domino() == BH5.getText().toString())
                {
                    BH5.setVisibility(BH5.GONE);
                    player_list.get(0).cover(W5.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W5.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W5);
                    Human_change_GUI(BH5, W5);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                }
                else if (player_list.get(0).get_domino() == BH6.getText().toString())
                {
                    BH6.setVisibility(BH6.GONE);
                    player_list.get(0).cover(W5.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W5.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W5);
                    Human_change_GUI(BH6, W5);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                }
                else
                {
                    direction.setText("invalid entry please try again play from the hand again");
                    BH1.setClickable(true);
                    BH2.setClickable(true);
                    BH3.setClickable(true);
                    BH4.setClickable(true);
                    BH5.setClickable(true);
                    BH6.setClickable(true);
                    W1.setOnClickListener(this::onClick);
                    W2.setOnClickListener(this::onClick);
                    W3.setOnClickListener(this::onClick);
                    W4.setOnClickListener(this::onClick);
                    W5.setOnClickListener(this::onClick);
                    W6.setOnClickListener(this::onClick);
                }
            }
            else
            {
                direction.setText("invalid entry please try again play from the hand again");
                BH1.setClickable(true);
                BH2.setClickable(true);
                BH3.setClickable(true);
                BH4.setClickable(true);
                BH5.setClickable(true);
                BH6.setClickable(true);
                BH1.setOnClickListener(this::onClick);
                BH2.setOnClickListener(this::onClick);
                BH3.setOnClickListener(this::onClick);
                BH4.setOnClickListener(this::onClick);
                BH5.setOnClickListener(this::onClick);
                BH6.setOnClickListener(this::onClick);

            }
        }
        else if (v.getId() == R.id.W6)
        {
            if (player_list.get(0).determine_legal_placement(W6.getText().toString()) == true)
            {
                if (player_list.get(0).get_domino() == BH1.getText().toString())
                {
                    BH1.setVisibility(BH1.GONE);
                    player_list.get(0).cover(W6.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W6.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W6);
                    Human_change_GUI(BH1, W6);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                }
                else if (player_list.get(0).get_domino() == BH2.getText().toString())
                {
                    BH2.setVisibility(BH2.GONE);
                    player_list.get(0).cover(W6.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W6.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W6);
                    Human_change_GUI(BH2, W6);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                }
                else if (player_list.get(0).get_domino() == BH3.getText().toString())
                {
                    BH3.setVisibility(BH3.GONE);
                    player_list.get(0).cover(W6.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W6.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W6);
                    Human_change_GUI(BH3, W6);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                }
                else if (player_list.get(0).get_domino() == BH4.getText().toString())
                {
                    BH4.setVisibility(BH4.GONE);
                    player_list.get(0).cover(W6.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W6.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W6);
                    Human_change_GUI(BH4, W6);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                }
                else if (player_list.get(0).get_domino() == BH5.getText().toString())
                {
                    BH5.setVisibility(BH5.GONE);
                    player_list.get(0).cover(W6.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W6.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W6);
                    Human_change_GUI(BH5, W6);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);
                }
                else if (player_list.get(0).get_domino() == BH6.getText().toString())
                {
                    BH6.setVisibility(BH6.GONE);
                    player_list.get(0).cover(W6.getText().toString());
                    player_list.get(1).draw_domino_from_hand(player_list.get(0).get_domino());
                    W6.setText(player_list.get(0).get_domino());
                    draw_domino_picture(W6);
                    Human_change_GUI(BH6, W6);
                    W1.setClickable(false);
                    W2.setClickable(false);
                    W3.setClickable(false);
                    W4.setClickable(false);
                    W5.setClickable(false);
                    W6.setClickable(false);
                    prompt.setClickable(true);
                    prompt.setVisibility(prompt.VISIBLE);
                    turn.setText("Turn: " + player_list.get(0).get_name());
                    prompt.setOnClickListener(this::onClick);


                }
                else
                {
                    direction.setText("invalid entry please try again play from the hand again");
                    BH1.setClickable(true);
                    BH2.setClickable(true);
                    BH3.setClickable(true);
                    BH4.setClickable(true);
                    BH5.setClickable(true);
                    BH6.setClickable(true);
                    W1.setOnClickListener(this::onClick);
                    W2.setOnClickListener(this::onClick);
                    W3.setOnClickListener(this::onClick);
                    W4.setOnClickListener(this::onClick);
                    W5.setOnClickListener(this::onClick);
                    W6.setOnClickListener(this::onClick);
                }
            }
            else
            {
                direction.setText("invalid entry please try again play from the hand again");
                BH1.setClickable(true);
                BH2.setClickable(true);
                BH3.setClickable(true);
                BH4.setClickable(true);
                BH5.setClickable(true);
                BH6.setClickable(true);
                BH1.setOnClickListener(this::onClick);
                BH2.setOnClickListener(this::onClick);
                BH3.setOnClickListener(this::onClick);
                BH4.setOnClickListener(this::onClick);
                BH5.setOnClickListener(this::onClick);
                BH6.setOnClickListener(this::onClick);

            }
        }
        else if (v.getId() == R.id.prompt)
        {
            prompt.setVisibility(prompt.GONE);


            if(counter == 0)
            {
                Computer_move_GUI();
            }
            else
            {
                Human_move_GUI();
            }

        }
    }
    /** *********************************************************************
     Function Name: Human_change_GUI
     Purpose: (HELPER) Update the GUI when the Human makes a move to match its model respectively
     Parameters:
                 a the original domino GUI button id passed by value
                 b the domino GUI button that will be updated appropriately id passed by value
     Return Value:
     none
     Algorithm:
                 1. if the first character of the text for a is 'B' then make b the same text color and background color (black)
                 and then draw the approiate domino picture
                 ELSE
                 2. make b text and background (white) and then draw the approiate domino picture
                 3. return
     Assistance Received:   used this to change the button color without using .xml
                            https://www.themebin.com/how-to-change-the-background-tint-of-an-android-view-programmatically/
     ********************************************************************* */
    public void Human_change_GUI(Button a, Button b)
    {
        Button prompt = findViewById(R.id.prompt);
        ImageView Error = findViewById(R.id.Error);
        TextView turn = findViewById(R.id.turn);
        if(a.getText().toString().charAt(0) == 'B')
        {
            b.setBackgroundTintList(ColorStateList.valueOf( Color.BLACK));
            b.setTextColor(Color.BLACK);
            draw_domino_picture(b);
        }
        else
        {
            b.setBackgroundTintList(ColorStateList.valueOf( Color.WHITE));
            b.setTextColor(Color.WHITE);
            draw_domino_picture(b);
        }
        return;
    }
    /** *********************************************************************
     Function Name: Computer_change_GUI
     Purpose: (HELPER) Update the GUI when the Computer makes a move to match its model respectively
     Parameters:
                a the domino GUI button that will be updated appropriately id passed by value
     Return Value:
     none
     Algorithm:
     1. if the first character of the text for a is 'B' then make b the same text color and background color (black)
     and then draw the approiate domino picture
     ELSE
     2. make b text and background (white) and then draw the approiate domino picture
     3. return
     Assistance Received:   used this to change the button color without using .xml
     https://www.themebin.com/how-to-change-the-background-tint-of-an-android-view-programmatically/
     ********************************************************************* */
    public void Computer_change_GUI(Button a)
    {
        if(a.getText().toString().charAt(0) == 'B')
        {
            a.setBackgroundTintList(ColorStateList.valueOf( Color.BLACK));
            a.setTextColor(Color.BLACK);
            draw_domino_picture(a);
        }
        else
        {
            a.setBackgroundTintList(ColorStateList.valueOf( Color.WHITE));
            a.setTextColor(Color.WHITE);
            draw_domino_picture(a);
        }
        return;
    }
    /** *********************************************************************
     Function Name: Computer_move_GUI
     Purpose: (MAIN CONTROLLER) Update the GUI when the Computer makes a move to match its model respectively
     Parameters:
     none
     Return Value:
     none
     Algorithm:
     1. model makes a move and then updates the domino GUI buttons to the correct pieces to match the model
     Assistance Received:   none
     ********************************************************************* */
    public void Computer_move_GUI()
    {
        counter = 1;
        TextView first = findViewById(R.id.Player_one_name);
        TextView second = findViewById(R.id.Player_two_name);
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
        Button WH1 = findViewById(R.id.WH1);
        Button WH2 = findViewById(R.id.WH2);
        Button WH3 = findViewById(R.id.WH3);
        Button WH4 = findViewById(R.id.WH4);
        Button WH5 = findViewById(R.id.WH5);
        Button WH6 = findViewById(R.id.WH6);
        Button BH1 = findViewById(R.id.BH1);
        Button BH2 = findViewById(R.id.BH2);
        Button BH3 = findViewById(R.id.BH3);
        Button BH4 = findViewById(R.id.BH4);
        Button BH5 = findViewById(R.id.BH5);
        Button BH6 = findViewById(R.id.BH6);
        Button prompt = findViewById(R.id.prompt);
        ImageView Error = findViewById(R.id.Error);
        TextView turn = findViewById(R.id.turn);
        Button on_own = findViewById(R.id.on_own);
        Button help = findViewById(R.id.help);
        Button on_opponents = findViewById(R.id.on_opponents);
        TextView Cscore = findViewById(R.id.Cbone);
        TextView Hscore = findViewById(R.id.Hscore);
        TextView Crounds_won = findViewById(R.id.Crounds_won);
        TextView Hrounds_won = findViewById(R.id.Hrounds_won);
        Button CB = findViewById(R.id.CB);
        Button HB = findViewById(R.id.HB);
        prompt.setVisibility(prompt.GONE);
        TextView direction = findViewById(R.id.direction);

        if(WH1.getVisibility() == WH1.GONE && WH2.getVisibility() == WH2.GONE && WH3.getVisibility() == WH3.GONE && WH4.getVisibility() == WH4.GONE && WH5.getVisibility() == WH5.GONE && WH6.getVisibility() == WH6.GONE)
        {

            player_list.get(0).reset_hand();
            if((player_list.get(1).can_move(player_list.get(0), 1) == true || player_list.get(1).can_move(player_list.get(0), 2) == true) || (player_list.get(1).can_move(player_list.get(0), 1) == true && player_list.get(1).can_move(player_list.get(0), 2) == true))
            {

                Human_move_GUI();

            }
            else
            {
                player_list.get(1).reset_hand();

            }

        }
        if((player_list.get(0).can_move(player_list.get(1), 2) == false && player_list.get(0).can_move(player_list.get(1), 1) == false) && (player_list.get(1).can_move(player_list.get(0), 2) == false && player_list.get(1).can_move(player_list.get(0), 1) == false))
        {
            prompt.setVisibility(prompt.GONE);

            direction.setText("At this time there are no more playable moves scores will now be tallied");
            player_list.get(0).tally_score(player_list.get(1));
            player_list.get(1).tally_score(player_list.get(0));
            player_list.get(0).reset_hand();
            player_list.get(1).reset_hand();
            Hscore.setText("Score: " + player_list.get(1).get_score());
            Cscore.setText("Score: " + player_list.get(0).get_score());
            //prompt.setVisibility(prompt.GONE);
            hand_count++;
            if(hand_count == 5)
            {
                prompt.setVisibility(prompt.GONE);
                if(player_list.get(1).get_score() > player_list.get(0).get_score())
                {
                    direction.setText("And the winner is " + player_list.get(1).get_name() + " with a score of " + player_list.get(1).get_score());
                    player_list.get(1).winner();

                    Hrounds_won.setText("Rounds Won: " + player_list.get(1).get_wins());
                    BH1.setVisibility(BH1.GONE);
                    BH2.setVisibility(BH2.GONE);
                    BH3.setVisibility(BH3.GONE);
                    BH4.setVisibility(BH4.GONE);
                    WH1.setVisibility(WH1.GONE);
                    WH2.setVisibility(WH2.GONE);
                    WH3.setVisibility(WH3.GONE);
                    WH4.setVisibility(WH4.GONE);
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(View->{another_one();});
                }
                else if(player_list.get(0).get_score() > player_list.get(1).get_score())
                {
                    direction.setText("And the winner is " + player_list.get(0).get_name() + " with a score of " + player_list.get(0).get_score());
                    player_list.get(0).winner();

                    Crounds_won.setText("Rounds Won: " + player_list.get(0).get_wins());
                    BH1.setVisibility(BH1.GONE);
                    BH2.setVisibility(BH2.GONE);
                    BH3.setVisibility(BH3.GONE);
                    BH4.setVisibility(BH4.GONE);
                    WH1.setVisibility(WH1.GONE);
                    WH2.setVisibility(WH2.GONE);
                    WH3.setVisibility(WH3.GONE);
                    WH4.setVisibility(WH4.GONE);
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(View->{another_one();});

                }
                else
                {
                    direction.setText("The round is a draw");
                    BH1.setVisibility(BH1.GONE);
                    BH2.setVisibility(BH2.GONE);
                    BH3.setVisibility(BH3.GONE);
                    BH4.setVisibility(BH4.GONE);
                    WH1.setVisibility(WH1.GONE);
                    WH2.setVisibility(WH2.GONE);
                    WH3.setVisibility(WH3.GONE);
                    WH4.setVisibility(WH4.GONE);
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(View->{another_one();});


                }
                return;
            }
            if(hand_count == 4)
            {
                for(int i = 0; i < player_list.size(); i++)
                {

                    for(int j = 0; j < 4; j++)
                    {
                        player_list.get(i).add_to_hand(player_list.get(i).draw_domino_from_boneyard());
                    }
                }
                BH1.setText(player_list.get(1).get_at_hand_position(0));
                draw_domino_picture(BH1);
                BH2.setText(player_list.get(1).get_at_hand_position(1));
                draw_domino_picture(BH2);
                BH3.setText(player_list.get(1).get_at_hand_position(2));
                draw_domino_picture(BH3);
                BH4.setText(player_list.get(1).get_at_hand_position(3));
                draw_domino_picture(BH4);
                WH1.setText(player_list.get(0).get_at_hand_position(0));
                draw_domino_picture(WH1);
                WH2.setText(player_list.get(0).get_at_hand_position(1));
                draw_domino_picture(WH2);
                WH3.setText(player_list.get(0).get_at_hand_position(2));
                draw_domino_picture(WH3);
                WH4.setText(player_list.get(0).get_at_hand_position(3));
                draw_domino_picture(WH4);

                CB.setVisibility(CB.GONE);
                HB.setVisibility(HB.GONE);
                BH5.setVisibility(BH5.GONE);
                BH6.setVisibility(BH6.GONE);
                WH5.setVisibility(WH5.GONE);
                WH6.setVisibility(WH6.GONE);
                BH1.setVisibility(BH1.VISIBLE);
                BH2.setVisibility(BH2.VISIBLE);
                BH3.setVisibility(BH3.VISIBLE);
                BH4.setVisibility(BH4.VISIBLE);
                WH1.setVisibility(WH1.VISIBLE);
                WH2.setVisibility(WH2.VISIBLE);
                WH3.setVisibility(WH3.VISIBLE);
                WH4.setVisibility(WH4.VISIBLE);
                prompt.setVisibility(prompt.GONE);

                if(turn.getText().toString() == "Turn: Computer")
                {
                    Computer_move_GUI();
                }
                else{
                    prompt.setVisibility(prompt.GONE);

                    Human_move_GUI();

                }

            }
            if(hand_count >= 2 && hand_count < 4)
            {
                for(int i = 0; i < player_list.size(); i++)
                {

                    for(int j = 0; j < 6; j++)
                    {
                        player_list.get(i).add_to_hand(player_list.get(i).draw_domino_from_boneyard());
                    }
                }
                BH1.setText(player_list.get(1).get_at_hand_position(0));
                draw_domino_picture(BH1);
                BH2.setText(player_list.get(1).get_at_hand_position(1));
                draw_domino_picture(BH2);
                BH3.setText(player_list.get(1).get_at_hand_position(2));
                draw_domino_picture(BH3);
                BH4.setText(player_list.get(1).get_at_hand_position(3));
                draw_domino_picture(BH4);
                BH5.setText(player_list.get(1).get_at_hand_position(4));
                draw_domino_picture(BH5);
                BH6.setText(player_list.get(1).get_at_hand_position(5));
                draw_domino_picture(BH6);
                WH1.setText(player_list.get(0).get_at_hand_position(0));
                draw_domino_picture(WH1);
                WH2.setText(player_list.get(0).get_at_hand_position(1));
                draw_domino_picture(WH2);
                WH3.setText(player_list.get(0).get_at_hand_position(2));
                draw_domino_picture(WH3);
                WH4.setText(player_list.get(0).get_at_hand_position(3));
                draw_domino_picture(WH4);
                WH5.setText(player_list.get(0).get_at_hand_position(4));
                draw_domino_picture(WH5);
                WH6.setText(player_list.get(0).get_at_hand_position(5));
                draw_domino_picture(WH6);
                CB.setText(player_list.get(0).get_at_boneyard_position(0));
                draw_domino_picture(CB);
                HB.setText(player_list.get(1).get_at_boneyard_position(0));
                draw_domino_picture(HB);
                BH1.setVisibility(BH1.VISIBLE);
                BH2.setVisibility(BH2.VISIBLE);
                BH3.setVisibility(BH3.VISIBLE);
                BH4.setVisibility(BH4.VISIBLE);
                BH5.setVisibility(BH5.VISIBLE);
                BH6.setVisibility(BH6.VISIBLE);
                WH1.setVisibility(WH1.VISIBLE);
                WH2.setVisibility(WH2.VISIBLE);
                WH3.setVisibility(WH3.VISIBLE);
                WH4.setVisibility(WH4.VISIBLE);
                WH5.setVisibility(WH5.VISIBLE);
                WH6.setVisibility(WH6.VISIBLE);
                direction.setText("");
                if(turn.getText().toString() == "Turn: Computer")
                {
                    Computer_move_GUI();
                }
                else if (turn.getText().toString() == "Turn: Human"){
                    prompt.setVisibility(prompt.VISIBLE);
                    WH6.setVisibility(WH6.VISIBLE);
                    Human_move_GUI();
                    prompt.setVisibility(prompt.GONE);

                }
            }

        }
        player_list.get(0).make_move(player_list.get(1));
        direction.setText(player_list.get(0).grab_text());
        W1.setText(player_list.get(0).get_at_stack_position(0));
        draw_domino_picture(W1);
        Computer_change_GUI(W1);
        W2.setText(player_list.get(0).get_at_stack_position(1));
        draw_domino_picture(W2);
        Computer_change_GUI(W2);
        W3.setText(player_list.get(0).get_at_stack_position(2));
        draw_domino_picture(W3);
        Computer_change_GUI(W3);
        W4.setText(player_list.get(0).get_at_stack_position(3));
        draw_domino_picture(W4);
        Computer_change_GUI(W4);
        W5.setText(player_list.get(0).get_at_stack_position(4));
        draw_domino_picture(W5);
        Computer_change_GUI(W5);
        W6.setText(player_list.get(0).get_at_stack_position(5));
        draw_domino_picture(W6);
        Computer_change_GUI(W6);
        B1.setText(player_list.get(1).get_at_stack_position(0));
        draw_domino_picture(B1);
        Computer_change_GUI(B1);
        B2.setText(player_list.get(1).get_at_stack_position(1));
        draw_domino_picture(B2);
        Computer_change_GUI(B2);
        B3.setText(player_list.get(1).get_at_stack_position(2));
        draw_domino_picture(B3);
        Computer_change_GUI(B3);
        B4.setText(player_list.get(1).get_at_stack_position(3));
        draw_domino_picture(B4);
        Computer_change_GUI(B4);
        B5.setText(player_list.get(1).get_at_stack_position(4));
        draw_domino_picture(B5);
        Computer_change_GUI(B5);
        B6.setText(player_list.get(1).get_at_stack_position(5));
        draw_domino_picture(B6);
        Computer_change_GUI(B6);
        if(player_list.get(0).hand_size() == 5)
        {
            WH6.setVisibility(WH6.GONE);
            WH1.setText(player_list.get(0).get_at_hand_position(0));
            draw_domino_picture(WH1);
            WH2.setText(player_list.get(0).get_at_hand_position(1));
            draw_domino_picture(WH2);
            WH3.setText(player_list.get(0).get_at_hand_position(2));
            draw_domino_picture(WH3);
            WH4.setText(player_list.get(0).get_at_hand_position(3));
            draw_domino_picture(WH4);
            WH5.setText(player_list.get(0).get_at_hand_position(4));
            draw_domino_picture(WH5);
            prompt.setClickable(true);
            prompt.setVisibility(prompt.VISIBLE);
            turn.setText("Turn: " +player_list.get(1).get_name());

            prompt.setOnClickListener(this::onClick);
        }
        else if(player_list.get(0).hand_size() == 4)
        {
            WH6.setVisibility(WH6.GONE);
            WH5.setVisibility(WH5.GONE);
            WH1.setText(player_list.get(0).get_at_hand_position(0));
            draw_domino_picture(WH1);
            WH2.setText(player_list.get(0).get_at_hand_position(1));
            draw_domino_picture(WH2);
            WH3.setText(player_list.get(0).get_at_hand_position(2));
            draw_domino_picture(WH3);
            WH4.setText(player_list.get(0).get_at_hand_position(3));
            draw_domino_picture(WH4);
            prompt.setClickable(true);
            prompt.setVisibility(prompt.VISIBLE);
            turn.setText("Turn: " +player_list.get(1).get_name());

            prompt.setOnClickListener(this::onClick);
        }
        else if(player_list.get(0).hand_size() == 3)
        {
            WH6.setVisibility(WH6.GONE);
            WH5.setVisibility(WH5.GONE);
            WH4.setVisibility(WH4.GONE);
            WH1.setText(player_list.get(0).get_at_hand_position(0));
            draw_domino_picture(WH1);
            WH2.setText(player_list.get(0).get_at_hand_position(1));
            draw_domino_picture(WH2);
            WH3.setText(player_list.get(0).get_at_hand_position(2));
            draw_domino_picture(WH3);
            prompt.setClickable(true);
            prompt.setVisibility(prompt.VISIBLE);
            turn.setText("Turn: " +player_list.get(1).get_name());
            prompt.setOnClickListener(this::onClick);
        }
        else if(player_list.get(0).hand_size() == 2)
        {
            WH6.setVisibility(WH6.GONE);
            WH5.setVisibility(WH5.GONE);
            WH4.setVisibility(WH4.GONE);
            WH3.setVisibility(WH3.GONE);
            WH1.setText(player_list.get(0).get_at_hand_position(0));
            draw_domino_picture(WH1);
            WH2.setText(player_list.get(0).get_at_hand_position(1));
            draw_domino_picture(WH2);
            prompt.setClickable(true);
            prompt.setVisibility(prompt.VISIBLE);
            turn.setText("Turn: " +player_list.get(1).get_name());
            prompt.setOnClickListener(this::onClick);

        }
        else if(player_list.get(0).hand_size() == 1)
        {
            WH6.setVisibility(WH6.GONE);
            WH5.setVisibility(WH5.GONE);
            WH4.setVisibility(WH4.GONE);
            WH3.setVisibility(WH3.GONE);
            WH2.setVisibility(WH2.GONE);
            WH1.setText(player_list.get(0).get_at_hand_position(0));
            draw_domino_picture(WH1);
            prompt.setClickable(true);
            prompt.setVisibility(prompt.VISIBLE);
            turn.setText("Turn: " +player_list.get(1).get_name());
            prompt.setOnClickListener(this::onClick);

        }
        else
        {
            WH6.setVisibility(WH6.GONE);
            WH5.setVisibility(WH5.GONE);
            WH4.setVisibility(WH4.GONE);
            WH3.setVisibility(WH3.GONE);
            WH2.setVisibility(WH2.GONE);
            WH1.setVisibility(WH1.GONE);
            prompt.setClickable(true);
            prompt.setVisibility(prompt.VISIBLE);
            turn.setText("Turn: " +player_list.get(1).get_name());
            prompt.setOnClickListener(this::onClick);
        }

    }
    /** *********************************************************************
     Function Name: Human_move_GUI
     Purpose: (MAIN CONTROLLER) Update the GUI when the Human makes a move to match its model respectively
     Parameters:
     none
     Return Value:
     none
     Algorithm:
     1. model makes a move and then updates the domino GUI buttons to the correct pieces to match the model
     Assistance Received:   none
     ********************************************************************* */
    public void Human_move_GUI()
    {
        counter = 0;
        TextView first = findViewById(R.id.Player_one_name);
        TextView second = findViewById(R.id.Player_two_name);
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
        Button WH1 = findViewById(R.id.WH1);
        Button WH2 = findViewById(R.id.WH2);
        Button WH3 = findViewById(R.id.WH3);
        Button WH4 = findViewById(R.id.WH4);
        Button WH5 = findViewById(R.id.WH5);
        Button WH6 = findViewById(R.id.WH6);
        Button BH1 = findViewById(R.id.BH1);
        Button BH2 = findViewById(R.id.BH2);
        Button BH3 = findViewById(R.id.BH3);
        Button BH4 = findViewById(R.id.BH4);
        Button BH5 = findViewById(R.id.BH5);
        Button BH6 = findViewById(R.id.BH6);
        Button prompt = findViewById(R.id.prompt);
        ImageView Error = findViewById(R.id.Error);
        TextView turn = findViewById(R.id.turn);
        Button on_own = findViewById(R.id.on_own);
        Button help = findViewById(R.id.help);
        Button save = findViewById(R.id.save);
        Button on_opponents = findViewById(R.id.on_opponents);
        TextView Cscore = findViewById(R.id.Cbone);
        TextView Hscore = findViewById(R.id.Hscore);
        TextView Crounds_won = findViewById(R.id.Crounds_won);
        TextView Hrounds_won = findViewById(R.id.Hrounds_won);
        TextView direction = findViewById(R.id.direction);
        Button CB = findViewById(R.id.CB);
        Button HB = findViewById(R.id.HB);
        ImageButton iq = findViewById(R.id.iq);

        prompt.setVisibility(prompt.GONE);

        if((player_list.get(0).can_move(player_list.get(1), 2) == false && player_list.get(0).can_move(player_list.get(1), 1) == false) && (player_list.get(1).can_move(player_list.get(0), 2) == false && player_list.get(1).can_move(player_list.get(0), 1) == false))
        {
            direction.setText("At this time there are no more playable moves scores will now be tallied");
            player_list.get(0).tally_score(player_list.get(1));
            player_list.get(1).tally_score(player_list.get(0));
            player_list.get(0).reset_hand();
            player_list.get(1).reset_hand();
            Hscore.setText("Score: " + player_list.get(1).get_score());
            Cscore.setText("Score: " + player_list.get(0).get_score());
            //prompt.setVisibility(prompt.GONE);
            hand_count++;
            if(hand_count == 5)
            {
                prompt.setVisibility(prompt.GONE);
                if(player_list.get(1).get_score() > player_list.get(0).get_score())
                {
                    direction.setText("And the winner is " + player_list.get(1).get_name() + " with a score of " + player_list.get(1).get_score());
                    player_list.get(1).winner();

                    Hrounds_won.setText("Rounds Won: " + player_list.get(1).get_wins());
                    BH1.setVisibility(BH1.GONE);
                    BH2.setVisibility(BH2.GONE);
                    BH3.setVisibility(BH3.GONE);
                    BH4.setVisibility(BH4.GONE);
                    WH1.setVisibility(WH1.GONE);
                    WH2.setVisibility(WH2.GONE);
                    WH3.setVisibility(WH3.GONE);
                    WH4.setVisibility(WH4.GONE);
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(View->{another_one();});


                }
                else if(player_list.get(0).get_score() > player_list.get(1).get_score())
                {
                    direction.setText("And the winner is " + player_list.get(0).get_name() + " with a score of " + player_list.get(0).get_score());
                    player_list.get(0).winner();

                    Crounds_won.setText("Rounds Won: " + player_list.get(0).get_wins());
                    BH1.setVisibility(BH1.GONE);
                    BH2.setVisibility(BH2.GONE);
                    BH3.setVisibility(BH3.GONE);
                    BH4.setVisibility(BH4.GONE);
                    WH1.setVisibility(WH1.GONE);
                    WH2.setVisibility(WH2.GONE);
                    WH3.setVisibility(WH3.GONE);
                    WH4.setVisibility(WH4.GONE);
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(View->{another_one();});

                }
                else
                {
                    direction.setText( "The round is a draw");
                    BH1.setVisibility(BH1.GONE);
                    BH2.setVisibility(BH2.GONE);
                    BH3.setVisibility(BH3.GONE);
                    BH4.setVisibility(BH4.GONE);
                    WH1.setVisibility(WH1.GONE);
                    WH2.setVisibility(WH2.GONE);
                    WH3.setVisibility(WH3.GONE);
                    WH4.setVisibility(WH4.GONE);
                    prompt.setVisibility(prompt.VISIBLE);
                    prompt.setOnClickListener(View->{another_one();});
                }
                return;
            }
            if(hand_count == 4)
            {
                for(int i = 0; i < player_list.size(); i++)
                {

                    for(int j = 0; j < 4; j++)
                    {
                        player_list.get(i).add_to_hand(player_list.get(i).draw_domino_from_boneyard());
                    }
                }
                BH1.setText(player_list.get(1).get_at_hand_position(0));
                draw_domino_picture(BH1);
                BH2.setText(player_list.get(1).get_at_hand_position(1));
                draw_domino_picture(BH2);
                BH3.setText(player_list.get(1).get_at_hand_position(2));
                draw_domino_picture(BH3);
                BH4.setText(player_list.get(1).get_at_hand_position(3));
                draw_domino_picture(BH4);
                WH1.setText(player_list.get(0).get_at_hand_position(0));
                draw_domino_picture(WH1);
                WH2.setText(player_list.get(0).get_at_hand_position(1));
                draw_domino_picture(WH2);
                WH3.setText(player_list.get(0).get_at_hand_position(2));
                draw_domino_picture(WH3);
                WH4.setText(player_list.get(0).get_at_hand_position(3));
                draw_domino_picture(WH4);
                CB.setVisibility(CB.GONE);
                HB.setVisibility(HB.GONE);
                BH5.setVisibility(BH5.GONE);
                BH6.setVisibility(BH6.GONE);
                WH5.setVisibility(WH5.GONE);
                WH6.setVisibility(WH6.GONE);
                BH1.setVisibility(BH1.VISIBLE);
                BH2.setVisibility(BH2.VISIBLE);
                BH3.setVisibility(BH3.VISIBLE);
                BH4.setVisibility(BH4.VISIBLE);
                WH1.setVisibility(WH1.VISIBLE);
                WH2.setVisibility(WH2.VISIBLE);
                WH3.setVisibility(WH3.VISIBLE);
                WH4.setVisibility(WH4.VISIBLE);
                prompt.setVisibility(prompt.GONE);

                if(turn.getText().toString() == "Turn: Computer")
                {
                    Computer_move_GUI();
                }
                else{
                    prompt.setVisibility(prompt.GONE);

                    Human_move_GUI();


                }

            }
            if(hand_count >= 2 && hand_count < 4)
            {
                for(int i = 0; i < player_list.size(); i++)
                {

                    for(int j = 0; j < 6; j++)
                    {
                        player_list.get(i).add_to_hand(player_list.get(i).draw_domino_from_boneyard());
                    }
                }
                BH1.setText(player_list.get(1).get_at_hand_position(0));
                draw_domino_picture(BH1);
                BH2.setText(player_list.get(1).get_at_hand_position(1));
                draw_domino_picture(BH2);
                BH3.setText(player_list.get(1).get_at_hand_position(2));
                draw_domino_picture(BH3);
                BH4.setText(player_list.get(1).get_at_hand_position(3));
                draw_domino_picture(BH4);
                BH5.setText(player_list.get(1).get_at_hand_position(4));
                draw_domino_picture(BH5);
                BH6.setText(player_list.get(1).get_at_hand_position(5));
                draw_domino_picture(BH6);
                WH1.setText(player_list.get(0).get_at_hand_position(0));
                draw_domino_picture(WH1);
                WH2.setText(player_list.get(0).get_at_hand_position(1));
                draw_domino_picture(WH2);
                WH3.setText(player_list.get(0).get_at_hand_position(2));
                draw_domino_picture(WH3);
                WH4.setText(player_list.get(0).get_at_hand_position(3));
                draw_domino_picture(WH4);
                WH5.setText(player_list.get(0).get_at_hand_position(4));
                draw_domino_picture(WH5);
                WH6.setText(player_list.get(0).get_at_hand_position(5));
                draw_domino_picture(WH6);
                CB.setText(player_list.get(0).get_at_boneyard_position(0));
                draw_domino_picture(CB);
                HB.setText(player_list.get(1).get_at_boneyard_position(0));
                draw_domino_picture(HB);
                BH1.setVisibility(BH1.VISIBLE);
                BH2.setVisibility(BH2.VISIBLE);
                BH3.setVisibility(BH3.VISIBLE);
                BH4.setVisibility(BH4.VISIBLE);
                BH5.setVisibility(BH5.VISIBLE);
                BH6.setVisibility(BH6.VISIBLE);
                WH1.setVisibility(WH1.VISIBLE);
                WH2.setVisibility(WH2.VISIBLE);
                WH3.setVisibility(WH3.VISIBLE);
                WH4.setVisibility(WH4.VISIBLE);
                WH5.setVisibility(WH5.VISIBLE);
                WH6.setVisibility(WH6.VISIBLE);
                prompt.setVisibility(prompt.GONE);

                if(turn.getText().toString() == "Turn: Computer")
                {
                    Computer_move_GUI();
                }
                else
                {
                    prompt.setVisibility(prompt.GONE);
                    Human_move_GUI();
                }
            }

        }
        if(player_list.get(1).can_move(player_list.get(0), 2) == false && player_list.get(1).can_move(player_list.get(0), 1) == false)
        {
            Computer_move_GUI();
        }
        if(player_list.get(1).can_move(player_list.get(0), 1) == true)
        {
            prompt.setVisibility(prompt.GONE);
            iq.setVisibility(iq.VISIBLE);
            on_own.setVisibility(on_own.VISIBLE);
            help.setVisibility(help.VISIBLE);
            save.setVisibility(save.VISIBLE);
        }
        if(player_list.get(1).can_move(player_list.get(0), 2) == true)
        {
            iq.setVisibility(iq.VISIBLE);
            prompt.setVisibility(prompt.GONE);
            on_opponents.setVisibility(on_opponents.VISIBLE);
            help.setVisibility(help.VISIBLE);
            save.setVisibility(save.VISIBLE);
        }
        iq.setOnClickListener(view -> {
            makeText(getApplicationContext(), "press and hold to exit Buildup", LENGTH_SHORT).show();
            return;
        });
        iq.setOnLongClickListener(view -> {
           finish();
           System.exit(0);
           return true;
        });
        help.setOnClickListener(view -> {
            help.setVisibility(help.GONE);
            player_list.get(0).help_mode(player_list.get(1));
            direction.setText(player_list.get(0).grab_text());
        });
        save.setOnClickListener(view -> {
            iq.setVisibility(iq.GONE);

            help.setVisibility(help.GONE);
            save.setVisibility(save.GONE);
            save_game();
        });
        on_own.setOnClickListener(view -> {

            grab = 1;
            move_type = false;
            prompt.setVisibility(prompt.GONE);
            iq.setVisibility(iq.GONE);
            help.setVisibility(help.GONE);
            on_own.setVisibility(on_own.GONE);
            save.setVisibility(save.GONE);
            on_opponents.setVisibility(on_opponents.GONE);
            direction.setText("Please tap on the domino from your hand from which you would like to play");

            BH1.setOnClickListener(this::onClick);
            BH2.setOnClickListener(this::onClick);
            BH3.setOnClickListener(this::onClick);
            BH4.setOnClickListener(this::onClick);
            BH5.setOnClickListener(this::onClick);
            BH6.setOnClickListener(this::onClick);


        });
        on_opponents.setOnClickListener(view -> {
            move_type = true;
            grab = 0;
            prompt.setVisibility(prompt.GONE);
            help.setVisibility(help.GONE);
            save.setVisibility(save.GONE);
            iq.setVisibility(iq.GONE);
            on_own.setVisibility(on_own.GONE);
            on_opponents.setVisibility(on_opponents.GONE);
            direction.setText("Please tap on the domino from your hand from which you would like to play");
            BH1.setOnClickListener(this::onClick);
            BH2.setOnClickListener(this::onClick);
            BH3.setOnClickListener(this::onClick);
            BH4.setOnClickListener(this::onClick);
            BH5.setOnClickListener(this::onClick);
            BH6.setOnClickListener(this::onClick);
        });
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
     Function Name: another_one
     Purpose:
     Do you want to play another round?
     Parameters:
     none

     Return Value:
     none
     Algorithm:
     1. displays the play another round button option and the
     play again button is pressed round is reset and a new round is played
     ELSE
     2. winner of the tournament is announced and the user can exit the app
     Assistance Received: none
     ********************************************************************* */
    public void another_one()
    {
        TextView first = findViewById(R.id.Player_one_name);
        TextView second = findViewById(R.id.Player_two_name);
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
        Button WH1 = findViewById(R.id.WH1);
        Button WH2 = findViewById(R.id.WH2);
        Button WH3 = findViewById(R.id.WH3);
        Button WH4 = findViewById(R.id.WH4);
        Button WH5 = findViewById(R.id.WH5);
        Button WH6 = findViewById(R.id.WH6);
        TextView direction = findViewById(R.id.direction);
        Button BH1 = findViewById(R.id.BH1);
        Button BH2 = findViewById(R.id.BH2);
        Button BH3 = findViewById(R.id.BH3);
        Button BH4 = findViewById(R.id.BH4);
        Button BH5 = findViewById(R.id.BH5);
        Button BH6 = findViewById(R.id.BH6);
        Button prompt = findViewById(R.id.prompt);
        ImageView Error = findViewById(R.id.Error);
        TextView turn = findViewById(R.id.turn);
        Button on_own = findViewById(R.id.on_own);
        Button help = findViewById(R.id.help);
        Button on_opponents = findViewById(R.id.on_opponents);
        TextView Cscore = findViewById(R.id.Cbone);
        TextView Hscore = findViewById(R.id.Hscore);
        TextView Crounds_won = findViewById(R.id.Crounds_won);
        TextView Hrounds_won = findViewById(R.id.Hrounds_won);
        Button CB = findViewById(R.id.CB);
        Button HB = findViewById(R.id.HB);
        TextView CBD = findViewById(R.id.Cboneyard);
        TextView HBD = findViewById(R.id.Hboneyard);
        Button again = findViewById(R.id.again);
        Button quit = findViewById(R.id.quit);
        again.setVisibility(again.VISIBLE);
        quit.setVisibility(quit.VISIBLE);
        Button test = findViewById(R.id.test);

        next_round = true;
        quit.setOnClickListener(View-> {
            again.setVisibility(again.GONE);
            quit.setVisibility(quit.GONE);
            test.setVisibility(test.VISIBLE);
            prompt.setVisibility(prompt.GONE);
            if (player_list.get(0).get_wins() > player_list.get(1).get_wins())
            {
                direction.setText(player_list.get(0).get_name()+" has won the tournament with a score of "+player_list.get(0).get_wins());
            }
            else if (player_list.get(1).get_wins() > player_list.get(0).get_wins())
            {
                direction.setText(player_list.get(1).get_name()+" has won the tournament with a score of "+player_list.get(1).get_wins());
            }
            else
            {
                direction.setText("The tournament is a draw with a score of "+player_list.get(1).get_wins());
            }
            test.setOnClickListener(view -> {
                makeText(getApplicationContext(), "press and hold to exit Buildup", LENGTH_SHORT).show();
                return;
            });
            test.setOnLongClickListener(view -> {
                finish();
                System.exit(0);
                return true;
            });
        });
        prompt.setVisibility(prompt.GONE);
        again.setOnClickListener(View-> {
            direction.setText("please wait as new round loads... ");
            prompt.setVisibility(prompt.VISIBLE);
            again.setVisibility(again.GONE);
            quit.setVisibility(quit.GONE);
            do
            {
                for(int i = 0; i < player_list.size(); i++)
                {
                    player_list.get(i).reset_bone_yard();
                    player_list.get(i).reset_score();
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
            B1.setText(player_list.get(1).get_at_stack_position(0));
            draw_domino_picture(B1);
            B2.setText(player_list.get(1).get_at_stack_position(1));
            draw_domino_picture(B2);
            B3.setText(player_list.get(1).get_at_stack_position(2));
            draw_domino_picture(B3);
            B4.setText(player_list.get(1).get_at_stack_position(3));
            draw_domino_picture(B4);
            B5.setText(player_list.get(1).get_at_stack_position(4));
            draw_domino_picture(B5);
            B6.setText(player_list.get(1).get_at_stack_position(5));
            draw_domino_picture(B6);
            WH1.setVisibility(WH1.VISIBLE);
            WH2.setVisibility(WH2.VISIBLE);
            WH3.setVisibility(WH3.VISIBLE);
            WH4.setVisibility(WH4.VISIBLE);
            WH5.setVisibility(WH5.VISIBLE);
            WH6.setVisibility(WH6.VISIBLE);
            WH1.setText(player_list.get(0).get_at_hand_position(0));
            draw_domino_picture(WH1);
            WH2.setText(player_list.get(0).get_at_hand_position(1));
            draw_domino_picture(WH2);
            WH3.setText(player_list.get(0).get_at_hand_position(2));
            draw_domino_picture(WH3);
            WH4.setText(player_list.get(0).get_at_hand_position(3));
            draw_domino_picture(WH4);
            WH5.setText(player_list.get(0).get_at_hand_position(4));
            draw_domino_picture(WH5);
            WH6.setText(player_list.get(0).get_at_hand_position(5));
            draw_domino_picture(WH6);
            BH1.setVisibility(BH1.VISIBLE);
            BH2.setVisibility(BH2.VISIBLE);
            BH3.setVisibility(BH3.VISIBLE);
            BH4.setVisibility(BH4.VISIBLE);
            BH5.setVisibility(BH5.VISIBLE);
            BH6.setVisibility(BH6.VISIBLE);
            CB.setVisibility(CB.VISIBLE);
            HB.setVisibility(HB.VISIBLE);
            BH1.setText(player_list.get(1).get_at_hand_position(0));
            draw_domino_picture(BH1);
            BH2.setText(player_list.get(1).get_at_hand_position(1));
            draw_domino_picture(BH2);
            BH3.setText(player_list.get(1).get_at_hand_position(2));
            draw_domino_picture(BH3);
            BH4.setText(player_list.get(1).get_at_hand_position(3));
            draw_domino_picture(BH4);
            BH5.setText(player_list.get(1).get_at_hand_position(4));
            draw_domino_picture(BH5);
            BH6.setText(player_list.get(1).get_at_hand_position(5));
            draw_domino_picture(BH6);
            first.setText(player_list.get(0).get_name());
            second.setText(player_list.get(1).get_name());
            CB.setText(player_list.get(0).get_at_boneyard_position(0));
            draw_domino_picture(CB);
            HB.setText(player_list.get(1).get_at_boneyard_position(0));
            draw_domino_picture(HB);
            Hscore.setText("Score: " + player_list.get(1).get_score());
            Cscore.setText("Score: " + player_list.get(0).get_score());
            Hrounds_won.setText("Rounds Won: " + player_list.get(1).get_wins());
            Crounds_won.setText("Rounds Won: " + player_list.get(0).get_wins());
            prompt.setOnClickListener(v -> {
                hand_count = 1;
                if (player_list.get(1).domino_value(player_list.get(1).get_at_hand_position(0)) > player_list.get(0).domino_value(player_list.get(0).get_at_hand_position(0))) {
                        direction.setText(player_list.get(1).get_name() + " goes first because it drew the bigger domino it drew " + player_list.get(1).get_at_hand_position(0) +" while " + player_list.get(0).get_name() + " drew " +player_list.get(0).get_at_hand_position(0));
                        direction.setVisibility(direction.VISIBLE);
                        prompt.setVisibility(prompt.GONE);
                        turn.setText("Turn: " + player_list.get(0).get_name());
                        prompt.setOnClickListener(this::onClick);
                        Human_move_GUI();


                }
                else if (player_list.get(1).domino_value(player_list.get(1).get_at_hand_position(0)) < player_list.get(0).domino_value(player_list.get(0).get_at_hand_position(0))) {
                        direction.setText(player_list.get(0).get_name() + " goes first because it drew the bigger domino it drew " + player_list.get(0).get_at_hand_position(0) +" while " + player_list.get(1).get_name() + " drew " +player_list.get(1).get_at_hand_position(0));
                        direction.setVisibility(direction.VISIBLE);
                        turn.setText("Turn: " + player_list.get(0).get_name());
                        prompt.setOnClickListener(p ->
                        {
                            prompt.setVisibility(prompt.GONE);
                            Computer_move_GUI();
                            //prompt.setVisibility(prompt.GONE);

                        });


                }
                else
                {
                    return;
                }
            });

        });
    }
    /** *********************************************************************
    Function Name: save_game
    Purpose:
                Save the game to a file
    Parameters:
                none

    Return Value:
                none
    Algorithm:
                Please note player_list[0] is computer and player_list[1] is human
                1. Prompt for the serialization file, add the extension(.txt),
                display the computer won with its score.
                2. Open the file and write in the information for the players
                in the following order: Computer, Stacks, Boneyard, Hand, Score, Rounds Won,
                and Turn. Repeat this order for the human player.
                3. Terminate game.

    Assistance Received: none
    ********************************************************************* */
    public void save_game()
    {
        //https://stackoverflow.com/questions/13953744/file-mkdir-and-mkdirs-are-creating-file-instead-of-directory
        //https://stackoverflow.com/questions/6014028/closing-application-with-exit-button
        //https://www.youtube.com/watch?v=DkDOqsZXNt0

        TextView first = findViewById(R.id.Player_one_name);
        TextView second = findViewById(R.id.Player_two_name);
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
        Button WH1 = findViewById(R.id.WH1);
        Button WH2 = findViewById(R.id.WH2);
        Button WH3 = findViewById(R.id.WH3);
        Button WH4 = findViewById(R.id.WH4);
        Button WH5 = findViewById(R.id.WH5);
        Button WH6 = findViewById(R.id.WH6);
        Button BH1 = findViewById(R.id.BH1);
        Button BH2 = findViewById(R.id.BH2);
        Button BH3 = findViewById(R.id.BH3);
        Button BH4 = findViewById(R.id.BH4);
        Button BH5 = findViewById(R.id.BH5);
        Button BH6 = findViewById(R.id.BH6);
        Button save_file = findViewById(R.id.save_file);
        Button prompt = findViewById(R.id.prompt);
        ImageView Error = findViewById(R.id.Error);
        TextView turn = findViewById(R.id.turn);
        TextView Chand = findViewById(R.id.Chand);
        TextView Hhand = findViewById(R.id.Hhand);
        TextView CStack = findViewById(R.id.CStack);
        TextView HStack = findViewById(R.id.HStack);
        Button on_own = findViewById(R.id.on_own);
        Button help = findViewById(R.id.help);
        Button on_opponents = findViewById(R.id.on_opponents);
        TextView Cscore = findViewById(R.id.Cbone);
        TextView Hscore = findViewById(R.id.Hscore);
        TextView Crounds_won = findViewById(R.id.Crounds_won);
        TextView Hrounds_won = findViewById(R.id.Hrounds_won);
        Button CB = findViewById(R.id.CB);
        Button HB = findViewById(R.id.HB);
        TextView CBD = findViewById(R.id.Cboneyard);
        TextView HBD = findViewById(R.id.Hboneyard);
        TextView direction = findViewById(R.id.direction);
        EditText file_name = findViewById(R.id.file_name);
        prompt.setVisibility(prompt.GONE);
        first.setVisibility(first.GONE);
        second.setVisibility(second.GONE);
        B1.setVisibility(B1.GONE);
        B2.setVisibility(B2.GONE);
        B3.setVisibility(B3.GONE);
        B4.setVisibility(B4.GONE);
        B5.setVisibility(B5.GONE);
        B6.setVisibility(B6.GONE);
        W1.setVisibility(W1.GONE);
        W2.setVisibility(W2.GONE);
        W3.setVisibility(W3.GONE);
        W4.setVisibility(W4.GONE);
        W5.setVisibility(W5.GONE);
        W6.setVisibility(W6.GONE);
        BH1.setVisibility(BH1.GONE);
        BH2.setVisibility(BH2.GONE);
        BH3.setVisibility(BH3.GONE);
        BH4.setVisibility(BH4.GONE);
        BH5.setVisibility(BH5.GONE);
        BH6.setVisibility(BH6.GONE);
        WH1.setVisibility(WH1.GONE);
        WH2.setVisibility(WH2.GONE);
        WH3.setVisibility(WH3.GONE);
        WH4.setVisibility(WH4.GONE);
        WH5.setVisibility(WH5.GONE);
        WH6.setVisibility(WH6.GONE);
        Cscore.setVisibility(Cscore.GONE);
        Hscore.setVisibility(Hscore.GONE);
        Crounds_won.setVisibility(Crounds_won.GONE);
        Hrounds_won.setVisibility(Hrounds_won.GONE);
        HB.setVisibility(HB.GONE);
        CB.setVisibility(CB.GONE);
        HBD.setVisibility(HBD.GONE);
        CBD.setVisibility(CBD.GONE);
        turn.setVisibility(turn.GONE);
        Chand.setVisibility(Chand.GONE);
        Hhand.setVisibility(Hhand.GONE);
        CStack.setVisibility(CStack.GONE);
        HStack.setVisibility(HStack.GONE);
        on_own.setVisibility(on_own.GONE);
        on_opponents.setVisibility(on_opponents.GONE);
        file_name.setVisibility(file_name.VISIBLE);
        save_file.setVisibility(save_file.VISIBLE);
        direction.setText("Enter name for the saved game");
        Button test = findViewById(R.id.test);
        test.setVisibility(test.VISIBLE);
        test.setOnClickListener(view -> {
            makeText(getApplicationContext(), "press and hold to exit Buildup", LENGTH_SHORT).show();
            return;
        });
        test.setOnLongClickListener(view -> {
            finish();
            System.exit(0);
            return true;
        });
        save_file.setOnClickListener(View -> {
            file_name.setVisibility(file_name.GONE);
            save_file.setVisibility(save_file.GONE);
            File files = getApplicationContext().getFilesDir();
            final String EXTENSION = ".txt";

            //makeText(getApplicationContext(), files, LENGTH_LONG).show();
            String round = "";
            round += player_list.get(0).get_name();
            round += ':';
            round += '\n';
            round += "\tStacks: ";
            for(int i = 0; i < 6; i++)
            {
                round += player_list.get(0).get_at_stack_position(i);
                if(i < 5)
                {
                    round += ' ';
                }
            }
            round += '\n';
            round += "\tBoneyard: ";
            for(int i = 0; i < player_list.get(0).bone_yard_size(); i++)
            {
                round += player_list.get(0).get_at_boneyard_position(i);
                if(i < (player_list.get(0).bone_yard_size() - 1))
                {
                    round += ' ';
                }
            }
            round += '\n';
            round += "\tHand: ";
            for(int i = 0; i < player_list.get(0).hand_size(); i++)
            {
                round += player_list.get(0).get_at_hand_position(i);
                if(i < (player_list.get(0).hand_size() - 1))
                {
                    round += ' ';
                }
            }
            round += '\n';
            round +='\t';
            round += Cscore.getText().toString();
            round += '\n';
            round +='\t';
            round += Crounds_won.getText().toString();
            round += '\n';
            round += '\n';
            round += player_list.get(1).get_name();
            round += ':';
            round += '\n';
            round += "\tStacks: ";
            for(int i = 0; i < 6; i++)
            {
                round += player_list.get(1).get_at_stack_position(i);
                if(i < 5)
                {
                    round += ' ';
                }
            }
            round += '\n';
            round += "\tBoneyard: ";
            for(int i = 0; i < player_list.get(1).bone_yard_size(); i++)
            {
                round += player_list.get(1).get_at_boneyard_position(i);
                if(i < (player_list.get(1).bone_yard_size() - 1))
                {
                    round += ' ';
                }
            }
            round += '\n';
            round += "\tHand: ";
            for(int i = 0; i < player_list.get(1).hand_size(); i++)
            {
                round += player_list.get(1).get_at_hand_position(i);
                if(i < (player_list.get(1).hand_size() - 1))
                {
                    round += ' ';
                }
            }
            round += '\n';
            round +='\t';
            round += Hscore.getText().toString();
            round += '\n';
            round +='\t';
            round += Hrounds_won.getText().toString();
            round += '\n';
            round += '\n';
            round += turn.getText().toString();
            round += '\n';
            Hhand.setText(round);
            direction.setText(file_name.getEditableText().toString() + " is saved");
            String act_name = file_name.getEditableText().toString();
            if(act_name.indexOf(EXTENSION) == -1)
            {
                act_name += EXTENSION;
            }
            // makeText(getApplicationContext(), round, LENGTH_LONG).show();+
            InputStream inputStream = null;
            try {
                FileOutputStream game= new FileOutputStream(new File(files,act_name));
                game.write(round.getBytes());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
    // https://www.youtube.com/watch?v=DkDOqsZXNt0

    }

}

