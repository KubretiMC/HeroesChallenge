package com.mariyan.heroeschallenge;

import android.app.Notification;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class CreateHeroActivity extends AppCompatActivity {

    private TextView heroName;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hero);

        create = findViewById(R.id.CreateButton);
        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                createHero();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createHero() {
        heroName = findViewById(R.id.heroNamePlainText);
        String name = heroName.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Empty field", Toast.LENGTH_LONG).show();
            Notification notify = new Notification.Builder(getApplicationContext())
                    .setContentTitle("Empty field!")
                    .setContentText(name)
                    .build();
            notify.flags |= Notification.FLAG_AUTO_CANCEL;
        } else {
            boolean flag = false;
            for (Hero hero : Hero.list) {
                if (hero.getName().equals(name)) {
                    flag = true;
                    break;
                }
            }
            if (flag == true) {
                Toast.makeText(getApplicationContext(), "Hero name taken!", Toast.LENGTH_LONG).show();
                Notification notify = new Notification.Builder(getApplicationContext())
                        .setContentTitle("Hero name taken!")
                        .setContentText(name)
                        .build();
                notify.flags |= Notification.FLAG_AUTO_CANCEL;
            } else {
                try {
                    Integer id = Integer.valueOf(Hero.list.size()) + 1;
                    Hero hero = new Hero(id, name, 1, 5, 200, 1);
                    Hero.list.add(hero);


                    String q = "";
                    SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getPath() + "/" + "geroiOpit1.db", null);

                    //long count = DatabaseUtils.queryNumEntries(db, "geroiOpit1");
                    //int count2 = Integer.valueOf((int) count);
                    q = "INSERT INTO HEROES(name,attack,hitPoints,unspentPoints,status) VALUES(?,?,?,?,?);";
                    db.execSQL(q, new Object[]{name,1,5,200,1});
                    db.close();

                    Toast.makeText(getApplicationContext(), "Hero created successful!", Toast.LENGTH_LONG).show();
                    Notification notify = new Notification.Builder(getApplicationContext())
                            .setContentTitle("Hero created successful")
                            .setContentText(name)
                            .build();
                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                    finish();
                } catch (SQLiteException e) {
                    Notification notify = new Notification.Builder(getApplicationContext())
                            .setContentTitle("Error while working with database!")
                            .build();
                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                } catch (Exception e) {
                    Notification notify = new Notification.Builder(getApplicationContext())
                            .setContentTitle("Error while working with database!")
                            .build();
                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                }
            }
        }
    }
}