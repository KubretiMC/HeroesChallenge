package com.mariyan.heroeschallenge;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpgradeActivity extends AppCompatActivity {

    TextView heroUnspentPoints;
    TextView heroAttack;
    TextView heroHitPoints;
    Button addAttack;
    Button addHitPoints;
    Button confirm;
    Integer heroID;
    Integer attack;
    Integer hitPoints;
    Integer unspentPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);


        heroUnspentPoints = findViewById(R.id.heroUnspentPointsTextView);
        heroAttack = findViewById(R.id.heroAttackTextView);
        heroHitPoints = findViewById(R.id.heroHitPointsTextView);
        addAttack = findViewById(R.id.addAttackButton);
        addHitPoints = findViewById(R.id.addHitPointsButton);
        confirm = findViewById(R.id.comfirmButton);

        heroID= Integer.valueOf(getIntent().getIntExtra("heroID",-1));
        attack = Hero.list.get(heroID).getAttack();
        hitPoints = Hero.list.get(heroID).getHitPoints();
        unspentPoints = Hero.list.get(heroID).getUnspentPoints();
        Integer pointsChange=unspentPoints;

        heroUnspentPoints.setText(unspentPoints.toString());
        heroAttack.setText(attack.toString());
        heroHitPoints.setText(hitPoints.toString());

        addAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(add())
                {
                    attack++;
                    heroAttack.setText(attack.toString());
                };
            }
        });
        addHitPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(add())
                {
                    hitPoints++;
                    heroHitPoints.setText(hitPoints.toString());
                };
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pointsChange.equals(unspentPoints)) {
                    Toast.makeText(getApplicationContext(), "You didn't make any changes!", Toast.LENGTH_LONG).show();
                    Notification notify = new Notification.Builder(getApplicationContext())
                            .setContentTitle("You didn't make any changes!")
                            .setContentText("You didn't make any changes!")
                            .build();
                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                }else{
                    Hero.list.get(heroID).setAttack(attack);
                    Hero.list.get(heroID).setHitPoints(hitPoints);
                    Hero.list.get(heroID).setUnspentPoints(unspentPoints);
                    MainActivity.updatedHeroes.add(heroID);
                    Toast.makeText(getApplicationContext(), "Hero stats updated!", Toast.LENGTH_LONG).show();
                    Notification notify = new Notification.Builder(getApplicationContext())
                            .setContentTitle("Hero stats updated!")
                            .setContentText("Hero stats updated!")
                            .build();
                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                }
            }
        });

    }
    public Boolean add() {
        if (unspentPoints > 0) {
            unspentPoints -= 1;
            //attributePoints += 1;
            heroUnspentPoints.setText(unspentPoints.toString());
            //attributeName.setText(attributePoints.toString());
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Not enough points!", Toast.LENGTH_LONG).show();
            Notification notify = new Notification.Builder(getApplicationContext())
                    .setContentTitle("Not enough points!")
                    .setContentText("Not enough points!")
                    .build();
            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            return false;
        }
    }
}
