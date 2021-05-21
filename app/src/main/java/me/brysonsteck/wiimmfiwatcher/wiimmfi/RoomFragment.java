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

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

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
        if (roomData.error != null) {
            header = "Wiimmfi Watcher was unable to connect to the Wiimmfi servers. This could be that you are not connected to the internet, but it could be something else. Here was the error:\n\n" +
                    roomData.error.getMessage() + "\n\n" +
                    "If the error is along the lines of \"Unable to resolve host\" or \"Timeout\", you are probably having internet issues. Make sure you are connected to the internet then click the refresh button or press back to watch a new friend code.\n\n" +
                    "If the error is something other than that or if the error persists, make sure that Wiimmfi's website is currently running. Otherwise, please screenshot this screen and submit a bug report by clicking the About icon on the main page.";

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
            if (newRoomData.error instanceof java.net.SocketTimeoutException || newRoomData.error instanceof java.net.UnknownHostException) {
                header = "Wiimmfi Watcher was unable to connect to the Wiimmfi servers. This could be that you are not connected to the internet, but it could be something else. Here was the error:\n\n" +
                        roomData.error.getMessage() + "\n\n" +
                        "If the error is along the lines of \"Unable to resolve host\" or \"Timeout\", you are probably having internet issues. Make sure you are connected to the internet then click the refresh button or press back to watch a new friend code.\n\n" +
                        "If the error is something other than that or if the error persists, make sure that Wiimmfi's website is currently running. Otherwise, please screenshot this screen and submit a bug report by clicking the About icon on the main page.";
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new RoomAdapter(display, playerLink, header, players, getContext()));
            headerTextView.setText(header);

        });
    }
}
