package com.example.system.schedulemanager.DTO;

import java.util.Date;
import java.util.List;

public class EvenByDayDTO {
    Date date;
    List<EvenDTO> list;

    public EvenByDayDTO(Date date, List<EvenDTO> list) {
        this.date = date;
        this.list = list;
    }

    public Date getDate() {
        return date;
    }

    public List<EvenDTO> getList() {
        return list;
    }
}
