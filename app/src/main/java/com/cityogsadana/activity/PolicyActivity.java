package com.cityogsadana.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_web)
public class PolicyActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewById(R.id.activity_policy)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.web)
    WebView webView;

    private String type;
    @AfterViews
    public  void setData()
    {
        Global.setFont(viewGroup, Global.regular);

        if(type.equalsIgnoreCase(Config.T_C))
        {
            title.setText("Terms & Conditions");
            setWebview(Config.TERMS_URL);
        }else {
            title.setText("Privacy Policy");
            setWebview(Config.POLICY_URL);

        }
        backButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_cancel));
        backButton.setOnClickListener(this);

    }

    private void setWebview(String url) {

        webView.loadUrl(url);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type = getIntent().getStringExtra("type");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {

            case R.id.back_button:
                onBackPressed();
                break;

        }
    }
}
