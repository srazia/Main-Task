package com.example.sulta.venuefavourate.interfaces;

/**
 * Created by Razia on 12/18/2017.
 */

        import android.content.Context;
        import android.util.Base64;

        import com.example.sulta.venuefavourate.activities.MainActivity;
        import com.example.sulta.venuefavourate.models.VenuewEntity;
        import com.google.gson.JsonObject;

        import retrofit.Callback;
        import retrofit.RequestInterceptor;
        import retrofit.RestAdapter;
        import retrofit.RetrofitError;
        import retrofit.client.Response;

public class APIRequestHandler {
    public static String url="https://movesync-qa.dcsg.com/dsglabs/mobile/api";
    RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(
            url).build();



    APICommonInterface mInterfaces = createService();
    private static final APIRequestHandler instance = new APIRequestHandler();

    public static APIRequestHandler getInstance() {
        return instance;
    }

    private APIRequestHandler() {

    }

    public static APICommonInterface createService() {
        // set endpoint url and use OkHTTP as HTTP client
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(url);


        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {

                request.addHeader("Accept", "application/json");

            }
        });

        RestAdapter adapter = builder.build();

        return adapter.create(APICommonInterface.class);
    }


    public void getMyVenuewInfo(final MainActivity mInfo) {

        mInterfaces.getVenuewInfo(new Callback<VenuewEntity>() {
            @Override
            public void success(VenuewEntity venuewEntity, Response response) {
                mInfo.onRequestSuccess(venuewEntity);
            }

            @Override
            public void failure(RetrofitError error) {
                    mInfo.onRequestFailure(error);
            }
        });
    }
}

