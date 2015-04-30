package com.musicsalvation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
//import android.widget.Toast;
import android.widget.Toast;

import com.musicsalvation.ChgSongView.ChgsongView;
import com.musicsalvation.EditView.EditView;
import com.musicsalvation.GameView.GameView;

@SuppressLint({ "HandlerLeak", "NewApi" })
public class MainActivity extends Activity{
    int first_activity=0;//修改第一畫面
    int nowView=0;


    MainView mainview;
    EditView editview;
    MapView mapview;
    GameView gameview;
    ScoreView scoreview;
    ChgsongView chgsongview;
    ChartListView chartListView;
    ChartDetailView chartview;
    Video video;

    final int MAIN=1,MAP=2,GAME=3,SCORE=4,EDIT=6,CHOOSE_FILE=7,CHANGE_SONG=8,CHART=9,DATA=10;
    public static FilesAndData io;


    public void changeView(int what)//
    {
        Message msg = myHandler.obtainMessage(what);
        myHandler.sendMessage(msg);
        nowView=what;
    }

    Handler myHandler = new Handler(){//處理各個SurfaceView傳送的訊息
        public void handleMessage(Message msg) {
            switch(msg.what)//
            {
                case 0:
                    //goToStartView();//初始
                    startVideo();
                    break;
                case 1:
                    goToMainView();//主要
                    break;
                case 2:
                    goToMapView();//地圖
                    break;
                case 3:
                    goToGameView();//游戲
                    break;
                case 4:
                    goToScoreView();//得分
                    break;
                case 6:
                    goToEditView();//編輯介面
                    break;
                case 7:
                    chooseFile();//選檔案
                    break;
                case 8:
                    goToChgsongView();//選歌介面
                    break;
                case 9:
                    goToChartView();
                    break;
                case 10:
                    goToDataView();
                    break;


                case 255:
                    Exit();
                    break;
            }
        }
    };


    protected void startVideo(){
        if(video==null){
            video=new Video(this);
        }
        setContentView(video);
        video.requestFocus();
        video.setFocusableInTouchMode(true);
    }

    protected void goToEditView() {
        if(editview==null)
        {
            editview=new EditView(this);
        }
        setContentView(editview);
        editview.requestFocus();
        editview.setFocusableInTouchMode(true);
    }
    private void goToMainView() {
        if(mainview==null)
        {
            mainview=new MainView(this);
        }
        setContentView(mainview);
        mainview.requestFocus();//取得焦點
        mainview.setFocusableInTouchMode(true);//設為可觸控
    }
    private void goToMapView() {
        if(mapview==null)
        {
            mapview=new MapView(this);
        }
        setContentView(mapview);
        mapview.requestFocus();//取得焦點
        mapview.setFocusableInTouchMode(true);//設為可觸控
    }
    private void goToGameView() {
        if(gameview==null)
        {
            gameview=new GameView(this);
        }
        setContentView(gameview);
        gameview.requestFocus();//取得焦點
        gameview.setFocusableInTouchMode(true);//設為可觸控
    }
    private void goToScoreView() {
        if(scoreview==null)
        {
            scoreview=new ScoreView(this);
        }
        setContentView(scoreview);
        scoreview.requestFocus();//取得焦點
        scoreview.setFocusableInTouchMode(true);

    }

    private void  goToChgsongView() {
        if (chgsongview == null) {
            chgsongview = new ChgsongView(this);
        }
        setContentView(chgsongview);
        chgsongview.requestFocus();//取得焦點
        chgsongview.setFocusableInTouchMode(true);
    }
    protected void goToDataView() {
        if(chartListView ==null)
        {
            chartListView =new ChartListView(this);
        }
        setContentView(chartListView);
        chartListView.requestFocus();
        chartListView.setFocusableInTouchMode(true);
    }
    protected void goToChartView() {
        if(chartview ==null)
        {
            chartview =new ChartDetailView(this);
        }
        setContentView(chartview);
        chartview.requestFocus();
        chartview.setFocusableInTouchMode(true);
    }
    private void Exit() {
        this.io.writeData();
        System.exit(0);//離開游戲
    }

    public void callAlartDialog(String what)//Alert訊息傳送
    {
        Message msg = toastHandler.obtainMessage(1,what);
        toastHandler.sendMessage(msg);
    }
    Handler toastHandler = new Handler(){//處理各個SurfaceView傳送的Alert訊息
        public void handleMessage(Message msg) {
            createAlartDialog((String)msg.obj);
        }
    };
    public void createAlartDialog(String msg){//顯示Alert

        Builder MyAlertDialog = new Builder(this);
        MyAlertDialog.setTitle("恭喜!");
        MyAlertDialog.setMessage("您破關了!\n現在隱藏要素已經解鎖囉\n回標題畫面看看吧！");
        //建立按下按鈕
        DialogInterface.OnClickListener OkClick = new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which) {
                changeView(1);
            }
        };
        DialogInterface.OnClickListener noClick = new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        MyAlertDialog.setPositiveButton("確認",OkClick );
        MyAlertDialog.setNegativeButton("取消",noClick );
        MyAlertDialog.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            io=new FilesAndData(this);
        }catch (Exception e){
            Log.e("FileData",""+e);
        }
        //游戲過程中只容許調整多媒體音量，而不容許調整通話音量
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉標題
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉標頭
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//強制橫屏


        //Toast.makeText(this, "widthPixels"+dm.widthPixels+"heightPixels"+dm.heightPixels, Toast.LENGTH_LONG).show();
        this.io.readData();
        changeView(first_activity);//進入"歡迎界面"
    }




    @Override
    public boolean onKeyDown(int keyCode,KeyEvent e)
    {
        if(keyCode==4)
        {
            switch(nowView)
            {
                case 14:
                    Constant.Flag=false;
                    this.changeView(10);
                    break;
                case 13:
                    Constant.Flag=false;
                    this.changeView(10);
                    break;
                case 12:
                    Constant.Flag=false;
                    this.changeView(10);
                    break;
                case 11:
                    Constant.Flag=false;
                    this.changeView(10);
                    break;
                case 9:
                    Constant.Flag=false;
                    this.changeView(10);
                    break;
                case 10:
                    Constant.Flag=false;
                    this.changeView(8);
                    break;
                case 8:
                    Constant.Flag=false;
                    this.changeView(1);
                    break;
                case 2:
                case 6:
                    Constant.Flag=false;
                    this.changeView(1);
                    break;
                case 3:
                    Constant.Flag=false;
                    this.changeView(4);
                    break;
                case 4:
                    Constant.Flag=false;
                    this.changeView(2);
                    break;
                case 0://歡迎界面
                case 1://主控制界面
                    Exit();
                    break;

            }
            return true;
        }
		/*if(keyCode==e.KEYCODE_HOME){
			 System.exit(0);
			return true;
		}*/
        return false;

    }
    public void chooseFile(){//檔案選擇器設定
        Intent intent = new Intent( Intent.ACTION_GET_CONTENT);//ACTION_PICK,android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI );						// 建立 "選擇檔案 Action" 的 Intent
        intent.setType("audio/*");														// 過濾檔案格式
        Intent deintent = Intent.createChooser(intent, "選擇檔案");		// 建立 "檔案選擇器" 的 Intent  (第二個參數: 選擇器的標題)
        startActivityForResult( deintent, 0 );									// 切換到檔案選擇器 (它的處理結果, 會觸發 onActivityResult 事件)
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        // 有選擇檔案
        // Looper.prepare();
        if ( resultCode == RESULT_OK )
        {
            // 取得檔案的 Uri
            this.io.chosen_song = data.getData();
            if( this.io.chosen_song != null )
            {
                Toast.makeText(this, "檔案已選擇!", Toast.LENGTH_SHORT).show();
                this.changeView(8);
                Constant.Flag=true;
            }
            else
            {
                Toast.makeText(this, "無效的檔案路徑 !", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "取消選擇檔案 !", Toast.LENGTH_SHORT).show();
        }
        //Looper.loop();
    }
	/*public String checkType(Uri uri){
		ContextWrapper context = null;
		ContentResolver cR = context.getContentResolver();
		MimeTypeMap mime = MimeTypeMap.getSingleton();
		String type = mime.getExtensionFromMimeType(cR.getType(uri));
		return type;
	}*/
	/*public Uri sendUri(){
		return song;
	}*/

    /*public static String turnUriToName(Uri u){
        String a=u.toString(),b="";
        for(int i=a.length();i>0;i--){
            if(a.substring(i-1, i).equals("/")){
                break;
            }else if(a.substring(i-3, i).equals("%20")){
                b+=" ";
                i-=2;
            }else b+=a.substring(i-1, i);
        }

        return Reversion(b);
    }
    public static String Reversion(String temp){
        String c="";
        for(int i=temp.length();i>0;i--){
            c+=temp.subSequence(i-1, i);
        }
        return c;
    }

    public JSONObject read(Uri uri){//譜面讀取
        //String fileName=turnUriToName(uri)+".charts_obj";
        JSONObject json=null;
        String content=""; //內容
        byte[] buff = new byte[1024];

        try {
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File (sdCard.getAbsolutePath() + "/MusicSelvation_datas/charts_obj");
            dir.mkdirs();
            File files = new File(dir, turnUriToName(uri)+".charts_obj");
            FileInputStream file =new FileInputStream(files);
            //FileInputStream file=openFileInput(fileName);
            while((file.read(buff))!=-1){
                content+=new String(buff).trim();
            }
            json=new JSONObject(content);
            file.close();
        } catch (FileNotFoundException e) {
            Log.e("read", "找不到檔案");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("read", "讀取檔案失敗");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("read", "寫入json失敗");
            e.printStackTrace();
        };
        return json;
    }
    public JSONObject read(String name){//譜面讀取
        //String fileName=turnUriToName(uri)+".charts_obj";
        JSONObject json=null;
        String content=""; //內容
        byte[] buff = new byte[1024];

        try {
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File (sdCard.getAbsolutePath() + "/MusicSelvation_datas/charts_obj");
            dir.mkdirs();
            File files = new File(dir, name+".charts_obj");
            FileInputStream file =new FileInputStream(files);
            //FileInputStream file=openFileInput(fileName);
            while((file.read(buff))!=-1){
                content+=new String(buff).trim();
            }
            json=new JSONObject(content);
            file.close();
        } catch (FileNotFoundException e) {
            Log.e("read", "找不到檔案");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("read", "讀取檔案失敗");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("read", "寫入json失敗");
            e.printStackTrace();
        };
        return json;
    }

    public  void write(Uri uri,JSONObject btR,JSONObject btS,JSONObject btT,JSONObject btX){//譜面寫入
        JSONObject json=new JSONObject();
        try {
            json.put("R", btR);
            json.put("S", btS);
            json.put("T",btT);
            json.put("X", btX);
        } catch (JSONException e) {
            Log.e("write", "無法將參數導入json");
            e.printStackTrace();
        }
        try {
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File (sdCard.getAbsolutePath() + "/MusicSelvation_datas/charts_obj");
            dir.mkdirs();
            File file = new File(dir, turnUriToName(uri)+".charts_obj");
            FileOutputStream writer =new FileOutputStream(file);

            //String fileName=turnUriToName(uri)+".charts_obj";
            //FileOutputStream writer = openFileOutput(fileName, Context.MODE_PRIVATE);
            writer.write(json.toString().getBytes());
            writer.close();
            Log.v("write", "資料寫入成功");
        } catch (FileNotFoundException e) {
            Log.e("write", "FileNotFoundException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("write", "IOException");
            e.printStackTrace();
        }
    }

    public void readData(){//TAG 存檔讀取
        String fileName="Data.save";
        JSONObject json=null;
        String content=""; //內容
        byte[] buff = new byte[1024];

        try {
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File (sdCard.getAbsolutePath() + "/MusicSelvation_datas/data");
            dir.mkdirs();
            File files = new File(dir,fileName);
            FileInputStream file =new FileInputStream(files);
            //FileInputStream file=openFileInput(fileName);
            while((file.read(buff))!=-1){
                content+=new String(buff).trim();
            }
            json=new JSONObject(content);
            mp_Voiume=Float.valueOf(json.getString("mp_Voiume"));
            sp_Voiume=Float.valueOf(json.getString("sp_Voiume"));
            sp_num=json.getInt("sp_num");
            speed=json.optInt("game_speed",1);
            timing=json.getInt("game_timing");
            animax_buffer=json.optInt("animax_buffer", 3);

            for(int i=0;i<levels;i++){
                for(int j=0;j<3;j++){
                    if(json.optJSONArray("level_data").optJSONArray(i).optJSONObject(j)!=null){
                        hight_score[i][j]=json.optJSONArray("level_data").optJSONArray(i).optJSONObject(j).optInt("hight_score");
                        hight_rank[i][j]=json.optJSONArray("level_data").optJSONArray(i).optJSONObject(j).optInt("hight_rank");
                        level_clear[i][j]=json.optJSONArray("level_data").optJSONArray(i).optJSONObject(j).optBoolean("level_clear", false);
                    }else{
                        hight_score[i][j]=0;
                        hight_rank[i][j]=0;
                    }
                }
            }
            file.close();
            Log.v("Data", "Data read");
        } catch (FileNotFoundException e) {
            mp_Voiume=1;
            sp_Voiume=1;
            sp_num=0;
            speed=1;
            timing=0;
            animax_buffer=3;
            for(int i=0;i<levels;i++){
                for(int j=0;j<3;j++){
                    level_clear[i][j]=false;
                    hight_score[i][j]=0;
                    hight_rank[i][j]=0;
                }
            }
            Log.v("Data", "Data not found");
            writeData();
            e.printStackTrace();
        } catch (IOException e) {
            Log.v("Data","讀取檔案失敗");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.v("Data","寫入json失敗");
            e.printStackTrace();
        };
    }

    public  void writeData(){//存檔寫入
        JSONObject json=new JSONObject();
        try {
            json.put("mp_Voiume", String.valueOf(mp_Voiume));
            json.put("sp_Voiume", String.valueOf(sp_Voiume));
            json.put("sp_num",sp_num);
            json.put("game_speed",speed);
            json.put("game_timing", timing);
            json.put("animax_buffer", animax_buffer);

            json.put("level_data", new JSONArray());
            for(int i=0;i<levels;i++){
                json.optJSONArray("level_data").put(i, new JSONArray());
                for(int j=0;j<3;j++){
                    json.optJSONArray("level_data").optJSONArray(i).put(j, new JSONObject());
                    json.optJSONArray("level_data").optJSONArray(i).optJSONObject(j).put("hight_score",hight_score[i][j]);
                    json.optJSONArray("level_data").optJSONArray(i).optJSONObject(j).put("hight_rank",hight_rank[i][j]);
                    json.optJSONArray("level_data").optJSONArray(i).optJSONObject(j).put("level_clear",level_clear[i][j]);
                }
            }
        } catch (JSONException e) {
            Log.v("Data","無法將參數導入json");
            e.printStackTrace();
        }
        try {
            //String fileName="Data.save";
            //FileOutputStream writer = openFileOutput(fileName, Context.MODE_PRIVATE);
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File (sdCard.getAbsolutePath() + "/MusicSelvation_datas/data");
            dir.mkdirs();
            File file = new File(dir, "Data.save");
            FileOutputStream writer =new FileOutputStream(file);
            writer.write(json.toString().getBytes());
            writer.close();
            Log.v("Data", "Data saved");
        } catch (FileNotFoundException e) {
            Log.v("Data","FileNotFoundException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.v("Data","IOException");
            e.printStackTrace();
        }
    }*/
    @Override
    public void onResume(){
        Constant.setFlag(true);
        changeView(nowView);
        DisplayMetrics dm=new DisplayMetrics();
        if(Build.VERSION.SDK_INT>=19){
            getWindow().getDecorView().setSystemUiVisibility(
                    this.getWindow().getDecorView().SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | this.getWindow().getDecorView().SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | this.getWindow().getDecorView().SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | this.getWindow().getDecorView().SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | this.getWindow().getDecorView().SYSTEM_UI_FLAG_FULLSCREEN
                            | this.getWindow().getDecorView().SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | this.getWindow().getDecorView().INVISIBLE);
            getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        }else{
            //取得解析度
            getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        //給常數類別中的螢幕高和寬給予值
        if(dm.widthPixels>dm.heightPixels)
        {
            Constant.SYSTEM_WIDTH=dm.widthPixels;
            Constant.SYSTEM_HIGHT=dm.heightPixels;
        }else
        {
            Constant.SYSTEM_HIGHT=dm.widthPixels;
            Constant.SYSTEM_WIDTH=dm.heightPixels;
        }
        if(Constant.SYSTEM_HIGHT>Constant.SYSTEM_WIDTH/16*9) {//將螢幕固定為16:9
            Constant.SCREEN_HIGHT = Constant.SYSTEM_WIDTH / 16 * 9;//Y座標校正
            Constant.SCREEN_WIDTH=Constant.SYSTEM_WIDTH;
        }
        else{
            Constant.SCREEN_WIDTH = Constant.SYSTEM_HIGHT / 9 * 16;//X座標校正
            Constant.SCREEN_HIGHT=Constant.SYSTEM_HIGHT;
        }

        Constant.SCREEN_WIDTH_UNIT = ((float)Constant.SCREEN_WIDTH/Constant.DEFULT_WIDTH);
        Constant.SCREEN_HEIGHT_UNIT= ((float)Constant.SCREEN_HIGHT/Constant.DEFULT_HIGHT);
        super.onResume();
    }
    @Override
    public void onPause(){
        Constant.setFlag(false);
		/*	try{
		if(mainview.back_mp.isPlaying())
			mainview.back_mp.pause();
		}catch(Exception e){
			Log.e("pause", ""+e);
		}*/
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        this.io.writeData();
        super.onDestroy();
    }


    public void shareTo(String subject, String body, String chooserTitle, Bitmap imageUri) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);

        startActivity(Intent.createChooser(sharingIntent, chooserTitle));
        sharingIntent.putExtra(Intent.EXTRA_STREAM, imageUri);

    }

}
