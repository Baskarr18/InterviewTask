package com.example.root.jsonparsingwithrealm.utilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.Window;
import android.widget.ProgressBar;

import com.example.root.jsonparsingwithrealm.R;

/**
 * Created by root on 6/8/17.
 */

public class SystemProgressDialog {

    private ProgressDialog progress;
    private String TAG = SystemProgressDialog.this.getClass().getSimpleName();


    public void dismissDialog() {
        try {
            if ((progress != null) && progress.isShowing())
                progress.dismiss();
            progress = null;
        } catch (Exception e) {
            // Crashlytics.logException(e);
        }
    }

    public void showDialog(Context context) {
        try {

            if ((progress != null) && progress.isShowing())
                progress.dismiss();
            progress = null;

            //if (context == null)
            //context = HomeSlideMenuActivity.activity;

            progress = new ProgressDialog(context);
            progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

            if (!((Activity) context).isFinishing()) {
                progress.show();
            }

            ProgressBar spinner = new ProgressBar(context);
            spinner.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(context, R.color.progress_bg), PorterDuff.Mode.SRC_IN);
            progress.setContentView(spinner);
            // progress.setMessage("Loading...");
            progress.setCancelable(false);
            progress.setCanceledOnTouchOutside(false);
            // progress.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } catch (Exception e) {
        }
    }

}
