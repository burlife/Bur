package com.example.a1.zhoumi2080806.view;

import com.example.a1.zhoumi2080806.bean.NewsBean;

/**
 * Created by 1 on 2018/8/5.
 */

public interface INewsView {
    void onSuccess(NewsBean newsBean);
    void onError(int code);
}
