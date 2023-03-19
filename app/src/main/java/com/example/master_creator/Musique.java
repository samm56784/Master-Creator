package com.example.master_creator;



import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.media.MediaPlayer;

import androidx.activity.ComponentActivity;

public class Musique extends Activity {
    private String musiqueActuel;
    private ArrayList<String> playlist;
    private boolean musiqueEnCour;
    private int index;
    private  MediaPlayer mediaPlayer ;

    public Musique() {
        musiqueActuel = "";
        playlist = new ArrayList<String>();
        musiqueEnCour = false;
        playlist.add("chanson1");
        playlist.add("chanson2");
        playlist.add("chanson3");
        playlist.add("chanson4");
        playlist.add("chanson5");
        playlist.add("chanson6");
    }

    public void addSong(String song) {
        playlist.add(song);
    }

    public void removeSong(String song) {
        playlist.remove(song);
    }

    public void clearPlaylist() {
        playlist.clear();
    }
    public void play() {
        if (!musiqueEnCour) {
            musiqueEnCour = true;
            musiqueActuel = playlist.get(0);
            System.out.println("Joue: " + musiqueActuel);
            Resources res = getResources();
            int musicId1 = res.getIdentifier(playlist.get(0), "raw", getPackageName());
           mediaPlayer =  MediaPlayer.create(this, musicId1);

          //  mediaPlayer.start();



        } else {
            System.out.println("La musique est on");

        }
       /* mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
                index += 1;
                Resources res = getResources();
                int musicId1 = res.getIdentifier(playlist.get(index), "raw", getPackageName());
                mediaPlayer =  MediaPlayer.create(getApplicationContext(), musicId1);
                mediaPlayer.start();

            }
        });*/

    }

    public void pause() {
        if (musiqueEnCour) {
            musiqueEnCour = false;
            System.out.println("Musique en pause");
        } else {
            System.out.println("La musique est off");
        }
    }

    public void nextSong() {
        int currentIndex = playlist.indexOf(musiqueActuel);
        if (currentIndex == -1 || currentIndex == playlist.size() - 1) {
            System.out.println("No next song");
            return;
        }

        musiqueActuel = playlist.get(currentIndex + 1);
        System.out.println("Playing next song: " + musiqueActuel);
    }

    public void previousSong() {
        int currentIndex = playlist.indexOf(musiqueActuel);
        if (currentIndex == -1 || currentIndex == 0) {
            System.out.println("No previous song");
            return;
        }

        musiqueActuel = playlist.get(currentIndex - 1);
        System.out.println("Playing previous song: " + musiqueActuel);
    }



}


