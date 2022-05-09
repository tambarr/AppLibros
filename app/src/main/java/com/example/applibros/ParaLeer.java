package com.example.applibros;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applibros.R;
import com.example.applibros.adaptadores.AdaptadorLibros;
import com.example.applibros.entidades.Libro;
import com.example.applibros.utilidades.Utilidades;

import java.util.ArrayList;

public class ParaLeer extends AppCompatActivity {


    ConexionSQLiteHelper conn;
    ArrayList<Libro> listaLibro;
    RecyclerView recyclerViewLibro;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paraleer);

        Button bt_volver = findViewById(R.id.bt_volverParaLeer);
        Button bt_buscar = findViewById(R.id.bt_buscarParaLeer);


        conn = new ConexionSQLiteHelper(this, "bd_libros", null, 1);
        listaLibro = new ArrayList<>();
        recyclerViewLibro = (RecyclerView) findViewById(R.id.recyclerViewParaLeer);
        recyclerViewLibro.setLayoutManager(new LinearLayoutManager(this));

        consultarLibrosParaLeer();
        AdaptadorLibros adapter = new AdaptadorLibros(listaLibro);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numero = listaLibro.get(recyclerViewLibro.getChildAdapterPosition(view)).getNumero();
                String num = String.valueOf(numero);
                Libro lib = listaLibro.get(recyclerViewLibro.getChildAdapterPosition(view));

                Intent intent = new Intent(ParaLeer.this, DetallesLibro.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("Libro", lib);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });



        recyclerViewLibro.setAdapter(adapter);


        bt_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParaLeer.this, Buscar.class));
            }
        });
    }

    private void consultarLibrosParaLeer() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Libro libro = null;
        String[] args = new String[]{"para leer"};
        Cursor cursor = db.rawQuery("select * from libro where estado = ?", args);
        while (cursor.moveToNext()){
            libro = new Libro();

            libro.setNumero(cursor.getInt(0));
            libro.setTitulo(cursor.getString(1));
            libro.setAutor(cursor.getString(2));
            libro.setDescripcion(cursor.getString(3));
            libro.setEstado(cursor.getString(4));

            listaLibro.add(libro);
        }
    }


}