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
    String string; //�r��
    int Fontsize;  //��r�j�p
    int color;    //�C��
    int x;          //X�y��
    int y;         //Y�y��




    public void Draw(String s,int x,int y,Canvas canvas,Paint paint){

        canvas.drawText(s,x,y,paint);

    }

}
