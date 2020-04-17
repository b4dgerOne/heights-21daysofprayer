package com.theorytwelve.heights.a21daysofprayer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.theorytwelve.heights.a21daysofprayer.utilities.JsonUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView tvJsonDisplay;
    ImageView ivImageDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJsonDisplay = (TextView) findViewById(R.id.tv_show_json);
        ivImageDisplay = (ImageView) findViewById(R.id.iv_show_image);
        InputStream inputStream = getResources().openRawResource(R.raw.prayerdays);
        JsonUtils jsonUtils = new JsonUtils();
        String [][] dayDataArray = jsonUtils.getDayArrayData(inputStream);
        if(dayDataArray == null){
            tvJsonDisplay.setText("Data Array Null");
        } else {
            String[] dayText = getDay(dayDataArray,6);
            tvJsonDisplay.setText(dayText[0]);
            for(int i = 1; i < dayText.length-1; i++) {
                tvJsonDisplay.append("\n" + dayText[i]);
            }
            ivImageDisplay.setImageResource(getResources().getIdentifier(dayText[dayText.length-1],"drawable",getPackageName()));
        }
    }

    private String[] getDay(String[][] dayArray, int dayNum) {
        String[] result = new String[dayArray[dayNum-1].length];

        for(int i = 0; i < result.length; i++){
            result[i] = dayArray[dayNum-1][i];
        }

        return result;
    }

}
