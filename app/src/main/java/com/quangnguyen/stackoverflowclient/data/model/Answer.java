package com.quangnguyen.stackoverflowclient.data.model;

public class Answer {

    public long    answer_id;
    public long    question_id;
    public long    last_edit_date;
    public int     up_vote_count;
    public int     down_vote_count;
    public boolean is_accepted;
    public int     score;
    public User    owner;
    public String  title;
    public String  body;
}
