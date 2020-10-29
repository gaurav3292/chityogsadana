package com.cityogsadana.adapter;

import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.bean.QuestionBean;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.Global;

import java.util.List;

/**
 * Created by aa on 10/21/2017.
 */

public class SelfTestAdapter extends RecyclerView.Adapter<SelfTestAdapter.ViewHolderList> {

    private Context context;
    private Activity activity;
    private List<QuestionBean> data;


    public SelfTestAdapter(Context context, Activity activity, List<QuestionBean> data) {
        this.context = context;
        this.activity = activity;
        this.data = data;
    }

    public List<QuestionBean> getData() {
        return data;
    }

    @Override
    public ViewHolderList onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_self_test, parent, false);
        SelfTestAdapter.ViewHolderList viewHolderList;
        viewHolderList = new SelfTestAdapter.ViewHolderList(view, context);
        return viewHolderList;
    }

    @Override
    public void onBindViewHolder(ViewHolderList holder, int position) {
        QuestionBean questionBean = data.get(position);
        holder.bind(questionBean);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolderList extends RecyclerView.ViewHolder {

        ViewGroup viewGroup;
        TextView ques;
        RadioButton trueBtn, falseBtn;

        public ViewHolderList(View itemView, Context context) {
            super(itemView);

            viewGroup = (ViewGroup) itemView.findViewById(R.id.item_self_test);
            ques = (TextView) itemView.findViewById(R.id.ques);
            trueBtn = (RadioButton) itemView.findViewById(R.id.radio_true);
            falseBtn = (RadioButton) itemView.findViewById(R.id.radio_false);

            Global.setFont(viewGroup, Global.regular);


        }

        public void bind(final QuestionBean str) {

            ques.setText(str.getQuestion());

            if (str.getAnswers() != null) {

                if (str.getAnswers().equalsIgnoreCase(Config.TRUE)) {
                    trueBtn.setChecked(true);
                } else {
                    falseBtn.setChecked(true);
                }
            }

            trueBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (b) {
                        str.setAnswers(Config.TRUE);
                    }


                }
            });


            falseBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        str.setAnswers(Config.FALSE);
                    }


                }
            });

        }

    }
}
