package com.verma.mobile.android.demoretrofitandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.verma.mobile.android.demoretrofitandroid.model.QuotesPresenterImp;
import com.verma.mobile.android.demoretrofitandroid.presenter.IQuotesPresenter;
import com.verma.mobile.android.demoretrofitandroid.service.quotes.Quotes;
import com.verma.mobile.android.demoretrofitandroid.view.IQuotesView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by verma on 26-12-2017.
 */

public class RetrofitActivity extends AppCompatActivity implements IQuotesView {


    private QuotesListAdapter adapter;
    private Unbinder unbinder;
    private IQuotesPresenter mIQuotesPresenter;
    private static ArrayList<Quotes> arrayList = new ArrayList<Quotes>();
    @BindView(R.id.idListRetrofit) public ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        unbinder =ButterKnife.bind(this);
        adapter = new QuotesListAdapter(RetrofitActivity.this, arrayList);
        mIQuotesPresenter = new QuotesPresenterImp(this);
        listView.setAdapter(adapter);
    }


    @OnClick({ R.id.idButtonClean, R.id.idButtonRetrofit})
    public void pickDoor(Button door) {
        if (R.id.idButtonClean ==door.getId()) {
            mIQuotesPresenter.cleanMe();
        } else  if (R.id.idButtonRetrofit ==door.getId()){
            mIQuotesPresenter.callMe();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void cleanDone() {
        arrayList.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onQuotesResponse(List<Quotes> quotes ) {
        Iterator iter = quotes.iterator();
        while (iter.hasNext()) {
            Quotes records =(Quotes)iter.next();
            arrayList.add(records);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onQuotesFailure(Throwable throwable) {
        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
