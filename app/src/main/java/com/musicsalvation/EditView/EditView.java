package com.musicsalvation.EditView;



import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.musicsalvation.MainActivity;
import com.musicsalvation.Graphic;
import com.musicsalvation.Constant;

@SuppressLint("ViewConstructor")
public class EditView extends SurfaceView implements SurfaceHolder.Callback{
    MainActivity activity;
    Paint paint;			//畫筆的參考
    chartEditScreen ce;

    public EditView(MainActivity mainActivity) {
        super(mainActivity);
        this.activity = mainActivity;
        this.getHolder().addCallback(this);//設定生命周期回調接口的實現者

    }
    @Override
     public void surfaceCreated(SurfaceHolder holder) {
        paint = new Paint();//建立畫筆
        paint.setAntiAlias(true);//開啟抗鋸齒
        ce=new chartEditScreen(activity,150,150,1130,570);

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
            ce.draw(canvas,paint,0);
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    public void surfaceDestroyed(SurfaceHolder arg0) {//銷毀時被呼叫

    }
}
