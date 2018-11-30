package com.bw.movie.mvp.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.TuiYingyuanNews;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.tuiHolder>{
    private Context context;
    private List<TuiYingyuanNews.ResultBean.NearbyCinemaListBean> list;
    public MovieAdapter(Context context, List<TuiYingyuanNews.ResultBean.NearbyCinemaListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public tuiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.tuijian_item, null);
        tuiHolder holder = new tuiHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull tuiHolder holder, int position) {
        holder.text.setText(list.get(position).getAddress());
        holder.km.setText("距离为："+list.get(position).getDistance()+" Km");
        holder.name.setText(list.get(position).getName());
        holder.simp.setImageURI(Uri.parse(list.get(position).getLogo()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class tuiHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView text;
        private final TextView name;
        private final TextView km;
        private final SimpleDraweeView simp;

        public tuiHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tui_text);
            name = itemView.findViewById(R.id.tuijian_name);
            km = itemView.findViewById(R.id.tuijian_km);
            simp = itemView.findViewById(R.id.tuijian_simp);
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
    public void setOnitemclick(MovieAdapter.onitemclick onitemclick) {
        this.onitemclick = onitemclick;
    }
}
