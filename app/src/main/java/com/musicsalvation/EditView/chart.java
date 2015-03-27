package com.musicsalvation.EditView;



import android.graphics.Canvas;
import android.graphics.Paint;

import com.musicsalvation.Bottom;
import com.musicsalvation.Charts;
import com.musicsalvation.MainActivity;

import org.json.JSONException;

import java.util.Iterator;

public class chart {
    chartEditScreen ce;
    Charts charts;
    int start_y;
    int end_y;
    int start_x;
    int end_x;
    int x_length;
    final int sec_1=0;
    final int sec_10=1;
    final int sec_20=2;
    final int sec_30=3;

    int last_chart=0;
    public chart(chartEditScreen ce,int start_x,int start_y,int end_x,int end_y){
        this.ce=ce;


    }
    public void draw(int time_lv,Canvas canvas,Paint paint){
        if (last_chart==0){
            Iterator<String> iter = json.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    Object value = json.get(key);
                } catch (JSONException e) {
                    // Something went wrong!
                }
            }
        }
    }

}
