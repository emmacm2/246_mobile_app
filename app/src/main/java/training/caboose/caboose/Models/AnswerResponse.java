package training.caboose.caboose.Models;

public class AnswerResponse {
    private String selectedAnswer;
    private Long dateComplete;
    private Boolean correct;

    public AnswerResponse(String selectedAnswer, Long dateComplete, Boolean isCorrect) {
        this.selectedAnswer = selectedAnswer;
        this.dateComplete = dateComplete;
        this.correct = isCorrect;
    }

    public AnswerResponse() {
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public Long getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(Long dateComplete) {
        this.dateComplete = dateComplete;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
}
