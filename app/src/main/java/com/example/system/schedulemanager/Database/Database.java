package com.example.system.schedulemanager.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static final String TB_TIMETABLE = "TIMETABLE";
    public static final String TB_OBJECT = "OBJECT";
    public static final String TB_EXAM = "EXAM";
    public static final String TB_EVEN = "EVEN";

    public static final String TB_TIMETABLE_ID = "ID";
    public static final String TB_TIMETABLE_TITLE = "TITLE";
    public static final String TB_TIMETABLE_START = "STARTTIME";
    public static final String TB_TIMETABLE_END = "ENDTIME";

    public static final String TB_OBJECT_ID = "ID";
    public static final String TB_OBJECT_TIMETABLEID = "TIMETABLEID";
    public static final String TB_OBJECT_OBJECTNAME = "OBJECTNAME";
    public static final String TB_OBJECT_DAYOFWEEK = "DAYOFWEEK";
    public static final String TB_OBJECT_JIGEN = "JIGEN";
    public static final String TB_OBJECT_PLACE = "PLACE";
    public static final String TB_OBJECT_NUMOFUNITS = "NUMOFUNITS";
    public static final String TB_OBJECT_NUM = "NUM";
    public static final String TB_OBJECT_NOTE = "NOTE";

    public static final String TB_EXAM_ID = "ID";
    public static final String TB_EXAM_OBJECTID = "OBJECTID";
    public static final String TB_EXAM_NUM = "NUM";
    public static final String TB_EXAM_NOTE = "NOTE";

    /*
    Even Type:
        0: 普通
        1: 終日
        2: 科目（イベントでない）
        3: レポート
        4: 休講
        5: 補講
        6: 試験
        7: その他
     */
    public static final String TB_EVEN_ID = "ID";
    public static final String TB_EVEN_TYPE = "TYPE";
    public static final String TB_EVEN_COLOR = "COLOR";
    public static final String TB_EVEN_NAME = "NAME";
    public static final String TB_EVEN_OBJECTID = "OBJECTID";
    public static final String TB_EVEN_STARTYEAR = "STARTYEAR";
    public static final String TB_EVEN_STARTMONTH = "STARTMONTH";
    public static final String TB_EVEN_STARTDAY = "STARTDAY";
    public static final String TB_EVEN_STARTHOUR = "STARTHOUR";
    public static final String TB_EVEN_STARTMIN = "STARTMIN";
    public static final String TB_EVEN_ENDYEAR = "ENDYEAR";
    public static final String TB_EVEN_ENDMONTH = "ENDMONTH";
    public static final String TB_EVEN_ENDDAY = "ENDDAY";
    public static final String TB_EVEN_ENDHOUR = "ENDHOUR";
    public static final String TB_EVEN_ENDMIN = "ENDMIN";
    public static final String TB_EVEN_NOTE = "NOTE";


    public Database(Context context) {
        super(context, "ScheduleManager", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tbTIMETABLE = "CREATE TABLE " + TB_TIMETABLE + " ( " + TB_TIMETABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_TIMETABLE_TITLE + " TEXT, " + TB_TIMETABLE_START + " TEXT, " + TB_TIMETABLE_END + " TEXT)";

        String tbOBJECT = "CREATE TABLE " + TB_OBJECT + "(" + TB_OBJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_OBJECT_TIMETABLEID + " INTEGER, " + TB_OBJECT_DAYOFWEEK + " INTEGER, " + TB_OBJECT_JIGEN + " INTEGER, "
                + TB_OBJECT_OBJECTNAME + " TEXT, " + TB_OBJECT_PLACE + " TEXT, " + TB_OBJECT_NUM + " INTEGER, " + TB_OBJECT_NUMOFUNITS + " INTEGER, "
                + TB_OBJECT_NOTE + " TEXT)";

        String tbEXAM = "CREATE TABLE " + TB_EXAM + "(" + TB_EXAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_EXAM_OBJECTID + " INTEGER, " + TB_EXAM_NUM + " INTEGER, " + TB_EXAM_NOTE + " TEXT)";

        String tbEVEN = "CREATE TABLE " + TB_EVEN + "(" + TB_EVEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_EVEN_TYPE + " INTEGER," + TB_EVEN_COLOR + " INTEGER, " + TB_EVEN_NAME + " TEXT, " + TB_EVEN_OBJECTID + " INTEGER, "
                + TB_EVEN_STARTYEAR + " TEXT, " + TB_EVEN_STARTMONTH + " TEXT, " + TB_EVEN_STARTDAY + " TEXT, "
                + TB_EVEN_STARTHOUR + " TEXT, " + TB_EVEN_STARTMIN + " TEXT, " + TB_EVEN_ENDYEAR + " TEXT, "
                + TB_EVEN_ENDMONTH + " TEXT, " + TB_EVEN_ENDDAY + " TEXT, " + TB_EVEN_ENDHOUR + " TEXT, "
                + TB_EVEN_ENDMIN + " TEXT, " + TB_EVEN_NOTE + " TEXT)";

        sqLiteDatabase.execSQL(tbTIMETABLE);
        sqLiteDatabase.execSQL(tbOBJECT);
        sqLiteDatabase.execSQL(tbEXAM);
        sqLiteDatabase.execSQL(tbEVEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase open() {
        return this.getWritableDatabase();
    }
}
