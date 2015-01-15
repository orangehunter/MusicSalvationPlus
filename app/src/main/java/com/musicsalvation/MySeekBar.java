package com.musicsalvation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class MySeekBar {
	MainActivity activity;

	float btmX;//按鈕的中央x座標
	float btmY;//按鈕的中央y座標

	float barX;//搜尋條的中央x座標
	float barY;//搜尋條的中央y座標

	float btm_width;//按鈕的寬
	float btm_height;//按鈕的高

	float bar_width;//搜尋條的寬
	float bar_height;//搜尋條的高

	float bar_btn_dis;

	Boolean isOn;

	Bitmap Bar;//搜尋條
	Bitmap Btm;//按鈕

	public MySeekBar(MainActivity activity,Bitmap Bar,Bitmap Btm,int x,int y){
		this.activity=activity;
		this.Bar=Bar;
		this.Btm=Btm;
		this.isOn=false;

		this.bar_width=this.Bar.getWidth();
		this.bar_height=this.Bar.getHeight();

		this.btm_width=this.Btm.getWidth();
		this.btm_height=this.Btm.getHeight();

		this.barX=Coordinate.CoordinateX(x)-(this.bar_width/2);
		this.barY=Coordinate.CoordinateY(y)-(this.bar_height/2);

		this.btmX=Coordinate.CoordinateX(x)-(this.btm_width/2);
		this.btmY=Coordinate.CoordinateY(y)-(this.btm_height/2);

	}
	public void Move(int x,int y){
		//Log.v("SeekBar", "X:"+x+"Y:"+y);
		this.barX=Coordinate.CoordinateX(x)-(this.bar_width/2);
		this.barY=Coordinate.CoordinateY(y)-(this.bar_height/2);

		this.btmX=barX+bar_btn_dis;
		this.btmY=Coordinate.CoordinateY(y)-(this.btm_height/2);
	}
	public void drawSeekBar(Canvas canvas,Paint paint){
		canvas.drawBitmap(Bar, barX, barY, paint);
		canvas.drawBitmap(Btm, btmX, btmY, paint);
	}
	public void setSeekBarInt(int a){//整數百分比設定
		//Log.v("SeekBar", "Int"+a);
		this.btmX=(this.barX+(this.btm_width/2))+(this.bar_width-this.btm_width)*(a/100)-(this.btm_width/2);
		bar_btn_dis=btmX-barX;
	}
	public void setSeekBarFloat(float a){//小數百分比設定
		//Log.v("SeekBar", "Float"+a);
		this.btmX=(this.barX+(this.btm_width/2))+(this.bar_width-this.btm_width)*(a/100)-(this.btm_width/2);
		bar_btn_dis=btmX-barX;
	}
	public void setSeekBarX(float x){//按鈕位置設定
		//Log.v("SeekBar", "X"+x);
		if(x>(this.barX+(this.btm_width/2))&& x<(this.barX+this.bar_width-(this.btm_width/2))){
			this.btmX=x-(this.btm_width/2);

		}else if(x<(this.barX+(this.btm_width/2))-(this.btm_width/2)){
			setSeekBarInt(0);

		}else if(x>(this.barX+this.bar_width-(this.btm_width/2))-(this.btm_width/2)){
			setSeekBarInt(100);
		}
		bar_btn_dis=btmX-barX;
	}
	public double getSeekBarValue(){//回傳百分比
		return ((this.btmX+(this.btm_width/2))-(this.barX+(this.btm_width/2)))/(this.bar_width-this.btm_width)*100;
	}
	public Boolean isOn(float x,float y){//按鈕案到確認
		if(x>this.btmX&&x<this.btmX+this.btm_width   &&   y>this.btmY&&y<this.btmY+this.btm_height)
			return true;
		return false;
	}
	public void recycle(){
		Bar.recycle();//搜尋條
		Btm.recycle();//按鈕
	}
}
