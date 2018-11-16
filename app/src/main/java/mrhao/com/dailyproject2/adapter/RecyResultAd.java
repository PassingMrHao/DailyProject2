package mrhao.com.dailyproject2.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mrhao.com.dailyproject2.R;
import mrhao.com.dailyproject2.bean.SearchDateBean;

public class RecyResultAd extends RecyclerView.Adapter<RecyResultAd.MyHolder> {

    Context context;
    SearchDateBean searchbean;

    public RecyResultAd(Context context, SearchDateBean searchbean) {
        this.context = context;
        this.searchbean = searchbean;

    }

    @Override
    public RecyResultAd.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vvvv = LayoutInflater.from(context).inflate(R.layout.recy_searchresult, parent, false);
        return new MyHolder(vvvv);
    }

    @Override
    public void onBindViewHolder(RecyResultAd.MyHolder h, int i) {
        h.tv.setText(searchbean.getList().get(i));
        h.desc.setText("\u3000\u3000" + searchbean.getDesclist().get(i));
        Glide.with(context).load(searchbean.getImglist().get(i)).into(h.im);

    }

    @Override
    public int getItemCount() {
        return searchbean.getList().size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tv, desc;
        ImageView im;

        public MyHolder(View v) {
            super(v);
            tv = v.findViewById(R.id.tb_searchresult);
            desc = v.findViewById(R.id.tb_desc);
            im = v.findViewById(R.id.tb_im);
        }
    }
}