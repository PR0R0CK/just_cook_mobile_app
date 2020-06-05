package com.example.justcook.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justcook.R;
import com.example.justcook.model.Comment;

import java.util.ArrayList;

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Comment> comments = new ArrayList<>();
    Context mContext;

    public CommentRecyclerViewAdapter(ArrayList<Comment> commentList, Context mContext) {
        this.comments = commentList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout_comment,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.username.setText(comment.getUsername());
        holder.comment.setText(comment.getComment());
    }

    @Override
    public int getItemCount(){
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        TextView comment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.user_textView_comment);
            comment = itemView.findViewById(R.id.comment_textView_comment);
        }
    }
}
