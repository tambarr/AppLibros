package com.example.applibros.entidades;

import java.io.Serializable;

public class Libro implements Serializable {
    private int numero;
    private String titulo;
    private String autor;
    private String descripcion;
    private String estado;



    public Libro(int numero, String titulo, String autor, String descripcion, String estado) {
        this.numero = numero;
        this.titulo = titulo;
        this.autor = autor;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Libro() {

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
