package com.cityogsadana.activity.introduction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.activity.LevelActivity;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_four_program)
public class FourProgramActivity extends AppCompatActivity implements View.OnClickListener{


    @ViewById(R.id.activity_four_program)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.prog_1)
    TextView prog1;
    @ViewById(R.id.prog_2)
    TextView prog2;
    @ViewById(R.id.prog_3)
    TextView prog3;
    @ViewById(R.id.prog_4)
    TextView prog4;

    @AfterViews
    public void setData()
    {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Level 5");

        backButton.setOnClickListener(this);
        prog1.setOnClickListener(this);
        prog2.setOnClickListener(this);
        prog3.setOnClickListener(this);
        prog4.setOnClickListener(this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.prog_1:
                Intent intent5 = new Intent(this, LevelFiveProgramActivity_.class);
                intent5.putExtra("value", "51");
                intent5.putExtra("isVisible", true);
                startActivity(intent5);

                break;

            case R.id.prog_2:
                Intent intent = new Intent(this, LevelFiveProgramActivity_.class);
                intent.putExtra("value", "52");
                intent.putExtra("isVisible", true);
                startActivity(intent);
                break;


             case R.id.prog_3:
                 Intent intent1 = new Intent(this, LevelFiveProgramActivity_.class);
                 intent1.putExtra("value", "53");
                 intent1.putExtra("isVisible", true);
                 startActivity(intent1);
                break;

            case R.id.prog_4:
                Intent intent2 = new Intent(this, LevelFiveProgramActivity_.class);
                intent2.putExtra("value","54");
                intent2.putExtra("isVisible", true);
                startActivity(intent2);
                break;


        }
    }
}
