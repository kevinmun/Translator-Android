package com.crossover.translator.net.requestlisteners;

import com.crossover.translator.models.TranslationList;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by Kevin on 7/8/2015.
 */
public class TranslationRequestListener implements RequestListener<TranslationList> {
    @Override
    public void onRequestFailure(SpiceException spiceException) {

    }

    @Override
    public void onRequestSuccess(TranslationList translationList) {

    }
}
