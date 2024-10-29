package com.lhw.lemon.utils;

import android.content.Context;
import android.widget.Toast;

import com.lhw.lemon.config.Config;

public class CommonUtils {
    public static String videoTypeConverter(Integer videoType) {
        switch (videoType) {
            case 1 :
                return Config.MOVIE;
            case 2 :
                return Config.TELEPLAY;
        }
        return null;
    }

    public static void topToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(80,0,0);
        toast.show();
    }
}
