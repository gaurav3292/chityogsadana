package com.cityogsadana.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.activity.introduction.LevelFiveActivity;
import com.cityogsadana.activity.introduction.LevelFiveActivity_;
import com.cityogsadana.activity.introduction.LevelFourActivity;
import com.cityogsadana.activity.introduction.LevelOneActivity;
import com.cityogsadana.activity.introduction.LevelOneActivity_;
import com.cityogsadana.activity.introduction.LevelThreeActivity;
import com.cityogsadana.activity.introduction.LevelThreeActivity_;
import com.cityogsadana.activity.introduction.LevelTwoActivity;
import com.cityogsadana.activity.introduction.LevelTwoActivity_;
import com.cityogsadana.activity.introduction.SubLevelFourActivity_;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.prefrences.UserPref;
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
    @ViewById(R.id.level_one)
    LinearLayout level1;
    @ViewById(R.id.level_two)
    LinearLayout level2;
    @ViewById(R.id.level_three)
    LinearLayout level3;
    @ViewById(R.id.level_four)
    LinearLayout level4;
    @ViewById(R.id.level_five)
    LinearLayout level5;
    @ViewById(R.id.icon_1)
    ImageView icon1;
    @ViewById(R.id.icon_2)
    ImageView icon2;
    @ViewById(R.id.icon_3)
    ImageView icon3;
    @ViewById(R.id.icon_4)
    ImageView icon4;
    @ViewById(R.id.icon_5)
    ImageView icon5;
    @ViewById(R.id.icon_6)
    ImageView icon6;
    @ViewById(R.id.result_1)
    TextView result1;
    @ViewById(R.id.result_2)
    TextView result2;
    @ViewById(R.id.result_3)
    TextView result3;
    @ViewById(R.id.result_4)
    TextView result4;
    @ViewById(R.id.result_5)
    TextView result5;
    @ViewById(R.id.result_6)
    TextView result6;
    @ViewById(R.id.days_1)
    TextView days1;
    @ViewById(R.id.days_2)
    TextView days2;
    @ViewById(R.id.days_3)
    TextView days3;
    @ViewById(R.id.days_4)
    TextView days4;
    @ViewById(R.id.days_5)
    TextView days5;
    @ViewById(R.id.days_6)
    TextView days6;
    @ViewById(R.id.level_six)
    LinearLayout level6;

    private UserBean userBean;

    @AfterViews
    public void setData()
    {
        Global.setFont(viewGroup,Global.regular);

        title.setText("Levels");

        backButton.setOnClickListener(this);


        setLevels(userBean.getLevel());

    }

    private void setLevels(String level) {

        switch (level)
        {
            case "1":

                level1.setOnClickListener(this);
                break;

            case "2":
                level2.setOnClickListener(this);
                break;

            case "3":
                level3.setOnClickListener(this);
                break;

            case "4":
                level4.setOnClickListener(this);
                break;

            case "5":
                level5.setOnClickListener(this);
                break;

            case "6":
                level6.setOnClickListener(this);
                break;

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBean = UserPref.getUser(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.level_one:
                Intent intent = new Intent(LevelActivity.this, LevelOneActivity_.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.level_two:
                Intent intent2 = new Intent(LevelActivity.this, LevelTwoActivity_.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                break;
            case R.id.level_three:
                Intent intent3 = new Intent(LevelActivity.this, LevelThreeActivity_.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                break;
            case R.id.level_four:
                Intent intent4 = new Intent(LevelActivity.this, SubLevelFourActivity_.class);
                intent4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent4);
                break;
            case R.id.level_five:
                Intent intent5 = new Intent(LevelActivity.this, LevelFiveActivity_.class);
                intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent5);
                break;

        }
    }
}
