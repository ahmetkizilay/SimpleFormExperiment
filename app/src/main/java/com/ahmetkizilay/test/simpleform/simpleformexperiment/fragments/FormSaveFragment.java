package com.ahmetkizilay.test.simpleform.simpleformexperiment.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ahmetkizilay.test.simpleform.simpleformexperiment.R;

/**
 * Created by ahmetkizilay on 20.12.2014.
 */
public class FormSaveFragment extends Fragment {

    public static final String NAME = "FRG_FORM_SAVE";

    public static FormSaveFragment getInstance() {
        FormSaveFragment frg = new FormSaveFragment();
        Bundle args = new Bundle();
        frg.setArguments(args);
        return frg;
    }

    private EditText etName;
    private EditText etAge;

    private Button btnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_formsave, container, false);

        etName = (EditText) v.findViewById(R.id.etName);
        etAge = (EditText) v.findViewById(R.id.etAge);

        btnSubmit = (Button) v.findViewById(R.id.btnSubmit);

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateButtonState();
            }
        };

        etName.addTextChangedListener(tw);
        etAge.addTextChangedListener(tw);

        return v;
    }

    /**
     * disables the button according to the following:
     *
     * Name and age cannot be empty
     * Age has to be an integer
     */
    private void updateButtonState() {
        boolean enabled = !etName.getText().toString().equals("") &&
                !etAge.getText().toString().equals("");
        if(enabled) {
            try {
                Integer.parseInt(etAge.getText().toString());
            } catch(NumberFormatException nfe) {
                enabled = false;
            }
        }

        btnSubmit.setEnabled(enabled);
    }
}
