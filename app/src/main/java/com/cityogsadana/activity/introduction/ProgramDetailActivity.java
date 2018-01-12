package com.cityogsadana.activity.introduction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_program_detail)
public class ProgramDetailActivity extends AppCompatActivity implements View.OnClickListener {


    @ViewById(R.id.activity_program_detail)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.done_button)
    TextView doneButton;
    @ViewById(R.id.intro_text)
    TextView introText;

    private UserBean userBean;
    private String subLevel, value;

    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Program");

        backButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);

        setLevelData(value, subLevel);

        if (subLevel.equalsIgnoreCase(userBean.getLevel().getUserSubLevel())) {
            doneButton.setVisibility(View.VISIBLE);
        } else {
            doneButton.setVisibility(View.GONE);
        }


    }

    private void setLevelData(String value, String subLevel) {

        switch (value) {

            case "51":
                if (subLevel.equalsIgnoreCase("1")) {
                    introText.setText(R.string.p1_51);
                } else {
                    introText.setText(R.string.p2_51);
                }

                break;

            case "52":
                if (subLevel.equalsIgnoreCase("1")) {
                    introText.setText(R.string.p1_52);
                } else {
                    introText.setText(R.string.p2_52);
                }
                break;

            case "53":
                if (subLevel.equalsIgnoreCase("1")) {
                    introText.setText(R.string.p1_53);
                } else {
                    introText.setText(R.string.p2_53);
                }
                break;

            case "54":
                if (subLevel.equalsIgnoreCase("1")) {
                    introText.setText(R.string.p1_54);
                } else {
                    introText.setText(R.string.p2_54);
                }
                break;


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBean = UserPref.getUser(this);
        value = (String) getIntent().getSerializableExtra("value");
        subLevel = (String) getIntent().getSerializableExtra("subLevel");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.done_button:
                break;

        }
    }
}
