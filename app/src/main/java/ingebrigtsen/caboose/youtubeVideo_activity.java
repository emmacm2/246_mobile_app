package ingebrigtsen.caboose;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class youtubeVideo_activity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    Button PlayButton;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    private static final String TAG = "Youtube";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_YoutubePlayer);
        Log.d(TAG, "onCreate: Starting");
        PlayButton = (Button) findViewById(R.id.playButton);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.YoutubePlay);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                String youtubeURL = MainActivity.YOUTUBE_URL;
                Log.d(TAG, "onClick: Successfully Initialized Youtube Player");
                /*
                List<String> videoList;
                // videoList.add( /* Last portion of url goes here "");
                youTubePlayer.loadVideos(videoList);
                 */
                /*
                youTubePlayer.loadPlaylist("");
                 */

                youTubePlayer.loadVideo(/* Last portion of url goes here */youtubeURL);

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
}