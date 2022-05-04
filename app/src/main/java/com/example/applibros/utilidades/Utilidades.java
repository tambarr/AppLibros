package com.example.applibros.utilidades;

public class Utilidades {

    //Constantes campos tabla libro
    public static final String TABLA_LIBRO = "libro";
    public static final String CAMPO_INDICE = "id";
    public static final String CAMPO_TITULO = "titulo";
    public static final String CAMPO_AUTOR = "autor";
    public static final String CAMPO_DESCRIPCION = "descripcion";
    public static final String CAMPO_ESTADO = "estado";



    public static final String CREAR_TABLA_LIBRO = "CREATE TABLE " + TABLA_LIBRO + " ("  + CAMPO_INDICE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CAMPO_TITULO + " TEXT, " + CAMPO_AUTOR + " TEXT, " + CAMPO_DESCRIPCION + " TEXT, " + CAMPO_ESTADO + " TEXT)";
}
