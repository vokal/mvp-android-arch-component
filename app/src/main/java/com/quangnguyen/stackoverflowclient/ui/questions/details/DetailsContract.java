package com.quangnguyen.stackoverflowclient.ui.questions.details;

import android.support.annotation.NonNull;

import com.quangnguyen.stackoverflowclient.data.model.Answer;
import com.quangnguyen.stackoverflowclient.data.model.Comment;
import com.quangnguyen.stackoverflowclient.ui.base.BasePresenter;

import java.util.List;

public interface DetailsContract {

    interface View {

        void setQuestionText(@NonNull String text);
        void setQuestionUserName(@NonNull String username);
        void setQuestionUserImage(@NonNull String username);
        void setQuestionTime(@NonNull String created);

        void setQuestionComments(@NonNull List<Comment> comments);
        void setQuestionAnswers(@NonNull List<Answer> answers);
    }

    interface Presenter extends BasePresenter<View> {
        void getQuestion(long questionId);
        void onAnswerVotedUp();
        void onAnswerVotedDown();

    }
}
