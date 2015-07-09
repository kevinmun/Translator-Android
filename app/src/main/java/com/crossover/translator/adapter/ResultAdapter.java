package com.crossover.translator.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.crossover.translator.R;
import com.crossover.translator.models.TranslationResult;

import java.util.List;

/**
 * Created by Kevin on 7/9/2015.
 */
public class ResultAdapter extends ArrayAdapter<TranslationResult> {
    private LayoutInflater inflater;
    private static String webtemplate = "https://%1$s.wikipedia.org/wiki/%2$s";

    public ResultAdapter(Context context, int resource, List<TranslationResult> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.result_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.parentView = convertView;
            viewHolder.phraseView = (TextView) convertView.findViewById(R.id.phrase_text);
            viewHolder.meaningView = (TextView) convertView.findViewById(R.id.meaning_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final TranslationResult result = getItem(position);
        if(result!=null){
            viewHolder.phraseView.setText(result.getPhrase());
            viewHolder.meaningView.setText(result.getMeaning());
            viewHolder.parentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(result.getPhrase().length() >0) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(webtemplate, result.getLanguageCode(), result.getPhrase())));
                        getContext().startActivity(browserIntent);
                    }
                }
            });
        }
        return convertView;
    }

    static class ViewHolder{
        public View parentView;
        public TextView phraseView;
        public TextView meaningView;
    }
}
