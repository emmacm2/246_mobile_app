package ingebrigtsen.caboose;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

public static final String EXTRA_MESSAGE = "com.ingebrigtsen.caboose.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayContent (View view) {
        Intent intent = new Intent (this, Content.class);
        String content = "<html><body>This is HTML</body></html>";
        String modID = "module ID placeholder";
        intent.putExtra(EXTRA_MESSAGE, content);
        intent.putExtra(EXTRA_MESSAGE, modID);
        startActivity(intent);
    }

}
