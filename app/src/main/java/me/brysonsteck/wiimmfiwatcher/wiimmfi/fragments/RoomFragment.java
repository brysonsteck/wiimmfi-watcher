package me.brysonsteck.wiimmfiwatcher.wiimmfi.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import me.brysonsteck.wiimmfiwatcher.R;
import me.brysonsteck.wiimmfiwatcher.wiimmfi.Player;
import me.brysonsteck.wiimmfiwatcher.wiimmfi.RoomData;
import me.brysonsteck.wiimmfiwatcher.wiimmfi.SetImageView;

public class RoomFragment extends Fragment {
    String display;
    String header;
    String playerLink;
    ArrayList<Player> players;
    RoomData roomData;
    MaterialToolbar toolbar;
    ImageView image;

    public RoomFragment(String friendCode, ArrayList<Player> players, String playerLink, String display, MaterialToolbar toolbar, ImageView image) {
        super(R.layout.room_fragment);
        this.roomData = new RoomData(players, friendCode);
        new Thread(() -> {
            this.header = roomData.getRoomHeader();
        }).start();
        this.display = display;
        this.players = players;
        this.playerLink = playerLink;
        this.toolbar = toolbar;
        this.image = image;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton refreshButton = view.findViewById(R.id.refresh_button);
        TextView headerTextView = view.findViewById(R.id.room_header_text);
        if (header == null) {
            headerTextView.setText(R.string.header_null_error);
            toolbar.setNavigationIcon(null);
            SetImageView setImageView = new SetImageView(image, header, true);
        }
        if (roomData.error != null) {
            headerTextView.setText(getResources().getString(R.string.jsoup_error, roomData.error));
            toolbar.setNavigationIcon(null);
            SetImageView setImageView = new SetImageView(image, header, true);
        }
        if (roomData.error == null && header != null) {
            headerTextView.setText(header);
            toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
            SetImageView setImageView = new SetImageView(image, header, false);
        }
        RecyclerView recyclerView = view.findViewById(R.id.player_data_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RoomAdapter(display, playerLink, header, players, getContext()));

        refreshButton.setOnClickListener((buttonView) -> {
            Toast.makeText(getContext(), "Refreshing!", Toast.LENGTH_LONG).show();
            this.players.clear();
            this.header = "";
            this.roomData = roomData.refresh();
            RoomData newRoomData = roomData.refresh();
            this.players = roomData.getPlayers();
            header = newRoomData.getRoomHeader();
            if (header == null) {
                headerTextView.setText(R.string.header_null_error);
                toolbar.setNavigationIcon(null);
                SetImageView setImageView = new SetImageView(image, header, true);
            }
            if (newRoomData.error instanceof java.net.SocketTimeoutException || newRoomData.error instanceof java.net.UnknownHostException) {
                headerTextView.setText(getResources().getString(R.string.jsoup_error, roomData.error));
                toolbar.setNavigationIcon(null);
                SetImageView setImageView = new SetImageView(image, header, true);
            }
            if (roomData.error == null && header != null) {
                headerTextView.setText(header);
                toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
                SetImageView setImageView = new SetImageView(image, header, false);
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new RoomAdapter(display, playerLink, header, players, getContext()));

        });
    }
}
