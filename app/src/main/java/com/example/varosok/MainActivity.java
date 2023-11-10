package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextOrszag;
    private Button buttonKereses;
    private Button buttonUjFelvetele;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        buttonKereses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data();
            }
        });

        buttonUjFelvetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void init(){
        editTextOrszag = findViewById(R.id.editTextOrszag);
        buttonKereses = findViewById(R.id.buttonKereses);
        buttonUjFelvetele = findViewById(R.id.buttonUjFelvetele);
    }

    public void data(){
        if (editTextOrszag.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nem lehet üres az ország mező", Toast.LENGTH_SHORT).show();
        }
        else {
            SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("adat", editTextOrszag.getText().toString());
            editor.apply();
            Toast.makeText(MainActivity.this, "Sikeres adatátvitel", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
            startActivity(intent);
            finish();
        }
    }
}