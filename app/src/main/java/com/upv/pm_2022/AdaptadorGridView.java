package com.upv.pm_2022;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorGridView extends BaseAdapter {


    public Context context;
    private int layout;
    private List<String> identificador;
    private List<String> nombre;
    private List<String> Tareas;

    public AdaptadorGridView(Context context, int layout,List<String> identificador, List<String> nombre, List<String> Tareas){
        this.context = context;
        this.identificador = identificador;
        this.nombre = nombre;
        this.Tareas = Tareas;
    }


    @Override
    public int getCount() {
        return identificador.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        v = layoutInflater.inflate(R.layout.elementogrid, null);

        String idP = identificador.get(position);
//        String idP = idP.get(position);
        String nombreActual = nombre.get(position);
        String tareActual = Tareas.get(position);

        TextView txt_id = v.findViewById(R.id.txt_idP);
        TextView txt_nombre = v.findViewById(R.id.txt_nombre);
        TextView txt_tarea = v.findViewById(R.id.txt_tarea);

        txt_id.setText(idP);
        txt_nombre.setText(nombreActual);
        txt_tarea.setText(tareActual);

        return v;
    }
}
