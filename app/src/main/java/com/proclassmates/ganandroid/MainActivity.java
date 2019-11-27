package com.proclassmates.ganandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv_select_photo;
    private DialogView mPhotoDialog;
    private TextView tv_camera;
    private TextView tv_ablum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSelectPhotoDialog();
        tv_select_photo = findViewById(R.id.select_photo);
        tv_select_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectPhotoDialog();
            }
        });
    }

    private void initSelectPhotoDialog() {
        mPhotoDialog = DialogManager.getInstance().initView(this, R.layout.dialog_select_photo, Gravity.BOTTOM);

        tv_camera = (TextView) mPhotoDialog.findViewById(R.id.tv_camera);
        tv_ablum = (TextView) mPhotoDialog.findViewById(R.id.tv_ablum);

        tv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManager.getInstance().hide(mPhotoDialog);
//                FileHelper.getInstance().toCamera(MainActivity.this);
                Toast.makeText(MainActivity.this, "camera", Toast.LENGTH_SHORT).show();
            }
        });
        tv_ablum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManager.getInstance().hide(mPhotoDialog);
//                FileHelper.getInstance().toAlbum(MainActivity.this);
                Toast.makeText(MainActivity.this, "ablum", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSelectPhotoDialog() {
        DialogManager.getInstance().show(mPhotoDialog);
    }
}
