package com.bw.movie.mvp.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.HotNews;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HotItemAdapter extends RecyclerView.Adapter<HotItemAdapter.hotHolder> {
    private Context context;
    private List<HotNews.ResultBean> list;

    public HotItemAdapter(Context context, List<HotNews.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public hotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.liebiao_item, null);
        hotHolder hold = new hotHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(@NonNull hotHolder holder, int position) {
        String[] split = list.get(position).getImageUrl().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simp.setImageURI(uri);
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(30f);
        holder.simp.getHierarchy().setRoundingParams(roundingParams);
        holder.title.setText(list.get(position).getName());
        holder.jianjie.setText("   简介："+list.get(position).getSummary());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class hotHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final SimpleDraweeView simp;
        private final TextView title;
        private final TextView jianjie;

        public hotHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.liebiao_simp);
            title = itemView.findViewById(R.id.liebiao_title);
            jianjie = itemView.findViewById(R.id.liebiao_jianjie);
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
    public void setOnitemclick(HotItemAdapter.onitemclick onitemclick) {
        this.onitemclick = onitemclick;
    }
}
