package com.musicsalvation.EditView;



import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;
import android.util.SparseArray;

import com.musicsalvation.Bottom;
import com.musicsalvation.Charts;
import com.musicsalvation.MainActivity;
import com.musicsalvation.R;
import com.musicsalvation.Graphic;
import com.musicsalvation.Constant;
import com.musicsalvation.touchPoint;

@SuppressLint("ViewConstructor")
public class EditView extends SurfaceView implements SurfaceHolder.Callback{
    MainActivity activity;
    Paint paint;			//畫筆的參考
    Charts charts;
    MediaPlayer mp;
    chartEditScreen ce;

    Bitmap back,frame;
    Bitmap btn_del_0,btn_del_1;
    Bitmap btn_edt_0,btn_edt_1;
    Bitmap play,pause,save;
    Bottom btn_del_mod,btn_edt_mod,btn_mp_ctrl,btn_save;
    boolean del_mod_flag;
    boolean drag_flag;

    Bitmap BR,BS,BT,BX,BB;
    Bottom btn_r;
    Bottom btn_s;
    Bottom btn_t;
    Bottom btn_x;

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

        if (activity.io.song_uri!=null) {
            mp = MediaPlayer.create(activity, activity.io.song_uri);
        }else{
            activity.changeView(1);
        }
        String n=activity.io.song_name+activity.io.chart_id;
        if (activity.io.chart_exists(n)){
            charts=activity.io.readChart(n);
        }else {
            charts=new Charts();
        }
        ce=new chartEditScreen(activity,this,180,50,1100,545,mp.getDuration());
        if (charts!=null) {
            ce.ct.chart_key = charts.readChartsKey();
        }else {
            ce.ct.chart_key=new SparseArray<>();
        }


        Resources rs=activity.getResources();
        back=Graphic.LoadBitmap(rs,R.drawable.edit_view_back,1280,720,false);
        frame=Graphic.LoadBitmap(rs,R.drawable.edit_view_frame_2,1279,501,false);

        btn_del_0=Graphic.LoadBitmap(rs,R.drawable.edit_view_btn_del_0,247,125,true);
        btn_del_1=Graphic.LoadBitmap(rs,R.drawable.edit_view_btn_del_1,247,125,true);
        btn_del_mod=new Bottom(activity,btn_del_0,btn_del_1,517,665);
        btn_del_mod.setBottomTo(true);//將刪除模式按鈕設為滅

        btn_edt_0=Graphic.LoadBitmap(rs,R.drawable.edit_view_btn_edit_0,247,125,true);
        btn_edt_1=Graphic.LoadBitmap(rs,R.drawable.edit_view_btn_edit_1,247,125,true);
        btn_edt_mod=new Bottom(activity,btn_edt_0,btn_edt_1,763,665);

        play=Graphic.LoadBitmap(rs,R.drawable.edit_view_btn_play,164,182,false);
        pause=Graphic.LoadBitmap(rs,R.drawable.edit_view_btn_pause,164,182,false);
        save=Graphic.LoadBitmap(rs,R.drawable.edit_view_btn_save,141,216,false);
        btn_mp_ctrl=new Bottom(activity,pause,play,93,163);
        btn_save=new Bottom(activity,save,save,1180,180);

        del_mod_flag=false;//將刪除模式關閉
        drag_flag=false;//拖曳功能初始值

        int btn_size=175;
        BR=Graphic.LoadBitmap(rs,R.drawable.bottom_round,btn_size,btn_size,true);
        BS=Graphic.LoadBitmap(rs,R.drawable.bottom_square,btn_size,btn_size,true);
        BT=Graphic.LoadBitmap(rs,R.drawable.bottom_trangle,btn_size,btn_size,true);
        BX=Graphic.LoadBitmap(rs,R.drawable.bottom_x,btn_size,btn_size,true);
        BB=Graphic.LoadBitmap(rs,R.drawable.bottom_pushed,btn_size,btn_size,true);
        btn_r=new Bottom(activity,BB,BR,90, 455);
        btn_s = new Bottom(activity, BB, BS, 265, 625);
        btn_t = new Bottom(activity, BB, BT, 1015, 625);
        btn_x = new Bottom(activity, BB, BX, 1190, 455);

        current=0;

        Constant.Flag=true;
        new Thread(){
            @SuppressLint("WrongCall")
            public void run(){
                while(Constant.Flag){
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
    @SuppressLint("DrawAllocation")
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
            Graphic.drawPic(canvas,back,1280/2,720/2,0,255,paint);
            try {current=mp.getCurrentPosition();
            }catch (Exception e){}
            ce.draw(canvas, paint, current);
            Graphic.drawPic(canvas,frame,640,295,0,255,paint);
            btn_r.drawBtm(canvas,paint);
            btn_s.drawBtm(canvas,paint);
            btn_t.drawBtm(canvas,paint);
            btn_x.drawBtm(canvas,paint);

            btn_edt_mod.drawBtm(canvas,paint);
            btn_del_mod.drawBtm(canvas,paint);

            btn_mp_ctrl.drawBtm(canvas,paint);
            btn_save.drawBtm(canvas,paint);

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
                Graphic.drawText(canvas,time,1280/2,570,Color.WHITE,24,paint);
            }
        }
    }
    SparseArray<touchPoint> pointers = new SparseArray<touchPoint>();
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
                touchPoint fd=new touchPoint();
                fd.x=event.getX(pointerIndex);
                fd.y=event.getY(pointerIndex);
                fd.down_x=fd.x;
                fd.down_y=fd.y;
                if (mp!=null)
                    fd.down_time=mp.getCurrentPosition()/ce.accuracy;
                pointers.put(pointerId,fd);
                if (ce.isIn(fd.x,fd.y)&&pointerIndex==0){
                    if (ce.ct.isIn(fd.x,fd.y)&&!btn_del_mod.getBottom()){
                        ce.ct.del();
                    }else {
                        ce_touch_id1 = pointerId;
                        ce_move_flag = true;
                    }
                }
                if (ce.isIn(fd.x,fd.y)&& ce_touch_id1 !=-1&&pointerIndex==1){
                    ce_move_flag=false;
                    ce_zoom_flag=true;
                    ce_touch_id2 =pointerId;
                    touchPoint tmp1=pointers.get(ce_touch_id1),tmp2=pointers.get(ce_touch_id2);
                    ce_zoom_dis=Math.sqrt(Math.pow((tmp1.down_x - tmp2.down_x), 2) + Math.pow((tmp1.down_y - tmp2.down_y), 2));
                }
                if(btn_edt_mod.isIn(fd.x,fd.y)){
                    if (btn_edt_mod.getBottom()){
                        btn_edt_mod.setBottomTo(false);
                        btn_del_mod.setBottomTo(true);
                    }
                }
                if (btn_del_mod.isIn(fd.x,fd.y)){
                    if (btn_del_mod.getBottom()){
                        btn_edt_mod.setBottomTo(true);
                        btn_del_mod.setBottomTo(false);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                    touchPoint fm =pointers.get(event.getPointerId(i));
                    if (fm != null) {
                        fm.x = event.getX(i);
                        fm.y = event.getY(i);
                    }
                }
                try {
                    touchPoint tmp1,tmp2;
                    double tmp_dis;
                    if (ce_move_flag){
                        if (mp.isPlaying()){
                            mp.pause();
                        }
                        tmp1=pointers.get(ce_touch_id1);
                        tmp_dis=tmp1.down_x-tmp1.x;
                        ce.Move(tmp_dis);
                        ce.ct.last_chart=0;
                    }
                    if (ce_zoom_flag) {
                        tmp1 = pointers.get(ce_touch_id1);
                        tmp2 = pointers.get(ce_touch_id2);
                        tmp_dis =  Math.sqrt(Math.pow((tmp1.x - tmp2.x), 2) + Math.pow((tmp1.y - tmp2.y), 2));
                        if (tmp_dis > ce_zoom_dis * 2) {
                            ce.reLv(-1);
                            ce_zoom_flag=false;
                            ce_touch_id1 =-1;
                            mp_restart();
                        }
                        if (tmp_dis < ce_zoom_dis / 2) {
                            ce.reLv(1);
                            ce_zoom_flag=false;
                            ce_touch_id1 =-1;
                            mp_restart();
                        }
                        ce.ct.last_chart=0;
                    }
                }catch (Exception e){}
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                touchPoint fu;
                if (pointers.get(pointerIndex)!=null) {
                    fu = pointers.get(pointerIndex);
                }else{
                    fu=new touchPoint();
                }
                fu.x=event.getX(pointerIndex);
                fu.y=event.getY(pointerIndex);
                pointers.remove(pointerId);
                if (ce_zoom_flag) {
                    if (pointerId == ce_touch_id1 || pointerId == ce_touch_id2) {
                        ce_zoom_flag = false;
                        ce_touch_id1 = -1;
                    }
                    mp_restart();
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
                            mp_restart();
                        }
                        ce_move_flag=false;
                        ce_touch_id1=-1;
                    }
                }
                if (btn_r.isIn(fu.x,fu.y)){
                    put(fu.down_time,fu,"R");
                }
                if (btn_s.isIn(fu.x,fu.y)){
                    put(fu.down_time,fu,"S");
                }
                if (btn_t.isIn(fu.x,fu.y)){
                    put(fu.down_time,fu,"T");
                }
                if (btn_x.isIn(fu.x,fu.y)){
                    put(fu.down_time,fu,"X");
                }
                if (btn_mp_ctrl.isIn(fu.x,fu.y)){
                    if (mp.isPlaying()){
                        mp.pause();
                        btn_mp_ctrl.setBottomTo(false);
                    }else{
                        mp.start();
                        btn_mp_ctrl.setBottomTo(true);
                    }
                }
                if (btn_save.isIn(fu.x,fu.y)){
                    charts.saveCharts(ce.ct.chart_key);
                    String n=activity.io.song_name+activity.io.chart_id;
                    activity.io.writeChart(n,charts);
                }
                break;
        }
        return true;
    }
    public void mp_restart(){
        if (btn_mp_ctrl.getBottom())
            mp.start();
    }
    public void put(int now,touchPoint po,String btn){
        if (mp!=null) {
            //if (now - po.down_time >= 5) {
            //  ce.ct.put_long(po.down_time, now, btn);
            //} else {
            ce.ct.put(now, btn, 1);
            //}
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    public void surfaceDestroyed(SurfaceHolder arg0) {//銷毀時被呼叫
        ce.recycle();
        back.recycle();
        frame.recycle();
        btn_del_0.recycle();
        btn_del_1.recycle();
        btn_edt_0.recycle();
        btn_edt_1.recycle();

        play.recycle();
        pause.recycle();
        save.recycle();

        BR.recycle();
        BS.recycle();
        BT.recycle();
        BX.recycle();
        BB.recycle();
        mp.stop();
        mp.release();

        System.gc();
        Constant.Flag=false;
    }
}
