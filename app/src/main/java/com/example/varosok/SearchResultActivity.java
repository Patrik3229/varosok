package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {

    private TextView textViewOsszesVaros;
    private Button buttonVisszaSearchResult;
    private SharedPreferences sharedPreferences;
    private DBHelper dbhelper;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        init();
        results();

        buttonVisszaSearchResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SearchResultActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void init(){
        textViewOsszesVaros = findViewById(R.id.textViewOsszesVaros);
        buttonVisszaSearchResult = findViewById(R.id.buttonVisszaSearchResult);
        sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        dbhelper = new DBHelper(this);

        sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void results(){

        String keresettSzo = sharedPreferences.getString("adat", "Nincs ilyen adat");
        Cursor adatok = dbhelper.adatLekerdezes(keresettSzo);
        if (adatok == null){
            textViewOsszesVaros.setText("Hiba történt a lekérdezés során");
            return;
        }
        if(adatok.getCount() == 0){
            textViewOsszesVaros.setText("Nem található rekord a következő adattal: " + keresettSzo);
            return;
        }

        StringBuilder builder = new StringBuilder();
        while (adatok.moveToNext()){
            builder.append(adatok.getString(1)).append("\n\n");
        }
        textViewOsszesVaros.setText(builder);
    }
}