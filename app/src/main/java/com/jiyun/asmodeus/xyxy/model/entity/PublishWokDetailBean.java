package com.jiyun.asmodeus.xyxy.model.entity;


public class PublishWokDetailBean {


    /**
     * code : 0
     * message : 成功
     * data : {"majorIds":"表演,舞蹈","teacherId":1,"intro":null,"id":1,"userType":null,"source":"求教作品","title":"表演一个小狗","content":"我表演的神剧","realname":"王亮"}
     */

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
         * majorIds : 表演,舞蹈
         * teacherId : 1
         * intro : null
         * id : 1
         * userType : null
         * source : 求教作品
         * title : 表演一个小狗
         * content : 我表演的神剧
         * realname : 王亮
         */

        private String majorIds;
        private int teacherId;
        private String intro;
        private int id;
        private double price;
        private int userType;
        private String source;
        private String title;
        private String content;
        private String realname;

        public String getMajorIds() {
            return majorIds;
        }

        public void setMajorIds(String majorIds) {
            this.majorIds = majorIds;
        }

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
