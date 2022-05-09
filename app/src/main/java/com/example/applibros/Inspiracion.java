package com.example.applibros;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applibros.R;

public class Inspiracion extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inspiracion);


        Button bt_Lecturalia = (Button) findViewById(R.id.bt_lecturalia);
        bt_Lecturalia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webLecturalia = new Intent(Intent.ACTION_VIEW);
                webLecturalia.setData(Uri.parse("https://www.lecturalia.com/"));
                startActivity(webLecturalia);
            }
        });

        Button bt_Volver = (Button) findViewById(R.id.bt_volverInspiracion);
        bt_Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button bt_GoodReads = (Button) findViewById(R.id.bt_GoodreadsInspiracion);
        bt_GoodReads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webGoodReads = new Intent(Intent.ACTION_VIEW);
                webGoodReads.setData(Uri.parse("https://www.goodreads.com/"));
                startActivity(webGoodReads);
            }
        });



    }
}
