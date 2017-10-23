package com.cityogsadana.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cityogsadana.DataEntry;
import com.cityogsadana.R;
import com.cityogsadana.adapter.SelfTestAdapter;
import com.cityogsadana.bean.QuestionBean;
import com.cityogsadana.bean.SelfTestBean;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.Global;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_self_test)
public class SelfTestActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewById(R.id.activity_self_test)
    ViewGroup viewGroup;
    @ViewById(R.id.list)
    RecyclerView recyclerView;
    @ViewById(R.id.button_next)
    TextView nextBtn;
    @ViewById(R.id.back_button)
    ImageButton backButton;

    private UserBean userBean;
    private SelfTestAdapter selfTestAdapter;
    private DataEntry dataEntry = new DataEntry();

    @AfterViews
    public void setData() {

        Global.setFont(viewGroup, Global.regular);

        selfTestAdapter = new SelfTestAdapter(this, this, getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(selfTestAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        nextBtn.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    public List<String> getData(){
//        List<String> stringList = new ArrayList<>();
//        stringList.add("I experience worry and anxiety");
//        stringList.add("My mind is always busy");
//        stringList.add("I get stressed easily");
//        stringList.add("I can feel depressed easily");
//        stringList.add("I suffer from panic attacks");
//
//        return stringList;
//    }

    public  ArrayList<SelfTestBean> getData()

    {
        ArrayList<SelfTestBean> data = new ArrayList<>();

        String[] heading = {"Current Emotional State", "Substance Dependency", "Sleep", "Previous History", "Ethics", "Diet"};
        ArrayList<ArrayList<QuestionBean>> questions = new ArrayList<>();
        questions.add(dataEntry.getEmoList());
//        questions.add(dataEntry.getDependencyList());


        for (int i = 0; i < heading.length; i++) {
            SelfTestBean current = new SelfTestBean();
            current.setHeading(heading[i]);
            current.setQuestionBeanList(questions.get(i));
            data.add(current);


        }
//        ItemList itemList = new ItemList();
//        itemList.setData(data);
//        // conversion gson to json
//        Gson gson = new Gson();
//        String jsonData = gson.toJson(itemList, ItemList.class);
//        return jsonData;
        return data;

    }





    @Override
    protected void onResume() {
        super.onResume();
        userBean = UserPref.getUser(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_next:
                Intent next = new Intent(this, ResultActivity_.class);
                next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(next);
                break;

            case R.id.back_button:
                onBackPressed();
                break;
        }

    }
}
