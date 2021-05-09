package me.brysonsteck.wiimmfiwatcher;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AboutFragment extends Fragment {
    public AboutFragment() { super(R.layout.about_fragment); }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView aboutWatcher = view.findViewById(R.id.about_watcher_text);
        TextView aboutMe = view.findViewById(R.id.about_me_text);
        TextView github = view.findViewById(R.id.github_text);
        TextView donations = view.findViewById(R.id.donations_text);
        TextView contact = view.findViewById(R.id.contact_text);
        TextView bugs = view.findViewById(R.id.bugs_text);

        aboutWatcher.setText("Wiimmfi Watcher is an UNOFFICIAL application created for a school project that I have decided to turn into a full application. " +
                "This application was made to provide an easy shortcut to the Wiimmfi website and display data in a mobile friendly way, since the official website doesn't have a mobile friendly version. " +
                "Free and open source, you can watch your Wiimmfi Mario Kart Wii matches on your phone in a quick and easy way. " +
                "");

        aboutMe.setText("Hi there! My name is Bryson Steck. I am a student studying Computer Science at Utah State University. This is my first official application that I'm maintaining. " +
                "This whole \"application on the Google Play Store\" thing is new to me, so please be patient as I am learning how to maintain something like this. " +
                "I hope you enjoy my first application!");

        github.setClickable(true);
        github.setMovementMethod(LinkMovementMethod.getInstance());
        String githubLink = "<a href='https://github.com/brysonsteck/wiimmfi-watcher/tree/master'>here.</a>";

//        github.setText("All of the code in this project is open source on my GitHub repository " + Html.fromHtml(githubLink) + " You are free to use this code and expand upon it under the GNU General Public License.");
        github.setText(R.string.github);
//        donations.setMovementMethod(LinkMovementMethod.getInstance());
//        String donationsLink = "<a href='https://github.com/brysonsteck/wiimmfi-watcher/tree/master'>here.</a>";
        donations.setText("Since this application is free and the code is open source, I do not receive income from maintaining this app. Because of that, I'd appreciate any donation in the following methods:\n\n" +
                "PayPal: @bryzinga\n" +
                "Venmo: @brysonsteck\n" +
                "Bitcoin: 1Kbnp5JMTKd7a3Zs2WWm2JMCjfVb5tpcky\n" +
                "Litecoin: LRboJVNzoJCjXHmwN6RQgyvYEQjjaFzEA7\n" +
                "Dogecoin: DMx362YBEBYw1uDGetX3svdg8RypHsWTCS");

        contact.setText("If you would like to get ahold of me for any reason unrelated to bug reports or this app in general, you can contact me through email at steck.bryson@gmail.com " +
                "or on Discord at bryzinga#9971.");

//        String todoList = "<a href='https://github.com/brysonsteck/wiimmfi-watcher/blob/master/TODO.md'>todo list.</a>";
//        String issueGithub = "<a href='https://github.com/brysonsteck/wiimmfi-watcher/issues'>here.</a>";
//        bugs.setText("Speaking of bugs, did you find a bug? First, make sure that the issue you found is not listed on my " + Html.fromHtml(todoList) +
//                "It's possible I'm already aware of it or working on it. If your issue is not addressed on the todo list, then you can create an issue on my GitHub repository " + Html.fromHtml(issueGithub) +
//                "If you aren't sure how to use GitHub, you can also fill out this Google Forum.");
        bugs.setText(R.string.bugs);
    }

}
