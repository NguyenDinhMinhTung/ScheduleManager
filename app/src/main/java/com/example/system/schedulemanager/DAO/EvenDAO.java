package com.example.system.schedulemanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.system.schedulemanager.DTO.EvenDTO;
import com.example.system.schedulemanager.Database.Database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EvenDAO {
    SQLiteDatabase database;

    public EvenDAO(Context context) {
        Database database = new Database(context);
        database.open();
    }

    public void addEven(EvenDTO evenDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.TB_EVEN_OBJECTID, evenDTO.getObjectID());
        contentValues.put(Database.TB_EVEN_NAME, evenDTO.getName());

        contentValues.put(Database.TB_EVEN_STARTYEAR, evenDTO.getStartTime().getYear());
        contentValues.put(Database.TB_EVEN_STARTMONTH, evenDTO.getStartTime().getMonth());
        contentValues.put(Database.TB_EVEN_STARTDAY, evenDTO.getStartTime().getDay());
        contentValues.put(Database.TB_EVEN_STARTHOUR, evenDTO.getStartTime().getHours());
        contentValues.put(Database.TB_EVEN_STARTMIN, evenDTO.getStartTime().getMinutes());

        contentValues.put(Database.TB_EVEN_ENDYEAR, evenDTO.getEndTime().getYear());
        contentValues.put(Database.TB_EVEN_ENDMONTH, evenDTO.getEndTime().getMonth());
        contentValues.put(Database.TB_EVEN_ENDDAY, evenDTO.getEndTime().getDay());
        contentValues.put(Database.TB_EVEN_ENDHOUR, evenDTO.getEndTime().getHours());
        contentValues.put(Database.TB_EVEN_ENDMIN, evenDTO.getEndTime().getMinutes());

        contentValues.put(Database.TB_EVEN_NOTE, evenDTO.getNote());
        contentValues.put(Database.TB_EVEN_TYPE, evenDTO.getType());

        database.insert(Database.TB_EVEN, null, contentValues);
    }

    public List<EvenDTO> getListEven() {
        List<EvenDTO> list = new ArrayList<>();

        String querry = "SELECT * FROM " + Database.TB_EVEN;
        Cursor cursor = database.rawQuery(querry, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_ID));
            int objectID = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_OBJECTID));
            int type = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_TYPE));

            int startYear = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_STARTYEAR));
            int startMonth = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_STARTMONTH));
            int startDay = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_STARTDAY));
            int startHour = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_STARTHOUR));
            int startMin = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_STARTMIN));

            int endYear = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_ENDYEAR));
            int endMonth = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_ENDMONTH));
            int endDay = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_ENDDAY));
            int endHour = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_ENDHOUR));
            int endMin = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_ENDMIN));

            String name = cursor.getString(cursor.getColumnIndex(Database.TB_EVEN_NAME));

            String note = cursor.getString(cursor.getColumnIndex(Database.TB_EVEN_NOTE));

            Date start = new Date(startYear, startMonth, startDay, startHour, startMin);
            Date end = new Date(endYear, endMonth, endDay, endHour, endMin);

            EvenDTO evenDTO = new EvenDTO(type, objectID, name, note, start, end);
            evenDTO.setId(id);

            list.add(evenDTO);

            cursor.moveToNext();
        }

        return list;
    }

    public List<EvenDTO> getListEvenByDate(int year, int month, int day) {
        List<EvenDTO> list = new ArrayList<>();

        String querry = "SELECT * FROM " + Database.TB_EVEN + " WHERE " + Database.TB_EVEN_STARTYEAR + "=" + year
                + " AND " + Database.TB_EVEN_STARTMONTH + " = " + month + " AND " + Database.TB_EVEN_STARTDAY + "=" + day;

        Cursor cursor = database.rawQuery(querry, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_ID));
            int objectID = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_OBJECTID));
            int type = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_TYPE));

            int startYear = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_STARTYEAR));
            int startMonth = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_STARTMONTH));
            int startDay = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_STARTDAY));
            int startHour = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_STARTHOUR));
            int startMin = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_STARTMIN));

            int endYear = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_ENDYEAR));
            int endMonth = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_ENDMONTH));
            int endDay = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_ENDDAY));
            int endHour = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_ENDHOUR));
            int endMin = cursor.getInt(cursor.getColumnIndex(Database.TB_EVEN_ENDMIN));

            String name = cursor.getString(cursor.getColumnIndex(Database.TB_EVEN_NAME));

            String note = cursor.getString(cursor.getColumnIndex(Database.TB_EVEN_NOTE));

            Date start = new Date(startYear, startMonth, startDay, startHour, startMin);
            Date end = new Date(endYear, endMonth, endDay, endHour, endMin);

            EvenDTO evenDTO = new EvenDTO(type, objectID, name, note, start, end);
            evenDTO.setId(id);

            list.add(evenDTO);

            cursor.moveToNext();
        }

        return list;
    }
}
