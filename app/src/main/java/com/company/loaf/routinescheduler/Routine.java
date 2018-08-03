package com.company.loaf.routinescheduler;

public class Routine {

    private String name;

    private int interval;
    //year|month|day
    private String savedDate;

    public Routine(String name, int interval, String savedDate){
        this.name = name;
        this.interval = interval;
        this.savedDate = savedDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(String savedDate) {
        this.savedDate = savedDate;
    }
}
