package com.example.applibros;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applibros.R;
import com.example.applibros.adaptadores.AdaptadorLibros;
import com.example.applibros.entidades.Libro;

import java.util.ArrayList;

public class Deseados extends AppCompatActivity {

    ConexionSQLiteHelper conn;
    ArrayList<Libro> listaLibro;
    RecyclerView recyclerViewLibro;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deseados);

        Button bt_volver = findViewById(R.id.bt_volverDeseados);
        Button bt_buscar = findViewById(R.id.bt_buscarDeseados);

        conn = new ConexionSQLiteHelper(this, "bd_libros", null, 1);
        listaLibro = new ArrayList<>();
        recyclerViewLibro = (RecyclerView) findViewById(R.id.recyclerViewDeseados);
        recyclerViewLibro.setLayoutManager(new LinearLayoutManager(this));

        consultarLibrosDeseados();
        AdaptadorLibros adapter = new AdaptadorLibros(listaLibro);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numero = listaLibro.get(recyclerViewLibro.getChildAdapterPosition(view)).getNumero();
                String num = String.valueOf(numero);
                Libro lib = listaLibro.get(recyclerViewLibro.getChildAdapterPosition(view));

                Intent intent = new Intent(Deseados.this, DetallesLibro.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("Libro", lib);
                //Toast.makeText(getApplicationContext(),num, Toast.LENGTH_SHORT).show();

                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });



        recyclerViewLibro.setAdapter(adapter);


        bt_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Deseados.this, MainActivity.class));
                finish();
            }
        });
        bt_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Deseados.this, Buscar.class));
                //finish();
            }
        });
    }

    private void consultarLibrosDeseados() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Libro libro = null;
        String[] args = new String[]{"deseado"};
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

