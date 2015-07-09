package com.crossover.translator.net.requestlisteners;

import android.util.Log;

import com.crossover.translator.events.GetTranslationSuccessEvent;
import com.crossover.translator.models.Language;
import com.crossover.translator.models.TranslationList;
import com.crossover.translator.models.TranslationResult;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Kevin on 7/8/2015.
 */
public class TranslationRequestListener implements RequestListener<TranslationList> {
    private String phrase;
    private Language fromLanguage;

    public TranslationRequestListener(Language fromLanguage, String phrase){
        this.phrase = phrase;
        this.fromLanguage = fromLanguage;
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {

    }

    @Override
    public void onRequestSuccess(TranslationList translationList) {
        //enter first result as original phrase
        List<TranslationResult> translationResultList = translationList.getResults();
        TranslationResult result = new TranslationResult();
        result.setPhrase(phrase);
        result.setLanguageCode(fromLanguage.getWikiLanguageCode());
        translationResultList.add(0,result);
        EventBus.getDefault().post(new GetTranslationSuccessEvent(translationResultList));
    }
}
