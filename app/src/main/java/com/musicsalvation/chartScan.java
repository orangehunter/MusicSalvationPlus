package com.musicsalvation;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;

import android.util.Log;


public class chartScan {

	int scan_time_flag;
	Timer timer;
	TimerTask task;
	MainActivity activity;

	String view;	

	boolean R_scan_flag=false;
	boolean S_scan_flag=false;
	boolean T_scan_flag=false;
	boolean X_scan_flag=false;
	int R_scan_id;
	int S_scan_id;
	int T_scan_id;
	int X_scan_id;

	int r_last=-10;
	int s_last=-10;
	int t_last=-10;
	int x_last=-10;

	int dis;


	JSONObject 
	BtR=new JSONObject()
	,BtS=new JSONObject()
	,BtT=new JSONObject()
	,BtX=new JSONObject();

	public chartScan(MainActivity activity,JSONObject R,JSONObject S,JSONObject T,JSONObject X,int time_dis,String view){
		this.activity=activity;
		this.view=view;
		dis=time_dis;
		build(R,S,T,X);
	}
	public void build(JSONObject R,JSONObject S,JSONObject T,JSONObject X){
		reset();
		timer = new Timer();
		scan_time_flag=0;
		BtR=R;
		BtS=S;
		BtT=T;
		BtX=X;
	}
	public void Start(){
		timer.schedule( new TimerTask(){
			@Override
			public void run() {
				mainScan();
			}
		},0,10 );
	}
	public void pause(){
		timer.cancel();
	}
	public void resume(JSONObject R,JSONObject S,JSONObject T,JSONObject X){
		build(R,S,T,X);
		Start();
	}
	public void reset(){
		r_last=-10;
		s_last=-10;
		t_last=-10;
		x_last=-10;
	}
	public void checkChart(JSONObject R,JSONObject S,JSONObject T,JSONObject X){
		if(!R.equals(BtR)){
			BtR=R;
		}
		if(!S.equals(BtS)){
			BtS=S;
		}
		if(!T.equals(BtT)){
			BtT=T;
		}
		if(!X.equals(BtX)){
			BtX=X;
		}
	}
	public void Stop(){
		timer.cancel();
		scan_time_flag=0;
	}

	public void mainScan(){
		if(this.view.equals("EditView")){
			scan_time_flag=EditView.mp.getCurrentPosition();
		}else if(this.view.equals("GameView")){
			scan_time_flag=GameView.mp.getCurrentPosition();
			//+(activity.timing*10);
		}
		scan_time_flag=(scan_time_flag+dis)/100;
		//Log.v("ChartScan",String.valueOf(scan_time_flag));
		if(BtR.optBoolean(Integer.toString(scan_time_flag))&&scan_time_flag-r_last>=1){
			r_last=scan_time_flag;
			R_scan_flag=true;
			R_scan_id=scan_time_flag;
		}else
			if(BtS.optBoolean(Integer.toString(scan_time_flag))&&scan_time_flag-s_last>=1){
				s_last=scan_time_flag;
				S_scan_flag=true;
				S_scan_id=scan_time_flag;
			}else
				if(BtT.optBoolean(Integer.toString(scan_time_flag))&&scan_time_flag-t_last>=1){
					t_last=scan_time_flag;
					T_scan_flag=true;
					T_scan_id=scan_time_flag;
				}else
					if(BtX.optBoolean(Integer.toString(scan_time_flag))&&scan_time_flag-x_last>=1){
						x_last=scan_time_flag;
						X_scan_flag=true;
						X_scan_id=scan_time_flag;
					}
	}
	public int getTime(){
		return scan_time_flag;
	}
}
