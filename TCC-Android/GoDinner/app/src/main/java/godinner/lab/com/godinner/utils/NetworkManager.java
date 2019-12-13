package godinner.lab.com.godinner.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import godinner.lab.com.godinner.R;

public class NetworkManager {

    public static boolean isNetworkAvailable(Object mService) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mService;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String typeConnection(Object mService) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) mService;

        if (connectivityManager != null) {
            Network network = connectivityManager.getActiveNetwork();

            if (network != null) {
                NetworkCapabilities nc = connectivityManager.getNetworkCapabilities(network);

                if (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return "3G";
                } else if (nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return "WIFI";
                }
            }
        }
        return null;
    }

    public static Snackbar getSnackBarNetwork(final Context context, View view){
        Snackbar mSnackbar = Snackbar.make(view, "Sem conexão com a internet.", Snackbar.LENGTH_INDEFINITE);
        mSnackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        mSnackbar.setAction("Conectar", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iWifiSettings = new Intent(Settings.ACTION_WIFI_SETTINGS);
                ((Activity) context).startActivity(iWifiSettings);
            }
        });

        return mSnackbar;
    }
}
