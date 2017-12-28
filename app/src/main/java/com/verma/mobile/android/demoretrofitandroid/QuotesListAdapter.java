package com.verma.mobile.android.demoretrofitandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.verma.mobile.android.demoretrofitandroid.service.quotes.Quotes;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by verma on 26-12-2017.
 */

public class QuotesListAdapter extends BaseAdapter {

    private ArrayList<Quotes> arrayList;
    private Context context;
    private LayoutInflater inflater;

    public QuotesListAdapter(Context context, ArrayList<Quotes> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @BindView(R.id.textViewID)                  TextView textViewID;
    @BindView(R.id.textViewTitle)               TextView textViewTitle;
    @BindView(R.id.textViewContent)             TextView textViewContent;

    public Unbinder unbinder;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Holder holder;
        if (v == null) {
            v = inflater.inflate(R.layout.inflate_quotes_list_item, null);
            unbinder = ButterKnife.bind(this, v);
            holder = new Holder();
            holder.textViewID = textViewID;
            holder.textViewContent = textViewContent;
            holder.textViewTitle=textViewTitle;
            v.setTag(holder);
        } else {
            holder = (Holder) v.getTag();
        }
        Quotes curRecords = arrayList.get(position);
        holder.textViewID.setText(String.valueOf(curRecords.getID()));
        holder.textViewTitle.setText(curRecords.getTitle());
        holder.textViewContent.setText(curRecords.getContent());

        return v;
    }

    class Holder {
        public TextView textViewID;
        public TextView textViewTitle;
        public TextView textViewContent;
    }

}
