package com.example.wiimmterfaceandroid;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wiimmterfaceandroid.model.FriendCode;
import com.example.wiimmterfaceandroid.wiimmfi.WiimmfiActivity;
import com.google.android.material.button.MaterialButton;

public class WatchCodeAdapter extends RecyclerView.Adapter<WatchCodeAdapter.ViewHolder>{
    ObservableArrayList<FriendCode> entries;
    OnFriendCodeClicked listener;
    public interface OnFriendCodeClicked {
        public void onClick(FriendCode entry);
    }

    public WatchCodeAdapter(ObservableArrayList<FriendCode> entries) {

        this.entries = entries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_friend_codes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MaterialButton fcButton = holder.itemView.findViewById(R.id.recent_friend_code_button);
        FriendCode currentFC = entries.get(position);
        fcButton.setText(currentFC.friendCode);
        fcButton.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), WiimmfiActivity.class);
                intent.putExtra("friendCode", currentFC.friendCode);
                view.getContext();

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
