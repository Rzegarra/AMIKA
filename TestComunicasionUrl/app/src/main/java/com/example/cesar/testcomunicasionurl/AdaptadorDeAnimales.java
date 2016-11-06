package com.example.cesar.testcomunicasionurl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 05/11/2016.
 */
public class AdaptadorDeAnimales extends ArrayList{
    public AdaptadorDeAnimales(Context context, List objects){
        super (context,0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext().
    }
}
