package godinner.lab.com.godinner.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import godinner.lab.com.godinner.R;

public class LoadingDialog {

    public static AlertDialog showLoadingDialog(LayoutInflater inflater, Context context) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);

        @SuppressLint("InflateParams")
        final View mView = inflater.inflate(R.layout.loading, null);
        mBuilder.setView(mView);

        return mBuilder.create();
    }
}
