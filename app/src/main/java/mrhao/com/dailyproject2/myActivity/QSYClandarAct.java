package mrhao.com.dailyproject2.myActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.othershe.calendarview.weiget.CalendarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mrhao.com.dailyproject2.R;

public class QSYClandarAct extends AppCompatActivity {

    @BindView(R.id.calander)
    CalendarView calander;
    @BindView(R.id.lin_qiandao)
    LinearLayout linQiandao;
    @BindView(R.id.tv_jinri)
    TextView tvJinri;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.tv_qiandao)
    TextView tvQiandao;
    @BindView(R.id.calander2)
    CalendarView calander2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qsyclandar);
        ButterKnife.bind(this);
        InitCalendarDate();

    }

    private void InitCalendarDate() {
        final List<String> list = new ArrayList<>();
        list.add("2018.11.6");
        list.add("2018.11.7");
        list.add("2018.11.1");
        list.add("2018.11.3");

        calander.setStartEndDate("2017.1", "2019.12")
                .setDisableStartEndDate("2017.10.7", "2019.10.7")
                .setInitDate("2018.11")
                .setMultiDate(list)
                .init();


        linQiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> list2 = new ArrayList<>();
                list2.add("2018.11.6");
                list2.add("2018.11.7");
                list2.add("2018.11.1");
                list2.add("2018.11.3");
                list2.add("2018.11.8");
                tvJinri.setText("今日");
                tvJifen.setText("已签到");
                tvQiandao.setText("累计签到：5天");
                calander2.setStartEndDate("2017.1", "2019.12")
                        .setDisableStartEndDate("2017.10.7", "2019.10.7")
                        .setInitDate("2018.11")
                        .setMultiDate(list2)
                        .init();
                calander.setVisibility(View.INVISIBLE);
                calander2.setVisibility(View.VISIBLE);
                Toast.makeText(QSYClandarAct.this, "签到成功", Toast.LENGTH_SHORT).show();
                linQiandao.setClickable(false);


            }
        });


    }
}
