package com.example.a1.zhoumi2080806;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.a1.zhoumi2080806.bean.EventBusBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.y_title)
    TextView yTitle;
    @BindView(R.id.y_simple)
    SimpleDraweeView ySimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ySimple.setImageURI("https://p.ssl.qhimg.com/dmfd/420_627_/t01ccdc27d55aad46a4.jpg");
        EventBus.getDefault().register(this);
    }
    @OnClick(R.id.y_title)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, SencondActivity.class));
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void user(EventBusBean eventBean){
        //赋值
        ySimple.setImageURI(eventBean.getUrl().toString());
        yTitle.setText(eventBean.getTitle().toString());
    }
    @Override
    protected void onDestroy() {
        //销毁
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
