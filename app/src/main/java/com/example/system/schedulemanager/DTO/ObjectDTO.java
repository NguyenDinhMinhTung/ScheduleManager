package com.example.system.schedulemanager.DTO;

import java.io.Serializable;

public class ObjectDTO implements Serializable {
    int id, timetableid, num, dayOfWeek, jigen, numOfUnits;
    String objectName, Place, note;

    public ObjectDTO(int id, int timetableid, int dayOfWeek, int jigen, int num, int numOfUnits, String objectName, String place, String note) {
        this.id = id;
        this.timetableid = timetableid;
        this.num = num;
        this.objectName = objectName;
        Place = place;
        this.note = note;
        this.dayOfWeek = dayOfWeek;
        this.jigen = jigen;
        this.numOfUnits = numOfUnits;
    }

    public int getNumOfUnits() {
        return numOfUnits;
    }

    public void setNumOfUnits(int numOfUnits) {
        this.numOfUnits = numOfUnits;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getJigen() {
        return jigen;
    }

    public void setJigen(int jigen) {
        this.jigen = jigen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimetableid() {
        return timetableid;
    }

    public void setTimetableid(int timetableid) {
        this.timetableid = timetableid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
