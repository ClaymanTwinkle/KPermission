package com.kesar.kpermission;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


/**
 * PermissionHostFragment
 *
 * @author andy <br/>
 * support permission request and callback
 * create time: 2019/2/15 15:55
 */
public class PermissionHostFragment extends Fragment implements IPermissionRequest {
    //private static final String TAG = "KPermission";
    private static final String HOLDER_TAG = "com.kesar.kpermission.PermissionHostFragment";

    private PermissionProcessor processor = new PermissionProcessor();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        processor.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        processor.onDestroy();
    }

    /**
     * @hide
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    @Override
    public void requestPermissions(@NonNull String[] permissions, IPermissionCallback callback) {
        processor.requestPermissions(this, permissions, callback);
    }

    /**
     * @hide
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public static PermissionHostFragment holderFragmentFor(FragmentActivity activity) {
        return holderFragmentFor(activity.getSupportFragmentManager());
    }

    /**
     * @hide
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public static PermissionHostFragment holderFragmentFor(Fragment fragment) {
        return holderFragmentFor(fragment.getChildFragmentManager());
    }

    private static PermissionHostFragment holderFragmentFor(FragmentManager fm) {
        PermissionHostFragment holder = findHolderFragment(fm);
        if (holder != null) {
            return holder;
        }
        holder = createHolderFragment(fm);
        return holder;
    }

    private static PermissionHostFragment findHolderFragment(FragmentManager manager) {
        if (manager.isDestroyed()) {
            throw new IllegalStateException("Can't access ViewModels from onDestroy");
        }

        Fragment fragmentByTag = manager.findFragmentByTag(HOLDER_TAG);
        if (fragmentByTag != null && !(fragmentByTag instanceof PermissionHostFragment)) {
            throw new IllegalStateException("Unexpected fragment instance was returned by HOLDER_TAG");
        }
        return (PermissionHostFragment) fragmentByTag;
    }

    private static PermissionHostFragment createHolderFragment(FragmentManager fragmentManager) {
        PermissionHostFragment holder = new PermissionHostFragment();
        fragmentManager.beginTransaction().add(holder, HOLDER_TAG).commitNowAllowingStateLoss(); // need quickly
        return holder;
    }

}
