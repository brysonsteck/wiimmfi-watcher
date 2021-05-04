package com.example.wiimmterfaceandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.wiimmterfaceandroid.database.AppDatabase;
import com.example.wiimmterfaceandroid.model.FriendCode;
import com.example.wiimmterfaceandroid.viewmodel.FriendCodeViewModel;
import com.example.wiimmterfaceandroid.wiimmfi.WiimmfiActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecentCodesFragment extends Fragment {

    public RecentCodesFragment() {
        super(R.layout.fragment_recent_friend_codes);
    }
    FriendCodeViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(FriendCodeViewModel.class);

        RecentCodesAdapter adapter = new RecentCodesAdapter(
                viewModel.getEntries(),
                (entry) -> {
                    viewModel.setCurrentEntry(entry);
                    Intent intent = new Intent(view.getContext(), WiimmfiActivity.class);
                    intent.putExtra("friendCode", entry.friendCode);
                    startActivity(intent);
                }
        );
        viewModel.getEntries().addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<FriendCode>>() {
            @Override
            public void onChanged(ObservableList<FriendCode> sender) {
                getActivity().runOnUiThread(adapter::notifyDataSetChanged);
            }

            @Override
            public void onItemRangeChanged(ObservableList<FriendCode> sender, int positionStart, int itemCount) {
                getActivity().runOnUiThread(() -> {
                    adapter.notifyItemRangeChanged(positionStart, itemCount);
                });
            }

            @Override
            public void onItemRangeInserted(ObservableList<FriendCode> sender, int positionStart, int itemCount) {
                getActivity().runOnUiThread(() -> {
                    adapter.notifyItemRangeInserted(positionStart, itemCount);
                });
            }

            @Override
            public void onItemRangeMoved(ObservableList<FriendCode> sender, int fromPosition, int toPosition, int itemCount) {
                getActivity().runOnUiThread(() -> {
                    adapter.notifyItemMoved(fromPosition, toPosition);
                });
            }

            @Override
            public void onItemRangeRemoved(ObservableList<FriendCode> sender, int positionStart, int itemCount) {
                getActivity().runOnUiThread(() -> {
                    adapter.notifyItemRangeRemoved(positionStart, itemCount);
                });
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recent_friend_codes_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        view.findViewById(R.id.fab).setOnClickListener((button) -> {
//            viewModel.setCurrentEntry(null);
//            getActivity().getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container_view, CreateOrUpdateFriendCodeFragment.class, null)
//                    .setReorderingAllowed(true)
//                    .addToBackStack(null)
//                    .commit();
//        });
//        RecyclerView recyclerView = view.findViewById(R.id.recent_friend_codes_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new RecentCodesAdapter(recentFCList));
    }

}
