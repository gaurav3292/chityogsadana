package com.cityogsadana.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.error.VolleyError;
import com.cityogsadana.R;
import com.cityogsadana.adapter.NotificationAdapter;
import com.cityogsadana.bean.NotificationBean;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.handler.ApiHandler;
import com.cityogsadana.interfaces.DataHandlerCallback;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.CustomJsonParams;
import com.cityogsadana.utils.ErrorHelper;
import com.cityogsadana.utils.Global;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EActivity(R.layout.activity_notification)
public class NotificationActivity extends AppCompatActivity implements View.OnClickListener{

    @ViewById(R.id.activity_notification)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.list)
    RecyclerView list;


    private UserBean userBean;
    private NotificationAdapter notificationAdapter;
    private ConnectionMessageDialog cDialog =  new ConnectionMessageDialog();
    private List<NotificationBean> notifications;

    @AfterViews
    public void setData()
    {
        Global.setFont(viewGroup,Global.regular);
        title.setText("Notifications");

        backButton.setOnClickListener(this);

        setListAdapter(notifications);

    }



    private void setListAdapter(List<NotificationBean> notiList) {
        notificationAdapter = new NotificationAdapter(this,notiList);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setNestedScrollingEnabled(false);
        list.setAdapter(notificationAdapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBean = UserPref.getUser(this);
        notifications = (List<NotificationBean>) getIntent().getSerializableExtra("data");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.back_button:
                onBackPressed();
                break;
            
        }
    }

}
