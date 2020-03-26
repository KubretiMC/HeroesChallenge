package com.mariyan.heroeschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button chooseHero;
    private Button createHero;
    private Button highScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chooseHero = findViewById(R.id.ChooseHeroButton);
        chooseHero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openChooseHeroActivity();
            }
        });

        createHero = findViewById(R.id.CreateHeroButton);
        createHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateHeroActivity();
            }
        });

        highScore = findViewById(R.id.HighScoreButton);
        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHighScoreActivity();
            }
        });
    }

    private void openChooseHeroActivity(){
        Intent intent = new Intent(this, ChooseHeroActivity.class);
        startActivity(intent);
    }

    private void openCreateHeroActivity(){
        Intent intent = new Intent(this, CreateHeroActivity.class);
        startActivity(intent);
    }

    private void openHighScoreActivity(){
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);
    }
}
