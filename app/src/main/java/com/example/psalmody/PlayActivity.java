package com.example.psalmody;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.psalmody.ListenActivity;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    Button playBtn;
    SeekBar songPositionBar, volumeBar;
    TextView elapsedTimeLabel, remainingTimeLabel;
    int totalTime;
    MediaPlayer mediaPlayer ;
    ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        playBtn = (Button) findViewById(R.id.playBtn);
        elapsedTimeLabel = (TextView) findViewById(R.id.elapsedTimelabel);
        remainingTimeLabel = (TextView) findViewById(R.id.remainingTimelabel);
        mediaPlayer = MediaPlayer.create(this, R.raw.swedish);
        mediaPlayer.setLooping(true);
        mediaPlayer.seekTo(0);
        mediaPlayer.setVolume(0.5f, 0.5f);
        totalTime = mediaPlayer.getDuration();

        songPositionBar = (SeekBar)findViewById(R.id.positionBar);
        songPositionBar.setMax(totalTime);

        songPositionBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if(fromUser){
                             mediaPlayer.seekTo(progress);
                                songPositionBar.setProgress(progress);
                            }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {  }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) { }
                }
        );


        volumeBar = (SeekBar) findViewById(R.id.volumeBar);

        volumeBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        float volumeNum = progress/100f;
                        mediaPlayer.setVolume(volumeNum, volumeNum);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) { }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {  }
                }
        );

        // Thread (Update positionBar and timelabel

        new Thread(new Runnable() {
            @Override
            public void run() {
              while(mediaPlayer !=null){
                 try{
                     Message msg = new Message();
                     msg.what = mediaPlayer.getCurrentPosition();
                     handler.sendMessage(msg);
                     Thread.sleep(1000);
                 } catch(InterruptedException ie){

                  }
                }
            }
        }).start();

        Intent intent= getIntent();
        int position = (int)intent.getLongExtra("position",0 );


//        if(position!=0) {
//
//
//            if (mediaPlayer != null) {
//                mediaPlayer.release();
//            }
////            arrayList = new ArrayList<String>();
////            Field[] fields = R.raw.class.getFields();
//////
////            for (int i = 0; i < fields.length; i++){
////                arrayList.add(fields[i].getName());
////            }
//            //int resId = getResources().getIdentifier(arrayList.get(position), "raw", getPackageName());
//            mediaPlayer = MediaPlayer.create(PlayActivity.this, position);
//            mediaPlayer.start();
//        }
    }

    private  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            int currentPosition = msg.what;
            songPositionBar.setProgress(currentPosition); // update songPositionBar

            //update time lables
            String elapsedTime = createTimeLable(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            String remainingTime = createTimeLable(totalTime - currentPosition);
            remainingTimeLabel.setText("-" + remainingTime);
        }
    };

    public String createTimeLable(int time){
       String timeLabel = "";
       int min = time / 1000 / 60;
       int sec = time / 1000 % 60;

        timeLabel = min + ":";

       if (sec < 10){
           timeLabel  += "0";
       }
        timeLabel += sec;

       return timeLabel;
    }

    public void playBtnClick(View view) {
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
            playBtn.setBackgroundResource(R.drawable.stop);
        }
        else{
            mediaPlayer.pause();
            playBtn.setBackgroundResource(R.drawable.play);
        }
    }
}
