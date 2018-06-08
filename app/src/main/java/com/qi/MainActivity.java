package com.qi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Creator  liuqi
 * Data     2018/4/24
 * Class    com.qi.MainActivity
 */
public class MainActivity extends AppCompatActivity implements RefreshRecyclerView.OnRefreshListener {

    RefreshRecyclerView recyclerView;
    ExecutorService executorService = Executors.newCachedThreadPool();
    private MyAdapter adapter;
    private TextView mFooter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFooter = new TextView(this);
        mFooter.setText("底部啦");
        mFooter.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        mFooter.setPadding(0, 16, 0, 16);
        mFooter.setGravity(Gravity.CENTER);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mFooter.setLayoutParams(layoutParams);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setOnRefreshListener(this);
        recyclerView.setFooter(mFooter);
        ProgressBar headerView = new ProgressBar(this);
        recyclerView.setHeader(headerView);
    }


    @Override
    public void onRefresh() {
        mFooter.setText("正在加载");
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addItems();
                            recyclerView.finishRefresh();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onFinish() {
        mFooter.setText("加载完毕");
    }
}
