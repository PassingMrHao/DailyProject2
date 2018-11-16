package mrhao.com.dailyproject2.utils;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * AlertDialog.Builder封装工具类
 * 郝玉龙
 * 2018/08/02
 * 用法：
 * 1.BaseDialogUtil.normalDialog(context,title,Message，listener)；//普通两个按钮的AlertDiaolog
 * 2.BaseDialogUtil.listDialog(context, list_item, title, listener);//List列表AlertDiaolog
 * ... etc.
 */

public class BaseDialogUtil {
    static int choose;


    /**
     * 普通两个按钮的AlertDiaolog
     *
     * @param context
     * @param title    Diaolog标题
     * @param Message  Diaolog信息
     * @param listener 方法回调接口
     * @return
     */

    public static AlertDialog.Builder normalDialog(Context context, String title, String Message, final ShowDialogListener listener) {
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(context);
        normalDialog.setTitle(title);
        normalDialog.setMessage(Message);
        normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.Positive();
            }
        });


        normalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.Negative();
            }
        });
        normalDialog.show();
        return normalDialog;
    }


    /**
     * List列表AlertDiaolog
     *
     * @param context
     * @return
     */
    public static AlertDialog.Builder listDialog(Context context, String[] items, String title, final listDialogListener listener) {

        AlertDialog.Builder listDialog = new AlertDialog.Builder(context);
        listDialog.setTitle(title);
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.ItemClick(i);
            }
        });
        listDialog.show();
        return listDialog;
    }


    /**
     * 单项选择AlertDiaolog
     *
     * @param context
     * @param items
     * @param title
     * @param listener
     * @return
     */

    public static AlertDialog.Builder OneChooseDialog(final Context context, final String[] items, String title, final listDialogListener listener) {
        choose = -1;
        AlertDialog.Builder OneDialog = new AlertDialog.Builder(context);
        OneDialog.setTitle(title);
        OneDialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose = i;
            }
        });

        OneDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (choose == -1) {
                    listener.ItemClick(0);
                } else {
                    listener.ItemClick(choose);
                }
            }
        });

        OneDialog.show();
        return OneDialog;
    }

    /**
     * 郝玉龙
     *
     * @param context
     * @param title        ProgressDialog标题
     * @param MAX_PROGRESS 最大进度
     * @param Per_Second   加载执行周期，值越小，加载速度越快
     * @param listener     方法回调接口
     * @return
     */
    public static ProgressDialog ProgressDialog(final Context context, String title, final int MAX_PROGRESS, final int Per_Second, final ProgressDialogListener listener) {
        final int i = 0;
        final ProgressDialog proDialog = new ProgressDialog(context);
        proDialog.setProgress(0);
        proDialog.setTitle(title);
        proDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        proDialog.setMax(MAX_PROGRESS);
        proDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress < MAX_PROGRESS) {
                    try {
                        Thread.sleep(Per_Second);
                        progress++;
                        proDialog.setProgress(progress);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                proDialog.cancel();
                listener.ProgressFinish();


            }
        }).start();
        return proDialog;
    }


    /* 等待弹窗 WaitingDialog 具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
    public static ProgressDialog waitingDialog(Context context, String title, String msg, waitingDialogListener listener) {
        ProgressDialog waitingDialog = new ProgressDialog(context);
        waitingDialog.setTitle(title);
        waitingDialog.setMessage(msg);
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(true);
        waitingDialog.show();
        listener.waitDiaCancle(waitingDialog);
        return waitingDialog;
    }


    //normalDialog方法回调接口
    public interface ShowDialogListener {
        void Positive();

        void Negative();
    }

    //listDialog方法回调接口 , OneChooseDialog方法回调接口
    public interface listDialogListener {
        void ItemClick(int i);
    }

    //ProgressDialog方法回调接口
    public interface ProgressDialogListener {
        //用法：须在调用接口的Activity/Fragment中的UI线程中执行需要的操作
        void ProgressFinish();

    }

    //waitingDialog方法回调接口
    public interface waitingDialogListener {

        void waitDiaCancle(ProgressDialog waitDialog);

    }

}


