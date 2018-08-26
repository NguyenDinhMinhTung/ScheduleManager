package com.example.system.schedulemanager.DTO;

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
        this.start=start.getYear()+"-"+start.getMonth()+"-"+start.getDate();
        this.end=end.getYear()+"-"+end.getMonth()+"-"+end.getDate();
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
