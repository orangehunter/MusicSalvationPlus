package com.musicsalvation;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

public class Graphic {

	static Bitmap LoadBitmap(Resources rs,int r,int x,int y,int scale){
		try{
		 InputStream inputStream = rs.openRawResource(r);
		 Bitmap s=BitmapFactory.decodeStream(inputStream, null, getBitmapOptions(scale));
		    return Bitmap.createScaledBitmap(s, (int)Coordinate.CoordinateX(x), (int)Coordinate.CoordinateY(y), true);
		}catch(OutOfMemoryError e){
			return null;
		}
		//return BitmapFactory.decodeResource(getResources(), r);
	}
	static Bitmap LoadBitmap(Resources rs,int r,int x,int y){
		InputStream inputStream = rs.openRawResource(r);
		BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream,null,bmpFactoryOptions);
        
        int heightRatio = (int)Math.ceil(bmpFactoryOptions.outHeight/y);//(float)Coordinate.CoordinateY(y));
        int widthRatio = (int)Math.ceil(bmpFactoryOptions.outWidth/x);//(float)Coordinate.CoordinateX(x));
        
        if (heightRatio > 1 || widthRatio > 1)
        {
         if (heightRatio > widthRatio)
         {
          bmpFactoryOptions.inSampleSize = heightRatio;
         } else {
          bmpFactoryOptions.inSampleSize = widthRatio; 
         }
        }
        
        bmpFactoryOptions.inJustDecodeBounds = false;
        inputStream = rs.openRawResource(r);
        bitmap = BitmapFactory.decodeStream(inputStream,null, bmpFactoryOptions);
        bitmap=bitSize(bitmap,x,y);
        try {
			inputStream.reset();
		} catch (IOException e) {
			Log.e("graphic", ""+e);
		}
        if(bitmap==null){
        	Log.e("graphic", "bitmail null");
        }
     return bitmap;
	}
	static BitmapFactory.Options getBitmapOptions(int scale){
	    BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds=false;
		options.inPreferredConfig=Bitmap.Config.ARGB_4444;
	    options.inPurgeable = true;
	    options.inInputShareable = true;
	    options.inSampleSize = scale;
	    return options;
	}
	
	static Bitmap bitSize(Bitmap bf,int f,int g){//????蝮格??
		int bw=0;
		int bh=0;
		float scaleWidth=0;
		float scaleHeight=0;
		// ??敺??唾?蝻拇?曄??matrix????
		Matrix matrix = new Matrix();
		while(scaleWidth<=0&&scaleHeight<=0){
			bw=bf.getWidth();
			bh=bf.getHeight();
			scaleWidth = Coordinate.CoordinateX(f) / bw;
			scaleHeight = Coordinate.CoordinateY(g)/ bh;
		}
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bit=Bitmap.createBitmap(bf, 0,0,bw,bh, matrix, true);//蝮格?曉????
		matrix.reset();
		//bf.recycle();//?瑟?????

		return bit;
	}
	static Bitmap CutArea(Bitmap bt,int start_x,int start_y,int width,int height){
		Bitmap temp=Bitmap.createBitmap(bt, start_x,start_y, width, height);
		return temp;
	}
    static Bitmap MirrorFlipHorizontal(Bitmap bf){//鏡像水平翻轉
        Matrix matrix = new Matrix();
        matrix.postScale(-1,1);
        Bitmap bit=Bitmap.createBitmap(bf, 0,0,bf.getWidth(),bf.getHeight(), matrix, true);
        matrix.reset();

        return bit;
    }

	static void drawPic(Canvas canvas,Bitmap bit,int mid_x,int mid_y,float rot,int alpha,Paint paint){
		paint.setAntiAlias(true);
		paint.setAlpha(alpha);
		float x=Coordinate.CoordinateX(mid_x),y=Coordinate.CoordinateY(mid_y);
		if(rot%360==0)
			canvas.drawBitmap(bit, x-(bit.getWidth()/2), y-(bit.getHeight()/2), paint);
		else{
			Matrix matrix = new Matrix();
			matrix.preRotate(rot, (bit.getWidth()/2), (bit.getHeight()/2));
			matrix.postTranslate( x-(bit.getWidth()/2), y-(bit.getHeight()/2));
			canvas.drawBitmap(bit, matrix, paint);
			matrix.reset();
		}
		paint.reset();
	}

	static void drawLine(Canvas canvas,int color,int start_x,int start_y,int end_x,int end_y,int with,Paint paint){
		paint.setColor(color);																	//閮剖?憿???
		paint.setStrokeWidth(with);    //閮剖?蝺?撖?
		canvas.drawLine(Coordinate.CoordinateX(start_x), Coordinate.CoordinateY(start_y), Coordinate.CoordinateX(end_x),Coordinate.CoordinateY( end_y), paint);      //蝜芾ˊ?渡?
		paint.reset();
	}
    static void drawRect(Canvas canvas,int color,int start_x,int start_y,int end_x,int end_y,Paint paint){
        paint.setColor(color);
        canvas.drawRect(Coordinate.CoordinateX(start_x), Coordinate.CoordinateY(start_y), Coordinate.CoordinateX(end_x),Coordinate.CoordinateY( end_y), paint);
        paint.reset();
    }
}
