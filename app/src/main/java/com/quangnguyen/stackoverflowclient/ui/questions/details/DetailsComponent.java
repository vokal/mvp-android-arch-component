package com.quangnguyen.stackoverflowclient.ui.questions.details;

import com.quangnguyen.stackoverflowclient.data.QuestionRepositoryComponent;
import com.quangnguyen.stackoverflowclient.ui.base.ActivityScope;
import com.quangnguyen.stackoverflowclient.util.schedulers.SchedulerModule;

import dagger.Component;

@ActivityScope
@Component(
        modules = {
                DetailsPresenterModule.class,
                SchedulerModule.class},
        dependencies = {
                QuestionRepositoryComponent.class
        }
)
public interface DetailsComponent {
    void inject(DetailsActivity questionsActivity);
}
