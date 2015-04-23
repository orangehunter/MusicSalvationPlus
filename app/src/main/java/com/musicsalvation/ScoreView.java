package com.musicsalvation;
//

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.lang.*;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility" })
public class ScoreView extends SurfaceView
implements SurfaceHolder.Callback{

	boolean deJump=true;

	Bitmap bg;
	Bitmap titlebar;
	Bitmap rightbar;
	Bitmap leftbar;
	Bitmap rankbar;
	Bitmap clearbar;
	Bitmap line;

	Bitmap title;
	Bitmap totalvirus;
	Bitmap nice;
	Bitmap hit;
	Bitmap safe;
	Bitmap miss;
	Bitmap max_combo;
	Bitmap score;
	Bitmap highscore;
	Bitmap quest_stage;
	Bitmap bossclear;
	Bitmap rank;
	Bitmap clear;
	Bitmap failed;

	Bitmap easy;
	Bitmap normal;
	Bitmap hard;

	Bitmap y;
	Bitmap n;
	/*Bitmap num_grey;
	Bitmap num_white;
	Bitmap num_red;
	Bitmap num_yellow;
	Bitmap num_green;
	Bitmap num_cyan;
	Bitmap num_blue;*/

	Bitmap rank_s;
	Bitmap rank_a;
	Bitmap rank_b;
	Bitmap rank_c;
	Bitmap rank_d;
	Bitmap rank_e;
	Bitmap rank_f;
	
	//離開與再挑戰、更新紀錄圖片宣告===========================================
	Bitmap exit;
	Bitmap again;
	Bitmap rank_new;
	Bitmap rank_gold;
	Bitmap rank_record;
	Bitmap score_record;
	//成績數字==============================================================================
	Number num;
	//---------------------------------------------------------------------------------------
	
	Bottom exit_btm;
	Bottom again_btm;

	int pointx;//觸控到螢幕的x座標
	int pointy;//觸控到螢幕的y座標

	//儲存遊戲判定用參數=======================================
	int sc_nice ;
	int sc_hit ;
	int sc_safe ;
	int sc_miss ;
	int sc_combo ;
	int sc_score ;
	int sc_rank;
	//儲存遊戲判定用參數---------------------------------------
	
	//TAG 按鈕音宣告=======================================
	SoundPool sp;
	int btn_se;
	//TAG 按鈕音宣告---------------------------------------

	int touch_flag=0;
	boolean clear_flag;
	boolean new_score_flag;
	boolean new_rank_flag;

	Paint paint;			//畫筆的參考
	int i=0,j=10;
	MainActivity activity;

	public ScoreView(MainActivity mainActivity) {
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
		//初始化=======================================================
		num =new Number(activity.getResources());
		sc_nice = 0;
		sc_hit = 0;
		sc_safe = 0;
		sc_miss = 0;
		sc_combo = 0;
		sc_score = 0;

		clear_flag=false;
		new_score_flag=false;
		new_rank_flag=false;
		//初始化--------------------------------------------------------------------------------------------
		bg=Graphic.bitSize(LoadBitmap( R.drawable.sv_background), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
		titlebar = Graphic.bitSize(LoadBitmap(R.drawable.title_bar), 1280, 90);
		rightbar = Graphic.bitSize(LoadBitmap(R.drawable.right_bar), 625, 75);
		leftbar  = Graphic.bitSize(LoadBitmap(R.drawable.left_bar), 620, 75);
		rankbar  = Graphic.bitSize(LoadBitmap(R.drawable.rank_bar), 615, 185);
		clearbar = Graphic.bitSize(LoadBitmap(R.drawable.clear_bar), 620, 150);
		line     = Graphic.bitSize(LoadBitmap(R.drawable.line), 625, 2);

		title   = Graphic.bitSize(LoadBitmap(R.drawable.score_title_1+activity.io.level), 560, 65);
		totalvirus = Graphic.bitSize(LoadBitmap(R.drawable.totalvirus), 410, 60);
		nice = Graphic.bitSize(LoadBitmap(R.drawable.sv_nice), 165, 60);
		hit  = Graphic.bitSize(LoadBitmap(R.drawable.sv_hit), 125, 60);
		safe = Graphic.bitSize(LoadBitmap(R.drawable.sv_safe), 165, 60);
		miss = Graphic.bitSize(LoadBitmap(R.drawable.sv_miss), 165, 60);
		max_combo = Graphic.bitSize(LoadBitmap(R.drawable.max_combo), 340, 60);
		quest_stage = Graphic.bitSize(LoadBitmap(R.drawable.quest_stage), 415, 60);
		bossclear = Graphic.bitSize(LoadBitmap(R.drawable.boss_del), 465, 70);
		score = Graphic.bitSize(LoadBitmap(R.drawable.score), 170, 55);
		highscore = Graphic.bitSize(LoadBitmap(R.drawable.highscore), 335, 55);
		clear = Graphic.bitSize(LoadBitmap(R.drawable.clear), 395, 140);
		failed = Graphic.bitSize(LoadBitmap(R.drawable.failed), 475, 140);
		rank = Graphic.bitSize(LoadBitmap(R.drawable.rank), 190, 95);

		easy = Graphic.bitSize(LoadBitmap(R.drawable.easyv2), 205, 80);
		normal = Graphic.bitSize(LoadBitmap(R.drawable.normalv2psd), 205, 80);
		hard = Graphic.bitSize(LoadBitmap(R.drawable.hardv2), 205, 80);

		y = Graphic.bitSize(LoadBitmap(R.drawable.y), 30, 50);
		n = Graphic.bitSize(LoadBitmap(R.drawable.n), 30, 50);

		/*num_grey = Graphic.bitSize(LoadBitmap(R.drawable.num_gray), 350, 50);
		num_red = Graphic.bitSize(LoadBitmap(R.drawable.num_red), 350, 50);
		num_yellow = Graphic.bitSize(LoadBitmap(R.drawable.num_yellow), 350, 50);
		num_green = Graphic.bitSize(LoadBitmap(R.drawable.num_green), 350, 50);
		num_blue = Graphic.bitSize(LoadBitmap(R.drawable.num_blue), 350, 50);
		num_cyan = Graphic.bitSize(LoadBitmap(R.drawable.num_cyan), 350, 50);*/

		rank_f = Graphic.bitSize(LoadBitmap(R.drawable.r_f), 86, 146);
		rank_e = Graphic.bitSize(LoadBitmap(R.drawable.r_e), 99, 152);
		rank_d = Graphic.bitSize(LoadBitmap(R.drawable.r_d), 124, 152);
		rank_c = Graphic.bitSize(LoadBitmap(R.drawable.r_c), 117, 176);
		rank_b = Graphic.bitSize(LoadBitmap(R.drawable.r_b), 92, 152);
		rank_a = Graphic.bitSize(LoadBitmap(R.drawable.r_a), 133, 182);
		rank_s = Graphic.bitSize(LoadBitmap(R.drawable.r_s), 309, 257);
		
		//離開與再挑戰、更新紀錄特效===========================================
		exit = Graphic.bitSize(LoadBitmap(R.drawable.sc_exit), 215, 95);
		again = Graphic.bitSize(LoadBitmap(R.drawable.sc_again), 215, 95);
		rank_gold = Graphic.bitSize(LoadBitmap(R.drawable.rank_gold), 190, 95);
		rank_new = Graphic.bitSize(LoadBitmap(R.drawable.rank_new), 95, 30);
		rank_record = Graphic.bitSize(LoadBitmap(R.drawable.rank_record), 610, 180);
		score_record = Graphic.bitSize(LoadBitmap(R.drawable.score_record), 655, 160);
		//離開與再挑戰、更新紀錄特效---------------------------------------------
		sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
		btn_se = sp.load(activity, R.raw.sv_btn, 1);
		
		//按鈕音=========================================
		
		//按鈕音--------------------------
		
		exit_btm = new Bottom(activity, exit, exit, 1120, 670);
		again_btm = new Bottom(activity, again, again, 825, 670);
		//判定是否過關==============================================================================
		//條件修正
		if(activity.io.percent > ((int)activity.io.virus*0.7)&& activity.io.boss_delete)  //如果打擊率超過70%並且擊殺BOSS
		{
			clear_flag=true;
			if(activity.io.level==2){
				activity.callAlartDialog("");
			}
			//FULL COMBO判定為S級
			if(activity.io.combo == activity.io.virus){
				sc_rank=7;
			}
			//打擊率超過90% 為A
			else if(activity.io.percent > ((int)activity.io.virus*0.9))
			{
				sc_rank=6;
			}
			//打擊率超過80% 低於90%為B
			else if(activity.io.percent > ((int)activity.io.virus*0.8) && activity.io.percent < ((int)activity.io.virus*0.9))
			{
				sc_rank=5;
			}
			//打擊率超過70% 低於80%為C
			else if(activity.io.percent > ((int)activity.io.virus*0.7) && activity.io.percent < ((int)activity.io.virus*0.8)) //大於70% 小於80%
			{
				sc_rank=4;
			}
		}
		else
		{
			clear_flag=false;
			//打擊率超過60% 低於70%為D
			if(activity.io.percent > ((int)activity.io.virus*0.6))
			{
				sc_rank=3;
			}
			//打擊率超過50% 低於60%為E
			else if(activity.io.percent > ((int)activity.io.virus*0.5) && activity.io.percent < ((int)activity.io.virus*0.6))
			{
				sc_rank=2;
			}
			//低於50%以下一律為F
			else
			{
				sc_rank=1;
			}
		}
		if(activity.io.score>activity.io.hight_score[activity.io.level][activity.io.difficulty]){
			new_score_flag=true;
			activity.io.hight_score[activity.io.level][activity.io.difficulty]=activity.io.score;
			activity.io.writeData();
		}
		if(sc_rank>activity.io.hight_rank[activity.io.level][activity.io.difficulty]){
			new_rank_flag=true;
			activity.io.hight_rank[activity.io.level][activity.io.difficulty]=this.sc_rank;
			activity.io.writeData();
		}

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
					SurfaceHolder myholder=ScoreView.this.getHolder();
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
			canvas.clipRect(new Rect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HIGHT));//只在螢幕範圍內繪制圖片
			canvas.drawColor(Color.BLACK);//界面設定為黑色
			Graphic.drawPic(canvas, bg, 1280/2, 720/2, 0, 255, paint);//背景
			Graphic.drawPic(canvas, titlebar, 1280/2, 45, 0, 255, paint);
			Graphic.drawPic(canvas, clearbar, 950, 165, 0, 255, paint);

			Graphic.drawPic(canvas, leftbar, 320, 178, 0, 255, paint);
			Graphic.drawPic(canvas, leftbar, 320, 270, 0, 255, paint);
			Graphic.drawPic(canvas, leftbar, 320, 356, 0, 255, paint);
			Graphic.drawPic(canvas, leftbar, 320, 438, 0, 255, paint);
			Graphic.drawPic(canvas, leftbar, 320, 517, 0, 255, paint);

			Graphic.drawPic(canvas, rightbar, 960, 300, 0, 255, paint);
			Graphic.drawPic(canvas, rightbar, 960, 390, 0, 255, paint);
			//Graphic.drawPic(canvas, rightbar, 960, 480, 0, 255, paint);
			if(new_rank_flag){
			Graphic.drawPic(canvas, rank_record, 960, 530, 0, 255, paint);
			Graphic.drawPic(canvas, rank_new, 1150, 475, 0, 255, paint);
			}
			if(new_score_flag)
			Graphic.drawPic(canvas, score_record, 325, 640, 0, 255, paint);

			Graphic.drawPic(canvas, title, 290, 40, 0, 255, paint);

			//難易度
			if(activity.io.difficulty == 0)
			{
			Graphic.drawPic(canvas, easy, 1150, 45, 0, 255, paint);
			}else if(activity.io.difficulty == 1){
			Graphic.drawPic(canvas, normal, 1150, 45, 0, 255, paint);
			}else if(activity.io.difficulty == 2){
			Graphic.drawPic(canvas, hard, 1150, 45, 0, 255, paint);
			}

			Graphic.drawPic(canvas, line, 323, 640, 0, 255, paint);
			Graphic.drawPic(canvas, line, 323, 710, 0, 255, paint);

			Graphic.drawPic(canvas, totalvirus, 240, 180, 0, 255, paint);
			Graphic.drawPic(canvas, nice, 120, 275, 0, 255, paint);
			Graphic.drawPic(canvas, hit, 100, 360, 0, 255, paint);
			Graphic.drawPic(canvas, safe, 120, 440, 0, 255, paint);
			Graphic.drawPic(canvas, miss, 120, 520, 0, 255, paint);
			Graphic.drawPic(canvas, max_combo, 845, 300, 0, 255, paint);
			//Graphic.drawPic(canvas, quest_stage, 880, 390, 0, 255, paint);
			if(activity.io.boss_delete){
			Graphic.drawPic(canvas, bossclear, 900, 390, 0, 255, paint);
			}
			Graphic.drawPic(canvas, score, 120, 610, 0, 255, paint);
			Graphic.drawPic(canvas, highscore, 200, 675, 0, 255, paint);
			Graphic.drawPic(canvas, rank, 760, 560, 0, 255, paint);
			//Graphic.drawPic(canvas, rank_gold, 765, 565, 0, 255, paint);
			
			exit_btm.drawBtm(canvas, paint);
			again_btm.drawBtm(canvas, paint);
			


			//數字====================================================================================

			if(sc_nice != activity.io.nice){
				sc_nice=Coordinate.AnalogSpeedMove(sc_nice, activity.io.nice);
			}
			if(sc_hit != activity.io.hit){
				sc_hit=Coordinate.AnalogSpeedMove(sc_hit, activity.io.hit);
			}
			if(sc_safe != activity.io.safe){
				sc_safe=Coordinate.AnalogSpeedMove(sc_safe, activity.io.safe);
			}
			if(sc_miss != activity.io.miss){
				sc_miss=Coordinate.AnalogSpeedMove(sc_miss, activity.io.miss);
			}
			if(sc_score != activity.io.score){
				sc_score=Coordinate.AnalogSpeedMove(sc_score, activity.io.score);
			}
			if(sc_combo != activity.io.combo){
				sc_combo=Coordinate.AnalogSpeedMove(sc_combo, activity.io.combo);
			}

			num.setSize(35, 60);
			num.drawNumberRightStart(630, 180, activity.io.virus, Number.Gray, canvas, paint);
			num.drawNumberRightStart(630, 270, sc_nice, Number.Yellow, canvas, paint);
			num.drawNumberRightStart(630, 360, sc_hit, Number.Cyan, canvas, paint);
			num.drawNumberRightStart(630, 440, sc_safe, Number.Green, canvas, paint);
			num.drawNumberRightStart(630, 520, sc_miss, Number.Red, canvas, paint);
			num.drawNumberRightStart(1250, 300, sc_combo, Number.Blue, canvas, paint);

			num.setSize(30, 55);

			num.drawNumberRightStart(620, 610, sc_score, Number.Wite, canvas, paint);
			num.drawNumberRightStart(620, 675, activity.io.hight_score[activity.io.level][activity.io.difficulty], Number.Wite, canvas, paint);
			//數字------------------------------------------------------------------------------------

			if(sc_score == activity.io.score){
				if(clear_flag){
					Graphic.drawPic(canvas, clear, 950, 165, 0, 255, paint);
				}else{
					Graphic.drawPic(canvas, failed, 950, 160, 0, 255, paint);
				}
				switch(sc_rank){
				case 7:
					Graphic.drawPic(canvas, rank_s, 1030, 530, 0, 255, paint);
					break;
				case 6:
					Graphic.drawPic(canvas, rank_a, 1030, 530, 0, 255, paint);
					break;
				case 5:
					Graphic.drawPic(canvas, rank_b, 1030, 530, 0, 255, paint);
					break;
				case 4:
					Graphic.drawPic(canvas, rank_c, 1030, 530, 0, 255, paint);
					break;
				case 3:
					Graphic.drawPic(canvas, rank_d, 1030, 530, 0, 255, paint);
					break;
				case 2:
					Graphic.drawPic(canvas, rank_e, 1030, 530, 0, 255, paint);
					break;
				case 1:
					Graphic.drawPic(canvas, rank_f, 1030, 530, 0, 255, paint);
					break;
				}
			}
			//判定是否過關------------------------------------------------------------------------------

			/*擊殺BOSS與否
			Graphic.drawPic(canvas, y, 1225, 390, 0, 255, paint);
			Graphic.drawPic(canvas, n, 1225, 390, 0, 255, paint);
			 */
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		pointx=(int) event.getX();
		pointy=(int) event.getY();

		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN://按下
			if(deJump == true){
				if(touch_flag==0){
					sc_nice = activity.io.nice;
					sc_hit = activity.io.hit;
					sc_safe = activity.io.safe;
					sc_miss = activity.io.miss;
					sc_score = activity.io.score;
					sc_combo = activity.io.combo;
					touch_flag=1;
				}/*if(touch_flag==1){
					activity.changeView(2);
				}*/
				if(exit_btm.isIn(pointx, pointy)){
                    if (activity.io.level!=3) {
                        activity.changeView(2);
                    }else {
                        activity.changeView(8);
                    }
					sp.play(btn_se, activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
				}
				if(again_btm.isIn(pointx, pointy)){
					sp.play(btn_se, activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
					activity.changeView(3);
					
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
		bg.recycle();
		titlebar.recycle();
		rightbar.recycle();
		leftbar.recycle();
		rankbar.recycle();
		clearbar.recycle();
		line.recycle();

		title.recycle();
		totalvirus.recycle();
		nice.recycle();
		hit.recycle();
		safe.recycle();
		miss.recycle();
		max_combo.recycle();
		score.recycle();
		highscore.recycle();
		quest_stage.recycle();
		bossclear.recycle();
		rank.recycle();
		clear.recycle();
		failed.recycle();

		easy.recycle();
		normal.recycle();
		hard.recycle();

		y.recycle();
		n.recycle();
		/*num_grey.recycle();
		num_white.recycle();
		num_red.recycle();
		num_yellow.recycle();
		num_green.recycle();
		num_cyan.recycle();
		num_blue.recycle();*/

		rank_s.recycle();
		rank_a.recycle();
		rank_b.recycle();
		rank_c.recycle();
		rank_d.recycle();
		rank_e.recycle();
		rank_f.recycle();
		
		//離開與再挑戰、更新紀錄圖片宣告===========================================
		exit.recycle();
		again.recycle();
		rank_new.recycle();
		rank_gold.recycle();
		rank_record.recycle();
		score_record.recycle();
		//成績數字==============================================================================
		exit_btm.recycle();
		again_btm.recycle();
		num.recycle();
		sp.release();
		Constant.Flag=false;
	}


}
