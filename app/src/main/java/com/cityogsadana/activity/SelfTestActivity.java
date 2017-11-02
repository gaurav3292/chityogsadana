package com.cityogsadana.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.error.VolleyError;
import com.cityogsadana.R;
import com.cityogsadana.adapter.SelfTestAdapter;
import com.cityogsadana.bean.QuestionBean;
import com.cityogsadana.bean.SelfTestBean;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.handler.ApiHandler;
import com.cityogsadana.interfaces.DataHandlerCallback;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.CustomCrouton;
import com.cityogsadana.utils.CustomJsonParams;
import com.cityogsadana.utils.DataEntry;
import com.cityogsadana.utils.ErrorHelper;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EActivity(R.layout.activity_self_test)
public class SelfTestActivity extends AppCompatActivity implements View.OnClickListener,DataHandlerCallback {

    @ViewById(R.id.activity_self_test)
    ViewGroup viewGroup;
    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;
    @ViewById(R.id.list)
    RecyclerView recyclerView;
    @ViewById(R.id.button_next)
    TextView nextBtn;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.section_name)
    TextView sectionName;
    @ViewById(R.id.title)
    TextView title;

    private UserBean userBean;
    private SelfTestAdapter selfTestAdapter;
    private DataEntry dataEntry = new DataEntry();

    private int masterPosition = 0;
    private ArrayList<SelfTestBean> data;
    private int totalTrue = 0;


    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();

    @AfterViews
    public void setData() {

        Global.setFont(viewGroup, Global.regular);
        title.setText("Self Assessment");

        ArrayList<SelfTestBean> data = getData();
        sectionName.setText(getData().get(masterPosition).getHeading());


        getAdapterData();

        nextBtn.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    private void getAdapterData() {

        selfTestAdapter = new SelfTestAdapter(this, this, getData().get(masterPosition).getQuestionBeanList());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(selfTestAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public ArrayList<SelfTestBean> getData()

    {
        data = new ArrayList<>();

        String[] heading = {"Current Emotional State", "Substance Dependency", "Sleep", "Previous History", "Ethics", "Diet"};
        ArrayList<ArrayList<QuestionBean>> questions = new ArrayList<>();
        questions.add(dataEntry.getEmoList());
        questions.add(dataEntry.getDependencyList());
        questions.add(dataEntry.getSleepList());
        questions.add(dataEntry.getPreviousHistoryList());
        questions.add(dataEntry.getEthicsList());
        questions.add(dataEntry.getDietList());


        for (int i = 0; i < heading.length; i++) {
            SelfTestBean current = new SelfTestBean();
            current.setHeading(heading[i]);
            current.setQuestionBeanList(questions.get(i));
            data.add(current);


        }
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

                boolean check = validate();
                if(!check){
                    if (masterPosition < getData().size()) {
                        masterPosition = masterPosition + 1;
                        getAdapterData();
                        sectionName.setText(getData().get(masterPosition).getHeading());
                    } else {

                        checkSelfTestResult();

                    }
                }



                break;

            case R.id.back_button:
                onBackPressed();
                break;
        }

    }

    private void checkSelfTestResult() {
        CustomJsonParams customJsonParams = new CustomJsonParams();
        JSONObject params = customJsonParams.getSelfTestParams(userBean.getUser_id(), totalTrue);
        new ApiHandler(SelfTestActivity.this).apiResponse(SelfTestActivity.this, Config.SELF_TEST, params);
    }

    private boolean validate() {
        boolean error = false;
        int t=0;
        List<QuestionBean> questionBeanList  = selfTestAdapter.getData();
        for(int i=0;i<questionBeanList.size();i++){

            QuestionBean queeBean = questionBeanList.get(i);
            if(queeBean.getAnswers()==null){
                error = true;
                new CustomCrouton(this, "Please mark all questions", errorLayout).setInAnimation();
                break;

            }else{
                if(queeBean.getAnswers().equalsIgnoreCase(Config.TRUE)){
                    t++;
                }



            }

        }

        data.get(masterPosition).setT(t);
        data.get(masterPosition).setF(data.get(masterPosition).getQuestionBeanList().size()-t);

        totalTrue = totalTrue+t;



        return error;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_change, R.anim.exit_to_right_fast);
    }

    @Override
    public void onSuccess(HashMap<String, Object> map) {

        JSONObject jsonObject = (JSONObject) map.get(Config.POST_JSON_RESPONSE);
        if(jsonObject!=null){

            try {
                String level = jsonObject.getString("level");
                String msg = jsonObject.getString("msg");

                Intent next = new Intent(this, ResultActivity_.class);
                next.putExtra("level",level);
                next.putExtra("msg",msg);
                next.putExtra("true",totalTrue);
                next.putExtra("false",data.get(masterPosition).getF());
                next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(next);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }



    }

    @Override
    public void onFailure(HashMap<String, Object> map) {
        if (map.containsKey(Config.ERROR)) {
            cDialog.successShow(this, "Error!", (String) map.get(Config.ERROR), "Ok", false);
        } else {
            VolleyError error = (VolleyError) map.get(Config.VOLLEY_ERROR);
            cDialog.successShow(this, "Error!", ErrorHelper.getErrorResponse(error), "Ok", false);

        }

    }
}
