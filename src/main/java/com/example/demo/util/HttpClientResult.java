package com.example.demo.util;

import java.io.Serializable;

public class HttpClientResult<T> implements Serializable {
    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private T content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public HttpClientResult() {
        super();
    }

    public HttpClientResult(int code) {
        super();
        this.code = code;
    }

    public HttpClientResult(int code, T content) {
        super();
        this.code = code;
        this.content = content;
    }

    @Override
    public String toString() {
        return "HttpClientResult [code=" + code + ", content=" + content + "]";
    }
}
