package com.crossover.translator.events;

import com.crossover.translator.models.TranslationResult;

import java.util.List;

/**
 * Created by Kevin on 7/8/2015.
 */
public class GetTranslationSuccessEvent {
    private List<TranslationResult> translationList;

    public  GetTranslationSuccessEvent(List<TranslationResult> translationList){
        this.translationList = translationList;
    }

    public List<TranslationResult> getTranslationList(){
        return this.translationList;
    }
}
