package com.sda.servlets.links;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {
    private Integer id;
    private String url;
    private String text;

    @JsonCreator
    public Link(
            @JsonProperty("id") Integer id,
            @JsonProperty("url") String url,
            @JsonProperty("text") String text) {
        this.id = id;
        this.url = url;
        this.text = text;
    }

    public Link(String url, String text) {
       this(null, url, text);
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setText(String text) {
        this.text = text;
    }
}
