package me.brysonsteck.wiimmfiwatcher.wiimmfi;

import android.widget.ImageView;

import me.brysonsteck.wiimmfiwatcher.R;

public class SetImageView {

    public SetImageView(ImageView image, String header, boolean failure) {
        if (failure) {
            image.setImageResource(R.drawable.globe);
        } else {
            header = header.toLowerCase();
            if (header.contains("wii luigi circuit")) {
                image.setImageResource(R.drawable.course_lc);
            } else if (header.contains("wii moo moo meadows")) {
                image.setImageResource(R.drawable.course_mmm);
            } else if (header.contains("wii mushroom gorge")) {
                image.setImageResource(R.drawable.course_mg);
            } else if (header.contains("wii toad's factory")) {
                image.setImageResource(R.drawable.course_tf);
            } else if (header.contains("wii mario circuit")) {
                image.setImageResource(R.drawable.course_mc);
            } else if (header.contains("wii coconut mall")) {
                image.setImageResource(R.drawable.course_cm);
            } else if (header.contains("wii dk summit") || header.contains("wii dk's snowboard cross")) {
                image.setImageResource(R.drawable.course_dks);
            } else if (header.contains("wii wario's gold mine")) {
                image.setImageResource(R.drawable.course_wgm);
            } else if (header.contains("wii daisy circuit")) {
                image.setImageResource(R.drawable.course_dc);
            } else if (header.contains("wii koopa cape")) {
                image.setImageResource(R.drawable.course_kc);
            } else if (header.contains("wii maple treeway")) {
                image.setImageResource(R.drawable.course_mt);
            } else if (header.contains("wii grumble volcano")) {
                image.setImageResource(R.drawable.course_gv);
            } else if (header.contains("wii dry dry ruins")) {
                image.setImageResource(R.drawable.course_ddr);
            } else if (header.contains("wii moonview highway")) {
                image.setImageResource(R.drawable.course_mh);
            } else if (header.contains("wii bowser's castle")) {
                image.setImageResource(R.drawable.course_bc);
            } else if (header.contains("wii rainbow road")) {
                image.setImageResource(R.drawable.course_rr);
            } else if (header.contains("gcn peach beach")) {
                image.setImageResource(R.drawable.course_rpb);
            } else if (header.contains("ds yoshi falls")) {
                image.setImageResource(R.drawable.course_ryf);
            } else if (header.contains("snes ghost valley 2")) {
                image.setImageResource(R.drawable.course_rgv2);
            } else if (header.contains("n64 mario raceway")) {
                image.setImageResource(R.drawable.course_rmr);
            } else if (header.contains("n64 sherbet land")) {
                image.setImageResource(R.drawable.course_rsl);
            } else if (header.contains("gba shy guy beach")) {
                image.setImageResource(R.drawable.course_rsgb);
            } else if (header.contains("ds delfino square")) {
                image.setImageResource(R.drawable.course_rds);
            } else if (header.contains("gcn waluigi stadium")) {
                image.setImageResource(R.drawable.course_rws);
            } else if (header.contains("ds desert hills")) {
                image.setImageResource(R.drawable.course_rdh);
            } else if (header.contains("gba bowser's castle 3")) {
                image.setImageResource(R.drawable.course_rbc3);
            } else if (header.contains("n64 dk's jungle parkway")) {
                image.setImageResource(R.drawable.course_rdkjp);
            } else if (header.contains("gcn mario circuit")) {
                image.setImageResource(R.drawable.course_rmc);
            } else if (header.contains("snes mario circuit 3")) {
                image.setImageResource(R.drawable.course_rmc3);
            } else if (header.contains("ds peach gardens")) {
                image.setImageResource(R.drawable.course_rpg);
            } else if (header.contains("gcn dk mountain")) {
                image.setImageResource(R.drawable.course_rdkm);
            } else if (header.contains("n64 bowser's castle")) {
                image.setImageResource(R.drawable.course_rbc);
            } else if (header.contains("wii block plaza")) {
                image.setImageResource(R.drawable.battle_bp);
            } else if (header.contains("wii delfino pier")) {
                image.setImageResource(R.drawable.battle_dp);
            } else if (header.contains("wii funky stadium")) {
                image.setImageResource(R.drawable.battle_fs);
            } else if (header.contains("wii chain chomp")) {
                image.setImageResource(R.drawable.battle_ccw);
            } else if (header.contains("wii thwomp desert")) {
                image.setImageResource(R.drawable.battle_td);
            } else if (header.contains("snes battle course 4")) {
                image.setImageResource(R.drawable.battle_rbc4);
            } else if (header.contains("n64 skyscraper")) {
                image.setImageResource(R.drawable.battle_rs);
            } else if (header.contains("gba battle course 3")) {
                image.setImageResource(R.drawable.battle_rbc3);
            } else if (header.contains("gcn cookie land")) {
                image.setImageResource(R.drawable.battle_rcl);
            } else if (header.contains("ds twilight house")) {
                image.setImageResource(R.drawable.battle_rth);
            } else if (header.contains("last track:") && !header.contains("nintendo")) {
                image.setImageResource(R.drawable.course_ctgp);
            }
        }
    }
}
