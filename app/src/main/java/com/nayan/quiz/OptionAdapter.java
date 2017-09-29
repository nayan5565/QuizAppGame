package com.nayan.quiz;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Nayan on 9/29/2017.
 */
public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<MOption> mItems;
    private MOption mItem;

    View view;


    public OptionAdapter(Context context) {
        this.context = context;
        mItems = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<MOption> mItems) {
        this.mItems = mItems;
        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i).getTag() == 2) {
                mItems.get(i).setTag(0);
            }

        }
//        Collections.shuffle(mItems);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.option_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mItem = mItems.get(position);
        holder.textView.setText(mItem.getOption());
        if (Global.color == 1)
            if (mItem.getTag() == 1) {
                holder.textView.setTextColor(Color.GREEN);
            } else if (mItem.getTag() == 2) {
                holder.textView.setTextColor(Color.RED);
            } else {
                holder.textView.setTextColor(Color.YELLOW);
            }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button btnFav;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txtOption);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItem = mItems.get(getAdapterPosition());
                    if (OptionActivity.getInstance().stop > 0) {
                        return;
                    }
                    Global.color = 1;
                    OptionActivity.getInstance().stop++;
                    OptionActivity.getInstance().colorChange();
                    if (mItem.getTag() == 1) {
                        OptionActivity.getInstance().correct++;
                    } else {
                        mItem.setTag(2);
                        notifyDataSetChanged();
                        OptionActivity.getInstance().wrong++;
                    }

                    OptionActivity.getInstance().txtResult.setText(OptionActivity.getInstance().correct + " : " + OptionActivity.getInstance().wrong);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
