package com.proclassmates.ganandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tv_select_photo;
    private DialogView mPhotoDialog;
    private TextView tv_camera;
    private TextView tv_ablum;
    private File uploadPhotoFile;
    private ImageView iv_user_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        request(new OnPermissionsResult() {
            @Override
            public void OnSuccess() {

            }

            @Override
            public void OnFail(List<String> noPermissions) {
                Toast.makeText(MainActivity.this, ("noPermissions:" + noPermissions.toString()), Toast.LENGTH_SHORT).show();
            }
        });
        initSelectPhotoDialog();
        iv_user_photo = findViewById(R.id.iv_user_photo);
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
                FileHelper.getInstance().toCamera(MainActivity.this);
            }
        });
        tv_ablum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManager.getInstance().hide(mPhotoDialog);
                FileHelper.getInstance().toAlbum(MainActivity.this);
            }
        });
    }

    private void showSelectPhotoDialog() {
        DialogManager.getInstance().show(mPhotoDialog);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == FileHelper.CAMEAR_REQUEST_CODE) {
                FileHelper.getInstance().startPhotoZoom(this, FileHelper.getInstance().getTempFile());
            } else if (requestCode == FileHelper.ALBUM_REQUEST_CODE) {
                Uri uri = data.getData();
                if (uri != null) {
                    String path = FileHelper.getInstance().getRealPathFromURI(this, uri);
                    if (!TextUtils.isEmpty(path)) {
                        uploadPhotoFile = new File(path);
                        FileHelper.getInstance().startPhotoZoom(this, uploadPhotoFile);
                    }
                }
            } else if (requestCode == FileHelper.CAMERA_CROP_RESULT) {
                uploadPhotoFile = new File(FileHelper.getInstance().getCropPath());
                Log.d("MainActivity", ("uploadPhotoFile:" + uploadPhotoFile.getPath()));
            }
            if (uploadPhotoFile != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(uploadPhotoFile.getPath());
                iv_user_photo.setImageBitmap(bitmap);
            }
        }
    }

    //申请运行时权限的Code
    private static final int PERMISSION_REQUEST_CODE = 1000;
    //申请窗口权限的Code
    public static final int PERMISSION_WINDOW_REQUEST_CODE = 1001;

    //申明所需权限
    private String[] mStrPermission = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_FINE_LOCATION

    };

    //保存没有同意的权限
    private List<String> mPerList = new ArrayList<>();
    //保存没有同意的失败权限
    private List<String> mPerNoList = new ArrayList<>();

    private OnPermissionsResult permissionsResult;

    /**
     * 一个方法请求权限
     *
     * @param permissionsResult
     */
    protected void request(OnPermissionsResult permissionsResult) {
        if (!checkPermissionsAll()) {
            requestPermissionAll(permissionsResult);
        }
    }

    /**
     * 判断单个权限
     *
     * @param permissions
     * @return
     */
    protected boolean checkPermissions(String permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int check = checkSelfPermission(permissions);
            return check == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    /**
     * 判断是否需要申请权限
     *
     * @return
     */
    protected boolean checkPermissionsAll() {
        mPerList.clear();
        for (int i = 0; i < mStrPermission.length; i++) {
            boolean check = checkPermissions(mStrPermission[i]);
            //如果不同意则请求
            if (!check) {
                mPerList.add(mStrPermission[i]);
            }
        }
        return mPerList.size() > 0 ? false : true;
    }

    /**
     * 请求权限
     *
     * @param mPermissions
     */
    protected void requestPermission(String[] mPermissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(mPermissions, PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * 申请所有权限
     *
     * @param permissionsResult
     */
    protected void requestPermissionAll(OnPermissionsResult permissionsResult) {
        this.permissionsResult = permissionsResult;
        requestPermission((String[]) mPerList.toArray(new String[mPerList.size()]));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        mPerNoList.clear();
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        //你有失败的权限
                        mPerNoList.add(permissions[i]);
                    }
                }
                if (permissionsResult != null) {
                    if (mPerNoList.size() == 0) {
                        permissionsResult.OnSuccess();
                    } else {
                        permissionsResult.OnFail(mPerNoList);
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected interface OnPermissionsResult {
        void OnSuccess();

        void OnFail(List<String> noPermissions);
    }


}
