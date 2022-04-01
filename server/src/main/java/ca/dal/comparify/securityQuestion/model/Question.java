package ca.dal.comparify.securityQuestion.model;

import javax.persistence.Entity;

@Entity
public class Question {
    private String userIdentifier;
    private String question;
    private String answer;
    
    public Question() {
        // Create this constructor for Mongo Codec to create object
    }

    public Question(String question, String answer, String userIdentifier) {
        this.userIdentifier = userIdentifier;
        this.question = question;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
   public String getUserIdentifier() {
       return userIdentifier;
   }
    public String getQuestion() {
        return question;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }
    
}