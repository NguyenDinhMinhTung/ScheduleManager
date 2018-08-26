package com.example.system.schedulemanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.system.schedulemanager.DTO.EvenDTO;
import com.example.system.schedulemanager.DTO.ObjectDTO;
import com.example.system.schedulemanager.Database.Database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObjectDAO {
    SQLiteDatabase database;

    public ObjectDAO(Context context) {
        Database data = new Database(context);
        database = data.open();
    }

    public void addObject(ObjectDTO objectDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.TB_OBJECT_ID, objectDTO.getId());
        contentValues.put(Database.TB_OBJECT_DAYOFWEEK, objectDTO.getDayOfWeek());
        contentValues.put(Database.TB_OBJECT_JIGEN, objectDTO.getJigen());
        contentValues.put(Database.TB_OBJECT_OBJECTNAME, objectDTO.getObjectName());
        contentValues.put(Database.TB_OBJECT_NOTE, objectDTO.getNote());
        contentValues.put(Database.TB_OBJECT_NUM, objectDTO.getNum());
        contentValues.put(Database.TB_OBJECT_PLACE, objectDTO.getPlace());
        contentValues.put(Database.TB_OBJECT_TIMETABLEID, objectDTO.getTimetableid());

        database.insert(Database.TB_OBJECT, null, contentValues);
    }

    public List<ObjectDTO> getListObject(int timetableid, int dayofweek) {
        List<ObjectDTO> list = new ArrayList<>();

        String querry = "SELECT * FROM " + Database.TB_OBJECT + " WHERE " + Database.TB_OBJECT_TIMETABLEID
                + "= " + timetableid + " AND " + Database.TB_OBJECT_DAYOFWEEK + "= " + dayofweek + " ORDER BY " + Database.TB_OBJECT_JIGEN;

        Cursor cursor = database.rawQuery(querry, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(Database.TB_OBJECT_ID));
            int jigen = cursor.getInt(cursor.getColumnIndex(Database.TB_OBJECT_JIGEN));
            int num = cursor.getInt(cursor.getColumnIndex(Database.TB_OBJECT_NUM));

            String place = cursor.getString(cursor.getColumnIndex(Database.TB_OBJECT_NOTE));
            String note = cursor.getString(cursor.getColumnIndex(Database.TB_OBJECT_NOTE));
            String name = cursor.getString(cursor.getColumnIndex(Database.TB_OBJECT_OBJECTNAME));

            ObjectDTO objectDTO = new ObjectDTO(id, timetableid, dayofweek, jigen, num, name, place, note);
            list.add(objectDTO);

            cursor.moveToNext();
        }

        return list;
    }

    public void delete(int id) {
        database.delete(Database.TB_OBJECT, "id=?", new String[]{Integer.toString(id)});
    }
}
