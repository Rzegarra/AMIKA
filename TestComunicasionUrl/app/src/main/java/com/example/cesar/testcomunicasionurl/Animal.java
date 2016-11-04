package com.example.cesar.testcomunicasionurl;

import java.lang.String;

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
    /*
    * Metodo leerAnimal() accede a todos paras clave-valeor
    * que convirtio el lector JsonReader en datos interpretables
    * Asi que aprovechando esa estructura definida, es posible recorrer cada objeto particular en
    * busca de los valores que se refieran a una clave.
     */

    public Animal leerAnimal(JsonReader reader) throws IOExeption{
        String especie = null;
        String descripcion = null;
        String imagen = null;

        reader.beginOject(),
        while (reader.hasNext()){
            String name = reader.nextName();
            switch (name){
                case "especie":
                    especie = reader.nextString();
                    break;
                case "descripcion":
                    descripcion = reader.nextString();
                    break;
                case "imagen":
                    imagen = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;

            }
        }
        reader.endObject();
        return new Animal(especie, descripcion, imagen);
    }
}
