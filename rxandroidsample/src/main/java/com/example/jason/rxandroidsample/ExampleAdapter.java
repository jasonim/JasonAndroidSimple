package com.example.jason.rxandroidsample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsson on 16/4/20.
 */
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder> {

    private Context mContext;
    private List<ExampleActivityAndName> mExamples;

    public ExampleAdapter(Context context, List<ExampleActivityAndName> examples) {
        mContext = context;
        mExamples = examples;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("hujd", "oncreate");
        View v = LayoutInflater
                .from(mContext)
                .inflate(R.layout.example_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d("hujd", "onBind pos " + position);
        holder.mNameDisplay.setText(mExamples.get(position).mExampleName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exampleIntent = new Intent(mContext, mExamples.get(position).mExampleActivityClass);
                mContext.startActivity(exampleIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("hujd", "size: " + mExamples.size());
        return mExamples.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mNameDisplay;

        public ViewHolder(View itemView) {
            super(itemView);
            mNameDisplay = (TextView) itemView.findViewById(R.id.name_display);
        }
    }

//    @Override
//    public int getItemViewType(int position) {
//        Log.e("hujd", "getItemViewType() Called");
//        if (mExamples.size() == 0) {
//            return 0;
//        }
//        return super.getItemViewType(position);
//    }
}
