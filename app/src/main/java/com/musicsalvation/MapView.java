package com.musicsalvation;
//

import com.example.musicsalvationsdkverson.R;
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
import android.view.View;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility" })
public class MapView extends SurfaceView
implements SurfaceHolder.Callback{

	Bitmap wmap;
	Bitmap left_back;
	Bitmap left_exit;    //<<�b�Y-�L��  ���}MENU��
	Bitmap left_exit2;   //<<�b�Y-�`��

	Bitmap menubtn;      //>>�b�Y-�L��  �I�sMENU��
	Bitmap menubtn2;    //>>�b�Y-�`��

	Bitmap left_exitback; //���}���s�I��
	Bitmap left_btmback;  //MENU�ﶵ�I��
	Bitmap btnvol;
	Bitmap btnvol2;
	Bitmap chbtmse;
	Bitmap chbtmse2;
	Bitmap song_vol;
	Bitmap song_vol2;
	Bitmap remain;
	Bitmap remain2;

	//�t�׻P�P�w�Ϥ��ŧi=====================================
	Bitmap timing;
	Bitmap timing2;
	Bitmap timing_back;
	Bitmap timing_r_arrow;
	Bitmap timing_l_arrow;
	Bitmap timing_num[] = new Bitmap [16];
	//�t�׻P�P�w�Ϥ��ŧi----------------------------------------


	Bitmap volchback;
	Bitmap volBar;
	Bitmap volbtn;
	Bitmap sechange;

	//�k�����d��ܰ϶�
	Bitmap right_board;
	Bitmap right_chmodel;
	Bitmap right_stage01;
	Bitmap right_freely;
	Bitmap right_boss01;
	Bitmap right_boss01_gray;
	Bitmap right_st01Font;
	Bitmap right_easy;
	Bitmap right_normal;
	Bitmap right_hard;

	//�s�ŧi�������׻P�b�Y�Ϥ�===========================================
	Bitmap right_easy_ch;
	Bitmap right_normal_ch;
	Bitmap right_hard_ch;
	Bitmap right_arrow_left;
	Bitmap right_arrow_left2;
	Bitmap right_arrow_right;
	Bitmap right_arrow_right2;
	//�s�ŧi�������׻P�b�Y�Ϥ�-------------------------------------------

	//��2�B��3���d�һݭn���Ϥ�===========================================
	Bitmap right_stage02info;
	Bitmap right_stage03info;
	Bitmap right_boss_del;  //�L������|��ܪ�BOSS�Ϥ�
	Bitmap stagebtn_green;
	Bitmap stagebtn_green_l;
	Bitmap right_stage2;
	Bitmap right_stage3;
	//��2�B��3���d�һݭn���Ϥ�-------------------------------------------

	Bitmap right_start;
	Bitmap stage01btn0;
	Bitmap stage01btn;

	Bitmap se01;
	Bitmap se01l;
	Bitmap se02;
	Bitmap se02l;
	Bitmap se03;
	Bitmap se03l;
	Bitmap se04;
	Bitmap se04l;
	Bitmap se05;
	Bitmap se05l;

	Bitmap model_ch;


	Bottom menubtm; //MENU���s
	Bottom left_btm1;
	Bottom left_btm2;
	Bottom left_btm3;
	Bottom left_btm4;
	Bottom left_btm5;
	Bottom speed_left_arrow;
	Bottom speed_right_arrow;
	Bottom timing_left_arrow;
	Bottom timing_right_arrow;
	MySeekBar mp_Volume_bar;
	MySeekBar sp_Volume_bar;


	Bottom sebtm1;
	Bottom sebtm2;
	Bottom sebtm3;
	Bottom sebtm4;
	Bottom sebtm5;

	Bottom stbtn01;
	boolean st_02_flag;
	Bottom stbtn02;  //�ĤG�����s
	boolean st_03_flag;
	Bottom stbtn03;  //�ĤT�����s
	Bottom easy;
	Bottom normal;
	Bottom hard;
	Bottom model;
	Bottom start;

	//�b�Y���s�ŧi================================================
	Bottom arrow;
	//�b�Y���s�ŧi------------------------------------------------


	MediaPlayer mp;
	SoundPool sp;
	int sp_id[];

	int pointx;//Ĳ����ù���x�y��
	int pointy;//Ĳ����ù���y�y��
	boolean deJump = true;

	float rot=0;
	int ma=5;
	int x = 0;
	int alpha = 10;
	int x2=0;
	int alpha2 = 10;

	//FLAG�ŧi�ϰ�
	int menuFlag = 0;   //�@�ɦa�ϥ��b��
	int stageFlag = 0;  //�k�b��

	int mbgx = -500;
	int mbgx2 = 13;

	int leftbtmmx1 = -400;
	int leftbtmmove1 = 77;

	int leftbtmmx2 = -400;
	int leftbtmmove2 = 114;

	int leftbtmmx3 = -400;
	int leftbtmmove3 = 138;

	int leftbtmmx4 = -400;
	int leftbtmmove4 = 114;

	int leftbtmmx5 = -400;
	int leftbtmmove5 = 77;

	int sevolmx = -200;
	int songvolmx = -200;
	int sevolmovex = 381;
	int songvolmovex = 425;
	int sechx = -254;
	int sechmovex = 344;

	int baralpha = 0;
	int sebaralpha = 0;
	int speedbaralpha = 0;

	int right_board_x=1680;

	int set_speed = 6;
	int set_timing = 5;

	int stagecount = 0;


	//RANK===============================
	Bitmap rank[];
	//RANK------------------------------
	Number num;

	Paint paint;			//�e�����Ѧ�
	int i=0,j=5;
	MainActivity activity;

	public MapView(MainActivity mainActivity) {
		super(mainActivity);
		this.activity = mainActivity;
		this.getHolder().addCallback(this);//�]�w�ͩR�P���^�ձ��f����{��


	}

	public Bitmap LoadBitmap(int r){
		return BitmapFactory.decodeResource(getResources(), r);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		set_speed=activity.speed+5;
		set_timing=activity.timing+5;
		paint = new Paint();//�إߵe��
		paint.setAntiAlias(true);//�}�ҧܿ���
		wmap =Graphic.bitSize(LoadBitmap( R.drawable.wmap), Constant.DEFULT_WITH, Constant.DEFULT_HIGHT);
		left_back=Graphic.bitSize(LoadBitmap( R.drawable.menubackground), 846, 871);
		left_exit = Graphic.bitSize(LoadBitmap( R.drawable.left_exit1), 134, 75);
		left_exit2 = Graphic.bitSize(LoadBitmap( R.drawable.left_exit2), 134, 75);
		left_exitback = Graphic.bitSize(LoadBitmap( R.drawable.left_exitback) , 229, 78);
		menubtn  = Graphic.bitSize(LoadBitmap( R.drawable.menubtn) , 134, 75);
		menubtn2  = Graphic.bitSize(LoadBitmap( R.drawable.menubtn2) , 134, 75);
		left_btmback = Graphic.bitSize(LoadBitmap( R.drawable.left_btmback) , 369, 72);
		btnvol = Graphic.bitSize(LoadBitmap( R.drawable.button_vol) , 215, 67);
		btnvol2 = Graphic.bitSize(LoadBitmap( R.drawable.button_vol2) , 208, 67);
		chbtmse = Graphic.bitSize(LoadBitmap( R.drawable.chbtmse) , 267, 69);
		chbtmse2 = Graphic.bitSize(LoadBitmap( R.drawable.chbtmse2) , 263, 65);
		song_vol = Graphic.bitSize(LoadBitmap( R.drawable.song_vol) , 221, 66);
		song_vol2 = Graphic.bitSize(LoadBitmap( R.drawable.song_voltou) , 215, 66);
		remain = Graphic.bitSize(LoadBitmap( R.drawable.re_title) , 149, 53);
		remain2 = Graphic.bitSize(LoadBitmap( R.drawable.re_title2) , 149, 53);
		timing = Graphic.bitSize(LoadBitmap( R.drawable.speed_timing_font) , 253, 52);
		timing2 = Graphic.bitSize(LoadBitmap( R.drawable.speed_timing_font2) , 253, 52);
		volchback =  Graphic.bitSize(LoadBitmap(R.drawable.volchange_back),402 ,67 );
		volBar = Graphic.bitSize(LoadBitmap(R.drawable.volbar),216 ,37 );
		volbtn = Graphic.bitSize(LoadBitmap(R.drawable.volbtn),31 ,31 );
		sechange = Graphic.bitSize(LoadBitmap(R.drawable.sechange),254 ,324 );

		right_board = Graphic.bitSize(LoadBitmap(R.drawable.right_stageboard), 418, 714);
		right_chmodel = Graphic.bitSize(LoadBitmap(R.drawable.right_chmodel), 250, 250);
		right_stage01 = Graphic.bitSize(LoadBitmap(R.drawable.right_stage01), 266, 62);
		right_freely = Graphic.bitSize(LoadBitmap(R.drawable.right_freely), 378, 58);
		right_boss01 =Graphic.bitSize(LoadBitmap(R.drawable.boss_color4), 402, 246);
		right_boss01_gray= Graphic.bitSize(LoadBitmap(R.drawable.right_boss01), 402, 246);
		right_st01Font = Graphic.bitSize(LoadBitmap(R.drawable.right_stage01infor), 389, 336);
		right_easy = Graphic.bitSize(LoadBitmap(R.drawable.easyv2), 205, 80);
		right_normal = Graphic.bitSize(LoadBitmap(R.drawable.normalv2psd), 205, 80);
		right_hard = Graphic.bitSize(LoadBitmap(R.drawable.hardv2), 205, 80);
		right_start = Graphic.bitSize(LoadBitmap(R.drawable.startv2), 175, 75);

		//��������ת����s�P�i�}��檺�b�Y===============================================================
		model_ch =  Graphic.bitSize(LoadBitmap(R.drawable.model), 205, 80);
		right_easy_ch = Graphic.bitSize(LoadBitmap(R.drawable.easyv2_ch), 205, 80);
		right_normal_ch = Graphic.bitSize(LoadBitmap(R.drawable.normalv2_ch), 205, 80);
		right_hard_ch = Graphic.bitSize(LoadBitmap(R.drawable.hardv2_ch), 205, 80);
		right_arrow_left = Graphic.bitSize(LoadBitmap(R.drawable.map_arrow), 35, 50);
		right_arrow_left2 = Graphic.bitSize(LoadBitmap(R.drawable.map_arrow2), 35, 50);
		right_arrow_right = Graphic.bitSize(LoadBitmap(R.drawable.map_arrow_right), 35, 50);
		right_arrow_right2 = Graphic.bitSize(LoadBitmap(R.drawable.map_arrow_right2), 35, 50);

		//��������ת����s�P�i�}��檺�b�Y---------------------------------------------------------------

		//�s�W��2�B3���d��T====================================================================
		right_stage02info = Graphic.bitSize(LoadBitmap(R.drawable.right_stage02infor), 389, 336);
		right_stage03info = Graphic.bitSize(LoadBitmap(R.drawable.right_stage03infor), 389, 336);
		right_stage2 = Graphic.bitSize(LoadBitmap(R.drawable.right_stage02), 266, 62);
		right_stage3 = Graphic.bitSize(LoadBitmap(R.drawable.right_stage03), 266, 62);
		stagebtn_green = Graphic.bitSize(LoadBitmap(R.drawable.stagebtn_green), 64, 64);
		stagebtn_green_l = Graphic.bitSize(LoadBitmap(R.drawable.stagebtn_green_l), 64, 64);


		//�s�W��2�B3���d��T---------------------------------------------------------------------

		stage01btn0 = Graphic.bitSize(LoadBitmap(R.drawable.stage01btn0), 64, 64);
		stage01btn = Graphic.bitSize(LoadBitmap(R.drawable.stage01btn1), 64, 64);

		se01 = Graphic.bitSize(LoadBitmap(R.drawable.se01),70 ,70 );
		se01l = Graphic.bitSize(LoadBitmap(R.drawable.se01l),70 ,70 );
		se02 = Graphic.bitSize(LoadBitmap(R.drawable.se02),70 ,70 );
		se02l = Graphic.bitSize(LoadBitmap(R.drawable.se02l),70 ,70 );
		se03 = Graphic.bitSize(LoadBitmap(R.drawable.se03),70 ,70 );
		se03l = Graphic.bitSize(LoadBitmap(R.drawable.se03l),70 ,70 );
		se04 = Graphic.bitSize(LoadBitmap(R.drawable.se04),70 ,70 );
		se04l = Graphic.bitSize(LoadBitmap(R.drawable.se04l),70 ,70 );
		se05 = Graphic.bitSize(LoadBitmap(R.drawable.se05),70 ,70 );
		se05l = Graphic.bitSize(LoadBitmap(R.drawable.se05l),70 ,70 );

		//�t�׻P�P�w==============================================================
		timing_back = Graphic.bitSize(LoadBitmap(R.drawable.timing_back), 390 ,295 );
		timing_l_arrow = Graphic.bitSize(LoadBitmap(R.drawable.timing_l_arrow),45 ,40 );
		timing_r_arrow = Graphic.bitSize(LoadBitmap(R.drawable.timing_r_arrow),45 ,40 );
		timing_num[0] = Graphic.bitSize(LoadBitmap(R.drawable.ne5),48 ,48 );
		timing_num[1] = Graphic.bitSize(LoadBitmap(R.drawable.ne4),48 ,48 );
		timing_num[2] = Graphic.bitSize(LoadBitmap(R.drawable.ne3),48 ,48 );
		timing_num[3] = Graphic.bitSize(LoadBitmap(R.drawable.ne2),48 ,48 );
		timing_num[4] = Graphic.bitSize(LoadBitmap(R.drawable.ne1),48 ,48 );
		timing_num[5] = Graphic.bitSize(LoadBitmap(R.drawable.zero),48 ,48 );
		timing_num[6] = Graphic.bitSize(LoadBitmap(R.drawable.one),48 ,48 );
		timing_num[7] = Graphic.bitSize(LoadBitmap(R.drawable.two),48 ,48 );
		timing_num[8] = Graphic.bitSize(LoadBitmap(R.drawable.three),48 ,48 );
		timing_num[9] = Graphic.bitSize(LoadBitmap(R.drawable.four),48 ,48 );
		timing_num[10] = Graphic.bitSize(LoadBitmap(R.drawable.five),48 ,48 );
		timing_num[11] = Graphic.bitSize(LoadBitmap(R.drawable.num_06),48 ,48 );
		timing_num[12] = Graphic.bitSize(LoadBitmap(R.drawable.num_07),48 ,48 );
		timing_num[13] = Graphic.bitSize(LoadBitmap(R.drawable.num_08),48 ,48 );
		timing_num[14] = Graphic.bitSize(LoadBitmap(R.drawable.num_09),48 ,48 );
		timing_num[15] = Graphic.bitSize(LoadBitmap(R.drawable.num_10),48 ,48 );
		//�t�׻P�P�w----------------------------------------------------------------------

		menubtm = new Bottom(activity, left_exitback, left_exitback, 110, 35);
		left_btm1= new Bottom(activity, btnvol2, btnvol, 114, 166);
		left_btm2= new Bottom(activity, song_vol2, song_vol, 114, 257);
		left_btm3= new Bottom(activity, chbtmse2, chbtmse, 136, 355);
		left_btm4= new Bottom(activity, timing2, timing, 134, 451);
		left_btm5= new Bottom(activity, remain2, remain, 114, 542);

		//�t�׻P�P�w���s===========================================================
		speed_right_arrow = new Bottom(activity , timing_r_arrow,timing_r_arrow, 435 ,545);
		speed_left_arrow = new Bottom(activity , timing_l_arrow,timing_l_arrow, 322 ,545);
		timing_left_arrow = new Bottom(activity, timing_l_arrow , timing_l_arrow ,322 , 660);
		timing_right_arrow = new Bottom(activity, timing_r_arrow , timing_r_arrow ,435 , 660);
		//�t�׻P�P�w���s------------------------------------------------------------

		sebtm1 = new Bottom(activity, se01, se01l, 390, 390);
		sebtm2 = new Bottom(activity, se02, se02l, 393, 451);
		sebtm3 = new Bottom(activity, se03, se03l, 393, 511);
		sebtm4 = new Bottom(activity, se04, se04l, 393, 569);
		sebtm5 = new Bottom(activity, se05, se05l, 393, 628);
		stbtn01 = new Bottom(activity, stage01btn , stage01btn0 , 644, 609);
		stbtn02 = new Bottom(activity, stage01btn , stage01btn0 , 815, 165);
		stbtn03 = new Bottom(activity, stage01btn , stage01btn0 , 430, 335);

		st_02_flag=false;
		st_03_flag=false;
		for(int j=0;j<3;j++){
			if(activity.level_clear[0][j]){
				st_02_flag=true;
			}
			if(activity.level_clear[1][j]){
				st_03_flag=true;
			}
		}

		switch (activity.sp_num) {
		case 0:
			sebtm1.setBottomTo(true);
			break;
		case 1:
			sebtm2.setBottomTo(true);
			break;
		case 2:
			sebtm3.setBottomTo(true);
			break;
		case 3:
			sebtm4.setBottomTo(true);
			break;
		case 4:
			sebtm5.setBottomTo(true);
			break;
		}

		mp_Volume_bar=new MySeekBar(activity, volBar, volbtn, -300, 259);
		mp_Volume_bar.setSeekBarFloat((int)(activity.mp_Voiume*100));
		sp_Volume_bar=new MySeekBar(activity, volBar, volbtn, -300, 167);
		sp_Volume_bar.setSeekBarFloat((int)(activity.sp_Voiume*100));

		start = new Bottom(activity, right_start, right_start, 1161, 655);

		//�ثe�ϥΪ�����
		//TODO (�\��ݸ�)
		model = new Bottom(activity, model_ch, model_ch, 969, 655);
		//������רϥΪ���ܫ��s
		easy  = new Bottom(activity, right_easy_ch, right_easy, 741, 514);
		normal  = new Bottom(activity, right_normal_ch, right_normal, 741, 588);
		hard  = new Bottom(activity, right_hard_ch, right_hard, 741, 662);


		mp = MediaPlayer.create(this.getContext(), R.raw.map_bgm);
		mp.setVolume(activity.mp_Voiume, activity.mp_Voiume);
		mp.setLooping(true);
		mp.start();

		sp=new SoundPool(4, AudioManager.STREAM_MUSIC, 5);
		sp_id=new int[11];
		sp_id[0]=sp.load(activity, R.raw.tambourine, 1);
		sp_id[1]=sp.load(activity, R.raw.drum_cymbal, 1);
		sp_id[2]=sp.load(activity, R.raw.drum_snare, 1);
		sp_id[3]=sp.load(activity, R.raw.fall, 1);
		sp_id[4]=sp.load(activity, R.raw.voice_dog, 1);
		sp_id[5]=sp.load(activity, R.raw.left_menu_on, 1);
		sp_id[6]=sp.load(activity, R.raw.left_menu_off, 1);
		sp_id[7]=sp.load(activity, R.raw.leftm_btn, 1);
		sp_id[8]=sp.load(activity, R.raw.left_num, 1);
		sp_id[9]=sp.load(activity, R.raw.stagebtn, 1);
		sp_id[10]=sp.load(activity, R.raw.start, 1);

		num=new Number(getResources());
		num.setSize(30, 40);
		rank=new Bitmap[7];
		rank[6]=Graphic.LoadBitmap(getResources(), R.drawable.r_s, 45,60);
		for(int i=0;i<=5;i++){
			rank[i]=Graphic.LoadBitmap(getResources(), R.drawable.r_f-i, 45, 60);
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
						Log.e("Thread", "MapView Stop");
						e.printStackTrace();
					}
					SurfaceHolder myholder=MapView.this.getHolder();
					Canvas canvas = myholder.lockCanvas();//���o�e��
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
	protected void onDraw(Canvas canvas) {//���s�w�q��ø���k
		if(canvas!=null){
			super.onDraw(canvas);
			canvas.clipRect(new Rect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HIGHT));//�u�b�ù��d��ø��Ϥ�
			canvas.drawColor(Color.BLACK);//�ɭ��]�w���¦�

			if(!mp.isPlaying()){
				mp.prepareAsync();
				mp.start();
			}

			Graphic.drawPic(canvas, wmap, 1280/2, 720/2, 0, 255, paint);//�a��
			if(stbtn01.getBottom()){
				Graphic.drawPic(canvas, stage01btn0, 644, 609, 0, 255, paint);
				stbtn01.drawBtm(canvas, paint,x2);  //�Ĥ@�����s
			}else{
				stbtn01.drawBtm(canvas, paint);
			}

			//2�B3�����s==============================================================
			if(st_02_flag){
				if(stbtn02.getBottom()){
					Graphic.drawPic(canvas, stage01btn0, 815, 165, 0, 255, paint);
					stbtn02.drawBtm(canvas, paint,x2);
				}else{
					stbtn02.drawBtm(canvas, paint);
				}

			}
			if(st_03_flag){
				if(stbtn03.getBottom()){
					Graphic.drawPic(canvas, stage01btn0, 430, 335, 0, 255, paint);
					stbtn03.drawBtm(canvas, paint,x2);
				}else{
					stbtn03.drawBtm(canvas, paint);
				}
			}

			//2�B3�����s-------------------------------------------------------------
			if(mbgx!=-500){
				Graphic.drawPic(canvas, left_back, mbgx, 374, rot, 255, paint);
				Graphic.drawPic(canvas, timing_back, 270, 560, 0, speedbaralpha, paint);
				Graphic.drawPic(canvas, sechange,344, 504, 0, sebaralpha ,paint);
				Graphic.drawPic(canvas, volchback,sevolmx, 167, 0, baralpha, paint);
				Graphic.drawPic(canvas, volchback,songvolmx, 259, 0, baralpha, paint);
				mp_Volume_bar.drawSeekBar(canvas, paint);
				sp_Volume_bar.drawSeekBar(canvas, paint);
			}


			x+=alpha;
			if(x >= 250){
				alpha = -5;
			}
			if(x < 10){
				alpha = 5;
			}
			x2+=alpha2;
			if(x2 >= 250){
				alpha2 = -10;
			}
			if(x2 < 140){
				alpha2 = 10;
			}
			stagecount--;
			if(stagecount < 0){
				stagecount = 0;
			}
			//���b����汱��================================================
			if(menuFlag == 0){
				menubtm.drawBtm(canvas, paint);
				Graphic.drawPic(canvas, menubtn, 66, 34, 0, 255, paint);
				Graphic.drawPic(canvas, menubtn2, 66, 34, 0, x, paint);
				mbgx=Coordinate.AnalogSpeedMove(mbgx, -500);
				leftbtmmx1 = Coordinate.AnalogSpeedMove(leftbtmmx1,-400);
				leftbtmmx2 = Coordinate.AnalogSpeedMove(leftbtmmx2,-400);
				leftbtmmx3 = Coordinate.AnalogSpeedMove(leftbtmmx3,-400);
				leftbtmmx4 = Coordinate.AnalogSpeedMove(leftbtmmx4,-400);
				leftbtmmx5 = Coordinate.AnalogSpeedMove(leftbtmmx5,-400);
				sevolmx = Coordinate.AnalogSpeedMove(sevolmx, -300);
				songvolmx= Coordinate.AnalogSpeedMove(songvolmx, -300);
				if(sevolmx!=-300)
					sp_Volume_bar.Move(sevolmx, 167);
				if(songvolmx!=-300)
					mp_Volume_bar.Move(songvolmx, 259);
				baralpha = 0;
				speedbaralpha = 0;
				sebaralpha = 0;
			}else if(menuFlag == 1)  {
				rot-= 0.5;
				if(rot == -360){
					rot = 0;
				}
				//��menu�I��
				mbgx=Coordinate.AnalogSpeedMove(mbgx, mbgx2);
				menubtm.drawBtm(canvas, paint);
				Graphic.drawPic(canvas, left_exit, 66, 34, 0, 255, paint);
				Graphic.drawPic(canvas, left_exit2, 66, 34, 0, x, paint);

				Graphic.drawPic(canvas, left_btmback,leftbtmmx1, 166, 0, 255, paint);
				Graphic.drawPic(canvas, left_btmback,leftbtmmx2, 257, 0, 255, paint);
				Graphic.drawPic(canvas, left_btmback,leftbtmmx3, 354, 0, 255, paint);
				Graphic.drawPic(canvas, left_btmback,leftbtmmx4, 450, 0, 255, paint);
				Graphic.drawPic(canvas, left_btmback,leftbtmmx5, 542, 0, 255, paint);

				leftbtmmx1 = Coordinate.AnalogSpeedMove(leftbtmmx1, leftbtmmove1);
				leftbtmmx2 = Coordinate.AnalogSpeedMove(leftbtmmx2, leftbtmmove2);
				leftbtmmx3 = Coordinate.AnalogSpeedMove(leftbtmmx3, leftbtmmove3);
				leftbtmmx4 = Coordinate.AnalogSpeedMove(leftbtmmx4, leftbtmmove4);
				leftbtmmx5 = Coordinate.AnalogSpeedMove(leftbtmmx5, leftbtmmove5);

				left_btm1.move(mbgx+101, 166);
				left_btm2.move(mbgx+101, 257);
				left_btm3.move(mbgx+123, 355);
				left_btm4.move(mbgx+120, 451);
				left_btm5.move(mbgx+101, 542);
				left_btm1.drawBtm(canvas, paint);
				left_btm2.drawBtm(canvas, paint);
				left_btm3.drawBtm(canvas, paint);
				left_btm4.drawBtm(canvas, paint);
				left_btm5.drawBtm(canvas, paint);
				//�����s===============================================================
				if(left_btm1.getBottom()){
					if(sevolmx!=sevolmovex){
						sevolmx = Coordinate.AnalogSpeedMove(sevolmx, sevolmovex);
						baralpha = 255;
						sp_Volume_bar.Move(sevolmx, 167);
					}
				}
				else {
					if(sevolmx!=-300){
						sevolmx = Coordinate.AnalogSpeedMove(sevolmx, -300);
						sp_Volume_bar.Move(sevolmx, 167);
					}
				}
				if(left_btm2.getBottom()){
					if(songvolmx!=songvolmovex){
						songvolmx = Coordinate.AnalogSpeedMove(songvolmx, songvolmovex);
						baralpha = 255;
						mp_Volume_bar.Move(songvolmx, 259);
					}
				}
				else{
					if(songvolmx!=-300){
						songvolmx = Coordinate.AnalogSpeedMove(songvolmx, -300);
						mp_Volume_bar.Move(songvolmx, 259);
					}
				}
				if(left_btm3.getBottom()){
					sebtm1.drawBtm(canvas, paint);
					sebtm2.drawBtm(canvas, paint);
					sebtm3.drawBtm(canvas, paint);
					sebtm4.drawBtm(canvas, paint);
					sebtm5.drawBtm(canvas, paint);
					sebaralpha = 255;
				}
				else{
					sebaralpha = 0;
				}
				if(left_btm4.getBottom()){
					speedbaralpha = 255;
					speed_left_arrow.drawBtm(canvas, paint);
					speed_right_arrow.drawBtm(canvas, paint);
					Graphic.drawPic(canvas, timing_num[set_speed], 380, 545, 0, 255, paint);
					timing_left_arrow.drawBtm(canvas, paint);
					timing_right_arrow.drawBtm(canvas, paint);
					Graphic.drawPic(canvas, timing_num[set_timing], 380, 660, 0, 255, paint);

				}else{
					speedbaralpha = 0;

				}
				//�����s----------------------------------------------------------------------------------------------------
			}


			//���b����汱��ܦ�=======================================================


			//�k�b�����d��汱��==========================================
			switch(stageFlag){
			case 0:
				if(right_board_x!=1680){
					right_board_x=Coordinate.AnalogSpeedMove(right_board_x, 1680);
					Graphic.drawPic(canvas, right_board, right_board_x, 355, 0, 255, paint);
					//�b�Y���==================================================
					if(!model.getBottom()){

						Graphic.drawPic(canvas, right_arrow_left, right_board_x-86, 665, 0, 255,  paint);
						Graphic.drawPic(canvas, right_arrow_left2, right_board_x-86, 665, 0, x2,  paint);
					}else if(model.getBottom()){
						Graphic.drawPic(canvas, right_arrow_right, right_board_x-86, 665, 0, 255,  paint);
						Graphic.drawPic(canvas, right_arrow_right2, right_board_x-86, 665, 0, x2,  paint);
					}
					//�b�Y���----------------------------------------------------
					start.drawBtm(canvas, paint,right_board_x+101, 645);
					model.drawBtm(canvas, paint,right_board_x-86, 667);

					//�l�[����:��Flag = 0 �|���easy=================================================
					if(activity.difficulty==0){

						Graphic.drawPic(canvas, right_easy_ch, right_board_x-86, 655, 0, 255, paint);
						//�l�[����Flag = 0 �|���easy-----------------------------------------------
					}else if(activity.difficulty==1){

						Graphic.drawPic(canvas, right_normal_ch, right_board_x-86, 667, 0, 255, paint);	
					}else if(activity.difficulty==2){

						Graphic.drawPic(canvas, right_hard_ch, right_board_x-86, 667, 0, 255, paint);	
					}
				}
				break;
			case 1:
				right_board_x=Coordinate.AnalogSpeedMove(right_board_x, 1062);
				Graphic.drawPic(canvas, right_board, right_board_x, 355, 0, 255, paint);
				Graphic.drawPic(canvas, right_stage01, right_board_x+11, 37, 0, 255, paint);
				if(activity.level_clear[activity.level][activity.difficulty]){

				Graphic.drawPic(canvas, right_boss01, right_board_x+2, 179, 0, 255, paint);
				}else{
					Graphic.drawPic(canvas, right_boss01_gray, right_board_x+2, 179, 0, 255, paint);
				}
				Graphic.drawPic(canvas, right_st01Font, right_board_x+3, 465, 0, 255, paint);
				start.drawBtm(canvas, paint,right_board_x+101, 655,x2);
				model.drawBtm(canvas, paint,right_board_x-86, 667);

				break;
			case 2:
				right_board_x=Coordinate.AnalogSpeedMove(right_board_x, 1062);
				Graphic.drawPic(canvas, right_board, right_board_x, 355, 0, 255, paint);
				Graphic.drawPic(canvas, right_stage2, right_board_x+11, 37, 0, 255, paint);
				if(activity.level_clear[activity.level][activity.difficulty]){
					Graphic.drawPic(canvas, right_boss01, right_board_x+2, 179, 0, 255, paint);
					}else{
						Graphic.drawPic(canvas, right_boss01_gray, right_board_x+2, 179, 0, 255, paint);
					}
				Graphic.drawPic(canvas, right_stage02info, right_board_x-4, 465, 0, 255, paint);
				start.drawBtm(canvas, paint,right_board_x+101, 655,x2);
				model.drawBtm(canvas, paint,right_board_x-86, 667);

				break;
			case 3:
				right_board_x=Coordinate.AnalogSpeedMove(right_board_x, 1062);
				Graphic.drawPic(canvas, right_board, right_board_x, 355, 0, 255, paint);
				Graphic.drawPic(canvas, right_stage3, right_board_x+11, 37, 0, 255, paint);
				if(activity.level_clear[activity.level][activity.difficulty]){
					Graphic.drawPic(canvas, right_boss01, right_board_x+2, 179, 0, 255, paint);
					}else{
						Graphic.drawPic(canvas, right_boss01_gray, right_board_x+2, 179, 0, 255, paint);
					}
				Graphic.drawPic(canvas, right_stage03info, right_board_x-4, 465, 0, 255, paint);
				start.drawBtm(canvas, paint,right_board_x+101, 655,x2);
				model.drawBtm(canvas, paint,right_board_x-86, 667);

				break;
			}

			//��stageFlag������0�A�N�|��������׻P���������
			if(stageFlag !=0){
				if(activity.hight_rank[activity.level][activity.difficulty]!=0){
					Graphic.drawPic(canvas, rank[activity.hight_rank[activity.level][activity.difficulty]-1], right_board_x+68, 585, 0, 255, paint);
				}
				num.drawNumberLeftStart(right_board_x-10, 528, activity.hight_score[activity.level][activity.difficulty], Number.Wite, canvas, paint);
				//�l�[����:��Flag = 0 �|���easy=================================================
				if(activity.difficulty==0){
					Graphic.drawPic(canvas, right_easy_ch, right_board_x-86, 655, 0, 255, paint);
					//�l�[����Flag = 0 �|���easy-----------------------------------------------

				}else if(activity.difficulty==1){
					Graphic.drawPic(canvas, right_normal_ch, right_board_x-86, 655, 0, 255, paint);

				}else if(activity.difficulty==2){
					Graphic.drawPic(canvas, right_hard_ch, right_board_x-86, 655, 0, 255, paint);

				}

				//�b�Y���=========================================================================
				if(!model.getBottom()){
					Graphic.drawPic(canvas, right_arrow_left, right_board_x-150, 655, 0, 255,  paint);
					Graphic.drawPic(canvas, right_arrow_left2, right_board_x-150, 655, 0, x2,  paint);
				}else if(model.getBottom()){
					Graphic.drawPic(canvas, right_arrow_right, right_board_x-150, 655, 0, 255,  paint);
					Graphic.drawPic(canvas, right_arrow_right2, right_board_x-150, 655,0, x2,  paint);
				}
				//�b�Y---------------------------------------------------------------------------

				//�����b������d�ɤ~��վ�������
				if(model.getBottom()){
					Graphic.drawPic(canvas, right_chmodel, 741, 588, 0, 255, paint);

					//�l�[�z�����ܤơA�ثe��ܪ����׬��G�A��l���׬��t=======================================
					if(activity.difficulty==0){
						easy.drawBtm(canvas, paint, 255);
						normal.drawBtm(canvas, paint,150);
						hard.drawBtm(canvas, paint, 150);
					}else if(activity.difficulty == 1){
						easy.drawBtm(canvas, paint, 150);
						normal.drawBtm(canvas, paint,255);
						hard.drawBtm(canvas, paint, 150);
					}else if(activity.difficulty == 2){
						easy.drawBtm(canvas, paint, 150);
						normal.drawBtm(canvas, paint,150);
						hard.drawBtm(canvas, paint, 255);
					}

					//�l�[�z�����ܤơA�ثe��ܪ����׬��G�A��l���׬��t----------------------------------------
				}
			}

			//canvas.drawText(String.valueOf(menuFlag), Coordinate.CoordinateX(360), Coordinate.CoordinateY(360), paint);
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		pointx=(int) event.getX();
		pointy=(int) event.getY();
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN://���U
			if(deJump == true)
			{
				//���b�������s�ƥ�====================================
				if(menubtm.isIn(pointx, pointy)){
					if(menuFlag == 0){
						sp.play(sp_id[5], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
						menuFlag = 1;
					}
					else if(menuFlag == 1){
						sp.play(sp_id[6], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
						left_btm1.setBottomTo(false);
						left_btm2.setBottomTo(false);
						left_btm3.setBottomTo(false);
						left_btm4.setBottomTo(false);
						left_btm5.setBottomTo(false);
						menuFlag = 0;
					}
				}
				//------------------------------
				if(menuFlag == 1)
				{
					if(left_btm1.isIn(pointx, pointy)){
						if(!left_btm1.getBottom()){
							sp.play(sp_id[7], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
							left_btm1.setBottomTo(true);
							left_btm2.setBottomTo(false);
							left_btm3.setBottomTo(false);
							left_btm4.setBottomTo(false);
							left_btm5.setBottomTo(false);
						}
						else if(left_btm1.getBottom()){
							left_btm1.setBottomTo(false);

						}
					}
					else if(left_btm2.isIn(pointx, pointy))
					{
						if(!left_btm2.getBottom()){
							sp.play(sp_id[7], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
							left_btm1.setBottomTo(false);
							left_btm2.setBottomTo(true);
							left_btm3.setBottomTo(false);
							left_btm4.setBottomTo(false);
							left_btm5.setBottomTo(false);
						}
						else if(left_btm2.getBottom()){
							left_btm2.setBottomTo(false);

						}
					}
					else if(left_btm3.isIn(pointx, pointy)){
						if(!left_btm3.getBottom()){
							sp.play(sp_id[7], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
							left_btm1.setBottomTo(false);
							left_btm2.setBottomTo(false);
							left_btm3.setBottomTo(true);
							left_btm4.setBottomTo(false);
							left_btm5.setBottomTo(false);
						}
						else if(left_btm3.getBottom()){
							left_btm3.setBottomTo(false);

						}

					}
					else if(left_btm4.isIn(pointx, pointy)){
						if(!left_btm4.getBottom()){
							sp.play(sp_id[7], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
							left_btm1.setBottomTo(false);
							left_btm2.setBottomTo(false);
							left_btm3.setBottomTo(false);
							left_btm4.setBottomTo(true);
							left_btm5.setBottomTo(false);

						}else if(left_btm4.getBottom()){
							left_btm4.setBottomTo(false);

						}
					}
					else if(left_btm5.isIn(pointx, pointy)){
						if(!left_btm5.getBottom()){

							activity.changeView(1);

						}
					}

					//-------------���ī��s������------------------
					if(left_btm3.getBottom()){ //�u�����s��true�ɤ~�ͮ�

						if(sebtm1.isIn(pointx, pointy)){
							if(!sebtm1.getBottom()){
								sebtm1.setBottomTo(true);
								sebtm2.setBottomTo(false);
								sebtm3.setBottomTo(false);
								sebtm4.setBottomTo(false);
								sebtm5.setBottomTo(false);
								sp.play(sp_id[0], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
								activity.sp_num=0;
								activity.writeData();
							}
							else if(sebtm1.getBottom()){
								sebtm1.setBottomTo(false);
								activity.sp_num=-1;
								activity.writeData();
							}
						}
						else if(sebtm2.isIn(pointx, pointy)){
							if(!sebtm2.getBottom()){
								sebtm1.setBottomTo(false);
								sebtm2.setBottomTo(true);
								sebtm3.setBottomTo(false);
								sebtm4.setBottomTo(false);
								sebtm5.setBottomTo(false);
								sp.play(sp_id[1], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
								activity.sp_num=1;
								activity.writeData();
							}
							else if(sebtm2.getBottom()){
								sebtm2.setBottomTo(false);
								activity.sp_num=-1;
								activity.writeData();
							}
						}
						else if(sebtm3.isIn(pointx, pointy)){
							if(!sebtm3.getBottom()){
								sebtm1.setBottomTo(false);
								sebtm2.setBottomTo(false);
								sebtm3.setBottomTo(true);
								sebtm4.setBottomTo(false);
								sebtm5.setBottomTo(false);
								sp.play(sp_id[2], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
								activity.sp_num=2;
								activity.writeData();
							}
							else if(sebtm3.getBottom()){
								sebtm3.setBottomTo(false);
								activity.sp_num=-1;
								activity.writeData();
							}
						}
						else if(sebtm4.isIn(pointx, pointy)){
							if(!sebtm4.getBottom()){
								sebtm1.setBottomTo(false);
								sebtm2.setBottomTo(false);
								sebtm3.setBottomTo(false);
								sebtm4.setBottomTo(true);
								sebtm5.setBottomTo(false);
								sp.play(sp_id[3], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
								activity.sp_num=3;
								activity.writeData();
							}
							else if(sebtm4.getBottom()){
								sebtm4.setBottomTo(false);
								activity.sp_num=-1;
								activity.writeData();
							}
						}
						else if(sebtm5.isIn(pointx, pointy)){
							if(!sebtm5.getBottom()){
								sebtm1.setBottomTo(false);
								sebtm2.setBottomTo(false);
								sebtm3.setBottomTo(false);
								sebtm4.setBottomTo(false);
								sebtm5.setBottomTo(true);
								sp.play(sp_id[4], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
								activity.sp_num=4;
								activity.writeData();
							}
							else if(sebtm5.getBottom()){
								sebtm5.setBottomTo(false);
								activity.sp_num=-1;
								activity.writeData();
							}
						}
					}
					if(mp_Volume_bar.isOn(pointx, pointy)){
						mp_Volume_bar.isOn=true;
					}
					if(sp_Volume_bar.isOn(pointx, pointy)){
						sp_Volume_bar.isOn=true;
					}

					//�t�׻P�P�w���s�ƥ�====================================================
					if(left_btm4.getBottom())
					{
						if(speed_left_arrow.isIn(pointx, pointy)){
							if(set_speed > 6){
								sp.play(sp_id[8], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
								set_speed--;
							}
						}
						if(speed_right_arrow.isIn(pointx, pointy)){
							if(set_speed < 10){
								sp.play(sp_id[8], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
								set_speed++;
							}
						}
						if(timing_left_arrow.isIn(pointx, pointy)){
							if(set_timing > 0){
								sp.play(sp_id[8], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
								set_timing--;
							}
						}
						if(timing_right_arrow.isIn(pointx, pointy)){
							if(set_timing < 15){
								sp.play(sp_id[8], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
								set_timing++;
							}
						}
					}
					//�t�׻P�P�w���s�ƥ�-----------------------------------------------------

				}
				//���b�������s�ƥ�ܦ�=====================================================

				//�k�b�����d���s�ƥ�}�l====================================================
				if(stbtn01.isIn(pointx, pointy)){
					if(!stbtn01.getBottom()){
						sp.play(sp_id[10],activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
						stageFlag = 1;
						activity.level=0;//�]�wgameview ���d�Ѽ�
						activity.difficulty=0;//gameview���װѼ�
						stbtn01.setBottomTo(true);
						stbtn02.setBottomTo(false);
						stbtn03.setBottomTo(false);


					}
					else if(stbtn01.getBottom())
					{
						stageFlag = 0;
						stbtn01.setBottomTo(false);
						model.setBottomTo(false);
					}
				}
				//�ĤG��==================================================
				if(stbtn02.isIn(pointx, pointy)&&st_02_flag){
					if(!stbtn02.getBottom()){
						sp.play(sp_id[10], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
						stageFlag = 2;
						activity.level=1;//�]�wgameview ���d�Ѽ�
						activity.difficulty=0;//gameview���װѼ�
						stbtn02.setBottomTo(true);
						stbtn01.setBottomTo(false);
						stbtn03.setBottomTo(false);
					}
					else if(stbtn02.getBottom())
					{
						stageFlag = 0;
						stbtn02.setBottomTo(false);
						model.setBottomTo(false);
					}
				}
				//�ĤG��--------------------------------------------------------
				//�ĤT��==================================================
				if(stbtn03.isIn(pointx, pointy)&&st_03_flag){
					if(!stbtn03.getBottom()){
						sp.play(sp_id[10], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
						stageFlag = 3;
						activity.level=2;//�]�wgameview ���d�Ѽ�
						activity.difficulty=0;//gameview���װѼ�
						stbtn03.setBottomTo(true);
						stbtn01.setBottomTo(false);
						stbtn02.setBottomTo(false);
					}
					else if(stbtn03.getBottom())
					{
						stageFlag = 0;
						stbtn03.setBottomTo(false);
						model.setBottomTo(false);
					}
				}
				//�ĤT��--------------------------------------------------------

				if(stageFlag!=0){
					if(start.isIn(pointx, pointy)){
						sp.play(sp_id[10], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
						activity.video_select=2;
						activity.changeView(0);
					}

					//�����׽վ���s�ƥ�
					if(model.isIn(pointx, pointy)){
						if(model.getBottom()){

							model.setBottomTo(false);
						}else{
							model.setBottomTo(true);
							sp.play(sp_id[9], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
						}
					}
					if(model.getBottom()){
						if(easy.isIn(pointx, pointy)){
							sp.play(sp_id[9], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
							activity.difficulty=0;//gameview���װѼ�
							model.setBottomTo(false);
						}
						if(normal.isIn(pointx, pointy)){
							sp.play(sp_id[9], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
							activity.difficulty=1;//gameview���װѼ�
							model.setBottomTo(false);
						}
						if(hard.isIn(pointx, pointy)){
							sp.play(sp_id[9], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
							activity.difficulty=2;//gameview���װѼ�
							model.setBottomTo(false);
						}
					}
				}


				//�k�b�����d���s�ƥ�ܦ�====================================================

			}
			deJump = false;
			break;
		case MotionEvent.ACTION_MOVE:
			if(mp_Volume_bar.isOn){
				mp_Volume_bar.setSeekBarX(pointx);
			}
			if(sp_Volume_bar.isOn){
				sp_Volume_bar.setSeekBarX(pointx);
			}
			break;
			//---------------------------------------
		case MotionEvent.ACTION_UP:
			if(deJump == false){
				/*if(left_btm1.isIn(pointx, pointy)){
				}
				else if(left_btm2.isIn(pointx, pointy)){

				}
				else if(left_btm3.isIn(pointx, pointy)){

				}
				else if(left_btm4.isIn(pointx, pointy)){

				}
				else if(left_btm5.isIn(pointx, pointy)){

				}*/
				if(mp_Volume_bar.isOn){
					int temp=(int)mp_Volume_bar.getSeekBarValue();
					mp_Volume_bar.setSeekBarFloat((temp-(temp%10)));
					activity.mp_Voiume=(float) ((temp-(temp%10))/100.0);
					activity.writeData();
					mp_Volume_bar.isOn=false;
				}
				if(sp_Volume_bar.isOn){
					int temp=(int)sp_Volume_bar.getSeekBarValue();
					sp_Volume_bar.setSeekBarFloat((temp-(temp%10)));
					activity.sp_Voiume=(float) ((temp-(temp%10))/100.0);
					activity.writeData();
					sp_Volume_bar.isOn=false;
				}
			}
			deJump  = true;
			break;
		}
		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {

	}

	public void surfaceDestroyed(SurfaceHolder arg0) {//�P���ɳQ�I�s
		activity.speed=set_speed-5;
		activity.timing=set_timing-5;
		wmap.recycle();
		left_back.recycle();
		left_exit.recycle();    //<<�b�Y-�L��  ���}MENU��
		left_exit2.recycle();   //<<�b�Y-�`��

		menubtn.recycle();      //>>�b�Y-�L��  �I�sMENU��
		menubtn2.recycle();    //>>�b�Y-�`��

		left_exitback.recycle(); //���}���s�I��
		left_btmback.recycle();  //MENU�ﶵ�I��
		btnvol.recycle();
		btnvol2.recycle();
		chbtmse.recycle();
		chbtmse2.recycle();
		song_vol.recycle();
		song_vol2.recycle();
		remain.recycle();
		remain2.recycle();

		//�t�׻P�P�w�Ϥ��ŧi=====================================
		timing.recycle();
		timing2.recycle();
		timing_back.recycle();
		timing_r_arrow.recycle();
		timing_l_arrow.recycle();
		for(int i=0;i<11;i++){
			timing_num[i].recycle();
		}
		//�t�׻P�P�w�Ϥ��ŧi----------------------------------------


		volchback.recycle();
		volBar.recycle();
		volbtn.recycle();
		sechange.recycle();

		//�k�����d��ܰ϶�
		right_board.recycle();
		right_chmodel.recycle();
		right_stage01.recycle();
		right_freely.recycle();
		right_boss01.recycle();
		right_st01Font.recycle();
		right_easy.recycle();
		right_normal.recycle();
		right_hard.recycle();

		//�s�ŧi�������׻P�b�Y�Ϥ�===========================================
		right_easy_ch.recycle();
		right_normal_ch.recycle();
		right_hard_ch.recycle();
		right_arrow_left.recycle();
		right_arrow_left2.recycle();
		right_arrow_right.recycle();
		right_arrow_right2.recycle();
		//�s�ŧi�������׻P�b�Y�Ϥ�-------------------------------------------

		//��2�B��3���d�һݭn���Ϥ�===========================================
		right_stage02info.recycle();
		right_stage03info.recycle();
		// right_boss_del.recycle();  //�L������|��ܪ�BOSS�Ϥ�
		stagebtn_green.recycle();
		stagebtn_green_l.recycle();
		right_stage2.recycle();
		right_stage3.recycle();
		//��2�B��3���d�һݭn���Ϥ�-------------------------------------------

		right_start.recycle();
		stage01btn0.recycle();
		stage01btn.recycle();

		se01.recycle();
		se01l.recycle();
		se02.recycle();
		se02l.recycle();
		se03.recycle();
		se03l.recycle();
		se04.recycle();
		se04l.recycle();
		se05.recycle();
		se05l.recycle();

		model_ch.recycle();


		menubtm.recycle(); //MENU���s
		left_btm1.recycle();
		left_btm2.recycle();
		left_btm3.recycle();
		left_btm4.recycle();
		left_btm5.recycle();
		speed_left_arrow.recycle();
		speed_right_arrow.recycle();
		timing_left_arrow.recycle();
		timing_right_arrow.recycle();
		mp_Volume_bar.recycle();
		sp_Volume_bar.recycle();


		sebtm1.recycle();
		sebtm2.recycle();
		sebtm3.recycle();
		sebtm4.recycle();
		sebtm5.recycle();

		stbtn01.recycle();
		stbtn02.recycle();  //�ĤG�����s
		stbtn03.recycle();  //�ĤT�����s
		easy.recycle();
		normal.recycle();
		hard.recycle();
		model.recycle();
		start.recycle();

		//�b�Y���s�ŧi================================================
		// arrow.recycle();
		//�b�Y���s�ŧi--------------------------------------------------------------------------------
		num.recycle();
		for(int i=0;i<rank.length;i++){
			rank[i].recycle();
		}
		System.gc();
		Constant.Flag=false;
		sp.release();
		mp.stop();
	}


}
