package com.theorytwelve.heights.a21daysofprayer.utilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {
    private static final String TAG = JsonUtils.class.getSimpleName();
    private int dayNum;
    private String dayTitle;
    private String dayFocus;
    private String dayVerse;
    private String dayDescription;
    private String dayPrayer;
    private String dayImageRef;

    public String[][] getDayArrayData(InputStream inputStream){
        String jsonData = readJsonFile(inputStream);

        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            String[] fieldsArray = {"title","focus","verse","description","prayer","image"};
            String[][] dataArray = new String[jsonArray.length()][fieldsArray.length];

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jObject = jsonArray.getJSONObject(i);
                Log.d(TAG, "getDayArrayData: " + jsonArray.length());

                this.dayTitle = jObject.getString(fieldsArray[0]);
                this.dayFocus = jObject.getString(fieldsArray[1]);
                this.dayVerse = jObject.getString(fieldsArray[2]);
                this.dayDescription = jObject.getString(fieldsArray[3]);
                this.dayPrayer = jObject.getString(fieldsArray[4]);
                this.dayImageRef = "a21_dop_" + Integer.toString(i+1);

                dataArray[i][0] = this.dayTitle;
                dataArray[i][1] = this.dayFocus;
                dataArray[i][2] = this.dayVerse;
                dataArray[i][3] = this.dayDescription;
                dataArray[i][4] = this.dayPrayer;
                dataArray[i][5] = this.dayImageRef;
            }

            return dataArray;
        } catch (JSONException e) {
            Log.d(TAG, "getDayArray: " + e);
            String[][] s=null;
            return s;
        }
    }

    private String readJsonFile(InputStream inputStream) {
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
