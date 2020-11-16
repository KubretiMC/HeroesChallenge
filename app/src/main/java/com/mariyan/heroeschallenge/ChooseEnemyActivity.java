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
    private Integer villainID =-1;
    private Integer heroID;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_enemy);

        ShowAllVillains();
        ListView villainsList=ShowAllVillains();

        heroID= Integer.valueOf(getIntent().getIntExtra("heroID",0));

        villainsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                villainID=position;
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
                if (villainID == -1) {
                    Toast.makeText(getApplicationContext(), "No enemy chosen!", Toast.LENGTH_LONG).show();
                    Notification notify = new Notification.Builder(getApplicationContext())
                            .setContentTitle("No enemy chosen!")
                            .setContentText("No enemy chosen!")
                            .build();
                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                } else {
                    openFightEnemyActivity();
                }
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private ListView ShowAllVillains() {
        res = findViewById(R.id.result);
        final ListView simpleList = findViewById(R.id.simpleListView);
        ArrayList<String> listResults = new ArrayList<>();

        for(int i = 0; i<Villain.list.size(); i++) {
            listResults.add(Villain.list.get(i).getName()+ "                         " + Villain.list.get(i).getAttack()+ "                         "
                    + Villain.list.get(i).getHitPoints() +"                        "+ (i+1));
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

    private void openFightEnemyActivity() {
        Intent intent=new Intent(getApplicationContext(),FightEnemyActivity.class);
        intent.putExtra("villainID", villainID);
        intent.putExtra("heroID", heroID);
        startActivity(intent);
    }
}
