package com.theorytwelve.heights.a21daysofprayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class PrayerDayDetailActivity extends AppCompatActivity {

    private String pdTitle;
    private String pdVerse;
    private String pdFocus;
    private String pdDescr;
    private String pdImage;
    private String pdPrayer;

    private ImageView mImageDisplay;
    private TextView mTitleDisplay;
    private TextView mVerseDisplay;
    private TextView mFocusDisplay;
    private TextView mPrayerDisplay;
    private TextView mDescrDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_day);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mImageDisplay = (ImageView) findViewById(R.id.iv_image_prayer_day);
        mTitleDisplay = (TextView) findViewById(R.id.tv_title_prayer_day);
        mVerseDisplay = (TextView) findViewById(R.id.tv_verse_prayer_day);
        mFocusDisplay = (TextView) findViewById(R.id.tv_focus_prayer_day);
        mPrayerDisplay = (TextView) findViewById(R.id.tv_prayer_prayer_day);
        mDescrDisplay = (TextView) findViewById(R.id.tv_description_prayer_day);

        Intent intent = getIntent();
        getIntentData(intent);
        setDisplayData();
    }

    private void getIntentData(Intent intent) {
        if (intent.hasExtra("title")) { pdTitle = intent.getStringExtra("title"); }
        if (intent.hasExtra("verse")) { pdVerse = intent.getStringExtra("verse"); }
        if (intent.hasExtra("focus")) { pdFocus = intent.getStringExtra("focus"); }
        if (intent.hasExtra("prayer")) { pdPrayer = intent.getStringExtra("prayer"); }
        if (intent.hasExtra("image")) { pdImage = intent.getStringExtra("image"); }
        if (intent.hasExtra("descr")) { pdDescr = intent.getStringExtra("descr"); }
    }

    private void setDisplayData() {
        if (pdTitle != null) { mTitleDisplay.setText(pdTitle);}
        if (pdVerse != null) { mVerseDisplay.setText(getString(R.string.detail_verse_lead) + " " + pdVerse);}
        if (pdFocus != null) { mFocusDisplay.setText(getString(R.string.detail_focus_lead) + " " + pdFocus);}
        if (pdPrayer != null) { mPrayerDisplay.setText(getString(R.string.detail_prayer_lead) + " " + pdPrayer);}
        if (pdImage != null) { mImageDisplay.setImageResource(getResources().getIdentifier(pdImage,"drawable",this.getPackageName()));}
        if (pdDescr != null) { mDescrDisplay.setText(pdDescr);}
    }

}
