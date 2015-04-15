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

import org.json.JSONException;
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
    static SparseArray<JSONObject> chart_key;
    public chart(MainActivity activity,chartEditScreen ce,int start_x,int start_y,int end_x,int end_y){
        BR=new Bitmap[2];
        BR[0]=Graphic.LoadBitmap(activity.getResources(), R.drawable.bottom_round  ,80,80,false);
        BR[1]=Graphic.LoadBitmap(activity.getResources(),R.drawable.btn_long_red_0,60,20,false);

        BS=new Bitmap[2];
        BS[0]=Graphic.LoadBitmap(activity.getResources(),R.drawable.bottom_square  ,80,80,false);
        BS[1]=Graphic.LoadBitmap(activity.getResources(),R.drawable.btn_long_yellow_0  ,80,80,false);

        BT=new Bitmap[2];
        BT[0]=Graphic.LoadBitmap(activity.getResources(),R.drawable.bottom_trangle ,80,80,false);
        BT[1]=Graphic.LoadBitmap(activity.getResources(),R.drawable.btn_long_green_0,80,80,false);

        BX=new Bitmap[2];
        BX[0]=Graphic.LoadBitmap(activity.getResources(),R.drawable.bottom_x       ,80,80,false);
        BX[1]=Graphic.LoadBitmap(activity.getResources(),R.drawable.btn_long_blue_0  ,80,80,false);

        this.start_x=start_x;
        this.start_y=start_y;
        this.end_x=end_x;
        this.end_y=end_y;
        this.ce=ce;
        chart_key =charts.readCharts();

    }
    int main_counter=0;

    JSONObject tmp;
    int key_time;
    public static SparseArray<keyPoint>isInIndex;
    SparseArray<keyPoint>isInIndex_tmp=new SparseArray<keyPoint>();
    keyPoint isIn_point=null;
    SparseArray<Integer>finalDraw_key;
    SparseArray<String>finalDraw_volume;

    public void draw(int time_lv,Canvas canvas,Paint paint){
        int isInIndex_tmp_counter=0;
        while (true){
            if (last_chart<chart_key.size()) {
                if ((int) (start_x+((end_x-start_x)/2)-(((ce.time_current/ce.accuracy)-chart_key.keyAt(last_chart))*ce.unit_lv[time_lv]))>end_x){
                    break;
                }
                last_chart++;
            }else {
                break;
            }
        }
        if (last_chart>1) {
            main_counter = last_chart;
        }else {
            main_counter=0;
        }
        finalDraw_key=new SparseArray<Integer>();
        finalDraw_volume=new SparseArray<String>();
        int finalDraw_counter=0;

        isInIndex_tmp.clear();
        while (true){
            if (main_counter>=chart_key.size()&&main_counter!=0){
                main_counter=chart_key.size()-1;
            }
            tmp=chart_key.get(chart_key.keyAt(main_counter));
            key_time=chart_key.keyAt(main_counter);
            if (tmp!=null){
                int point_x= (int) (start_x+((end_x-start_x)/2)-(((ce.time_current/ce.accuracy)-key_time)*ce.unit_lv[time_lv]));
                if (point_x<start_x){
                    break;
                }
                if (tmp.optInt("R",0)!=0){
                    keyPoint point=new keyPoint(key_time,"R",tmp.optInt("R"),point_x,ce.y1);
                    switch(tmp.optInt("R")){
                        case 1:
                        case 2:
                            Graphic.drawPic(canvas, BR[0], point_x, ce.y1, 0, 255, paint);
                            point.setPic(BR[0].getWidth(), BR[0].getHeight());
                            break ;
                        case 3:
                            Graphic.drawPic(canvas, BR[1], point_x, ce.y1, 0, 255, paint);
                            point.setPic(BR[1].getWidth(), BR[1].getHeight());
                            break;
                        case 4:
                            point.setPic(BR[0].getWidth(),BR[0].getHeight());
                            finalDraw_key.put(finalDraw_counter,key_time);
                            finalDraw_volume.put(finalDraw_counter,"R");
                            finalDraw_counter++;
                            break;
                    }
                    isInIndex_tmp.put(isInIndex_tmp_counter,point);
                    isInIndex_tmp_counter++;
                }
                if (tmp.optInt("S",0)!=0){
                    keyPoint point=new keyPoint(key_time,"S",tmp.optInt("S"),point_x,ce.y2);
                    switch(tmp.optInt("S")){
                        case 1:
                        case 2:
                            Graphic.drawPic(canvas, BS[0], point_x, ce.y2, 0, 255, paint);
                            point.setPic(BS[0].getWidth(), BS[0].getHeight());
                            break ;
                        case 3:
                            Graphic.drawPic(canvas, BS[1], point_x, ce.y2, 0, 255, paint);
                            point.setPic(BS[1].getWidth(), BS[1].getHeight());
                            break;
                        case 4:
                            point.setPic(BS[0].getWidth(),BS[0].getHeight());
                            finalDraw_key.put(finalDraw_counter,key_time);
                            finalDraw_volume.put(finalDraw_counter,"S");
                            finalDraw_counter++;
                            break;
                    }
                    isInIndex_tmp.put(isInIndex_tmp_counter,point);
                    isInIndex_tmp_counter++;
                }
                if (tmp.optInt("T",0)!=0){
                    keyPoint point=new keyPoint(key_time,"T",tmp.optInt("T"),point_x,ce.y3);
                    switch(tmp.optInt("T")){
                        case 1:
                        case 2:
                            Graphic.drawPic(canvas, BT[0], point_x, ce.y3, 0, 255, paint);
                            point.setPic(BT[0].getWidth(), BT[0].getHeight());
                            break ;
                        case 3:
                            Graphic.drawPic(canvas, BT[1], point_x, ce.y3, 0, 255, paint);
                            point.setPic(BT[1].getWidth(), BT[1].getHeight());
                            break;
                        case 4:
                            point.setPic(BT[0].getWidth(),BT[0].getHeight());
                            finalDraw_key.put(finalDraw_counter,key_time);
                            finalDraw_volume.put(finalDraw_counter,"T");
                            finalDraw_counter++;
                            break;
                    }
                    isInIndex_tmp.put(isInIndex_tmp_counter,point);
                    isInIndex_tmp_counter++;
                }
                if (tmp.optInt("X",0)!=0){
                    keyPoint point=new keyPoint(key_time,"X",tmp.optInt("X"),point_x,ce.y4);
                    switch(tmp.optInt("X")){
                        case 1:
                        case 2:
                            Graphic.drawPic(canvas, BX[0], point_x, ce.y4, 0, 255, paint);
                            point.setPic(BX[0].getWidth(), BX[0].getHeight());
                            break ;
                        case 3:
                            Graphic.drawPic(canvas, BX[1], point_x, ce.y4, 0, 255, paint);
                            point.setPic(BX[1].getWidth(), BX[1].getHeight());
                            break;
                        case 4:
                            point.setPic(BX[0].getWidth(),BX[0].getHeight());
                            finalDraw_key.put(finalDraw_counter,key_time);
                            finalDraw_volume.put(finalDraw_counter,"X");
                            finalDraw_counter++;
                            break;
                    }
                    isInIndex_tmp.put(isInIndex_tmp_counter,point);
                    isInIndex_tmp_counter++;
                }
            }
            main_counter--;
            if (main_counter<0)
                break;
        }

        isInIndex=isInIndex_tmp;
        //Log.e("index_tmp",""+isInIndex_tmp_counter+" "+isInIndex.size());

        /*if (finalDraw_key.size()!=&&finalDraw_volume.size()!=0){
            for (int a=finalDraw_counter;a>0;a--){
                int point_x= (int) (start_x+((end_x-start_x)/2)-(((ce.time_current/ce.accuracy)-key_time)*ce.unit_lv[time_lv]));
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
        }*/

    }
    public void put_long(int key_start,int key_end,String btn){
        put(key_start,btn,2);
        for (int i=key_start+1;i<key_end;i++){
            put(i,btn,3);
        }
        put(key_end,btn,4);
    }
    public void put(int key,String btn,int volum){
        JSONObject tmp;
        try {
            if (chart_key.get(key)==null){
                tmp=new JSONObject();
                tmp.put(btn, volum);
                chart_key.put(key,tmp);
            }else{
                tmp=chart_key.get(key);
                tmp.put(btn, volum);
                chart_key.remove(key);
                chart_key.put(key,tmp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void remove(int key,String btn){
        JSONObject tmp;
        if (chart_key.get(key).length()>1){
            tmp=chart_key.get(key);
            tmp.remove(btn);
            chart_key.remove(key);
            chart_key.put(key,tmp);
        }else{
            chart_key.remove(key);
        }
    }
    public void drag(int from,int to,String btn,int type){
        if (from!=to) {
            if (type == 1) {
                remove(from, btn);
                put(to, btn, 1);
            } else {
                int dis = to - from;
                int tmp_counter=0;
                if (type != 2) {
                    tmp_counter=chart_key.indexOfKey(from);
                    do {
                        tmp_counter--;
                    }while(chart_key.get(tmp_counter).optInt(btn,0)!=2);
                }else {
                    tmp_counter=from;
                }
                remove(tmp_counter,btn);
                put(tmp_counter+dis,btn,2);
                tmp_counter++;
                do {
                    remove(tmp_counter,btn);
                    put(tmp_counter+dis,btn,3);
                    tmp_counter++;
                }while (chart_key.get(tmp_counter).optInt(btn,0)!=4);
                remove(tmp_counter,btn);
                put(tmp_counter+dis,btn,4);
            }
        }
    }
    public boolean isIn(double x,double y){
        if (isInIndex!=null) {
            for (int i = 0; i < isInIndex.size(); i++) {
                double start_x = isInIndex.get(i).x;
                double start_y = isInIndex.get(i).y;
                double end_x = start_x + isInIndex.get(i).width;
                double end_y = start_y + isInIndex.get(i).hight;
                if (x > start_x && x < end_x && y > start_y && y < end_y) {
                    isIn_point = isInIndex.get(i);
                    return true;
                }
            }
            return false;
        }else {
            return false;
        }
    }
    public void del(){
        if (isIn_point!=null){
            remove(isIn_point.key,isIn_point.btn);
            isIn_point=null;
        }
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
