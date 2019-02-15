package com.kesar.kpermission;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * KPermission
 *
 * @author andy <br/>
 * create time: 2019/2/15 15:56
 */
public class KPermission {
    public static IPermissionRequest of(FragmentActivity activity) {
        return PermissionHostFragment.holderFragmentFor(activity);
    }

    public static IPermissionRequest of(Fragment fragment) {
        return PermissionHostFragment.holderFragmentFor(fragment);
    }
}
