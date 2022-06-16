package com.upv.pm_2022;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class mostrarp  extends AppCompatActivity {
    final String NOMBRE_BASE_DATOS = "bdPyM.db";
    final String TABLA_PRINCIPAL = "persona";
    final String TABLA_SECUNDARIA= "persona_tarea";
    ArrayList<String> listaP = new ArrayList<String>();
    Button BTN1;

    static bdPyM usdbh;
    SQLiteDatabase db;
    Cursor cursor;

    TextView TVP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarp);
        TVP = findViewById(R.id.TVP);
        BTN1 = findViewById(R.id.BTN1);

        Intent myIntent = getIntent();
        String inforecibida = myIntent.getExtras().getString("informacion");
        TVP.setText(inforecibida);




        BTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mostrarp.this,actualizartarea.class);
                startActivity(intent);
            }
        });





    }
}
