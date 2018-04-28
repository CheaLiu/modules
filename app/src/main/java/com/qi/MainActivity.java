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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Creator  liuqi
 * Data     2018/4/24
 * Class    com.qi.MainActivity
 */
public class MainActivity extends AppCompatActivity implements RefreshManager.OnLoadListener {

    RefreshRecyclerView recyclerView;
    ExecutorService executorService = Executors.newCachedThreadPool();
    ArrayList data;
    RefreshManager refreshManager;

    {
        data = new ArrayList();
        data.add("你好吗");
        data.add("你好吗");
        data.add("你好吗");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView footer = new TextView(this);
        footer.setText("底部啦");
        footer.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        footer.setPadding(0, 16, 0, 16);
        footer.setGravity(Gravity.CENTER);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        footer.setLayoutParams(layoutParams);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RefreshAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
        refreshManager = new RefreshManager.Builder()
                .recyclerView(recyclerView)
                .header(View.inflate(this, R.layout.view_header, null))
                .footer(footer)
                .loadListener(this)
                .auto(false)
                .builder();
    }

    @Override
    public void onLoad() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (data.size() % 2 == 0)
                                refreshManager.loadFail();
                            data.add("你好吗");
                            data.add("你好吗");
                            data.add("你好吗");
                            refreshManager.loadSuccess();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
