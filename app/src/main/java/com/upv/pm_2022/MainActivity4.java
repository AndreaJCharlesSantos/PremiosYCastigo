package com.upv.pm_2022;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity4 extends AppCompatActivity {
    EditText ET,EDT2,EDT3,EDT4,EDT5,ET2;
    AlertDialog.Builder ADX;
    AlertDialog AD;
    Button BTN1,BTN2;
    final String NOMBRE_BASE_DATOS = "bdPyM.db";
    final String TABLA_PRINCIPAL = "tarea";
    final String TABLA_SECUNDARIA= "persona";
    final String TABLA_TERCERA= "persona_tarea";
    String C1, C2, C3, C4, Fin;

    TextView TV1;
    bdPyM usdbh;
    SQLiteDatabase db;
    Cursor cursor;
    int SiguienteId;
    String[] argumentos;
    /*
    * 	RegistroAutos usdbh;
	SQLiteDatabase db;
	Cursor cursor;
	int SiguienteID;
	String[] argumentos;
    * */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //String[] objects = { "Andrea", "Julio", "Jose", "Adriana", "Maria"};
        //instanciias
        TV1 = findViewById(R.id.TV1);
        C1= "";
        C2= "";
        C3= "";
        Fin="";

        ET = (EditText) findViewById(R.id.ET);
        EDT2 = (EditText) findViewById(R.id.EDT2);
        EDT3 = (EditText) findViewById(R.id.EDT3);
        EDT4 = (EditText) findViewById(R.id.EDT4);
        EDT5 = (EditText) findViewById(R.id.EDT5);
        ET2 = (EditText) findViewById(R.id.ET2);
        BTN1 = (Button) findViewById(R.id.BTN1);
        BTN2 = (Button) findViewById(R.id.BTN2);

        argumentos = new String[1];

        ADX = new AlertDialog.Builder(this);
        AD = ADX.create();

        String ArchivoDB = NOMBRE_BASE_DATOS;

        usdbh = new bdPyM(this, ArchivoDB, null, 1);
        db = usdbh.getWritableDatabase();


        BTN1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(db != null)
                {
                    //Generamos los datos
                    int codigo = SiguienteId;
                    ContentValues values = new ContentValues();
                    values.put("nombre_tarea",ET.getText().toString());
                    values.put("estado",ET2.getText().toString());
                    values.put("fecha_asignada",EDT5.getText().toString()+"-"+EDT4.getText().toString()+"-"+EDT3.getText().toString());
                    db.insert(TABLA_PRINCIPAL,null,values);
                    //ConsultaTabla_ActualizaControl ();
                    AD.setMessage("Agregando nueva tarea");
                    AD.show();
                }
            }
        });
        cursor = db.rawQuery("select tarea_id, nombre_tarea,fecha_asignada ,estado from tarea", null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    C1 = cursor.getString(cursor
                            .getColumnIndexOrThrow("tarea_id")) + " - " + cursor.getString(cursor
                            .getColumnIndexOrThrow("nombre_tarea")) + " - " +  cursor.getString(cursor
                            .getColumnIndexOrThrow("estado")) +cursor.getString(cursor
                            .getColumnIndexOrThrow("fecha_asignada"));

                    Fin += C1  + "\n";

                } while (cursor.moveToNext());
            }
            TV1.setText(Fin);
        }
        cursor.close();
        TV1.setText(Fin);

        BTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this,MainActivity3.class);
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
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            case R.id.medium_text:
                // Set the text size to medium
                Intent intencion = new Intent(this, Activity2.class);
                startActivity(intencion);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}