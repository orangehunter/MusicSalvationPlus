package com.musicsalvation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by user on 2015/5/13.
 */
public class Setting {
    MainActivity activity;
    boolean main_flag=false;

    Bitmap back;

    MySeekBar music_volum,button_volum;
    Bitmap bar,btn;

    Botton sound_btn[];
    Bitmap btn_on[],btn_off[];

    Botton exit;
    Bitmap ext;
    int main_alpha=0;

    public Setting(MainActivity activity){
        this.activity=activity;
        back=Graphic.LoadBitmap(activity.getResources(),R.drawable.st_tempback,1280,540,false);

        bar=Graphic.LoadBitmap(activity.getResources(),R.drawable.st_volbar,490,90,false);
        btn=Graphic.LoadBitmap(activity.getResources(),R.drawable.st_volbtn,46,97,false);
        music_volum=new MySeekBar(activity,bar,btn,730,175);
        button_volum=new MySeekBar(activity,bar,btn,730,300);

        btn_on=new Bitmap[5];
        btn_off=new Bitmap[5];
        sound_btn=new Botton[5];
        for (int i=0;i<5;i++){
            btn_on[i]=Graphic.LoadBitmap(activity.getResources(),R.drawable.st_t_se01+i,138,138,true);
            btn_off[i]=Graphic.LoadBitmap(activity.getResources(),R.drawable.st_f_se01+i,84,84,true);
            sound_btn[i]=new Botton(activity,btn_on[i],btn_off[i],525+(110*i),420);
        }
        ext=Graphic.LoadBitmap(activity.getResources(),R.drawable.st_exit_btn,140,130,true);
        exit=new Botton(activity,ext,ext,75,180);
    }
    public void start(){
        main_flag=true;
    }
    public void Draw(Canvas canvas,Paint paint){
        if (main_flag){
            Coordinate.AnalogSpeedMove(main_alpha,255);
        }else {
            Coordinate.AnalogSpeedMove(main_alpha,0);
        }
        if (main_alpha>0){
            if (main_alpha-100>0) {
                Graphic.drawRect(canvas, Color.BLACK, 0, 0, 1280, 720, main_alpha - 100, paint);
            }

        }
    }
    public void Action_Dowm(){
        if (main_flag){

        }
    }
    public void Action_Up(){
        if (main_flag){

        }
    }
    public void recycle(){
        back.recycle();

        bar.recycle();
        btn.recycle();

        for(Bitmap arr:btn_on){
            arr.recycle();
        }
        for(Bitmap arr:btn_off){
            arr.recycle();
        }

        ext.recycle();
    }

}
