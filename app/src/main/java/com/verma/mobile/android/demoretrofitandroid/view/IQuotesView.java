package com.verma.mobile.android.demoretrofitandroid.view;

import com.verma.mobile.android.demoretrofitandroid.service.quotes.Quotes;

import java.util.List;

/**
 * Created by verma on 28-12-2017.
 */

public interface IQuotesView {
    public void cleanDone();
    public void onQuotesResponse(List<Quotes> quotes );
    public void onQuotesFailure(Throwable throwable);
}
