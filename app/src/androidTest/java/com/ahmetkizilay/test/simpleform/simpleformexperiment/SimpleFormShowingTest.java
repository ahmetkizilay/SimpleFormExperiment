package com.ahmetkizilay.test.simpleform.simpleformexperiment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.TextView;

import com.ahmetkizilay.test.simpleform.simpleformexperiment.fragments.FormSaveFragment;
import com.ahmetkizilay.test.simpleform.simpleformexperiment.fragments.FormShowFragment;
import com.ahmetkizilay.test.simpleform.simpleformexperiment.mock.MockSharedPrefsWithThemeWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ahmetkizilay on 21.12.2014.
 */
public class SimpleFormShowingTest extends ActivityUnitTestCase<MainActivity> {
    public SimpleFormShowingTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testPreconditions() {
        MockSharedPrefsWithThemeWrapper mMockContext = new MockSharedPrefsWithThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);
        Map<String, Object> data = new HashMap<>();
        data.put("name", "John");
        data.put("age", new Integer(23));
        mMockContext.addNewSharedPref("info", data);

        setActivityContext(mMockContext);

        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        startActivity(intent, null, null);

        Activity mActivity = getActivity();

        getInstrumentation().callActivityOnStart(mActivity);

        assertNotNull("Name field should exist", mActivity.findViewById(R.id.tvName));
        assertNotNull("Age field should exist", mActivity.findViewById(R.id.tvAge));

        assertNotNull("Clear button should exist", mActivity.findViewById(R.id.btnClear));
    }

    public void testFieldsShouldShowTheCorrectValues() {
        String name = "My Random Name";
        int age = 42;


        MockSharedPrefsWithThemeWrapper mMockContext = new MockSharedPrefsWithThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("age", new Integer(age));
        mMockContext.addNewSharedPref("info", data);

        setActivityContext(mMockContext);

        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        startActivity(intent, null, null);

        Activity mActivity = getActivity();

        getInstrumentation().callActivityOnStart(mActivity);

        TextView tvName = (TextView) mActivity.findViewById(R.id.tvName);
        TextView tvAge = (TextView) mActivity.findViewById(R.id.tvAge);

        assertEquals("Name field should show the right value", name, String.valueOf(tvName.getText()));
        assertEquals("Age field should show the right value", age + "", String.valueOf(tvAge.getText()));
    }

    public void testClearSharedPrefsAfterClick() {
        String name = "My Random Name";
        int age = 42;


        MockSharedPrefsWithThemeWrapper mMockContext = new MockSharedPrefsWithThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("age", new Integer(age));
        mMockContext.addNewSharedPref("info", data);

        setActivityContext(mMockContext);

        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        startActivity(intent, null, null);

        FragmentActivity mActivity = getActivity();

        getInstrumentation().callActivityOnStart(mActivity);

        assertNull("Save fragment should not be visible", mActivity.getSupportFragmentManager().findFragmentByTag(FormSaveFragment.NAME));

        Button btnClear = (Button) mActivity.findViewById(R.id.btnClear);
        btnClear.performClick();

        mActivity.getSupportFragmentManager().executePendingTransactions();

        SharedPreferences sp = mActivity.getSharedPreferences("info", Context.MODE_PRIVATE);

        assertEquals("name should be empty", "", sp.getString("name", ""));
        assertEquals("age should be zero", 0, sp.getInt("age", 0));

        assertNotNull("Save fragment should be visible", mActivity.getSupportFragmentManager().findFragmentByTag(FormSaveFragment.NAME));
        assertNull("Show fragment should not be visible", mActivity.getSupportFragmentManager().findFragmentByTag(FormShowFragment.NAME));
    }
}
