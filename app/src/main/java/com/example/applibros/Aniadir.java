package com.example.applibros;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applibros.utilidades.Utilidades;

public class Aniadir extends AppCompatActivity {
    TextView ed_TituloAniadir;
    TextView ed_AutorAniadir;
    TextView ed_DescripcionAniadir;
    String estado_libro ="";

    RadioGroup rg_Grupo;
    RadioButton rb_LeyendoAniadir;
    RadioButton rb_LeidoAniadir;
    RadioButton rb_DeseadoAniadir;
    RadioButton rb_ParaLeer;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aniadir);

        ed_TituloAniadir = (TextView) findViewById(R.id.ed_TituloAniadir);
        ed_AutorAniadir = (TextView) findViewById(R.id.ed_AutorAniadir);
        ed_DescripcionAniadir = (TextView) findViewById(R.id.ed_DescripcionAniadir);


        Button bt_VolverAniadir = (Button) findViewById(R.id.bt_volverAniadir);
        Button bt_BorrarAniadir = (Button) findViewById(R.id.bt_borrarAniadir);
        Button bt_GuardarAniadir = (Button) findViewById(R.id.bt_GuardarAniadir);


        rg_Grupo = (RadioGroup) findViewById(R.id.rg_aniadir);
        rb_DeseadoAniadir = (RadioButton) findViewById(R.id.rb_deseados);
        rb_LeidoAniadir = (RadioButton) findViewById(R.id.rb_leidos);
        rb_LeyendoAniadir = (RadioButton) findViewById(R.id.rb_leyendo);
        rb_ParaLeer = (RadioButton) findViewById(R.id.rb_paraleer);


        bt_VolverAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bt_BorrarAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_TituloAniadir.setText("");
                ed_AutorAniadir.setText("");
                ed_DescripcionAniadir.setText("");
                rg_Grupo.clearCheck();
            }
        });


        bt_GuardarAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String tit = ed_TituloAniadir.getText().toString();
                //String aut = ed_AutorAniadir.getText().toString();
                if (ed_TituloAniadir.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Falta el título", Toast.LENGTH_SHORT).show();
                }
                else if (ed_AutorAniadir.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Falta el autor", Toast.LENGTH_SHORT).show();
                }else if (estado_libro == "") {
                    Toast.makeText(getApplicationContext(), "¿Dónde quieres guardar tu libro?", Toast.LENGTH_SHORT).show();
                } else {
                    registrarLibro();
                    ed_TituloAniadir.setText("");
                    ed_AutorAniadir.setText("");
                    ed_DescripcionAniadir.setText("");
                    rg_Grupo.clearCheck();
                    estado_libro = "";
                    Toast.makeText(getApplicationContext(), "Libro registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_deseados:
                estado_libro = "deseado";
                break;
            case R.id.rb_leidos:
                estado_libro = "leido";
                break;
            case R.id.rb_leyendo:
                estado_libro = "leyendo";
                break;
            case R.id.rb_paraleer:
                estado_libro = "para leer";
                break;
        }
    }

    private void registrarLibro() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_libros", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase(); //abro la BD para editarla

        //recogemos los valores
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_TITULO, ed_TituloAniadir.getText().toString());
        values.put(Utilidades.CAMPO_AUTOR, ed_AutorAniadir.getText().toString());
        values.put(Utilidades.CAMPO_DESCRIPCION, ed_DescripcionAniadir.getText().toString());
        values.put(Utilidades.CAMPO_ESTADO, estado_libro);

        //insertamos los valores. usamos insert de SQLiteDatabase. Este método nos retorna un dato long dependiendo de los datos que enviamos
        Long idResultante = db.insert(Utilidades.TABLA_LIBRO, Utilidades.CAMPO_TITULO, values);
        db.close();

    }


}
