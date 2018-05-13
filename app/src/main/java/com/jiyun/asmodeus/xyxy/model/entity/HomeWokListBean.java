package com.jiyun.asmodeus.xyxy.model.entity;


import java.util.List;

public class HomeWokListBean {


    /**
     * code : 0
     * message : 成功
     * data : {"pageNum":1,"pageSize":20,"size":3,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"tUserType":null,"coverImg":null,"answerDate":null,"photo":"http://image.5roo.com/uwo-file/fast/data/00/A1/ctcYLloL8auAOUKnAAAXBjxZVsQ145.jpg","praiseNum":0,"isPeep":0,"content":"带你去看蓝色的土耳其","studentId":63,"majorIds":"2,3","duration":null,"commentNum":0,"path":"FscBoFLeKK5iFiXce3Kp2mNWbd4N","tIntro":null,"tPhoto":null,"worksType":"音频","giftNum":0,"nickname":"xyxy","tUserId":null,"isPraise":0,"id":20,"pushDate":null,"tRealname":null,"createDate":1510911810000},{"tUserType":null,"coverImg":null,"answerDate":null,"photo":"http://image.5roo.com/uwo-file/fast/data/00/A1/ctcYLloL8auAOUKnAAAXBjxZVsQ145.jpg","praiseNum":0,"isPeep":0,"content":"少年，放下手机看看外面，那是朕的江山啊。","studentId":63,"majorIds":"1,5,4","duration":null,"commentNum":0,"path":"Fu6ymJlZDPh6cSRpxJvENrRAdk23","tIntro":null,"tPhoto":null,"worksType":"图片","giftNum":0,"nickname":"xyxy","tUserId":null,"isPraise":0,"id":19,"pushDate":null,"tRealname":null,"createDate":1510911666000},{"tUserType":null,"coverImg":null,"answerDate":null,"photo":"http://image.5roo.com/uwo-file/fast/data/00/A1/ctcYLloL8auAOUKnAAAXBjxZVsQ145.jpg","praiseNum":0,"isPeep":0,"content":"这是我拍摄的一部视频舞蹈，介绍的是海军绿的日常，像个猪一样吃了睡，睡了吃，日复一日年复一年，很强势。","studentId":63,"majorIds":"1,2","duration":null,"commentNum":0,"path":"lo6OI1qUHbw6Ag5Iow4O0ftwHk0w","tIntro":null,"tPhoto":null,"worksType":"视频","giftNum":0,"nickname":"xyxy","tUserId":null,"isPraise":0,"id":18,"pushDate":null,"tRealname":null,"createDate":1510911623000}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * size : 3
         * startRow : 1
         * endRow : 3
         * total : 3
         * pages : 1
         * list : [{"tUserType":null,"coverImg":null,"answerDate":null,"photo":"http://image.5roo.com/uwo-file/fast/data/00/A1/ctcYLloL8auAOUKnAAAXBjxZVsQ145.jpg","praiseNum":0,"isPeep":0,"content":"带你去看蓝色的土耳其","studentId":63,"majorIds":"2,3","duration":null,"commentNum":0,"path":"FscBoFLeKK5iFiXce3Kp2mNWbd4N","tIntro":null,"tPhoto":null,"worksType":"音频","giftNum":0,"nickname":"xyxy","tUserId":null,"isPraise":0,"id":20,"pushDate":null,"tRealname":null,"createDate":1510911810000},{"tUserType":null,"coverImg":null,"answerDate":null,"photo":"http://image.5roo.com/uwo-file/fast/data/00/A1/ctcYLloL8auAOUKnAAAXBjxZVsQ145.jpg","praiseNum":0,"isPeep":0,"content":"少年，放下手机看看外面，那是朕的江山啊。","studentId":63,"majorIds":"1,5,4","duration":null,"commentNum":0,"path":"Fu6ymJlZDPh6cSRpxJvENrRAdk23","tIntro":null,"tPhoto":null,"worksType":"图片","giftNum":0,"nickname":"xyxy","tUserId":null,"isPraise":0,"id":19,"pushDate":null,"tRealname":null,"createDate":1510911666000},{"tUserType":null,"coverImg":null,"answerDate":null,"photo":"http://image.5roo.com/uwo-file/fast/data/00/A1/ctcYLloL8auAOUKnAAAXBjxZVsQ145.jpg","praiseNum":0,"isPeep":0,"content":"这是我拍摄的一部视频舞蹈，介绍的是海军绿的日常，像个猪一样吃了睡，睡了吃，日复一日年复一年，很强势。","studentId":63,"majorIds":"1,2","duration":null,"commentNum":0,"path":"lo6OI1qUHbw6Ag5Iow4O0ftwHk0w","tIntro":null,"tPhoto":null,"worksType":"视频","giftNum":0,"nickname":"xyxy","tUserId":null,"isPraise":0,"id":18,"pushDate":null,"tRealname":null,"createDate":1510911623000}]
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

            private int userType;
            private int tUserType;
            private String coverImg;
            private long answerDate;
            private String photo;
            private String source;
            private int praiseNum;
            private int isPeep;
            private String content;
            private int studentId;
            private String majorIds;
            private String duration;
            private int commentNum;
            private String path;
            private String tIntro;
            private String tPhoto;
            private String worksType;
            private int giftNum;
            private String nickname;
            private Integer tUserId;
            private int isPraise;
            private int id;
            private String tRealname;
            private long createDate;
            private double peepPrice;
            private int answerPermission;
            private String picProperty;
            private int isAnswer;

            public int getTUserType() {
                return tUserType;
            }

            public void setTUserType(int tUserType) {
                this.tUserType = tUserType;
            }

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public long getAnswerDate() {
                return answerDate;
            }

            public void setAnswerDate(long answerDate) {
                this.answerDate = answerDate;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
            }

            public int getIsPeep() {
                return isPeep;
            }

            public void setIsPeep(int isPeep) {
                this.isPeep = isPeep;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStudentId() {
                return studentId;
            }

            public void setStudentId(int studentId) {
                this.studentId = studentId;
            }

            public String getMajorIds() {
                return majorIds;
            }

            public void setMajorIds(String majorIds) {
                this.majorIds = majorIds;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public int getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(int commentNum) {
                this.commentNum = commentNum;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getTIntro() {
                return tIntro;
            }

            public void setTIntro(String tIntro) {
                this.tIntro = tIntro;
            }

            public String getTPhoto() {
                return tPhoto;
            }

            public void setTPhoto(String tPhoto) {
                this.tPhoto = tPhoto;
            }

            public String getWorksType() {
                return worksType;
            }

            public void setWorksType(String worksType) {
                this.worksType = worksType;
            }

            public int getGiftNum() {
                return giftNum;
            }

            public void setGiftNum(int giftNum) {
                this.giftNum = giftNum;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public Integer getTUserId() {
                return tUserId;
            }

            public void setTUserId(Integer tUserId) {
                this.tUserId = tUserId;
            }

            public int getIsPraise() {
                return isPraise;
            }

            public void setIsPraise(int isPraise) {
                this.isPraise = isPraise;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTRealname() {
                return tRealname;
            }

            public void setTRealname(String tRealname) {
                this.tRealname = tRealname;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public double getPeepPrice() {
                return peepPrice;
            }

            public void setPeepPrice(double peepPrice) {
                this.peepPrice = peepPrice;
            }

            public int getAnswerPermission() {
                return answerPermission;
            }

            public void setAnswerPermission(int answerPermission) {
                this.answerPermission = answerPermission;
            }

            public String getPicProperty() {
                return picProperty;
            }

            public void setPicProperty(String picProperty) {
                this.picProperty = picProperty;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public int getIsAnswer() {
                return isAnswer;
            }

            public void setIsAnswer(int isAnswer) {
                this.isAnswer = isAnswer;
            }
        }
    }
}
