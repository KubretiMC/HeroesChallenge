package com.mariyan.heroeschallenge;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HeroActivity extends AppCompatActivity {

    TextView attack;
    TextView hitPoints;
    TextView unspentPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        attack = findViewById(R.id.heroAttackTextView);
        hitPoints=findViewById(R.id.heroHitPointsTextView);
        unspentPoints=findViewById(R.id.heroUnspentPointsTextView);

        attack.setText("2");
        hitPoints.setText("4");
        unspentPoints.setText("13");
    }
}
