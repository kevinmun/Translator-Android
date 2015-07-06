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

    //region action bar events
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion
}
