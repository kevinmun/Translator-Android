package com.crossover.translator.app;

import android.app.Application;
import android.content.Context;

import com.crossover.translator.net.IRestManager;
import com.crossover.translator.net.RestManager;

import de.greenrobot.event.EventBus;

/**
 * Created by Kevin on 7/6/2015.
 *  Application context for translator app. Persistent singleton objects should be stored here
 */
public class TranslatorApplication extends Application {
    private EventBus eventBus;
    private IAppController appController;
    private IRestManager restManager;

    @Override
    public void onCreate() {
        super.onCreate();
        eventBus = EventBus.getDefault();
        restManager = new RestManager();
        restManager.start(this);
        appController = AppController.start(this);
    }

    public EventBus getEventBus(){
        return eventBus;
    }

    public IRestManager getRestManager() {return restManager;}

}
