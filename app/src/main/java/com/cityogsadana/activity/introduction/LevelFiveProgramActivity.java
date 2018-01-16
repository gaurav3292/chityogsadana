package com.cityogsadana.activity.introduction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.activity.LevelActivity;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_level_five_program)
public class LevelFiveProgramActivity extends AppCompatActivity implements View.OnClickListener {


    @ViewById(R.id.activity_level_five_program)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.step_1)
    TextView step1;
    @ViewById(R.id.step_2)
    TextView step2;

    private String value;
    private UserBean userBean;

    @AfterViews()
    public void setData() {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Level 5");

        backButton.setOnClickListener(this);

        if (userBean.getLevel().getUserSubLevel().equalsIgnoreCase("1")) {
            step1.setOnClickListener(this);
        } else {
            step2.setOnClickListener(this);
        }


        setValueData(value);

    }

    private void setValueData(String value) {
        switch (value) {

            case "51":
                step1.setText("Open Eye Candle Meditation");
                step2.setText("Closed Eye Candle Meditation");
                break;

            case "52":
                step1.setText("Visualisation");
                step2.setText("Closed Eye Candle Meditation");
                break;

            case "53":
                step1.setText("Sound Meditation");
                step2.setText("Closed Eye Candle Meditation");
                break;

            case "54":
                step1.setText("Light Music Meditation");
                step2.setText("Closed Eye Candle Meditation");
                break;

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        value = (String) getIntent().getSerializableExtra("value");
        userBean = UserPref.getUser(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.step_1:
                Intent intent5 = new Intent(this, ProgramDetailActivity_.class);
                intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent5.putExtra("value", value);
                intent5.putExtra("subLevel", "1");
                startActivity(intent5);
                break;

            case R.id.step_2:
                Intent intent = new Intent(this, ProgramDetailActivity_.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("value", value);
                intent.putExtra("subLevel", "2");
                startActivity(intent);
                break;

        }
    }
}
