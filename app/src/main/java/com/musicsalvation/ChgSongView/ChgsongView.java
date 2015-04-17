package com.musicsalvation.ChgSongView;
//

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.musicsalvation.Bottom;
import com.musicsalvation.Constant;
import com.musicsalvation.Graphic;
import com.musicsalvation.MainActivity;
import com.musicsalvation.R;
import com.musicsalvation.Constant;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility" })
public class ChgsongView extends SurfaceView
        implements SurfaceHolder.Callback{

    boolean deJump=true;
    boolean hidden_flag;
    //---------------------------------------
    //特效
    songWheel songwheel;
    Bitmap opc[] = new Bitmap [7];
    int spp =0 ;
    int sppp=0;
    Bitmap star;
    //特效



    Bitmap bg_0;//背景
    Bitmap bg_1;//背景
    Bitmap a1;//整張圖

    Bitmap titl;//標題
    Bitmap sondata;//歌曲資訊
    Bitmap pomecon;//普面框

    Bitmap set_0;//設定
    Bitmap set_1;//設定
    Bottom set_btn;//設定按鈕

    Bitmap pomeChg_1_0;//1號譜面
    Bitmap pomeChg_1_1;//1號譜面
    Bottom pomeChg_1_btn;//1號譜面按鈕

    Bitmap pomeChg_2_0;//2號譜面
    Bitmap pomeChg_2_1;//2號譜面
    Bottom pomeChg_2_btn;//2號譜面按鈕

    Bitmap pomeChg_3_0;//3號譜面
    Bitmap pomeChg_3_1;//3號譜面
    Bottom pomeChg_3_btn;//3號譜面按鈕

    Bitmap play_0;//遊玩按鈕
    Bitmap play_1;//遊玩按鈕
    Bottom play_btn;//遊玩按鈕

    Bitmap shar_0;//分享按鈕
    Bitmap shar_1;//分享按鈕
    Bottom shar_btn;//分享按鈕

    Bitmap cart_0;//創作按鈕
    Bitmap cart_1;//創作按鈕
    Bottom cart_btn;//創作按鈕

    Bitmap add_0;//新增按鈕
    Bitmap add_1;//新增按鈕
    Bottom add_btn;//新增按鈕

    Bitmap up_0;
    Bitmap up_1;
    Bottom up_btn;

    Bitmap down_0;
    Bitmap down_1;
    Bottom down_btn;

    Bitmap sonbar;

    String zo_ch;//作曲
    String zo_si;//作詞
    String zo_vocal;
    String zo_cover;
    String zo_carter;//作者
    String zo_bpm;
    String zo_score;
    String zo_rank;

    Bitmap dacon;
    boolean dacon_flag=false;

    Bitmap sicon_1;
    Bitmap sicon_2;

    Bottom sicon_1_btn;
    Bottom sicon_2_btn;






    //--------------------------------------
    int mainFlag=0;

    boolean toEditView=false;

    int pointx;//觸控到螢幕的x座標
    int pointy;//觸控到螢幕的y座標


    //背景音樂宣告，更改為陣列====================================

    MediaPlayer back_mp;

    //背景音樂宣告------------------------------------

    //音效宣告=======================================
    SoundPool sp;
    int btn_se[] = new int[2];
    //音效宣告---------------------------------------

    Paint paint;			//畫筆的參考
    int i=0,j=10;
    MainActivity activity;

    public ChgsongView(MainActivity mainActivity) {
        super(mainActivity);
        this.activity = mainActivity;
        this.getHolder().addCallback(this);//設定生命周期回調接口的實現者


    }

    public Bitmap LoadBitmap(int r){
        return BitmapFactory.decodeResource(getResources(), r);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        paint = new Paint();//建立畫筆
        paint.setAntiAlias(true);//開啟抗鋸齒
        if (activity.io.chosen_song!=null) {
            boolean has = false;
            if (activity.io.song_list!=null) {
                for (int i = 0; i < activity.io.song_list.length(); i++) {
                    if (activity.io.song_list.optString(i).equals(activity.io.turnUriToName(activity.io.chosen_song))) {
                        has = true;
                        break;
                    }
                }
            }else {
                activity.io.song_list=new JSONArray();
            }
            if (!has) {
                activity.io.song_list.put(activity.io.turnUriToName(activity.io.chosen_song));
                if (activity.io.uri_list==null){
                    activity.io.uri_list=new JSONArray();
                }
                activity.io.uri_list=new JSONArray();
                activity.io.uri_list.put(activity.io.chosen_song);
                activity.io.chosen_song=null;

            }
        }
        songwheel=new songWheel(activity);
        star= Graphic.bitSize(LoadBitmap(R.drawable.fv_star), 1280, 720);

        opc[0]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_1),1280, 720);
        opc[1]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_2),1280, 720);
        opc[2]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_3),1280, 720);
        opc[3]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_4),1280, 720);
        opc[4]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_5),1280, 720);
        opc[5]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_6),1280, 720);
        opc[6]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_7),1280, 720);



        bg_0= Graphic.bitSize(LoadBitmap( R.drawable.fv_background),1280, 720);
        bg_1= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_part),1280, 720);

        titl=Graphic.bitSize(LoadBitmap( R.drawable.fv_title),1280,94);
        add_0= Graphic.bitSize(LoadBitmap( R.drawable.fv_addbtn),99,82);
        set_0=Graphic.bitSize(LoadBitmap( R.drawable.fv_setbtn),112,82);
        sondata=Graphic.bitSize(LoadBitmap( R.drawable.fv_imforboard),451,499);
        pomecon=Graphic.bitSize(LoadBitmap( R.drawable.fv_pumenbar),456,121);

        pomeChg_1_0=Graphic.bitSize(LoadBitmap( R.drawable.fv_pumen1_0),88,88);
        pomeChg_1_1=Graphic.bitSize(LoadBitmap( R.drawable.fv_pumen1_1),88,88);

        pomeChg_2_0=Graphic.bitSize(LoadBitmap( R.drawable.fv_pumen2_0),88,88);
        pomeChg_2_1=Graphic.bitSize(LoadBitmap( R.drawable.fv_pumen2_1),88,88);

        pomeChg_3_0=Graphic.bitSize(LoadBitmap( R.drawable.fv_pumen3_0),88,88);
        pomeChg_3_1=Graphic.bitSize(LoadBitmap( R.drawable.fv_pumen3_1),88,88);

        play_0=Graphic.bitSize(LoadBitmap( R.drawable.fv_playbtn),144,144);

        shar_0=Graphic.bitSize(LoadBitmap( R.drawable.fv_sharebtn),122,122);

        cart_0=Graphic.bitSize(LoadBitmap( R.drawable.fv_createbtn),122,122);

        up_0=Graphic.bitSize(LoadBitmap( R.drawable.fv_up_arrow),129,139);
        down_0=Graphic.bitSize(LoadBitmap( R.drawable.fv_down_arrow),129,139);

        sonbar=Graphic.bitSize(LoadBitmap( R.drawable.fv_choosebar),810,369);

        dacon=Graphic.bitSize(LoadBitmap( R.drawable.dv_addback),450,210);
        sicon_1=Graphic.bitSize(LoadBitmap( R.drawable.dv_chartenter_btn),392,74);
        sicon_2=Graphic.bitSize(LoadBitmap( R.drawable.dv_songadd),392,74);

        add_btn=new Bottom(activity,play_0,add_0,1230, 44);
        set_btn=new Bottom(activity, set_0, set_0,56, 44);
        pomeChg_1_btn=new Bottom(activity,  pomeChg_1_1,  pomeChg_1_0,189, 653);
        pomeChg_2_btn=new Bottom(activity,  pomeChg_2_1,  pomeChg_2_0,282, 653);
        pomeChg_3_btn=new Bottom(activity,  pomeChg_3_1,  pomeChg_3_0,375, 653);
        play_btn=new Bottom(activity,  play_0,  play_0,557, 408);
        shar_btn=new Bottom(activity,  play_0,  shar_0,628, 294);
        cart_btn=new Bottom(activity,   play_0,  cart_0,628, 519);

        up_btn=new Bottom(activity,   play_0,  up_0,664, 160);
        down_btn=new Bottom(activity,   play_0,  down_0,667, 665);

        sicon_1_btn=new Bottom(activity,   sicon_1, sicon_1,1025, 145);
        sicon_2_btn=new Bottom(activity,   sicon_2, sicon_2,1025, 235);

        pomeChg_1_btn.setBottomTo(true);
        pomeChg_2_btn.setBottomTo(false);
        pomeChg_3_btn.setBottomTo(false);

        //載入音樂=============================================================
        if(!hidden_flag){
            back_mp=MediaPlayer.create(this.getContext(), R.raw.cameras);

        }else{
            back_mp=MediaPlayer.create(this.getContext(), R.raw.cameras);
        }
        back_mp.setVolume(activity.io.mp_Voiume, activity.io.mp_Voiume);
        back_mp.setLooping(true);
        back_mp.start();

        sp=new SoundPool(4, AudioManager.STREAM_MUSIC, 5);
        btn_se[0] = sp.load(activity, R.raw.start, 1);
        btn_se[1] = sp.load(activity, R.raw.title_touch, 1);
        //載入音樂-------------------------------------------------------------


        Constant.Flag=true;
        new Thread(){
            @SuppressLint("WrongCall")
            public void run()
            {
                while(Constant.Flag){
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SurfaceHolder myholder=ChgsongView.this.getHolder();
                    Canvas canvas = myholder.lockCanvas();//取得畫布
                    onDraw(canvas);
                    if(canvas != null){
                        try {
                            myholder.unlockCanvasAndPost(canvas);
                        }catch (Exception e){

                        }
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
            canvas.clipRect(new Rect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HIGHT));//只在螢幕範圍內繪制圖片
            canvas.drawColor(Color.GRAY);//界面設定為黑色


            Graphic.drawPic(canvas,  bg_0, 1280/2, 720/2, 0, 255, paint);//背景在此
            Graphic.drawPic(canvas,  star, 1280/2+sppp*12, 720/2-sppp*6, 0, 255, paint);//星星
            Graphic.drawPic(canvas,  star, 1280/2+sppp*6, 716/2-sppp*4, 0, 255, paint);//星星
            Graphic.drawPic(canvas,  star, 1280/2+sppp, 712/2-sppp*10, 0, 255, paint);//星星
            Graphic.drawPic(canvas,  bg_1, 1280/2, 720/2, 0, 255, paint);//背景在此-02

            sppp++;
            if(sppp>=10){
                sppp=0;
            }
            if(sppp>=9) {
                spp++;
                if (spp >= 7) {
                    spp = 0;
                }
            }


            Graphic.drawPic(canvas, opc[spp], 1280/2, 720/2, 0, 255, paint);

            Graphic.drawPic(canvas, titl, 640, 47, 0, 255, paint);//標題在此
            Graphic.drawPic(canvas, sondata, 238, 336, 0, 255, paint);//資訊在此
            Graphic.drawPic(canvas, pomecon, 230, 652, 0, 255, paint);


            add_btn.drawBtm(canvas, paint);//加歌按鈕
            set_btn.drawBtm(canvas, paint);//設定按鈕
            pomeChg_1_btn.drawBtm(canvas, paint);//普面1 按鈕
            pomeChg_2_btn.drawBtm(canvas, paint);//普面2 按鈕
            pomeChg_3_btn.drawBtm(canvas, paint);//普面3 按鈕

            Graphic.drawPic(canvas, sonbar, 875, 407, 0, 255, paint);
            play_btn.drawBtm(canvas, paint);
            shar_btn.drawBtm(canvas, paint);
            cart_btn.drawBtm(canvas, paint);

            up_btn.drawBtm(canvas, paint);
            down_btn.drawBtm(canvas, paint);

            Graphic.drawText(canvas,zo_si,238,150,Color.BLACK,50,paint);
            Graphic.drawText(canvas,zo_ch,238,200,Color.BLACK,50,paint);
            Graphic.drawText(canvas,zo_vocal,238,250,Color.BLACK,50,paint);
            Graphic.drawText(canvas,zo_cover,238,300,Color.BLACK,50,paint);
            Graphic.drawText(canvas,zo_carter,238,350,Color.BLACK,50,paint);
            Graphic.drawText(canvas,zo_bpm,238,405,Color.BLACK,50,paint);
            Graphic.drawText(canvas,zo_score,238,460,Color.BLACK,50,paint);
            //Graphic.drawText(canvas,"test測試2",238,510,Color.BLACK,50,paint);
            Graphic.drawText(canvas,zo_rank,238,560 ,Color.BLACK,50,paint);
            songwheel.draw(canvas,paint);
            if(dacon_flag) {
                Graphic.drawPic(canvas, dacon, 1020, 185, 0, 255, paint);

                sicon_1_btn.drawBtm(canvas, paint);
                sicon_2_btn.drawBtm(canvas, paint);
            }


        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        pointx = (int) event.getX();
        pointy = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下
                if (deJump == true) {
                    if (add_btn.isIn(pointx, pointy)) {
                        dacon_flag = !dacon_flag;
                        sp.play(btn_se[0], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
                    }
                    //
                    //TODO 做到一半
                    if (sicon_1_btn.isIn(pointx, pointy)&&dacon_flag) {
                        activity.changeView(10);
                        sp.play(btn_se[0], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
                    }
                    if (sicon_2_btn.isIn(pointx, pointy)&&dacon_flag) {
                        activity.changeView(7);
                        sp.play(btn_se[0], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
                    }


                }
                if (pomeChg_1_btn.isIn(pointx, pointy)) {
                    pomeChg_1_btn.setBottomTo(true);
                    pomeChg_2_btn.setBottomTo(false);
                    pomeChg_3_btn.setBottomTo(false);
<<<<<<< HEAD
                    activity.io.chart_id=1;
=======
                    sp.play(btn_se[0], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
>>>>>>> origin/RRR

                }

                if (pomeChg_2_btn.isIn(pointx, pointy)) {
                    pomeChg_2_btn.setBottomTo(true);
                    pomeChg_1_btn.setBottomTo(false);
                    pomeChg_3_btn.setBottomTo(false);
<<<<<<< HEAD
                    activity.io.chart_id=2;
=======
                    sp.play(btn_se[0], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
>>>>>>> origin/RRR
                }

                if (pomeChg_3_btn.isIn(pointx, pointy)) {
                    pomeChg_3_btn.setBottomTo(true);
                    pomeChg_2_btn.setBottomTo(false);
                    pomeChg_1_btn.setBottomTo(false);
<<<<<<< HEAD
                    activity.io.chart_id=3;
=======
                    sp.play(btn_se[0], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
>>>>>>> origin/RRR
                }

                if (play_btn.isIn(pointx, pointy)) {
                    play_btn.setBottomTo(true);
                    sp.play(btn_se[0], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
                }
                if (shar_btn.isIn(pointx, pointy)) {
                    shar_btn.setBottomTo(true);
                    sp.play(btn_se[0], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
                }
                if (cart_btn.isIn(pointx, pointy)) {
                    cart_btn.setBottomTo(true);
                    sp.play(btn_se[0], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
                }

                if (up_btn.isIn(pointx, pointy)) {
                    up_btn.setBottomTo(true);
                    sp.play(btn_se[0], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
                }
                if (down_btn.isIn(pointx, pointy)) {
                    down_btn.setBottomTo(true);
                    sp.play(btn_se[0], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
                }

                deJump = false;
                break;

            case MotionEvent.ACTION_UP://抬起
                if (deJump == false) {//防止彈跳part2


                    if (play_btn.isIn(pointx, pointy)) {
                        play_btn.setBottomTo(false);
                        activity.io.level=3;
                        activity.io.chosen_int=songwheel.getSelected();
                        activity.io.video_select = 2;
                        activity.changeView(0);

                        //TODO 遊玩畫面

                    }
                    if (shar_btn.isIn(pointx, pointy)) {
                        shar_btn.setBottomTo(false);
                        activity.shareTo("FB", "google.com.tw", "FB", sondata);
                        //TODO 柏仰的網址分享

                    }
                    if (cart_btn.isIn(pointx, pointy)) {
                        cart_btn.setBottomTo(false);
                        activity.io.chosen_int=songwheel.getSelected();
                        activity.io.song_uri= Uri.parse(activity.io.uri_list.optString(activity.io.chosen_int));
                        activity.changeView(6);
                    }


                    if (up_btn.isIn(pointx, pointy)) {
                        up_btn.setBottomTo(false);
                        songwheel.change(1);
                    }
                    if (down_btn.isIn(pointx, pointy)) {
                        down_btn.setBottomTo(false);
                        songwheel.change(-1);
                    }

                }
                deJump = true;
                break;
        }
        return true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {

    }

    public void surfaceDestroyed(SurfaceHolder arg0) {//銷毀時被呼叫

        back_mp.stop();
        back_mp.release();
        sp.release();
        System.gc();
        Constant.Flag=false;

        bg_0.recycle();
        bg_1.recycle();

        titl.recycle();//標題
        sondata.recycle();//歌曲資訊
        pomecon.recycle();//普面框

        set_0.recycle();//設定



        pomeChg_1_0.recycle();//1號譜面
        pomeChg_1_1.recycle();//1號譜面
        pomeChg_2_0.recycle();//2號譜面
        pomeChg_2_1.recycle();//2號譜面
        pomeChg_3_0.recycle();//3號譜面
        pomeChg_3_1.recycle();//3號譜面
        play_0.recycle();//遊玩按鈕
        shar_0.recycle();//分享按鈕

        cart_0.recycle();//創作按鈕



        add_0.recycle();//新增按鈕

        up_0.recycle();
        down_0.recycle();

        star.recycle();

        songwheel.recycle();

         dacon.recycle();
        

         sicon_1.recycle();
         sicon_2.recycle();

         sicon_1_btn.recycle();
         sicon_2_btn.recycle();
    }


}
