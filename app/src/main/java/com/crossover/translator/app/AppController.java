package com.crossover.translator.app;


/**
 * Created by Kevin on 7/6/2015.
 */
public class AppController implements  IAppController {
    private static AppController instance;
    private final TranslatorApplication context;

    /**
     *  Private singleton constructor
     *
     * @param context TranslatorApplication context
     */
    private AppController(TranslatorApplication context){
        this.context = context;
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
}
