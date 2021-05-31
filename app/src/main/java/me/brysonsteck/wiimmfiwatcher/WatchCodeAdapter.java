package me.brysonsteck.wiimmfiwatcher;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import me.brysonsteck.wiimmfiwatcher.model.FriendCode;
import me.brysonsteck.wiimmfiwatcher.wiimmfi.WiimmfiActivity;

public class WatchCodeAdapter extends RecyclerView.Adapter<WatchCodeAdapter.ViewHolder>{
    ObservableArrayList<FriendCode> entries;
    Context context;
    MaterialTextView errorText;
    ProgressDialog progressBar;
    ArrayList<String> recentCodes;

    public WatchCodeAdapter(Context context, ObservableArrayList<FriendCode> entries,
                            MaterialTextView errorText, ProgressDialog progressBar) {
        this.context = context;
        this.entries = entries;
        this.errorText = errorText;
        this.progressBar = progressBar;
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
        int nightModeFlags =
                context.getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            // Night mode is active, we're using dark theme
            fcButton.setBackgroundColor(Color.parseColor("#313131"));
        }
        fcButton.setText(currentFC);
        fcButton.setOnClickListener(view -> {
            progressBar.setCancelable(true);
            progressBar.setMessage(holder.itemView.getResources().getString(R.string.locating_text, currentFC));
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.show();
            errorText.setText("");
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
