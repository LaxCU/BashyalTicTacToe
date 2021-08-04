package com.example.bashyaltictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//Name: Laxman Bashyal
//Date: March 24th, 2020
//Purpose: Unit 1 Project - Numerical Tic-Tac-Toe
public class Username extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
    }

    //Used to get the names from the player(s) input
    public void name(View view) {
//Get the names out of the EditTexts
        EditText name1 = (EditText) findViewById(R.id.p1name);
        EditText name2 = (EditText) findViewById(R.id.p2name);
        String n1 = name1.getText().toString(); //Converts the EditText to strings
        String n2 = name2.getText().toString();
        if (n1.length() <= 0 || n2.length() <= 0) //Checks if the name is blank
            NameError(); //calls method that alerts the user
        else {
//Print them out to a file.
            try {
                FileOutputStream out = openFileOutput("names.txt", Activity.MODE_PRIVATE);
//Print out the length of name 1
                out.write(n1.length());
//Print out each ASCII letter in the name
                for (int i = 0; i < n1.length(); i++) {
                    out.write((int) (n1.charAt(i)));
                }
//Print out the length of name 1
                out.write(n2.length());
//Print out each ASCII letter in the name
                for (int i = 0; i < n2.length(); i++) {
                    out.write((int) (n2.charAt(i)));
                }
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Takes the user to playing to screen to play the game
            Intent i = new Intent(this, Game.class);
            startActivity(i);
        }
    }

    //Method tells the user that they need to enter the names and can't leave it blank
    public void NameError() {
        new AlertDialog.Builder(this)
                //The title on the Dialog
                .setTitle("Player Name")
                //The message that will appear
                .setMessage("Player name(s) aren't entered." +
                        "\nEnter names and try again!")
                //What to do if the button is pressed
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do something if they click the button
//otherwise, it just dismisses the dialog
                    }
                }).show();
    }
}