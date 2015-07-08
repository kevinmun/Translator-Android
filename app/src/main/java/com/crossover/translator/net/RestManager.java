package com.crossover.translator.net;

import android.content.Context;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Created by Kevin on 7/6/2015.
 */
public class RestManager implements IRestManager {
    private SpiceManager spiceManager;

    public void start(Context context){
        spiceManager = new SpiceManager(RestService.class);
        spiceManager.start(context);
    }

    public void performRequest(SpringAndroidSpiceRequest request,RequestListener listener) {
        spiceManager.execute(request, null, DurationInMillis.ONE_MINUTE, listener);
    }

    public void stop(){
        spiceManager.shouldStop();
    }
}
