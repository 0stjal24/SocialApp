package com.example.lukeboyde.socialapp.Utils;

import android.Manifest;

/**
 * Created by lukeboyde on 16/04/2018.
 */

public class Permissions {

    public static final String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    public static final String[] CAMERA_PERMISSION = {
            Manifest.permission.CAMERA
    };

    public static final String[] WRITE_STORAGE_PERMISSION = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final String[] READ_EXTERNAL_PERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
}
