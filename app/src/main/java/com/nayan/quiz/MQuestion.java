package com.nayan.quiz;

import java.util.ArrayList;

/**
 * Created by Nayan on 9/29/2017.
 */
public class MQuestion {
    private int id;
    private String ques;
    private ArrayList<MOption> optionArrayList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public ArrayList<MOption> getOptionArrayList() {
        return optionArrayList;
    }

    public void setOptionArrayList(ArrayList<MOption> optionArrayList) {
        this.optionArrayList = optionArrayList;
    }
}
