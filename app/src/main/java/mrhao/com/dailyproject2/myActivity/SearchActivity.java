package mrhao.com.dailyproject2.myActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import mrhao.com.dailyproject2.R;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.linlay_search)
    LinearLayout linlaySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        linlaySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, MacaoSearchActivity.class));
            }
        });
    }
}
