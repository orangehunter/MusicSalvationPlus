package com.musicsalvation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

/**
 * Created by user on 2015/5/13.
 */
public class Setting {
    MainActivity activity;
    boolean main_flag=false;

    Bitmap back;

    MySeekBar music_volum,button_volum;
    Bitmap bar,btn;

    Botton sound_btn[];
    Bitmap btn_on[],btn_off[];

    Botton exit;
    Bitmap ext;

    SoundPool st_sp;
    int sp_id[];
    int main_alpha=0;

    public Setting(MainActivity activity){
        this.activity=activity;
        back=Graphic.LoadBitmap(activity.getResources(),R.drawable.st_tempback,1280,540,false);

        bar=Graphic.LoadBitmap(activity.getResources(),R.drawable.st_volbar,490,90,false);
        btn=Graphic.LoadBitmap(activity.getResources(),R.drawable.st_volbtn,46,97,false);
        music_volum=new MySeekBar(activity,bar,btn,730,175);
        music_volum.setSeekBarFloat(activity.io.mp_Voiume);
        button_volum=new MySeekBar(activity,bar,btn,730,300);
        button_volum.setSeekBarFloat(activity.io.sp_Voiume);

        btn_on=new Bitmap[5];
        btn_off=new Bitmap[5];
        sound_btn=new Botton[5];
        for (int i=0;i<5;i++){
            btn_on[i]=Graphic.LoadBitmap(activity.getResources(),R.drawable.st_t_se01+i,138,138,true);
            btn_off[i]=Graphic.LoadBitmap(activity.getResources(),R.drawable.st_f_se01+i,84,84,true);
            sound_btn[i]=new Botton(activity,btn_on[i],btn_off[i],525+(110*i),420);
        }
        ext=Graphic.LoadBitmap(activity.getResources(),R.drawable.st_exit_btn,140,130,true);
        exit=new Botton(activity,ext,ext,75,180);

        st_sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
        sp_id=new int[8];
        sp_id[0]=st_sp.load(activity, R.raw.tambourine, 1);
        sp_id[1]=st_sp.load(activity, R.raw.drum_cymbal, 1);
        sp_id[2]=st_sp.load(activity, R.raw.drum_snare, 1);
        sp_id[3]=st_sp.load(activity, R.raw.fall, 1);
        sp_id[4]=st_sp.load(activity, R.raw.voice_dog, 1);
        sp_id[5]=st_sp.load(activity, R.raw.left_menu_on, 1);
        sp_id[6]=st_sp.load(activity, R.raw.left_menu_off, 1);
        sp_id[7]=st_sp.load(activity, R.raw.leftm_btn, 1);
    }
    public void start(){
        st_sp.play(sp_id[5],activity.io.sp_Voiume,activity.io.sp_Voiume,0,0,1);
        main_flag=true;

    }
    public void Draw(Canvas canvas,Paint paint){
        if (main_flag){
            main_alpha = Coordinate.AnalogSpeedMove(main_alpha,255);

        }else {
            main_alpha = Coordinate.AnalogSpeedMove(main_alpha,0);
        }
        if (main_alpha>0){
            if (main_alpha-100>0) {
                Graphic.drawRect(canvas, Color.BLACK, 0, 0, 1280, 720, main_alpha - 100, paint);
            }
            Graphic.drawPic(canvas,back,1280/2,720/2,0,main_alpha,paint);
            music_volum.drawSeekBar(canvas,main_alpha,paint);
            button_volum.drawSeekBar(canvas,main_alpha,paint);
            for (Botton arr:sound_btn){
                arr.drawBtm(canvas,paint,main_alpha);
            }
            exit.drawBtm(canvas,paint,main_alpha);

        }
    }
    public void Action_Dowm(double pointX,double pointY){
        if (main_flag){
            for(int i=0;i<5;i++){
                if (sound_btn[i].isIn(pointX, pointY)){
                    st_sp.play(sp_id[i], activity.io.sp_Voiume, activity.io.sp_Voiume, 0, 0, 1);
                    for(int j=0;j<5;j++){
                        if (j==i){
                            activity.io.sp_num=j;
                            sound_btn[j].setBottomTo(true);
                        }else {
                            sound_btn[j].setBottomTo(false);
                        }
                    }
                    break;
                }
            }
            if (music_volum.isOn((float) pointX, (float) pointY)){
                st_sp.play(sp_id[5],activity.io.mp_Voiume,activity.io.mp_Voiume,0,0,1);
                music_volum.setFlag(true);
            }
            if (button_volum.isOn((float) pointX, (float) pointY)){
                st_sp.play(sp_id[activity.io.sp_num],activity.io.sp_Voiume,activity.io.sp_Voiume,0,0,1);
                button_volum.setFlag(true);
            }
        }
    }
    public  void Action_Move(double pointX,double pointY){
        if (main_flag){
            if (music_volum.getFlag()){
                music_volum.setSeekBarX((float)pointX);
            }
            if (button_volum.getFlag()){
                button_volum.setSeekBarX((float)pointX);
            }
        }
    }
    public void Action_Up(double pointX,double pointY){
        if (main_flag){
            if (exit.isIn(pointX,pointY)){
                st_sp.play(sp_id[6],activity.io.sp_Voiume,activity.io.sp_Voiume,0,0,1);
                main_flag=false;
            }
            if (music_volum.getFlag()){
                int temp=(int)music_volum.getSeekBarValue();
                music_volum.setSeekBarFloat((temp - (temp % 10)));
                activity.io.mp_Voiume=(float) ((temp-(temp%10))/100.0);
                activity.io.writeData();
                st_sp.play(sp_id[6], activity.io.mp_Voiume, activity.io.mp_Voiume, 0, 0, 1);
                music_volum.setFlag(false);
            }
            if (button_volum.getFlag()){
                int temp=(int)button_volum.getSeekBarValue();
                button_volum.setSeekBarFloat((temp - (temp % 10)));
                activity.io.sp_Voiume=(float) ((temp - (temp % 10)) / 100.0);
                activity.io.writeData();
                st_sp.play(sp_id[activity.io.sp_num],activity.io.sp_Voiume,activity.io.sp_Voiume,0,0,1);
                button_volum.setFlag(false);
            }
        }
    }
    public Boolean getMainFlag(){
        return main_flag;
    }
    public void recycle(){
        back.recycle();

        bar.recycle();
        btn.recycle();

        for(Bitmap arr:btn_on){
            arr.recycle();
        }
        for(Bitmap arr:btn_off){
            arr.recycle();
        }

        ext.recycle();
    }

}
