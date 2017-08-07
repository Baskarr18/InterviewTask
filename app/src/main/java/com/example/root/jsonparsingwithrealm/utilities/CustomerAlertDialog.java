package com.example.root.jsonparsingwithrealm.utilities;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.root.jsonparsingwithrealm.R;

/**
 * Created by root on 6/8/17.
 */

public class CustomerAlertDialog {


    private static CustomerAlertDialog ourInstance = new CustomerAlertDialog();

    public static CustomerAlertDialog getInstance() {
        return ourInstance;
    }

    private CustomerAlertDialog() {
    }

    public void showMessage(Context context, String title, String message, String positive_text, String negative_text, final int request_code, final AlertDialogListener alertDialogListener) {

        if (negative_text == null)
            showMessage(context, title, message, positive_text, request_code, alertDialogListener);
        else
            new MaterialDialog.Builder(context)
                    .title(R.string.app_name)
                    .content(message)
                    .cancelable(false)
                    .canceledOnTouchOutside(false)
                    .positiveText(positive_text)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            if (alertDialogListener != null) {
                                alertDialogListener.onPositiveClicked(request_code);
                            }
                        }
                    })
                    .negativeText(negative_text)
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            if (alertDialogListener != null) {
                                alertDialogListener.onNegativeClicked(request_code);
                            }

                        }
                    })
                    .show();
    }

    public void showMessage(Context context, String title, String message, String positive_text, final int request_code, final AlertDialogListener alertDialogListener) {

        new MaterialDialog.Builder(context)
                .title(R.string.app_name)
                .content(message)
                .cancelable(false)
                .canceledOnTouchOutside(false)
                .positiveText(positive_text)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (alertDialogListener != null) {
                            alertDialogListener.onPositiveClicked(request_code);
                        }
                    }
                })
                .show();
    }

    public interface AlertDialogListener {
        void onPositiveClicked(int request_code);

        void onNegativeClicked(int request_code);
    }

}
