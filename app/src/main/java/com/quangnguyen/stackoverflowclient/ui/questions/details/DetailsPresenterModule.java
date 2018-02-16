package com.quangnguyen.stackoverflowclient.ui.questions.details;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailsPresenterModule {

    private final DetailsContract.View view;

    public DetailsPresenterModule(@NonNull DetailsContract.View view) {
        this.view = view;
    }

    @Provides
    public DetailsContract.View provideDetailsView() {
        return view;
    }
}
