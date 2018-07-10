package ingebrigtsen.caboose;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends AppCompatActivity {


    public static final String YOUTUBE_URL = "/* content needed */";
    public static final String MODULE_ID = "/* content needed*/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void getYouTube(View view, String youtubeURL) {
        Intent youTubeIntent = new Intent(this, YoutubeVideo_activity.class);
        youTubeIntent.putExtra(YOUTUBE_URL, youtubeURL, MODULE_ID, moduleId);
        startActivity(youTubeIntent);
    }

}
