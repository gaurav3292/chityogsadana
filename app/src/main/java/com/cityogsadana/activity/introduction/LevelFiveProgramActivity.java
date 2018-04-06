package com.cityogsadana.activity.introduction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cityogsadana.R;
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
    @ViewById(R.id.layout1)
    LinearLayout layout1;
    @ViewById(R.id.layout2)
    LinearLayout layout2;
    @ViewById(R.id.icon_1)
    ImageView icon1;
    @ViewById(R.id.icon_2)
    ImageView icon2;

    private String value;
    private boolean isVisible = false;
    private UserBean userBean;

    @AfterViews()
    public void setData() {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Level 5");

        backButton.setOnClickListener(this);


        if (isVisible) {
            layout1.setOnClickListener(this);
            layout2.setOnClickListener(this);

            icon1.setImageResource(R.drawable.ic_unlocked);
            icon2.setImageResource(R.drawable.ic_unlocked);

        } else {
            if (userBean.getLevel().getUserSubLevel().equalsIgnoreCase("1")) {
                layout1.setOnClickListener(this);
                icon1.setImageResource(R.drawable.ic_unlocked);
                icon2.setImageResource(R.drawable.ic_locked);
            } else {
                layout2.setOnClickListener(this);
                icon1.setImageResource(R.drawable.ic_check);
                icon2.setImageResource(R.drawable.ic_unlocked);
            }
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

        if (getIntent().hasExtra("isVisible")) {
            isVisible = (boolean) getIntent().getSerializableExtra("isVisible");
        }
        userBean = UserPref.getUser(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.layout1:
                Intent intent5 = new Intent(this, ProgramDetailActivity_.class);
                intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent5.putExtra("value", value);
                intent5.putExtra("subLevel", "1");
                startActivity(intent5);
                break;

            case R.id.layout2:
                Intent intent = new Intent(this, ProgramDetailActivity_.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("value", value);
                intent.putExtra("subLevel", "2");
                startActivity(intent);
                break;

        }
    }
}
