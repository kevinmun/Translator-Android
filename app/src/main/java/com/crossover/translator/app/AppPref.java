package com.crossover.translator.app;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Kevin on 7/9/2015.
 */
public class AppPref {
    private static String AUTOCOMPLETE_ACTIVITY = "autocomplete_activity_key";
    private static String AUTOCOMPLETE_KEY = "autocomplete_key";
    private Context context;

    public AppPref(Context context){
        this.context = context;
    }

    public String[] getAutoComplete(){
        SharedPreferences settings = context.getSharedPreferences(AUTOCOMPLETE_ACTIVITY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        Set<String> myStrings = settings.getStringSet(AUTOCOMPLETE_KEY, new HashSet<String>());
        return myStrings.toArray(new String[myStrings.size()]);
    }

    public void saveAutoComplete(String s){
        SharedPreferences settings = context.getSharedPreferences(AUTOCOMPLETE_ACTIVITY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        Set<String> myStrings = settings.getStringSet(AUTOCOMPLETE_KEY, new HashSet<String>());
        //maximum of 10 words
        if(myStrings.size() >9){
            Iterator<String> iterator = myStrings.iterator();
            while (iterator.hasNext()) {
                iterator.remove();
                break;
            }
        }
        myStrings.add(s);
        editor.putStringSet(AUTOCOMPLETE_KEY,myStrings);
        editor.commit();

    }

}
