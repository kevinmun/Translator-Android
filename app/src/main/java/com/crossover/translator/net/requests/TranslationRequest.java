package com.crossover.translator.net.requests;

import com.crossover.translator.models.Language;
import com.crossover.translator.models.TranslationList;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Created by Kevin on 7/8/2015.
 */
public class TranslationRequest extends SpringAndroidSpiceRequest<TranslationList> {
    private final static String req = "https://glosbe.com/gapi/translate?from=%1$s&dest=%2$s&format=json&phrase=%3$s&page=%4$d&pageSize=%5$d";
    private Language fromLanguage;
    private Language toLanguage;
    private String phrase;
    private int page;
    private int pageSize;

    public TranslationRequest(Language fromLanguage, Language toLanguage, String phrase, int page, int pageSize) {
        super(TranslationList.class);
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        this.phrase = phrase;
        this.page = page;
        this.pageSize = pageSize;
    }

    @Override
    public TranslationList loadDataFromNetwork() throws Exception {
        String url = String.format(req,fromLanguage.getLanguageCode(),toLanguage.getLanguageCode(),phrase,page,pageSize);
        return getRestTemplate().getForObject(url, TranslationList.class);
    }
}
