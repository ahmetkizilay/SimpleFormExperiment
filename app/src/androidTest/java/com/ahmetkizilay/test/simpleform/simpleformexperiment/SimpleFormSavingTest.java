package com.ahmetkizilay.test.simpleform.simpleformexperiment;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ahmetkizilay on 20.12.2014.
 */
public class SimpleFormSavingTest extends ActivityUnitTestCase<MainActivity> {
    public SimpleFormSavingTest() {
        super(MainActivity.class);
    }

    private Activity mActivity;

    private EditText etName;
    private EditText etAge;

    private Button btnSubmit;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        ContextThemeWrapper context = new ContextThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);
        setActivityContext(context);

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
     * fields should be cleared and button should fall back to disabled after click
     * activity should open viewfragment
     */
    public void testSaveButtonClicked() {
        String name = "John Smith";
        String age = "22";

        etName.setText(name);
        etAge.setText(age);

        btnSubmit.performClick();

        assertTrue("Name field should be empty", etName.getText().toString().equals(""));
        assertTrue("Age field should be empty", etAge.getText().toString().equals(""));
        assertFalse("Submit button should be disabled", btnSubmit.isEnabled());
    }

}
