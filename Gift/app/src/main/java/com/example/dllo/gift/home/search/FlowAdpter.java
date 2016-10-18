package com.example.dllo.gift.home.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dllo.gift.R;

/**
 * Created by dllo on 16/9/29.
 */
public class FlowAdpter extends RecyclerView.Adapter<FlowAdpter.MyViewHodler> {

    private Context context;
    private HotBean hotBeen;
    private OnRecyclerItemClick onRecyclerItemClick;

    public void setOnRecyclerItemClick(OnRecyclerItemClick onRecyclerItemClick) {
        this.onRecyclerItemClick = onRecyclerItemClick;
    }

    public void setHotBeen(HotBean hotBeen) {
        this.hotBeen = hotBeen;
    }

    public FlowAdpter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rl_hot_search, parent, false);
        MyViewHodler myViewHodler = new MyViewHodler(view);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(final MyViewHodler holder, final int position) {
        holder.hotWord.setText(hotBeen.getData().getHot_words().get(position));
        holder.hotWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerItemClick.click(position,holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotBeen.getData().getHot_words().size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {

        private final TextView hotWord;

        public MyViewHodler(View itemView) {
            super(itemView);
            hotWord = (TextView) itemView.findViewById(R.id.tv_hot_search);
        }
    }
}
