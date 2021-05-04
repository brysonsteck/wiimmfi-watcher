package com.example.wiimmterfaceandroid.wiimmfi;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wiimmterfaceandroid.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.color.MaterialColors;

import java.io.IOException;
import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder>{

    String display;
    String playerLink;
    String header;
    ArrayList<Player> players;
    boolean online = true;
    public RoomAdapter (String display, String playerLink, String header, ArrayList<Player> players) {
        this.display = display;
        this.playerLink = playerLink;
        this.header = header;
        this.players = players;
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
