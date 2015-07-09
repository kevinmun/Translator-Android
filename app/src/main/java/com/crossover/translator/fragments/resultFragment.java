package com.crossover.translator.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.crossover.translator.R;
import com.crossover.translator.adapter.ResultAdapter;
import com.crossover.translator.events.GetTranslationEvent;
import com.crossover.translator.events.GetTranslationSuccessEvent;
import com.crossover.translator.models.TranslationResult;

import java.util.ArrayList;

/**
 * Created by Kevin on 7/7/2015.
 */
public class ResultFragment extends BaseFragment {
    private ListView listView;
    private ResultAdapter resultAdapter;
    private InfiniteListFooter mListFooter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        listView = (ListView)view.findViewById(R.id.result_list_view);
        resultAdapter = new ResultAdapter(getActivity(),R.layout.result_list_item,new ArrayList<TranslationResult>());
        mListFooter = new InfiniteListFooter(getActivity());
        listView.setAdapter(resultAdapter);
        listView.addFooterView(mListFooter);
        initializeEventSupport();
        return view;
    }

    public void clearAll(){
        resultAdapter.clear();
        resultAdapter.notifyDataSetChanged();
    }

    public void onEvent(GetTranslationEvent event){
        clearAll();
        mListFooter.showLoadingView();
    }

    public void onEvent(GetTranslationSuccessEvent event){
        mListFooter.hideLoadingView();
        resultAdapter.addAll(event.getTranslationList());
        resultAdapter.notifyDataSetChanged();
    }
}
