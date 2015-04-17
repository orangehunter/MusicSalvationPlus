package com.musicsalvation.ChgSongView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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
    public songWheel(MainActivity activity){
        //test
        activity.io.song_list=new JSONArray();
        for (int i=0;i<20;i++) {
            activity.io.song_list.put(String.valueOf(i));
        }
        st[0]=activity.io.song_list.optString(0);
        st[3]=activity.io.song_list.optString(1);
        st[2]="";
        st[1]="";
        //test
        this.activity=activity;
        tag= Graphic.LoadBitmap(activity.getResources(), R.drawable.fv_song_bar,618,223,true);


        x[1]=1040;
        y[1]=185;

        x[0]=970;
        y[0]=405;

        x[3]=1040;
        y[3]=625;

        x[2]=1660;
        y[2]=405;

        x_now[0]=x[0];
        y_now[0]=y[0];

        x_now[1]=x[1];
        y_now[1]=y[1];

        x_now[2]=x[2];
        y_now[2]=y[2];

        x_now[3]=x[3];
        y_now[3]=y[3];
    }
    public void draw(Canvas canvas,Paint paint){

        for (int i=0;i<4;i++){
            x_now[i]= Coordinate.AnalogSpeedMove(x_now[i],x[Math.abs(now+i)%4]);
            y_now[i]= Coordinate.AnalogSpeedMove(y_now[i],y[Math.abs(now+i)%4]);
            Graphic.drawPic(canvas,tag,x_now[i],y_now[i],0,255,paint);
            Graphic.drawText(canvas,String.valueOf(i),x_now[i]-200,y_now[i],Color.WHITE,24,paint);
        }
    }
    public void change(int ch){
        if (ch>0){
            if (now+2<activity.io.song_list.length()) {
                st[(now + 2) % 4] = activity.io.song_list.optString(now + 2);
            }else {
                st[(now + 2) % 4] ="";
            }
        }else {
            if (now-2>=0){
                st[(now + 2) % 4] = activity.io.song_list.optString(now - 2);
            }else{
                st[(now+ 2) % 4] ="";
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
