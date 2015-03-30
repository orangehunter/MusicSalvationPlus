package com.musicsalvation.EditView;



import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.util.SparseArray;

import com.musicsalvation.Charts;
import com.musicsalvation.Graphic;

import org.json.JSONObject;

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
    int time_side_dis[]={30,300,600,900};
    SparseArray<JSONObject> chart_key;
    public chart(chartEditScreen ce,int start_x,int start_y,int end_x,int end_y){
        this.start_x=start_x;
        this.start_y=start_y;
        this.end_x=end_x;
        this.end_y=end_y;
        this.ce=ce;
        chart_key =new SparseArray<JSONObject>();
        Iterator<String> iter = charts.chart.keys();
        while (iter.hasNext()) {
            String key=iter.next();
            chart_key.put(STI(key),charts.chart.optJSONObject(key));
        }


    }
    int main_counter=0;
    public void draw(int time_lv,Canvas canvas,Paint paint){
        if (ce.time_current+ time_side_dis[time_lv]>chart_key.keyAt(last_chart)) {
            last_chart++;
        }
        main_counter=last_chart;
        JSONObject tmp;
        do{
            tmp=chart_key.get(chart_key.keyAt(main_counter));
            if (tmp==null){
                Log.e("chart draw","chart_key volume is null.");
            }else{

            }
            main_counter--;
        }while (ce.time_current-time_side_dis[time_lv]>chart_key.keyAt(main_counter));
    }

    public int STI(String key){
        return Integer.valueOf(key);
    }
}
