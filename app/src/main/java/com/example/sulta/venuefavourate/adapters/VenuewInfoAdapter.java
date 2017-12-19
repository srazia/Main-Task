package com.example.sulta.venuefavourate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sulta.venuefavourate.R;
import com.example.sulta.venuefavourate.interfaces.ItemClik;
import com.example.sulta.venuefavourate.models.VenuewEntity;

import java.util.ArrayList;


/**
 * Created by Razia on 12/18/2017.
 */

public class VenuewInfoAdapter extends RecyclerView.Adapter<VenuewInfoAdapter.VenuewHolder> {
    Context mCxt;
    ArrayList<VenuewEntity> venuewDetails;
    ItemClik selVenue;
    public int sel_item_pos=-1;
    public VenuewInfoAdapter(Context applicationContext, int custom_venuew_info, ArrayList<VenuewEntity> allVenuewInfo,ItemClik veuewListener) {

        mCxt = applicationContext;
        venuewDetails = allVenuewInfo;
        selVenue = veuewListener;
    }

    @Override
    public VenuewInfoAdapter.VenuewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View mView = LayoutInflater.from(mCxt).inflate(R.layout.custom_venuew_info,parent,false);
        return new VenuewHolder(mView);
    }

    @Override
    public void onBindViewHolder(VenuewInfoAdapter.VenuewHolder holder, final int position) {
        VenuewEntity currVenue = venuewDetails.get(position);
        if(currVenue != null){

            if(currVenue.getLocation() != null)
                holder.lblAddress.setText(currVenue.getLocation().getAddress());
            /*if(currVenue.getDistance()>0){
                Log.d("Distance",""+currVenue.getDistance());
            }*/
            holder.lblName.setText(currVenue.getName()+" "+currVenue.getDistance());
            if(currVenue.isFavourate()){
                holder.imgFav.setImageResource(R.mipmap.heart_sel);
            }else
                holder.imgFav.setImageResource(R.mipmap.fav);
            if(!currVenue.getPhotos().isEmpty()){
                String currVenuImage =  currVenue.getPhotos().get(0).getUrl();
                if(currVenuImage !=null && !currVenuImage.isEmpty())
                Glide.with(mCxt).load(currVenuImage).fitCenter().into(holder.imgVenuewImage);
            }
            holder.rlTotView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selVenue.onItemClick(v,position);
                }
            });
            holder.imgFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sel_item_pos != -1){
                        venuewDetails.get(sel_item_pos).setFavourate(false);
                    }
                    sel_item_pos =position;
                    venuewDetails.get(sel_item_pos).setFavourate(true);
                    selVenue.onItemClick(v,position);

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return venuewDetails.size();
    }
    public class VenuewHolder extends RecyclerView.ViewHolder{

        TextView lblName,lblAddress;
        ImageView imgVenuewImage,imgFav;
        RelativeLayout rlTotView;


        public VenuewHolder(View itemView) {
            super(itemView);
            lblName = (TextView) itemView.findViewById(R.id.lblName);
            lblAddress = (TextView) itemView.findViewById(R.id.lblAddress);
            imgVenuewImage = (ImageView) itemView.findViewById(R.id.imgVenuewPic);
            imgFav = (ImageView) itemView.findViewById(R.id.imgFavuorate);
            rlTotView = (RelativeLayout) itemView.findViewById(R.id.rlTotView);
        }
    }
}
