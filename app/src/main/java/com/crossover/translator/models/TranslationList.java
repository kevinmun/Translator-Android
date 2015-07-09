package com.crossover.translator.models;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Kevin on 7/8/2015.
 */

public class TranslationList {
    private List<TranslationResult> results;

    public TranslationList(){
        results = new ArrayList<TranslationResult>();
    }
    @JsonAnySetter
    public void extractResults(String name, Object object){
        if(name.equals("tuc") && object!=null){
            ArrayList<LinkedHashMap> arrayList = (ArrayList<LinkedHashMap>)object;
            for(LinkedHashMap hashMap:arrayList){
                TranslationResult result = new TranslationResult();
                if(hashMap.containsKey("phrase")){
                    LinkedHashMap innerPhraseHashmap = (LinkedHashMap) hashMap.get("phrase");
                    result.setPhrase((String)innerPhraseHashmap.get("text"));
                    result.setLanguageCode((String)innerPhraseHashmap.get("language"));
                }
                if(hashMap.containsKey("meanings")){
                    ArrayList<LinkedHashMap> innerMeaningsHashmap = (ArrayList<LinkedHashMap>) hashMap.get("meanings");
                    if(innerMeaningsHashmap.size()>0) {
                        LinkedHashMap firstMeaningHasmap = innerMeaningsHashmap.get(0);
                        result.setMeaning((String)firstMeaningHasmap.get("text"));
                    }
                }
                results.add(result);
            }
        }
    }

    public  List<TranslationResult> getResults(){
        return results;
    }
}
