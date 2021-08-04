package com.example.bashyaltictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//Name: Laxman Bashyal
//Date: March 24th, 2020
//Purpose: Unit 1 Project - Numerical Tic-Tac-Toe
public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
    }

    //This takes you to the game screen
    public void play(View view){
        Intent i = new Intent(this, Game.class);
        startActivity(i);
    }
}