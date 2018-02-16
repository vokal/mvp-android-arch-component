package com.quangnguyen.stackoverflowclient.ui.questions.details;

import android.arch.lifecycle.*;
import android.support.annotation.NonNull;

import com.quangnguyen.stackoverflowclient.data.repository.QuestionRepository;
import com.quangnguyen.stackoverflowclient.util.DateTimeUtils;
import com.quangnguyen.stackoverflowclient.util.schedulers.RunOn;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

import static com.quangnguyen.stackoverflowclient.util.schedulers.SchedulerType.IO;
import static com.quangnguyen.stackoverflowclient.util.schedulers.SchedulerType.UI;

public class DetailsPresenter implements DetailsContract.Presenter, LifecycleObserver {

    private final DetailsContract.View view;

    private final QuestionRepository repository;

    private final Scheduler           ioScheduler;
    private final Scheduler           uiScheduler;

    private         CompositeDisposable disposeBag;

    @Inject public DetailsPresenter(@NonNull QuestionRepository repository,
                                   @NonNull DetailsContract.View view,
                                   @RunOn(IO) Scheduler ioScheduler,
                                   @RunOn(UI) Scheduler uiScheduler) {
       this.repository = repository;
       this.view = view;
       this.ioScheduler = ioScheduler;
       this.uiScheduler = uiScheduler;

        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }

        disposeBag = new CompositeDisposable();
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAttach() {

    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDetach() {
        disposeBag.clear();
    }

    @Override public void getQuestion(long questionId) {
        Timber.d("getQuestion(%d)", questionId);
        Disposable disposable = repository.getQuestion(questionId)
                .doOnNext(question -> Timber.d(question.getTitle()))
                .filter(question -> question != null)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(question -> {
                    Timber.d("Question: %s", question.getTitle());
                    view.setQuestionText(question.getTitle());
                    view.setQuestionUserName(question.getUser().getName());
                    view.setQuestionUserImage(question.getUser().getImage());
                    view.setQuestionTime(DateTimeUtils.formatRelativeTime(question.getCreationDate()));
//                    view.setQuestionComments(); // TODO
//                    view.setQuestionAnswers(…);
                }, error -> Timber.e(error, "ERROR: getting question by ID (%d)", questionId));

        disposeBag.add(disposable);
    }

    @Override
    public void onAnswerVotedUp() {

    }

    @Override
    public void onAnswerVotedDown() {

    }
}
