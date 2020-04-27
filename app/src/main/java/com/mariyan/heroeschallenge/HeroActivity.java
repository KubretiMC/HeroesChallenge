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
    Button fightEnemy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        heroName = findViewById(R.id.heroNameTextView);
        heroAttack = findViewById(R.id.heroAttackTextView);
        heroHitPoints=findViewById(R.id.heroHitPointsTextView);
        heroUnspentPoints=findViewById(R.id.heroUnspentPointsTextView);
        upgrade=findViewById(R.id.upgradeButton);
        fightEnemy=findViewById(R.id.fightEnemyButton);


        Integer heroID= Integer.valueOf(getIntent().getIntExtra("heroID",0));
        String name = Hero.list.get(heroID).getName();
        heroName.setText(name);


        upgrade.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                openUpgradeActivity(heroID);
            }
        });

        fightEnemy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View V) {
                openChooseEnemyActivity();
            }
        });


    }

    @Override
    protected void onResume(){
        super.onResume();

        Integer heroID= Integer.valueOf(getIntent().getIntExtra("heroID",0));
        Integer attack = Hero.list.get(heroID).getAttack();
        Integer unspentPoints = Hero.list.get(heroID).getUnspentPoints();
        Integer hitPoints = Hero.list.get(heroID).getHitPoints();

        heroAttack.setText(attack.toString());
        heroHitPoints.setText(hitPoints.toString());
        heroUnspentPoints.setText(unspentPoints.toString());
    }

    private void openUpgradeActivity(Integer heroID) {
        Intent intent = new Intent(this, UpgradeActivity.class);
        intent.putExtra("heroID", heroID);
        startActivity(intent);
    }

    private void openChooseEnemyActivity() {
        Intent intent=new Intent(getApplicationContext(),ChooseEnemyActivity.class);
        startActivity(intent);
    }
}
