package com.example.a1.zhoumi2080806.presenter;

import com.example.a1.zhoumi2080806.bean.NewsBean;
import com.example.a1.zhoumi2080806.model.INewsModel;
import com.example.a1.zhoumi2080806.model.NewsModel;
import com.example.a1.zhoumi2080806.view.INewsView;

/**
 * Created by 1 on 2018/8/6.
 */

public class Ipresenter implements INewsPresenter{
    private INewsView iNewsView;
    private NewsModel newsModel;
    public Ipresenter(INewsView iNewsView){
        this.iNewsView=iNewsView;
        this.newsModel=new NewsModel();
    }
    public  void setDatas(int page){
        newsModel.getDatas(new INewsModel() {
            @Override
            public void onSetSuccess(NewsBean newsBean) {
                iNewsView.onSuccess(newsBean);
            }

            @Override
            public void onSetError(int code) {
iNewsView.onError(code);
            }
        },page);
    }
    @Override
    public void Destorys() {
if (iNewsView!=null){
    iNewsView=null;
}
    }
}
