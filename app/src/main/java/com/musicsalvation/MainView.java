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
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility" })
public class MainView extends SurfaceView
implements SurfaceHolder.Callback{

	boolean deJump=true;
	boolean hidden_flag;

	/*Bitmap main_back;
	Bitmap main_back2;
	Bitmap main_back3;*/
    Bitmap mv_background;
	Bitmap storymode;
	Bitmap createmode;
	Bitmap main_title;
	Bitmap main_touchstart;

	Bitmap left_xia;
	//Bitmap right_miku;
	Bitmap staff;

	Bottom storybtm;
	Bottom creatbtm;
	Bottom staffList;



	int mainFlag=0;


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

	public MainView(MainActivity mainActivity) {
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
		/*main_back=			Graphic.bitSize(LoadBitmap( R.drawable.main_back3), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
		main_back2=			Graphic.bitSize(LoadBitmap( R.drawable.main_back2), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
		main_back3=			Graphic.bitSize(LoadBitmap( R.drawable.tellyouworld), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
		*/
        mv_background = Graphic.bitSize(LoadBitmap( R.drawable.mv_background), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
		main_title=			Graphic.bitSize(LoadBitmap( R.drawable.main_title ),730 ,269 );
		main_touchstart=	Graphic.bitSize(LoadBitmap( R.drawable.main_touchstart ), 594, 85);

		left_xia =          Graphic.bitSize(LoadBitmap( R.drawable.xia), 385, 717);
		//right_miku =        Graphic.bitSize(LoadBitmap( R.drawable.mikuv3_img2), 620, 717);
		storymode =  			Graphic.bitSize(LoadBitmap( R.drawable.mv_storymode), 314,85);
		createmode  =  			Graphic.bitSize(LoadBitmap( R.drawable.mv_createmode), 314,85);

		storybtm = 	new Bottom(activity, storymode,storymode, 640, 518);
		creatbtm = 	new Bottom(activity, createmode, createmode, 640, 643);

		hidden_flag=false;
		for(int i=0;i<3;i++){
			if(activity.io.level_clear[2][i]){
				hidden_flag=true;
			}
		}
		//Log.v("mainView", ""+hidden_flag);
		if(hidden_flag){
			staff =Graphic.bitSize(LoadBitmap(R.drawable.staff), 314, 85);
			storybtm.move(640, 450);
			creatbtm.move(640, 550);
			staffList=	new Bottom(activity,staff,staff,640,650);
		}

		//載入音樂=============================================================
		if(!hidden_flag){
			back_mp=MediaPlayer.create(this.getContext(), R.raw.summersounds_instrumental);
		}else{
			back_mp=MediaPlayer.create(this.getContext(), R.raw.summersounds_instrumental);
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
					SurfaceHolder myholder=MainView.this.getHolder();
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

			if(!back_mp.isPlaying()){
				back_mp.prepareAsync();
				back_mp.start();
			}

            Graphic.drawPic(canvas, mv_background, 1280/2, 720/2, 0, 255, paint);//背景
			/*if(!hidden_flag){
				if(apa<= 10){
					a =7;
				} 
				if(apa >240){
					a = -7;
				}
				apa+= a;
				Graphic.drawPic(canvas, main_back, 1280/2, 720/2, 0, 255, paint);//背景
				Graphic.drawPic(canvas, main_back2, 1280/2, 720/2, 0, apa, paint);
			}else{
				Graphic.drawPic(canvas, main_back3, 1280/2, 720/2, 0, 255, paint);//背景
			}*/

			if(mainFlag==0){
				if(i<250)
					i+=j;//透明度參數
				Graphic.drawPic(canvas, main_title, mtx, mty, 0, i, paint);//Title
				mty=Coordinate.AnalogSpeedMove(mty, mty1);
				if(mty==mty1){
					mtoa+=mtoc;
					Graphic.drawPic(canvas, main_touchstart, 1280/2, mtoy, 0, mtoa, paint);
					if(mtoa>235)
						mtoc=-7;
					if(mtoa<20)
						mtoc=7;
				}

				paint.reset();
			}
			if(mainFlag==1){
				Graphic.drawPic(canvas, main_title, mtx, mty, 0, 255, paint);//Title
				mty=Coordinate.AnalogSpeedMove(mty, mty2);

				Graphic.drawPic(canvas, left_xia, mlx, mly, 0, 255, paint);//Left
				mlx=Coordinate.AnalogSpeedMove(mlx, mlx1);

				//Graphic.drawPic(canvas, right_miku, mrx, mry, 0, 255, paint);//Right
				mrx=Coordinate.AnalogSpeedMove(mrx, mrx1);

				/*alpha2+=alpha;
				if(alpha2 > 250){
					alpha = -10;
				}
				if(alpha2 <100){
					alpha = 10;
				}*/
				storybtm.drawBtm(canvas, paint);
				creatbtm.drawBtm(canvas, paint);
				if(hidden_flag){
					staffList.drawBtm(canvas, paint);
				}
			}
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
					sp.play(btn_se[1], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
					mainFlag=1;
				}
				deJump = false;
				break;
			case MotionEvent.ACTION_UP://抬起
				if(deJump==false){//防止彈跳part2

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
					if(storybtm.isIn(pointx, pointy)){
						sp.play(btn_se[0], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
					}
					if(creatbtm.isIn(pointx, pointy)){
						sp.play(btn_se[0], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
						creatbtm.setBottomTo(true);
					}
					if(hidden_flag){
						if(staffList.isIn(pointx, pointy)){
							activity.io.video_select=3;
							activity.changeView(0);
						}
					}
				}
				deJump=false;
				break;
				//.....................................................................................
			case MotionEvent.ACTION_UP://抬起
				if(deJump==false){//防止彈跳part2
					if(storybtm.isIn(pointx, pointy)){//進入地圖畫面
							activity.io.video_select=1;
							activity.changeView(0);
					}

					if(creatbtm.isIn(pointx, pointy)){
                            //TODO 還沒有改進創遊模式
							activity.changeView(8);
					}
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
		/*main_back.recycle();
		main_back2.recycle();
		main_back3.recycle();
		*/
		storymode.recycle();
		createmode.recycle();

		main_title.recycle();
		left_xia.recycle();
		//right_miku.recycle();
		main_touchstart.recycle();
		storybtm.recycle();
		creatbtm.recycle();
		if(hidden_flag){
			staff.recycle();
			staffList.recycle();
		}
		back_mp.stop();
		back_mp.release();
		sp.release();
		System.gc();
		Constant.Flag=false;
	}


}
