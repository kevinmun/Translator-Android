package com.crossover.translator.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.crossover.translator.models.Language;

import java.util.List;

/**
 * Created by Kevin on 7/8/2015.
 */
public class LanguageSpinnerAdapter extends ArrayAdapter<Language> {
    private LayoutInflater inflater;

    public LanguageSpinnerAdapter(Context context, int resource, List<Language> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position,convertView,parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent){
        Language language = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }

        TextView languageTextView = (TextView) convertView.findViewById(android.R.id.text1);
        languageTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        languageTextView.setText(language.getLanguageName());

        return convertView;
    }
}
