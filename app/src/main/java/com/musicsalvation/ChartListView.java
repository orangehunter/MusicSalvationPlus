package com.musicsalvation;
//

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility" })
public class ChartListView extends SurfaceView
implements SurfaceHolder.Callback{

	boolean deJump=true;

	Bitmap dv_background;
	Bitmap dv_pumen_bar;


    //按鈕區圖片
	Bitmap dv_newbtn_0;
	Bitmap dv_newbtn_1;
	Bitmap dv_heavybtn_0;
	Bitmap dv_heavybtn_1;
	Bitmap dv_easybtn_0;
	Bitmap dv_easybtn_1;
	Bitmap dv_normalbtn_0;
	Bitmap dv_normalbtn_1;
	Bitmap dv_hardbtn_0;
	Bitmap dv_hardbtn_1;
    Bitmap dv_seachbtn_0;
    Bitmap dv_seachbtn_1;

    Bitmap dv_magicmelody;
    Bitmap dv_starroadbtn;
    Bitmap dv_summersoundbtn;
    Bitmap dv_utabtn;
    Bitmap dv_windbtn;



    //按鈕宣告
	Botton newbtn;
	Botton heavybtn;
	Botton easybtn;
    Botton normalbtn;
    Botton hardbtn;
    Botton seachbtn;
    Botton pumenbtn[] = new Botton[5];


    Botton mgbtn;
    Botton starroadbtn;
    Botton ssbtn;
    Botton utabtn;
    Botton windbtn;


	int pointx;//觸控到螢幕的x座標
	int pointy;//觸控到螢幕的y座標

    int btn_wsize = 155;
    int btn_hsize = 100;


    Number num;
	//背景音樂宣告，更改為陣列====================================

	MediaPlayer back_mp;

	//背景音樂宣告------------------------------------

	//音效宣告=======================================
	/*SoundPool sp;
	int btn_se[] = new int[2];*/
	//音效宣告---------------------------------------

	Paint paint;			//畫筆的參考
	int i=0,j=10;
	MainActivity activity;

	public ChartListView(MainActivity mainActivity) {
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
        dv_background=			Graphic.bitSize(LoadBitmap( R.drawable.dv_background), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
        dv_pumen_bar=			Graphic.bitSize(LoadBitmap( R.drawable.dv_pumen_bar), 1235, 100);
        dv_starroadbtn=			Graphic.bitSize(LoadBitmap( R.drawable.dv_starroadbtn), 1235, 100);
		dv_easybtn_0=			Graphic.bitSize(LoadBitmap( R.drawable.dv_easybtn_0), btn_wsize, btn_hsize);
		dv_easybtn_1=			Graphic.bitSize(LoadBitmap( R.drawable.dv_easybtn_1 ),btn_wsize ,btn_hsize );
		dv_hardbtn_0=	        Graphic.bitSize(LoadBitmap( R.drawable.dv_hardbtn_0 ), btn_wsize, btn_hsize);
		dv_hardbtn_1=			Graphic.bitSize(LoadBitmap( R.drawable.dv_hardbtn_1 ),btn_wsize, btn_hsize);
		dv_heavybtn_0=			Graphic.bitSize(LoadBitmap( R.drawable.dv_heavybtn_0), btn_wsize, btn_hsize);
		dv_heavybtn_1 =          Graphic.bitSize(LoadBitmap( R.drawable.dv_heavybtn_1), btn_wsize, btn_hsize);
		dv_newbtn_0 =        Graphic.bitSize(LoadBitmap( R.drawable.dv_newbtn_0), btn_wsize, btn_hsize);
		dv_newbtn_1 =  			Graphic.bitSize(LoadBitmap( R.drawable.dv_newbtn_1), btn_wsize,btn_hsize);
		dv_normalbtn_0  =  			Graphic.bitSize(LoadBitmap( R.drawable.dv_normalbtn_0), btn_wsize,btn_hsize);
        dv_normalbtn_1  =  			Graphic.bitSize(LoadBitmap( R.drawable.dv_normalbtn_1), btn_wsize,btn_hsize);
        dv_seachbtn_0  =  			Graphic.bitSize(LoadBitmap( R.drawable.dv_seachbtn_0), 320,btn_hsize);
        dv_seachbtn_1  =  			Graphic.bitSize(LoadBitmap( R.drawable.dv_seachbtn_1), 320,btn_hsize);

        dv_starroadbtn = Graphic.bitSize(LoadBitmap( R.drawable.dv_starroadbtn), 1235, 100);


		newbtn = 	new Botton(activity, dv_newbtn_1,dv_newbtn_0, 120, 68);
		heavybtn = 	new Botton(activity, dv_heavybtn_1, dv_heavybtn_0, 300, 68);
        easybtn = 	new Botton(activity, dv_easybtn_1, dv_easybtn_0, 480, 68);
        normalbtn = new Botton(activity, dv_normalbtn_1, dv_normalbtn_0, 660, 68);
        hardbtn = 	new Botton(activity, dv_hardbtn_1, dv_hardbtn_0, 840, 68);
        seachbtn = 	new Botton(activity, dv_seachbtn_1, dv_seachbtn_0, 1100, 68);

        pumenbtn[0] = new Botton(activity, dv_starroadbtn, dv_starroadbtn, 640, 190);
        pumenbtn[1] = new Botton(activity, dv_pumen_bar, dv_pumen_bar, 640, 305);
        pumenbtn[2] = new Botton(activity, dv_pumen_bar, dv_pumen_bar, 640, 420);
        pumenbtn[3] = new Botton(activity, dv_pumen_bar, dv_pumen_bar, 640, 535);
        pumenbtn[4] = new Botton(activity, dv_pumen_bar, dv_pumen_bar, 640, 650);

        newbtn.setBottomTo(true);
        heavybtn.setBottomTo(false);
        easybtn.setBottomTo(false);
        normalbtn.setBottomTo(false);
        hardbtn.setBottomTo(false);
        seachbtn.setBottomTo(false);
        pumenbtn[0].setBottomTo(false);
        pumenbtn[1].setBottomTo(false);
        pumenbtn[2].setBottomTo(false);
        pumenbtn[3].setBottomTo(false);
        pumenbtn[4].setBottomTo(false);

        num=new Number(getResources());
        num.setSize(20,26);




        /*
		hidden_flag=false;
		for(int i=0;i<3;i++){
			if(activity.io.level_clear[2][i]){
				hidden_flag=true;
			}
		}
		Log.v("mainView", ""+hidden_flag);
		if(hidden_flag){
			staff =Graphic.bitSize(LoadBitmap(R.drawable.staff), 314, 85);
			startbtm.move(640, 450);
			exitbtm.move(640, 550);
			staffList=	new Bottom(activity,staff,staff,640,650);
		}

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
		*/
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
					SurfaceHolder myholder= ChartListView.this.getHolder();
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
			canvas.drawColor(Color.BLACK);//界面設定為黑色

            Graphic.drawPic(canvas, dv_background, 1280/2, 720/2, 0, 255, paint);//背景

            newbtn.drawBtm(canvas,paint);
            heavybtn.drawBtm(canvas,paint);
            easybtn.drawBtm(canvas,paint);
            normalbtn.drawBtm(canvas,paint);
            hardbtn.drawBtm(canvas,paint);
            seachbtn.drawBtm(canvas,paint);

            pumenbtn[0].drawBtm(canvas,paint);
            pumenbtn[1].drawBtm(canvas, paint);
            pumenbtn[2].drawBtm(canvas, paint);
            pumenbtn[3].drawBtm(canvas, paint);
            pumenbtn[4].drawBtm(canvas, paint);

            num.drawNumberLeftStart(1105,170,activity.io.dl,Number.cv_blue,canvas,paint);
            num.drawNumberLeftStart(1105,215,activity.io.like,Number.cv_red,canvas,paint);




            if(newbtn.getBottom()){

            }
            if(heavybtn.getBottom()){

            }
            if(easybtn.getBottom()){

            }
            if(normalbtn.getBottom()){

            }
            if(hardbtn.getBottom()){

            }
            if(seachbtn.getBottom()){

            }
            if(pumenbtn[0].getBottom()){

            }
            if(pumenbtn[1].getBottom()){

            }
            if(pumenbtn[2].getBottom()){

            }
            if(pumenbtn[3].getBottom()){

            }
            if(pumenbtn[4].getBottom()){

            }



				//paint.reset();


		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		pointx=(int) event.getX();
		pointy=(int) event.getY();

			switch(event.getAction())
			{
			case MotionEvent.ACTION_DOWN://按下
				if(deJump == true) {

                    //標籤列按鈕管理區---------------------------------
                    if(newbtn.isIn(pointx,pointy)){
                        newbtn.setBottomTo(true);
                        heavybtn.setBottomTo(false);
                        easybtn.setBottomTo(false);
                        normalbtn.setBottomTo(false);
                        hardbtn.setBottomTo(false);
                        seachbtn.setBottomTo(false);
                    }
                    if(heavybtn.isIn(pointx,pointy)){
                        newbtn.setBottomTo(false);
                        heavybtn.setBottomTo(true);
                        easybtn.setBottomTo(false);
                        normalbtn.setBottomTo(false);
                        hardbtn.setBottomTo(false);
                        seachbtn.setBottomTo(false);
                    }
                    if(easybtn.isIn(pointx,pointy)){
                        newbtn.setBottomTo(false);
                        heavybtn.setBottomTo(false);
                        easybtn.setBottomTo(true);
                        normalbtn.setBottomTo(false);
                        hardbtn.setBottomTo(false);
                        seachbtn.setBottomTo(false);
                    }
                    if(normalbtn.isIn(pointx,pointy)){
                        newbtn.setBottomTo(false);
                        heavybtn.setBottomTo(false);
                        easybtn.setBottomTo(false);
                        normalbtn.setBottomTo(true);
                        hardbtn.setBottomTo(false);
                        seachbtn.setBottomTo(false);

                    }
                    if(hardbtn.isIn(pointx,pointy)){
                        newbtn.setBottomTo(false);
                        heavybtn.setBottomTo(false);
                        easybtn.setBottomTo(false);
                        normalbtn.setBottomTo(false);
                        hardbtn.setBottomTo(true);
                        seachbtn.setBottomTo(false);

                    }
                    if(seachbtn.isIn(pointx,pointy)){
                        newbtn.setBottomTo(false);
                        heavybtn.setBottomTo(false);
                        easybtn.setBottomTo(false);
                        normalbtn.setBottomTo(false);
                        hardbtn.setBottomTo(false);
                        seachbtn.setBottomTo(true);

                    }
                    //標籤列按鈕管理區=========================================

                    //譜面詳細資訊按鈕管理區-----------------------------------
                    if(pumenbtn[0].isIn(pointx,pointy)){
                        activity.changeView(9);
                    }
                    if(pumenbtn[1].isIn(pointx,pointy)){
                       // activity.changeView(9);

                    }
                    if(pumenbtn[2].isIn(pointx,pointy)){
                        //activity.changeView(9);

                    }
                    if(pumenbtn[3].isIn(pointx,pointy)){
                        //activity.changeView(9);

                    }
                    if(pumenbtn[4].isIn(pointx,pointy)){
                       // activity.changeView(9);

                    }

                }
				deJump = false;
				break;
			case MotionEvent.ACTION_UP://抬起
				if(deJump==false){//防止彈跳part2

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
		dv_background.recycle();
		dv_pumen_bar.recycle();
		dv_newbtn_0.recycle();
		dv_newbtn_1.recycle();
		dv_easybtn_0.recycle();
		dv_easybtn_1.recycle();
		dv_normalbtn_0.recycle();
		dv_normalbtn_1.recycle();
		dv_seachbtn_0.recycle();
		dv_seachbtn_1.recycle();
		dv_hardbtn_0.recycle();
		dv_hardbtn_1.recycle();
        dv_heavybtn_0.recycle();
        dv_heavybtn_1.recycle();

		newbtn.recycle();
		hardbtn.recycle();
        heavybtn.recycle();
        easybtn.recycle();
        normalbtn.recycle();
        hardbtn.recycle();
        seachbtn.recycle();
        pumenbtn[0].recycle();
        pumenbtn[1].recycle();
        pumenbtn[2].recycle();
        pumenbtn[3].recycle();
        pumenbtn[4].recycle();

        num.recycle();
		//back_mp.stop();
		//back_mp.release();
		//sp.release();
		System.gc();
		Constant.Flag=false;
	}


}
