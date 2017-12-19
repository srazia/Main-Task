package com.example.sulta.venuefavourate.models;

import java.io.Serializable;

/**
 * Created by Razia on 12/18/2017.
 */

public class ContactsEntity implements Serializable {
    private String phone;
    private String twitter;
    private String facebook;
    private String facebookName;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }
}
