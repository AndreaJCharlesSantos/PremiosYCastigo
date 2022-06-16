package com.upv.pm_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    EditText ET;
    AlertDialog.Builder ADX;
    AlertDialog AD;
    Button BTN1,BTN2;
    ListView L_persona;

    ArrayAdapter<String> adapter;

    final String NOMBRE_BASE_DATOS = "bdPyM.db";
    final String TABLA_PRINCIPAL = "persona";
    final String TABLA_SECUNDARIA= "persona_tarea";
    ArrayList<String> listaP = new ArrayList<String>();

    static bdPyM usdbh;
    SQLiteDatabase db;
    Cursor cursor;
    int SiguienteId;
    String[] argumentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BTN1 = findViewById(R.id.BTN1);
        L_persona = findViewById(R.id.L_persona);


        usdbh = new bdPyM(this, "bdPyM.db", null, 1);
        ConsultaTabla_ActualizaControl();

        L_persona.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
                String selectedP = adapter.getItem(position);
                Intent myIntent = new Intent(getApplicationContext(), mostrarp.class);
                myIntent.putExtra("informacion",selectedP);
                startActivity(myIntent);

            }
        });

        BTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.medium_text:
                // Set the text size to medium
                Intent intencion = new Intent(this, Activity2.class);
                startActivity(intencion);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
    void ConsultaTabla_ActualizaControl ()
    {
        String tmp = "";
        cursor = usdbh.getReadableDatabase().rawQuery("select nombre_persona from persona", null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                tmp = cursor.getString(cursor.getColumnIndexOrThrow("nombre_persona"));
                listaP.add(tmp);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaP);
        L_persona.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
