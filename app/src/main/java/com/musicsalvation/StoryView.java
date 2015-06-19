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
public class StoryView extends SurfaceView
implements SurfaceHolder.Callback{

    //======新介面圖片宣告===============
    Bitmap map_back;
    //Bitmap map_frame;

    Bitmap map_frame_upbar;
    Bitmap map_frame_leftbar;
    Bitmap map_frame_rightbar;
    Bitmap map_frame_underbar;


    Bitmap map_set_btn;
    Bitmap map_set_btn2; //會轉的小齒輪
    Bitmap map_set_btn2_back;

    Bitmap map_stage01;
    Bitmap map_stage02;
    Bitmap map_stage03;
    Bitmap map_stage01_back;
    Bitmap map_stage02_back;
    Bitmap map_stage03_back;

    Bitmap map_easy_btn_f;
    Bitmap map_easy_btn_t;
    Bitmap map_normal_btn_f;
    Bitmap map_normal_btn_t;
    Bitmap map_hard_btn_f;
    Bitmap map_hard_btn_t;

    Bitmap map_start_btn;
    Bitmap map_startbar;
    Bitmap map_start_btn_back;

    Bitmap map_hand;
    Bitmap map_quest_btn;
    Bitmap map_quest_back;
    Bitmap map_sc_rank_bar;



    Botton setting_btn;
    Botton quest_btn;

    //最終確認欄位元件
    Bitmap map_checK_bar;
    Bitmap map_check_font;
    Bitmap map_check_return;
    Bitmap map_check_light;
    Bitmap map_check_dark;
    Bitmap map_s01data;
    Bitmap map_s02data;
    Bitmap map_s03data;
    Bitmap map_virusdata_grey;
    Bitmap map_virusdata;
    Bitmap map_check_easy;
    Bitmap map_check_nornmal;
    Bitmap map_check_hard;
    Bitmap map_ok_btn;
    Bitmap map_ok_btn2;
    Botton return_btn;
    Botton check_ok_btn;
    //Botton stageselect;

    Setting setting;

    Boolean set_baralpha_Flag = false;




    //--------------------新介面圖片宣告-----------------------
	Bitmap wmap;
	Bitmap left_back;
	Bitmap left_exit;    //<<箭頭-淺色  離開MENU用
	Bitmap left_exit2;   //<<箭頭-深色

	Bitmap menubtn;      //>>箭頭-淺色  呼叫MENU用
	Bitmap menubtn2;    //>>箭頭-深色

	Bitmap left_exitback; //離開按鈕背景
	Bitmap left_btmback;  //MENU選項背景
	Bitmap btnvol;
	Bitmap btnvol2;
	Bitmap chbtmse;
	Bitmap chbtmse2;
	Bitmap song_vol;
	Bitmap song_vol2;
	Bitmap remain;
	Bitmap remain2;

	//速度與判定圖片宣告=====================================
	Bitmap timing;
	Bitmap timing2;
	Bitmap timing_back;
	Bitmap timing_r_arrow;
	Bitmap timing_l_arrow;
	Bitmap timing_num[] = new Bitmap [16];
	//速度與判定圖片宣告----------------------------------------


	Bitmap volchback;
	Bitmap volBar;
	Bitmap volbtn;
	Bitmap sechange;

	//右邊關卡選擇區塊
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

	//新宣告的難易度與箭頭圖片===========================================
	Bitmap right_easy_ch;
	Bitmap right_normal_ch;
	Bitmap right_hard_ch;
	Bitmap right_arrow_left;
	Bitmap right_arrow_left2;
	Bitmap right_arrow_right;
	Bitmap right_arrow_right2;
	//新宣告的難易度與箭頭圖片-------------------------------------------

	//第2、第3關卡所需要的圖片===========================================
	Bitmap right_stage02info;
	Bitmap right_stage03info;
	Bitmap right_boss_del;  //過關之後會顯示的BOSS圖片
	Bitmap stagebtn_green;
	Bitmap stagebtn_green_l;
	Bitmap right_stage2;
	Bitmap right_stage3;
	//第2、第3關卡所需要的圖片-------------------------------------------

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


	Botton menubtm; //MENU按鈕
	Botton left_btm1;
	Botton left_btm2;
	Botton left_btm3;
	Botton left_btm4;
	Botton left_btm5;
	Botton speed_left_arrow;
	Botton speed_right_arrow;
	Botton timing_left_arrow;
	Botton timing_right_arrow;
	MySeekBar mp_Volume_bar;
	MySeekBar sp_Volume_bar;


	Botton sebtm1;
	Botton sebtm2;
	Botton sebtm3;
	Botton sebtm4;
	Botton sebtm5;

	Botton stbtn01;
	boolean st_02_flag;
	Botton stbtn02;  //第二關按鈕
	boolean st_03_flag;
	Botton stbtn03;  //第三關按鈕
	Botton easy;
	Botton normal;
	Botton hard;

	Botton start;


	//箭頭按鈕宣告================================================
	Botton arrow;
	//箭頭按鈕宣告------------------------------------------------


	MediaPlayer mp;
	SoundPool sp;
	int sp_id_s[];

	int pointx;//觸控到螢幕的x座標
	int pointy;//觸控到螢幕的y座標
	boolean deJump = true;

	float rot=0;
	int x2=100;
	int alpha2 = 10;


	int stageFlag = 1;  //右半部



	int set_speed = 6;
	int set_timing = 5;
    int setalpha = 0;
    int start_alpha;

    int check_alpha = 0;

    int upbar_my = -67;
    int upbar_my2 = 67;
    int rightbar_mx = 1325;
    int rightbar_mx2 = 1235;
    int leftbar_mx = -50;
    int leftbar_mx2 = 50;
    int underbar_my = 767;
    int underbar_my2 = 673;
    int set_btn_mx = -47;
    int set_btn_mx2 = 47;
    int set_btn2_mx = -55,set_btn2_mx2 = 39;
    int sc_rank_bar_my = 790 , sc_rank_bar_my2 = 648;
    int hand_mx = 915,hand_mx2 = 1155,hand_count=3,hand_alpha = 0,hand_recount = 0;

    int checkbar_mx = 1860,checkbar_mx2 = 640;
    int checkbar_light_mx = 1270,checkbar_light_mx2 = 52;
    boolean check_Flag;
    //int start_btn_my = 805 , getStart_btn_my2 = 635;


    int song[] = new int[3];

	int stagecount = 0;

    int downX = 0;
    int downY = 0;
    int upX = 0;
    int upY = 0;
    int stageselect = 0;
    int stage_standby_Flag = 0;



	//RANK===============================
	Bitmap rank[];
	//RANK------------------------------
	Number num;

	Paint paint;			//畫筆的參考
	int i=0,j=5;
	MainActivity activity;

	public StoryView(MainActivity mainActivity) {
		super(mainActivity);
		this.activity = mainActivity;
		this.getHolder().addCallback(this);//設定生命周期回調接口的實現者


	}

	public Bitmap LoadBitmap(int r){
		return BitmapFactory.decodeResource(getResources(), r);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		set_speed=activity.io.speed+5;
		set_timing=activity.io.timing+5;
		paint = new Paint();//建立畫筆
		paint.setAntiAlias(true);//開啟抗鋸齒


        //===============新介面圖片載入=======================

        stage_standby_Flag=0;
        hand_count = 3;
        set_baralpha_Flag = false;
        upbar_my = -67;
        rightbar_mx = 1325;
        leftbar_mx = -53;
        underbar_my = 767;
        set_btn_mx = -47;
        set_btn2_mx = -55;
        sc_rank_bar_my = 790;
        hand_mx = 915;
        hand_alpha = 0;
        start_alpha = setalpha;
        hand_recount = 0;
        checkbar_mx = 1860;
        checkbar_light_mx = 1270;
        check_Flag = false;



        map_back = Graphic.bitSize(LoadBitmap(R.drawable.mapview_back), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
		map_stage01_back = Graphic.bitSize(LoadBitmap( R.drawable.mapview_stage01_back), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
        map_stage02_back = Graphic.bitSize(LoadBitmap( R.drawable.mapview_stage02_back), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);
        map_stage03_back = Graphic.bitSize(LoadBitmap( R.drawable.mapview_stage03_back), Constant.DEFULT_WIDTH, Constant.DEFULT_HIGHT);

        map_stage01 = Graphic.bitSize(LoadBitmap( R.drawable.mapview_stage1_font), 234, 63);
        map_stage02 = Graphic.bitSize(LoadBitmap( R.drawable.mapview_stage2_font), 234, 63);
        map_stage03 = Graphic.bitSize(LoadBitmap( R.drawable.mapview_stage3_font), 234, 63);

        map_start_btn = Graphic.bitSize(LoadBitmap( R.drawable.mapview_start_btn), 241, 171);
        map_startbar = Graphic.bitSize(LoadBitmap( R.drawable.mapview_startbar), 342, 342);
        map_start_btn_back = Graphic.bitSize(LoadBitmap( R.drawable.mapview_start2_btn), 241, 171);

        map_set_btn = Graphic.bitSize(LoadBitmap( R.drawable.mapview_set_btn), 95, 146);
        map_set_btn2 = Graphic.bitSize(LoadBitmap( R.drawable.map_set_btn2), 59, 59);
        map_set_btn2_back = Graphic.bitSize(LoadBitmap( R.drawable.map_set_btn2_back), 59, 59);
        map_easy_btn_f = Graphic.bitSize(LoadBitmap( R.drawable.mapview_easy_btn_f), 393, 104);
        map_easy_btn_t = Graphic.bitSize(LoadBitmap( R.drawable.mapview_easy_btn_t), 393, 104);
        map_normal_btn_f = Graphic.bitSize(LoadBitmap( R.drawable.mapview_normal_btn_f), 393, 104);
        map_normal_btn_t = Graphic.bitSize(LoadBitmap( R.drawable.mapview_normal_btn_t), 393, 104);
        map_hard_btn_f = Graphic.bitSize(LoadBitmap( R.drawable.mapview_hard_btn_f), 393, 104);
        map_hard_btn_t = Graphic.bitSize(LoadBitmap( R.drawable.mapview_hard_btn_t), 393, 104);

        map_frame_leftbar = Graphic.bitSize(LoadBitmap( R.drawable.mapview_frame_leftbar), 102, 605);
        map_frame_rightbar = Graphic.bitSize(LoadBitmap( R.drawable.mapview_frame_rightbar), 90, 720);
        map_frame_underbar = Graphic.bitSize(LoadBitmap( R.drawable.mapview_frame_underbar), 1280, 94);
        map_frame_upbar = Graphic.bitSize(LoadBitmap( R.drawable.mapview_frame_upbar), 1280, 134);

        map_hand = Graphic.bitSize(LoadBitmap( R.drawable.mapview_hand), 63, 76);
        map_quest_btn = Graphic.bitSize(LoadBitmap( R.drawable.mapview_quest_btn), 105, 198);
        map_quest_back = Graphic.bitSize(LoadBitmap( R.drawable.mapview_quest_back), 105, 198);
        map_sc_rank_bar = Graphic.bitSize(LoadBitmap( R.drawable.mapview_sc_rank_bar), 1163, 140);



        setting = new Setting(activity);
        setting_btn = new Botton(activity, map_set_btn, map_set_btn, set_btn_mx, 160);
        quest_btn = new Botton(activity,map_quest_back,map_quest_btn,set_btn_mx-1,621);

        map_checK_bar = Graphic.bitSize(LoadBitmap( R.drawable.mapview_checkbar), 1280, 412);
        map_check_font = Graphic.bitSize(LoadBitmap( R.drawable.mapview_finallcheck_font), 1246, 107);
        map_check_return = Graphic.bitSize(LoadBitmap( R.drawable.mapview_check_return_btn), 140, 131);
        map_check_light = Graphic.bitSize(LoadBitmap( R.drawable.mapview_checkbar_light), 77, 174);
        map_check_dark = Graphic.bitSize(LoadBitmap( R.drawable.mapview_checkbar_dark), 77, 174);
        map_s01data = Graphic.bitSize(LoadBitmap( R.drawable.mapview_s01data), 415, 287);
        map_s02data = Graphic.bitSize(LoadBitmap( R.drawable.mapview_s02data), 415, 287);;
        map_s03data = Graphic.bitSize(LoadBitmap( R.drawable.mapview_s03data), 415, 287);;
        map_virusdata_grey = Graphic.bitSize(LoadBitmap( R.drawable.map_bvirus_live), 596, 292);
        map_virusdata = Graphic.bitSize(LoadBitmap( R.drawable.map_bvirus_dead), 596, 292);
        map_check_easy = Graphic.bitSize(LoadBitmap( R.drawable.mapview_fn_easy), 137, 50);
        map_check_nornmal = Graphic.bitSize(LoadBitmap( R.drawable.mapview_fn_normal), 137, 50);
        map_check_hard = Graphic.bitSize(LoadBitmap( R.drawable.mapview_fn_hard), 137, 50);
        map_ok_btn = Graphic.bitSize(LoadBitmap( R.drawable.mapview_ok_btn), 241, 147);
        map_ok_btn2 = Graphic.bitSize(LoadBitmap( R.drawable.mapview_ok_btn2), 241, 147);

        return_btn = new Botton(activity, map_check_return, map_check_return, 1210, 275);
        check_ok_btn = new Botton(activity, map_ok_btn, map_ok_btn, 640, 647);


		st_02_flag=false;
		st_03_flag=false;
		for(int j=0;j<3;j++){
			if(activity.io.level_clear[0][j]){
				st_02_flag=true;
			}
			if(activity.io.level_clear[1][j]){
				st_03_flag=true;
			}
		}



		start = new Botton(activity, map_start_btn, map_start_btn, 1280/2, 635);


		easy  = new Botton(activity, map_easy_btn_t, map_easy_btn_f, 450, 50);
		normal  = new Botton(activity, map_normal_btn_t, map_normal_btn_f, 770, 50);
		hard  = new Botton(activity, map_hard_btn_t, map_hard_btn_f, 1090, 50);

        set_baralpha_Flag = false;
        setalpha = 0;

        song[0] = R.raw.celluloid_yuyao_cut;
        song[1] = R.raw.tipsydessert_yuyao_cut;
        song[2] = R.raw.kokoronashi_cut;

		mp = MediaPlayer.create(this.getContext(),song[activity.io.level]);

		mp.setVolume(activity.io.mp_Voiume, activity.io.mp_Voiume);
		mp.setLooping(true);
		mp.start();

		sp=new SoundPool(4, AudioManager.STREAM_MUSIC, 5);
		sp_id_s=new int[11];
        sp_id_s[0]=sp.load(activity, R.raw.tambourine, 1);
        sp_id_s[1]=sp.load(activity, R.raw.drum_cymbal, 1);
        sp_id_s[2]=sp.load(activity, R.raw.drum_snare, 1);
        sp_id_s[3]=sp.load(activity, R.raw.fall, 1);
        sp_id_s[4]=sp.load(activity, R.raw.voice_dog, 1);
        sp_id_s[5]=sp.load(activity, R.raw.left_menu_on, 1);
        sp_id_s[6]=sp.load(activity, R.raw.left_menu_off, 1);
        sp_id_s[7]=sp.load(activity, R.raw.leftm_btn, 1);
        sp_id_s[8]=sp.load(activity, R.raw.left_num, 1);
        sp_id_s[9]=sp.load(activity, R.raw.stagebtn, 1);
        sp_id_s[10]=sp.load(activity, R.raw.start, 1);



		num=new Number(getResources());
		num.setSize(28, 50);
		rank=new Bitmap[7];
		rank[6]=Graphic.LoadBitmap(getResources(), R.drawable.r_s, 54,72,true);
		for(int i=0;i<=5;i++){
			rank[i]=Graphic.LoadBitmap(getResources(), R.drawable.r_f-i, 54, 72,true);
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
					SurfaceHolder myholder=StoryView.this.getHolder();
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


            //新介面==================================================
            x2+=alpha2;
            if(x2 >= 250){
                alpha2 = -10;
            }
            if(x2 < 50){
                alpha2 = 10;
            }
            Graphic.drawPic(canvas, map_back, 640, 360, 0, 255, paint);

            //切換關卡時的動作============================================

            if(stage_standby_Flag !=0) {
                if (set_baralpha_Flag) {
                    setalpha -= 40;
                    start_alpha = setalpha;
                    if (setalpha < 0) {

                        setalpha = 0;
                        set_baralpha_Flag = false;

                        if (stageselect == 1) {
                            stageselect = 0;
                            stageFlag++;
                            if (stageFlag > 3 || stageFlag == 0) {
                                stageFlag = 1;
                            }
                            activity.io.level = stageFlag - 1;

                            if (mp != null) {

                                mp.stop();
                                mp.release();
                                mp = null;
                                mp = MediaPlayer.create(this.getContext(), song[activity.io.level]);
                                mp.setVolume(activity.io.mp_Voiume, activity.io.mp_Voiume);
                                mp.setLooping(true);
                                mp.start();

                            }

                        } else if (stageselect == -1) {
                            stageselect = 0;
                            stageFlag--;
                            if (stageFlag == 0) {
                                stageFlag = 3;
                            }
                            activity.io.level = stageFlag - 1;

                            if (mp != null) {

                                mp.stop();
                                mp.release();
                                mp = null;
                                mp = MediaPlayer.create(this.getContext(), song[activity.io.level]);
                                mp.setVolume(activity.io.mp_Voiume, activity.io.mp_Voiume);
                                mp.setLooping(true);
                                mp.start();

                            }
                        }
                    }
                } else if (!set_baralpha_Flag) {
                    setalpha += 40;
                    if (setalpha >= 255) {
                        setalpha = 255;
                        start_alpha = x2;
                    }
                }
            }
            //切換關卡時的動作-------------------------------------
            switch(stageFlag){
                case 0:
                    break;
                case 1:
                    Graphic.drawPic(canvas,map_stage01_back , 1280/2, 720/2, 0, setalpha, paint);
                    break;
                case 2:
                    Graphic.drawPic(canvas,map_stage02_back , 1280/2, 720/2, 0, setalpha, paint);
                    break;
                case 3:
                    Graphic.drawPic(canvas,map_stage03_back , 1280/2, 720/2, 0, setalpha, paint);
                    break;
            }



            if(stage_standby_Flag == 0)
            {
                start_alpha = setalpha;
                underbar_my = Coordinate.AnalogSpeedMove(underbar_my,underbar_my2);
                leftbar_mx = Coordinate.AnalogSpeedMove(leftbar_mx,leftbar_mx2);
                rightbar_mx = Coordinate.AnalogSpeedMove(rightbar_mx,rightbar_mx2);
                upbar_my = Coordinate.AnalogSpeedMove(upbar_my,upbar_my2);
                set_btn2_mx = Coordinate.AnalogSpeedMove(set_btn2_mx,set_btn2_mx2);
                set_btn_mx = Coordinate.AnalogSpeedMove(set_btn_mx,set_btn_mx2);
                sc_rank_bar_my = Coordinate.AnalogSpeedMove(sc_rank_bar_my,sc_rank_bar_my2);

                if(underbar_my == underbar_my2 && leftbar_mx == leftbar_mx2 && rightbar_mx == rightbar_mx2 && upbar_my == upbar_my2){
                    stage_standby_Flag = 1;

                }

            }

            Graphic.drawPic(canvas, map_sc_rank_bar, 641, sc_rank_bar_my, 0, setalpha, paint);
            Graphic.drawPic(canvas, map_frame_underbar, 640, underbar_my, 0, 255, paint);
            Graphic.drawPic(canvas, map_frame_leftbar, leftbar_mx, 340, 0, 255, paint);
            Graphic.drawPic(canvas, map_frame_rightbar, rightbar_mx, 360, 0, 255, paint);
            Graphic.drawPic(canvas, map_frame_upbar, 640, upbar_my, 0, 255, paint);


                if(stageFlag == 1) {
                    Graphic.drawPic(canvas, map_stage01, 120, 43, 0, setalpha, paint);
                }else if(stageFlag == 2){
                    Graphic.drawPic(canvas, map_stage02, 120, 43, 0, setalpha, paint);
                }else if(stageFlag == 3){
                    Graphic.drawPic(canvas, map_stage03, 120, 43, 0, setalpha, paint);
                }
                rot+= 3;
                if(rot == 360){
                    rot = 0;
                }
            if(activity.io.hight_rank[activity.io.level][activity.io.difficulty]!=0){
                Graphic.drawPic(canvas, rank[activity.io.hight_rank[activity.io.level][activity.io.difficulty]-1], 1160, 650, 0, setalpha, paint);
            }
                num.drawNumberRightStart(430, 650, activity.io.hight_score[activity.io.level][activity.io.difficulty], Number.Blue, canvas, paint);
				setting_btn.drawBtm(canvas, paint,set_btn_mx,160);
                quest_btn.drawBtm(canvas,paint,set_btn_mx+1,621);
                Graphic.drawPic(canvas, map_set_btn2_back, set_btn2_mx, 121, rot, setalpha, paint);
                Graphic.drawPic(canvas, map_set_btn2, set_btn2_mx, 121, rot, start_alpha, paint);
                Graphic.drawPic(canvas, map_quest_back, set_btn_mx+1, 621, 0, start_alpha, paint);
                easy.drawBtm(canvas, paint,setalpha);
                normal.drawBtm(canvas, paint,setalpha);
                hard.drawBtm(canvas, paint,setalpha);
                if(!check_Flag) {
                    Graphic.drawPic(canvas, map_start_btn_back, 640, 635, 0, setalpha, paint);
                    start.drawBtm(canvas, paint, start_alpha);
                    Graphic.drawPic(canvas, map_startbar, 640, 669, rot, setalpha, paint);
                }

            //控制觸控手勢提示==========================================================
                if(stage_standby_Flag !=0 && hand_count > 0){

                    Graphic.drawPic(canvas, map_hand, hand_mx, 570, 0, hand_alpha, paint);
                    if(hand_mx != hand_mx2){
                        hand_alpha+=40;
                    }
                    if(hand_alpha > 255){
                        hand_alpha = 255;
                        hand_mx = Coordinate.AnalogSpeedMove(hand_mx,hand_mx2);
                    }
                    if(hand_mx == hand_mx2){
                        hand_alpha-=40;
                        if(hand_alpha < 0) {
                            hand_alpha = 0;
                            hand_count--;
                            hand_mx = 915;
                        }
                    }
                }
            if(hand_count <= 0){

                hand_recount++;
                if(hand_recount >=1800){
                    hand_recount = 0;
                    hand_count = 3;
                }
            }
            //控制觸控手勢提示-----------------------------------------------------------------




                if(activity.io.difficulty==0){

                    Graphic.drawPic(canvas, map_easy_btn_t, 450, 50, 0, start_alpha, paint);
                    //追加條件Flag = 0 會顯示easy-----------------------------------------------
                }else if(activity.io.difficulty==1){

                    Graphic.drawPic(canvas, map_normal_btn_t, 770, 50, 0, start_alpha, paint);
                }else if(activity.io.difficulty==2){

                    Graphic.drawPic(canvas, map_hard_btn_t, 1090, 50, 0, start_alpha, paint);
                }
            //最終確認欄============================================================
            Graphic.drawPic(canvas, map_checK_bar, checkbar_mx, 395, 0, 255, paint);
            Graphic.drawPic(canvas, map_check_dark, checkbar_light_mx, 383, 0, 255, paint);
            Graphic.drawPic(canvas, map_check_light, checkbar_light_mx, 383, 0, start_alpha, paint);

            if(check_Flag){
				checkbar_mx = Coordinate.AnalogSetSpeedMove(checkbar_mx,checkbar_mx2,2);
				checkbar_light_mx = Coordinate.AnalogSetSpeedMove(checkbar_light_mx,checkbar_light_mx2,2);


                if(checkbar_mx == checkbar_mx2 && checkbar_light_mx == checkbar_light_mx2){
                    check_alpha = 255;

                    Graphic.drawPic(canvas, map_ok_btn2, 640, 647, 0, 255, paint);

                    Graphic.drawPic(canvas, map_check_font, 675, 244, 0, check_alpha, paint);
                    return_btn.drawBtm(canvas,paint);
                    check_ok_btn.drawBtm(canvas,paint,start_alpha);
                    switch(stageFlag){
                        case 0:
                            break;
                        case 1:
                            Graphic.drawPic(canvas,map_s01data , 267, 419, 0, check_alpha, paint);
                            if(activity.io.level_clear[activity.io.level][activity.io.difficulty]){

                                Graphic.drawPic(canvas, map_virusdata, 887, 424, 0, 255, paint);
                            }else{
                                Graphic.drawPic(canvas, map_virusdata_grey, 887,424, 0, 255, paint);
                            }
                            break;
                        case 2:
                            Graphic.drawPic(canvas,map_s02data , 267, 419, 0, check_alpha, paint);
                            if(activity.io.level_clear[activity.io.level][activity.io.difficulty]){

                                Graphic.drawPic(canvas, map_virusdata, 887, 424, 0, 255, paint);
                            }else{
                                Graphic.drawPic(canvas, map_virusdata_grey, 887,424, 0, 255, paint);
                            }
                            break;
                        case 3:
                            Graphic.drawPic(canvas,map_s03data , 267, 419, 0, check_alpha, paint);
                            if(activity.io.level_clear[activity.io.level][activity.io.difficulty]){

                                Graphic.drawPic(canvas, map_virusdata, 887, 424, 0, 255, paint);
                            }else{
                                Graphic.drawPic(canvas, map_virusdata_grey, 887,424, 0, 255, paint);
                            }
                            break;
                    }
                    if(activity.io.difficulty==0){
                        Graphic.drawPic(canvas, map_check_easy, 289, 535, 0, check_alpha, paint);
                        //追加條件Flag = 0 會顯示easy-----------------------------------------------
                    }else if(activity.io.difficulty==1){
                        Graphic.drawPic(canvas, map_check_nornmal, 289, 535, 0, check_alpha, paint);
                    }else if(activity.io.difficulty==2){
                        Graphic.drawPic(canvas, map_check_hard, 289, 535, 0, check_alpha, paint);
                    }
                }
            }else{
                check_alpha = 0;

				checkbar_mx = Coordinate.AnalogSetSpeedMove(checkbar_mx,1860,2);
				checkbar_light_mx = Coordinate.AnalogSetSpeedMove(checkbar_light_mx,1270,2);
                /*if(checkbar_light_mx <= 1270){
                    int x = 1860 -checkbar_mx ;
                    if(x>150){
                        checkbar_light_mx +=150;
                    }else{
                        checkbar_light_mx = 1270;
                    }
                }*/


            }



            setting.Draw(canvas, paint);
			//canvas.drawText(String.valueOf(menuFlag), Coordinate.CoordinateX(360), Coordinate.CoordinateY(360), paint);





		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		pointx=(int) event.getX();
		pointy=(int) event.getY();
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN://按下
			if(deJump == true)
			{
                if(setting.main_flag) {
                    setting.Action_Dowm(pointx, pointy);
                }

				if(!setting.main_flag) {
                    //新介面用關卡選擇----------------------------
                    if(check_Flag){
                        if(check_ok_btn.isIn(pointx,pointy)){
                            sp.play(sp_id_s[10], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
                            check_Flag = false;

                            activity.io.video_select=2;
                            activity.changeView(0);
                        }
                        if(return_btn.isIn(pointx,pointy)){
                            check_Flag = false;
                        }
                    }
                    if(!check_Flag) {
                        if (start.isIn(pointx, pointy)) {
                            sp.play(sp_id_s[10], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
                            check_Flag = true;

                        }
                        downX = pointx;
                        downY = pointy;
                    }

						if(easy.isIn(pointx, pointy)){
							sp.play(sp_id_s[9], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
							activity.io.difficulty=0;//gameview難度參數
							//model.setBottomTo(false);
						}
						if(normal.isIn(pointx, pointy)){
							sp.play(sp_id_s[9], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
							activity.io.difficulty=1;//gameview難度參數
							//model.setBottomTo(false);
						}
						if(hard.isIn(pointx, pointy)){
							sp.play(sp_id_s[9], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
							activity.io.difficulty=2;//gameview難度參數

						}

				}
			//右半部關卡按鈕事件至此====================================================

			}
			deJump = false;
			break;
		case MotionEvent.ACTION_MOVE:
            if(setting.main_flag) {
                setting.Action_Move(pointx, pointy);
                if (setting.getMainFlag()) {
                    break;
                }
            }

			break;
			//---------------------------------------
		case MotionEvent.ACTION_UP:
			if(deJump == false){


                if(setting.main_flag) {
                    setting.Action_Up(pointx, pointy);
                }

                if(!setting.main_flag) {
                    if(!check_Flag) {
                        upX = pointx;
                        upY = pointy;

                        float move_x = upX - downX;
                        if (move_x > 100) {
                            set_baralpha_Flag = true;
                            stageselect = 1;

                        } else if (move_x < -100) {
                            set_baralpha_Flag = true;
                            stageselect = -1;

                        }
                    }

                    if (setting.main_alpha == 0) {
                        if (setting_btn.isIn(pointx, pointy)) {
                            setting.start();
                        }
                    }
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
	public void surfaceDestroyed(SurfaceHolder arg0) {//銷毀時被呼叫
        stage_standby_Flag = 0;

        map_easy_btn_f.recycle();
        map_stage01.recycle();
        map_back.recycle();
        map_set_btn.recycle();
        map_easy_btn_t.recycle();
        map_hard_btn_f.recycle();
        map_hard_btn_t.recycle();
        map_normal_btn_f.recycle();
        map_normal_btn_t.recycle();
        map_stage01_back.recycle();
        map_stage02.recycle();
        map_stage02_back.recycle();
        map_stage03.recycle();
        map_stage03_back.recycle();
        map_start_btn.recycle();
        map_startbar.recycle();
        map_frame_upbar.recycle();
        map_frame_underbar.recycle();
        map_frame_leftbar.recycle();
        map_frame_rightbar.recycle();

        map_start_btn_back.recycle();
        map_set_btn2.recycle();
        map_set_btn2_back.recycle();

        map_hand.recycle();
        map_sc_rank_bar.recycle();
        map_quest_back.recycle();
        map_quest_btn.recycle();

        quest_btn.recycle();

        map_checK_bar.recycle();
        map_check_font.recycle();
         map_check_return.recycle();
         map_check_light.recycle();
        map_check_dark.recycle();
         map_s01data.recycle();
         map_s02data.recycle();
         map_s03data.recycle();
         map_virusdata_grey.recycle();
         map_virusdata.recycle();
         map_check_easy.recycle();
        map_check_nornmal.recycle();
        map_check_hard.recycle();
         return_btn.recycle();
         check_ok_btn.recycle();
        map_ok_btn.recycle();
        map_ok_btn2.recycle();



		activity.io.speed=set_speed-5;
		activity.io.timing=set_timing-5;
/*		wmap.recycle();
		left_back.recycle();
		left_exit.recycle();    //<<箭頭-淺色  離開MENU用
		left_exit2.recycle();   //<<箭頭-深色

		menubtn.recycle();      //>>箭頭-淺色  呼叫MENU用
		menubtn2.recycle();    //>>箭頭-深色

		left_exitback.recycle(); //離開按鈕背景
		left_btmback.recycle();  //MENU選項背景
		btnvol.recycle();
		btnvol2.recycle();
		chbtmse.recycle();
		chbtmse2.recycle();
		song_vol.recycle();
		song_vol2.recycle();
		remain.recycle();
		remain2.recycle();

		//速度與判定圖片宣告=====================================
		timing.recycle();
		timing2.recycle();
		timing_back.recycle();
		timing_r_arrow.recycle();
		timing_l_arrow.recycle();
		for(int i=0;i<11;i++){
			timing_num[i].recycle();
		}
		//速度與判定圖片宣告----------------------------------------


		volchback.recycle();
		volBar.recycle();
		volbtn.recycle();
		sechange.recycle();

		//右邊關卡選擇區塊
		right_board.recycle();
		right_chmodel.recycle();
		right_stage01.recycle();
		right_freely.recycle();
		right_boss01.recycle();
		right_st01Font.recycle();
		right_easy.recycle();
		right_normal.recycle();
		right_hard.recycle();

		//新宣告的難易度與箭頭圖片===========================================
		right_easy_ch.recycle();
		right_normal_ch.recycle();
		right_hard_ch.recycle();
		right_arrow_left.recycle();
		right_arrow_left2.recycle();
		right_arrow_right.recycle();
		right_arrow_right2.recycle();
		//新宣告的難易度與箭頭圖片-------------------------------------------

		//第2、第3關卡所需要的圖片===========================================
		right_stage02info.recycle();
		right_stage03info.recycle();
		// right_boss_del.recycle();  //過關之後會顯示的BOSS圖片
		stagebtn_green.recycle();
		stagebtn_green_l.recycle();
		right_stage2.recycle();
		right_stage3.recycle();
		//第2、第3關卡所需要的圖片-------------------------------------------

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


		menubtm.recycle(); //MENU按鈕
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
		stbtn02.recycle();  //第二關按鈕
		stbtn03.recycle();  //第三關按鈕
		easy.recycle();
		normal.recycle();
		hard.recycle();
		model.recycle();
		*/
		start.recycle();
		setting.recycle();
        setting_btn.recycle();
        easy.recycle();
        normal.recycle();
        hard.recycle();
		//箭頭按鈕宣告================================================
		// arrow.recycle();
		//箭頭按鈕宣告--------------------------------------------------------------------------------
		num.recycle();
		for(int i=0;i<rank.length;i++){
			rank[i].recycle();
		}
		System.gc();
		Constant.Flag=false;
		sp.release();
		mp.stop();
        mp.release();


	}


}
