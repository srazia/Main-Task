package com.example.sulta.venuefavourate.models;

import java.io.Serializable;

/**
 * Created by Razia on 12/18/2017.
 */
public class PhotoEntity  implements Serializable{
    //{"photoId":"500a0041e4b06a8f7c366a41","createdAt":1342832705,"url":"https://igx.4sqi.net/img/general/405x540/0AOYCVdPRp2SYP-uCZJq1KB98sxMoWM0rk9SWjc_egY.jpg"}
    private String photoId;
    private String createdAt;
    private String url;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
