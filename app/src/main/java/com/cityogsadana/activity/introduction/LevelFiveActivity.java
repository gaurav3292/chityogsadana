package com.cityogsadana.activity.introduction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_level_five)
public class LevelFiveActivity extends AppCompatActivity implements View.OnClickListener{

    @ViewById(R.id.activity_level_five)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;

    @AfterViews
    public void setData()
    {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Introduction Level 5");

        backButton.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.back_button:
                onBackPressed();
                break;

        }
    }
}
