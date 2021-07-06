package com.grain.utils.utils.Permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;

import com.grain.utils.InitUtilsModule;
import com.grain.utils.hint.toast;

import androidx.core.app.ActivityCompat;

/**
 * @anthor
 * @funcation 动态申请权限 在Manifest里先添加需要申请的权限
 * @date 2019/8/9
 */
public class ApplyPermission {

    public ApplyPermission(Context context) {
        ActivityCompat.requestPermissions((Activity) context, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.REQUEST_INSTALL_PACKAGES,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.RECEIVE_BOOT_COMPLETED,
                Manifest.permission.REORDER_TASKS,
        }, 1);
    }

    /**
     * 权限判断
     * @param permName
     * @return
     */
    public static boolean permissionJudge(String permName) {
        PackageManager pm = InitUtilsModule.getActivity().getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED == pm.checkPermission(permName, "packageName"));
        return permission;
    }
}

