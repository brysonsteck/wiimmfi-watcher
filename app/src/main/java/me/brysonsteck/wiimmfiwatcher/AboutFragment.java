package me.brysonsteck.wiimmfiwatcher;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Objects;

public class AboutFragment extends Fragment {
    View aboutButton;
    MaterialToolbar toolbar;
    ScrollView scrollView;

    public AboutFragment() {
        super(R.layout.about_fragment);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
        setExitTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scrollView = view.findViewById(R.id.about_view);

        int nightModeFlags =
                getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            // Night mode is active, we're using dark theme
            scrollView.setBackgroundColor(Color.parseColor("#151515"));
        }

        aboutButton = getActivity().findViewById(R.id.about_button);
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("About Wiimmfi Watcher");

        TextView aboutWatcher = view.findViewById(R.id.about_watcher_text);
        TextView aboutMe = view.findViewById(R.id.about_me_text);
        TextView github = view.findViewById(R.id.github_text);
        TextView contact = view.findViewById(R.id.contact_text);
        TextView bugs = view.findViewById(R.id.bugs_text);
        TextView license = view.findViewById(R.id.license_text);

        aboutWatcher.setText(R.string.about_watcher);

        aboutMe.setText(R.string.about_me);

        github.setClickable(true);
        github.setMovementMethod(LinkMovementMethod.getInstance());

        github.setText(R.string.github);

        contact.setClickable(true);
        contact.setMovementMethod(LinkMovementMethod.getInstance());

        contact.setText(R.string.contact);

        bugs.setClickable(true);
        bugs.setMovementMethod(LinkMovementMethod.getInstance());

        bugs.setText(R.string.bugs);

        license.setClickable(true);
        license.setMovementMethod(LinkMovementMethod.getInstance());

        license.setText(R.string.license);
    }

    @Override
    public void onStop() {
        super.onStop();
        aboutButton.setVisibility(View.VISIBLE);
        toolbar.setTitle("Wiimmfi Watcher");
    }
    @Override
    public void onResume() {
        super.onResume();
        aboutButton.setVisibility(View.INVISIBLE);
        toolbar.setTitle("About Wiimmfi Watcher");
    }

}
