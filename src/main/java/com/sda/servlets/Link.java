package com.sda.servlets;

public class Link {
    private String url;
    private String text;

    public Link(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }
}
