package com.jiyun.asmodeus.xyxy.model.entity;


public class LiveDetailBean {


    /**
     * code : 0
     * message : 成功
     * data : {"coverImg":"http://img1.3lian.com/2015/a1/136/d/242.jpg","endDate":1510387638000,"subscribe":1,"photo":null,"replay":8000,"title":"舞蹈直播","type":"讲堂","isSubscribe":0,"content":"666666666","realname":"王亮","majorIds":"2,3","teacherId":1,"subscribeNum":100,"price":2000,"intro":null,"nickname":"王老师","attention":0,"id":1,"userType":null,"favorite":0,"startDate":1510304943000}
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


        private String coverImg;
        private long endDate;
        private int subscribe;
        private String photo;
        private double replay;
        private String title;
        private String type;
        private int isSubscribe;
        private String content;
        private String realname;
        private String majorIds;
        private int teacherId;
        private int subscribeNum;
        private double price;
        private String intro;
        private String nickname;
        private int attention;
        private int id;
        private int userType;
        private int isFavorite;
        private long startDate;
        private int liveStatus;
        private String replayPath;

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public long getEndDate() {
            return endDate;
        }

        public void setEndDate(long endDate) {
            this.endDate = endDate;
        }

        public int getSubscribe() {
            return subscribe;
        }

        public void setSubscribe(int subscribe) {
            this.subscribe = subscribe;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public double getReplay() {
            return replay;
        }

        public void setReplay(double replay) {
            this.replay = replay;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getIsSubscribe() {
            return isSubscribe;
        }

        public void setIsSubscribe(int isSubscribe) {
            this.isSubscribe = isSubscribe;
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

        public int getSubscribeNum() {
            return subscribeNum;
        }

        public void setSubscribeNum(int subscribeNum) {
            this.subscribeNum = subscribeNum;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getAttention() {
            return attention;
        }

        public void setAttention(int attention) {
            this.attention = attention;
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

        public int getFavorite() {
            return isFavorite;
        }

        public void setFavorite(int isFavorite) {
            this.isFavorite = isFavorite;
        }

        public long getStartDate() {
            return startDate;
        }

        public void setStartDate(long startDate) {
            this.startDate = startDate;
        }

        public int getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(int liveStatus) {
            this.liveStatus = liveStatus;
        }

        public String getReplayPath() {
            return replayPath;
        }

        public void setReplayPath(String replayPath) {
            this.replayPath = replayPath;
        }
    }
}
