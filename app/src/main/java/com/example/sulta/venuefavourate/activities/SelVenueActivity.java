package com.example.sulta.venuefavourate.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sulta.venuefavourate.R;
import com.example.sulta.venuefavourate.models.VenuewEntity;

/**
 * Created by Razia on 12/18/2017.
 */
public class SelVenueActivity extends AppCompatActivity{
    ImageView imgVenuePic;
    TextView lblVenueName,lblAddrssInfo,lblContactInfo;
    RatingBar mRating;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sel_venue_more_info);

        imgVenuePic = (ImageView) findViewById(R.id.imgSelVenue);
        lblVenueName = (TextView) findViewById(R.id.lblVenueName);
        lblAddrssInfo = (TextView) findViewById(R.id.lblVenueAddrsInfo);
        lblContactInfo = (TextView) findViewById(R.id.lblContactInfo);
        mRating = (RatingBar) findViewById(R.id.ratingbar);

        Bundle bInfo = getIntent().getExtras();
        if(bInfo != null){
            if(bInfo.containsKey("VenuewInfo")){
                VenuewEntity myCurrvenue = (VenuewEntity) bInfo.getSerializable("VenuewInfo");
                if(!myCurrvenue.getPhotos().isEmpty() && myCurrvenue.getPhotos().get(0) != null){
                    String venuePic  = myCurrvenue.getPhotos().get(0).getUrl();
                    Glide.with(getApplicationContext()).load(venuePic).fitCenter().into(imgVenuePic);
                }
                lblVenueName.setText(myCurrvenue.getName());
                if(myCurrvenue.getLocation() != null ){
                    lblAddrssInfo.setText(myCurrvenue.getLocation().getAddress()+"\n"+myCurrvenue.getLocation().getCity()+","+myCurrvenue.getLocation().getState()+","+myCurrvenue.getLocation().getCountry()+"\n"+myCurrvenue.getLocation().getPostalCode());
                }
                mRating.setRating(myCurrvenue.getRating());


            }
        }

    }


}
