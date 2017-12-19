package com.example.sulta.venuefavourate.interfaces;

import retrofit.RetrofitError;

/**
 * Created by Razia on 12/18/2017.
 */
public interface ComonInterface {
    public void onRequestSuccess(Object responseObj);
    public void onRequestFailure(RetrofitError errorCode);
}
