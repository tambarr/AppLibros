package com.example.applibros;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applibros.entidades.Libro;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.WeakHashMap;

public class DetallesLibro extends AppCompatActivity {

    TextView campoTitulo, campoAutor, campoDescripcion, campoNumero, campoEstado;
    Button borrar, editar, volver;
    ArrayList<Libro> listaLibro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_libro);

        campoNumero = findViewById(R.id.tv_numero);
        campoTitulo = findViewById(R.id.tv_tituloDetalles);
        campoAutor = findViewById(R.id.tv_autorDetalles);
        campoDescripcion = findViewById(R.id.tv_descripcionDetalles);
        campoEstado = findViewById(R.id.tv_estado);
        borrar = findViewById(R.id.bt_borrarDetalles);
        editar = findViewById(R.id.bt_editar);
        volver = findViewById(R.id.bt_volver);


        listaLibro = new ArrayList<>();


        Bundle objetoEnviado = getIntent().getExtras();
        Libro lib = null;
        if(objetoEnviado!=null){
            lib= (Libro) objetoEnviado.getSerializable("Libro");
            campoNumero.setText(String.valueOf(lib.getNumero()));
            campoTitulo.setText(lib.getTitulo());
            campoAutor.setText(lib.getAutor());
            campoDescripcion.setText(lib.getDescripcion());
            campoEstado.setText(lib.getEstado());
        }



        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = campoTitulo.getText().toString();
                String autor = campoAutor.getText().toString();
                String descripcion = campoDescripcion.getText().toString();
                String num = campoNumero.getText().toString();
                String estado = campoEstado.getText().toString();



                Intent intent = new Intent(DetallesLibro.this, Editar.class);

                Bundle b_titulo = new Bundle();
                b_titulo.putString("titulo", titulo);
                Bundle b_autor = new Bundle();
                b_autor.putString("autor", autor);
                Bundle b_descrip = new Bundle();
                b_descrip.putString("descripcion", descripcion);
                Bundle b_num = new Bundle();
                b_num.putString("numero", num);
                Bundle b_estado = new Bundle();
                b_estado.putString("estado", estado);

                intent.putExtras(b_titulo);
                intent.putExtras(b_autor);
                intent.putExtras(b_descrip);
                intent.putExtras(b_num);
                startActivity(intent);
                finish();


            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetallesLibro.this, MainActivity.class));
                finish();
            }
        });



        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetallesLibro.this);

                builder.setMessage("¿Confirmas que deseas borrar este libro?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                borrarLibro();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.create().show();

            }
        });




    }

    private void borrarLibro() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_libros", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        String num = String.valueOf(campoNumero.getText());
        db.execSQL("delete from libro where id = '" + num + "'");
        db.close();
        finish();
        //startActivity(new Intent(DetallesLibro.this, Leidos.class));
    }
   }