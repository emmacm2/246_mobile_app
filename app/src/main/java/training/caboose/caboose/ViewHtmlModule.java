package training.caboose.caboose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class ViewHtmlModule extends AppCompatActivity {

    String htmlData;
    String moduleId;
    String positionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_html_module);
        Intent intent = getIntent();
        htmlData = intent.getStringExtra(getString(R.string.htmlModuleIntentHtmlData));
        moduleId = intent.getStringExtra(getString(R.string.moduleIntent_ModuleId));
        positionId = intent.getStringExtra("positionId");

        WebView moduleWebView = (WebView) findViewById(R.id.htmlModuleWebView);
        moduleWebView.loadData(htmlData, "text/html", null);

    }

    public void toQuiz (View view) {
        Intent intent = new Intent (this, Quiz.class);
        intent.putExtra(getString(R.string.moduleIntent_ModuleId), moduleId);
        intent.putExtra(getString(R.string.moduleIntent_PositionId), positionId);
        startActivity(intent);
    }




}
