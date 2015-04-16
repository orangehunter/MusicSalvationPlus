package com.musicsalvation;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by user on 2015/3/24.
 */
public class FilesAndData {
    MainActivity activity;
    public FilesAndData(MainActivity activity) throws FileNotFoundException, IOException {
        this.activity=activity;
    }
    //影片選擇====================================
    public static int video_select=0;
    //影片選擇------------------------------------------------------------

    //判定與分數===================================
    public static int virus = 0;  //病毒數量
    public static int percent = 0; //判定是否過關數量
    public static int nice = 0;
    public static int hit = 0;
    public static int safe = 0;
    public static int miss = 0;
    public static int score = 0;
    public static int combo = 0;
    public static boolean boss_delete;
    //判定與分數-----------------------------------

    //選關參數=====================================
    public static int level;//關卡
    public static int levels=3;//關卡總數
    public static int difficulty;//難度
    public static int [][]hight_score=new int [levels][3];
    public static int [][]hight_rank=new int [levels][3];
    public static Boolean [][]level_clear=new Boolean[levels][3];
    //選關參數-------------------------------------

    //選歌參數======================================
    public static String song_name;
    public static int chart_id;
    public static Uri song_uri;
    //選歌參數--------------------------------------

    //存檔用參數====================================
    public static float mp_Voiume;
    public static float sp_Voiume;
    public static int sp_num;
    public static int timing;
    public static int speed;
    public static int animax_buffer;
    //存檔用參數-------------------------------------

    public static File getChartDir(){
        File root = Environment.getDataDirectory();
        File dir = new File (root.getAbsolutePath() + "/charts");
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir;
    }
    public static File getDataDir(){
        File root = Environment.getDataDirectory();
        File dir = new File (root.getAbsolutePath() + "/Datas");
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir;
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
    public static boolean chart_exists(String fileName){
        File dir = getChartDir();
        File files = new File(dir, fileName + ".charts");
        return files.exists();
    }
    public static Charts readChart(String fileName){//譜面讀取
        Charts ct=new Charts();
        String content=""; //內容
        byte[] buff = new byte[1024];

        try {

            File dir = getChartDir();
            File files = new File(dir, fileName + ".charts");
            FileInputStream file = new FileInputStream(files);
            while ((file.read(buff)) != -1) {
                content += new String(buff).trim();
            }
            JSONObject json = new JSONObject(content);
            ct.charts =json;
        }catch (Exception e){
            Log.e("readChart",""+e);
        }
        return ct;
    }
    public static JSONObject readGameChart(String fileName){//譜面讀取
        Charts ct=new Charts();
        String content=""; //內容
        byte[] buff = new byte[1024];

        try {

            File dir = getChartDir();
            File files = new File(dir, fileName + ".charts");
            FileInputStream file = new FileInputStream(files);
            while ((file.read(buff)) != -1) {
                content += new String(buff).trim();
            }
            JSONObject json = new JSONObject(content);
            ct.charts =json;
        }catch (Exception e){
            Log.e("readGameChart",""+e);
        }
        JSONObject js=new JSONObject();
        JSONObject Rt,St,Tt,Xt;
        Rt=new JSONObject();
        St=new JSONObject();
        Tt=new JSONObject();
        Xt=new JSONObject();
        if (ct!=null) {
            Iterator<String> iter = ct.charts.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    if (ct.charts.getJSONObject(key).has("R")) {
                        Rt.put(key,true);
                    }
                    if (ct.charts.getJSONObject(key).has("S")) {
                        St.put(key,true);
                    }
                    if (ct.charts.getJSONObject(key).has("T")) {
                        Tt.put(key,true);
                    }
                    if (ct.charts.getJSONObject(key).has("X")) {
                        Xt.put(key,true);
                    }
                }catch (Exception e){
                    Log.e("readGameChart",""+e);
                }
            }
        }
        try {
        js.put("R",Rt);
        js.put("S",St);
        js.put("T",Tt);
        js.put("X",Xt);
        }catch (Exception e){
            Log.e("readGameChart",""+e);
        }
        return js;
    }

    public static  void writeChart(String fileName,Charts ct){//譜面寫入
        JSONObject json=ct.charts;
        try {
            File dir = getDataDir();
            File file = new File(dir, fileName+".charts");
            Log.v("Chart","write to"+file);
            FileOutputStream writer =new FileOutputStream(file);
            writer.write(json.toString().getBytes());
            writer.close();
            Log.v("Chart", "writeChart");
        } catch (FileNotFoundException e) {
            Log.v("Chart","FileNotFoundException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.v("Chart","IOException");
            e.printStackTrace();
        }
    }

    public static void readData(){//TAG 存檔讀取
        String fileName="Data.save";
        JSONObject json=null;
        String content=""; //內容
        byte[] buff = new byte[1024];

        try {

            File dir = getDataDir();
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

    public static void writeData(){//存檔寫入
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
            File dir = getDataDir();
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
}
