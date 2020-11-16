package com.mariyan.heroeschallenge;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class FightEnemyActivity extends AppCompatActivity {
    TextView heroName;
    TextView heroAttack;
    TextView heroHitPoints;

    TextView villainName;
    TextView villainAttack;
    TextView villainHitPoints;

    Integer heroID;
    Integer villainID;

    Button attack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_enemy);

        heroName = findViewById(R.id.heroNameTextView);
        heroAttack = findViewById(R.id.heroAttackTextView);
        heroHitPoints=findViewById(R.id.heroHitPointsTextView);

        villainName=findViewById(R.id.villainNameTextView);
        villainAttack=findViewById(R.id.villainAttackTextView);
        villainHitPoints=findViewById(R.id.villainHitPointsTextView);


        heroID= Integer.valueOf(getIntent().getIntExtra("heroID",0));
        villainID=Integer.valueOf(getIntent().getIntExtra("villainID",0));

        String heroName2 = Hero.list.get(heroID).getName();
        Integer heroAttack2 = Hero.list.get(heroID).getAttack();
        Integer heroHitPoints2 = Hero.list.get(heroID).getHitPoints();

        String villainName2 = Villain.list.get(villainID).getName();
        Integer villainAttack2 = Villain.list.get(villainID).getAttack();
        Integer villainHitPoints2 = Villain.list.get(villainID).getHitPoints();

        heroName.setText(heroName2);
        heroAttack.setText(heroAttack2.toString());
        heroHitPoints.setText(heroHitPoints2.toString());

        villainName.setText(villainName2);
        villainAttack.setText(villainAttack2.toString());
        villainHitPoints.setText(villainHitPoints2.toString());


        attack=findViewById(R.id.attackButton);
        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // int position = (Integer) v.getTag();
                //Integer position=ShowAllHeroes().getSelectedItemPosition();
                //String d = position.toString();
                //choose.setText(position);
                if (heroID == -1) {
                    Toast.makeText(getApplicationContext(), "No hero chosen!", Toast.LENGTH_LONG).show();
                    Notification notify = new Notification.Builder(getApplicationContext())
                            .setContentTitle("Empty field!")
                            .setContentText("No hero chosen!")
                            .build();
                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                } else {

                    //openHeroActivity();
                }
            }
        });


    }

}
