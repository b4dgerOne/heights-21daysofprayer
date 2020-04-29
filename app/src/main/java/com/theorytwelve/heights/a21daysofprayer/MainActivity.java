package com.theorytwelve.heights.a21daysofprayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import com.theorytwelve.heights.a21daysofprayer.utilities.JsonUtils;
import com.theorytwelve.heights.a21daysofprayer.utilities.PrayerDay;
import com.theorytwelve.heights.a21daysofprayer.utilities.PrayerDayAdapter;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements PrayerDayAdapter.PrayerDayClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private PrayerDayAdapter mPrayerAdapter;
    private RecyclerView mPrayerDaysList;
    private TextView mTimeDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the data from JSON file
        JsonUtils jsonUtils = new JsonUtils(this);
        PrayerDay[] prayerDays = jsonUtils.getPrayerData("prayerdays");

        // set up the RecyclerView
        mPrayerDaysList = (RecyclerView) findViewById(R.id.rv_day_cards);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        layoutManager.scrollToPosition(getDayStartingPosition(prayerDays));
        mPrayerDaysList.setLayoutManager(layoutManager);
        mPrayerDaysList.setHasFixedSize(true);

        mPrayerAdapter = new PrayerDayAdapter(this,prayerDays,this);

        mPrayerDaysList.setAdapter(mPrayerAdapter);
    }

    @Override
    public void onPrayerDayClick(PrayerDay prayerDay) {

        Intent intent = new Intent(MainActivity.this,PrayerDayDetailActivity.class);
        intent.putExtra("title",prayerDay.getDayTitle())
                .putExtra("verse",prayerDay.getDayVerse())
                .putExtra("focus",prayerDay.getDayFocus())
                .putExtra("prayer",prayerDay.getDayPrayer())
                .putExtra("image",prayerDay.getDayImageRef())
                .putExtra("descr",prayerDay.getDayDescription())
                .putExtra("date",prayerDay.getFormattedDate());
        startActivity(intent);
    }

    public int getDayStartingPosition(PrayerDay[] prayerDays) {
        Calendar rightNow = Calendar.getInstance();
        int startDay = 99;

        FindTomorrow:
        for(int i = 0; i < prayerDays.length; i++) {

            Calendar findTomorrow = prayerDays[i].getCalendarDate();

            if(findTomorrow.after(rightNow)){
                startDay = i;
                break FindTomorrow;
            }
        }

        if(startDay == 99){
            startDay = 1;
        }

        return startDay-1;
    }
}
