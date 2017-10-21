package com.cityogsadana.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.adapter.SelfTestAdapter;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_self_test)
public class SelfTestActivity extends AppCompatActivity implements View.OnClickListener{

    @ViewById(R.id.activity_self_test)
    ViewGroup viewGroup;
    @ViewById(R.id.noti_img)
    ImageView profileTab;
    @ViewById(R.id.home_img)
    ImageView homeImg;
    @ViewById(R.id.list)
    RecyclerView recyclerView;
    @ViewById(R.id.button_next)
    TextView nextBtn;

    private UserBean userBean;
    private SelfTestAdapter selfTestAdapter;

    @AfterViews
    public void setData(){

        Global.setFont(viewGroup,Global.regular);

        homeImg.setOnClickListener(this);

        selfTestAdapter = new SelfTestAdapter(this,this,getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(selfTestAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        nextBtn.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public List<String> getData(){
        List<String> stringList = new ArrayList<>();
        stringList.add("I experience worry and anxiety");
        stringList.add("My mind is always busy");
        stringList.add("I get stressed easily");
        stringList.add("I can feel depressed easily");
        stringList.add("I suffer from panic attacks");

        return stringList;
    }

    @Override
    protected void onResume() {
        super.onResume();
        userBean = UserPref.getUser(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.home_img:
                Intent level = new Intent(this,LevelActivity_.class);
                level.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(level);
                break;

            case R.id.button_next:
                Intent next = new Intent(this,ResultActivity_.class);
                next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(next);
                break;
        }

    }
}
