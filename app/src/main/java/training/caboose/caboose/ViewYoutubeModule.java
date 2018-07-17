package training.caboose.caboose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import training.caboose.caboose.Configs.PlayerConfig;


public class ViewYoutubeModule extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    Button PlayButton;
    Button QuizButton;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    private static final String TAG = "Youtube";
    String videoId;
    String moduleId;
    String positionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_youtube_module);
        Log.d(TAG, "onCreate: Starting");
        PlayButton = (Button) findViewById(R.id.playButton);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.YoutubePlay);
        QuizButton = (Button) findViewById(R.id.quizButton);

        Intent intent = getIntent();
        videoId = intent.getStringExtra(getString(R.string.youtubeModuleIntentYoutubeData));
        moduleId = intent.getStringExtra(getString(R.string.moduleIntent_ModuleId));
        positionId = intent.getStringExtra("positionId");


        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //String youtubeURL = MainActivity.YOUTUBE_URL;
                Log.d(TAG, "onClick: Successfully Initialized Youtube Player");
                youTubePlayer.loadVideo(videoId);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onClick: Failed to Initialize Youtube Player");

            }
        };

        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Initializing Youtube Player");
                youTubePlayerView.initialize(PlayerConfig.getAPI_Key(), onInitializedListener);
            }
        });
    }

    public void loadQuiz(View view){
        Intent intent = new Intent(this, Quiz.class);
        intent.putExtra(getString(R.string.moduleIntent_ModuleId), moduleId);
        intent.putExtra(getString(R.string.moduleIntent_PositionId), positionId);
        startActivity(intent);
    }
}