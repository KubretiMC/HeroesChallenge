package com.mariyan.heroeschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button ChooseHero;
    private Button CreateHero;
    private Button HighScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChooseHero = findViewById(R.id.ChooseHero);
        ChooseHero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenChooseHeroActivity();
            }
        });

        CreateHero = findViewById(R.id.CreateHero);
        CreateHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCreateHeroActivity();
            }
        });

        HighScore = findViewById(R.id.HighScore);
        HighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHighScoreActivity();
            }
        });
    }

    private void OpenChooseHeroActivity(){
        Intent intent = new Intent(this, ChooseHeroActivity.class);
        startActivity(intent);
    }

    private void OpenCreateHeroActivity(){
        Intent intent = new Intent(this, CreateHeroActivity.class);
        startActivity(intent);
    }

    private void OpenHighScoreActivity(){
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);
    }
}
