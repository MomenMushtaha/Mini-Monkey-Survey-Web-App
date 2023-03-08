package com.app.surveymonkey.survey;

import com.app.surveymonkey.questions.MultipleChoiceQuestion;
import com.app.surveymonkey.questions.Question;
import com.app.surveymonkey.questions.RangeQuestion;
import com.app.surveymonkey.questions.TextQuestion;
import com.app.surveymonkey.surveyor.Surveyor;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

@Entity
public class Survey {

    @Id
    @GeneratedValue
    private int id;

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    private Boolean open;
    private String surveyName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Question> questions;

    public Survey() {
        this.questions = new HashSet();
    }

    public Survey(int id) {
        this.questions = new HashSet();
        this.id = id;
        this.open=false;
    }

    public void addQuestion(Question question) {
        if (question != null) {
            questions.add(question);
        }
    }

    public void removeQuestion(Question question) {
        Iterator<Question> questionIterator = questions.iterator();
        while (questionIterator.hasNext()) {
            if (questionIterator.next().getId() == question.getId()) {
                questionIterator.remove();
            }
        }
    }

    // ----------------- GETTERS & SETTERS -------------------
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurveyName() {
        return this.surveyName;
    }

    public void setSurveyName(String name) {
        this.surveyName = name;
    }

    public Collection<Question> getQuestions() {
        return this.questions;
    }

    @Override
    public String toString() {
        String surveyString = "Survey ID: " + id + "\n" + "Survey Name: " + surveyName + "\n";
        for (Question question : questions) {
            surveyString = surveyString + question.toString() + "\n\n";
        }
        return surveyString;
    }

}