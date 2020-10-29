package com.cityogsadana.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.bean.LevelResultBean;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.CustomPieChart;
import com.cityogsadana.utils.Global;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_level_result)
public class LevelResultActivity extends AppCompatActivity implements View.OnClickListener{

    @ViewById(R.id.activity_level_result)
    ViewGroup viewGroup;
    @ViewById(R.id.noti_img)
    ImageView profileTab;
    @ViewById(R.id.chart)
    PieChart mChart;
    @ViewById(R.id.chart1)
    PieChart mChart1;
    @ViewById(R.id.button_done)
    TextView doneBtn;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.title)
    TextView title;

    private UserBean userBean;
    private LevelResultBean levelResultBean;
    private int totalTrue,totalFalse,completedDays,remainingDays;
    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();
    private CustomPieChart customPieChart,customPieChart1;

    protected String[] mParties = new String[] {
            "Completed","Remaining"
    };

    protected String[] mParties1 = new String[] {
            "Yes","No"
    };



    @AfterViews
    public void setData(){

        Global.setFont(viewGroup,Global.regular);

        title.setText("Result");

        doneBtn.setOnClickListener(this);
        backButton.setOnClickListener(this);
        totalTrue = levelResultBean.getLevel().getNumberOfTrue();

        int totalQuestions = levelResultBean.getLevel().getTotalNumberOfQuestions();
        completedDays = levelResultBean.getLevel().getCompletedNumberOfDays();
        remainingDays = levelResultBean.getLevel().getTotalNumberOfDays()-completedDays;

        int total = totalQuestions*completedDays;

        totalFalse = total-totalTrue;

        customPieChart = new CustomPieChart(mChart,mParties,completedDays,remainingDays);
        customPieChart1 = new CustomPieChart(mChart1,mParties1,totalTrue,totalFalse);

        customPieChart.initialisePieChart();
        customPieChart1.initialisePieChart();



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBean = UserPref.getUser(this);
        levelResultBean = (LevelResultBean) getIntent().getSerializableExtra("result");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.button_done:
                onBackPressed();
                break;

        }

    }
}
