package com.cityogsadana.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.adapter.SelfTestAdapter;
import com.cityogsadana.application.AppController;
import com.cityogsadana.bean.QuestionBean;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.ConnectivityReceiver;
import com.cityogsadana.utils.CustomCrouton;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_test)
public class TestActivity extends AppCompatActivity implements View.OnClickListener, ConnectivityReceiver.ConnectivityReceiverListener {

    @ViewById(R.id.activity_test)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;
    @ViewById(R.id.list)
    RecyclerView recyclerView;
    @ViewById(R.id.button_next)
    TextView nextBtn;
    @ViewById(R.id.back_button)
    ImageButton backButton;

    private SelfTestAdapter selfTestAdapter;
    private ArrayList<QuestionBean> listData;


    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Test");


        getAdapterData();
    }

    private void getAdapterData() {

        selfTestAdapter = new SelfTestAdapter(this, this, listData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(selfTestAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listData = (ArrayList<QuestionBean>) getIntent().getSerializableExtra("data");
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            new CustomCrouton(this, getString(R.string.no_connection), errorLayout).setInAnimation();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.button_next:
                boolean check = validate();
                if (!check) {

                }
                break;

        }
    }

    private boolean validate() {
        boolean error = false;
        int t = 0;
        List<QuestionBean> questionBeanList = selfTestAdapter.getData();
        for (int i = 0; i < questionBeanList.size(); i++) {

            QuestionBean queeBean = questionBeanList.get(i);
            if (queeBean.getAnswers() == null) {
                error = true;
                new CustomCrouton(this, "Please mark all answers", errorLayout).setInAnimation();
                break;

            } else {
                if (queeBean.getAnswers().equalsIgnoreCase(Config.TRUE)) {
                    t++;
                }
            }
        }

//        data.get(masterPosition).setT(t);
//        data.get(masterPosition).setF(data.get(masterPosition).getQuestionBeanList().size() - t);
//
//        totalTrue = totalTrue + t;

        return error;
    }
}
