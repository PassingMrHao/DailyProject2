package mrhao.com.dailyproject2.myActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mrhao.com.dailyproject2.R;
import mrhao.com.dailyproject2.utils.MySearchViewUtils.ICallBack;
import mrhao.com.dailyproject2.utils.MySearchViewUtils.MySearchView;
import mrhao.com.dailyproject2.utils.MySearchViewUtils.bCallBack;

public class MacaoSearchActivity extends AppCompatActivity {

    @BindView(R.id.mysearch_view)
    MySearchView mysearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macao_search);
        ButterKnife.bind(this);
        setSearchEvent();
    }


    private void setSearchEvent() {
        mysearchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {

            }
        });
    }
}
