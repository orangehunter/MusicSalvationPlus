package com.musicsalvation;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
/**
 * Created by Ryo on 2015/6/18.
 */
public class Chat {
    MainActivity activity;
    //String hello = "hello";
    String string; //字串
    int Fontsize;  //文字大小
    int color;    //顏色
    int x;          //X座標
    int y;         //Y座標




    public void Draw(String s,int x,int y,Canvas canvas,Paint paint){

        canvas.drawText(s,x,y,paint);

    }

}
