package com.crossover.translator.app;

import android.app.Application;
import android.content.Context;

import de.greenrobot.event.EventBus;

/**
 * Created by Kevin on 7/6/2015.
 *  Application context for translator app. Persistent singleton objects should be stored here
 */
public class TranslatorApplication extends Application {
    private EventBus eventBus;
    private IAppController appController;

    @Override
    public void onCreate() {
        super.onCreate();
        eventBus = EventBus.getDefault();
        appController = AppController.start(this);
    }

    public EventBus getEventBus(){
        return eventBus;
    }

}
