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
    public static JSONObject charts_obj;
    public Charts(){
        charts_obj =new JSONObject();
    }

    public JSONObject saveCharts(SparseArray<JSONObject> key) {
        charts_obj =null;
        charts_obj =new JSONObject();
        for(int i=0;i<key.size();i++){
            try {
                charts_obj.put(String.valueOf(key.keyAt(i)), key.valueAt(i));
            }catch (JSONException e){
                Log.e("Charts",""+e);
            }
        }
        return charts_obj;
    }
    public SparseArray<JSONObject> readChartsKey() {
        SparseArray<JSONObject> chart_key =new SparseArray<JSONObject>();
        if (charts_obj !=null) {
            Iterator<String> iter = charts_obj.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                chart_key.put(STI(key), charts_obj.optJSONObject(key));
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
