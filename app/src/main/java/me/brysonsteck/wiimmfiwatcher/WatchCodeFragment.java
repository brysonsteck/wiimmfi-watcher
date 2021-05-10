package me.brysonsteck.wiimmfiwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import me.brysonsteck.wiimmfiwatcher.model.FriendCode;
import me.brysonsteck.wiimmfiwatcher.viewmodel.FriendCodeViewModel;
import me.brysonsteck.wiimmfiwatcher.wiimmfi.WiimmfiActivity;

public class WatchCodeFragment extends Fragment {

    public WatchCodeFragment() {
        super(R.layout.friend_code_input_fragment);
    }

    public boolean isValidFriendCode(String friendCode) {
        String[] friendCodeSplit = friendCode.split("-");
        boolean valid = false;
        if (friendCodeSplit.length == 3) {
            for (String friendCodePart : friendCodeSplit) {
                valid = friendCodePart.length() == 4;
                if (!valid) {
                    break;
                }
            }
        }
        return valid;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FriendCodeViewModel viewModel = new ViewModelProvider(getActivity()).get(FriendCodeViewModel.class);

        WatchCodeAdapter adapter = new WatchCodeAdapter(getContext(), viewModel.getEntries());
        viewModel.getEntries().addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<FriendCode>>() {
            @Override
            public void onChanged(ObservableList<FriendCode> sender) {
                getActivity().runOnUiThread(() -> {
                    adapter.notifyDataSetChanged();
                });
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        Button watchButton = view.findViewById(R.id.watch_button);
        EditText friendCode = view.findViewById(R.id.friend_code_edit_text);
        MaterialTextView errorText = view.findViewById(R.id.error_text);
        watchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), WiimmfiActivity.class);
                if (!isValidFriendCode(friendCode.getText().toString())) {
                    errorText.setText("ERROR: Insert a friend code in the format XXXX-XXXX-XXXX");
                    friendCode.setText("");
                } else {
                    errorText.setText("");
                    viewModel.saveFriendCode("", friendCode.getText().toString());
//                    FriendCodeObj friendCodeObj = new FriendCodeObj();
//                    friendCodeObj.friendCode = friendCode.getText().toString();
//                    database.getFriendCodeDao().insert(friendCodeObj);
                    intent.putExtra("friendCode", friendCode.getText().toString());
                    startActivity(intent);
                }
            }
        });



    }
}
