package com.musicsalvation.EditView;



import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;
import android.util.SparseArray;

import com.musicsalvation.MainActivity;
import com.musicsalvation.R;
import com.musicsalvation.Graphic;
import com.musicsalvation.Constant;
import com.musicsalvation.Point;

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

        mp=MediaPlayer.create(activity, R.raw.freely_tomorrow);//activity.song);
        ce=new chartEditScreen(activity,150,100,1130,570,mp.getDuration());
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
    SparseArray<Point> pointers = new SparseArray<Point>();
    boolean ce_zoom_flag=false;
    boolean ce_move_flag=false;
    int ce_touch_id1 =-1, ce_touch_id2 =-1;
    double ce_zoom_dis;
    @Override
    public  boolean onTouchEvent(MotionEvent event){
        int pointerIndex=event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN://按下
                Point fd=new Point();
                fd.x=event.getX(pointerIndex);
                fd.y=event.getY(pointerIndex);
                fd.down_x=fd.x;
                fd.down_y=fd.y;
                pointers.put(pointerId,fd);
                if (ce.isIn(fd.x,fd.y)&&pointerIndex==0){
                ce_touch_id1 =pointerId;
                ce_move_flag=true;
        }
                if (ce.isIn(fd.x,fd.y)&& ce_touch_id1 !=-1&&pointerIndex==1){
                    ce_move_flag=false;
                    ce_zoom_flag=true;
                    ce_touch_id2 =pointerId;
                    Point tmp1=pointers.get(ce_touch_id1),tmp2=pointers.get(ce_touch_id2);
                    ce_zoom_dis=Math.sqrt(Math.pow((tmp1.down_x - tmp2.down_x), 2) + Math.pow((tmp1.down_y - tmp2.down_y), 2));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                    Point fm =pointers.get(event.getPointerId(i));
                    if (fm != null) {
                        fm.x = event.getX(i);
                        fm.y = event.getY(i);
                    }
                }
                try {
                    Point tmp1,tmp2;
                    double tmp_dis;
                    if (ce_move_flag){
                        if (mp.isPlaying()){
                            mp.pause();
                        }
                        tmp1=pointers.get(ce_touch_id1);
                        tmp_dis=tmp1.down_x-tmp1.x;
                        ce.Move(tmp_dis);
                    }
                    if (ce_zoom_flag) {
                        tmp1 = pointers.get(ce_touch_id1);
                        tmp2 = pointers.get(ce_touch_id2);
                        tmp_dis =  Math.sqrt(Math.pow((tmp1.x - tmp2.x), 2) + Math.pow((tmp1.y - tmp2.y), 2));
                        if (tmp_dis > ce_zoom_dis * 2) {
                            ce.reLv(1);
                            ce_zoom_flag=false;
                            ce_touch_id1 =-1;
                        }
                        if (tmp_dis < ce_zoom_dis / 2) {
                            ce.reLv(-1);
                            ce_zoom_flag=false;
                            ce_touch_id1 =-1;
                        }
                    }
                }catch (Exception e){}
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                Point fu=new Point();
                fu.x=event.getX(pointerIndex);
                fu.y=event.getY(pointerIndex);
                pointers.remove(pointerId);
                if (ce_zoom_flag) {
                    if (pointerId == ce_touch_id1 || pointerId == ce_touch_id2) {
                        ce_zoom_flag = false;
                        ce_touch_id1 = -1;
                    }
                }
                if (ce_move_flag){
                    if (pointerId==ce_touch_id1){
                        if (!mp.isPlaying()){
                            int tmp=ce.setMove();
                            if (tmp<0)
                                tmp=0;
                            if (tmp>mp.getDuration())
                                tmp=mp.getDuration();
                            mp.seekTo(tmp);
                            mp.start();
                        }
                        ce_move_flag=false;
                        ce_touch_id1=-1;
                    }
                }
                break;
        }
        return true;
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    public void surfaceDestroyed(SurfaceHolder arg0) {//銷毀時被呼叫
        mp.stop();
        mp.release();
    }
}
