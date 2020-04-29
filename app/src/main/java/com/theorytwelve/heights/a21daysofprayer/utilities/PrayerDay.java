package com.theorytwelve.heights.a21daysofprayer.utilities;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PrayerDay {

    //private int dayNum;   // not currently used.
    private String dayTitle;
    private String dayFocus;
    private String dayVerse;
    private String dayDescription;
    private String dayPrayer;
    private String dayImageRef;
    private String dayDate;

    public PrayerDay(String title, String focus, String verse, String descr, String prayer, String image, String date){
        this.dayTitle = title;
        this.dayFocus = focus;
        this.dayVerse = verse;
        this.dayDescription = descr;
        this.dayPrayer = prayer;
        this.dayImageRef = image;
        this.dayDate = date;
    }

    // Getters for PrayerDay object
    public String getDayTitle() { return dayTitle;}
    public String getDayFocus() {return dayFocus;}
    public String getDayVerse() {return dayVerse;}
    public String getDayDescription() {return dayDescription;}
    public String getDayPrayer() {return dayPrayer;}
    public String getDayImageRef() {return dayImageRef;}
    public String getDayDate() { return dayDate; }
    public String getFormattedDate() { return formatDayDate(); }
    public Calendar getCalendarDate() { return dayDateToCal(); }

    // Setters for PrayerDay object
    public void setDayTitle (String title) {this.dayTitle = title;}
    public void setDayFocus (String focus) {this.dayFocus = focus;}
    public void setDayVerse (String verse) {this.dayVerse = verse;}
    public void setDayDescription (String descr) {this.dayDescription = descr;}
    public void setDayPrayer (String prayer) {this.dayPrayer = prayer;}
    public void setDayImageRef (String image) {this.dayImageRef = image;}
    public void setDayDate (String date) {this.dayDate = date; }

    private String formatDayDate() {
        Calendar calendar = dayDateToCal();
        String month = mmToMonth(calendar.get(Calendar.MONTH));
//        String[] dateArray = dayDate.split("/");
//        String month = mmToMonth(dateArray[0]);
//        String day = deleteLeadingZero(dateArray[1]);
        return month + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR);
    }

    private Calendar dayDateToCal(){
        Date date = null;
        Calendar calendar = Calendar.getInstance();

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
            date = dateFormat.parse(dayDate);
            calendar.setTime(date);
        } catch (java.text.ParseException e) {
            Log.d("exception", "onPrayerDayClick: " + e);}
        
        return calendar;
    }

    private String mmToMonth(int month){
        switch (month){
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
            default:
                return "Whoops";
        }
    }

//    private String deleteLeadingZero(String day) {
//        if (day.startsWith("0")) {
//            return day.substring(1);
//        }
//        return day;
//    }
}
