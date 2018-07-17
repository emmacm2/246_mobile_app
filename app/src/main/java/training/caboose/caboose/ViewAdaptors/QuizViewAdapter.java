package training.caboose.caboose.ViewAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.List;
import java.util.Map;

import training.caboose.caboose.Models.Answer;
import training.caboose.caboose.Models.AnswerResponse;
import training.caboose.caboose.Models.Question;
import training.caboose.caboose.R;



public class QuizViewAdapter extends RecyclerView.Adapter<QuizViewAdapter.ViewHolder>{

    Context mContext;
    List<Question> mQuestions;
    private RadioGroup lastCheckedRadioGroup = null;
    String orgId,userId , positionId, moduleId;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    public QuizViewAdapter(List<Question> questionList, Context context, String orgId, String userId, String positionId, String moduleId){
        mQuestions = questionList;
        mContext = context;
        this.orgId = orgId;
        this.userId = userId;
        this.positionId = positionId;
        this.moduleId = moduleId;
    }

    @NonNull
    @Override
    public QuizViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz_item, parent, false);
        QuizViewAdapter.ViewHolder viewHolder = new QuizViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewAdapter.ViewHolder holder, int position) {
        Question thisQuestion = mQuestions.get(position);
        holder.questionText.setText(thisQuestion.getText());
        holder.answerGroup.setTag(thisQuestion.getUid());

        int id = (position+1)*100;
        for (Map.Entry<String, Answer> answer : thisQuestion.getAnswers().entrySet())
        {
            //System.out.println(answer.getKey() + "/" + answer.getValue());
            RadioButton thisRb = new RadioButton(QuizViewAdapter.this.mContext);
            thisRb.setId(id++);
            answer.getValue().setUid(answer.getKey());
            thisRb.setText(answer.getValue().getText());
            thisRb.setTag(answer.getValue());
            thisRb.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            thisRb.setPadding(0,15,0,15);

            holder.answerGroup.addView(thisRb);
        }




    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView questionText;
        public RadioGroup answerGroup;

        public ViewHolder(final View itemView) {
            super(itemView);
            questionText = (TextView) itemView.findViewById(R.id.quizQuestion);
            answerGroup = (RadioGroup) itemView.findViewById(R.id.quizAnswerGroup);



            answerGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    Boolean isCorrect = false;

                    String questionId = (String) radioGroup.getTag();
                    Log.d("sd332432432", radioGroup.getTag().toString());

                    RadioButton thisRb =  (RadioButton) itemView.findViewById(i);
                    Answer ans = (Answer) thisRb.getTag();
                    Log.d("thisRb",  ans.getUid() );
                    if(ans.getMarked() != null){
                        isCorrect = ans.getMarked();
                    }

                    AnswerResponse response = new AnswerResponse();
                    response.setDateComplete(new Date().getTime());
                    response.setCorrect(isCorrect);
                    response.setSelectedAnswer(ans.getUid());



                    database.getReference("EmployeeResponses/"+ orgId +"/" + userId +"/"+ positionId +"/"+moduleId +"/"+questionId).setValue(response);




                }

            });


        }

        @Override
        public void onClick(View v) {

        }
    }


}
