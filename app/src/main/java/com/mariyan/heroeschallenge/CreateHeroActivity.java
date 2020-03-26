package com.mariyan.heroeschallenge;

import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
            @Override
            public void onClick(View v) {
                createHero();
            }
        });

    }

    private void createHero() {
        heroName = findViewById(R.id.heroNamePlainText);
        try {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getPath() + "/" + "geroiOpit1.db", null);
            String q = "CREATE TABLE if not exists geroiOpit1(";
            q += "ID integer primary key AUTOINCREMENT, ";
            q += "name text unique not null, ";
            q += "attack integer not null, ";
            q += "hitPoints integer not null, ";
            q += "status integer not null);";
            db.execSQL(q);
            String name = heroName.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Empty field", Toast.LENGTH_LONG).show();
                Notification notify = new Notification.Builder(getApplicationContext())
                        .setContentTitle("Empty field!")
                        .setContentText(name)
                        .build();
                notify.flags |= Notification.FLAG_AUTO_CANCEL;
            } else {
                q = "SELECT name FROM geroiOpit1 where name = '"+name+"'";
                Cursor cursor = db.rawQuery(q, null);
                if (cursor.getCount() <= 0) {
                    cursor.close();

                    q = "INSERT INTO geroiOpit1(name,attack,hitPoints,status) VALUES(?, ?, ?, ?);";
                    db.execSQL(q, new Object[]{name, 1, 5, 1});
                    db.close();
                    Toast.makeText(getApplicationContext(), "Hero created successful!", Toast.LENGTH_LONG).show();
                    Notification notify = new Notification.Builder(getApplicationContext())
                            .setContentTitle("Task added successful")
                            .setContentText(name)
                            .build();
                    notify.flags |= Notification.FLAG_AUTO_CANCEL;

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    cursor.close();
                    Toast.makeText(getApplicationContext(), "Hero name taken!", Toast.LENGTH_LONG).show();
                    Notification notify = new Notification.Builder(getApplicationContext())
                            .setContentTitle("Hero name taken!")
                            .setContentText(name)
                            .build();
                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                }
            }
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