package com.upshotdev.okWord.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;

public class Utils {
    public static int digitAfterComma(double number) {
        return (int) ((Math.round(number * 100) % 100));
    }


    public static void play(final Context context, final ArrayList<Integer> list) {

        if (list.size() == 0) return;

        final int[] position = {0};


        final MediaPlayer[] mediaPlayer = {MediaPlayer.create(context, list.get(position[0]))};
        mediaPlayer[0].start();
        mediaPlayer[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                position[0] += 1;
                if (position[0] < list.size()) {

                    AssetFileDescriptor afd = context.getResources().openRawResourceFd(list.get(position[0]));
                    mp.reset();
                    try {
                        mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
                        mp.prepare();
                        mp.start();
                        afd.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
    }
}
