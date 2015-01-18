package com.musicsalvation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;



public class Bottom {
	MainActivity activity;
	float x;//圖片的中央x座標
	float y;//圖片的中央y座標
	float width;//虛擬按鈕的寬
	float height;//虛擬按鈕的高
	Bitmap onBitmap;//按下狀態的圖片
	Bitmap offBitmap;//未按下狀態的圖片
	boolean isOn=false;//按下狀態為true
	int key;
	public Bottom(MainActivity activity,Bitmap onBitmap,Bitmap offBitmap,int x,int y){
		this.activity=activity;
		//this.isOn=activity.backgroundsoundFlag;
		this.onBitmap=onBitmap;
		this.offBitmap=offBitmap;
		this.width=offBitmap.getWidth();
		this.height=offBitmap.getHeight();
		this.x=Coordinate.CoordinateX(x)-(this.width/2);
		this.y=Coordinate.CoordinateY(y)-(this.height/2);
	}

	public void drawBtm(Canvas canvas,Paint paint){//繪製按鈕
		if(isOn)
			canvas.drawBitmap(onBitmap, x, y, paint);
		else
			canvas.drawBitmap(offBitmap, x, y,paint);
	}
	public void drawBtm(Canvas canvas,Paint paint,int alpha){//繪製按鈕
		paint.setAlpha(alpha);
		drawBtm(canvas,paint);
		paint.reset();
	} 
	public void drawBtm(Canvas canvas,Paint paint,int x,int y){//繪製按鈕
		move(x,y);
		drawBtm(canvas,paint);
	}
	public void drawBtm(Canvas canvas,Paint paint,int x,int y,int alpha){//繪製按鈕
		paint.setAlpha(alpha);
		move(x,y);
		drawBtm(canvas,paint);
		paint.reset();
	}
	
	
	public void setBottom(){//切換按鈕狀態
		this.isOn=!this.isOn;
	}
	public boolean getBottom(){
		return this.isOn;
	}
	
	public void setBottomTo(Boolean i){
		this.isOn=i;
	}
	public void move(int x,int y){
		this.x=Coordinate.CoordinateX(x)-(this.width/2);
		this.y=Coordinate.CoordinateY(y)-(this.height/2);
	}
	public void setKey(int key){
		this.key=key;
	}
	public int getKey(){
		return key;
	}
	public Boolean isIn(float pointx,float pointy){//判斷觸控位置
		if(pointx>=x&&pointx<=x+width&&      	pointy>=y&&pointy<=y+height)
			return true;
		return false;
	}
	public void recycle(){
		onBitmap.recycle();//按下狀態的圖片
		offBitmap.recycle();//未按下狀態的圖片
	}
}
