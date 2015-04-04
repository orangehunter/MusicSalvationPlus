package com.musicsalvation.EditView;

import com.musicsalvation.Coordinate;

/**
 * Created by Michael on 2015/4/4.
 */
public class Point {
    public int key;
    public String btn;
    public int type,mid_x,mid_y;
    public double x,y;
    public double width,hight;
    public Point(int key,String btn,int type,int mid_x,int mid_y){
        this.key=key;
        this.btn=btn;
        this.type=type;
        this.mid_x=mid_x;
        this.mid_y=mid_y;
    }
    public void setPic(double width,double hight){
        this.width=width;
        this.hight=hight;
        this.x= Coordinate.DeCoordinateX(mid_x)-width/2;
        this.y= Coordinate.DeCoordinateY(mid_y)-hight/2;
    }
}
