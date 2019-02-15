package com.kesar.kpermission;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;

/**
 * PermissionProcessor
 *
 * @author andy <br/>
 * create time: 2019/2/15 18:48
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class PermissionProcessor {

    private static final int REQUEST_CODE = 1555; // lucky num

    private IPermissionRequest.IPermissionCallback callback;

    void requestPermissions(@NonNull Fragment fragment, @NonNull String[] permissions, IPermissionRequest.IPermissionCallback callback) {
        this.callback = callback;
        fragment.requestPermissions(permissions, REQUEST_CODE);
    }

    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (callback != null) {
                callback.onResult(requestCode, permissions, grantResults);
                callback = null;
            }
        }
    }

    void onDestroy() {
        callback = null;
    }
}
