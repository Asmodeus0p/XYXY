package com.jiyun.asmodeus.xyxy.model.entity;

import java.util.List;


public class LivingListBean {


    /**
     * code : 0
     * message : 成功
     * data : {"pageNum":1,"pageSize":20,"size":3,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"coverImg":"http://imgsrc.baidu.com/imgad/pic/item/5ab5c9ea15ce36d32ae0f90a31f33a87e950b120.jpg","endDate":1514534841000,"subscribe":1,"replay":8000,"title":"表演直播","type":"讲堂","isSubscribe":0,"realname":"师大路","majorIds":"2,3","teacherId":2,"subscribeNum":50,"price":800,"nickname":"师老师","id":2,"userType":2,"startDate":1509095350000},{"coverImg":"http://img3.imgtn.bdimg.com/it/u=4187445916,507368304&fm=214&gp=0.jpg","endDate":1511597249000,"subscribe":0,"replay":8000,"title":"声乐直播","type":"讲堂","isSubscribe":0,"realname":"刘莉莉","majorIds":"3","teacherId":3,"subscribeNum":20,"price":500,"nickname":"刘老师","id":3,"userType":3,"startDate":1509958382000},{"coverImg":"http://img1.3lian.com/2015/a1/136/d/242.jpg","endDate":1510387638000,"subscribe":1,"replay":8000,"title":"舞蹈直播","type":"讲堂","isSubscribe":0,"realname":"王亮","majorIds":"2,3","teacherId":1,"subscribeNum":100,"price":2000,"nickname":"王老师","id":1,"userType":1,"startDate":1510304943000}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}
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


        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int lastPage;
        private int firstPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * coverImg : http://imgsrc.baidu.com/imgad/pic/item/5ab5c9ea15ce36d32ae0f90a31f33a87e950b120.jpg
             * endDate : 1514534841000
             * subscribe : 1
             * replay : 8000
             * title : 表演直播
             * type : 讲堂
             * isSubscribe : 0
             * realname : 师大路
             * majorIds : 2,3
             * teacherId : 2
             * subscribeNum : 50
             * price : 800
             * nickname : 师老师
             * id : 2
             * userType : 2
             * startDate : 1509095350000
             */

            private String coverImg;
            private long endDate;
            private int subscribe;
            private double replay;
            private String title;
            private String type;
            private int isSubscribe;
            private String realname;
            private String majorIds;
            private int teacherId;
            private int subscribeNum;
            private double price;
            private String nickname;
            private int id;
            private int userType;
            private long startDate;
            private int liveStatus;


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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
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
        }
    }
}
