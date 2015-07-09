package com.crossover.translator.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.crossover.translator.R;

/**
 * Created by Kevin on 7/9/2015.
 */
public class InfiniteListFooter extends LinearLayout {
    private View mLoadingView;

    public InfiniteListFooter(Context context) {
        super(context);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.infinite_list_footer, this, true);
        mLoadingView = findViewById(R.id.loading_view);
    }
    /**
     *  Show the loading view
     */
    public void showLoadingView(){
        mLoadingView.setVisibility(View.VISIBLE);
    }

    /**
     * Hide the loading view
     */
    public void hideLoadingView(){
        mLoadingView.setVisibility(View.INVISIBLE);
    }
}
