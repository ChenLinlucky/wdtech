package com.bw.movie.mvp.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.AllYingyuanNews;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.allHolder>{
    private Context context;
    private List<AllYingyuanNews.ResultBean> list;
    public AllAdapter(Context context, List<AllYingyuanNews.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public allHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.tuijian_item, null);
        allHolder holder = new allHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull allHolder holder, int position) {
        holder.text.setText(list.get(position).getAddress());
        holder.km.setText("距离为："+list.get(position).getDistance()+"");
        holder.name.setText(list.get(position).getName());
        holder.simp.setImageURI(Uri.parse(list.get(position).getLogo()));
        holder.guanZhuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==holder.guanZhuu){
                    holder.guanZhuu.setImageResource(R.drawable.selector_hreat);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class allHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView text;
        private final TextView name;
        private final TextView km;
        private final SimpleDraweeView simp;
        private final ImageView guanZhuu;

        public allHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tui_text);
            name = itemView.findViewById(R.id.tuijian_name);
            km = itemView.findViewById(R.id.tuijian_km);
            simp = itemView.findViewById(R.id.tuijian_simp);
            guanZhuu = itemView.findViewById(R.id.img_GuanZhu);
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
    public void setOnitemclick(AllAdapter.onitemclick onitemclick) {
        this.onitemclick = onitemclick;
    }
}
