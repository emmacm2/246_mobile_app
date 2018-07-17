package training.caboose.caboose;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import training.caboose.caboose.Models.Question;
import training.caboose.caboose.ViewAdaptors.QuizViewAdapter;

public class Quiz extends AppCompatActivity {

    private String moduleId;
    private String positionId;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    SharedPreferences pref;
    String orgId;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        moduleId = intent.getStringExtra(getString(R.string.moduleIntent_ModuleId));
        positionId = intent.getStringExtra(getString(R.string.moduleIntent_PositionId));
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        pref = getApplicationContext().getSharedPreferences(getString(R.string.userSharedPrefs), 0);
        orgId = pref.getString("orgId", null);
        mRecyclerView = (RecyclerView) findViewById(R.id.quizRCView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onStart() {

        super.onStart();

        currentUser = mAuth.getCurrentUser();
        //send user to login screen if not authenticated

        if (currentUser == null) {
            startActivity(new Intent(Quiz.this, SplashScreen.class));
        }
        if (orgId == null) {
            startActivity(new Intent(Quiz.this, SplashScreen.class));
        }


        database.getReference("Questions/" + orgId + "/" + moduleId)
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            List<Question> questionList = new ArrayList<>();


                            private void createAdapter() {
                                QuizViewAdapter mAdapter = new QuizViewAdapter(questionList, Quiz.this, orgId,currentUser.getUid(),positionId,moduleId );
                                mRecyclerView.setAdapter(mAdapter);
                            }

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                for (final DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
                                    Question thisQuestion = new Question();
                                    thisQuestion.setUid(questionSnapshot.getKey());
                                    thisQuestion.setText(questionSnapshot.getValue(Question.class).getText());
                                    thisQuestion.setAnswers(questionSnapshot.getValue(Question.class).getAnswers());
                                    questionList.add(thisQuestion);
                                }
                                createAdapter();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                //handle error
                            }
                        }
                );
    }


    public void submitQuiz(View view){
           database.getReference("EmployeeResponses/" + orgId +"/"+currentUser.getUid()+"/"+positionId+"/"+moduleId+"/complete").setValue(true);
           Intent intent = new Intent(this, ViewModules.class );
           intent.putExtra("positionId", positionId);
           startActivity(intent);
    }


}
