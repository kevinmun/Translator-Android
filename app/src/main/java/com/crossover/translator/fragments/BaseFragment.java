package com.crossover.translator.fragments;

import android.app.Activity;
import android.app.Fragment;

import com.crossover.translator.activities.BaseActivity;

import de.greenrobot.event.EventBus;

/**
 * Created by Kevin on 7/7/2015.
 */
public class BaseFragment extends Fragment {
    private BaseActivity baseActivity;
    private EventBus eventBus;
    public boolean eventEnabled = false;

    //region fragment lifecycle
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        baseActivity = (BaseActivity)activity;
        eventBus = EventBus.getDefault();
    }

    @Override
    public void onDestroy() {
        if(eventEnabled) {
            eventEnabled =false;
            eventBus.unregister(this);
        }
        super.onDestroy();
    }
    //endregion

    public void initializeEventSupport() {
        EventBus.getDefault().register(this);
        eventEnabled = true;
    }

    public EventBus getEventBus(){
        return eventBus;
    }

    public BaseActivity getBaseActivity(){
        return baseActivity;
    }
}
