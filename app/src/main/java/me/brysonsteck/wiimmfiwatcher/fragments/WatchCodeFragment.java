package me.brysonsteck.wiimmfiwatcher.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import me.brysonsteck.wiimmfiwatcher.R;
import me.brysonsteck.wiimmfiwatcher.model.FriendCode;
import me.brysonsteck.wiimmfiwatcher.viewmodel.FriendCodeViewModel;
import me.brysonsteck.wiimmfiwatcher.wiimmfi.WiimmfiActivity;

public class WatchCodeFragment extends Fragment {
    ProgressDialog progressBar;
    boolean friendCodeMode = true;

    public WatchCodeFragment(boolean friendCodeMode) {
        super(R.layout.watch_code_fragment);
        this.friendCodeMode = friendCodeMode;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setEnterTransition(inflater.inflateTransition(R.transition.fade));
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FriendCodeViewModel viewModel = new ViewModelProvider(getActivity()).get(FriendCodeViewModel.class);
        progressBar = new ProgressDialog(getContext(), R.style.AppCompatAlertDialogStyle);

        MaterialTextView errorText = view.findViewById(R.id.error_text);
        WatchCodeAdapter adapter = new WatchCodeAdapter(getContext(), viewModel.getEntries(), errorText, progressBar);
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
                    adapter.notifyItemRangeInserted(positionStart, itemCount); // this is the only method that seems to be called
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

        EditText friendCode = view.findViewById(R.id.friend_code_edit_text);
        if (!friendCodeMode) {
            friendCode.setHint(R.string.enter_mii);
        }
        Button watchButton = view.findViewById(R.id.watch_button);
        watchButton.setOnClickListener(buttonClick -> {
            startWiimmfiActivity(
                    view,
                    friendCode,
                    errorText,
                    watchButton,
                    viewModel
            );
            watchButton.setText(R.string.watch);
        });
        friendCode.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View view1, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            startWiimmfiActivity(
                                    view,
                                    friendCode,
                                    errorText,
                                    watchButton,
                                    viewModel
                            );
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (progressBar.isShowing()) { progressBar.dismiss(); }
    }

    public void startWiimmfiActivity(View view, EditText friendCode, MaterialTextView errorText, Button watchButton, FriendCodeViewModel viewModel) {
        Intent intent = new Intent(view.getContext(), WiimmfiActivity.class);
        if (!isValidFriendCode(friendCode.getText().toString())) {
            errorText.setText(R.string.error_fc_syntax);
        } else {
            progressBar.setCancelable(false);
            progressBar.setMessage(getResources().getString(R.string.locating_text, friendCode.getText()));
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.show();

            errorText.setText("");
            viewModel.saveFriendCode("", friendCode.getText().toString());
            intent.putExtra("friendCode", friendCode.getText().toString());
            ProgressBar p = view.findViewById(R.id.progressBar1);
            if(p.getVisibility() != View.GONE){ // check if it is visible
                p.setVisibility(View.GONE); // if not set it to visible
                watchButton.setVisibility(View.VISIBLE); // use 1 or 2 as parameters.. arg0 is the view(your button) from the onclick listener
            }
            startActivity(intent);
        }
    }
}
