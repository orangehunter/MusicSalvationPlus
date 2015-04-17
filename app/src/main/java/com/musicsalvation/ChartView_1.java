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
public class ChartView_1 extends SurfaceView
implements SurfaceHolder.Callback{

	boolean deJump=true;

	Bitmap pv_background;

    //按鈕區圖片
	Bitmap pv_dwbtn_0;
	Bitmap pv_dwbtn_1;
	Bitmap pv_sharebtn;
	Bitmap pv_likebtn_0;
	Bitmap pv_likebtn_1;
	Bitmap pv_pumennum_1;
	Bitmap pv_pumennum_2;
	Bitmap pv_pumennum_3;

    Bitmap coverback;

    //按鈕宣告
	Bottom dwbtn;
	Bottom likebtn;
	Bottom sharebtn;


	int pointx;//觸控到螢幕的x座標
	int pointy;//觸控到螢幕的y座標


    int btn_wsize = 155;
    int btn_hsize = 100;

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

	public ChartView_1(MainActivity mainActivity) {
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
        pv_background=			Graphic.bitSize(LoadBitmap( R.drawable.pv_background), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
        pv_dwbtn_0=			Graphic.bitSize(LoadBitmap( R.drawable.pv_dwbtn_0), btn_wsize, btn_hsize);
		pv_dwbtn_1=			Graphic.bitSize(LoadBitmap( R.drawable.pv_dwbtn_1), btn_wsize, btn_hsize);
		pv_likebtn_0=			Graphic.bitSize(LoadBitmap( R.drawable.pv_likebtn_0 ),btn_wsize ,btn_hsize );
		pv_likebtn_1=	        Graphic.bitSize(LoadBitmap( R.drawable.pv_likebtn_1 ), btn_wsize, btn_hsize);
		pv_sharebtn=			Graphic.bitSize(LoadBitmap( R.drawable.pv_sharebtn ),btn_wsize, btn_hsize);
		pv_pumennum_1=			Graphic.bitSize(LoadBitmap( R.drawable.pv_pumennum_1), btn_wsize, btn_hsize);
		pv_pumennum_2 =          Graphic.bitSize(LoadBitmap( R.drawable.pv_pumennum_2), btn_wsize, btn_hsize);
		pv_pumennum_3 =        Graphic.bitSize(LoadBitmap( R.drawable.pv_pumennum_3), btn_wsize, btn_hsize);

        likebtn = 	new Bottom(activity, pv_likebtn_1, pv_likebtn_0, 655, 620);
		dwbtn = 	new Bottom(activity, pv_dwbtn_1,pv_dwbtn_0, 875, 620);
        sharebtn = 	new Bottom(activity, pv_sharebtn, pv_sharebtn, 1095, 620);







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
					SurfaceHolder myholder= ChartView_1.this.getHolder();
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

            Graphic.drawPic(canvas, pv_background, 1280/2, 720/2, 0, 255, paint);//背景

            dwbtn.drawBtm(canvas,paint);
            likebtn.drawBtm(canvas, paint);
            sharebtn.drawBtm(canvas,paint);


            if(dwbtn.getBottom()){

            }
            if(likebtn.getBottom()){

            }
            if(sharebtn.getBottom()){

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


                    if(likebtn.isIn(pointx,pointy)){
                        if(likebtn.getBottom()) {
                            likebtn.setBottomTo(false);
                        }
                        else
                            likebtn.setBottomTo(true);
                    }
                    if(dwbtn.isIn(pointx,pointy)){

                    }
                    if(sharebtn.isIn(pointx,pointy)){

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
		pv_background.recycle();
		pv_pumennum_3.recycle();
		pv_pumennum_2.recycle();
		pv_pumennum_1.recycle();
		pv_sharebtn.recycle();
		pv_likebtn_1.recycle();
		pv_likebtn_0.recycle();
		pv_dwbtn_0.recycle();
		pv_dwbtn_1.recycle();
		dwbtn.recycle();
		likebtn.recycle();
		sharebtn.recycle();


		//back_mp.stop();
		//back_mp.release();
		//sp.release();
		System.gc();
		Constant.Flag=false;
	}


}
