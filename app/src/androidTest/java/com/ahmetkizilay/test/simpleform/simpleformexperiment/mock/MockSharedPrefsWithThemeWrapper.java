package com.ahmetkizilay.test.simpleform.simpleformexperiment.mock;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.ContextThemeWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ahmetkizilay on 20.12.2014.
 */
public class MockSharedPrefsWithThemeWrapper extends ContextThemeWrapper {
    MockSharedPreferences mockSharedPreferences;
    private List<String> mSharedPrefName;
    private List<Map<String, ?>> mSharedPrefData;

    public MockSharedPrefsWithThemeWrapper(Context context, int theme) {
        super(context, theme);

        mSharedPrefName = new ArrayList<>();
        mSharedPrefData = new ArrayList<>();


    }

    public void addNewSharedPref(String name, Map<String, ?> map) {
        if(mSharedPrefName.contains(name)) {
            int index = mSharedPrefName.indexOf(name);
            mSharedPrefData.remove(index);
            mSharedPrefData.add(index, map);
        }
        else {
            mSharedPrefName.add(name);
            mSharedPrefData.add(map);
        }
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        if(mSharedPrefName.contains(name)) {
            return new MockSharedPreferences(mSharedPrefData.get(mSharedPrefName.indexOf(name)));
        }
        return null;
    }
}
