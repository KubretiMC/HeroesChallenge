package com.mariyan.heroeschallenge;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChooseHeroActivity extends AppCompatActivity {

    private TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_hero);
        ShowAllHeroes();
    }

    private void ShowAllHeroes() {
        res = findViewById(R.id.result);
        final ListView simpleList = findViewById(R.id.simpleListView);
        String q;
        ArrayList<String> listResults = new ArrayList<>();
        try {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getPath() + "/" + "geroiOpit1.db", null);
            q = "SELECT * FROM geroiOpit1";
            Cursor c = db.rawQuery(q, null);
            while (c.moveToNext()) {
                String name = c.getString(c.getColumnIndex("name"));
                Integer attack = c.getInt(c.getColumnIndex("attack"));
                Integer hitPoints = c.getInt(c.getColumnIndex("hitPoints"));
                Integer status = c.getInt(c.getColumnIndex("status"));
                listResults.add(name + " \t " + attack + " \t " + hitPoints + " \t " + status);
            }
            c.close();
            db.close();

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    R.layout.activity_list_view,
                    R.id.result,
                    listResults
            );
            simpleList.setAdapter(arrayAdapter);


        } catch (SQLiteException e) {
            res.setText("Грешка при работа с БД: " + e.getLocalizedMessage() + e.getStackTrace());
        }
    }
}
