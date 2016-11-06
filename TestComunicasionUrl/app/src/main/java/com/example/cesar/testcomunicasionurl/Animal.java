package com.example.cesar.testcomunicasionurl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.String;
import com.google.gson.Gson;
import com.google.gson.*;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonReader;
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

    public Animal leerAnimal(JsonReader reader) throws IOException{
        String especie = null;
        String descripcion = null;
        String imagen = null;
        reader.beginObject();
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

    /*
    *Se creo la funcion leerArrayAnimales() para recorrer todo
    * el array enviado desde el servidor.
    * Recibe como parametro el lector Json para continuar
    * la labor de lectura.
    * Luego se apunta al primer elemento del array con el
    * metodo beginArray(), lo que permite leer con el bucle
    * while y luego con el elemento hasNext().
     */
    public List leerArrayAnimales(JsonReader reader) throws IOException{
        //List temporal
        ArrayList animales = new ArrayList();

        reader.beginObject();
        while(reader.hasNext())
            animales.add(leerAnimal(reader));
        reader.endArray();
        return  animales;
    }

        /*
    *Primero se implementa un metodo que cordine el retorno de los
    * objetos de tipo animal(tipo que desees)
    * Y debe crearse una instancia JsonReader
     */

    public List<Animal> readJsonStrea(InputStream in) throws IOException{
        //nueva instancia JsonReader
        JsonReader reader = new JsonReader (new InputStreamReader(in, "UTF-8"));

        try{
            //leer array
            return leerArrayAnimales(reader);
        } finally {
            reader.close();
        }
    }
    /*
    public List<Animal> readJsonStream(InputStreamReader in) throws IOException{
        //nueva instancia JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            //leer array
            return  leerArrayAnimales(reader);
        } finally {
            reader.close();
        }

    }*/

}
