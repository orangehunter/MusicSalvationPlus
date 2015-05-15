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
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility" })
public class MainView_2 extends SurfaceView
implements SurfaceHolder.Callback{

	boolean deJump=true;
	boolean hidden_flag;



    //特效
    int spp=0;


	int mbx=1000000;//白棒飛行速度 "yo"
	int BBB_S=300;
	int BBB_S_B=255;

	int bar_m=1900;

	int x =0;

	int val_y=0;
	int val_y_s=1;
    Bitmap wrmax[] = new Bitmap[5];
	Bitmap wrmaxg[] = new Bitmap[5];
    Bitmap main2_fs_sp[] =new Bitmap[10];

	Bitmap big_bg ;
	Bitmap ms_cl[] = new Bitmap[4];
	Bitmap w_bar ;
	Bitmap B_bar ;
	Bitmap BBB;
	Bitmap ww_bar ;
	Bitmap val ;


    int wr_sp=0;

    //特效
    Bitmap mv_background;
    Bitmap mv_background_2;
	Bitmap storymode;
	Bitmap createmode;
	Bitmap main_title;
	Bitmap main_touchstart;

	Bitmap left_xia;

	Bitmap staff;

	Botton storybtm;
	Botton creatbtm;
	Botton staffList;



	int mainFlag=0;


	int pointx;//觸控到螢幕的x座標
	int pointy;//觸控到螢幕的y座標


	int mtx=640;
	int mty=-200;
	int mty1=360;

	int mtoa=0;
	int mtoc=20;



	int point[]=new int[9];
	int tmp_alpha[]=new  int[9];
	double ta=0;
	int target=0;

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

	public MainView_2(MainActivity mainActivity) {
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


        mv_background = Graphic.bitSize(LoadBitmap( R.drawable.mv_background), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
        mv_background_2= Graphic.bitSize(LoadBitmap( R.drawable.mv_background_2), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
		main_title=			Graphic.bitSize(LoadBitmap( R.drawable.main_title ),730 ,269 );
		main_touchstart=	Graphic.bitSize(LoadBitmap( R.drawable.main_touchstart ), 594, 85);

		left_xia =          Graphic.bitSize(LoadBitmap( R.drawable.xia), 385, 717);

		storymode =  			Graphic.bitSize(LoadBitmap( R.drawable.gio), 314,85);
		createmode  =  			Graphic.bitSize(LoadBitmap( R.drawable.tron), 314,85);

        wrmax[0] = Graphic.bitSize(LoadBitmap( R.drawable.main2_wr_0), 1280,720);
        wrmax[1] = Graphic.bitSize(LoadBitmap( R.drawable.main2_wr_1), 1280,720);
        wrmax[2] = Graphic.bitSize(LoadBitmap( R.drawable.main2_wr_2), 1280,720);
        wrmax[3] = Graphic.bitSize(LoadBitmap(R.drawable.main2_wr_3), 1280, 720);
        wrmax[4] = Graphic.bitSize(LoadBitmap(R.drawable.main2_wr_4), 1280, 720);

		wrmaxg[0] = Graphic.bitSize(LoadBitmap( R.drawable.main2_wr_0g), 1280,720);
		wrmaxg[1] = Graphic.bitSize(LoadBitmap( R.drawable.main2_wr_1g), 1280,720);
		wrmaxg[2] = Graphic.bitSize(LoadBitmap( R.drawable.main2_wr_2g), 1280,720);
		wrmaxg[3] = Graphic.bitSize(LoadBitmap(R.drawable.main2_wr_3g), 1280, 720);
		wrmaxg[4] = Graphic.bitSize(LoadBitmap(R.drawable.main2_wr_4g), 1280, 720);


		main2_fs_sp[0]= Graphic.bitSize(LoadBitmap(R.drawable.touch_0), 50, 50);
		main2_fs_sp[1]= Graphic.bitSize(LoadBitmap(R.drawable.touch_0), 50, 50);
		main2_fs_sp[2]= Graphic.bitSize(LoadBitmap(R.drawable.touch_1), 50, 50);
		main2_fs_sp[3]= Graphic.bitSize(LoadBitmap(R.drawable.touch_2), 50, 50);
		main2_fs_sp[4]= Graphic.bitSize(LoadBitmap(R.drawable.touch_3), 50, 50);

		main2_fs_sp[5]= Graphic.bitSize(LoadBitmap(R.drawable.touch_4), 50, 50);
		main2_fs_sp[6]= Graphic.bitSize(LoadBitmap(R.drawable.touch_5), 50, 50);
		main2_fs_sp[7]= Graphic.bitSize(LoadBitmap(R.drawable.touch_0), 50, 50);
		main2_fs_sp[8]= Graphic.bitSize(LoadBitmap(R.drawable.touch_0), 50, 50);



		big_bg= Graphic.bitSize(LoadBitmap(R.drawable.black_bg), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);

		ww_bar= Graphic.bitSize(LoadBitmap(R.drawable.tati_gg), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);



		val= Graphic.bitSize(LoadBitmap(R.drawable.val), 400,288);

		w_bar= Graphic.bitSize(LoadBitmap( R.drawable.w_bar), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
		BBB=Graphic.bitSize(LoadBitmap( R.drawable.fv_star_s), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
		B_bar=Graphic.bitSize(LoadBitmap( R.drawable.tati_g), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
		ms_cl[0] = Graphic.bitSize(LoadBitmap( R.drawable.ms_b1), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
	    ms_cl[1] = Graphic.bitSize(LoadBitmap( R.drawable.ms_b2), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
		ms_cl[2] = Graphic.bitSize(LoadBitmap( R.drawable.ms_b3), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
		ms_cl[3] = Graphic.bitSize(LoadBitmap( R.drawable.ms_b4), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);



		for(int i=0;i<9;i++){
			point[i]=(1280/5)+((1280/5*4-1280/5)/9*(i+1));
		}

		storybtm = 	new Botton(activity, storymode,storymode, 640, 518);
		creatbtm = 	new Botton(activity, createmode, createmode, 640, 643);




		hidden_flag=false;
		for(int i=0;i<3;i++){
            try {
                if (activity.io.level_clear[2][i]) {
                    hidden_flag = true;
                }
            }catch (Exception e){}
		}

		if(hidden_flag){
			staff =Graphic.bitSize(LoadBitmap(R.drawable.staff), 314, 85);
			storybtm.move(640, 450);
			creatbtm.move(640, 550);
			staffList=	new Botton(activity,staff,staff,640,650);
		}

		//載入音樂=============================================================
		if(!hidden_flag){

			back_mp=MediaPlayer.create(this.getContext(), R.raw.club_thump);

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
					SurfaceHolder myholder=MainView_2.this.getHolder();
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
		if(canvas!=null) {
			super.onDraw(canvas);
			canvas.clipRect(new Rect(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HIGHT));//只在螢幕範圍內繪制圖片
			canvas.drawColor(Color.WHITE);//界面設定為黑色

			if (!back_mp.isPlaying()) {
				back_mp.prepareAsync();
				back_mp.start();
			}
			if(mbx<657) {
				if (mainFlag == 0) {
					Graphic.drawPic(canvas, mv_background, 1280 / 2, 720 / 2, 0, 200, paint);//背景
				}
				if (mainFlag == 1) {
					Graphic.drawPic(canvas, mv_background, 1280 / 2, 720 / 2, 0, 215, paint);//背景
				}

			}

			spp = spp + 40;

			if (spp >= 255) {
				spp = 0;

			}

			ta += 0.8;


			mbx = Coordinate.AnalogSpeedMove(mbx, 640);
			if (ta >= 1) {
				ta = 0;
				target++;
				target = target % 9;

				x = x + 1;

				if (x >= 4) {
					x = 0;

				}

				mtoa += mtoc;

				wr_sp = wr_sp + 1;

				if (wr_sp >= 4) {
					wr_sp = 0;
				}

			}
			if (bar_m<=641) {
				int now, last, next;
				now = target;
				if (now - 1 == -1) {
					last = 7;
				} else {
					last = now - 1;
				}
				next = (now + 1) % 8;
				for (int i = 0; i < 9; i++) {
					if (i == last || i == next) {
						tmp_alpha[i] = 256 / 2;
					} else if (i == now) {
						tmp_alpha[i] = 255;
					} else {
						tmp_alpha[i] =80;
					}
				}
			}
			Graphic.drawPic(canvas, B_bar, 1280 / 2, 720 / 2, 0, 255, paint);




			if (mainFlag == 0) {
				if (i < 250)
					i += j;//透明度參數


				for (int i = 0; i < 9; i++) {

					Graphic.drawPic(canvas, main2_fs_sp[i], point[i], 585, 0, tmp_alpha[i], paint);
				}

				if (bar_m>=641) {
					Graphic.drawPic(canvas, wrmax[2], mtx, mty, 0, mtoa, paint);//Title
				} else {

					Graphic.drawPic(canvas, wrmax[wr_sp], mtx, mty, 0, mtoa, paint);//Title
				}


				mty = Coordinate.AnalogSpeedMove(mty, mty1);
				if (mty == mty1) {




					if (mtoa > 255)
						mtoc = -15;
					if (mtoa < 135)
						mtoc = 15;
				}

				paint.reset();
			}
			if (mainFlag == 1) {


				Graphic.drawPic(canvas, wrmaxg[4], mtx, mty, 0, i, paint);//Titlev
				Graphic.drawPic(canvas, val, 1050, 580+val_y, 0, 255, paint);//620-580
				val_y+=val_y_s;
				if(val_y>35){
					val_y_s=-4;
				}


				if(val_y<2){
					val_y_s=12;
				}


				storybtm.drawBtm(canvas, paint);
				creatbtm.drawBtm(canvas, paint);
				if (hidden_flag) {
					staffList.drawBtm(canvas, paint);
				}
			}

			if (mbx >= 730) {


				Graphic.drawPic(canvas, big_bg, 1280 / 2, 720 / 2, 0, 255, paint);//black bg

				Graphic.drawPic(canvas, w_bar, mbx, 360, 0, BBB_S_B, paint);//b
				if (mbx<730+50){
					BBB_S_B=Coordinate.AnalogSpeedMove(BBB_S_B, 0);
				}
				Graphic.drawPic(canvas, ms_cl[x], 640, 360, 0, BBB_S_B, paint);//Title_cl
			}



			if (mbx < 730 && mbx > 655) {
				Graphic.drawPic(canvas, BBB, 1280 / 2, 720 / 2, 0, 255, paint);
			}
			if (mbx < 657) {

				Graphic.drawPic(canvas, BBB, 1280 / 2, 720 / 2, 0, BBB_S, paint);
				BBB_S = Coordinate.AnalogSpeedMove(BBB_S, 0);
			}
			if (mbx <= 640) {
				bar_m = Coordinate.AnalogSpeedMove(bar_m, 640);
				Graphic.drawPic(canvas, ww_bar, bar_m, 720 / 2, 0, 255, paint);

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
		else if(mainFlag==1){
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


         mv_background.recycle();
       mv_background_2.recycle();

		main_title.recycle();
		main_touchstart.recycle();
		storymode.recycle();
		createmode.recycle();

		main_title.recycle();
		left_xia.recycle();

		for(int i=0;i<5;i++){
			wrmax[i].recycle();
		}
		for(int i=0;i<5;i++){
			wrmaxg[i].recycle();
		}
		for(int i=0;i<9;i++){
			main2_fs_sp[i].recycle();
		}
		big_bg.recycle();
		ww_bar.recycle();

		val.recycle();
		w_bar.recycle();
		BBB.recycle();
		B_bar.recycle();
		for(int i=0;i<4;i++){
			ms_cl[i].recycle();
		}

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
