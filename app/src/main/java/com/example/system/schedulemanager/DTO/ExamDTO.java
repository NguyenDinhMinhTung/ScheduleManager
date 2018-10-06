package com.example.system.schedulemanager.DTO;

public class ExamDTO {
    int id, objectID, num;
    String note;

    public ExamDTO(int id, int objectID, int num, String note) {
        this.id = id;
        this.objectID = objectID;
        this.num = num;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
