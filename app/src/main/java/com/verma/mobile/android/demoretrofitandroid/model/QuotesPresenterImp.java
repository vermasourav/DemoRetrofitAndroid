package com.verma.mobile.android.demoretrofitandroid.model;

import com.verma.mobile.android.demoretrofitandroid.presenter.IQuotesPresenter;
import com.verma.mobile.android.demoretrofitandroid.service.quotes.QuotesApi;
import com.verma.mobile.android.demoretrofitandroid.service.quotes.Quotes;
import com.verma.mobile.android.demoretrofitandroid.view.IQuotesView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by verma on 28-12-2017.
 */

public class QuotesPresenterImp implements IQuotesPresenter {
     public IQuotesView mIQuotesView;

    public QuotesPresenterImp(IQuotesView mIQuotesView) {
        this.mIQuotesView = mIQuotesView;
    }

    @Override
    public void cleanMe() {
        mIQuotesView.cleanDone();
    }

    @Override
    public void callMe() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(QuotesApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuotesApi quotesApi = retrofit.create(QuotesApi.class);
        Call<List<Quotes>> call = quotesApi.getQuotes();
        call.enqueue(new Callback<List<Quotes>>() {
            @Override
            public void onResponse(Call<List<Quotes>> call, Response<List<Quotes>> response) {
                List<Quotes> quotes = response.body();
                mIQuotesView.onQuotesResponse(quotes);
            }

            @Override
            public void onFailure(Call<List<Quotes>>call, Throwable t) {
                mIQuotesView.onQuotesFailure(t);
            }
        });

    }
}
