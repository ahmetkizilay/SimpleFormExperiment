package com.ahmetkizilay.test.simpleform.simpleformexperiment.mock;

import android.content.SharedPreferences;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This is not a complete implementation. I will try to implement this class to the extent
 * that I will need it to be.
 *
 * Created by ahmetkizilay on 20.12.2014.
 */
class MockSharedPreferences implements SharedPreferences {

    private Map<String, Object> mData;
    public MockSharedPreferences(Map<String, Object> data) {
        mData = data;
    }

    @Override
    public Map<String, ?> getAll() {
        return mData;
    }

    @Override
    public String getString(String key, String defValue) {
        if(mData.containsKey(key)) {
            if(mData.get(key) instanceof String) {
                return (String) mData.get(key);

            }
        }
        return defValue;
    }

    @Override
    public Set<String> getStringSet(String key, Set<String> defValues) {
        return null;
    }

    @Override
    public int getInt(String key, int defValue) {
        if(mData.containsKey(key)) {
            if(mData.get(key) instanceof Integer) {
                return (Integer) mData.get(key);
            }
        }
        return defValue;
    }

    @Override
    public long getLong(String key, long defValue) {
        return 0;
    }

    @Override
    public float getFloat(String key, float defValue) {
        return 0;
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return false;
    }

    @Override
    public boolean contains(String key) {
        return mData.containsKey(key);
    }

    @Override
    public Editor edit() {
        MockEditor mockEditor = new MockEditor();
        mockEditor.setCommitRequestListener(new MockEditor.CommitRequestListener() {
            @Override
            public void onCommitRequested(Map<String, Object> map) {
                for(Iterator<String> keys = map.keySet().iterator(); keys.hasNext();) {
                    String key = keys.next();
                    mData.put(key, map.get(key));
                }
            }
        });
        return mockEditor;
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

    }
}
