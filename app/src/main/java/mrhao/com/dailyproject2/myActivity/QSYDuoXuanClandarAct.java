package mrhao.com.dailyproject2.myActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.othershe.calendarview.weiget.CalendarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mrhao.com.dailyproject2.R;

public class QSYDuoXuanClandarAct extends AppCompatActivity {

    @BindView(R.id.calendar)
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qsyduo_xuan_clandar);
        ButterKnife.bind(this);
        setBaseDate();
    }

    private void setBaseDate() {
        List<String> list = new ArrayList<>();
        list.add("2017.11.11");
        list.add("2017.11.12");
        list.add("2017.12.22");
        list.add("2017.12.25");
        calendarView
                .setStartEndDate("2017.1", "2019.12")
                .setDisableStartEndDate("2017.10.7", "2019.10.7")
                .setInitDate("2017.11")
                .setMultiDate(list)
                .init();
    }
}
