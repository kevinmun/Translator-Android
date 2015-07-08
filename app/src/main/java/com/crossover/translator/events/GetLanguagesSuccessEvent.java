package com.crossover.translator.events;

import com.crossover.translator.models.Language;

import java.util.List;

/**
 * Created by Kevin on 7/8/2015.
 */
public class GetLanguagesSuccessEvent {
    private List<Language> languageList;

    public GetLanguagesSuccessEvent(List<Language> languageList){
        this.languageList = languageList;
    }

    public List<Language> getLanguageList(){
        return languageList;
    }
}
