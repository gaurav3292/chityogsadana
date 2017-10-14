package com.cityogsadana.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.utils.Config;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @ViewById(R.id.activity_main)
    ViewGroup viewGroup;
    @ViewById(R.id.chit_yog)
    TextView chitYogTxt;
    @ViewById(R.id.chit)
    TextView chitTxt;
    @ViewById(R.id.needs)
    TextView needsTxt;
    @ViewById(R.id.works)
    TextView worksTxt;
    @ViewById(R.id.test)
    TextView testTxt;
    @ViewById(R.id.aim)
    TextView aimTxt;

    @AfterViews
    public void setData()
    {

        chitYogTxt.setOnClickListener(this);
        chitTxt.setOnClickListener(this);
        testTxt.setOnClickListener(this);
        aimTxt.setOnClickListener(this);
        worksTxt.setOnClickListener(this);
        needsTxt.setOnClickListener(this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.chit_yog:
                Intent intent = new Intent(this,DescriptionActivity_.class);
                intent.putExtra("type", Config.CHIT_YOG);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            case R.id.chit:
                Intent intent1 = new Intent(this,DescriptionActivity_.class);
                intent1.putExtra("type", Config.CHIT);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                break;

            case R.id.aim:
                Intent intent2 = new Intent(this,DescriptionActivity_.class);
                intent2.putExtra("type", Config.AIM);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                break;

            case R.id.works:
                Intent intent3 = new Intent(this,DescriptionActivity_.class);
                intent3.putExtra("type", Config.WORKS);
                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                break;

            case R.id.needs:
                Intent intent4 = new Intent(this,DescriptionActivity_.class);
                intent4.putExtra("type", Config.NEED);
                intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent4);
                break;

            case R.id.test:
                break;

        }
    }
}
