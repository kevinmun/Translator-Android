package com.crossover.translator.models;

/**
 * Created by Kevin on 7/8/2015.
 */
public class Language {
    private String languageName;
    private String languageCode;
    private String wikiLanguageCode;

    public void setLanguageName(String languageName){
        this.languageName = languageName;
    }

    public String getLanguageName(){
        return languageName;
    }

    public void setLanguageCode(String languageCode){
        this.languageCode = languageCode;
    }

    public String getLanguageCode(){
        return languageCode;
    }

    public void setWikiLanguageCode(String wikiLanguageCode){
        this.wikiLanguageCode = wikiLanguageCode;
    }

    public String getWikiLanguageCode(){
        return wikiLanguageCode;
    }
}
