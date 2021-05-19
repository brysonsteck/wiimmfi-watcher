package me.brysonsteck.wiimmfiwatcher.wiimmfi;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import me.brysonsteck.wiimmfiwatcher.R;

public class RoomFragment extends Fragment {
    String display;
    String header;
    String playerLink;
    ArrayList<Player> players;
    RoomData roomData;

    public RoomFragment(String friendCode, ArrayList<Player> players, String playerLink, String display) {
        super(R.layout.room_fragment);
        this.roomData = new RoomData(players, friendCode);
        new Thread(() -> {
            this.header = roomData.getRoomHeader();
        }).start();
        this.display = display;
        this.players = players;
        this.playerLink = playerLink;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton refreshButton = view.findViewById(R.id.refresh_button);
        TextView headerTextView = view.findViewById(R.id.room_header_text);
        if (header == null) {
            header = "This player is not online, not inside a room or does not exist. Click the refresh button to try again, or click on the back button to enter a different friend code.";
        }
        headerTextView.setText(header);
        RecyclerView recyclerView = view.findViewById(R.id.player_data_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RoomAdapter(display, playerLink, header, players, getContext()));

        refreshButton.setOnClickListener((buttonView) -> {
            this.players.clear();
            this.header = "";
            this.roomData = roomData.refresh();
            RoomData newRoomData = roomData.refresh();
            this.players = roomData.getPlayers();
            header = newRoomData.getRoomHeader();
            if (header == null) {
                header = "This player is not online, not inside a room or does not exist. Click the refresh button to try again, or click on the back button to enter a different friend code.";
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new RoomAdapter(display, playerLink, header, players, getContext()));
            headerTextView.setText(header);

        });
    }
}
