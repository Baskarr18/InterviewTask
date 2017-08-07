package com.example.root.jsonparsingwithrealm.utilities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by root on 6/8/17.
 */

public class Utility {

    private static Utility mInstance = null;

    public static Utility getInstance() {
        if (mInstance == null) {
            mInstance = new Utility();
        }
        return mInstance;
    }


    public void startInstalledAppDetailsActivity(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
