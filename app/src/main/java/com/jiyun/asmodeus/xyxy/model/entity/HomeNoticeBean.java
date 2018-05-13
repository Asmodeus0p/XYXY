package com.jiyun.asmodeus.xyxy.model.entity;

import java.util.List;

/**
 * Created by vicoltree on 17/11/7.
 */

public class HomeNoticeBean {


    /**
     * code : 0
     * message : 成功
     * data : {"pageNum":1,"pageSize":20,"size":9,"startRow":1,"endRow":9,"total":9,"pages":1,"list":[{"majorIds":"2","address":"","coverImg":"http://img1.3lian.com/2015/w22/96/d/61.jpg","endDate":1509800359000,"subscribeNum":12,"price":100,"collegeIds":"7","reservationNum":0,"id":8,"title":"舞蹈体验课","startDate":1509713959000},{"majorIds":"7","address":"","coverImg":"http://img15.3lian.com/2015/a1/15/d/62.jpg","endDate":1509721159000,"subscribeNum":20,"price":200,"collegeIds":"7","reservationNum":0,"id":9,"title":"美术课","startDate":1509713959000},{"majorIds":"3","address":"","coverImg":"http://pic.qiantucdn.com/58pic/26/19/12/58c5d6f206d5e_1024.jpg","endDate":1509724759000,"subscribeNum":100,"price":150,"collegeIds":"5","reservationNum":0,"id":10,"title":"声乐课","startDate":1509713959000},{"majorIds":"3","address":"","coverImg":"http://pic2.16pic.com/00/07/29/16pic_729715_b.jpg","endDate":1509717525000,"subscribeNum":211,"price":230,"collegeIds":"5","reservationNum":0,"id":11,"title":"声乐课","startDate":1509713925000},{"majorIds":"7","address":"222222","coverImg":"http://img1.3lian.com/2015/w22/96/d/61.jpg","endDate":1509519668000,"subscribeNum":12,"price":800,"collegeIds":"7","reservationNum":0,"id":7,"title":"7777","startDate":1509350103000},{"majorIds":"2,33","address":"发达十分大","coverImg":"http://img1.3lian.com/2015/w22/96/d/61.jpg","endDate":1510470053000,"subscribeNum":50,"price":800,"collegeIds":"2","reservationNum":0,"id":2,"title":"表演体验课","startDate":1509095350000},{"majorIds":"2,33","address":"666666666","coverImg":"http://img1.3lian.com/2015/w22/96/d/61.jpg","endDate":1509519649000,"subscribeNum":100,"price":2000,"collegeIds":"2","reservationNum":2,"id":1,"title":"舞蹈体验课程","startDate":1509095343000},{"majorIds":"2","address":"顶顶顶顶","coverImg":"http://img1.3lian.com/2015/w22/96/d/61.jpg","endDate":1515222063000,"subscribeNum":10,"price":700,"collegeIds":"3","reservationNum":0,"id":4,"title":"波音支持","startDate":1509094988000},{"majorIds":"3","address":"阿斯蒂芬大","coverImg":"http://img1.3lian.com/2015/w22/96/d/61.jpg","endDate":1514617258000,"subscribeNum":20,"price":500,"collegeIds":"5","reservationNum":0,"id":3,"title":"声乐体验课","startDate":1508403182000}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * pageNum : 1
         * pageSize : 20
         * size : 9
         * startRow : 1
         * endRow : 9
         * total : 9
         * pages : 1
         * list : [{"majorIds":"2","address":"","coverImg":"http://img1.3lian.com/2015/w22/96/d/61.jpg","endDate":1509800359000,"subscribeNum":12,"price":100,"collegeIds":"7","reservationNum":0,"id":8,"title":"舞蹈体验课","startDate":1509713959000},{"majorIds":"7","address":"","coverImg":"http://img15.3lian.com/2015/a1/15/d/62.jpg","endDate":1509721159000,"subscribeNum":20,"price":200,"collegeIds":"7","reservationNum":0,"id":9,"title":"美术课","startDate":1509713959000},{"majorIds":"3","address":"","coverImg":"http://pic.qiantucdn.com/58pic/26/19/12/58c5d6f206d5e_1024.jpg","endDate":1509724759000,"subscribeNum":100,"price":150,"collegeIds":"5","reservationNum":0,"id":10,"title":"声乐课","startDate":1509713959000},{"majorIds":"3","address":"","coverImg":"http://pic2.16pic.com/00/07/29/16pic_729715_b.jpg","endDate":1509717525000,"subscribeNum":211,"price":230,"collegeIds":"5","reservationNum":0,"id":11,"title":"声乐课","startDate":1509713925000},{"majorIds":"7","address":"222222","coverImg":"http://img1.3lian.com/2015/w22/96/d/61.jpg","endDate":1509519668000,"subscribeNum":12,"price":800,"collegeIds":"7","reservationNum":0,"id":7,"title":"7777","startDate":1509350103000},{"majorIds":"2,33","address":"发达十分大","coverImg":"http://img1.3lian.com/2015/w22/96/d/61.jpg","endDate":1510470053000,"subscribeNum":50,"price":800,"collegeIds":"2","reservationNum":0,"id":2,"title":"表演体验课","startDate":1509095350000},{"majorIds":"2,33","address":"666666666","coverImg":"http://img1.3lian.com/2015/w22/96/d/61.jpg","endDate":1509519649000,"subscribeNum":100,"price":2000,"collegeIds":"2","reservationNum":2,"id":1,"title":"舞蹈体验课程","startDate":1509095343000},{"majorIds":"2","address":"顶顶顶顶","coverImg":"http://img1.3lian.com/2015/w22/96/d/61.jpg","endDate":1515222063000,"subscribeNum":10,"price":700,"collegeIds":"3","reservationNum":0,"id":4,"title":"波音支持","startDate":1509094988000},{"majorIds":"3","address":"阿斯蒂芬大","coverImg":"http://img1.3lian.com/2015/w22/96/d/61.jpg","endDate":1514617258000,"subscribeNum":20,"price":500,"collegeIds":"5","reservationNum":0,"id":3,"title":"声乐体验课","startDate":1508403182000}]
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
         */

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
        private int firstPage;
        private int lastPage;
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

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
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
             * majorIds : 2
             * address :
             * coverImg : http://img1.3lian.com/2015/w22/96/d/61.jpg
             * endDate : 1509800359000
             * subscribeNum : 12
             * price : 100
             * collegeIds : 7
             * reservationNum : 0
             * id : 8
             * title : 舞蹈体验课
             * startDate : 1509713959000
             */

            private String majorIds;
            private String address;
            private String coverImg;
            private long endDate;
            private int subscribeNum;
            private double price;
            private String collegeIds;
            private int reservationNum;
            private int id;
            private String title;
            private long startDate;

            public String getMajorIds() {
                return majorIds;
            }

            public void setMajorIds(String majorIds) {
                this.majorIds = majorIds;
            }

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getStartDate() {
                return startDate;
            }

            public void setStartDate(long startDate) {
                this.startDate = startDate;
            }
        }
    }
}
