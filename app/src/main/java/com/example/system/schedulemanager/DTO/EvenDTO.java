package com.example.system.schedulemanager.DTO;

import java.io.Serializable;
import java.util.Date;

public class EvenDTO implements Serializable {
    int id, type, objectID, color;
    String name;
    String note;
    Date startTime, endTime;

    public EvenDTO(int type, int objectID, int color, String name, String note, Date startTime, Date endTime) {
        this.type = type;
        this.objectID = objectID;
        this.name = name;
        this.color = color;
        this.note = note;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEnd(Date endTime) {
        this.endTime = endTime;
    }
}
