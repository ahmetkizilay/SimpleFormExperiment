package com.ahmetkizilay.test.simpleform.simpleformexperiment.mock;

import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ahmetkizilay on 20.12.2014.
 */
class MockEditor implements SharedPreferences.Editor {
    private Map<String, Object> mChanges;
    private CommitRequestListener mListener;

    public MockEditor() {
        mChanges = new HashMap<>();
    }

    public void setCommitRequestListener(CommitRequestListener listener) {
        this.mListener = listener;
    }

    @Override
    public SharedPreferences.Editor putString(String key, String value) {
       mChanges.put(key, value);
       return this;
    }

    @Override
    public SharedPreferences.Editor putStringSet(String key, Set<String> values) {
        return null;
    }

    @Override
    public SharedPreferences.Editor putInt(String key, int value) {
       mChanges.put(key, new Integer(value));
        return this;
    }

    @Override
    public SharedPreferences.Editor putLong(String key, long value) {
        return null;
    }

    @Override
    public SharedPreferences.Editor putFloat(String key, float value) {
        return null;
    }

    @Override
    public SharedPreferences.Editor putBoolean(String key, boolean value) {
        return null;
    }

    @Override
    public SharedPreferences.Editor remove(String key) {
        return null;
    }

    @Override
    public SharedPreferences.Editor clear() {
        return null;
    }

    @Override
    public boolean commit() {
        apply();
        return true;
    }

    @Override
    public void apply() {
        if(mListener != null) {
            mListener.onCommitRequested(this.mChanges);
        }
    }

    interface CommitRequestListener {
        public void onCommitRequested(Map<String, Object> map);
    }
}
