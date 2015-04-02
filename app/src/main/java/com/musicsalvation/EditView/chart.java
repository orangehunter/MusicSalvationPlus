package com.musicsalvation.EditView;



import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.util.SparseArray;

import com.musicsalvation.Charts;
import com.musicsalvation.Graphic;
import com.musicsalvation.MainActivity;
import com.musicsalvation.R;

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

    Bitmap [] BR,BS,BT,BX;


    int last_chart=0;
    int time_side_dis[]={30,300,600,900};
    SparseArray<JSONObject> chart_key;
    public chart(MainActivity activity,chartEditScreen ce,int start_x,int start_y,int end_x,int end_y){
        BR=new Bitmap[2];
        BR[0]=Graphic.LoadBitmap(activity.getResources(), R.drawable.bottom_round  ,80,80,false);

        BS=new Bitmap[2];
        BS[0]=Graphic.LoadBitmap(activity.getResources(),R.drawable.bottom_square  ,80,80,false);

        BT=new Bitmap[2];
        BT[0]=Graphic.LoadBitmap(activity.getResources(),R.drawable.bottom_trangle ,80,80,false);

        BX=new Bitmap[2];
        BX[0]=Graphic.LoadBitmap(activity.getResources(),R.drawable.bottom_x       ,80,80,false);

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

    JSONObject tmp;
    int key_time;
    SparseArray<Integer>finalDraw_key;
    SparseArray<String>finalDraw_volume;

    public void draw(int time_lv,Canvas canvas,Paint paint){
        while (ce.time_current+ time_side_dis[time_lv]>chart_key.keyAt(last_chart)) {
            if (last_chart<chart_key.size()-1) {
                last_chart++;
            }else {
                break;
            }
        }
        main_counter=last_chart;
        finalDraw_key=new SparseArray<Integer>();
        finalDraw_volume=new SparseArray<String>();
        int finalDraw_counter=0;

        do{
            tmp=chart_key.get(chart_key.keyAt(main_counter));
            key_time=chart_key.keyAt(main_counter);
            if (tmp==null){
                Log.e("chart draw","chart_key volume is null.");
            }else{
                int point_x= (int) (start_x+(end_x-start_x)+(((ce.time_current/ce.accuracy)-key_time)*ce.unit_lv[time_lv]));

                if (tmp.optInt("R",0)!=0){
                    if (tmp.optInt("R")==3){
                        finalDraw_key.put(finalDraw_counter,key_time);
                        finalDraw_volume.put(finalDraw_counter,"R");
                        finalDraw_counter++;
                    }else {
                        Graphic.drawPic(canvas, BR[tmp.optInt("R") - 1], point_x, ce.y1, 0, 255, paint);
                    }
                }
                if (tmp.optInt("S",0)!=0){
                    if (tmp.optInt("S")==3){
                        finalDraw_key.put(finalDraw_counter,key_time);
                        finalDraw_volume.put(finalDraw_counter,"S");
                        finalDraw_counter++;
                    }else {
                        Graphic.drawPic(canvas, BS[tmp.optInt("S") - 1], point_x, ce.y2, 0, 255, paint);
                    }
                }
                if (tmp.optInt("T",0)!=0){
                    if (tmp.optInt("T")==3){
                        finalDraw_key.put(finalDraw_counter,key_time);
                        finalDraw_volume.put(finalDraw_counter,"T");
                        finalDraw_counter++;
                    }else {
                        Graphic.drawPic(canvas,BT[tmp.optInt("T")-1],point_x,ce.y3,0,255,paint);
                    }
                }
                if (tmp.optInt("X",0)!=0){
                    if (tmp.optInt("X")==3){
                        finalDraw_key.put(finalDraw_counter,key_time);
                        finalDraw_volume.put(finalDraw_counter,"X");
                        finalDraw_counter++;
                    }else {
                        Graphic.drawPic(canvas,BX[tmp.optInt("X")-1],point_x,ce.y4,0,255,paint);
                    }
                }
            }
            main_counter--;
        }while (ce.time_current-time_side_dis[time_lv]>chart_key.keyAt(main_counter));

        if (finalDraw_counter!=0){
            for (int a=finalDraw_counter;a>0;a--){
                int point_x= (int) (start_x+(end_x-start_x)+(((ce.time_current/ce.accuracy)-finalDraw_key.get(a))*ce.unit_lv[time_lv]));
                switch (finalDraw_volume.get(a)){
                    case "R":
                        Graphic.drawPic(canvas, BR[0], point_x, ce.y1, 0, 255, paint);
                        break;
                    case "S":
                        Graphic.drawPic(canvas, BS[0], point_x, ce.y2, 0, 255, paint);
                        break;
                    case "T":
                        Graphic.drawPic(canvas, BT[0], point_x, ce.y3, 0, 255, paint);
                        break;
                    case "X":
                        Graphic.drawPic(canvas, BX[0], point_x, ce.y4, 0, 255, paint);
                        break;
                }
            }
        }

    }

    public int STI(String key){
        return Integer.valueOf(key);
    }
    public void recycle(){
        for(Bitmap arr:BR){
            arr.recycle();
        }
        for(Bitmap arr:BS){
            arr.recycle();
        }
        for(Bitmap arr:BT){
            arr.recycle();
        }
        for(Bitmap arr:BX){
            arr.recycle();
        }
    }
}
