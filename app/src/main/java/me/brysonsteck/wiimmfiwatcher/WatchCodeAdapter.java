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

import java.util.ArrayList;

import me.brysonsteck.wiimmfiwatcher.model.FriendCode;
import me.brysonsteck.wiimmfiwatcher.wiimmfi.WiimmfiActivity;

public class WatchCodeAdapter extends RecyclerView.Adapter<WatchCodeAdapter.ViewHolder>{
    ObservableArrayList<FriendCode> entries;
    Context context;
    ArrayList<String> recentCodes;

    public WatchCodeAdapter(Context context, ObservableArrayList<FriendCode> entries) {
        this.context = context;
        this.entries = entries;
        this.recentCodes = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_friend_codes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String currentFC = entries.get(position).friendCode;
        MaterialButton fcButton = holder.itemView.findViewById(R.id.recent_friend_code_button);
        fcButton.setText(currentFC);
        fcButton.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), WiimmfiActivity.class);
            intent.putExtra("friendCode", currentFC);
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
