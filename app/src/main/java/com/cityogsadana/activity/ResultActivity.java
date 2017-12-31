package com.cityogsadana.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.prefrences.UserPref;
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

@EActivity(R.layout.activity_result)
public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    @ViewById(R.id.activity_result)
    ViewGroup viewGroup;
    @ViewById(R.id.noti_img)
    ImageView profileTab;
    @ViewById(R.id.chart)
    PieChart mChart;
    @ViewById(R.id.button_done)
    TextView doneBtn;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.title)
    TextView title;

    private UserBean userBean;
    private int totalTrue,totalFalse;
    private String msg;
    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();

    protected String[] mParties = new String[] {
            "Yes", "No"
    };

    @AfterViews
    public void setData(){

        Global.setFont(viewGroup,Global.regular);

        title.setText("Result");

        doneBtn.setOnClickListener(this);
        backButton.setOnClickListener(this);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

      //  mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        setData(2, 10);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTypeface(Global.regular);
        mChart.setEntryLabelTextSize(12f);



    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 2, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 2, s.length() - 3, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 2, s.length() - 3, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 2, s.length() - 3, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 2, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 2, s.length(), 0);
        return s;
    }

    private void setData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.


        entries.add(new PieEntry((float) (( totalTrue* mult) + mult / 5),
                mParties[0]));

        entries.add(new PieEntry((float) ((totalFalse * mult) + mult / 5),
                mParties[1]));

        PieDataSet dataSet = new PieDataSet(entries, "Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();


            colors.add(R.color.icon_color);


            colors.add(R.color.app_red);

       // colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(Global.regular);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBean = UserPref.getUser(this);
        msg = getIntent().getExtras().getString("msg");
        totalTrue = Integer.parseInt(userBean.getSelf_result());
        totalFalse = getIntent().getExtras().getInt("false");
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.button_done:
                cDialog.showResult(this, "Congratulations",msg, "Start", false);
                break;

            case R.id.back_button:
                onBackPressed();
                break;

        }

    }
}
