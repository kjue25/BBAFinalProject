package com.example.kyliejue.bbafinalproject;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by aashnashroff on 3/14/18.
 */

public class OutputSound implements Output {
    public OutputSound(){}

    @Override
    public void onTrigger(Context context) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.happy );
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
    }
}
