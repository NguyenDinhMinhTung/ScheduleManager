package com.example.system.schedulemanager.DTO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTableDTO {
    int id;
    String title, start ,end;

    public TimeTableDTO(int id, String title, String start, String end) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public TimeTableDTO(int id, String title, Date start, Date end){
        this.id=id;
        this.title=title;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        start.setYear(start.getYear()-1900);
        start.setMonth(start.getMonth()-1);
        end.setYear(end.getYear()-1900);
        end.setMonth(end.getMonth()-1);

        this.start=dateFormat.format(start);
        this.end=dateFormat.format(end);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
