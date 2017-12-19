package com.example.sulta.venuefavourate.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Razia on 12/18/2017.
 */

public class VenuewEntity implements Serializable {
    private ArrayList<VenuewEntity> venues;


    private String id;
    private String name;
    private String verified;
    private String url;
    private String ratingColor;
    private String ratingSignals;

    private float rating;
    private String storeId;
    private ArrayList<ContactsEntity> contacts;
    private LocationEntity location;
    private ArrayList<PhotoEntity> photos;
    private double distance;
    private boolean isFavourate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public String getRatingSignals() {
        return ratingSignals;
    }

    public void setRatingSignals(String ratingSignals) {
        this.ratingSignals = ratingSignals;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public ArrayList<VenuewEntity> getVenues() {
        return venues;
    }

    public void setVenues(ArrayList<VenuewEntity> venues) {
        this.venues = venues;
    }

    public ArrayList<ContactsEntity> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ContactsEntity> contacts) {
        this.contacts = contacts;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public ArrayList<PhotoEntity> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<PhotoEntity> photos) {
        this.photos = photos;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isFavourate() {
        return isFavourate;
    }

    public void setFavourate(boolean favourate) {
        isFavourate = favourate;
    }
}
