package com.crossover.translator.events;

import com.crossover.translator.models.TranslationList;

/**
 * Created by Kevin on 7/8/2015.
 */
public class GetTranslationSuccessEvent {
    private TranslationList translationList;

    public  GetTranslationSuccessEvent(TranslationList translationList){
        this.translationList = translationList;
    }

    public TranslationList getTranslationList(){
        return this.translationList;
    }
}
