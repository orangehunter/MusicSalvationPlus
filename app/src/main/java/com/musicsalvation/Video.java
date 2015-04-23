package com.musicsalvation;


import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Video extends SurfaceView {
	MainActivity activity;
	MediaPlayer mp;
	SurfaceHolder sh;
	int video[]={	R.raw.exl,
			R.raw.story,
			R.raw.access,
			R.raw.staff_csu};
	int view[]={	1,
			2,
			3,
			1};
	@SuppressWarnings("deprecation")
	public Video(final MainActivity activity){
		super(activity.getApplicationContext());
		this.activity=activity;
		sh=this.getHolder();
		sh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		sh.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) {
				if(mp!=null){
					mp.release();
					mp=null;
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder arg0) {

				mp=MediaPlayer.create(activity, video[activity.io.video_select]);
				mp.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						changeView();
					}
				});
				mp.setDisplay(sh);
				mp.start();

			}

			@Override
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub

			}
		});
	}
	public void changeView(){
			activity.changeView(view[activity.io.video_select]);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN://按下

			break;
		case MotionEvent.ACTION_UP://抬起
			changeView();
			break;
		}

		return true;
	}

}
