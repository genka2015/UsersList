package com.example.android.userslist;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {

    private ArrayList<User> users;
    private Context context;
    ItemClickListener mItemClickListener;

    public UserRecyclerAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }

    @Override
    public UserRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recycler_item,parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserRecyclerAdapter.ViewHolder holder, int position) {

        User currentUser = users.get(position);
        holder.userTV.setText(currentUser.getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        TextView userTV;

        public ViewHolder(View itemView){
            super(itemView);
            userTV = (TextView)itemView.findViewById(R.id.tv_names);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mItemClickListener != null){
                mItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public void setClickListener(ItemClickListener listener){
        this.mItemClickListener = listener;
    }

}
