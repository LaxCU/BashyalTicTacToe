package com.example.bashyaltictactoe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//Name: Laxman Bashyal
//Date: March 24th, 2020
//Purpose: Unit 1 Project - Numerical Tic-Tac-Toe

public class Game extends AppCompatActivity {

    String n1, n2; //Holds the player names.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //This is used to get the names that the user entered from the other screen & store in the variable n1, n2
        try {
            FileInputStream in = openFileInput("names.txt");
            String display = "";
            //How long is the first name?
            int nameLength = in.read();
            //Read in that many ints, convert to chars
            for (int i = 0; i < nameLength; i++) {
                int data = in.read();
                char letter = (char) data;
                display += letter;
            }
            n1 = display; //stores player1's name

            //How long is the second name?
            nameLength = in.read();
            display = "";
            //Read in that many ints, convert to chars
            for (int i = 0; i < nameLength; i++) {
                int data = in.read();
                char letter = (char) data;
                display += letter;
            }
            n2 = display; //stores player2's name
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    int board[][] = new int[3][3]; //sets up the grid where the game is played
    int turn = 1; // keeps track of whose turn it is
    int turn2 = 0; //placeholder for the number that the user selects

    public void flip(ImageView i) {
        //Changes the picture of the board for the odd player
        if (turn == 1) {
            i.setBackgroundResource(0); //Sets the board's background to clear such that the blank image doesn't show
            if (turn2 == 1)
                i.setImageResource(R.drawable.pic1);
            else if (turn2 == 3)
                i.setImageResource(R.drawable.pic3);
            else if (turn2 == 5)
                i.setImageResource(R.drawable.pic5);
            else if (turn2 == 7)
                i.setImageResource(R.drawable.pic7);
            else if (turn2 == 9)
                i.setImageResource(R.drawable.pic9);
            turn = 2; //changes the turn
            turn2 = 0; //sets the number the user selects to 0
            sswap(); //calls method to change the options
        } else {
            i.setBackgroundResource(0);
            if (turn2 == 2)
                i.setImageResource(R.drawable.pic2);
            else if (turn2 == 4)
                i.setImageResource(R.drawable.pic4);
            else if (turn2 == 6)
                i.setImageResource(R.drawable.pic6);
            else if (turn2 == 8)
                i.setImageResource(R.drawable.pic8);
            else if (turn2 == 10)
                i.setImageResource(R.drawable.pic10);

            turn = 1;
            turn2 = 0;
            sswap();
        }
    }

    //Method checks if a player has won
    public void win() {
        int winner = 0; //placeholder that holds the winner
//The three Horizontal Checks
        if ((board[0][0] + board[0][1] + board[0][2] == 15) && board[0][0] != 0 && board[0][1] != 0 && board[0][2] != 0)
            winmsg(turn); //if the check is true, the winning message is shown
        else if ((board[1][0] + board[1][1] + board[1][2] == 15) && board[1][0] != 0 && board[1][1] != 0 && board[1][2] != 0)
            winmsg(turn);
        else if ((board[2][0] + board[2][1] + board[2][2] == 15) && board[2][0] != 0 && board[2][1] != 0 && board[2][2] != 0)
            winmsg(turn);
//The three Vertical Checks
        else if ((board[0][0] + board[1][0] + board[2][0] == 15) && board[0][0] != 0 && board[1][0] != 0 && board[2][0] != 0)
            winmsg(turn);
        else if ((board[0][1] + board[1][1] + board[2][1] == 15) && board[0][1] != 0 && board[1][1] != 0 && board[2][1] != 0)
            winmsg(turn);
        else if ((board[0][2] + board[1][2] + board[2][2] == 15) && board[0][2] != 0 && board[1][2] != 0 && board[2][2] != 0)
            winmsg(turn);
//The two slanted cross, they form the X
        else if ((board[0][0] + board[1][1] + board[2][2] == 15) && board[0][0] != 0 && board[1][1] != 0 && board[2][2] != 0)
            winmsg(turn);
        else if ((board[0][2] + board[1][1] + board[2][0] == 15) && board[0][2] != 0 && board[1][1] != 0 && board[2][0] != 0)
            winmsg(turn);

//Checks if the game is a tied
        else if (board[0][0] != 0 && board[0][1] != 0 && board[0][2] != 0 &&
                board[1][0] != 0 && board[1][1] != 0 && board[1][2] != 0 &&
                board[2][0] != 0 && board[2][1] != 0 && board[2][2] != 0)
            winmsg(3);

    }

    //This method asks the user if they want to reset the game, if yes, resetgo method is called
    public void reset(View view) {
        new AlertDialog.Builder(this)
                //The title on the Dialog
                .setTitle("Confirmation")
                //The message that will appear
                .setMessage("Do you want to reset the game?")
                //What to do if the button is pressed
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetgo(); //Resets the game, so that the player can play again
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    //This method sets everything to default
    public void resetgo() {
        ImageView a = (ImageView) findViewById(R.id.a); //Sets each board piece to blank
        a.setImageResource(R.drawable.blank);
        ImageView b = (ImageView) findViewById(R.id.b);
        b.setImageResource(R.drawable.blank);
        ImageView c = (ImageView) findViewById(R.id.c);
        c.setImageResource(R.drawable.blank);
        ImageView d = (ImageView) findViewById(R.id.d);
        d.setImageResource(R.drawable.blank);
        ImageView e = (ImageView) findViewById(R.id.e);
        e.setImageResource(R.drawable.blank);
        ImageView f = (ImageView) findViewById(R.id.f);
        f.setImageResource(R.drawable.blank);
        ImageView g = (ImageView) findViewById(R.id.g);
        g.setImageResource(R.drawable.blank);
        ImageView h = (ImageView) findViewById(R.id.h);
        h.setImageResource(R.drawable.blank);
        ImageView i = (ImageView) findViewById(R.id.i);
        i.setImageResource(R.drawable.blank);
        for (int k = 0; k < 3; k++) { //Loops sets the value of board to 0
            for (int j = 0; j < 3; j++) {
                board[k][j] = 0;
            }
        }
        turn2 = 0;
        turn = 1;
        sswap(); //calls the method to change the options for default
    }

    //This method is for when user clicks the button [0][0] in the grid
    public void aClick(View view) {
        if (board[0][0] == 0) {
            ImageView i = (ImageView) findViewById(R.id.a);
            if (turn2 != 0) //Player needs to choose their number
            {
                board[0][0] = turn2; //Changes the value of the board to selected number
                flip(i); //calls the flip method to change the image on the board
            } else
                Toast.makeText(getApplicationContext(), "Select a number", Toast.LENGTH_SHORT).show(); //Tells the user to select a number if they haven't
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show(); //if the board already has a number placed, alerts the player
        }
        win(); // Checks if the win conditions are met
    }

    //This method is for when user clicks the button [0][1] in the grid
    public void bClick(View view) {
        if (board[0][1] == 0) {
            ImageView i = (ImageView) findViewById(R.id.b);
            if (turn2 != 0) {
                board[0][1] = turn2;
                flip(i);
            } else
                Toast.makeText(getApplicationContext(), "Select a number", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //This method is for when user clicks the button [0][2] in the grid
    public void cClick(View view) {
        if (board[0][2] == 0) {
            ImageView i = (ImageView) findViewById(R.id.c);
            if (turn2 != 0) {
                board[0][2] = turn2;
                flip(i);
            } else
                Toast.makeText(getApplicationContext(), "Select a number", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //This method is for when user clicks the button [1][0] in the grid
    public void dClick(View view) {
        if (board[1][0] == 0) {
            ImageView i = (ImageView) findViewById(R.id.d);
            if (turn2 != 0) {
                board[1][0] = turn2;
                flip(i);
            } else
                Toast.makeText(getApplicationContext(), "Select a number", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //This method is for when user clicks the button [1][1] in the grid
    public void eClick(View view) {
        if (board[1][1] == 0) {
            ImageView i = (ImageView) findViewById(R.id.e);
            if (turn2 != 0) {
                board[1][1] = turn2;
                flip(i);
            } else
                Toast.makeText(getApplicationContext(), "Select a number", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //This method is for when user clicks the button [1][2] in the grid
    public void fClick(View view) {
        if (board[1][2] == 0) {
            ImageView i = (ImageView) findViewById(R.id.f);
            if (turn2 != 0) {
                board[1][2] = turn2;
                flip(i);
            } else
                Toast.makeText(getApplicationContext(), "Select a number", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //This method is for when user clicks the button [2][0] in the grid
    public void gClick(View view) {
        if (board[2][0] == 0) {
            ImageView i = (ImageView) findViewById(R.id.g);
            if (turn2 != 0) {
                board[2][0] = turn2;
                flip(i);
            } else
                Toast.makeText(getApplicationContext(), "Select a number", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //This method is for when user clicks the button [2][1] in the grid
    public void hClick(View view) {
        if (board[2][1] == 0) {
            ImageView i = (ImageView) findViewById(R.id.h);
            if (turn2 != 0) {
                board[2][1] = turn2;
                flip(i);
            } else
                Toast.makeText(getApplicationContext(), "Select a number", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //This method is for when user clicks the button [2][2] in the grid
    public void iClick(View view) {
        if (board[2][2] == 0) {
            ImageView i = (ImageView) findViewById(R.id.i);
            if (turn2 != 0) {
                board[2][2] = turn2;
                flip(i);
            } else
                Toast.makeText(getApplicationContext(), "Select a number", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //These methods are for when a user clicks the first option, either 1 or 2
    public void s1(View view) {
        if (turn == 1) {
            turn2 = 1;
            sselect(turn2);
        } else {
            turn2 = 2;
            sselect(turn2);
        }
    }

    //These methods are for when a user clicks the second option, either 3 or 4
    public void s2(View view) {
        if (turn == 1) {
            turn2 = 3;
            sselect(turn2);
        } else {
            turn2 = 4;
            sselect(turn2);
        }
    }

    //These methods are for when a user clicks the third option, either 5 or 6
    public void s3(View view) {
        if (turn == 1) {
            turn2 = 5;
            sselect(turn2);
        } else {
            turn2 = 6;
            sselect(turn2);
        }
    }

    //These methods are for when a user clicks the fourth option, either 7 or 8
    public void s4(View view) {
        if (turn == 1) {
            turn2 = 7;
            sselect(turn2);
        } else {
            turn2 = 8;
            sselect(turn2);
        }
    }

    //These methods are for when a user clicks the fifth option, either 9 or 10
    public void s5(View view) {
        if (turn == 1) {
            turn2 = 9;
            sselect(turn2);
        } else {
            turn2 = 10;
            sselect(turn2);
        }
    }

    //This changes the options that the user has available
    public void sswap() {
        Button s1 = (Button) findViewById(R.id.s1); //Finds the button using their id
        Button s2 = (Button) findViewById(R.id.s2);
        Button s3 = (Button) findViewById(R.id.s3);
        Button s4 = (Button) findViewById(R.id.s4);
        Button s5 = (Button) findViewById(R.id.s5);
        ImageView option = (ImageView) findViewById(R.id.option); //Sets the Options
        option.setBackgroundResource(R.drawable.options);
        if (turn == 1) { //Changes the options to all the odd numbers
            s1.setBackgroundResource(R.drawable.spic1);
            s2.setBackgroundResource(R.drawable.spic3);
            s3.setBackgroundResource(R.drawable.spic5);
            s4.setBackgroundResource(R.drawable.spic7);
            s5.setBackgroundResource(R.drawable.spic9);
        } else { //changes the options to all the even numbers
            s1.setBackgroundResource(R.drawable.spic2);
            s2.setBackgroundResource(R.drawable.spic4);
            s3.setBackgroundResource(R.drawable.spic6);
            s4.setBackgroundResource(R.drawable.spic8);
            s5.setBackgroundResource(R.drawable.spic10);
        }
    }

    //This shows the options that the user has selected from the options
    public void sselect(int n) {
        Button s1 = (Button) findViewById(R.id.s1); //Finds the button using their id
        Button s2 = (Button) findViewById(R.id.s2);
        Button s3 = (Button) findViewById(R.id.s3);
        Button s4 = (Button) findViewById(R.id.s4);
        Button s5 = (Button) findViewById(R.id.s5);
        ImageView option = (ImageView) findViewById(R.id.option); //Changes the options image to chosen
        option.setBackgroundResource(R.drawable.chosen);

        //Sets the first option image according what option the user chose
        if (n == 1)
            s1.setBackgroundResource(R.drawable.spic1);
        else if (n == 2)
            s1.setBackgroundResource(R.drawable.spic2);
        else if (n == 3)
            s1.setBackgroundResource(R.drawable.spic3);
        else if (n == 4)
            s1.setBackgroundResource(R.drawable.spic4);
        else if (n == 5)
            s1.setBackgroundResource(R.drawable.spic5);
        else if (n == 6)
            s1.setBackgroundResource(R.drawable.spic6);
        else if (n == 7)
            s1.setBackgroundResource(R.drawable.spic7);
        else if (n == 8)
            s1.setBackgroundResource(R.drawable.spic8);
        else if (n == 9)
            s1.setBackgroundResource(R.drawable.spic9);
        else if (n == 10)
            s1.setBackgroundResource(R.drawable.spic10);

        //Makes the second,third,fourth, & fifth option blank so that only the selected option is shown
        s2.setBackgroundResource(0);
        s3.setBackgroundResource(0);
        s4.setBackgroundResource(0);
        s5.setBackgroundResource(0);
    }

    //This method displays the winner
    public void winmsg(int n) {
        String winner; //holder for winner name
        if (n == 1)
            winner = n2; //if the value entered is 1, the winner is player 2
        else
            winner = n1; //if the value entered is 2, the winner is player 1
        if (n == 3) { //If the value entered is 3, its a tie so this message is displayed
            new AlertDialog.Builder(this)
                    .setTitle("Winner")
                    .setMessage("Game has ended in a tie!")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Again();
                        }
                    }).show();
        } else { //This is displayed if player 1 or player 2 wins
            new AlertDialog.Builder(this)
                    .setTitle("Winner")
                    .setMessage(winner + " has won this game of Numerical Tic-Tac-Toe")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Again();
                        }
                    }).show();
        }
    }

    //This method asks the user if they want to play again.
    public void Again() {
        new AlertDialog.Builder(this)
                //The title on the Dialog
                .setTitle("Play Again?")
                //The message that will appear
                .setMessage("Would you like to play again?")
                //What to do if the button is pressed
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetgo(); //Resets the game, so that the player can play again
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    //finishAffinity() requires API to work
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity(); //Closes the app
                    }
                })
                .show();
    }

}