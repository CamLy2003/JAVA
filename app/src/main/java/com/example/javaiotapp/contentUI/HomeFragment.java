package com.example.javaiotapp.contentUI;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.javaiotapp.R;

public class HomeFragment extends Fragment {
    private VideoView videoView;
    private int currentVideoPosition = 0;

    private TextView textView;
    private String userName = "Guest";  //will be replace by the log-in info


    private Handler handler = new Handler();
    static private int i = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        videoView = view.findViewById(R.id.videoViewBg);
        textView = view.findViewById(R.id.welcomeTxtView);

        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.bg;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                videoView.seekTo(currentVideoPosition);
                videoView.start();
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                // Handle error
                return true;
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        currentVideoPosition = videoView.getCurrentPosition();
        videoView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        videoView.seekTo(currentVideoPosition);
        videoView.start();
    }

    private void welcomeLineWithAnimation(String textToAnimate) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(i < textToAnimate.length()) {
                    textView.append(String.valueOf(textToAnimate.charAt(i)));
                    i++;
                    handler.postDelayed(this, 75);
                }
            }
        }, 150);
    }

    public void updateNameInfo(String name) {
        userName = getNameOnly(name);
        welcomeLineWithAnimation("Welcome \""+ userName + "\"!");
    }

    private String getNameOnly(String fullName) {
        String[] parts = fullName.split(" ");
        return parts[parts.length - 1].trim();
    }
}