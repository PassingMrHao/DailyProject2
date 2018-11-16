package mrhao.com.dailyproject2.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;


public class CopyIosDialog extends Dialog {
    private Context context;
    private int resId;

    public CopyIosDialog(Context context, int resLayout) {
        this(context, 0, 0);
    }

    public CopyIosDialog(Context context, int themeResId, int resLayout) {
        super(context, themeResId);
        this.context = context;
        this.resId = resLayout;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(resId);
    }

}
