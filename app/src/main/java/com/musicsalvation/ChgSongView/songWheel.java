package com.musicsalvation.ChgSongView;

import android.graphics.Bitmap;

import com.musicsalvation.Graphic;
import com.musicsalvation.MainActivity;
import com.musicsalvation.R;

/**
 * Created by user on 2015/4/17.
 */
public class songWheel {
    MainActivity activity;
    Bitmap tag;
    public songWheel(MainActivity activity){
        this.activity=activity;
        //tag= Graphic.LoadBitmap(activity.getResources(), R.drawable.d)
    }
    public void draw(){
        
    }
    public void change(int ch){

    }
    public void recycle(){
        tag.recycle();
    }
}
