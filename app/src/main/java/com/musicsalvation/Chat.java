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
    public static void Fontsize(int size,Paint paint){
        paint.setTextSize(size);
    }
    public static void Fontcolor(int color,Paint paint){
        paint.setColor(color);
    }
    public static void DrawFont(String s,int x,int y,Canvas canvas,Paint paint){
        canvas.drawText(s,x,y,paint);

    }

}
