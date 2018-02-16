package com.quangnguyen.stackoverflowclient.ui.questions.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.quangnguyen.stackoverflowclient.AndroidApplication;
import com.quangnguyen.stackoverflowclient.R;
import com.quangnguyen.stackoverflowclient.data.QuestionRepositoryComponent;
import com.quangnguyen.stackoverflowclient.data.model.Answer;
import com.quangnguyen.stackoverflowclient.data.model.Comment;
import com.quangnguyen.stackoverflowclient.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class DetailsActivity extends BaseActivity implements DetailsContract.View {

    public static final String EXTRA_QUESTION_ID = ".questionID";

    @Inject DetailsPresenter presenter;

    @BindView(R.id.text_title)        TextView  titleText;
    @BindView(R.id.text_user)         TextView  userText;
    @BindView(R.id.text_created_time) TextView  createdTimeText;
    @BindView(R.id.image_profile)     ImageView profileImage;

    @BindView(R.id.answer_recycler) RecyclerView answersRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        DaggerDetailsComponent.builder()
                .questionRepositoryComponent(getQuestionRepositoryComponent())
                .detailsPresenterModule(new DetailsPresenterModule(this))
                .build()
                .inject(this);

        long id = getIntent().getLongExtra(EXTRA_QUESTION_ID, -1);
        Timber.d("Question ID: %d", id);
        if (id > 0) {
            presenter.getQuestion(id);
        }
    }

    protected QuestionRepositoryComponent getQuestionRepositoryComponent() {
        return ((AndroidApplication) getApplication()).getQuestionRepositoryComponent();
    }

    @Override
    public void setQuestionText(@NonNull String text) {
        titleText.setText(text);
    }

    @Override
    public void setQuestionUserName(@NonNull String username) {

    }

    @Override
    public void setQuestionUserImage(@NonNull String username) {

    }

    @Override
    public void setQuestionTime(@NonNull String created) {

    }

    @Override
    public void setQuestionComments(@NonNull List<Comment> comments) {

    }

    @Override
    public void setQuestionAnswers(@NonNull List<Answer> answers) {

    }
}
