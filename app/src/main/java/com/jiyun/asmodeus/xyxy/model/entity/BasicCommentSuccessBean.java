package com.jiyun.asmodeus.xyxy.model.entity;

public class BasicCommentSuccessBean {

    /**
     * code : 0
     * data : {}
     * message : string
     */

    private int code;
    private WokDetailBean.DataBean.CommentsBean.ListBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public WokDetailBean.DataBean.CommentsBean.ListBean getData() {
        return data;
    }

    public void setData(WokDetailBean.DataBean.CommentsBean.ListBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
