package com.cityogsadana.activity.introduction;

import android.content.Intent;
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

@EActivity(R.layout.activity_level_four)
public class LevelFourActivity extends AppCompatActivity implements View.OnClickListener{

    @ViewById(R.id.activity_level_four)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.step_1)
    TextView step_one;
    @ViewById(R.id.step_2)
    TextView step_two;

    @AfterViews
    public  void setData()
    {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Introduction Level 4");

        backButton.setOnClickListener(this);
        step_one.setOnClickListener(this);
        step_two.setOnClickListener(this);


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

            case R.id.step_1:
                Intent in = new Intent(this,SubLevelFourActivity_.class);
                in.putExtra("title_txt","Process your thoughts daily");
                in.putExtra("is_true",false);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);

                break;

            case R.id.step_2:
                Intent intent = new Intent(this,SubLevelFourActivity_.class);
                intent.putExtra("title_txt","Face the Mirror");
                intent.putExtra("is_true",true);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
