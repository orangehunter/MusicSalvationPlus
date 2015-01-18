package com.musicsalvation;



import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("ViewConstructor")
public class StartView extends SurfaceView
implements SurfaceHolder.Callback{
	Bitmap logo;
	Paint paint;			//畫筆的參考
	int i=0,j=5;
	MainActivity activity;

	public Bitmap LoadBitmap(int r){
		return BitmapFactory.decodeResource(getResources(), r);
	}
	public StartView(MainActivity mainActivity) {
		super(mainActivity);
		this.activity = mainActivity;
		this.getHolder().addCallback(this);//設定生命周期回調接口的實現者
		paint = new Paint();//建立畫筆
		paint.setAntiAlias(true);//開啟抗鋸齒
		logo=Graphic.bitSize(LoadBitmap(R.drawable.llc), 1280, 720);
	}
	@Override
	protected void onDraw(Canvas canvas) {//重新定義的繪制方法
		if(canvas!=null){
			super.onDraw(canvas);
			paint.setAntiAlias(true);	//開啟抗鋸齒
			//繪制黑填充矩形清背景
			paint.setColor(Color.BLACK);//設定畫筆彩色
			paint.setAlpha(255);
			canvas.drawRect(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HIGHT, paint);
			paint.reset();

			i+=j;//透明度參數
			
			Graphic.drawPic(canvas, logo, 1280/2, 720/2, 0, i, paint);
		}
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {

	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		new Thread(){
			@SuppressLint("WrongCall")
			public void run()
			{
				while(i<250){
					SurfaceHolder myholder=StartView.this.getHolder();
					Canvas canvas = myholder.lockCanvas();//取得畫布
					onDraw(canvas);
					if(canvas != null){
						myholder.unlockCanvasAndPost(canvas);
					}
				}
				activity.changeView(1);
			}
		}.start();
	}
	public void surfaceDestroyed(SurfaceHolder arg0) {//銷毀時被呼叫

	}
}
