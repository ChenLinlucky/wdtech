package com.bw.movie.mvp.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.YingYuanPinglunNews;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class XiangPinglunAdapter extends RecyclerView.Adapter<XiangPinglunAdapter.yingpingHolder>{
    private Context context;
    private List<YingYuanPinglunNews.ResultBean> list;
    public XiangPinglunAdapter(Context context, List<YingYuanPinglunNews.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public yingpingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.yingping_item, null);
        yingpingHolder holder = new yingpingHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull yingpingHolder holder, int position) {
        holder.pingjia.setText("        "+list.get(position).getCommentContent());
        holder.simp.setImageURI(Uri.parse(list.get(position).getCommentHeadPic()));
        holder.name.setText(list.get(position).getCommentUserName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
       class yingpingHolder extends RecyclerView.ViewHolder{

       private final TextView pingjia;
       private final SimpleDraweeView simp;
       private final TextView name;

       public yingpingHolder(View itemView) {
           super(itemView);
           pingjia = itemView.findViewById(R.id.text_pingjia);
           simp = itemView.findViewById(R.id.pingjia_usimp);
           name = itemView.findViewById(R.id.pingjia_uname);
       }
   }


}
