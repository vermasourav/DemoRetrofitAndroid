package com.verma.mobile.android.demoretrofitandroid.model;

import com.verma.mobile.android.demoretrofitandroid.service.quotes.Api;
import com.verma.mobile.android.demoretrofitandroid.service.quotes.Quotes;
import com.verma.mobile.android.demoretrofitandroid.presenter.QuotesPresenter;
import com.verma.mobile.android.demoretrofitandroid.view.QuotesView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by verma on 28-12-2017.
 */

public class QuotesPresenterImp implements QuotesPresenter {

    public QuotesPresenterImp(QuotesView mQuotesView) {
        this.mQuotesView = mQuotesView;
    }

    public QuotesView mQuotesView;
    @Override
    public void cleanMe() {
        mQuotesView.cleanDone();
    }

    @Override
    public void callMe() {

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

                mQuotesView.onQuotesResponse(quotes);

            }

            @Override
            public void onFailure(Call<List<Quotes>>call, Throwable t) {
                mQuotesView.onQuotesFailure(t);

            }
        });

    }
}
