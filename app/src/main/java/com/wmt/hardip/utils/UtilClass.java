package com.wmt.hardip.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.wmt.hardip.R;

public class UtilClass {

    static KProgressHUD progressDialog;

    public static void loadImage(Context context, String filepath, int placeHolderDrawable, ImageView imageView) {
        Glide.with(context).load(filepath).apply(new RequestOptions().placeholder(placeHolderDrawable).error(placeHolderDrawable)).into(imageView);
    }

    public static void showProgress(Context context) {
        showProgress(context, context.getString(R.string.please_wait));
    }

    public static void showProgress(Context context, String message) {
        try {
            if (!((Activity) context).isFinishing()) {
                progressDialog = KProgressHUD.create(context)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel(message)
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f);

                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideProgress() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidEmail(CharSequence text) {
        return text != null && text.toString().trim().length() != 0 && android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches();
    }

    public static boolean isOnline(Context context) {

        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } else {
            return false;
        }
    }
}
