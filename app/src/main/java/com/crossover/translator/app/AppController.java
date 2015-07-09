package com.crossover.translator.app;


import android.content.res.Resources;

import com.crossover.translator.R;
import com.crossover.translator.events.GetLanguagesEvent;
import com.crossover.translator.events.GetLanguagesSuccessEvent;
import com.crossover.translator.events.GetTranslationEvent;
import com.crossover.translator.models.Language;
import com.crossover.translator.net.requestlisteners.TranslationRequestListener;
import com.crossover.translator.net.requests.TranslationRequest;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Kevin on 7/6/2015.
 */
public class AppController implements  IAppController {
    private static AppController instance;
    private EventBus eventBus;
    private final TranslatorApplication context;

    /**
     *  Private singleton constructor
     *
     * @param context TranslatorApplication context
     */
    private AppController(TranslatorApplication context){
        this.context = context;
        eventBus = EventBus.getDefault();
        eventBus.register(this);
    }

    /**
     *  Initializes Appcontroller once and only once.
     *  Initializing again will throw {@link java.lang.RuntimeException} to prevent global access
     *
     * @param context
     * @return {@link com.crossover.translator.app.AppController}
     */
    public static AppController start(TranslatorApplication context){
        if(instance == null){
            instance = new AppController(context);
        } else {
            throw new RuntimeException("AppController has already started");
        }
        return instance;
    }

    private void getTranslation(Language fromLanguage, Language toLanguage, String phrase, int page, int pageSize){
        TranslationRequest req = new TranslationRequest(fromLanguage,toLanguage,phrase,page,pageSize);
        context.getRestManager().performRequest(req,new TranslationRequestListener(fromLanguage,phrase));
    }

    private void getLanguages(){
       Resources resources = context.getResources();
       String[] stringList = resources.getStringArray(R.array.languages);
        List<Language>languages = new ArrayList<Language>();
       for(int i=0;i<stringList.length;i++){
           Language language = new Language();
           String[] breakDownString = stringList[i].split(":");
           int resourceId = resources.getIdentifier(breakDownString[0], "string", context.getPackageName());
           language.setLanguageName(resources.getString(resourceId));
           language.setLanguageCode(breakDownString[1]);
           language.setWikiLanguageCode(breakDownString[2]);
           languages.add(language);
       }
        context.getEventBus().post(new GetLanguagesSuccessEvent(languages));
    }

    public void onEvent(GetLanguagesEvent event){
        getLanguages();
    }

    public void onEvent(GetTranslationEvent event){
        getTranslation(event.getFromLanguage(),event.getToLanguage(),event.getPhrase(),event.getPage(),event.getPageSize());
    }



}
