package com.musicsalvation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class chartLine {
	int start;
	int target;
	int end;
	int linex;
	boolean flag;
	int start_time;
	int time_dis;
	double move_unit,move;
	public chartLine(int start,int target,int end){
		this.start=start;
		this.target=target;
		this.end=end;
		this.flag=false;
	}
	public void start(int start_time,int time_dis){
		this.time_dis=time_dis;
		this.start_time=start_time;
		this.flag=true;
	}
	public void drawChatrLine(int now_time,Canvas canvas,Paint paint){
		move_unit=(start-target)/(time_dis*1.0);
		move=(now_time*1.0)-(start_time);
		linex=(int)(start-move_unit*move);
		Graphic.drawLine(canvas, Color.WHITE, linex, 27, linex, 448, 1, paint);
		if(linex<=end)
			this.flag=false;
	}
	public boolean getFlag(){
		return flag;
	}
}
