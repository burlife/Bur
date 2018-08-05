package com.example.a1.zhoumi2080806.bean;

/**
 * Created by 1 on 2018/8/6.
 */

public class EventBusBean {
    private String url;
    private String title;

    public EventBusBean(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public EventBusBean() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
