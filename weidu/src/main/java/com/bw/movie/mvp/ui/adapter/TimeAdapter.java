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

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.timeHolder>{
    private Context context;
    private List<YingyuanPaiqi.ResultBean> list;
    public TimeAdapter(Context context, List<YingyuanPaiqi.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public timeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.time_item, null);
        timeHolder holder = new timeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull timeHolder holder, int position) {
        holder.startTime.setText(list.get(position).getBeginTime());
        holder.endTime.setText("   ——   "+list.get(position).getEndTime());
        holder.seat.setText(list.get(position).getScreeningHall());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class timeHolder extends RecyclerView.ViewHolder{

        private final TextView startTime;
        private final TextView endTime;
        private final TextView seat;

        public timeHolder(View itemView) {
            super(itemView);
            startTime = itemView.findViewById(R.id.start_time);
            endTime = itemView.findViewById(R.id.end_time);
            seat = itemView.findViewById(R.id.seat);
        }
    }
}
