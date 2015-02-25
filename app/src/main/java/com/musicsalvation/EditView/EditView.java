package com.musicsalvation.EditView;



import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.musicsalvation.MainActivity;
import com.musicsalvation.R;
import com.musicsalvation.Graphic;
import com.musicsalvation.Constant;

@SuppressLint("ViewConstructor")
public class EditView extends SurfaceView implements SurfaceHolder.Callback{
    MainActivity activity;
    Paint paint;			//畫筆的參考
    MediaPlayer mp;
    chartEditScreen ce;

    int current=0;

    public EditView(MainActivity mainActivity) {
        super(mainActivity);
        this.activity = mainActivity;
        this.getHolder().addCallback(this);//設定生命周期回調接口的實現者

    }
    @Override
     public void surfaceCreated(SurfaceHolder holder) {
        paint = new Paint();//建立畫筆
        paint.setAntiAlias(true);//開啟抗鋸齒
        ce=new chartEditScreen(activity,150,100,1130,570);
        mp=MediaPlayer.create(activity, R.raw.freely_tomorrow);//activity.song);
        mp.start();
        current=0;
        new Thread(){
            @SuppressLint("WrongCall")
            public void run(){
                while(true){
                    SurfaceHolder myholder=EditView.this.getHolder();
                    Canvas canvas = myholder.lockCanvas();//取得畫布
                    onDraw(canvas);
                    if(canvas != null){
                        myholder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }.start();
    }



    @Override
    protected void onDraw(Canvas canvas) {//重新定義的繪制方法
        if(canvas!=null){
            super.onDraw(canvas);
            paint.setAntiAlias(true);	//開啟抗鋸齒
            //繪制黑填充矩形清背景
            paint.setColor(Color.WHITE);//設定畫筆彩色
            paint.setAlpha(255);
            canvas.drawRect(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HIGHT, paint);
            paint.reset();
            try {current=mp.getCurrentPosition();
            }catch (Exception e){}
            ce.draw(canvas,paint,current);
            if(mp!=null) {
                String min, sec, msec;

                if (mp.getCurrentPosition() / 1000 / 60 % 60 < 10)//計算分鐘
                    min = "0" + Integer.toString(mp.getCurrentPosition() / 1000 / 60 % 60);
                else
                    min = Integer.toString(mp.getCurrentPosition() / 1000 / 60 % 60);
                if (mp.getCurrentPosition() / 1000 % 60 < 10)//計算秒鐘
                    sec = "0" + Integer.toString(mp.getCurrentPosition() / 1000 % 60);
                else
                    sec = Integer.toString(mp.getCurrentPosition() / 1000 % 60);
                if (mp.getCurrentPosition() % 1000 / 10 < 10)//計算豪秒
                    msec = "0" + Integer.toString(mp.getCurrentPosition() % 1000 / 10);
                else
                    msec = Integer.toString(mp.getCurrentPosition() % 1000 / 10);


                String time = min + ":" + sec + ":" + msec;
                Graphic.drawText(canvas,time,1110,650,Color.BLACK,24,paint);
            }
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    public void surfaceDestroyed(SurfaceHolder arg0) {//銷毀時被呼叫
        mp.stop();
        mp.release();
    }
}
