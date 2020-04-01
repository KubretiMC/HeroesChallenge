package com.mariyan.heroeschallenge;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChooseHeroActivity extends AppCompatActivity {
    private Button choose;
    private TextView res;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_hero);

        choose=findViewById(R.id.ChooseButton);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Integer position=ShowAllHeroes().getSelectedItemPosition();
                //String d = position.toString();
                //choose.setText(d);
                openHeroActivity();
            }
        });

        ShowAllHeroes();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void ShowAllHeroes() {
        res = findViewById(R.id.result);
        final ListView simpleList = findViewById(R.id.simpleListView);
        ArrayList<String> listResults = new ArrayList<>();

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

    private void openHeroActivity() {
        Intent intent=new Intent(getApplicationContext(),HeroActivity.class);
        startActivity(intent);
    }
}
