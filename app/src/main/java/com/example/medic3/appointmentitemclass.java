package com.example.medic3;

public class appointmentitemclass {

    private String region;
    private String center;
    private String doctor;
    private String date;
    private String time;

    public appointmentitemclass(String region, String center, String doctor, String date, String time) {
        this.region = region;
        this.center = center;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    public String getRegion() {
        return region;
    }

    public String getCenter() {
        return center;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
