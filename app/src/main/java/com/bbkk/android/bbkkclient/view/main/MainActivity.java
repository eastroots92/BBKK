package com.bbkk.android.bbkkclient.view.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.model.Timeline;
import com.bbkk.android.bbkkclient.model.response.CardFeedsResponse;
import com.bbkk.android.bbkkclient.presenter.MainPresenter;
import com.bbkk.android.bbkkclient.adapter.TimeLineAdapter;
import com.bbkk.android.bbkkclient.view.detail.DetailActivity;
import com.bbkk.android.bbkkclient.view.write.WriteActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View{
  private MainContract.Presenter presenter;

  @BindView(R.id.tv_back_button)
  public TextView tvBackBtn;
  @BindView(R.id.tv_main_counter)
  public TextView tvMainCounter;
  @BindView(R.id.drawer_layout)
  public DrawerLayout drawer;
  @BindView(R.id.iv_menu_button)
  public ImageView btnOpenMenu;
  @BindView(R.id.nv_header_main)
  public NavigationView nvHeaderMain;
  @BindView(R.id.btn_start_write)
  public FloatingActionButton btnWrite;
  @BindView(R.id.rv_timeline)
  public RecyclerView rvTimeLineLayout;
  private RecyclerView.Adapter timeLineAdapter;
  private View headerView;
  private ImageView ivCloseMenu;
  public static ArrayList<CardFeedsResponse.Result.PopularData> popularDataLists;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    this.presenter = new MainPresenter(this);
  }

  @Override
  public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public void initView() {
    headerView = nvHeaderMain.getHeaderView(0);
    ivCloseMenu = headerView.findViewById(R.id.iv_close_button);
    tvBackBtn.setVisibility(View.GONE);
    this.drawerManager();
    this.writeListener();
  }

  @Override
  public void renderTimeLine(ArrayList<CardFeedsResponse.Result.PopularData> popularDataLists) {
    this.popularDataLists = popularDataLists;
    rvTimeLineLayout.setLayoutManager(new LinearLayoutManager(this));
    timeLineAdapter = new TimeLineAdapter(this.popularDataLists, this::handleClickTimelineEntry);
    rvTimeLineLayout.setAdapter(timeLineAdapter);
  }

  @Override
  public void renderMainCounter(int size) {
    tvMainCounter.setText(size + "개");
  }

  private void writeListener() {
    btnWrite.setOnClickListener((__) -> {
      startWriteActivity();
    });
  }

  private void startWriteActivity() {
      startActivity(new Intent(this, WriteActivity.class));
  }

  private void drawerManager() {
    this.openHeaderMenu();
    this.closeHeaderMenu();
  }

  private void closeHeaderMenu() {
    ivCloseMenu.setOnClickListener((__) -> {
      drawer.closeDrawer(GravityCompat.START);
    });
  }

  private void openHeaderMenu() {
    btnOpenMenu.setOnClickListener((__) -> {
      drawer.openDrawer(GravityCompat.START);
    });
  }

  private void handleClickTimelineEntry(View view, CardFeedsResponse.Result.PopularData item) {
    Intent intent = new Intent(this, DetailActivity.class);
    intent.putExtra("FEED_ID", item.feedId);
    this.startActivity(intent);
  }
}
