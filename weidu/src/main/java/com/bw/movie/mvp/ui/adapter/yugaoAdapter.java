package com.bw.movie.mvp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.XiangqingNews;
import com.bw.movie.mvp.ui.activity.VideoActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class yugaoAdapter extends RecyclerView.Adapter<yugaoAdapter.YuViewHolder>{
    private Context context;
    private List<XiangqingNews.ResultBean.ShortFilmListBean> list;
    private ArrayList<String> videslist = new ArrayList<>();
    private View inflate;

    public yugaoAdapter(Context context, List<XiangqingNews.ResultBean.ShortFilmListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public YuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.yugao_item, null);
        YuViewHolder holder = new YuViewHolder(this.inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull YuViewHolder holder, int position) {
        holder.simp.setImageURI(list.get(position).getImageUrl());
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = list.get(position).getVideoUrl();

                Intent intent = new Intent(context,VideoActivity.class);

                intent.putExtra("videolist",videoUrl);
                intent.putExtra("position",position);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class YuViewHolder extends RecyclerView.ViewHolder
    {

        private final SimpleDraweeView simp;

        public YuViewHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.yugao_simp);
        }
    }
}
