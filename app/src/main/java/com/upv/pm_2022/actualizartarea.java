package com.upv.pm_2022;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class actualizartarea extends AppCompatActivity {
    EditText ET2, EDT3, EDT2;
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
        setContentView(R.layout.actualizartarea);

        usdbh = new bdPyM(this, "bdPyM.db", null, 1);

        ET2=(EditText) findViewById(R.id.ET2);
        EDT2=(EditText) findViewById(R.id.EDT2);
        EDT3=(EditText) findViewById(R.id.EDT3);
        BTN1=(Button) findViewById(R.id.BTN1);
        BTN2=(Button)findViewById(R.id.BTN2);



        BTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int codigo = SiguienteId;
                ContentValues values = new ContentValues();
                values.put("id_persona",EDT2.getText().toString());
                values.put("tarea",EDT3.getText().toString());
                values.put("vigencia",ET2.getText().toString());
                db.insert(TABLA_SECUNDARIA,null,values);
                //ConsultaTabla_ActualizaControl ();
                AD.setMessage("TAREA ASIGNADA");
                AD.show();
            }
        });

    }



}
