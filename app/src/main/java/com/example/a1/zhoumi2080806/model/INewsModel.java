package com.example.a1.zhoumi2080806.model;

import com.example.a1.zhoumi2080806.bean.NewsBean;

/**
 * Created by 1 on 2018/8/6.
 */

public interface INewsModel {
    void onSetSuccess(NewsBean newsBean);
    void onSetError(int code);
}
