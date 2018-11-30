package com.bw.movie.mvp.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.LookingNews;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class LookingAdapter extends RecyclerView.Adapter<LookingAdapter.lookingHolder>{

    private Context context;
    private List<LookingNews.ResultBean> list;
    public LookingAdapter(Context context, List<LookingNews.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public lookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.hot_item, null);
        lookingHolder holder = new lookingHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull lookingHolder holder, int position) {
        String[] split = list.get(position).getImageUrl().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simp.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class lookingHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simp;

        public lookingHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.hot_item_simp);
        }
    }
}
