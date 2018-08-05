package com.example.a1.zhoumi2080806;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.a1.zhoumi2080806.adapter.MyAdapter;
import com.example.a1.zhoumi2080806.bean.EventBusBean;
import com.example.a1.zhoumi2080806.bean.NewsBean;
import com.example.a1.zhoumi2080806.bean.UserBean;
import com.example.a1.zhoumi2080806.presenter.Ipresenter;
import com.example.a1.zhoumi2080806.view.INewsView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 1 on 2018/8/6.
 */

public class SencondActivity extends AppCompatActivity implements INewsView{
    private  int page=1;
    private Ipresenter ipresenter;
    @BindView(R.id.recy_view)
    RecyclerView recy_view;
    @BindView(R.id.pull_to_refesh)
    PullToRefreshScrollView pullToRefesh;
    private MyAdapter myAdapter;
    private List<NewsBean.ResultsBean> results;
    private  boolean flag;
    private List<UserBean> userBeanList;
    private List<UserBean> userList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);
        ButterKnife.bind(this);
        isNetworkAvalible(SencondActivity.this);
        initView();
    }

    private void initView() {
        //recyView.setLayoutManager(new LinearLayoutManager(SencondActivity.this,LinearLayoutManager.VERTICAL,false));
        recy_view.setLayoutManager(new LinearLayoutManager(SencondActivity.this,LinearLayoutManager.VERTICAL,false));
        pullToRefesh.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefesh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page=1;
                ipresenter.setDatas(page);
                pullToRefesh.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page++;
                ipresenter.setDatas(page);
                pullToRefesh.onRefreshComplete();
            }
        });
        ipresenter=new Ipresenter(this);
    }

    public boolean isNetworkAvalible(Context context) {
        // 获得网络状态管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            Toast.makeText(context, "没网", Toast.LENGTH_SHORT).show();
            flag = false;
            return false;
        } else {
            NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
            if (allNetworkInfo != null) {
                for (int i = 0; i < allNetworkInfo.length; i++) {
                    // 判断获得的网络状态是否是处于连接状态
                    if (allNetworkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        Toast.makeText(context, "有网", Toast.LENGTH_SHORT).show();
                        // showPresenter.setDatas(page);
                        flag = true;
                        return true;
                    }
                }
            }
        }
        return false;

    }

    @Override
    public void onSuccess(final NewsBean newsBean) {
        results = newsBean.getResults();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myAdapter = new MyAdapter(SencondActivity.this, newsBean.getResults());
                recy_view.setAdapter( myAdapter);
                myAdapter.setItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onClickListener(View view, int postion) {
                        //发送
                        EventBus.getDefault().post(new EventBusBean(newsBean.getResults().get(postion).getUrl(),newsBean.getResults().get(postion).getWho()));
                        finish();
                    }
                });
            }
        });
    }

    @Override
    public void onError(int code) {

    }
}
