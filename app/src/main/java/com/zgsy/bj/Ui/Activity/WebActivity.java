package com.zgsy.bj.Ui.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zgsy.bj.R;
import com.zgsy.bj.Ui.Fragment.ContentFragment;
import com.zgsy.bj.Ui.Fragment.ContentWebFragment;
import com.zgsy.bj.Ui.Fragment.FirstFragment;

import java.util.ArrayList;

public class WebActivity extends AppCompatActivity {
    FragmentTransaction transaction = getSupportFragmentManager()
            .beginTransaction();
    private int count = 0;
    private ArrayList<Object> list = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp);
        FirstFragment firstFragment = FirstFragment.newInstance();
        list.add(firstFragment);
        if (getIntent().hasExtra("num")) {
            if (getIntent().getStringExtra("num").equals("1")) {
                count = 1;
                transaction.replace(R.id.main_container, firstFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
                transaction.show(firstFragment);
            }
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("position", getIntent().getExtras().getString("contentPosition"));
        ContentWebFragment contentFragment = ContentWebFragment.newInstance(bundle);
        fragmentTransaction.replace(R.id.main_container, contentFragment);
        fragmentTransaction.commit();
        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.show(contentFragment);
    }


}
