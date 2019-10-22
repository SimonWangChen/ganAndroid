package com.proclassmates.ganandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

//    String testURL = "http://fanyi.youdao.com/openapi.do?keyfrom=<keyfrom>&key=<key>&type=data&doctype=json&version=1.1&q=good";
    String testURL = "http://baidu.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<String, Void, Void>(){

                    @Override
                    protected Void doInBackground(String... strings) {
                        try{
                            URL url = new URL(strings[0]);

                            URLConnection connection = url.openConnection();

                            InputStream inputStream = connection.getInputStream();

                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                            String line ;

                            while((line = bufferedReader.readLine()) !=null ){
                                System.out.println(line);
                            }

                            bufferedReader.close();
                            inputStreamReader.close();
                            inputStream.close();

                        }catch(IOException e){
                            e.printStackTrace();
                        }finally {
                        }
                        return null;
                    }
                }.execute(testURL);
            }
        });
    }
    /**
     * 视频网站 https://www.bilibili.com/video/av10253907/?p=288
     * 【极客学院教学视频】Android从入门到精通
     * 有道 api
     * http://fanyi.youdao.com/openapi.do?keyfrom=<keyfrom>&key=<key>&type=data&doctype=json&version=1.1&q=good
     * return code 50 无效的 key
     * todo: 1.add INTERNET permission
     * todo: 2.extends AsyncTask
     * todo: 3.HttpUrlConnection get 请求
     *java.io.IOException: Cleartext HTTP traffic to dict.youdao.com not permitted
     * Android引入了对Https的推荐支持，而Android P的系统上面默认所有Http的请求都被阻止了
     *
     * 方法
     *
     * 在AnroidManifest.xml中的application设置android:usesCleartextTraffic="true"
     *
     * **/


}
