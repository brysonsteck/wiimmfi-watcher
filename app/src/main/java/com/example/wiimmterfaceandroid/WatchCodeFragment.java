package com.example.wiimmterfaceandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wiimmterfaceandroid.database.AppDatabase;
import com.example.wiimmterfaceandroid.model.FriendCode;
import com.example.wiimmterfaceandroid.viewmodel.FriendCodeViewModel;
import com.example.wiimmterfaceandroid.wiimmfi.WiimmfiActivity;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class WatchCodeFragment extends Fragment {
    List<FriendCode> recentFCList;
    AppDatabase database;

    public WatchCodeFragment(AppDatabase database, List<FriendCode> recentFCList) {
        super(R.layout.friend_code_input_fragment);
        this.database = database;
        this.recentFCList = recentFCList;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button watchButton = view.findViewById(R.id.watch_button);
        EditText friendCode = view.findViewById(R.id.friend_code_edit_text);
        MaterialTextView errorText = view.findViewById(R.id.error_text);
        watchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), WiimmfiActivity.class);
                FriendCodeViewModel friendCodeViewModel = new FriendCodeViewModel(friendCode.getText().toString());
                if (friendCodeViewModel.getFullFriendCode() == null) {
                    errorText.setText("ERROR: Insert a friend code in the format XXXX-XXXX-XXXX");
                    friendCode.setText("");
                } else {
                    errorText.setText("");
//                    FriendCodeObj friendCodeObj = new FriendCodeObj();
//                    friendCodeObj.friendCode = friendCode.getText().toString();
//                    database.getFriendCodeDao().insert(friendCodeObj);
                    intent.putExtra("friendCode", friendCodeViewModel.getFullFriendCode());
                    startActivity(intent);
                }
            }
        });
    }
}
