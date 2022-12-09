package com.srcarrillo.proyectompa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.animation.ValueAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Radio extends AppCompatActivity {

    private ImageView imagePlayPause;
    private TextView textCurrentTime, textTotalDuration;
    private SeekBar playerSeekBar;
    private MediaPlayer mediaplayer;
    private Handler handler = new Handler();
    private Boolean isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_radio);
        imagePlayPause = findViewById(R.id.imagePlayPause);
        /*textCurrentTime = findViewById(R.id.textCurrentTime);
        textTotalDuration = findViewById(R.id.textTotalDuration);
        playerSeekBar = findViewById(R.id.playerSeekBar);*/
        mediaplayer = new MediaPlayer();
        //playerSeekBar.setMax(100);

        prepareMediaPlayer();

        TextView first = (TextView) findViewById(R.id.first);

        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, -0.6f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(12000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = first.getWidth();
                final float translationX = width * progress;
                first.setTranslationX(translationX);

            }
        });
        animator.start();

        imagePlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaplayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaplayer.pause();
                    imagePlayPause.setImageResource(R.drawable.ic_play);
                } else {
                    mediaplayer.start();
                    isPlaying = true;
                    imagePlayPause.setImageResource(R.drawable.ic_pause);
                    //updateSeekBar();
                }
            }
        });

    }

    private void prepareMediaPlayer(){
        try {
            mediaplayer.setDataSource("http://stm.livestation.stream:7134");
            mediaplayer.prepare();
            //textTotalDuration.setText(milliSecondsToTimer(mediaplayer.getDuration()));
        } catch (Exception exception){
            Toast.makeText(this,exception.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = mediaplayer.getCurrentPosition();
            textCurrentTime.setText(milliSecondsToTimer(currentDuration));
        }
    };

    private void updateSeekBar(){
        if(mediaplayer.isPlaying()){
            playerSeekBar.setProgress((int) (((float) mediaplayer.getCurrentPosition()/ mediaplayer.getDuration())*100));
            handler.postDelayed(updater,1000);
        }
    }


    private String milliSecondsToTimer ( long milliSeconds){
        String timerString = "";
        String secondsString;

        int hours = (int)(milliSeconds/(1000*60*60));
        int minutes = (int)(milliSeconds % (1000*60*60));
        int seconds = (int) (milliSeconds% (1000*60*60))%(1000*60/1000);

        if (hours>0) {
            timerString = hours + ":";
        }
        if (seconds<10){
            secondsString = "0" +seconds;
        }
        else{
            secondsString = " + seconds;";
        }
        timerString = timerString+minutes+secondsString;
        return timerString;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isPlaying", isPlaying);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaplayer.pause();
    }
}