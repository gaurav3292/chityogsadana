package com.cityogsadana.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.utils.Global;

import java.util.List;

/**
 * Created by system2 on 10/23/2017.
 */

public class CountrySpinnerAdapter extends BaseAdapter {

    Context context;
    private List<String> countires;

    public CountrySpinnerAdapter(Context context, List<String> countires) {
        this.context = context;
        this.countires = countires;
    }


    @Override
    public int getCount() {
        return countires.size();
    }

    @Override
    public Object getItem(int position) {
        return countires.get(position);
    }

    @Override
    public long getItemId(int position) {
        return countires.indexOf(countires.get(position));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.spinner_item, viewGroup, false);
        TextView txt = (TextView) rowView.findViewById(R.id.txt);
        txt.setTypeface(Global.regular);
        String data = countires.get(i);
        txt.setText(data);

        return rowView;
    }
}
