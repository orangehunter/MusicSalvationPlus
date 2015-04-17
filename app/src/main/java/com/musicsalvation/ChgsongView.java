package com.musicsalvation;
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
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility" })
public class ChgsongView extends SurfaceView
implements SurfaceHolder.Callback{

	boolean deJump=true;
	boolean hidden_flag;

	Bitmap up;
    Bitmap down;
    Bitmap bg;
    Bitmap rl;










    int xmov =0;
//---------------------------------------
    //特效
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




    //--------------------------------------
	int mainFlag=0;

	boolean toEditView=false;

	int pointx;//觸控到螢幕的x座標
	int pointy;//觸控到螢幕的y座標
	int apa=10;
	int a=0;

	int mtx=640;
	int mty=-200;
	int mty1=360;
	int mty2=200;

	int mtoy=600;
	int mtoa=0;
	int mtoc=20;

	int mlx=-220;
	int mlx1=220-60;
	int mly=700;

	int mrx=1280+333;
	int mrx1=1280-333+190;
	int mry=700;

	int alpha = 5;
	int alpha2 = 0;

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

 star=Graphic.bitSize(LoadBitmap( R.drawable.fv_star),Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);

        opc[0]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_1),Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
        opc[1]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_2),Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
        opc[2]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_3),Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
        opc[3]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_4),Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
        opc[4]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_5),Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
        opc[5]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_6),Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
        opc[6]= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_7),Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);



        bg_0= Graphic.bitSize(LoadBitmap( R.drawable.fv_background),Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
        bg_1= Graphic.bitSize(LoadBitmap( R.drawable.fv_background_part),Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);

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

        add_btn=new Bottom(activity,add_0,add_0,1230, 44);
        set_btn=new Bottom(activity, set_0, set_0,56, 44);
        pomeChg_1_btn=new Bottom(activity,  pomeChg_1_1,  pomeChg_1_0,189, 653);
        pomeChg_2_btn=new Bottom(activity,  pomeChg_2_1,  pomeChg_2_0,282, 653);
        pomeChg_3_btn=new Bottom(activity,  pomeChg_3_1,  pomeChg_3_0,375, 653);
        play_btn=new Bottom(activity,  play_0,  play_0,557, 408);
        shar_btn=new Bottom(activity,  play_0,  shar_0,628, 294);
        cart_btn=new Bottom(activity,   play_0,  cart_0,628, 519);

        up_btn=new Bottom(activity,   play_0,  up_0,664, 160);
       down_btn=new Bottom(activity,   play_0,  down_0,667, 665);


        pomeChg_1_btn.setBottomTo(true);
        pomeChg_2_btn.setBottomTo(false);
        pomeChg_3_btn.setBottomTo(false);

		//載入音樂=============================================================
		if(!hidden_flag){
			back_mp=MediaPlayer.create(this.getContext(), R.raw.tell_your_world_piano);

		}else{
			back_mp=MediaPlayer.create(this.getContext(), R.raw.tellpiano);
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
            Graphic.drawPic(canvas,  star, 1280/2+sppp*10, 720/2-sppp*5, 0, 255, paint);//星星
            Graphic.drawPic(canvas,  star, 1280/2+sppp*3, 716/2-sppp*3, 0, 255, paint);//星星
            Graphic.drawPic(canvas,  star, 1280/2+sppp, 712/2-sppp*8, 0, 255, paint);//星星
            Graphic.drawPic(canvas,  bg_1, 1280/2, 720/2, 0, 255, paint);//背景在此-02

            sppp++;
            if(sppp>=12){
                sppp=0;
            }
            if(sppp>=10) {
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

            play_btn.drawBtm(canvas, paint);
            shar_btn.drawBtm(canvas, paint);
            cart_btn.drawBtm(canvas, paint);

            up_btn.drawBtm(canvas, paint);
            down_btn.drawBtm(canvas, paint);






        }
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		pointx=(int) event.getX();
		pointy=(int) event.getY();
		if(mainFlag==0){
			switch(event.getAction())
			{
			case MotionEvent.ACTION_DOWN://按下
				if(deJump == true){

                    if( pomeChg_1_btn.isIn(pointx, pointy)){
                        pomeChg_1_btn.setBottomTo(true);
                        pomeChg_2_btn.setBottomTo(false);
                        pomeChg_3_btn.setBottomTo(false);

                    }

                    if( pomeChg_2_btn.isIn(pointx, pointy)){
                        pomeChg_2_btn.setBottomTo(true);
                        pomeChg_1_btn.setBottomTo(false);
                        pomeChg_3_btn.setBottomTo(false);
                    }

                    if( pomeChg_3_btn.isIn(pointx, pointy)){
                        pomeChg_3_btn.setBottomTo(true);
                        pomeChg_2_btn.setBottomTo(false);
                        pomeChg_1_btn.setBottomTo(false);
                    }

                    if(  play_btn.isIn(pointx, pointy)){
                        play_btn.setBottomTo(true);
                    }
                    if( shar_btn.isIn(pointx, pointy)){
                        shar_btn.setBottomTo(true);
                    }
                    if( cart_btn.isIn(pointx, pointy)){
                        cart_btn.setBottomTo(true);
                    }

                    if( up_btn.isIn(pointx, pointy)){
                        up_btn.setBottomTo(true);
                    }if( down_btn.isIn(pointx, pointy)){
                        down_btn.setBottomTo(true);
                    }


				}
				deJump = false;
				break;
			case MotionEvent.ACTION_UP://抬起
				if(deJump==false){//防止彈跳part2


                    if(  play_btn.isIn(pointx, pointy)){
                        play_btn.setBottomTo(false);
                    }
                    if( shar_btn.isIn(pointx, pointy)){
                        shar_btn.setBottomTo(false);
                    }
                    if( cart_btn.isIn(pointx, pointy)){
                        cart_btn.setBottomTo(false);
                    }


                    if( up_btn.isIn(pointx, pointy)){
                        up_btn.setBottomTo(false);
                    }if( down_btn.isIn(pointx, pointy)){
                        down_btn.setBottomTo(false);
                    }





				}
				deJump = true;
				break;
			}
		}
		if(mainFlag==1){
			switch(event.getAction())
			{
			//......................................................................................
			case MotionEvent.ACTION_DOWN://按下
				if(deJump==true){//防止彈跳part1

				}
				deJump=false;
				break;
				//.....................................................................................
			case MotionEvent.ACTION_UP://抬起
				if(deJump==false){//防止彈跳part2


				}
				deJump=true;
				break;
			}
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






    }


}
