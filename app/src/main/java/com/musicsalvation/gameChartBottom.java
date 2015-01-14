package com.musicsalvation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class gameChartBottom {
	int ID;
	int start;
	int target;
	int end;
	int pointx,pointy;
	boolean flag;
	int start_time;
	int time_dis;
	double move_unit,move;
	Bottom btm;

	public gameChartBottom(int start,int target,int end,MainActivity activity,Bitmap on,Bitmap off,int x){
		this.pointx=x;
		btm=new Bottom(activity, on, off, pointx, -1000);
		btm.setBottomTo(false);
		this.start=start;
		this.target=target;
		this.end=end;
		this.flag=false;
	}
	public void start(int start_time,int time_dis,int id){
		this.ID=id;
		this.time_dis=time_dis;
		this.start_time=start_time;
		this.flag=true;
	}
	public boolean drawChartBottom(int now_time,Canvas canvas,Paint paint){
		move_unit=(start-target)/(time_dis*1.0);
		move=(now_time*1.0)-(start_time);
		pointy=(int)(start-move_unit*move);
		btm.move(pointx, pointy);
		btm.drawBtm(canvas, paint);
		if(pointy>=end){
			this.flag=false;
			return true;
		}else{
			return false;
		}
	}
	public int getY(){
		return pointy;
	}
	public void stop(){
		flag=false;
	}
	public boolean getFlag(){
		return flag;
	}
	public int getId(){
		return ID;
	}
public void recycle(){
	 btm.recycle();
	}
}
