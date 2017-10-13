package com.cityogsadana.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cityogsadana.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.activity_main)
    ViewGroup viewGroup;
//    @ViewById(R.id.)
//    TextView
//    @ViewById(R.id.)
//    TextView
//    @ViewById(R.id.)
//    TextView
//    @ViewById(R.id.)
//    TextView
//    @ViewById(R.id.)
//    TextView
//    @ViewById(R.id.)
//    TextView

    @AfterViews
    public void setData()
    {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
