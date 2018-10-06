package com.example.system.schedulemanager.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.system.schedulemanager.DTO.ExamDTO;
import com.example.system.schedulemanager.Database.Database;

import java.util.ArrayList;
import java.util.List;

public class ExamDAO {

    SQLiteDatabase database;

    public ExamDAO(Context context) {
        Database data = new Database(context);
        database = data.open();
    }

    public List<ExamDTO> getExamByObejectID(int objectID) {
        String query = "SELECT * FROM " + Database.TB_EXAM + " WHERE " + Database.TB_EXAM_OBJECTID
                + "= " + objectID;

        List<ExamDTO> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(Database.TB_EXAM_ID));
            int num = cursor.getInt(cursor.getColumnIndex(Database.TB_EXAM_NUM));

            String note = cursor.getString(cursor.getColumnIndex(Database.TB_EXAM_NOTE));

            ExamDTO examDTO = new ExamDTO(id, objectID, num, note);

            list.add(examDTO);
            cursor.moveToNext();
        }
        return list;
    }

    public void delete(int objectID) {
        database.delete(Database.TB_EXAM, Database.TB_EXAM_OBJECTID + "=?", new String[]{Integer.toString(objectID)});
    }
}
