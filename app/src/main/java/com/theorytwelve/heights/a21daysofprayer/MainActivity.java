package com.theorytwelve.heights.a21daysofprayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.theorytwelve.heights.a21daysofprayer.utilities.JsonUtils;
import com.theorytwelve.heights.a21daysofprayer.utilities.PrayerDay;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public TextView tvJsonDisplay;
    public ImageView ivImageDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJsonDisplay = (TextView) findViewById(R.id.tv_show_json);
        ivImageDisplay = (ImageView) findViewById(R.id.iv_show_image);

        displayPrayerData(3);
    }

    public void displayPrayerData(int day){
        JsonUtils jsonUtils = new JsonUtils(this);
        PrayerDay[] prayerDays = jsonUtils.getPrayerData("prayerdays");

        // this will all change once I implement the rest of the app, with recycler view and other build outs.
        // below only really used right now to make sure JsonUtils is parsing correctly and I can display data from file.
        if(prayerDays == null){
            tvJsonDisplay.setText("prayerDays: Null Object");
        } else {
            PrayerDay dayOne = prayerDays[day-1];
            if (prayerDays.length < day) {
                tvJsonDisplay.setText("Day not available");
            } else {
                tvJsonDisplay.setText("Title: " + dayOne.getDayTitle());

                tvJsonDisplay.append("\nFocus: " + dayOne.getDayFocus());
                tvJsonDisplay.append("\nVerse: " + dayOne.getDayVerse());
                tvJsonDisplay.append("\nDescription: " + dayOne.getDayDescription());
                tvJsonDisplay.append("\n\nPrayer:" + dayOne.getDayPrayer());

                ivImageDisplay.setImageResource(getResources().getIdentifier(dayOne.getDayImageRef(), "drawable", getPackageName()));
            }
        }
    }

}
