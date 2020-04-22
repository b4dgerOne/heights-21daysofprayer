package com.theorytwelve.heights.a21daysofprayer.utilities;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.theorytwelve.heights.a21daysofprayer.R;
import com.theorytwelve.heights.a21daysofprayer.utilities.PrayerDay;

public class JsonUtils {
    private static final String TAG = JsonUtils.class.getSimpleName();
    private Context mContext;
    //private int dayNum;

    public JsonUtils(Context context) {mContext = context;}     // this is to access the "res" folder

    public PrayerDay[] getPrayerData(String jsonFileName){
        PrayerDay[] prayerDays = null;

        try {
            String jsonData = readJsonFile(jsonFileName);
            JSONArray jsonArray = new JSONArray(jsonData);
            String[] fieldsArray = {"title","focus","verse","description","prayer","image"};

            prayerDays = new PrayerDay[jsonArray.length()];

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jObject = jsonArray.getJSONObject(i);
                Log.d(TAG, "getDayArrayData: " + jsonArray.length());

                //todone update these to be set with new PrayerDay objects
                PrayerDay prayerDay = new PrayerDay(
                        jObject.getString(fieldsArray[0]), // title
                        jObject.getString(fieldsArray[1]), // focus
                        jObject.getString(fieldsArray[2]), // verse
                        jObject.getString(fieldsArray[3]), // descr
                        jObject.getString(fieldsArray[4]), // prayer
                        "a21_dop_" + Integer.toString(i+1)); //image

                prayerDays[i] = prayerDay;
            }
            return prayerDays;

        } catch (JSONException e) {
            Log.d(TAG, "getDayArray: " + e);
            return prayerDays;
        }
    }

    private String readJsonFile(String jsonFileName) {
        int resourceID = mContext.getResources().getIdentifier(jsonFileName,"raw",mContext.getPackageName());
        InputStream inputStream = mContext.getResources().openRawResource(resourceID);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            Log.d(TAG, "readFile: " + e);
        }
        return outputStream.toString();
    }
}
