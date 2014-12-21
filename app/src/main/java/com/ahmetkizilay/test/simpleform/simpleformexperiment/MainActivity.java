package com.ahmetkizilay.test.simpleform.simpleformexperiment;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ahmetkizilay.test.simpleform.simpleformexperiment.fragments.FormSaveFragment;
import com.ahmetkizilay.test.simpleform.simpleformexperiment.fragments.FormShowFragment;
import com.ahmetkizilay.test.simpleform.simpleformexperiment.wrappers.User;
import com.ahmetkizilay.test.simpleform.simpleformexperiment.wrappers.UserStorage;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = UserStorage.getUser(this);

        if(user.getName().equals("")) {
            FormSaveFragment frgSave = FormSaveFragment.getInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.loFrgContainer, frgSave, FormSaveFragment.NAME)
                    .commit();
        }
        else {
            FormShowFragment frgShow = FormShowFragment.getInstance(user.getName(), user.getAge());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.loFrgContainer, frgShow, FormShowFragment.NAME)
                    .commit();
        }

    }
}
