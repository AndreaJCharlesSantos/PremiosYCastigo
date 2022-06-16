package com.upv.pm_2022;

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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    EditText ET;
    AlertDialog.Builder ADX;
    AlertDialog AD;
    Button BTN1,BTN2;
    ListView L_tarea;

    ArrayAdapter<String> adapter;

    final String NOMBRE_BASE_DATOS = "bdPyM.db";
    final String TABLA_PRINCIPAL = "tarea";
    final String TABLA_SECUNDARIA= "persona_tarea";
    ArrayList<String> ListaT = new ArrayList<String>();

    static bdPyM usdbh;
    SQLiteDatabase db;
    Cursor cursor;
    int SiguienteId;
    String[] argumentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        BTN1 = findViewById(R.id.BTN1);
        L_tarea = findViewById(R.id.L_tarea);


        usdbh = new bdPyM(this, "bdPyM.db", null, 1);
        ConsultaTabla_ActualizaControl();

        L_tarea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
                if (position == 0) {
                    Intent myIntent = new Intent(getApplicationContext(), MainActivity3.class);
                    startActivity(myIntent);
                }
            }
        });

        BTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
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

        cursor = usdbh.getReadableDatabase().rawQuery("select tarea_id, nombre_tarea, estado from tarea", null);

        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    tmp = cursor.getString(cursor
                            .getColumnIndexOrThrow("tarea_id")) + " - " + cursor.getString(cursor
                            .getColumnIndexOrThrow("nombre_tarea")) + " - " +  cursor.getString(cursor
                            .getColumnIndexOrThrow("estado")) ;
                    ListaT.add(tmp);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListaT);
        L_tarea.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
