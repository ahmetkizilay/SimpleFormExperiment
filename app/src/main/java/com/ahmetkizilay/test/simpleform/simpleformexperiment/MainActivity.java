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
            addSaveFragment();
        }
        else {
            addShowFragment(user);
        }
    }

    private void addSaveFragment() {
        FormSaveFragment frgSave = FormSaveFragment.getInstance();
        frgSave.setActionListener(new FormSaveFragment.ActionListener() {
            @Override
            public void onSave(String name, int age) {
                UserStorage.updateUser(MainActivity.this, name, age);

                User user = UserStorage.getUser(MainActivity.this);
                addShowFragment(user);
            }
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.loFrgContainer, frgSave, FormSaveFragment.NAME)
                .commit();
    }

    private void addShowFragment(User user) {
        FormShowFragment frgShow = FormShowFragment.getInstance(user.getName(), user.getAge());
        frgShow.setActionListener(new FormShowFragment.ActionListener() {
            @Override
            public void onClearRequested() {
                UserStorage.clearUser(MainActivity.this);

                addSaveFragment();
            }
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.loFrgContainer, frgShow, FormShowFragment.NAME)
                .commit();
    }
}
