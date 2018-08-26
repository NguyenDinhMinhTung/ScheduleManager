package com.example.system.schedulemanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.system.schedulemanager.DTO.EvenDTO;
import com.example.system.schedulemanager.DTO.ObjectDTO;
import com.example.system.schedulemanager.DTO.TimeTableDTO;
import com.example.system.schedulemanager.Database.Database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeTableDAO {
    SQLiteDatabase database;

    public TimeTableDAO(Context context) {
        Database data = new Database(context);
        database = data.open();
    }

    public void addTimeTable(TimeTableDTO timeTableDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.TB_TIMETABLE_TITLE, timeTableDTO.getTitle());
        contentValues.put(Database.TB_TIMETABLE_START, timeTableDTO.getStart());
        contentValues.put(Database.TB_TIMETABLE_END, timeTableDTO.getEnd());

        database.insert(Database.TB_TIMETABLE, null, contentValues);
    }

    public List<TimeTableDTO> getListObject() {
        List<TimeTableDTO> list = new ArrayList<>();

        String querry = "SELECT * FROM " + Database.TB_TIMETABLE;

        Cursor cursor = database.rawQuery(querry, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(Database.TB_TIMETABLE_ID));

            String title = cursor.getString(cursor.getColumnIndex(Database.TB_TIMETABLE_TITLE));
            String start = cursor.getString(cursor.getColumnIndex(Database.TB_TIMETABLE_START));
            String end = cursor.getString(cursor.getColumnIndex(Database.TB_TIMETABLE_END));

            TimeTableDTO timeTableDTO=new TimeTableDTO(id, title,start,end);
            list.add(timeTableDTO);

            cursor.moveToNext();
        }

        return list;
    }

    public void delete(int id) {
        database.delete(Database.TB_TIMETABLE, "id=?", new String[]{Integer.toString(id)});
    }
}
