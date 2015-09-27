package it.jaschke.alexandria;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by santosh on 9/27/15.
 * Utility class for reusable utility methods
 */
public class Utility {
    public static boolean isConnectedToInternet(Context applicationContext) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
