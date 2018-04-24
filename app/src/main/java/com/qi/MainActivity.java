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
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RefreshAdapter adapter = new RefreshAdapter() {
            @Override
            public RecyclerView.ViewHolder onNormalCreateViewHolder(ViewGroup parent) {
                return new MainActivity.ViewHolder(new TextView(parent.getContext()));
            }

            @Override
            protected void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position) {
                TextView itemView = (TextView) holder.itemView;
                itemView.setText(data[position] + "---------" + position);
            }

            @Override
            protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getDataCount() {
                return data.length;
            }
        };
        adapter.setFooter(null);
        adapter.setHeader(null);
        recyclerView.setAdapter(adapter);
        try {
            RefreshManager.newInstance(this, new RefreshManager.OnLoadListener() {
                @Override
                public void load() {

                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
