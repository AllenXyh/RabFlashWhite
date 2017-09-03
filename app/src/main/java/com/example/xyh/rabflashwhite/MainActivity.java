package com.example.xyh.rabflashwhite;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.feezu.liuli.timeselector.TimeSelector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int mSelect = 0;   //选中项
    RecyclerView mRecyclerView;
    private List<Money> mGalleries = new ArrayList<Money>();
    CommonAdapter<Money> mAdapter;
    private TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_time= (TextView) findViewById(R.id.tv_time);
        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInitView();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        Money money = new Money(500, "500");
        Money money1 = new Money(800, "800");
        Money money2 = new Money(1000, "1000");
        Money money3 = new Money(1500, "1500");
        mGalleries.add(money);
        mGalleries.add(money1);
        mGalleries.add(money2);
        mGalleries.add(money3);


        mAdapter = new CommonAdapter<Money>(this, R.layout.item_list, mGalleries) {

            @Override
            protected void convert(final ViewHolder holder, Money money, final int position) {
                holder.setText(R.id.id_item_list_title, money.getMoney() + "");
                if (mSelect == position) {
                    holder.itemView.setBackground(getResources().getDrawable(R.color.colorAccent));
                } else {
                    holder.itemView.setBackground(getResources().getDrawable(R.color.colorPrimary));
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeSelected(position);//刷新
                    }
                });

            }
        };
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(3));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getInitView() {
        TimeSelector timeSelector = new TimeSelector(MainActivity.this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                tv_time.setText(time);
                Toast.makeText(MainActivity.this, time, Toast.LENGTH_SHORT).show();
            }
        }, "2015-01-01 00:00", "2018-12-31 23:59:59");
        //限制选择小时时段
//        TimeSelector timeSelector = new TimeSelector(MainActivity.this, new TimeSelector.ResultHandler() {
//            @Override
//            public void handle(String time) {
//                Toast.makeText(MainActivity.this, time, Toast.LENGTH_SHORT).show();
//            }
//        }, "2015-01-01 00:00", "2018-12-31 23:59:59", "9:00", "17:00");

        timeSelector.setIsLoop(false);//设置不循环,true循环
        timeSelector.setMode(TimeSelector.MODE.YMDHM);//显示 年月日时分（默认）
//        timeSelector.setMode(TimeSelector.MODE.YMD);//只显示 年月日
        timeSelector.show();
}

    public void changeSelected(int positon) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon;
            mAdapter.notifyDataSetChanged();
        }
    }
}
