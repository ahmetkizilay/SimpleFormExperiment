package com.ahmetkizilay.test.simpleform.simpleformexperiment;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ahmetkizilay.test.simpleform.simpleformexperiment.fragments.FormSaveFragment;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FormSaveFragment frgSave = FormSaveFragment.getInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.loFrgContainer, frgSave, FormSaveFragment.NAME)
                .commit();
    }
}
