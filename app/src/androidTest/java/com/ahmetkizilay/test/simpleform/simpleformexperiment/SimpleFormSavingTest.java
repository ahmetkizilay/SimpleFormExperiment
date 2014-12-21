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

/**
 * Created by ahmetkizilay on 20.12.2014.
 */
public class SimpleFormSavingTest extends ActivityUnitTestCase<MainActivity> {
    public SimpleFormSavingTest() {
        super(MainActivity.class);
    }

    private MockSharedPrefsWithThemeWrapper mMockContext;
    private FragmentActivity mActivity;

    private EditText etName;
    private EditText etAge;

    private Button btnSubmit;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mMockContext = new MockSharedPrefsWithThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);
        setActivityContext(mMockContext);

        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        startActivity(intent, null, null);

        mActivity = getActivity();

        getInstrumentation().callActivityOnStart(mActivity);

        etName = (EditText) mActivity.findViewById(R.id.etName);
        etAge = (EditText) mActivity.findViewById(R.id.etAge);

        btnSubmit = (Button) mActivity.findViewById(R.id.btnSubmit);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * It should contain two edit fields: name, age
     * It should contain a button: submit
     */
    public void testPreconditions() {
        assertTrue("Name field should be defined", etName != null);
        assertTrue("Age field should be defined", etAge != null);

        assertTrue("Submit button should be defined", btnSubmit != null);
    }

    /**
     * button should remain disabled until both fields are filled with
     * valid values. age should be a number, name should be text
     * button state should update when fields become invalid.
     */
    public void testButtonDisabledUntilBothFieldsAreValid() {
        assertTrue("Name field should be empty", etName.getText().toString().trim().equals(""));
        assertTrue("Age field should be empty", etAge.getText().toString().trim().equals(""));
        assertFalse("Submit button should be disabled", btnSubmit.isEnabled());

        etName.setText("");
        etAge.setText("12");

        assertTrue("Name field should be empty", etName.getText().toString().trim().equals(""));
        assertFalse("Age field should not be empty", etAge.getText().toString().trim().equals(""));
        assertFalse("Submit button should be disabled", btnSubmit.isEnabled());

        etName.setText("Ahmet Kizilay");
        etAge.setText("");

        assertFalse("Name field should not be empty", etName.getText().toString().trim().equals(""));
        assertTrue("Age field should be empty", etAge.getText().toString().trim().equals(""));
        assertFalse("Submit button should be disabled", btnSubmit.isEnabled());


        etName.setText("Ahmet Kizilay");
        etAge.setText("cc");

        assertFalse("Name field should not be empty", etName.getText().toString().trim().equals(""));
        assertFalse("Age field should not be empty", etAge.getText().toString().trim().equals(""));
        assertFalse("Submit button should not enabled", btnSubmit.isEnabled());

        etName.setText("Ahmet Kizilay");
        etAge.setText("12");

        assertFalse("Name field should not be empty", etName.getText().toString().trim().equals(""));
        assertFalse("Age field should not be empty", etAge.getText().toString().trim().equals(""));
        assertTrue("Submit button should be enabled", btnSubmit.isEnabled());

    }

    /**
     * save fragment should be gone after save, and show fragment should be displayed instead
     * age and name fields should save the values in shared preferences
     */
    public void testSaveButtonClicked() {
        String name = "John Smith";
        int age = 24;

        etName.setText(name);
        etAge.setText(age + "");

        btnSubmit.performClick();

        assertNull("Show Fragment should not be visible", mActivity.getSupportFragmentManager().findFragmentByTag(FormShowFragment.NAME));

        SharedPreferences sp = mMockContext.getSharedPreferences("info", Context.MODE_PRIVATE);
        assertNotNull("info shared preference should exist", sp);

        assertEquals("name field should equal to the defined value", name, sp.getString("name", ""));
        assertEquals("age field should equal to the defined value", age, sp.getInt("age", -1));

        mActivity.getSupportFragmentManager().executePendingTransactions();

        assertNotNull("Show Fragment should be visible", mActivity.getSupportFragmentManager().findFragmentByTag(FormShowFragment.NAME));
        assertNull("Save Fragment should not be visible", mActivity.getSupportFragmentManager().findFragmentByTag(FormSaveFragment.NAME));
    }

}
