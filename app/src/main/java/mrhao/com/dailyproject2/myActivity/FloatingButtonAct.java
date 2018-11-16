package mrhao.com.dailyproject2.myActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mrhao.com.dailyproject2.R;
import mrhao.com.dailyproject2.utils.CopyIosDialog;

public class FloatingButtonAct extends AppCompatActivity {

    @BindView(R.id.zdy_dia)
    Button zdyDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_button);
        ButterKnife.bind(this);
        setBaseClickEvent();
    }

    private void setBaseClickEvent() {
        zdyDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CopyIosDialog dialog=new CopyIosDialog(FloatingButtonAct.this,R.style.copyIosDialog,R.layout.copy_ios_dialog);
                dialog.show();
                TextView tvCancel = dialog.findViewById(R.id.cancel);
                TextView tvOk =  dialog.findViewById(R.id.ok);
                TextView title = dialog.findViewById(R.id.dia_title);
                TextView msg = dialog.findViewById(R.id.dia_msg);
                title.setText("开启通知");
                msg.setText("请在手机“设置-通用”中，打开通知权限");
                tvOk.setText("去设置");
                tvOk.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "哦，买搞得儿~", Toast.LENGTH_SHORT).show();
                    }
                });
                tvCancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


            }
        });
    }
}
