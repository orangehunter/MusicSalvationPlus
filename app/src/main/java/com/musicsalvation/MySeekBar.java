package com.musicsalvation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class MySeekBar {
	MainActivity activity;

	float btmX;//���s������x�y��
	float btmY;//���s������y�y��

	float barX;//�j�M��������x�y��
	float barY;//�j�M��������y�y��

	float btm_width;//���s���e
	float btm_height;//���s����

	float bar_width;//�j�M�����e
	float bar_height;//�j�M������

	float bar_btn_dis;

	Boolean isOn;

	Bitmap Bar;//�j�M��
	Bitmap Btm;//���s

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
	public void setSeekBarInt(int a){//��Ʀʤ���]�w
		//Log.v("SeekBar", "Int"+a);
		this.btmX=(this.barX+(this.btm_width/2))+(this.bar_width-this.btm_width)*(a/100)-(this.btm_width/2);
		bar_btn_dis=btmX-barX;
	}
	public void setSeekBarFloat(float a){//�p�Ʀʤ���]�w
		//Log.v("SeekBar", "Float"+a);
		this.btmX=(this.barX+(this.btm_width/2))+(this.bar_width-this.btm_width)*(a/100)-(this.btm_width/2);
		bar_btn_dis=btmX-barX;
	}
	public void setSeekBarX(float x){//���s��m�]�w
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
	public double getSeekBarValue(){//�^�Ǧʤ���
		return ((this.btmX+(this.btm_width/2))-(this.barX+(this.btm_width/2)))/(this.bar_width-this.btm_width)*100;
	}
	public Boolean isOn(float x,float y){//���s�ר�T�{
		if(x>this.btmX&&x<this.btmX+this.btm_width   &&   y>this.btmY&&y<this.btmY+this.btm_height)
			return true;
		return false;
	}
	public void recycle(){
		Bar.recycle();//�j�M��
		Btm.recycle();//���s
	}
}
