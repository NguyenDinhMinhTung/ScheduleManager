package com.example.system.schedulemanager;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Window;
import android.view.WindowManager;

import com.example.system.schedulemanager.DAO.TimeTableDAO;
import com.example.system.schedulemanager.DTO.TimeTableDTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Tools {
    public static int getIntDayOfWeek(Context context, Date date) {
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();

        return ((day + 2 * month + (3 * (month + 1)) / 5) + year + (year / 4)) % 7;

    }

    public static String getStringDayOfWeek(Context context, int dayofweek) {
        String result = "";

        Resources res = context.getResources();
        switch (dayofweek) {
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

    public static String getStringDayOfWeek(Context context, Date date) {
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

    public static String getJigen(int jigen) {
        switch (jigen) {
            case 1:
                return "1-2時限";

            case 2:
                return "3-4時限";

            case 3:
                return "5-6時限";

            case 4:
                return "7-8時限";

            case 5:
                return "9-10時限";

            default:
                return "";
        }
    }

    public static TimeTableDTO getAvalibeTimeTable(Context context, Date date) {
        TimeTableDAO timeTableDAO = new TimeTableDAO(context);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        String now = dateFormat.format(date);

        List<TimeTableDTO> list = timeTableDAO.getListTimeTable();

        for (TimeTableDTO timeTableDTO : list) {
            int compare1 = now.compareTo(timeTableDTO.getStart());
            int compare2 = now.compareTo(timeTableDTO.getEnd());

            if (compare1 >= 0 && compare2 <= 0) {
                return timeTableDTO;
            }
        }

        return null;
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

    public static Date getStartTimeFromJigen(Date date, int jigen) {
        int year=date.getYear();
        int month=date.getMonth();
        int day=date.getDate();

        switch (jigen) {
            case 1:
                return new Date(year, month, day, 8, 50);

            case 2:
                return new Date(year, month, day,10, 30);

            case 3:
                return new Date(year, month, day,12, 50);

            case 4:
                return new Date(year, month, day, 14, 30);

            case 5:
                return new Date(year, month, day, 16, 10);

            default:
                return null;
        }
    }

    public static Date getEndTimeFromJigen(Date date, int jigen) {
        int year=date.getYear();
        int month=date.getMonth();
        int day=date.getDate();

        switch (jigen) {
            case 1:
                return new Date(year, month, day,10, 20);

            case 2:
                return new Date(year, month, day,12, 00);

            case 3:
                return new Date(year, month, day,14, 20);

            case 4:
                return new Date(year, month, day, 16, 00);

            case 5:
                return new Date(year, month, day, 17, 40);

            default:
                return null;
        }
    }

    public static String getStringObjetEven(int pos) {
        switch (pos) {
            case 0:
                return "レポート";

            case 1:
                return "休講";

            case 2:
                return "補講";

            case 3:
                return "試験";

            case 4:
                return "その他";

            default:
                return "";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarColor(Activity activity, int colorID) {
        Window window = activity.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(activity.getResources().getColor(colorID));
    }
}
