package com.example.medic3;

public class Pillitemclass {

    private String pillname;
    private String starttime;
    private String duration;
    private String notID;

    public Pillitemclass(String pillname, String starttime, String duration, String notID) {
        this.pillname = pillname;
        this.starttime = starttime;
        this.duration = duration;
        this.notID = notID;
    }

    public String getPillname() {
        return pillname;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getDuration() {
        return duration;
    }

    public String getNotID() {
        return notID;
    }
}
