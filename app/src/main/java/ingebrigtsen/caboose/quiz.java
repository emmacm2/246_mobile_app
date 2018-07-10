package ingebrigtsen.caboose;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class quiz extends AppCompatActivity {
    String moduleId = MainActivity.MODULE_ID;
    private questionLibrary mQuestionLibrary = new questionLibrary(moduleId);

    private TextView mQuestion;
    private Button mButtonResponse1;
    private Button mButtonResponse2;
    private Button mButtonResponse3;
    private Button mButtonResponse4;

    private String mAnswer;
    private int mQuestionNumber = 0;
    private int mScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestion = (TextView) findViewById(R.id.question);
        mButtonResponse1 = (Button) findViewById(R.id.response1);
        mButtonResponse2 = (Button) findViewById(R.id.response2);
        mButtonResponse3 = (Button) findViewById(R.id.response3);
        mButtonResponse4 = (Button) findViewById(R.id.response4);
        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);

        updateQuestions();
        //Button Listener for button 1
        mButtonResponse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonResponse1.getText() == mAnswer) {
                    mScore++;
                }
                mQuestionNumber++;
                updateQuestions();
            }
        });

        //Button Listener for button 2
        mButtonResponse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonResponse2.getText() == mAnswer) {
                    mScore++;
                }
                mQuestionNumber++;
                updateQuestions();
            }
        });

        //Button Listener for button 3
        mButtonResponse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonResponse3.getText() == mAnswer) {
                    mScore++;
                }
                mQuestionNumber++;
                updateQuestions();
            }
        });

        //Button Listener for button 4
        mButtonResponse4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonResponse4.getText() == mAnswer) {
                    mScore++;
                }
                mQuestionNumber++;
                updateQuestions();
            }
        });
    }

        //Update Questions
        private void updateQuestions() {
            mQuestion.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonResponse1.setText(mQuestionLibrary.getResponse1(mQuestionNumber));
            mButtonResponse2.setText(mQuestionLibrary.getResponse2(mQuestionNumber));
            mButtonResponse3.setText(mQuestionLibrary.getResponse3(mQuestionNumber));
            mButtonResponse4.setText(mQuestionLibrary.getResponse4(mQuestionNumber));
        }
}
