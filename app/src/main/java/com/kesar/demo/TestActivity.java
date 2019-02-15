package com.kesar.demo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.kesar.kpermission.IPermissionRequest;
import com.kesar.kpermission.KPermission;

public class TestActivity extends AppCompatActivity {

    private static final String EXTRA_OPEN_ACTIVITY = "isOpenActivity";

    public static void start(Context context, boolean isOpenActivity) {
        Intent intent = new Intent(context, TestActivity.class);
        intent.putExtra(EXTRA_OPEN_ACTIVITY, isOpenActivity);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        if (getIntent().getBooleanExtra(EXTRA_OPEN_ACTIVITY, true)) {
            setTitle("This is Activity");
            KPermission.of(this).requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new IPermissionRequest.IPermissionCallback() {
                @Override
                public void onResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                    StringBuilder stringBuilder = new StringBuilder("onResult\n");
                    for (int i = 0; i < permissions.length; i++) {
                        stringBuilder.append(permissions[i].replaceFirst("android.permission.", ""))
                                .append(" is ")
                                .append(grantResults[i] == PackageManager.PERMISSION_GRANTED ? "granted" : "denied");
                    }
                    Log.d("KPermission", stringBuilder.toString());
                    Toast.makeText(getApplicationContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            setTitle("This is Fragment");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TestFragment.newInstance())
                    .commitNow();
        }
    }
}
