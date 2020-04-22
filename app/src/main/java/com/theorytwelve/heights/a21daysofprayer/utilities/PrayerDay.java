package com.theorytwelve.heights.a21daysofprayer.utilities;

public class PrayerDay {

    //private int dayNum;   // not currently used.
    private String dayTitle;
    private String dayFocus;
    private String dayVerse;
    private String dayDescription;
    private String dayPrayer;
    private String dayImageRef;

    public PrayerDay(String title, String focus, String verse, String descr, String prayer, String image){
        this.dayTitle = title;
        this.dayFocus = focus;
        this.dayVerse = verse;
        this.dayDescription = descr;
        this.dayPrayer = prayer;
        this.dayImageRef = image;
    }

    // Getters for PrayerDay object
    public String getDayTitle () { return dayTitle;}
    public String getDayFocus () {return dayFocus;}
    public String getDayVerse () {return dayVerse;}
    public String getDayDescription () {return dayDescription;}
    public String getDayPrayer () {return dayPrayer;}
    public String getDayImageRef () {return dayImageRef;}

    // Setters for PrayerDay object
    public void setDayTitle (String title) {this.dayTitle = title;}
    public void setDayFocus (String focus) {this.dayFocus = focus;}
    public void setDayVerse (String verse) {this.dayVerse = verse;}
    public void setDayDescription (String descr) {this.dayDescription = descr;}
    public void setDayPrayer (String prayer) {this.dayPrayer = prayer;}
    public void setDayImageRef (String image) {this.dayImageRef = image;}
}
