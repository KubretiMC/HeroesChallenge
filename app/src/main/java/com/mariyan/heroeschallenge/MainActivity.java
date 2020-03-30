package com.mariyan.heroeschallenge;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button chooseHero;
    private Button createHero;
    private Button highScore;

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

    private void openChooseHeroActivity() {
        String q;
        ArrayList<Hero> heroes = new ArrayList<Hero>();
        try {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getPath() + "/" + "geroiOpit1.db", null);
            q = "CREATE TABLE if not exists geroiOpit1(";
            q += "ID integer primary key AUTOINCREMENT, ";
            q += "name text unique not null, ";
            q += "attack integer not null, ";
            q += "hitPoints integer not null, ";
            q += "status integer not null);";
            db.execSQL(q);



            db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getPath() + "/" + "geroiOpit1.db", null);
            q = "SELECT * FROM geroiOpit1";
            Cursor c = db.rawQuery(q, null);
            while (c.moveToNext()) {
                Integer id = c.getInt(c.getColumnIndex("ID"));
                String heroName = c.getString(c.getColumnIndex("name"));
                Integer heroAttackc = c.getInt(c.getColumnIndex("attack"));
                Integer heroHitPoints = c.getInt(c.getColumnIndex("hitPoints"));
                Integer heroStatus = c.getInt(c.getColumnIndex("status"));
                Hero hero = new Hero(id, heroName, heroAttackc, heroHitPoints, heroStatus);
                heroes.add(hero);
            }
            c.close();
            db.close();
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }


        Intent intent=new Intent(getApplicationContext(),ChooseHeroActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("123", heroes);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void openCreateHeroActivity() {
        Intent intent = new Intent(this, CreateHeroActivity.class);
        startActivity(intent);
    }

    private void openHighScoreActivity() {
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);
    }

    public void TakeHeroesFromSQL() {

//
    }
}
