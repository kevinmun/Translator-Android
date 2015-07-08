package com.crossover.translator.events;

import com.crossover.translator.models.Language;

/**
 * Created by Kevin on 7/8/2015.
 */
public class GetTranslationEvent {
    private Language fromLanguage;
    private Language toLanguage;
    private String phrase;
    private int page;
    private int pageSize;

    public GetTranslationEvent(Language fromLanguage, Language toLanguage, String phrase, int page, int pageSize){
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        this.phrase = phrase;
        this.page = page;
        this.pageSize = pageSize;
    }

    public Language getFromLanguage(){
        return fromLanguage;
    }

    public Language getToLanguage(){
        return toLanguage;
    }

    public String getPhrase(){
        return phrase;
    }

    public int getPage(){
        return page;
    }

    public int getPageSize(){
        return pageSize;
    }
}
