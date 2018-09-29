package com.bbkk.android.bbkkclient.presenter;

import android.view.View;

import com.bbkk.android.bbkkclient.model.ReviewModel;
import com.bbkk.android.bbkkclient.view.review.ReviewActivity;
import com.bbkk.android.bbkkclient.view.review.ReviewContract;

import java.util.ArrayList;

public class ReviewPresenter implements ReviewContract.Presenter{
  private ReviewContract.View view;
  private ArrayList<ReviewModel> reviews = new ArrayList<>();

  public ReviewPresenter(ReviewActivity view) {
    this.view = view;
    this.view.initView();
    this.requestContentList();
    this.checkDeleteBtn(view);
    this.checkBestReview(view);
  }

  private void checkBestReview(ReviewActivity view) {
    // TODO: 베스트 댓글일 경우 로고 표시
//    기본값을 GONE로 부여
    if(true) {
      view.tvBestLogo.setVisibility(View.VISIBLE);
    }
  }

  public void checkDeleteBtn(ReviewActivity view) {
//    TODO: 댓글작성자 ID와 사용자의 ID를 비교한다
//    기본값을 GONE로 부여하여 맞을시에만 보인다.
    if(true) {
      view.tvDeleteBtn.setVisibility(View.VISIBLE);
    }
  }

  private void requestContentList() {
//    TODO: 서버에 요청한다.
    reviews.add(new ReviewModel("하이1"));
    reviews.add(new ReviewModel("하이2"));
    reviews.add(new ReviewModel("하이3"));
    reviews.add(new ReviewModel("하이4"));
    reviews.add(new ReviewModel("하이5"));
    reviews.add(new ReviewModel("하이6"));
    view.renderReview(reviews);
  }

  @Override
  public void deleteReview() {
//TODO: 본인의 해당 리뷰 삭제
  }
}
