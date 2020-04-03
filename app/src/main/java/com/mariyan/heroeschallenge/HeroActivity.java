package com.mariyan.heroeschallenge;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class HeroActivity extends AppCompatActivity {
    TextView heroName;
    TextView heroAttack;
    TextView heroHitPoints;
    TextView heroUnspentPoints;
    Button upgrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        heroName = findViewById(R.id.heroNameTextView);
        heroAttack = findViewById(R.id.heroAttackTextView);
        heroHitPoints=findViewById(R.id.heroHitPointsTextView);
        heroUnspentPoints=findViewById(R.id.heroUnspentPointsTextView);
        upgrade=findViewById(R.id.upgradeButton);

        Integer heroID= Integer.valueOf(getIntent().getIntExtra("heroID",0));
        String name = Hero.list.get(heroID).getName();
        Integer attack = Hero.list.get(heroID).getAttack();
        Integer hitPoints = Hero.list.get(heroID).getHitPoints();

        heroName.setText(name);
        heroAttack.setText(attack.toString());
        heroHitPoints.setText(hitPoints.toString());
        heroUnspentPoints.setText("1");

        upgrade.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                openUpgradeActivity();
            }
        });
    }

    private void openUpgradeActivity() {
        Intent intent = new Intent(this, UpgradeActivity.class);
        startActivity(intent);
    }
}
