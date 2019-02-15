package com.kesar.demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kesar.kpermission.IPermissionRequest;
import com.kesar.kpermission.KPermission;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    public TestFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
                Toast.makeText(getContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
