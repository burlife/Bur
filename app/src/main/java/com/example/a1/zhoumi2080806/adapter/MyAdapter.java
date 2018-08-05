package com.example.a1.zhoumi2080806.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a1.zhoumi2080806.bean.NewsBean;
import com.example.a1.zhoumi2080806.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 1 on 2018/8/6.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<NewsBean.ResultsBean> list;
    private View view;

    public MyAdapter(Context context, List<NewsBean.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
                 holder.iSimple.setImageURI(list.get(position).getUrl());
                 holder.oneTitle.setText(list.get(position).getWho());
                 holder.oneDate.setText(list.get(position).getCreatedAt());
                 holder.itemView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         itemClickListener.onClickListener(view,position);
                     }
                 });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.i_simple)
        SimpleDraweeView iSimple;
        @BindView(R.id.one_title)
        TextView oneTitle;
        @BindView(R.id.one_date)
        TextView oneDate;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener{
        void onClickListener(View view,int postion);
    }
}
