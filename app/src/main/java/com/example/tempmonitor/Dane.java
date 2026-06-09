package com.example.tempmonitor;

public class Dane {
    private Long id;
    private String data;    // LocalDate ze Springa przyjdzie jako String
    private String godzina; // LocalTime ze Springa przyjdzie jako String
    private int temperatura;

    // Gettery
    public Long getId() { return id; }
    public String getData() { return data; }
    public String getGodzina() { return godzina; }
    public int getTemperatura() { return temperatura; }
}