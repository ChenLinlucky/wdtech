package com.bw.movie.mvp.ui;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.MovieXiangNews;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class XiangcengdieAdapter extends RecyclerView.Adapter<XiangcengdieAdapter.cengHolder>{
    private Context context;
    private List<MovieXiangNews.ResultBean> list;
    public XiangcengdieAdapter(Context context, List<MovieXiangNews.ResultBean> list) {
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
        String[] split = list.get(position).getImageUrl().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simp.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class cengHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final SimpleDraweeView simp;

        public cengHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.cengdie_simp);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            onitemclick.onclick(position);
        }
    }

    private onitemclick onitemclick;
    public interface onitemclick{
        void onclick(int position);
    }
    public void setOnitemclick(XiangcengdieAdapter.onitemclick onitemclick) {
        this.onitemclick = onitemclick;
    }
}
