package com.musicsalvation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;



public class Bottom {
	MainActivity activity;
	float x;//�Ϥ�������x�y��
	float y;//�Ϥ�������y�y��
	float width;//�������s���e
	float height;//�������s����
	Bitmap onBitmap;//���U���A���Ϥ�
	Bitmap offBitmap;//�����U���A���Ϥ�
	boolean isOn=false;//���U���A��true
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

	public void drawBtm(Canvas canvas,Paint paint){//ø�s���s
		if(isOn)
			canvas.drawBitmap(onBitmap, x, y, paint);
		else
			canvas.drawBitmap(offBitmap, x, y,paint);
	}
	public void drawBtm(Canvas canvas,Paint paint,int alpha){//ø�s���s
		paint.setAlpha(alpha);
		drawBtm(canvas,paint);
		paint.reset();
	} 
	public void drawBtm(Canvas canvas,Paint paint,int x,int y){//ø�s���s
		move(x,y);
		drawBtm(canvas,paint);
	}
	public void drawBtm(Canvas canvas,Paint paint,int x,int y,int alpha){//ø�s���s
		paint.setAlpha(alpha);
		move(x,y);
		drawBtm(canvas,paint);
		paint.reset();
	}
	
	
	public void setBottom(){//�������s���A
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
	public Boolean isIn(float pointx,float pointy){//�P�_Ĳ����m
		if(pointx>=x&&pointx<=x+width&&      	pointy>=y&&pointy<=y+height)
			return true;
		return false;
	}
	public void recycle(){
		onBitmap.recycle();//���U���A���Ϥ�
		offBitmap.recycle();//�����U���A���Ϥ�
	}
}
