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
    public static JSONObject charts;
    public Charts(){
        charts =new JSONObject();
    }

    public JSONObject saveCharts(SparseArray<JSONObject> key) {
        charts =null;
        charts =new JSONObject();
        for(int i=0;i<key.size();i++){
            try {
                charts.put(String.valueOf(key.keyAt(i)), key.valueAt(i));
            }catch (JSONException e){
                Log.e("Charts",""+e);
            }
        }
        return charts;
    }
    public SparseArray<JSONObject> readChartsKey() {
        SparseArray<JSONObject> chart_key =new SparseArray<JSONObject>();
        if (charts !=null) {
            Iterator<String> iter = charts.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                chart_key.put(STI(key), charts.optJSONObject(key));
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
