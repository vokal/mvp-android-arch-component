package com.quangnguyen.stackoverflowclient.data.api;

import com.google.gson.annotations.SerializedName;
import com.quangnguyen.stackoverflowclient.data.model.Answer;

import java.util.List;

public class AnswersResponse {

    @SerializedName("items")
    public List<Answer> answers;
}
