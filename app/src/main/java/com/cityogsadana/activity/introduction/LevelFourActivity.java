package com.cityogsadana.activity.introduction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.activity.TestActivity_;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.interfaces.ActivitySet;
import com.cityogsadana.utils.AccountChecker;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.DataEntry;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@EActivity(R.layout.activity_level_four)
public class LevelFourActivity extends AppCompatActivity implements View.OnClickListener,ActivitySet{

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
    @ViewById(R.id.test)
    TextView testText;

    private UserBean userBean;
    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();
    private DataEntry dataEntry = new DataEntry();


    @AfterViews
    public  void setData()
    {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Level 4");
        testText.setVisibility(View.VISIBLE);

        if (userBean.getLevel().getTotalNumberOfDays()== 0) {
            testText.setText("Start Routine");
        } else {
            testText.setText("Start Test");
        }

        backButton.setOnClickListener(this);
        step_one.setOnClickListener(this);
        step_two.setOnClickListener(this);
        testText.setOnClickListener(this);

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

            case R.id.test:
                if (userBean.getLevel().getTotalNumberOfDays() == 0) {
                    cDialog.showStartRoutine(this, this, "Routine Test", "Do you want to start your routine test?", false);
                } else {
                    Date currentTime = Calendar.getInstance().getTime();
                    DateFormat df = new SimpleDateFormat("HH:mm:ss");
                    String currentTimeStr = df.format(currentTime);
                    boolean check = AccountChecker.checkTime(currentTimeStr);

                    if(check)
                    {
                        Intent intent1 = new Intent(this, TestActivity_.class);
                        intent1.putExtra("data",dataEntry.getLevelFourList());
                        intent1.putExtra("ques", Config.LevelFour_one);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);

                    }else {
                        cDialog.successShow(this, "Alert!", "Your test will be active at 21:30:00. (09:30 pm)","Ok", false);

                    }

                }
                break;
        }
    }

    @Override
    public void startRoutineTest() {

    }
}
