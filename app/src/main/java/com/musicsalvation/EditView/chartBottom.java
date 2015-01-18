package com.musicsalvation.EditView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.musicsalvation.Bottom;
import com.musicsalvation.MainActivity;

public class chartBottom {
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
	
	public chartBottom(int start,int target,int end,MainActivity activity,Bitmap on,Bitmap off,int y){
		this.pointy=y;
		btm=new Bottom(activity, on, off, -1000, pointy);
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
	public void drawChartBottom(int now_time,Canvas canvas,Paint paint){
		move_unit=(start-target)/(time_dis*1.0);
		move=(now_time*1.0)-(start_time);
		pointx=(int)(start-move_unit*move);
		btm.move(pointx, pointy);
		btm.drawBtm(canvas, paint);
		if(pointx<=end)
			this.flag=false;
	}
	public boolean getFlag(){
		return flag;
	}
	public void cancel(){
		this.flag=false;
	}
	public int getId(){
		return ID;
	}
public void recycle(){
		btm.recycle();
	}
}
