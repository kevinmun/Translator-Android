package com.crossover.translator.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.crossover.translator.R;
import com.crossover.translator.app.TranslatorApplication;

import de.greenrobot.event.EventBus;

/**
 * Created by Kevin on 7/6/2015.
 */
public abstract class BaseActivity<A> extends ActionBarActivity {
    private EventBus eventBus;
    private boolean registered;
    private boolean eventEnabled;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registered = eventEnabled = false;
        eventBus = getApplicationContext().getEventBus();
    }

    /**
     * Returns reference to the current activity instance
     *
     * @return
     */
    protected abstract A getActivity();

    @Override
    public TranslatorApplication getApplicationContext() {
        return (TranslatorApplication)super.getApplicationContext();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(eventEnabled && !registered){
            registerEventBus();
        }
    }

    @Override
    protected void onStop() {
        if(eventEnabled && registered){
            unregisterEventBus();
        }
        super.onStop();
    }

    //region event bus
    public EventBus getEventBus(){
        return eventBus;
    }

    public void initEventSupport(){
        if (!registered) {
            registerEventBus();
            eventEnabled = true;
        }
    }

    public void uninitEventSupport(){
        if (registered) {
            unregisterEventBus();
            eventEnabled = false;
        }
    }

    private void registerEventBus(){
        eventBus.register(getActivity());
        registered = true;
    }

    private void unregisterEventBus(){
        eventBus.unregister(getActivity());
        registered = false;
    }
    //endregion
}
