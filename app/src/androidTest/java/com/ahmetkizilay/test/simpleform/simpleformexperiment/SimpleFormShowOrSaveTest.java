package com.ahmetkizilay.test.simpleform.simpleformexperiment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.EditText;

import com.ahmetkizilay.test.simpleform.simpleformexperiment.fragments.FormSaveFragment;
import com.ahmetkizilay.test.simpleform.simpleformexperiment.fragments.FormShowFragment;
import com.ahmetkizilay.test.simpleform.simpleformexperiment.mock.MockSharedPrefsWithThemeWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ahmetkizilay on 20.12.2014.
 */
public class SimpleFormShowOrSaveTest extends ActivityUnitTestCase<MainActivity> {
    public SimpleFormShowOrSaveTest() {
        super(MainActivity.class);
    }


    /**
     * If there is user info saved, display FormShowFragment
     */
    public void testDisplayFormShow() {
        MockSharedPrefsWithThemeWrapper mMockContext = new MockSharedPrefsWithThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);

        Map<String, Object> data = new HashMap<>();
        data.put("name", "John");
        data.put("age", new Integer(23));
        mMockContext.addNewSharedPref("info", data);

        setActivityContext(mMockContext);

        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        startActivity(intent, null, null);

        FragmentActivity mActivity = getActivity();

        getInstrumentation().callActivityOnStart(mActivity);

        assertNotNull("FormShowFragment should be displayed", mActivity.getSupportFragmentManager().findFragmentByTag(FormShowFragment.NAME));
    }

    /**
     * if there is no user info saved, display FormSaveFragment
     */
    public void testDisplayFormSave() {
        MockSharedPrefsWithThemeWrapper mMockContext = new MockSharedPrefsWithThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);
        setActivityContext(mMockContext);

        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        startActivity(intent, null, null);

        FragmentActivity mActivity = getActivity();

        getInstrumentation().callActivityOnStart(mActivity);

        assertNotNull("FormSaveFragment should be displayed", mActivity.getSupportFragmentManager().findFragmentByTag(FormSaveFragment.NAME));
    }

}
