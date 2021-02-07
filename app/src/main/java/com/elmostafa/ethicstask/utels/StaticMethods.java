package com.elmostafa.ethicstask.utels;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.elmostafa.ethicstask.R;
import com.google.android.material.snackbar.Snackbar;

public class StaticMethods {
    public static boolean isConnectingToInternet(Context _context) {
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }
    public static void ShowSnake(CoordinatorLayout _context, Context context, String message) {
        Snackbar snackbar = Snackbar
                .make(_context, message, Snackbar.LENGTH_LONG);
        int color;
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
        color = Color.WHITE;
        textView.setTextColor(color);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        snackbar.show();
    }
    public static void NOConnecting(CoordinatorLayout _context, Context context) {
        Snackbar snackbar = Snackbar
                .make(_context, context.getString(R.string.noconnection), Snackbar.LENGTH_LONG);
        int color;
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
        color = Color.RED;
        textView.setTextColor(color);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        snackbar.show();
    }
}
