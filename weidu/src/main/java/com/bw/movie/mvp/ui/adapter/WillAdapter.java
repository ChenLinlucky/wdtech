package com.bw.movie.mvp.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.WillNews;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class WillAdapter extends RecyclerView.Adapter<WillAdapter.willHolder>{

    private Context context;
    private List<WillNews.ResultBean> list;
    public WillAdapter(Context context, List<WillNews.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public willHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.hot_item, null);
        willHolder holder = new willHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull willHolder holder, int position) {
        String[] split = list.get(position).getImageUrl().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simp.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class willHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simp;

        public willHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.hot_item_simp);
        }
    }
}
