package com.ahmetkizilay.test.simpleform.simpleformexperiment.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ahmetkizilay.test.simpleform.simpleformexperiment.R;

/**
 * Created by ahmetkizilay on 20.12.2014.
 */
public class FormShowFragment extends Fragment {

    public static final String NAME = "FRG_FORM_SHOW";

    public static FormShowFragment getInstance(String name, int age) {
        FormShowFragment frg = new FormShowFragment();

        Bundle args = new Bundle();
        args.putString("name", name);
        args.putInt("age", age);

        frg.setArguments(args);

        return frg;
    }

    private TextView tvName;
    private TextView tvAge;

    private Button btnClear;

    private ActionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_formshow, container, false);

        Bundle args = getArguments();
        String name = args.getString("name");
        int age = args.getInt("age");

        tvName = (TextView) v.findViewById(R.id.tvName);
        tvAge = (TextView) v.findViewById(R.id.tvAge);
        btnClear = (Button) v.findViewById(R.id.btnClear);

        tvName.setText(name);
        tvAge.setText(age + "");

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    mListener.onClearRequested();
                }
            }
        });

        return v;
    }

    public void setActionListener(ActionListener listener) {
        this.mListener = listener;
    }

    public interface ActionListener {
        public void onClearRequested();
    }


}
