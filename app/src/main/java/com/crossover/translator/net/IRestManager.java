package com.crossover.translator.net;

import android.content.Context;

import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Created by Kevin on 7/6/2015.
 */
public interface IRestManager {
    public void start(Context context);
    public void performRequest(SpringAndroidSpiceRequest request,RequestListener listener);
    public void stop();
}
