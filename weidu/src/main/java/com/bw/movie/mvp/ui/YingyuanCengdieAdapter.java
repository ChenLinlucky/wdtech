package com.bw.movie.mvp.ui;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.YingyuanXiangNews;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class YingyuanCengdieAdapter extends RecyclerView.Adapter<YingyuanCengdieAdapter.cengHolder>{
    private Context context;
    private List<YingyuanXiangNews.ResultBean> list;
    public YingyuanCengdieAdapter(Context context, List<YingyuanXiangNews.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public cengHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.cengdie_item, null);
        cengHolder holder = new cengHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull cengHolder holder, int position) {
        String[] split = list.get(position).getLogo().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simp.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class cengHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simp;

        public cengHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.cengdie_simp);
        }
    }
}
