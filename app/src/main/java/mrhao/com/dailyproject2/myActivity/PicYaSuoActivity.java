package mrhao.com.dailyproject2.myActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nanchen.compresshelper.CompressHelper;
import com.nanchen.compresshelper.FileUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import mrhao.com.dailyproject2.R;
import mrhao.com.dailyproject2.utils.ImageCompressionUtil;

import static mrhao.com.dailyproject2.utils.ImageCompressionUtil.compressImage;


public class PicYaSuoActivity extends AppCompatActivity {

    @BindView(R.id.im_yuanshi)
    ImageView imYuanshi;
    @BindView(R.id.daxiao_yuanshi)
    TextView daxiaoYuanshi;
    @BindView(R.id.im_yasuo)
    ImageView imYasuo;
    @BindView(R.id.daxiao_yasuo)
    TextView daxiaoYasuo;
    String datu = "http://inspiration.chanyouji.cn/UserActivityContent/364280/f4b979b10f4d2a9939a816e551271815.jpg";
    Bitmap bitmap2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_ya_suo);
        ButterKnife.bind(this);
        YaSuoPic();
    }

    private void YaSuoPic() {
        Glide.with(this).load(datu).into(imYuanshi);

        new Thread() {
            @Override
            public void run() {
                super.run();
                bitmap2 = ImageCompressionUtil.getbitmap(datu);
                PicYaSuoActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 图片按比例大小压缩
                        imYasuo.setImageBitmap(ImageCompressionUtil.comp(bitmap2,800f,480f));
                    }
                });
            }
        }.start();


    }


}
