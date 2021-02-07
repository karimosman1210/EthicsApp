package com.elmostafa.ethicstask;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isTextEmpty(String text) {
        return text.equals("");
    }


    public static boolean validPasswordLength(String password) {
        return !(password.length() < 7 || password.length() > 50);
    }


    public static boolean isValid(final String password) {
        final String MIN_CHAR = ".{" + 7 + ",}";  //.{8,} at least 8 characters
        final String PASSWORD_PATTERN =
                MIN_CHAR;
       final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidMail(final String mail) {
        final String REGEX= "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        final String MAIL_PATTERN =
                REGEX;
        final Pattern pattern = Pattern.compile(MAIL_PATTERN);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

}
