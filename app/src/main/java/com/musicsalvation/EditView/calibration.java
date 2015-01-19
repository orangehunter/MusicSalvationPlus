package com.musicsalvation.EditView;

import android.util.SparseArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.musicsalvation.Graphic;

/**
 * Created by michael on 2015/1/17.
 */
public class calibration {
    int start_y;
    int end_y;
    int start_x;
    int end_x;
    int x_length;
    int unit;
    int unit_max;
    int unit_min;
    int time_lv;
    double time_unit;
    SparseArray<lines> timeLines;
    public calibration(int Duration,int start_x,int start_y,int end_x,int end_y,int line_length) {
        timeLines= new SparseArray<lines>();
        this.start_x=start_x;
        this.start_y=start_y;
        this.end_x=end_x;
        this.end_y=end_y;
        x_length=end_x-start_x;
        unit_max=x_length/5;
        unit_min=x_length/5/10;
        unit=unit_min;
        for (int i=0;i<=60;i++){
            lines li=new lines();
            li.x=start_x+unit*i;
            li.y=start_y;
            if (i%10==0) {
                li.lengh =line_length;
            }else if (i%5==0){
                li.lengh =line_length/2;
            }else{
                li.lengh =line_length/4;
            }
            timeLines.put(i,li);
        }
    }

    public void reSize(){

    }
    public void draw(int currentTime,Canvas canvas,Paint paint){
        for (int i=0;i<timeLines.size();i++){
            lines li;
            li=timeLines.get(i);
            if (li.x<=end_x&&li.x>=start_x) {
                Graphic.drawLine(canvas, Color.BLACK, li.x, li.y, li.x, li.y - li.lengh, 2, paint);
            }
        }
    }
}
