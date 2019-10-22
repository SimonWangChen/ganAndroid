package com.proclassmates.ganandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;

    private NetworkImageView networkImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // todo: 1.test Volley get
        // getJsonVolley();
        // todo: 2.test Volley image
        // todo: 3.init ImageView
        iv = findViewById(R.id.imageView);

        // todo: 4. NetworkImageView
        networkImageView = findViewById(R.id.imageView2);

        inflateImage();

        inflateNetworkImage();
    }

    private void inflateNetworkImage() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String imgUrl = "http://apidev.proclassmates.com:8999/media/avatar/jasmine.png";

        final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(20);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String key) {
                return lruCache.get(key);
            }

            @Override
            public void putBitmap(String key, Bitmap value) {
                lruCache.put(key, value);
            }
        };

        ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);

        networkImageView.setTag("url");
        networkImageView.setImageUrl(imgUrl, imageLoader);
    }


    private void inflateImage() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String imgUrl = "http://apidev.proclassmates.com:8999/media/avatar/jasmine.png";

        final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(20);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String key) {
                return lruCache.get(key);
            }

            @Override
            public void putBitmap(String key, Bitmap value) {
                lruCache.put(key, value);
            }
        };

        ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);

        ImageLoader.ImageListener imageListener = imageLoader.getImageListener(iv, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background);

        imageLoader.get(imgUrl, imageListener);
    }

    public void getJsonVolley(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String JSONUrl = "http://apidev.proclassmates.com";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSONUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("response:" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("对不起， 有问题！！！");
            }
        });

        requestQueue.add(jsonObjectRequest);
    }


}
