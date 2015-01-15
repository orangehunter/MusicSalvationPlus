package com.musicsalvation;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({ "ViewConstructor", "WrongCall", "UseSparseArrays", "ClickableViewAccessibility" })
public class EditView extends  SurfaceView
implements SurfaceHolder.Callback {
	boolean deTouchJump=true;
	int temp;

	Uri uri=null;
	Boolean uriFlag=true;

	Paint paint;
	MainActivity activity;
	int editFlag=0;

	Bitmap bottom[]=new Bitmap[5];
	Bottom btm_r,btm_s,btm_t,btm_x;

	static JSONObject 
	BtR=new JSONObject()
	,BtS=new JSONObject()
	,BtT=new JSONObject()
	,BtX=new JSONObject();

	Bitmap mp_play,mp_pause;
	Bottom playBtm;
	static MediaPlayer mp=null;
	String tittle="未選擇";


	int chartObject=20;
	chartLine line[]=new chartLine[chartObject];

	Bitmap 
	cr_on
	,cs_on
	,ct_on
	,cx_on
	,c_off;
	chartBottom 
	cr_btm[]=new chartBottom[chartObject]
			,cs_btm[]=new chartBottom[chartObject]
					,ct_btm[]=new chartBottom[chartObject]
							,cx_btm[]=new chartBottom[chartObject];
	boolean cr_btm_flag=false;
	boolean cs_btm_flag=false;
	boolean ct_btm_flag=false;
	boolean cx_btm_flag=false;

	static int target_dis=5000;
	static int accuracy=100;
	SparseArray<PointF> mActivePointers=new SparseArray<PointF>();
	SparseArray<Integer> btn_pointer=new SparseArray<Integer>();

	int last_line=-1001;
	boolean chart_FullScanFlag=false;
	chartScan chartscan;

	Bitmap Sbar,Sbtm;
	MySeekBar msb;
	Boolean msbFlag=true;

	Bottom load,save;
	Bitmap btm_load,btm_save;
	boolean loadFlag=false;

	public EditView(MainActivity mainActivity){
		super(mainActivity);
		this.activity = mainActivity;
		this.getHolder().addCallback(this);//設定生命周期回調接口的實現者
		Constant.Flag=true;	
	}

	public Bitmap LoadBitmap(int r){
		return BitmapFactory.decodeResource(getResources(), r);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		paint = new Paint();//建立畫筆
		//bottom[A];  A{0:圓  ,1:方  ,2:三角  ,3:叉 ,4:按下}
		int bottomSize=180;
		int btm_first=130,btm_dis=270;
		bottom[0]=Graphic.bitSize(LoadBitmap( R.drawable.bottom_round),bottomSize ,bottomSize);
		bottom[1]=Graphic.bitSize(LoadBitmap( R.drawable.bottom_square) ,bottomSize ,bottomSize);
		bottom[2]=Graphic.bitSize(LoadBitmap( R.drawable.bottom_trangle),bottomSize ,bottomSize);
		bottom[3]=Graphic.bitSize(LoadBitmap(R.drawable.bottom_x),bottomSize ,bottomSize);
		bottom[4]=Graphic.bitSize(LoadBitmap( R.drawable.bottom_pushed),bottomSize ,bottomSize);
		btm_r=new Bottom(activity,bottom[4],bottom[0],btm_first,640);
		btm_s=new Bottom(activity,bottom[4],bottom[1],btm_first+btm_dis,640);
		btm_t=new Bottom(activity,bottom[4],bottom[2],btm_first+btm_dis+btm_dis,640);
		btm_x=new Bottom(activity,bottom[4],bottom[3],btm_first+btm_dis+btm_dis+btm_dis,640);

		Constant.Flag=true;

		Sbar=Graphic.bitSize(LoadBitmap(R.drawable.default_bar), 1280/4*3, 40);
		Sbtm=Graphic.bitSize(LoadBitmap( R.drawable.default_bar_btm), 80, 80);
		msb=new MySeekBar(activity,Sbar,Sbtm,1280/4*3/2+20,500);
		msb.setSeekBarInt(0);

		mp_play=Graphic.bitSize(LoadBitmap(R.drawable.default_media_play), 100, 100);
		mp_pause=Graphic.bitSize(LoadBitmap(R.drawable.default_media_pause), 100, 100);
		playBtm=new Bottom(activity,mp_pause,mp_play,1050,500);
		if(mp!=null){
			mp.release();
		}

		int c_bottom_size=80;
		cr_on=Graphic.bitSize(LoadBitmap( R.drawable.bottom_round),c_bottom_size ,c_bottom_size);
		cs_on=Graphic.bitSize(LoadBitmap( R.drawable.bottom_square) ,c_bottom_size ,c_bottom_size);
		ct_on=Graphic.bitSize(LoadBitmap( R.drawable.bottom_trangle),c_bottom_size ,c_bottom_size);
		cx_on=Graphic.bitSize(LoadBitmap(R.drawable.bottom_x),c_bottom_size ,c_bottom_size);
		c_off=Graphic.bitSize(LoadBitmap( R.drawable.bottom_pushed),c_bottom_size ,c_bottom_size);


		for(int i=0;i<chartObject;i++){
			line[i]=new chartLine( 960,( 20+(960-20)/2),20);
			cr_btm[i]=new chartBottom(960,( 20+(960-20)/2),-40, activity, c_off, cr_on, 25+(85*1));
			cs_btm[i]=new chartBottom(960,( 20+(960-20)/2),-40, activity, c_off, cs_on, 25+(85*2));
			ct_btm[i]=new chartBottom(960,( 20+(960-20)/2),-40, activity, c_off, ct_on,  25+(85*3));
			cx_btm[i]=new chartBottom(960,( 20+(960-20)/2),-40, activity, c_off, cx_on, 25+(85*4));
		}

		btm_load=Graphic.bitSize(LoadBitmap( R.drawable.load),100, 50);
		btm_save=Graphic.bitSize(LoadBitmap(R.drawable.save), 100, 50);
		load=new Bottom(activity, btm_load, btm_load,1200, 625);
		save=new Bottom(activity, btm_save, btm_save, 1200, 680);

		new Thread(){
			@SuppressLint("WrongCall")
			public void run()
			{
				while(Constant.Flag){
					/*try {
						Thread.sleep(0);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}*/
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
			if(uriFlag){//音訊路徑狀態
				uri=activity.sendUri();
				if(uri!=null&& loadFlag){
					tittle=MainActivity.turnUriToName(uri);

					if(mp==null)//偵測撥放器物件
						mp=new MediaPlayer();
					try{
						mp.setDataSource(activity, uri);//載入音檔
						mp.prepare();
					} catch (IllegalArgumentException e) {
					} catch (IllegalStateException e) {
					} catch (IOException e) {
					}
					JSONObject json=null;
					json=activity.read( uri);
					uriFlag=false;
					loadFlag=false;
					chart_FullScanFlag=true;
					if(json==null){

					}else{
						try {
							BtR=json.getJSONObject("R");
							BtS=json.getJSONObject("S");
							BtT=json.getJSONObject("T");
							BtX=json.getJSONObject("X");
						} catch (JSONException e) {
							Log.e("EditView","輸入json失敗");
							e.printStackTrace();
						}
					}
					chartscan=new chartScan(activity,BtR,BtS,BtT,BtX,target_dis,"EditView");
				}
			}
			//底色
			canvas.clipRect(new Rect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HIGHT));//只在螢幕範圍內繪制圖片
			canvas.drawColor(Color.WHITE);//界面設定為白色
			paint.setAntiAlias(true);//開啟抗鋸齒
			//譜面畫面=============================================================================
			RectF rf1=new RectF(Coordinate.CoordinateX(20),Coordinate.CoordinateY(25),Coordinate.CoordinateX(960),Coordinate.CoordinateY(450));//設定譜面底圖矩形
			RectF rf2=new RectF(Coordinate.CoordinateX(960),Coordinate.CoordinateY(25),Coordinate.CoordinateX(1280),Coordinate.CoordinateY(450));//設定譜面右邊遮罩
			RectF rf3=new RectF(Coordinate.CoordinateX(0),Coordinate.CoordinateY(25),Coordinate.CoordinateX(20),Coordinate.CoordinateY(450));//設定譜面左邊遮罩
			paint.setColor(Color.BLACK);
			canvas.drawRect(rf1, paint);
			paint.reset();
			Graphic.drawLine(canvas, Color.GREEN, 20+(960-20)/2, 25, 20+(960-20)/2, 450, 3, paint);

			if(mp!=null){
				//TODO
				if(cr_btm_flag){//按鍵圓產生
					try {
						BtR.put(Integer.toString(mp.getCurrentPosition()/accuracy), true);
					} catch (JSONException e) {
						Log.e("EditView","產生R失敗");
						e.printStackTrace();
					}
					for(int i=0;i<chartObject;i++){
						if(!cr_btm[i].getFlag()){
							cr_btm[i].start(mp.getCurrentPosition()-target_dis, target_dis, mp.getCurrentPosition()/accuracy);
							cr_btm_flag=false;
							break;
						}
					}
				}
				//TODO
				if(cs_btm_flag){//按鍵方產生
					try {
						BtS.put(Integer.toString(mp.getCurrentPosition()/accuracy), true);
					} catch (JSONException e) {
						Log.e("EditView","產生S失敗");
						e.printStackTrace();
					}
					for(int i=0;i<chartObject;i++){
						if(!cs_btm[i].getFlag()){
							cs_btm[i].start(mp.getCurrentPosition()-target_dis, target_dis, mp.getCurrentPosition()/accuracy);
							cs_btm_flag=false;
							break;
						}
					}
				}
				//TODO
				if(ct_btm_flag){//按鍵三角產生
					try {
						BtT.put(Integer.toString(mp.getCurrentPosition()/accuracy), true);
					} catch (JSONException e) {
						Log.e("EditView","產生T失敗");
						e.printStackTrace();
					}
					for(int i=0;i<chartObject;i++){
						if(!ct_btm[i].getFlag()){
							ct_btm[i].start(mp.getCurrentPosition()-target_dis, target_dis, mp.getCurrentPosition()/accuracy);
							ct_btm_flag=false;
							break;
						}
					}
				}
				//TODO
				if(cx_btm_flag){//按鍵X產生
					try {
						BtX.put(Integer.toString(mp.getCurrentPosition()/accuracy), true);
					} catch (JSONException e) {
						Log.e("EditView","產生X失敗");
						e.printStackTrace();
					}
					for(int i=0;i<chartObject;i++){
						if(!cx_btm[i].getFlag()){
							cx_btm[i].start(mp.getCurrentPosition()-target_dis, target_dis, mp.getCurrentPosition()/accuracy);
							cx_btm_flag=false;
							break;
						}
					}
				}

				//TODO 需要重寫
				//全畫面掃描===============================================
				if(chart_FullScanFlag){
					
					
					chart_FullScanFlag=false;
				}//全畫面掃描**********************************************************************************************************************
				else{
					//for(int j=0;j<50;j++){//按鈕_圓 偵測
					//int BtTime=mp.getCurrentPosition()+target_dis+j;
					paint.setColor(Color.BLACK);
					try{
					canvas.drawText(String.valueOf(mp.getCurrentPosition()-temp), Coordinate.CoordinateX(1110),Coordinate.CoordinateY(600) , paint);
					}catch(IllegalStateException e){
						mp.release();
						mp=null;
						mp=new MediaPlayer();
						try {
							mp.setDataSource(activity, uri);
							mp.prepare();
						} catch (IllegalArgumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SecurityException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalStateException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					temp=mp.getCurrentPosition();
					paint.reset();
					if(chartscan.R_scan_flag){//BtR.optBoolean(Integer.toString(BtTime))){
						for(int i=0;i<chartObject;i++){
							if(!cr_btm[i].getFlag()){
								cr_btm[i].start(mp.getCurrentPosition(), target_dis, chartscan.R_scan_id);
								chartscan.R_scan_flag=false;
								break;
							}
						}
					}
					if(chartscan.S_scan_flag){//BtS.optBoolean(Integer.toString(BtTime))){
						for(int i=0;i<chartObject;i++){
							if(!cs_btm[i].getFlag()){
								cs_btm[i].start(mp.getCurrentPosition(), target_dis, chartscan.S_scan_id);
								chartscan.S_scan_flag=false;
								break;
							}
						}
					}
					if(chartscan.T_scan_flag){//BtT.optBoolean(Integer.toString(BtTime))){
						for(int i=0;i<chartObject;i++){
							if(!ct_btm[i].getFlag()){
								ct_btm[i].start(mp.getCurrentPosition(), target_dis, chartscan.T_scan_id);
								chartscan.T_scan_flag=false;
								break;
							}
						}
					}
					if(chartscan.X_scan_flag){//BtX.optBoolean(Integer.toString(BtTime))){
						for(int i=0;i<chartObject;i++){
							if(!cx_btm[i].getFlag()){
								cx_btm[i].start(mp.getCurrentPosition(), target_dis, chartscan.X_scan_id);
								chartscan.X_scan_flag=false;
								break;
							}
						}
						//}
					}
					if((mp.getCurrentPosition()+target_dis)%1000<=100 &&mp.isPlaying()&&(mp.getCurrentPosition()+target_dis)<mp.getDuration()){//時間基準線偵測
						for(int i=0;i<chartObject;i++){
							if(line[i].getFlag()==false &&(mp.getCurrentPosition()+target_dis-this.last_line)>900){
								line[i].start(mp.getCurrentPosition(), target_dis);
								this.last_line=(mp.getCurrentPosition()+target_dis);
								break;
							}
						}
					}
					int draw_time=mp.getCurrentPosition();
					for(int i=0;i<chartObject;i++){////按鈕/時間基準線繪圖
						if(line[i].getFlag()){
							line[i].drawChatrLine(draw_time, canvas, paint);
						}
						if(cr_btm[i].getFlag()){
							cr_btm[i].drawChartBottom(draw_time, canvas, paint);
						}
						if(cs_btm[i].getFlag()){
							cs_btm[i].drawChartBottom(draw_time, canvas, paint);
						}
						if(ct_btm[i].getFlag()){
							ct_btm[i].drawChartBottom(draw_time, canvas, paint);
						}
						if(cx_btm[i].getFlag()){
							cx_btm[i].drawChartBottom(draw_time, canvas, paint);
						}
					}
				}
			}
			paint.setColor(Color.WHITE);
			canvas.drawRect(rf2, paint);
			canvas.drawRect(rf3, paint);
			paint.reset();
			//譜面畫面***************************************************************************************************************************
			//音檔文字處理
			paint.setTextSize((float) (paint.getTextSize()*1.5));
			canvas.drawText(tittle,Coordinate.CoordinateX(20) , Coordinate.CoordinateY(23), paint);
			if(mp!=null && !loadFlag){
				String min,sec,msec;

				if(mp.getCurrentPosition()/1000/60%60<10)//計算分鐘
					min="0"+Integer.toString(mp.getCurrentPosition()/1000/60%60);
				else
					min=Integer.toString(mp.getCurrentPosition()/1000/60%60);				
				if(mp.getCurrentPosition()/1000%60<10)//計算秒鐘
					sec="0"+Integer.toString(mp.getCurrentPosition()/1000%60);
				else
					sec=Integer.toString(mp.getCurrentPosition()/1000%60);				
				if(mp.getCurrentPosition()%1000/10<10)//計算豪秒
					msec="0"+Integer.toString(mp.getCurrentPosition()%1000/10);
				else
					msec=Integer.toString(mp.getCurrentPosition()%1000/10);



				String time=min+":"+sec+":"+msec;
				canvas.drawText(time,Coordinate.CoordinateX(1110) , Coordinate.CoordinateY(500), paint);



				if(mp.getDuration()/1000/60%60<10)//計算分鐘
					min="0"+Integer.toString(mp.getDuration()/1000/60%60);
				else
					min=Integer.toString(mp.getDuration()/1000/60%60);
				if(mp.getDuration()/1000%60<10)//計算秒鐘
					sec="0"+Integer.toString(mp.getDuration()/1000%60);
				else
					sec=Integer.toString(mp.getDuration()/1000%60);				
				if(mp.getDuration()%1000/10<10)//計算豪秒
					msec="0"+Integer.toString(mp.getDuration()%1000/10);
				else
					msec=Integer.toString(mp.getDuration()%1000/10);


				time=min+":"+sec+":"+msec;
				canvas.drawText(time,Coordinate.CoordinateX(1110) , Coordinate.CoordinateY(525), paint);
			}
			//主要按鈕
			btm_r.drawBtm(canvas, paint);
			btm_s.drawBtm(canvas, paint);
			btm_t.drawBtm(canvas, paint);
			btm_x.drawBtm(canvas, paint);
			playBtm.drawBtm(canvas, paint);

			btm_r.setBottomTo(false);
			btm_s.setBottomTo(false);
			btm_t.setBottomTo(false);
			btm_x.setBottomTo(false);
			for(int i=0;i<btn_pointer.size();i++){
				int st=btn_pointer.valueAt(i);
				switch(st){
				case 0:
					btm_r.setBottomTo(true);
					break;
				case 1:
					btm_s.setBottomTo(true);
					break;
				case 2:
					btm_t.setBottomTo(true);
					break;
				case 3:
					btm_x.setBottomTo(true);
					break;
				}
			}
			//搜尋條
			msb.drawSeekBar(canvas, paint);
			if(mp!=null && !loadFlag){
				if(msbFlag)
					msb.setSeekBarFloat((float)mp.getCurrentPosition()/mp.getDuration()*100);
				if(mp.isPlaying()){
					chartscan.checkChart(BtR, BtS, BtT, BtX);
					playBtm.setBottomTo(true);
				}
				else{
					playBtm.setBottomTo(false);
					
				}
			}
			load.drawBtm(canvas, paint);
			save.drawBtm(canvas, paint);

			paint.reset();
		}
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event){
		//int pointerCount = event.getPointerCount();

		// get pointer index from the event object
		int pointerIndex = event.getActionIndex();

		// get pointer ID
		int pointerId = event.getPointerId(pointerIndex);

		switch(event.getActionMasked())
		{
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN://按下
			PointF f = new PointF();
			f.x = event.getX(pointerIndex);
			f.y = event.getY(pointerIndex);
			mActivePointers.put(pointerId, f);
			if(mp!=null){
				if(playBtm.isIn(f.x, f.y)){
					playBtm.setBottom();
					if(mp.isPlaying()){
						mp.pause();
						chartscan.pause();
					}
					else{
						mp.start();
						chartscan.resume(BtR, BtS, BtT, BtX);
					}
				}

				if(msb.isOn(f.x, f.y)){
					msbFlag=false;
				}
			}
			if(load.isIn(f.x, f.y)){
				loadFlag=true;
				if(mp!= null){
					mp.stop();
					mp.release();
					mp=null;
				}
				Constant.Flag=false;
				uriFlag=true;
				activity.changeView(7);
			}
			if(save.isIn(f.x, f.y)){
				//if(FileData.IfData(activity)){
					new Thread(){
						@SuppressLint("WrongCall")
						public void run()
						{
							activity.write( uri, BtR, BtS, BtT, BtX);
						}
					}.start();
					//FileData.write(activity, uri, BtR, BtS, BtT, BtX);
					//}
			}
			if(btm_r.isIn(f.x, f.y)){
				btn_pointer.put(pointerId, 0);
				cr_btm_flag=true;
			}
			if(btm_s.isIn(f.x, f.y)){
				btn_pointer.put(pointerId, 1);
				cs_btm_flag=true;
			}
			if(btm_t.isIn(f.x, f.y)){
				btn_pointer.put(pointerId, 2);
				ct_btm_flag=true;
			}
			if(btm_x.isIn(f.x, f.y)){
				btn_pointer.put(pointerId, 3);
				cx_btm_flag=true;
			}
			for(int i=0;i<chartObject;i++){
				if(cr_btm[i].btm.isIn(f.x, f.y)){
					BtR.remove(String.valueOf(cr_btm[i].getId()));
					cr_btm[i].cancel();
				}
				if(cs_btm[i].btm.isIn(f.x, f.y)){
					BtS.remove(String.valueOf(cs_btm[i].getId()));
					cs_btm[i].cancel();
				}
				if(ct_btm[i].btm.isIn(f.x, f.y)){
					BtT.remove(String.valueOf(ct_btm[i].getId()));
					ct_btm[i].cancel();
				}
				if(cx_btm[i].btm.isIn(f.x, f.y)){
					BtX.remove(String.valueOf(cx_btm[i].getId()));
					cx_btm[i].cancel();
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			for (int size = event.getPointerCount(), i = 0; i < size; i++) {
				PointF point = mActivePointers.get(event.getPointerId(i));
				if (point != null) {
					point.x = event.getX(i);
					point.y = event.getY(i);
				}
			}
			if(!msbFlag){
				msb.setSeekBarX(event.getX(0));
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_CANCEL: 
			f = new PointF();
			f.x = event.getX(pointerIndex);
			f.y = event.getY(pointerIndex);
			if(mp!=null){
				if(!msbFlag){
					mp.seekTo((int)(msb.getSeekBarValue()/100*mp.getDuration()));
					msbFlag=true;
				}
			}
			if(msb.isOn(f.x, f.y)){						
				chartscan.pause();
				chartscan.resume(BtR, BtS, BtT, BtX);
				for(int i=0;i<chartObject;i++){
					line[i].flag=false;
					cr_btm[i].flag=false;
					cs_btm[i].flag=false;
					ct_btm[i].flag=false;
					cx_btm[i].flag=false;
				}
				last_line=-1002;
				chart_FullScanFlag=true;
			}
			mActivePointers.remove(pointerId);
			btn_pointer.remove(pointerId);

			break;
		}
		return true;
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {

	}
	public void surfaceDestroyed(SurfaceHolder arg0) {//銷毀時被呼叫
		if(mp!=null){
			chartscan.Stop();
			mp.release();
		}
		Constant.Flag=false;
	}

}
