package com.example.wiimmterfaceandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wiimmterfaceandroid.model.FriendCode;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class RecentCodesAdapter extends RecyclerView.Adapter<RecentCodesAdapter.ViewHolder>{
    ObservableArrayList<FriendCode> entries;
    OnFriendCodeClicked listener;
    public interface OnFriendCodeClicked {
        public void onClick(FriendCode entry);
    }

    public RecentCodesAdapter (ObservableArrayList<FriendCode> entries, OnFriendCodeClicked listener) {
        this.entries = entries;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_friend_codes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Button fcButton = holder.itemView.findViewById(R.id.recent_friend_code_button);
        FriendCode currentFC = entries.get(position);
        fcButton.setText(currentFC.friendCode);
        fcButton.setOnClickListener(view -> {
            if (listener == null) return;
            listener.onClick(currentFC);
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
