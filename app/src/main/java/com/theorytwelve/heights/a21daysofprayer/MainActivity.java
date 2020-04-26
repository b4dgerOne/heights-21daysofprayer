package com.theorytwelve.heights.a21daysofprayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theorytwelve.heights.a21daysofprayer.utilities.JsonUtils;
import com.theorytwelve.heights.a21daysofprayer.utilities.PrayerDay;
import com.theorytwelve.heights.a21daysofprayer.utilities.PrayerDayAdapter;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements PrayerDayAdapter.PrayerDayClickListener {

    private TextView tvJsonDisplay;
    private ImageView ivImageDisplay;
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

//        tvJsonDisplay = (TextView) findViewById(R.id.tv_show_json);
//        ivImageDisplay = (ImageView) findViewById(R.id.iv_show_image);
//        displayPrayerData(3);
    }

    public void displayPrayerData(int day){
        JsonUtils jsonUtils = new JsonUtils(this);
        PrayerDay[] prayerDays = jsonUtils.getPrayerData("prayerdays");

        // this will all change once I implement the rest of the app, with recycler view and other build outs.
        // below only really used right now to make sure JsonUtils is parsing correctly and I can display data from file.
//        if(prayerDays == null){
//            tvJsonDisplay.setText("prayerDays: Null Object");
//        } else {
//            PrayerDay dayOne = prayerDays[day-1];
//            if (prayerDays.length < day) {
//                tvJsonDisplay.setText("Day not available");
//            } else {
//                tvJsonDisplay.setText("Title: " + dayOne.getDayTitle());
//
//                tvJsonDisplay.append("\nFocus: " + dayOne.getDayFocus());
//                tvJsonDisplay.append("\nVerse: " + dayOne.getDayVerse());
//                tvJsonDisplay.append("\nDescription: " + dayOne.getDayDescription());
//                tvJsonDisplay.append("\n\nPrayer:" + dayOne.getDayPrayer());
//
//                ivImageDisplay.setImageResource(getResources().getIdentifier(dayOne.getDayImageRef(), "drawable", getPackageName()));
//            }
//        }
    }

    @Override
    public void onPrayerDayClick(PrayerDay prayerDay) {
        if(mToast != null) {
            mToast.cancel();
        }

        String toastText = "Verse: " + prayerDay.getDayVerse();
        mToast = Toast.makeText(this,toastText,Toast.LENGTH_LONG);
        mToast.show();
    }
}
