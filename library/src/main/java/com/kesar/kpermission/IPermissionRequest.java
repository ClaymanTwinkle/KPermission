package com.kesar.kpermission;

import android.support.annotation.NonNull;

/**
 * IPermissionRequest
 *
 * @author andy <br/>
 * for request permissions
 * create time: 2019/2/15 15:59
 */
public interface IPermissionRequest {
    void requestPermissions(@NonNull String[] permissions, IPermissionCallback callback);

    interface IPermissionCallback{
        void onResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
    }
}
