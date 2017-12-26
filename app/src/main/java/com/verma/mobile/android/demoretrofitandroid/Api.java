package com.verma.mobile.android.demoretrofitandroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by verma on 26-12-2017.
 */

public interface Api {
    public String BASE_URL = "http://quotesondesign.com/wp-json/";
    public int QUOTES_OFFSET = 2;

    @GET("posts?"+"&filter[posts_per_page]="+QUOTES_OFFSET +"&filter[orderby]=rand")
     Call <List<Quotes>> getQuotes();
}
