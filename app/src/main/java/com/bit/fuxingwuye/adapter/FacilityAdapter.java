package com.bit.fuxingwuye.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bit.fuxingwuye.R;
import com.bit.fuxingwuye.bean.FacilityBean;

import java.util.List;

/**
 * Created by Dell on 2017/7/5.
 */

public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.ViewHolder> implements View.OnClickListener{

    private List<FacilityBean> datas;

    public FacilityAdapter(List<FacilityBean> datas) {
        this.datas = datas;
    }

    private OnItemClickListener mOnItemClickListener = null;

    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item ,parent,false);
            ViewHolder vh = new ViewHolder(view);
            view.setOnClickListener(this);
            return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item_iv.setImageResource(R.mipmap.icon_gate);
        holder.service_name.setText(datas.get(position).getDoorName());
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return null==datas?0:datas.size();
    }


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView service_name;
        ImageView item_iv;
        public ViewHolder(View view){
            super(view);
            service_name = (TextView) view.findViewById(R.id.service_name);
            item_iv = (ImageView)view.findViewById(R.id.item_iv);
        }
    }
}
