package com.verma.mobile.android.demoretrofitandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by verma on 26-12-2017.
 */

public class RetrofitActivity extends AppCompatActivity {


    @BindView(R.id.idListRetrofit) public ListView listView;
    private QuotesListAdapter adapter;
    private static ArrayList<Quotes> arrayList = new ArrayList<Quotes>();
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        unbinder =ButterKnife.bind(this);

        adapter = new QuotesListAdapter(RetrofitActivity.this, arrayList);
        listView.setAdapter(adapter);

    }


    @OnClick({ R.id.idButtonClean, R.id.idButtonRetrofit})
    public void pickDoor(Button door) {
        if (R.id.idButtonClean ==door.getId()) {
            arrayList.clear();
            adapter.notifyDataSetChanged();
        } else  if (R.id.idButtonRetrofit ==door.getId()){
            try {
                callMe();
            } catch (IOException e) {
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void callMe() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Quotes>> call = api.getQuotes();
        call.enqueue(new Callback<List<Quotes>>() {
            @Override
            public void onResponse(Call<List<Quotes>> call, Response<List<Quotes>> response) {
                List<Quotes> quotes = response.body();

                Iterator iter = quotes.iterator();
                while (iter.hasNext()) {
                    Quotes records =(Quotes)iter.next();
                    arrayList.add(records);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Quotes>>call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
