package com.example.hoomsun.testrxlife;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by hoomsun on 2017/4/17.
 */
public class DialogHelper {
    private KProgressHUD hud;

    public DialogHelper(Context context) {
        hud = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    public void showProgressDlg(String string) {
        if (string != null) {
            hud.setLabel(string);
        }
        hud.show();
    }

    public void stopProgressDlg() {
        if (hud != null)
            hud.dismiss();
    }
}
