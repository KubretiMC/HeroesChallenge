package com.mariyan.heroeschallenge;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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

    TextView fightInfo;


    Integer heroID;
    Integer villainID;

    Button attack;

    Integer heroAttack2;
    Integer heroHitPoints2;
    Integer villainAttack2;
    Integer villainHitPoints2;
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

        fightInfo=findViewById(R.id.unspentPointsTextView);

        heroID= Integer.valueOf(getIntent().getIntExtra("heroID",0));
        villainID=Integer.valueOf(getIntent().getIntExtra("villainID",0));

        String heroName2 = Hero.list.get(heroID).getName();
        heroAttack2 = Hero.list.get(heroID).getAttack();
        heroHitPoints2 = Hero.list.get(heroID).getHitPoints();

        String villainName2 = Villain.list.get(villainID).getName();
        villainAttack2 = Villain.list.get(villainID).getAttack();
        villainHitPoints2 = Villain.list.get(villainID).getHitPoints();

        heroName.setText(heroName2);
        heroAttack.setText(heroAttack2.toString());
        heroHitPoints.setText(heroHitPoints2.toString());

        villainName.setText(villainName2);
        villainAttack.setText(villainAttack2.toString());
        villainHitPoints.setText(villainHitPoints2.toString());

        fightInfo.setText("Fight not started yet");

        attack=findViewById(R.id.attackButton);
        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fight(villainHitPoints2-heroAttack2, heroHitPoints2-villainAttack2,v);
            }
        });
    }

    private void fight(Integer villainHpLeft, Integer heroHpLeft, View v)
    {
//        Integer villainHpLeft=villainHitPoints2-heroAttack2;
//        Integer heroHpLeft=heroHitPoints2-villainAttack2;

        fightInfo.setText("You dealt "+heroAttack2+" damage and the opponent has " + villainHpLeft+" hit points left. " +
                " He attacks and deals you " +
                villainAttack2+" damage and you have "+heroHpLeft+" hit points left.");
        heroHitPoints2=heroHpLeft;
        villainHitPoints2=villainHpLeft;
        heroHitPoints.setText(heroHpLeft.toString());
        villainHitPoints.setText(villainHpLeft.toString());
        if(heroHitPoints2<=0 && villainHitPoints2<=0)
        {
            Toast.makeText(getApplicationContext(), "You both lose!", Toast.LENGTH_LONG).show();
            Notification notify = new Notification.Builder(getApplicationContext())
                    .setContentTitle("You both lose!")
                    .setContentText("")
                    .build();
            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            attack.setText("END");
            fightInfo.setText("You both lose!");
            attack.setEnabled(false);
            onButtonShowPopupWindowClick(v);

        }
        else  if(heroHitPoints2<=0)
        {
            Toast.makeText(getApplicationContext(), "You lost!", Toast.LENGTH_LONG).show();
            Notification notify = new Notification.Builder(getApplicationContext())
                    .setContentTitle("You lost!")
                    .setContentText("")
                    .build();
            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            attack.setText("END");
            fightInfo.setText("You lost!");
            attack.setEnabled(false);
        }
        else  if(villainHitPoints2<=0)
        {
            Toast.makeText(getApplicationContext(), "You won!", Toast.LENGTH_LONG).show();
            Notification notify = new Notification.Builder(getApplicationContext())
                    .setContentTitle("You won!")
                    .setContentText("")
                    .build();
            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            attack.setText("END");
            fightInfo.setText("You won!");
            attack.setEnabled(false);
        }
    }

    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}