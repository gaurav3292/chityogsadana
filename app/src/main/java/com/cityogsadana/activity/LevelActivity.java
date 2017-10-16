package com.cityogsadana.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_level)
public class LevelActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewById(R.id.activity_level)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.level2)
    CardView level2;

    @AfterViews
    public void setData()
    {
        Global.setFont(viewGroup,Global.regular);

        title.setText("Levels");

        backButton.setOnClickListener(this);
        level2.setOnClickListener(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.level2:
                break;

        }
    }
}
