package com.bw.movie.mvp.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class juzhaoAdapter extends RecyclerView.Adapter<juzhaoAdapter.xiangHolder>{
    private Context context;
    private List<String> list;
    public juzhaoAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public xiangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.juzhao_item, null);
        xiangHolder holder = new xiangHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull xiangHolder holder, int position) {
       // String[] split = list.get(position).getImageUrl().split("\\|");
        Uri uri = Uri.parse(list.get(position));
        holder.simp.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class xiangHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simp;

        public xiangHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.juzhao_simp);
        }
    }
}
