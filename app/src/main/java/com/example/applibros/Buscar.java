package com.example.applibros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.applibros.entidades.Libro;
import com.example.applibros.utilidades.Utilidades;

import java.util.ArrayList;

public class Buscar extends AppCompatActivity {

    ConexionSQLiteHelper conn;

    EditText ed_buscar;
    RadioGroup rg_GrupoBuscar;
    RadioButton rb_titulo;
    RadioButton rb_autor;
    Button buscar, volver;
    String queBuscar="titulo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        ed_buscar = findViewById(R.id.ed_buscar);
        rg_GrupoBuscar = findViewById(R.id.rg_buscar);
        rb_titulo = findViewById(R.id.rb_titulo);
        rb_autor = findViewById(R.id.rb_autor);
        buscar = findViewById(R.id.botonBuscar);
        volver = findViewById(R.id.bt_volverBuscar);



        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busqueda = ed_buscar.getText().toString();

                Intent intent = new Intent(Buscar.this, Buscados.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("busqueda", busqueda);
                bundle.putSerializable("queBuscar", queBuscar);

                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Buscar.this, MainActivity.class));
                finish();
            }
        });


    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_autor:
                queBuscar = "autor";
                break;
            case R.id.rb_titulo:
                queBuscar = "titulo";
                break;
        }
    }


}