package com.cityogsadana.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_discription)
public class DescriptionActivity extends AppCompatActivity {

    @ViewById(R.id.activity_discription)
    ViewGroup viewGroup;
    @ViewById(R.id.heading_txt)
    TextView headingTxt;
    @ViewById(R.id.content_txt)
    TextView contentTxt;

    private String heading ;

    @AfterViews
    public void setData()
    {
        Global.setFont(viewGroup,Global.regular);
        Global.setCustomFont(Global.bold,findViewById(R.id.heading_txt));


        headingTxt.setText(heading);

        setContent(heading);

    }

    private void setContent(String heading) {

        switch (heading)
        {

            case Config.CHIT_YOG:

                contentTxt.setText(getResources().getString(R.string.chit_yog));

                break;

            case Config.CHIT:

                contentTxt.setText(getResources().getString(R.string.chit));

                break;

            case Config.AIM:

                contentTxt.setText(getResources().getString(R.string.aim));

                break;

            case Config.NEED:

                contentTxt.setText(getResources().getString(R.string.need));

                break;

            case Config.WORKS:

                contentTxt.setText(getResources().getString(R.string.works));

                break;


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().hasExtra("type"))
        {
            heading = getIntent().getStringExtra("type");
        }

    }
}
