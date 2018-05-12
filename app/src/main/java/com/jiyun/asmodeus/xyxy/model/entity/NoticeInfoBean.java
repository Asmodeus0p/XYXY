package com.jiyun.asmodeus.xyxy.model.entity;

public class NoticeInfoBean {

    /**
     * code : 0
     * message : 成功
     * data : {"address":"","coverImg":"http://img1.3lian.com/2015/w22/96/d/61.jpg","endDate":1509800359000,"collegeIds":"7","reservationNum":0,"mobile":null,"isReservation":0,"title":"舞蹈体验课","majorIds":"2","subscribeNum":12,"price":100,"id":8,"favorite":0,"startDate":1509713959000}
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
         * address :
         * coverImg : http://img1.3lian.com/2015/w22/96/d/61.jpg
         * endDate : 1509800359000
         * collegeIds : 7
         * reservationNum : 0
         * mobile : null
         * isReservation : 0
         * title : 舞蹈体验课
         * majorIds : 2
         * subscribeNum : 12
         * price : 100
         * id : 8
         * favorite : 0
         * startDate : 1509713959000
         */

        private String address;
        private String coverImg;
        private long endDate;
        private String collegeIds;
        private int reservationNum;
        private String mobile;
        private int isReservation;
        private String title;
        private String majorIds;
        private int subscribeNum;
        private double price;
        private int id;
        private int favorite;
        private long startDate;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

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

        public String getCollegeIds() {
            return collegeIds;
        }

        public void setCollegeIds(String collegeIds) {
            this.collegeIds = collegeIds;
        }

        public int getReservationNum() {
            return reservationNum;
        }

        public void setReservationNum(int reservationNum) {
            this.reservationNum = reservationNum;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIsReservation() {
            return isReservation;
        }

        public void setIsReservation(int isReservation) {
            this.isReservation = isReservation;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMajorIds() {
            return majorIds;
        }

        public void setMajorIds(String majorIds) {
            this.majorIds = majorIds;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFavorite() {
            return favorite;
        }

        public void setFavorite(int favorite) {
            this.favorite = favorite;
        }

        public long getStartDate() {
            return startDate;
        }

        public void setStartDate(long startDate) {
            this.startDate = startDate;
        }
    }
}
