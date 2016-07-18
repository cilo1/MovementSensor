package com.example.cmigayi.movementsensor;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by cmigayi on 02-Jul-16.
 */
public class BeepRunnable implements Runnable {
    private final MediaPlayer mediaPlayer;
    private final View view;
    private final int repeats;
    private final int interval;
    private int currentRepeat;

    public BeepRunnable(@NonNull View view, int repeats, int interval) {
        this.view = view;
        mediaPlayer = MediaPlayer.create(this.view.getContext(), R.raw.martian_gun);
        this.repeats = repeats;
        this.interval = interval;
    }

    @Override
    public void run() {

        mediaPlayer.start();
        if (currentRepeat < repeats) {
            // set to beep again
            currentRepeat = currentRepeat + 1;
            view.postDelayed(this, interval);
        }
        else {
            // beep is over, just reset the counter
            reset();
        }

    }

    public void reset() {
        currentRepeat = 0;
    }

    public void destroy() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }

        mediaPlayer.release();
        view.removeCallbacks(this);
    }
}
