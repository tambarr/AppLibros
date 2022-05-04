//Tamara Barrio Fernandez

package com.example.applibros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.applibros.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button bt_Leyendo;
    Button bt_Leido;
    Button bt_ParaLeer;
    Button bt_Deseados;
    Button bt_Aniadir;
    Button bt_Inspiracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_libros", null, 1);

        bt_Leyendo = findViewById(R.id.bt_Leyendo);
        bt_Leido = findViewById(R.id.bt_Leido);
        bt_ParaLeer = findViewById(R.id.bt_ParaLeer);
        bt_Deseados = findViewById(R.id.bt_Deseados);
        bt_Aniadir = findViewById(R.id.bt_Aniadir);
        bt_Inspiracion = findViewById(R.id.bt_Inspiracion);

        bt_Leyendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Leyendo.class));
            }
        });

        bt_Leido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Leidos.class));
            }
        });

        bt_ParaLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ParaLeer.class));
            }
        });

        bt_Deseados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Deseados.class));
            }
        });

        bt_Aniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Aniadir.class));
            }
        });

        bt_Inspiracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Inspiracion.class));
            }
        });
    }

}