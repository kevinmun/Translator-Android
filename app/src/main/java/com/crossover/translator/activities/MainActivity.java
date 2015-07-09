package com.crossover.translator.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.crossover.translator.R;
import com.crossover.translator.events.GetTranslationEvent;
import com.crossover.translator.events.GetTranslationSuccessEvent;
import com.crossover.translator.fragments.ResultFragment;
import com.crossover.translator.fragments.SearchFragment;
import com.crossover.translator.models.Language;

public class MainActivity extends BaseActivity<MainActivity> {
    private static int PER_PAGE = 30;
    private SearchFragment searchFragment;
    private ResultFragment resultFragment;
    private int page=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchFragment = (SearchFragment)getFragmentManager().findFragmentById(R.id.search_fragment);
        resultFragment = (ResultFragment)getFragmentManager().findFragmentById(R.id.result_fragment);
        searchFragment.setSearchDelegate(new SearchFragment.SearchDelegate() {
            @Override
            public void onSearch(Language from, Language to, String phrase) {
                if(phrase.length() >0){

                    getEventBus().post(new GetTranslationEvent(from,to,phrase,page,PER_PAGE));
                }
            }
        });

    }

    @Override
    protected MainActivity getActivity() {
        return this;
    }

}
