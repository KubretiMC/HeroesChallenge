package com.mariyan.heroeschallenge;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChooseEnemyActivity extends AppCompatActivity {
    private Button choose;
    private TextView res;
    private Integer heroID=-1;
    public static ArrayList<Hero> list = new ArrayList<Hero>();


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_enemy);



        ShowAllHeroes();

        ListView heroesList=ShowAllHeroes();

        heroesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                heroID=position;
            }
        });

        choose=findViewById(R.id.ChooseButton);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // int position = (Integer) v.getTag();
                //Integer position=ShowAllHeroes().getSelectedItemPosition();
                //String d = position.toString();
                //choose.setText(position);
                if (heroID == -1) {
                    Toast.makeText(getApplicationContext(), "No enemy chosen!", Toast.LENGTH_LONG).show();
                    Notification notify = new Notification.Builder(getApplicationContext())
                            .setContentTitle("No enemy chosen!")
                            .setContentText("No enemy chosen!")
                            .build();
                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                } else {

                    openHeroActivity();
                }
            }
        });

    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private ListView ShowAllHeroes() {
        res = findViewById(R.id.result);
        final ListView simpleList = findViewById(R.id.simpleListView);
        ArrayList<String> listResults = new ArrayList<>();

        for(int i = 0; i<Hero.list.size(); i++) {

            listResults.add(Hero.list.get(i).getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.activity_list_view,
                R.id.result,
                listResults
        );
        simpleList.setAdapter(arrayAdapter);
        return simpleList;

    }

    private void openHeroActivity() {
        Intent intent=new Intent(getApplicationContext(),HeroActivity.class);
        intent.putExtra("heroID", heroID);
        startActivity(intent);
    }
}
