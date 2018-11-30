package com.bw.movie.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.YingyuanPaiqi;

import java.util.List;

public class YingyuanPaiqiAdapter extends RecyclerView.Adapter<YingyuanPaiqiAdapter.YingyuanHolder>{
    private Context context;
    private List<YingyuanPaiqi.ResultBean> list;

    public YingyuanPaiqiAdapter(Context context, List<YingyuanPaiqi.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public YingyuanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.yingyuanpaiqi_item, null);
        YingyuanHolder holder = new YingyuanHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull YingyuanHolder holder, int position) {
        holder.name.setText(list.get(position).getScreeningHall()+"");
        holder.start_time.setText(list.get(position).getBeginTime()+"——");
        holder.end_time.setText(list.get(position).getEndTime());
        holder.price.setText("￥"+list.get(position).getSeatsTotal()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class YingyuanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView name;
        private final TextView start_time;
        private final TextView end_time;
        private final TextView price;

        public YingyuanHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.paiqi_name);
            start_time = itemView.findViewById(R.id.start_time);
            end_time = itemView.findViewById(R.id.end_time);
            price = itemView.findViewById(R.id.price);
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
    public void setOnitemclick(YingyuanPaiqiAdapter.onitemclick onitemclick) {
        this.onitemclick = onitemclick;
    }
}
