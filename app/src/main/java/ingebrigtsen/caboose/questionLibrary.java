package ingebrigtsen.caboose;

public class questionLibrary {
    private String mQuestions [] = {

    };
    private String mResponses [][] = {

    };

    private String mCorrectAnswers [] ={

    };

    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }

    public String getResponse1(int a) {
        String choice0 = mResponses[a][0];
        return choice0;
    }

    public String getResponse2(int a) {
        String choice1 = mResponses[a][1];
        return choice1;
    }

    public String getResponse3(int a) {
        String choice2 = mResponses[a][2];
        return choice2;
    }

    public String getResponse4(int a) {
        String choice3 = mResponses[a][3];
        return choice3;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }
}
