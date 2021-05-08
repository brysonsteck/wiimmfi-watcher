package me.brysonsteck.wiimmfiwatcher;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import me.brysonsteck.wiimmfiwatcher.model.FriendCode;
import me.brysonsteck.wiimmfiwatcher.wiimmfi.WiimmfiActivity;

public class WatchCodeAdapter extends RecyclerView.Adapter<WatchCodeAdapter.ViewHolder>{
    ObservableArrayList<FriendCode> entries;
    OnFriendCodeClicked listener;
    Context context;
    public interface OnFriendCodeClicked {
        public void onClick(FriendCode entry);
    }

    public WatchCodeAdapter(Context context, ObservableArrayList<FriendCode> entries) {
        this.context = context;
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
                context.startActivity(intent);
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
