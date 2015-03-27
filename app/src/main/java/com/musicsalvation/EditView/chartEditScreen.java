package com.musicsalvation.EditView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.SparseArray;

import com.musicsalvation.R;
import com.musicsalvation.Coordinate;
import com.musicsalvation.Graphic;
import com.musicsalvation.MainActivity;

import org.json.JSONObject;

/**
 * Created by michael on 2015/1/15.
 */

public class chartEditScreen {
    MainActivity activity;
    int left;
    int right;
    int up;
    int down;

    static int time_current;
    int move=0;

    static int accuracy=100;

    static int time_lv;
    final int sec_1=0;
    final int sec_10=1;
    final int sec_20=2;
    final int sec_30=3;

    static int unit;//刻度間隔
    static double unit_lv[];//每0.1sec移動單位


    Bitmap Rp,Sp,Tp,Xp;


    SparseArray<lines> timeLines=new SparseArray<lines>();
    calibration ca;
    chart ct;

    public chartEditScreen(MainActivity activity,int left,int up,int right,int down,int Duration){
        this.activity=activity;
        time_lv=sec_10;
        unit = (right-left) / 5 / 10;//間隔距離
        unit_lv = new double[4];
        unit_lv[0] = unit;//每大格1秒 每0.1秒移動距離
        unit_lv[1] = unit_lv[0] / 10;//每大格10秒 每0.1秒移動距離
        unit_lv[2] = unit_lv[1] / 2;//每大格20秒 每0.1秒移動距離
        unit_lv[3] = unit_lv[1] / 3;//每大格30秒 每0.1秒移動距離
        this.left=left;
        this.right=right;
        this.up=up;
        this.down=down;
        Rp=Graphic.LoadBitmap(activity.getResources(),R.drawable.bottom_round   ,80,80,false);
        Sp=Graphic.LoadBitmap(activity.getResources(),R.drawable.bottom_square  ,80,80,false);
        Tp=Graphic.LoadBitmap(activity.getResources(),R.drawable.bottom_trangle ,80,80,false);
        Xp=Graphic.LoadBitmap(activity.getResources(),R.drawable.bottom_x       ,80,80,false);
        ca=new calibration(this,Duration,left,up,right,down,30);
    }

    public void draw(Canvas canvas,Paint paint,int currentTime){
        time_current=currentTime+move;
        RectF rf1=new RectF(Coordinate.CoordinateX(left),Coordinate.CoordinateY(up),Coordinate.CoordinateX(right),Coordinate.CoordinateY(down));//設定譜面底圖矩形
        //RectF rf2=new RectF(Coordinate.CoordinateX(right),Coordinate.CoordinateY(up),Coordinate.CoordinateX(right)+Rp.getWidth(),Coordinate.CoordinateY(down));//設定譜面右邊遮罩
        //RectF rf3=new RectF(Coordinate.CoordinateX(left)-Rp.getWidth(),Coordinate.CoordinateY(up),Coordinate.CoordinateX(left),Coordinate.CoordinateY(down));//設定譜面左邊遮罩
        paint.setColor(Color.BLACK);
        canvas.drawRect(rf1, paint);
        paint.reset();
        Graphic.drawLine(canvas, Color.GREEN, left + (right - left) / 2, up, left + (right - left) / 2, down, 3, paint);
        ca.draw(time_lv,canvas,paint);

        paint.setColor(Color.WHITE);
        //canvas.drawRect(rf2, paint);
        //canvas.drawRect(rf3, paint);
        paint.reset();
    }

    public void Move(double dis){
        move=(int)(dis/unit_lv[time_lv])*100;
    }
    public int setMove(){
        int tmp=time_current;
        move=0;
        return tmp;
    }

    public boolean isIn(double x,double y){
        if (Coordinate.DeCoordinateX(x)>left&&Coordinate.DeCoordinateX(x)<right&&Coordinate.DeCoordinateY(y)>up&&Coordinate.DeCoordinateY(y)<down) {
            return true;
        }else {
            return false;
        }
    }
    public void reLv(int a){
        if (a==1){
            if (time_lv<sec_30){
                time_lv+=a;
            }
        }else if(a==-1){
            if (time_lv>sec_1){
                time_lv+=a;
            }
        }
    }
}