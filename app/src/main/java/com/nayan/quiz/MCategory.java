package com.nayan.quiz;

import java.util.ArrayList;

/**
 * Created by Nayan on 9/29/2017.
 */
public class MCategory {
    private int id;
    private ArrayList<MQuestion> questionArrayList;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<MQuestion> getQuestionArrayList() {
        return questionArrayList;
    }

    public void setQuestionArrayList(ArrayList<MQuestion> questionArrayList) {
        this.questionArrayList = questionArrayList;
    }
}
