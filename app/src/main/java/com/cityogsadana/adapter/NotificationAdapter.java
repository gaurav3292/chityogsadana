package com.cityogsadana.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.bean.NotificationBean;
import com.cityogsadana.utils.Global;

import java.util.ArrayList;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolderList> {

    private Context context;
    private ArrayList<NotificationBean> notificationList;

    public NotificationAdapter(Context context, ArrayList<NotificationBean> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @Override
    public NotificationAdapter.ViewHolderList onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        NotificationAdapter.ViewHolderList viewHolderList;
        viewHolderList = new NotificationAdapter.ViewHolderList(view);
        return viewHolderList;
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.ViewHolderList holder, int position) {
        NotificationBean bean = notificationList.get(position);
        holder.bind(bean);
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }


    public class ViewHolderList extends RecyclerView.ViewHolder {

        private ViewGroup viewGroup;
        private TextView messageTxt, dateTxt;

        public ViewHolderList(View itemView) {
            super(itemView);

            viewGroup = (ViewGroup) itemView.findViewById(R.id.item_notifiaction);
            messageTxt = (TextView) itemView.findViewById(R.id.message);
            dateTxt = (TextView) itemView.findViewById(R.id.date);

        }


        public void bind(NotificationBean bean) {
            Global.setFont(viewGroup, Global.regular);

            messageTxt.setText(bean.getMessage());
            dateTxt.setText(bean.getDate());
        }
    }
}
