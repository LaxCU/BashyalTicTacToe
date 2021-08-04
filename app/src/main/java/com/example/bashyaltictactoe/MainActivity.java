package com.example.bashyaltictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//Name: Laxman Bashyal
//Date: March 24th, 2020
//Purpose: Unit 1 Project - Numerical Tic-Tac-Toe
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Takes the user to the instruction screen
    public void inst(View view) {
        Intent i = new Intent(this, Instructions.class);
        startActivity(i);
    }

    //Takes the user to playing screen
    public void play(View view) {
        Intent i = new Intent(this, Username.class);
        startActivity(i);
    }
}