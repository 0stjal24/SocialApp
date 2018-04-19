package com.example.lukeboyde.socialapp.Utils;

import android.os.Environment;

/**
 * Created by lukeboyde on 17/04/2018.
 */

public class FilePaths {

    public String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();
    public String PICTURES = ROOT_DIR + "/Pictures";
    public String CAMERA = ROOT_DIR + "/DCIM/camera";

    public String FIREBASE_IMAGE_STORAGE = "pictures/users";

}
