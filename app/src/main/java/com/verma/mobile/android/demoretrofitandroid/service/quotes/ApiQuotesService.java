package com.verma.mobile.android.demoretrofitandroid.service.quotes;

import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static com.verma.mobile.android.demoretrofitandroid.service.quotes.ApiQuotesService.ApiQuotesClient.BASE_URL;


/**
 * Created by verma on 26-12-2017.
 */

public class ApiQuotesService {

    interface ApiQuotesClient {
        public String BASE_URL = "http://quotesondesign.com/wp-json/";
        @GET("/posts?")
        public Call<List<Quotes>> getQuotes();
    }

    private static ApiQuotesService apiQuotesService;
    private ApiQuotesService(){}
    public static ApiQuotesService getInstance(){
        if(apiQuotesService ==null)
            apiQuotesService = new ApiQuotesService();
            return apiQuotesService;
    }

    public ApiQuotesClient build(){
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        ApiQuotesClient apiQuotesClient =  retrofit.create(ApiQuotesClient.class);
        return apiQuotesClient;
    }


    public void getAllQuoets() {
        ApiQuotesClient apiQuotesClient = ApiQuotesService.getInstance().build();
        Call<List<Quotes>> call = apiQuotesClient.getQuotes();

        call.enqueue(new Callback<List<Quotes>>() {
            @Override
            public void onResponse(Call<List<Quotes>> call, Response<List<Quotes>> response) {
                List<Quotes> quotes = response.body();
            }

            @Override
            public void onFailure(Call<List<Quotes>>call, Throwable t) {
                Toast.makeText(null, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
