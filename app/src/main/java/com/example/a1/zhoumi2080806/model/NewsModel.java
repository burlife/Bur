package com.example.a1.zhoumi2080806.model;

import com.example.a1.zhoumi2080806.bean.NewsBean;
import com.example.a1.zhoumi2080806.utils.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 1 on 2018/8/6.
 */

public class NewsModel {
    private String api="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/";
  public void getDatas(final INewsModel iNewsModel,int page){
      HttpUtils.doGet(api + page, new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {
              iNewsModel.onSetError(1);
          }

          @Override
          public void onResponse(Call call, Response response) throws IOException {
                 String string=response.body().string();
              Gson gson=new Gson();
              NewsBean newsBean=gson.fromJson(string,NewsBean.class);
              iNewsModel.onSetSuccess(newsBean);
          }
      });
  }
}
