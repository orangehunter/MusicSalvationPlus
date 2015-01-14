package com.musicsalvation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
//import android.widget.Toast;
import android.widget.Toast;

@SuppressLint({ "HandlerLeak", "NewApi" })
public class MainActivity extends Activity{
	int first_activity=0;
	int nowView=0;
	StartView startview;
	MainView mainview;
	EditView editview;
	MapView mapview;
	GameView gameview;
	ScoreView scoreview;
	TestView testview;
	Video video;

	Intent intent;
	Intent deintent;
	Uri uri;

	//�v�����====================================
	int video_select=0;
	//�v�����------------------------------------------------------------

	//�P�w�P����===================================
	int virus = 0;  //�f�r�ƶq
	int percent = 0; //�P�w�O�_�L���ƶq
	int nice = 0;
	int hit = 0;
	int safe = 0;
	int miss = 0;
	int score = 0;
	int combo = 0;  
	boolean boss_delete;
	//�P�w�P����-----------------------------------

	//�����Ѽ�=====================================
	int level;//���d
	int levels=3;//���d�`��
	int difficulty;//����
	int [][]hight_score=new int [levels][3];
	int [][]hight_rank=new int [levels][3];
	Boolean [][]level_clear=new Boolean[levels][3];
	//�����Ѽ�-------------------------------------

	//�s�ɥΰѼ�====================================
	float mp_Voiume;
	float sp_Voiume;
	int sp_num;
	int timing;
	int speed;
	int animax_buffer;
	//�s�ɥΰѼ�-------------------------------------
	public void changeView(int what)//
	{
		Message msg = myHandler.obtainMessage(what); 
		myHandler.sendMessage(msg);
		nowView=what;
	} 

	Handler myHandler = new Handler(){//�B�z�U��SurfaceView�ǰe���T��
		public void handleMessage(Message msg) {
			switch(msg.what)//
			{
			case 0:
				//goToStartView();//��l
				startVideo();
				break;
			case 1:
				goToMainView();//�D�n
				break;
			case 2:
				goToMapView();//�a��
				break;
			case 3:
				goToGameView();//����
				break;
			case 4:
				goToScoreView();//�o��
				break;
			case 5:
				goToLastView();//����
				break;
			case 6:
				goToEditView();
				break;
			case 7:
				chooseFile();
				break;
			case 8:
				goToTestView();
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
	protected void goToTestView() {
		if(testview==null)
		{
			testview=new TestView(this);
		}
		setContentView(testview);
		testview.requestFocus();
		testview.setFocusableInTouchMode(true);
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
	private void goToStartView() {
		if(startview==null)
		{
			startview=new StartView(this);
		}
		setContentView(startview);
	}
	private void goToMainView() {
		if(mainview==null)
		{
			mainview=new MainView(this);
		}
		setContentView(mainview);
		mainview.requestFocus();//���o�J�I
		mainview.setFocusableInTouchMode(true);//�]���iĲ��
	}
	private void goToMapView() {
		if(mapview==null)
		{
			mapview=new MapView(this);
		}
		setContentView(mapview);
		mapview.requestFocus();//���o�J�I
		mapview.setFocusableInTouchMode(true);//�]���iĲ��
	}
	private void goToGameView() {
		if(gameview==null)
		{
			gameview=new GameView(this);
		}
		setContentView(gameview);
		gameview.requestFocus();//���o�J�I
		gameview.setFocusableInTouchMode(true);//�]���iĲ��
	}
	private void goToScoreView() {
		if(scoreview==null)
		{
			scoreview=new ScoreView(this);
		}
		setContentView(scoreview);
		scoreview.requestFocus();//���o�J�I
		scoreview.setFocusableInTouchMode(true);

	}
	private void goToLastView() {
		// TODO �۰ʲ��ͪ���k Stub

	}
	private void Exit() {
		writeData();
		System.exit(0);//���}����
	}

	public void callAlartDialog(String what)//Alert�T���ǰe
	{
		Message msg = toastHandler.obtainMessage(1,what); 
		toastHandler.sendMessage(msg);
	} 
	Handler toastHandler = new Handler(){//�B�z�U��SurfaceView�ǰe��Alert�T��
		public void handleMessage(Message msg) {
			createAlartDialog((String)msg.obj);
		}
	};
	public void createAlartDialog(String msg){//���Alert
		
		Builder MyAlertDialog = new Builder(this);
		MyAlertDialog.setTitle("����!");
		MyAlertDialog.setMessage("�z�}���F!\n�{�b���ín���w�g�����o\n�^���D�e���ݬݧa�I");
		//�إ߫��U���s
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
		MyAlertDialog.setPositiveButton("�T�{",OkClick );
		MyAlertDialog.setNegativeButton("����",noClick );
		MyAlertDialog.show();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//�����L�{���u�e�\�վ�h�C�魵�q�A�Ӥ��e�\�վ�q�ܭ��q
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//�h�����D
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);//�h�����Y
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//�j����

		//���o�ѪR��
		DisplayMetrics dm=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		//���`�����O�����ù����M�e������
		if(dm.widthPixels>dm.heightPixels)
		{
			Constant.SCREEN_WIDTH=dm.widthPixels;
			Constant.SCREEN_HIGHT=dm.heightPixels;
		}else
		{
			Constant.SCREEN_HIGHT=dm.widthPixels;
			Constant.SCREEN_WIDTH=dm.heightPixels;
		}
		if(Constant.SCREEN_HIGHT>Constant.SCREEN_WIDTH/16*9)//�N�ù��T�w��16:9
			Constant.SCREEN_HIGHT=Constant.SCREEN_WIDTH/16*9;
		else
			Constant.SCREEN_WIDTH=Constant.SCREEN_HIGHT/9*16;

		Constant.GAME_WIDTH_UNIT= ((float)Constant.SCREEN_WIDTH/Constant.DEFULT_WITH);
		Constant.SCREEN_HEIGHT_UNIT= ((float)Constant.SCREEN_HIGHT/Constant.DEFULT_HIGHT);
		//Toast.makeText(this, "widthPixels"+dm.widthPixels+"heightPixels"+dm.heightPixels, Toast.LENGTH_LONG).show();
		readData();
		changeView(first_activity);//�i�J"�w��ɭ�"
	}




	@Override
	public boolean onKeyDown(int keyCode,KeyEvent e)
	{
		if(keyCode==4)
		{
			switch(nowView)
			{
			case 2:
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
			case 0://�w��ɭ�
			case 1://�D����ɭ�
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
	public void chooseFile(){//�ɮ׿�ܾ��]�w
		Intent intent = new Intent( Intent.ACTION_GET_CONTENT);//ACTION_PICK,android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI );						// �إ� "����ɮ� Action" �� Intent
		intent.setType("audio/*");														// �L�o�ɮ׮榡
		Intent deintent = Intent.createChooser(intent, "����ɮ�");		// �إ� "�ɮ׿�ܾ�" �� Intent  (�ĤG�ӰѼ�: ��ܾ������D)
		startActivityForResult( deintent, 0 );									// �������ɮ׿�ܾ� (�����B�z���G, �|Ĳ�o onActivityResult �ƥ�)
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		// ������ɮ�
		// Looper.prepare();
		if ( resultCode == RESULT_OK )
		{
			// ���o�ɮת� Uri
			uri = data.getData();
			if( uri != null )
			{
				Toast.makeText(this, "�ɮפw���!", Toast.LENGTH_SHORT).show();
				this.changeView(6);
				Constant.Flag=true;
			}
			else
			{
				Toast.makeText(this, "�L�Ī��ɮ׸��| !", Toast.LENGTH_SHORT).show();
			}
		}
		else
		{
			Toast.makeText(this, "��������ɮ� !", Toast.LENGTH_SHORT).show();
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
	public Uri sendUri(){
		return uri;
	}

	public static String turnUriToName(Uri u){
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

	public JSONObject read(Uri uri){//�Э�Ū��
		//String fileName=turnUriToName(uri)+".chart";
		JSONObject json=null;
		String content=""; //���e
		byte[] buff = new byte[1024];

		try {
			File sdCard = Environment.getExternalStorageDirectory();
			File dir = new File (sdCard.getAbsolutePath() + "/MusicSelvation_datas/charts");
			dir.mkdirs();
			File files = new File(dir, turnUriToName(uri)+".chart");
			FileInputStream file =new FileInputStream(files);
			//FileInputStream file=openFileInput(fileName);
			while((file.read(buff))!=-1){
				content+=new String(buff).trim();
			}
			json=new JSONObject(content);
			file.close();
		} catch (FileNotFoundException e) {
			Log.e("read", "�䤣���ɮ�");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("read", "Ū���ɮץ���");
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e("read", "�g�Jjson����");
			e.printStackTrace();
		};
		return json;
	}
	public JSONObject read(String name){//�Э�Ū��
		//String fileName=turnUriToName(uri)+".chart";
		JSONObject json=null;
		String content=""; //���e
		byte[] buff = new byte[1024];

		try {
			File sdCard = Environment.getExternalStorageDirectory();
			File dir = new File (sdCard.getAbsolutePath() + "/MusicSelvation_datas/charts");
			dir.mkdirs();
			File files = new File(dir, name+".chart");
			FileInputStream file =new FileInputStream(files);
			//FileInputStream file=openFileInput(fileName);
			while((file.read(buff))!=-1){
				content+=new String(buff).trim();
			}
			json=new JSONObject(content);
			file.close();
		} catch (FileNotFoundException e) {
			Log.e("read", "�䤣���ɮ�");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("read", "Ū���ɮץ���");
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e("read", "�g�Jjson����");
			e.printStackTrace();
		};
		return json;
	}

	public  void write(Uri uri,JSONObject btR,JSONObject btS,JSONObject btT,JSONObject btX){//�Э��g�J
		JSONObject json=new JSONObject();
		try {
			json.put("R", btR);
			json.put("S", btS);
			json.put("T",btT);
			json.put("X", btX);
		} catch (JSONException e) {
			Log.e("write", "�L�k�N�ѼƾɤJjson");
			e.printStackTrace();
		}
		try {
			File sdCard = Environment.getExternalStorageDirectory();
			File dir = new File (sdCard.getAbsolutePath() + "/MusicSelvation_datas/charts");
			dir.mkdirs();
			File file = new File(dir, turnUriToName(uri)+".chart");
			FileOutputStream writer =new FileOutputStream(file);

			//String fileName=turnUriToName(uri)+".chart";
			//FileOutputStream writer = openFileOutput(fileName, Context.MODE_PRIVATE);
			writer.write(json.toString().getBytes());
			writer.close();
			Log.v("write", "��Ƽg�J���\");
		} catch (FileNotFoundException e) {
			Log.e("write", "FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("write", "IOException");
			e.printStackTrace();
		}
	}

	public void readData(){//TAG �s��Ū��
		String fileName="Data.save";
		JSONObject json=null;
		String content=""; //���e
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
			Log.v("Data","Ū���ɮץ���");
			e.printStackTrace();
		} catch (JSONException e) {
			Log.v("Data","�g�Jjson����");
			e.printStackTrace();
		};
	}

	public  void writeData(){//�s�ɼg�J
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
			Log.v("Data","�L�k�N�ѼƾɤJjson");
			e.printStackTrace();
		}
		try {
			/*String fileName="Data.save";
			FileOutputStream writer = openFileOutput(fileName, Context.MODE_PRIVATE);*/
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
	}

	@Override 
	public void onResume(){
		Constant.setFlag(true);
		changeView(nowView);
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
		writeData();
		super.onDestroy();
	}

}
