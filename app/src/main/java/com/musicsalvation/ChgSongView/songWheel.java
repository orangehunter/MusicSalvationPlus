package com.musicsalvation.ChgSongView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.SparseArray;

import com.musicsalvation.Coordinate;
import com.musicsalvation.Graphic;
import com.musicsalvation.MainActivity;
import com.musicsalvation.R;

import org.json.JSONArray;

/**
 * Created by user on 2015/4/17.
 */
public class songWheel {
    MainActivity activity;

    Bitmap tag;
    int now=0;
    String st[]=new String[4];
    int []x=new int[4],y=new int[4];
    int []x_now=new int[4],y_now=new int[4];
    SparseArray<point>point=new SparseArray<>();
    public songWheel(MainActivity activity){

        this.activity=activity;
        tag= Graphic.LoadBitmap(activity.getResources(), R.drawable.fv_song_bar,618,223,true);


        x[0]=1040;
        y[0]=185;

        x[1]=970;
        y[1]=405;

        x[2]=1040;
        y[2]=625;

        x[3]=1660;
        y[3]=405;

        x_now[0]=x[0];
        y_now[0]=y[0];

        x_now[1]=x[1];
        y_now[1]=y[1];

        x_now[2]=x[2];
        y_now[2]=y[2];

        x_now[3]=x[3];
        y_now[3]=y[3];
        if (activity.io.song_list!=null) {
            for (int i = 0; i < activity.io.song_list.length(); i++) {
                point p = new point();
                p.x = x[3];
                p.y = y[3];
                point.put(i, p);
            }
        }
    }
    public void draw(Canvas canvas,Paint paint){

        for (int i=0;i<4;i++){
            x_now[i]= Coordinate.AnalogSpeedMove(x_now[i],x[Math.abs(now+i)%4]);
            y_now[i]= Coordinate.AnalogSpeedMove(y_now[i],y[Math.abs(now+i)%4]);
            Graphic.drawPic(canvas,tag,x_now[i],y_now[i],0,255,paint);
        }
        if(activity.io.song_list!=null) {
            for (int k = 0; k < activity.io.song_list.length(); k++) {
                point p = point.get(k);
                if (now + 2 > k && now - 2 < k) {
                    if (k == now - 2 || k == now + 2) {
                        p.x = Coordinate.AnalogSpeedMove(p.x, x[3]);
                        p.y = Coordinate.AnalogSpeedMove(p.y, y[3]);
                        point.remove(k);
                        point.put(k, p);
                    }
                    if (k == now - 1) {
                        p.x = Coordinate.AnalogSpeedMove(p.x, x[2] - 200);
                        p.y = Coordinate.AnalogSpeedMove(p.y, y[2] + 20);
                        point.remove(k);
                        point.put(k, p);
                    }
                    if (k == now + 1) {
                        p.x = Coordinate.AnalogSpeedMove(p.x, x[0] - 200);
                        p.y = Coordinate.AnalogSpeedMove(p.y, y[0] + 20);
                        point.remove(k);
                        point.put(k, p);
                    }
                    if (k == now) {
                        p.x = Coordinate.AnalogSpeedMove(p.x, x[1] - 200);
                        p.y = Coordinate.AnalogSpeedMove(p.y, y[1] + 20);
                        point.remove(k);
                        point.put(k, p);
                    }
                    Graphic.drawText(canvas, activity.io.song_list.optString(k), p.x, p.y, Color.WHITE, 48, paint);
                } else {
                    p.x = Coordinate.AnalogSpeedMove(p.x, x[3]);
                    p.y = Coordinate.AnalogSpeedMove(p.y, y[3]);
                    point.remove(k);
                    point.put(k, p);
                }
            }
        }
    }
    public void change(int ch){
        if (ch>0){
            if (now+2<activity.io.song_list.length()) {
                st[(now+1) % 4] = activity.io.song_list.optString(now + 2);
            }else {
                st[(now+1) % 4] ="";
            }
        }else {
            if (now-2>=0){
                st[(now+1) % 4] = activity.io.song_list.optString(now - 2);
            }else{
                st[(now+1) % 4] ="";
            }
        }
        if (now+ch>=0&&now+ch<activity.io.song_list.length()) {
            now += ch;
        }
    }
    public void recycle(){
        tag.recycle();
    }
}
