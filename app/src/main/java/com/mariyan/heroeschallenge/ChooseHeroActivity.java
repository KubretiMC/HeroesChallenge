package com.mariyan.heroeschallenge;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChooseHeroActivity extends AppCompatActivity {

    private TextView res;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_hero);
        ShowAllHeroes();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void ShowAllHeroes() {
        //ArrayList<Hero> heroes = getIntent().getParcelableArrayListExtra("123");

        res = findViewById(R.id.result);
        final ListView simpleList = findViewById(R.id.simpleListView);
        ArrayList<String> listResults = new ArrayList<>();
        //assert heroes != null;

        for(int i = 0; i<Hero.list.size(); i++) {

            listResults.add(Hero.list.get(i).getId() + " \t " + Hero.list.get(i).getName() + " \t " + Hero.list.get(i).getAttack()
                    + " \t " + Hero.list.get(i).getHitPoints()+" \t "+Hero.list.get(i).getStatus());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.activity_list_view,
                R.id.result,
                listResults
        );
        simpleList.setAdapter(arrayAdapter);
    }
}
