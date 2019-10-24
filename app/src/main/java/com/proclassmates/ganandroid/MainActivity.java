package com.proclassmates.ganandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMap(View view) {
        String addressString = "1600 Amphitheatre Parkway, CA";

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo")
                .path("0,0")
                .appendQueryParameter("q", addressString);
        Uri addressUri = builder.build();

        Toast.makeText(this, addressUri.toString(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(addressUri);

        startActivity(intent);

    }

    public void openWebpage(View view) {
        Uri uri = Uri.parse("http://proclassmates.com");

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(intent);
    }

    public void shareText(View view) {
        String textThatYouWantToShare =
                "Sharing the coolest thing I've learned so far. You should " +
                        "check out Udacity and Google's Android Nanodegree!";
        String mimeType = "text/plain";
        String title = "learn how to share!";
        ShareCompat.IntentBuilder.from(this).setChooserTitle(title).setType(mimeType).setText(textThatYouWantToShare).startChooser();
    }
}
