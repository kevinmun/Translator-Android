package com.crossover.translator.models;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * Created by Kevin on 7/8/2015.
 */

public class TranslationList {

    @JsonAnySetter
    public void extractResults(String name, Object object){
        Log.d(name,object.toString());
    }
}
