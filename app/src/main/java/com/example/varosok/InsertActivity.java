package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private EditText editTextNevInsert;
    private EditText editTextOrszagInsert;
    private EditText editTextLakossagInsert;
    private Button buttonFelvetelInsert;
    private Button buttonVisszaInsert;
    private DBHelper dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        init();

        buttonFelvetelInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextNevInsert.getText().toString().trim().isEmpty() || editTextOrszagInsert.getText().toString().trim().isEmpty() || editTextLakossagInsert.getText().toString().trim().isEmpty()){
                    Toast.makeText(InsertActivity.this, "Nem lehet üres egyik mező sem.", Toast.LENGTH_SHORT).show();
                }
                else{
                    int lakossagInt = Integer.parseInt(editTextLakossagInsert.getText().toString());
                    if (dbhelper.rogzites(editTextNevInsert.getText().toString(), editTextOrszagInsert.getText().toString(), lakossagInt)){
                        Toast.makeText(InsertActivity.this, "Sikeres adatfelvétel!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(InsertActivity.this, "Nem sikerült az adatfelvétel", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonVisszaInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void init(){
        editTextNevInsert = findViewById(R.id.editTextNevInsert);
        editTextOrszagInsert = findViewById(R.id.editTextOrszagInsert);
        editTextLakossagInsert = findViewById(R.id.editTextLakossagInsert);
        buttonFelvetelInsert = findViewById(R.id.buttonFelvetelInsert);
        buttonVisszaInsert = findViewById(R.id.buttonVisszaInsert);

        dbhelper = new DBHelper(InsertActivity.this);
    }
}