package com.musicsalvation;


import com.example.musicsalvationsdkverson.R;

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
	Paint paint;			//�e�����Ѧ�
	int i=0,j=5;
	MainActivity activity;

	public Bitmap LoadBitmap(int r){
		return BitmapFactory.decodeResource(getResources(), r);
	}
	public StartView(MainActivity mainActivity) {
		super(mainActivity);
		this.activity = mainActivity;
		this.getHolder().addCallback(this);//�]�w�ͩR�P���^�ձ��f����{��
		paint = new Paint();//�إߵe��
		paint.setAntiAlias(true);//�}�ҧܿ���
		logo=Graphic.bitSize(LoadBitmap(R.drawable.llc), 1280, 720);
	}
	@Override
	protected void onDraw(Canvas canvas) {//���s�w�q��ø���k
		if(canvas!=null){
			super.onDraw(canvas);
			paint.setAntiAlias(true);	//�}�ҧܿ���
			//ø��¶�R�x�βM�I��
			paint.setColor(Color.BLACK);//�]�w�e���m��
			paint.setAlpha(255);
			canvas.drawRect(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HIGHT, paint);
			paint.reset();

			i+=j;//�z���װѼ�
			
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
					Canvas canvas = myholder.lockCanvas();//���o�e��
					onDraw(canvas);
					if(canvas != null){
						myholder.unlockCanvasAndPost(canvas);
					}
				}
				activity.changeView(1);
			}
		}.start();
	}
	public void surfaceDestroyed(SurfaceHolder arg0) {//�P���ɳQ�I�s

	}
}
