package com.quangnguyen.stackoverflowclient.data.api;

import io.reactivex.Flowable;
import retrofit2.http.*;

public interface QuestionService {
  @GET("questions?site=stackoverflow")
  Flowable<QuestionResponse> loadQuestionsByTag(@Query("tagged") String tag);

  @GET("questions/{id}/answers?site=stackoverflow&filter=!-*jbN.OXKfDP")
  Flowable<AnswersResponse> loadAnswersByQuestionId(@Path("id") long id);
}
