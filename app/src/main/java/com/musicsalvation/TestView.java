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
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.lang.*;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility" })
public class TestView extends SurfaceView
implements SurfaceHolder.Callback{

	boolean deJump=true;
	Bitmap bottom[]=new Bitmap[5];
	Bottom btm_r,btm_s,btm_t,btm_x;
	java.lang.Number number;//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<�ŧi
	int test=0;
	int tmp=1;
	Paint paint;			//�e�����Ѧ�
	MainActivity activity;

	public TestView(MainActivity mainActivity) {
		super(mainActivity);
		this.activity = mainActivity;
		this.getHolder().addCallback(this);//�]�w�ͩR�P���^�ձ��f����{��
	}

	public Bitmap LoadBitmap(int r){
		return BitmapFactory.decodeResource(getResources(), r);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		paint = new Paint();//�إߵe��
		paint.setAntiAlias(true);//�}�ҧܿ���

		number=new java.lang.Number(getResources());//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<���J
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
					SurfaceHolder myholder=TestView.this.getHolder();
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
			canvas.drawColor(Color.WHITE);//�ɭ��]�w��
			btm_r.drawBtm(canvas, paint);
			btm_s.drawBtm(canvas, paint);
			number.setSize(35, 50);//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<�r��j�p
			number.setDistence(20);//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<��r���Z
			number.drawNumberLeftStart(100, 100, test, java.lang.Number.Blue, canvas, paint);//<<<<<<<<<<<<<<<<<<<<<<<<�̥��y�ЩT�w
			number.drawNumberRightStart(1180, 200, test, java.lang.Number.Red, canvas, paint);//<<<<<<<<<<<<<<<<<<<<<<<�̥k�y�ЩT�w
			number.reset();//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<���]�r��j�p(35,50)�ζ��Z(0)
			test+=tmp;
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		int pointx = (int) event.getX();
		int pointy = (int) event.getY();

		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN://���U
			if(deJump == true){
				if(btm_r.isIn(pointx, pointy)){
					tmp=1;
					btm_r.setBottomTo(true);
				}
				if(btm_s.isIn(pointx, pointy)){
					tmp=-1;
					btm_s.setBottomTo(true);
				}
				if(btm_t.isIn(pointx, pointy)){
					
					btm_t.setBottomTo(true);
				}
				if(btm_x.isIn(pointx, pointy)){
					
					btm_x.setBottomTo(true);
				}
			}
			deJump = false;
			break;
		case MotionEvent.ACTION_UP://��_
			if(deJump==false){//����u��part2
				if(btm_r.getBottom()){
					
					btm_r.setBottomTo(false);
				}
				if(btm_s.getBottom()){
					
					btm_s.setBottomTo(false);
				}
				if(btm_t.getBottom()){
					
					btm_t.setBottomTo(false);
				}
				if(btm_x.getBottom()){
					
					btm_x.setBottomTo(false);
				}
			}
			deJump = true;
			break;
		}

		return true;
	}


	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {

	}

	public void surfaceDestroyed(SurfaceHolder arg0) {//�P���ɳQ�I�s
		Constant.Flag=false;
	}


}
