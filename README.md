# KPermission
a lib for android permissions request.
one line code to request android permissions.

## How to use?

```java
Activity that = this; // or fragment
String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
KPermission.of(that).requestPermissions(permissions, new IPermissionRequest.IPermissionCallback() {
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
```