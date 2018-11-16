package mrhao.com.dailyproject2.utils;

import android.os.Handler;
import android.os.SystemClock;

/**
 * 延时执行的工具类
 * @author 郝玉龙
 *
 */
public abstract class DelayedTask {
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            onPostExecute();
        }
    };

    public abstract void onPostExecute();

    ;
    /**
     * 延时执行
     * @param delayTime 毫秒值
     */
    public void delayRun(final long delayTime){
        new Thread(){
            public void run() {
                SystemClock.sleep(delayTime);
                handler.sendEmptyMessage(0);
            };
        }.start();
    }
}

