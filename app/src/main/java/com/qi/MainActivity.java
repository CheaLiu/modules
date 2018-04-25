package com.qi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Creator  liuqi
 * Data     2018/4/24
 * Class    com.qi.MainActivity
 */
public class MainActivity extends AppCompatActivity {

    @Refresh
    RecyclerView recyclerView;
    String[] data = new String[]{
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗",
            "你好吗"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView footer = new TextView(this);
        footer.setText("底部啦");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RefreshAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
        try {
            RefreshManager.newInstance(this, null, footer, new RefreshManager.OnLoadListener() {
                @Override
                public void load() {

                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
