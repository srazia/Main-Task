package com.example.sulta.venuefavourate.Utils;

import android.location.Location;
import android.util.Log;

import java.text.DecimalFormat;

/**
 * Created by Razia on 12/18/2017.
 */

public class Utils {
    /*** Caluculate the Distance between Two Points ***/
    public static float GetDistancee(double d, double e, double f, double g) {

        Location locationA = new Location("point A");

        locationA.setLatitude(d);
        locationA.setLongitude(e);

        Location locationB = new Location("point B");

        locationB.setLatitude(f);
        locationB.setLongitude(g);

        // float distance = locationA.distanceTo(locationB);

        Log.e("Location distance",
                "distance :" + locationA.distanceTo(locationB));

        return locationA.distanceTo(locationB);
        // return locationA.distanceTo(locationB)/1000;

    }
    public static double roundDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.####");
        return Double.valueOf(twoDForm.format(d));
    }
    public static float GetDistancee1(double d, double e, double f, double g) {

        Location locationA = new Location("point A");

        locationA.setLatitude(roundDecimals(d));
        locationA.setLongitude(roundDecimals(e));

        Location locationB = new Location("point B");

        locationB.setLatitude(g);
        locationB.setLongitude(f);

        Log.e("latlong", "lata :" + roundDecimals(d) + ", " + roundDecimals(e)
                + " lata :" + f + ", " + g);

        // float distance = locationA.distanceTo(locationB);

        return locationA.distanceTo(locationB);

        // return locationA.distanceTo(locationB)/1000;

    }
}
