package com.jiyun.asmodeus.xyxy.model.entity;


public class GetUpLoadModel {



    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : vf223GmIhu8jCWU_hOCj3dVkdT-9IcdgUl0pYvbl:5bp-eSAvV4bJAoxLhGA-Ka8F9kA=:eyJzY29wZSI6InVuaXZzdGFyIiwiY2FsbGJhY2tVcmwiOiJodHRwOi8vYXJ0bGl2ZS41cm9vLmNvbS92MS9hcGkvcmVjZWl2ZU1lc3NhZ2UiLCJkZWFkbGluZSI6MTUxMDMzMzk4OCwiY2FsbGJhY2tCb2R5IjoiZmlsZW5hbWVcdTAwM2QkKGZuYW1lKVx1MDAyNmZpbGVzaXplXHUwMDNkJChmc2l6ZSkifQ==
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
