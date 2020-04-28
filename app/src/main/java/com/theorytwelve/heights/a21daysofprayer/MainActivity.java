package com.theorytwelve.heights.a21daysofprayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theorytwelve.heights.a21daysofprayer.utilities.JsonUtils;
import com.theorytwelve.heights.a21daysofprayer.utilities.PrayerDay;
import com.theorytwelve.heights.a21daysofprayer.utilities.PrayerDayAdapter;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements PrayerDayAdapter.PrayerDayClickListener {

    private TextView tvVerseDisplay;
    private TextView tvDescriptionDisplay;
    private TextView tvPrayerDisplay;
    private PrayerDayAdapter mPrayerAdapter;
    private RecyclerView mPrayerDaysList;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrayerDaysList = (RecyclerView) findViewById(R.id.rv_day_cards);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, 0,false);
        mPrayerDaysList.setLayoutManager(layoutManager);
        mPrayerDaysList.setHasFixedSize(true);

        JsonUtils jsonUtils = new JsonUtils(this);
        PrayerDay[] prayerDays = jsonUtils.getPrayerData("prayerdays");
        mPrayerAdapter = new PrayerDayAdapter(this,prayerDays,this);

        mPrayerDaysList.setAdapter(mPrayerAdapter);
    }

    @Override
    public void onPrayerDayClick(PrayerDay prayerDay) {
//        if(mToast != null) {
//            mToast.cancel();
//        }
//        String toastText = "Verse: " + prayerDay.getDayVerse();
//        mToast = Toast.makeText(this,toastText,Toast.LENGTH_SHORT);
//        mToast.show();

        String pdTitle = prayerDay.getDayTitle();
        String pdVerse = prayerDay.getDayVerse();
        String pdFocus = prayerDay.getDayFocus();
        String pdDescr = prayerDay.getDayDescription();
        String pdImage = prayerDay.getDayImageRef();
        String pdPrayer = prayerDay.getDayPrayer();

        Intent intent = new Intent(MainActivity.this,PrayerDayDetailActivity.class);
        intent.putExtra("title",pdTitle)
                .putExtra("verse",pdVerse)
                .putExtra("focus",pdFocus)
                .putExtra("prayer",pdPrayer)
                .putExtra("image",pdImage)
                .putExtra("descr",pdDescr);
        startActivity(intent);
    }
}
