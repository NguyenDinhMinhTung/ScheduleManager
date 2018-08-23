package com.example.system.schedulemanager;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.Date;

public class Tools {
    public static String dayOfWeek(Context context, Date date) {
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();
        String result = "";

        int n = ((day + 2 * month + (3 * (month + 1)) / 5) + year + (year / 4)) % 7;

        Resources res = context.getResources();
        switch (n) {
            case 0:
                result = res.getString(R.string.sunday);
                break;

            case 1:
                result = res.getString(R.string.monday);
                break;

            case 2:
                result = res.getString(R.string.tuesday);
                break;

            case 3:
                result = res.getString(R.string.wednesday);
                break;

            case 4:
                result = res.getString(R.string.thursday);
                break;

            case 5:
                result = res.getString(R.string.friday);
                break;

            case 6:
                result = res.getString(R.string.saturday);
                break;
        }

        return result;
    }

    public static int getBackground(Date date) {
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();

        int n = ((day + 2 * month + (3 * (month + 1)) / 5) + year + (year / 4)) % 7;

        switch (n) {
            case 0:
                return R.drawable.day_of_week_red;

            case 6:
                return R.drawable.day_of_week_blue;

            default:
                return R.drawable.day_of_week;
        }
    }

    public static String shortDayOfWeek(Context context, Date date) {
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();
        String result = "";

        int n = ((day + 2 * month + (3 * (month + 1)) / 5) + year + (year / 4)) % 7;

        Resources res = context.getResources();
        switch (n) {
            case 0:
                result = res.getString(R.string.sun);
                break;

            case 1:
                result = res.getString(R.string.mon);
                break;

            case 2:
                result = res.getString(R.string.tue);
                break;

            case 3:
                result = res.getString(R.string.wed);
                break;

            case 4:
                result = res.getString(R.string.thu);
                break;

            case 5:
                result = res.getString(R.string.fri);
                break;

            case 6:
                result = res.getString(R.string.sat);
                break;
        }

        return result;
    }
}
