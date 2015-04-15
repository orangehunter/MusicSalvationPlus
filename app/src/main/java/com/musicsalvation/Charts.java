package com.musicsalvation;

import android.util.Log;
import android.util.SparseArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by user on 2015/3/19.
 */
public class Charts {
    public static JSONObject chart;
    public Charts(){
        chart=new JSONObject();
    }

    public JSONObject saveCharts(SparseArray<JSONObject> key) {
        chart=null;
        chart=new JSONObject();
        for(int i=0;i<key.size();i++){
            try {
                chart.put(String.valueOf(key.keyAt(i)),key.valueAt(i));
            }catch (JSONException e){
                Log.e("Charts",""+e);
            }
        }
        return chart;
    }
    public SparseArray<JSONObject> readCharts() {
        SparseArray<JSONObject> chart_key =new SparseArray<JSONObject>();
        if (chart!=null) {
            Iterator<String> iter = chart.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                chart_key.put(STI(key), chart.optJSONObject(key));
            }
        }
        return chart_key;
    }
    public int STI(String key){
        return Integer.valueOf(key);
    }
    public void toOldCharts(){

    }
}
