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
    chartEditScreen ce;
    //int Duration=240000;
    int start_y;
    int end_y;
    int start_x;
    int end_x;
    int x_length;
    final int sec_1=0;
    final int sec_10=1;
    final int sec_20=2;
    final int sec_30=3;

    SparseArray<lines> Line;
    SparseArray<String> String_lv[];
    public calibration(chartEditScreen ce,int Duration,int start_x,int start_y,int end_x,int end_y,int line_length) {
        this.ce=ce;
        Line = new SparseArray<lines>();
        String_lv = new SparseArray[4];
        String_lv[0] = new SparseArray<String>();
        String_lv[1] = new SparseArray<String>();
        String_lv[2] = new SparseArray<String>();
        String_lv[3] = new SparseArray<String>();
        this.start_x = start_x;
        this.start_y = start_y;
        this.end_x = end_x;
        this.end_y = end_y;
        x_length = end_x - start_x;


        int counter = 0;
        int c_1 = 0;
        int c_2 = 0;
        int c_3 = 0;
        int c_4 = 0;
        while (counter < Duration) {
            if (counter % 1000 == 0) {
                String_lv[0].put(c_1, getString(counter));
                c_1++;
            }
            if (counter % 10000 == 0) {
                String_lv[1].put(c_2, getString(counter));
                c_2++;
            }
            if (counter % 20000 == 0) {
                String_lv[2].put(c_3, getString(counter));
                c_3++;
            }
            if (counter % 30000 == 0) {
                String_lv[3].put(c_4, getString(counter));
                c_4++;
            }
            counter += 1000;
        }
        int stringCounter = 0;
        for (int i = 0; i <= (c_1 * 10); i++) {
            lines li = new lines();
            li.x = start_x + (x_length / 2) + ce.unit * i;
            li.y = start_y;
            li.string_code = -1;

            if (i % 10 == 0) {
                li.lengh = line_length;
                li.string_code = stringCounter;
                stringCounter++;
            } else if (i % 5 == 0) {
                li.lengh = line_length / 3 * 2;
            } else {
                li.lengh = line_length / 3;
            }
            Line.put(i, li);
        }
    }

    public void draw(int time_lv,Canvas canvas,Paint paint){
        double move=0;
        move=(ce.time_current/100)*ce.unit_lv[time_lv];
        int line_start=0;
        switch (time_lv){
            case sec_1:
                line_start=(ce.time_current/100)-26;
                break;
            case sec_10:
                line_start=(ce.time_current/100/10)-26;
                break;
            case sec_20:
                line_start=(ce.time_current/100/20)-26;
                break;
            case sec_30:
                line_start=(ce.time_current/100/30)-26;
                break;
        }
        if (line_start<0){
            line_start=0;
        }
        for (int k=0;k<54;k++){
            lines li;
            int i=k+line_start;
            try {
                li = Line.get(i);
                if ((li.x - move) <= end_x && (li.x - move) >= start_x) {
                    Graphic.drawLine(canvas, Color.BLACK, li.x - (int) move, li.y, li.x - (int) move, li.y - li.lengh, 2, paint);
                    if (li.string_code > -1 && String_lv[time_lv].get(li.string_code) != null) {
                        Graphic.drawText(canvas, String_lv[time_lv].get(li.string_code), li.x - (int) move, li.y - (li.lengh + 5), Color.BLACK, 12, paint);
                    }
                }
            }catch (Exception e){}
        }
    }
    public String getString(int time){
        String min,sec,sum;
        if(time/1000/60%60<10)//計算分鐘
            min="0"+Integer.toString(time/1000/60%60);
        else
            min=Integer.toString(time/1000/60%60);
        if(time/1000%60<10)//計算秒鐘
            sec="0"+Integer.toString(time/1000%60);
        else
            sec=Integer.toString(time/1000%60);
        sum=min+":"+sec;
        return sum;
    }
}
