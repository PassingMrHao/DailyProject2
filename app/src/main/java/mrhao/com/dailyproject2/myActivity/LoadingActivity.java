package mrhao.com.dailyproject2.myActivity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mrhao.com.dailyproject2.MainActivity;
import mrhao.com.dailyproject2.R;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
            }
        }, 2500);

    }
}
