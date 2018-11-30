package com.bw.movie.mvp.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.HotNews;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.hotHolder>{
    private Context context;
    private List<HotNews.ResultBean> list;
    public HotAdapter(Context context, List<HotNews.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public hotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.hot_item, null);
        hotHolder holder = new hotHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull hotHolder holder, int position) {
        String[] split = list.get(position).getImageUrl().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simp.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class hotHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simp;

        public hotHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.hot_item_simp);
        }
    }
}
