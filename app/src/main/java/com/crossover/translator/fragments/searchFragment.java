package com.crossover.translator.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.crossover.translator.R;
import com.crossover.translator.adapter.LanguageSpinnerAdapter;
import com.crossover.translator.events.GetLanguagesEvent;
import com.crossover.translator.events.GetLanguagesSuccessEvent;
import com.crossover.translator.models.Language;

import de.greenrobot.event.EventBus;


public class SearchFragment extends BaseFragment {
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private AutoCompleteTextView searchBarText;
    private ImageButton searchButton;
    private ImageButton inverseButton;
    private ArrayAdapter<String> autoCompleteAdapter;
    private SearchDelegate searchDelegate;

    //region fragment lifecycle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        fromSpinner = (Spinner) view.findViewById(R.id.from_spinner);
        toSpinner = (Spinner) view.findViewById(R.id.to_spinner);
        inverseButton = (ImageButton) view.findViewById(R.id.inverse_button);
        inverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fromPosition = fromSpinner.getSelectedItemPosition();
                int toPosition = toSpinner.getSelectedItemPosition();
                fromSpinner.setSelection(toPosition);
                toSpinner.setSelection(fromPosition);
            }
        });
        searchBarText = (AutoCompleteTextView) view.findViewById(R.id.search_bar_text);
        autoCompleteAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.simple_list_item_1,getBaseActivity().getApplicationContext().getAppPref().getAutoComplete());
        searchBarText.setThreshold(1);
        searchBarText.setAdapter(autoCompleteAdapter);
        searchButton = (ImageButton) view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchDelegate!=null) {
                    String phrase = searchBarText.getText().toString();
                    searchDelegate.onSearch((Language) fromSpinner.getSelectedItem(), (Language) toSpinner.getSelectedItem(),phrase);
                    if(phrase.length()>0) {
                        getBaseActivity().getApplicationContext().getAppPref().saveAutoComplete(phrase);
                        autoCompleteAdapter.add(phrase);
                        autoCompleteAdapter.notifyDataSetChanged();
                    }
                }
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

    //endregion

    public void setSearchDelegate(SearchDelegate searchDelegate){
        this.searchDelegate = searchDelegate;
    }

    public void onEvent(GetLanguagesSuccessEvent event){
        fromSpinner.setAdapter(new LanguageSpinnerAdapter(this.getActivity(), android.R.layout.simple_list_item_1,event.getLanguageList()));
        toSpinner.setAdapter(new LanguageSpinnerAdapter(this.getActivity(), android.R.layout.simple_list_item_1,event.getLanguageList()));
    }

    public interface SearchDelegate{
        public void onSearch(Language from, Language to, String phrase);
    }
}
