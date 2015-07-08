package com.crossover.translator.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.crossover.translator.R;
import com.crossover.translator.adapter.LanguageSpinnerAdapter;
import com.crossover.translator.events.GetLanguagesEvent;
import com.crossover.translator.events.GetLanguagesSuccessEvent;
import com.crossover.translator.models.Language;

import de.greenrobot.event.EventBus;


public class searchFragment extends BaseFragment {
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private EditText searchBarText;
    private ImageButton searchButton;
    private SearchDelegate searchDelegate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        fromSpinner = (Spinner) view.findViewById(R.id.from_spinner);
        toSpinner = (Spinner) view.findViewById(R.id.to_spinner);
        searchBarText = (EditText) view.findViewById(R.id.search_bar_text);
        searchButton = (ImageButton) view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchDelegate!=null)
                    searchDelegate.onSearch((Language)fromSpinner.getSelectedItem(),(Language)toSpinner.getSelectedItem(),searchBarText.getText().toString());
            }
        });
        EventBus.getDefault().post(new GetLanguagesEvent());
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        initializeEventSupport();
    }

    public void onEvent(GetLanguagesSuccessEvent event){
        fromSpinner.setAdapter(new LanguageSpinnerAdapter(this.getActivity(), android.R.layout.simple_list_item_1,event.getLanguageList()));
        toSpinner.setAdapter(new LanguageSpinnerAdapter(this.getActivity(), android.R.layout.simple_list_item_1,event.getLanguageList()));
    }

    public interface SearchDelegate{
        public void onSearch(Language from, Language to, String phrase);
    }
}
