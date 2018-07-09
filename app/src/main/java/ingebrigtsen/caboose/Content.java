package ingebrigtsen.caboose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class Content extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.ingebrigtsen.caboose.MESSAGE";
    String modID;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webview = new WebView(this);
        setContentView(webview);
        Intent intent = getIntent();
        String content = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        modID = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        webview.loadData(content, "text/html", null);
    }

    public void toQuiz (View view) {
        Intent intent = new Intent (this, Quiz.class);
        intent.putExtra(EXTRA_MESSAGE, modID);
        startActivity(intent);
    }
}
