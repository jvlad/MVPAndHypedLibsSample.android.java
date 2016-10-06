package net.derohimat.samplebasemvp.data.local.devicelocation;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;

import timber.log.Timber;

/**
 * Created by hp on 06/10/16.
 */

public class DeviceLocation {
    public static final int MIN_TIME_BEFORE_NEXT_REFRESH_MILLIS = 3000;
    public static final int MIN_DISTANCE_CHANGE_BEFORE_NEXT_REFRESH_METERS = 1;
    private static DeviceLocation instance;

    private DeviceLocation(Context context, LocationListener listener) {
        LocationManager locationManager = (LocationManager)
        context.getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    MIN_TIME_BEFORE_NEXT_REFRESH_MILLIS,
                    MIN_DISTANCE_CHANGE_BEFORE_NEXT_REFRESH_METERS,
                    listener);
        } catch (SecurityException e){
            Timber.e("Error: Location permisson not granted");
        }
    }

    public static void setSubscriber(Context context, LocationListener listener) {
        if (instance == null) {
            synchronized (DeviceLocation.class) {
                if (instance == null) {
                    instance = new DeviceLocation(context, listener);
                }
            }
        }
    }

}
