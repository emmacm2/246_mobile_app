package training.caboose.caboose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewPositionDetails extends AppCompatActivity {

    String positionId;
    String assingedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_position_details);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        TextView statusText = (TextView) findViewById(R.id.viewStatusPositionId);
        TextView assignedDateText = (TextView) findViewById(R.id.viewStatusAssignedDate);



        if(bd != null){
            positionId = (String) bd.get("PositionId");
            assingedDate = (String) bd.get("assignedDate");
            statusText.setText(positionId);
            assignedDateText.setText(assingedDate);
        }
    }
}
