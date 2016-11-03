package com.example.cesar.testcomunicasionurl;

/**
 * Created by Cesar on 03/11/2016.
 */
public class Animal {
    private String especie;
    private String descripcion;
    private String imagen;

    public Animal(String especie, String descripcion, String imagen){
        this.especie = especie;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getEspecie(){
        return especie;
    }

    public void setEspecie(String especie){
        this.especie = especie;
    }

    public String  getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getImagen(){
        return imagen;
    }

    public void setImagen(String imagen){
        this.imagen = imagen;
    }
}
