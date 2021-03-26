package com.mariyan.heroeschallenge;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button chooseHero;
    private Button createHero;
    private Button highScore;
    public static List updatedHeroes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chooseHero = findViewById(R.id.ChooseHeroButton);
        chooseHero.setOnClickListener(new View.OnClickListener() {
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

        TakeHeroesFromSQL();
    }

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!updatedHeroes.isEmpty())
        {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(new File(getFilesDir().getPath() + "/" + "geroiOpit1.db"), null);
            ContentValues cv = new ContentValues();
            Integer j;
            for(int i=0;i<updatedHeroes.size();i++)
            {
                j= (Integer) updatedHeroes.get(i);
                cv.put("attack",Hero.list.get(j).getAttack());
                cv.put("hitPoints",Hero.list.get(j).getHitPoints());
                cv.put("unspentPoints",Hero.list.get(j).getUnspentPoints());
                db.update("HEROES", cv, "ID=?", new String[]{(++j).toString()});
            }
        }
        if(!Hero.list.isEmpty()) {
            updateHeroesSQL();
            Hero.list.clear();
        }
    }

    private void openChooseHeroActivity() {
        Intent intent=new Intent(getApplicationContext(),ChooseHeroActivity.class);
        startActivity(intent);
    }

    private void openCreateHeroActivity() {
        Intent intent = new Intent(getApplicationContext(), CreateHeroActivity.class);
        startActivity(intent);
    }

    private void openHighScoreActivity() {
        Intent intent = new Intent(getApplicationContext(), HighScoreActivity.class);
        startActivity(intent);
    }

    public void TakeHeroesFromSQL() {
            String q="";

            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getPath() + "/" + "geroiOpit1.db", null);
            createTableIfNotExistsHeroes(q,db);
            createTableIfNotExistsVillains(q,db);

            //db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getPath() + "/" + "geroiOpit1.db", null);
            q = "SELECT * FROM HEROES";
            Cursor c = db.rawQuery(q, null);
            while (c.moveToNext()) {
                Integer id = c.getInt(c.getColumnIndex("ID"));
                String heroName = c.getString(c.getColumnIndex("name"));
                Integer heroAttack = c.getInt(c.getColumnIndex("attack"));
                Integer heroHitPoints = c.getInt(c.getColumnIndex("hitPoints"));
                Integer unspentPoints = c.getInt(c.getColumnIndex("unspentPoints"));
                Integer heroStatus = c.getInt(c.getColumnIndex("status"));
                Hero hero = new Hero(id, heroName, heroAttack, heroHitPoints, unspentPoints, heroStatus);
                Hero.list.add(hero);
            }

            
            q = "SELECT * FROM VILLAINS";
            c = db.rawQuery(q, null);
                    for(int i=1;i<5;i++) {
              q = "INSERT INTO VILLAINS(name,attack,hitPoints) VALUES(?,?,?);";
          db.execSQL(q, new Object[]{"Villain      "+i, 1, 5});
            }

        while (c.moveToNext()) {
                Integer id = c.getInt(c.getColumnIndex("ID"));
                String heroName = c.getString(c.getColumnIndex("name"));
                Integer heroAttack = c.getInt(c.getColumnIndex("attack"));
                Integer heroHitPoints = c.getInt(c.getColumnIndex("hitPoints"));
                Villain villain = new Villain(id, heroName, heroAttack, heroHitPoints);
                Villain.list.add(villain);
            }

            c.close();
            db.close();

    }

    public void updateHeroesSQL()
    {
        String q="";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getPath() + "/" + "geroiOpit1.db", null);;
        long count =  DatabaseUtils.queryNumEntries(db, "HEROES");
        int count2 = Integer.valueOf((int) count);
        if(Hero.list.size()>count) {
            for(int i=count2;i<Hero.list.size();i++) {
                q = "INSERT INTO HEROES(name,attack,hitPoints,unspentPoints,status) VALUES(?,?,?,?,?);";
                db.execSQL(q, new Object[]{Hero.list.get(i).getName(), Hero.list.get(i).getAttack(),
                Hero.list.get(i).getHitPoints(),Hero.list.get(i).getUnspentPoints(), Hero.list.get(i).getStatus()});
            }
        }
        db.close();
    }

    public void createTableIfNotExistsHeroes(String q, SQLiteDatabase db)
    {
        q = "CREATE TABLE if not exists Heroes(";
        q += "ID integer primary key AUTOINCREMENT, ";
        q += "name text unique not null, ";
        q += "attack integer not null, ";
        q += "hitPoints integer not null, ";
        q += "unspentPoints integer not null, ";
        q += "status integer not null);";
        db.execSQL(q);
    }

    public void createTableIfNotExistsVillains(String q, SQLiteDatabase db)
    {
        q = "CREATE TABLE if not exists Villains(";
        q += "ID integer primary key AUTOINCREMENT, ";
        q += "name text not null, ";
        q += "attack integer not null, ";
        q += "hitPoints integer not null);";
        db.execSQL(q);
    }

}
