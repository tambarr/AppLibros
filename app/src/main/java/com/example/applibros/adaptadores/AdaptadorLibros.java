package com.example.applibros.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applibros.R;
import com.example.applibros.entidades.Libro;

import java.util.ArrayList;

/*
Este AdaptadorLibros es el que se va a encargar de alimentar el item_list_libros
 */

public class AdaptadorLibros extends RecyclerView.Adapter<AdaptadorLibros.ViewHolderLibros>
implements View.OnClickListener{
    /*
    Generamos la lista
     */
    ArrayList<Libro> listaLibros;
    private View.OnClickListener listener;

    public AdaptadorLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }


    @NonNull
    @Override
    public ViewHolderLibros onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
        Inflamos mediate un View el itemList
         */
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_libros, null, false);

        view.setOnClickListener(this);

        return new ViewHolderLibros(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLibros holder, int position) {
        holder.etiTitulo.setText(listaLibros.get(position).getTitulo());
        holder.etiAutor.setText(listaLibros.get(position).getAutor());
    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    /*
    Aquí tenemos que poner la referencia a esos elementos, los cuales tienen que llegar a una lista que va a referenciar cada uno de los datos que queremos presentar,
    por eso hay que generar un ArrayList
     */
    public class ViewHolderLibros extends RecyclerView.ViewHolder {

        TextView etiTitulo, etiAutor;

        public ViewHolderLibros(@NonNull View itemView) {
            super(itemView);
            /*
            con estas referencias podemos empezar a alimentar la información
             */
            etiTitulo = itemView.findViewById(R.id.idTitulo);
            etiAutor = itemView.findViewById(R.id.idAutor);
        }
    }
}
