package com.example.applibros;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.applibros.adaptadores.AdaptadorLibros;
import com.example.applibros.entidades.Libro;
import com.example.applibros.utilidades.Utilidades;

import java.util.ArrayList;

public class Editar extends AppCompatActivity {
    TextView ed_TituloEditar, ed_numero;
    TextView ed_AutorEditar;
    TextView ed_DescripcionEditar;
    String estado_libroEditar ="";

    RadioGroup rg_GrupoEditar;
    RadioButton rb_LeyendoEditar;
    RadioButton rb_LeidoEditar;
    RadioButton rb_DeseadoEditar;
    RadioButton rb_ParaLeerEditar;


    Button volver, borrar, editar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);


        ed_numero = (TextView) findViewById(R.id.tv_numero);
        ed_TituloEditar = (TextView) findViewById(R.id.ed_TituloEditar);
        ed_AutorEditar = (TextView) findViewById(R.id.ed_AutorEditar);
        ed_DescripcionEditar = (TextView) findViewById(R.id.ed_DescripcionEditar);


        Button volver = (Button) findViewById(R.id.bt_volverEditar);
        Button borrar = (Button) findViewById(R.id.bt_borrarEditar);
        Button editar = (Button) findViewById(R.id.bt_EditarEditar);


        rg_GrupoEditar = (RadioGroup) findViewById(R.id.rg_aniadirEditar);
        rb_DeseadoEditar = (RadioButton) findViewById(R.id.rb_deseadosEditar);
        rb_LeidoEditar = (RadioButton) findViewById(R.id.rb_leidosEditar);
        rb_LeyendoEditar = (RadioButton) findViewById(R.id.rb_leyendoEditar);
        rb_ParaLeerEditar = (RadioButton) findViewById(R.id.rb_paraleerEditar);





        Bundle bolsa = getIntent().getExtras();
        ed_TituloEditar.setText(bolsa.getString("titulo"));
        ed_AutorEditar.setText(bolsa.getString("autor"));
        ed_DescripcionEditar.setText(bolsa.getString("descripcion"));
        ed_numero.setText(bolsa.getString("numero"));


        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estado_libroEditar == "") {
                    Toast.makeText(getApplicationContext(), "¿Dónde quieres guardar tu libro?", Toast.LENGTH_SHORT).show();
                } else {

                modificarLibro();
                finish();
                }
            }
        });


        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_TituloEditar.setText("");
                ed_AutorEditar.setText("");
                ed_DescripcionEditar.setText("");
                rg_GrupoEditar.clearCheck();
            }
        });


    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_deseadosEditar:
                estado_libroEditar = "deseado";
                break;
            case R.id.rb_leidosEditar:
                estado_libroEditar = "leido";
                break;
            case R.id.rb_leyendoEditar:
                estado_libroEditar = "leyendo";
                break;
            case R.id.rb_paraleerEditar:
                estado_libroEditar = "para leer";
                break;
        }
    }


    private void modificarLibro() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_libros", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase(); //abro la BD para editarla

        String[] parametros = {ed_numero.getText().toString()};
        //Toast.makeText(getApplicationContext(),parametros, Toast.LENGTH_SHORT).show();

        //recogemos los valores
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_TITULO, ed_TituloEditar.getText().toString());
        values.put(Utilidades.CAMPO_AUTOR, ed_AutorEditar.getText().toString());
        values.put(Utilidades.CAMPO_DESCRIPCION, ed_DescripcionEditar.getText().toString());
        values.put(Utilidades.CAMPO_ESTADO, estado_libroEditar);
        //values.put(Utilidades.CAMPO_INDICE, ed_numero.getText().toString());

        db.update(Utilidades.TABLA_LIBRO, values, Utilidades.CAMPO_INDICE+"=?",parametros);
                //, autor = ed_AutorEditar, descripcion = ed_DescripcionEditar, estado = estado_libroEditar where id=ed_numero");
        db.close();

    }

}