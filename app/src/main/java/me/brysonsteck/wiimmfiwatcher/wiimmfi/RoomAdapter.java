package me.brysonsteck.wiimmfiwatcher.wiimmfi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import org.jsoup.*;

import java.util.ArrayList;

import me.brysonsteck.wiimmfiwatcher.R;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder>{
    String display;
    String playerLink;
    String header;
    ArrayList<Player> players;
    Context context;

    public RoomAdapter (String display, String playerLink, String header, ArrayList<Player> players, Context context) {
        this.display = display;
        this.playerLink = playerLink;
        this.header = header;
        this.players = players;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_player_data_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.ViewHolder holder, int position) {
        MaterialCardView cardView = holder.itemView.findViewById(R.id.player_card_view);
        TextView rosterNumber = holder.itemView.findViewById(R.id.roster_number);
        TextView miiName = holder.itemView.findViewById(R.id.mii_names);
        TextView variableDisplay = holder.itemView.findViewById(R.id.variable_side_data);
        Player currentPlayer = players.get(position);
        LinearLayout.LayoutParams cardViewParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        cardView.setLayoutParams(cardViewParams);
        ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) cardView.getLayoutParams();
        cardViewMarginParams.setMargins(40,40,40,40);
        cardView.requestLayout();
        int nightModeFlags =
                context.getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            // Night mode is active, we're using dark theme
            cardView.setCardBackgroundColor(Color.parseColor("#313131"));
        }
        if (currentPlayer.watching) {
            cardView.setCardBackgroundColor(Color.parseColor("#0D47A1"));
            rosterNumber.setTextColor(Color.WHITE);
            miiName.setTextColor(Color.WHITE);
            variableDisplay.setTextColor(Color.WHITE);
        }
        rosterNumber.setText(currentPlayer.rosterNumber + ")  ");
        miiName.setText(currentPlayer.miiName);

        switch (display) {
            case "fc":
                variableDisplay.setText(currentPlayer.friendCode);
                break;
            case "roles":
                variableDisplay.setText(currentPlayer.role);
                break;
            case "login_regions":
                variableDisplay.setText(currentPlayer.loginRegion);
                break;
            case "room_match":
                variableDisplay.setText(currentPlayer.roomMatch);
                break;
            case "world":
                variableDisplay.setText(currentPlayer.world);
                break;
            case "conn_fail":
                variableDisplay.setText(currentPlayer.connFail);
                break;
            case "vr_br":
                variableDisplay.setText("VR: " + currentPlayer.vr + " / BR: " + currentPlayer.br);
                break;
        }
        if (position + 1 == getItemCount()) {
            cardViewMarginParams.setMargins(40,40,40,250);
            cardView.requestLayout();
        }

    }

    @Override
    public int getItemCount() {
        if (players == null) {
            return 0;
        } else {
            return players.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
