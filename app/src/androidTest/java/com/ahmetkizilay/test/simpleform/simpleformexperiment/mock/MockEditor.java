package com.ahmetkizilay.test.simpleform.simpleformexperiment.mock;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ahmetkizilay on 20.12.2014.
 */
class MockEditor implements SharedPreferences.Editor {
    private List<EditHolder> mChanges;
    private CommitRequestListener mListener;

    public MockEditor() {
        mChanges = new ArrayList<>();
    }

    public void setCommitRequestListener(CommitRequestListener listener) {
        this.mListener = listener;
    }

    @Override
    public SharedPreferences.Editor putString(String key, String value) {
       mChanges.add(new EditHolder(key, value));
       return this;
    }

    @Override
    public SharedPreferences.Editor putStringSet(String key, Set<String> values) {
        return null;
    }

    @Override
    public SharedPreferences.Editor putInt(String key, int value) {
       mChanges.add(new EditHolder(key, new Integer(value)));
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
        mChanges.add(new EditHolder(EditAction.CLEAR, null, null));
        return this;
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
        public void onCommitRequested(List<EditHolder> edits);
    }

    class EditHolder {
        public String key;
        public Object value;
        public int action;

        public EditHolder(String key, Object value) {
            this.key = key;
            this.value = value;
            this.action = EditAction.EDIT;
        }


        public EditHolder(int action, String key, Object value) {
            this.action = action;
            this.value = value;
            this.key = key;
        }

    }

    class EditAction {
        final static int CLEAR = 0;
        final static int EDIT = 1;
    }
}
