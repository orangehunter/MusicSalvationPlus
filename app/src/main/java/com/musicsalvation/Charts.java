package com.musicsalvation;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2015/3/19.
 */
public class Charts {
    public static JSONObject chart;
    public Charts(){
        chart=new JSONObject();
    }
    public void put(int time,String key,int type){
        try {
            JSONObject tmp;
            String time_s=String.valueOf(time/100);
            tmp=chart.optJSONObject(time_s);
            if (tmp!=null){
                tmp.put(key,type);
                chart.remove(time_s);
                chart.put(time_s,tmp);
            }else{
                tmp.put(key, type);
                chart.put(time_s,tmp);
            }
        } catch (JSONException e) {
            Log.e("Charts put error",""+e);
        }
    }
    public void reomve(int time,String key,int type){
        try {
            JSONObject tmp;
            String time_s=String.valueOf(time/100);
            tmp=chart.optJSONObject(time_s);
            if (tmp!=null){
                tmp.remove(key);
                chart.remove(time_s);
                chart.put(time_s,tmp);
            }else{
                chart.remove(time_s);
            }
        } catch (JSONException e) {
            Log.e("Charts put error",""+e);
        }
    }
}
