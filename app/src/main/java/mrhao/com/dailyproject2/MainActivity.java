package mrhao.com.dailyproject2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import mrhao.com.dailyproject2.myActivity.FloatingButtonAct;
import mrhao.com.dailyproject2.myActivity.MaJiaUrlControlAct;
import mrhao.com.dailyproject2.myActivity.PicYaSuoActivity;
import mrhao.com.dailyproject2.myActivity.QSYClandarAct;
import mrhao.com.dailyproject2.myActivity.QSYClanderActivity;
import mrhao.com.dailyproject2.myActivity.SearchActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.btn_picyasuo)
    Button btnPicyasuo;
    @BindView(R.id.btn_majiaurl)
    Button btnMajiaurl;
    @BindView(R.id.btn_calander)
    Button btnCalander;
    @BindView(R.id.btn_floatButton)
    Button btnFloatButton;
    @BindView(R.id.btn_shuxingdonghua)
    Button btnShuxingdonghua;
    @BindView(R.id.btn_calander2)
    Button btnCalander2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setButtonClickEvent();
    }

    private void setButtonClickEvent() {

        //搜索框功能
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });


        //图片压缩
        btnPicyasuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PicYaSuoActivity.class));
            }
        });


        //马甲地址
        btnMajiaurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MaJiaUrlControlAct.class));
            }
        });

        //青松盈签到日历
        btnCalander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QSYClandarAct.class));
            }
        });


        //悬浮按钮
        btnFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FloatingButtonAct.class));
            }
        });


        //Android属性动画
        btnShuxingdonghua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        //青松赢签到日历2
        btnCalander2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,QSYClanderActivity.class));
            }
        });

    }
}
