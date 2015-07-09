package com.crossover.translator.models;

/**
 * Created by Kevin on 7/8/2015.
 */
public class TranslationResult {
    private String phrase;
    private String meaning;
    private String languageCode;

    public TranslationResult(){
        phrase = "";
        meaning = "";
        languageCode = "";
    }

    public void setPhrase(String phrase){
        this.phrase = phrase;
    }

    public void setMeaning(String meaning){
        this.meaning = meaning;
    }

    public void setLanguageCode(String languageCode){
        this.languageCode = languageCode;
    }

    public String getPhrase(){
        return phrase;
    }

    public String getMeaning(){
        return meaning;
    }

    public String getLanguageCode(){
        return languageCode;
    }


}
