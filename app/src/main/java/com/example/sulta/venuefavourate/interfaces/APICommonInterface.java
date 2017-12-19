package com.example.sulta.venuefavourate.interfaces;

import com.example.sulta.venuefavourate.models.VenuewEntity;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Razia on 12/18/2017.
 */

public interface APICommonInterface {

    @GET("/venue/")
    public void getVenuewInfo(Callback<VenuewEntity> callback);
}
