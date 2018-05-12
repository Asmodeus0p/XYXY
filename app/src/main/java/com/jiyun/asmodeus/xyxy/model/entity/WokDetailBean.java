package com.jiyun.asmodeus.xyxy.model.entity;

import java.util.List;

public class WokDetailBean {


    /**
     * code : 0
     * message : 成功
     * data : {"comments":{"pageNum":1,"pageSize":20,"size":0,"startRow":0,"endRow":0,"total":0,"pages":0,"list":[],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[],"navigateFirstPage":0,"navigateLastPage":0,"firstPage":0,"lastPage":0},"homewok":{"tUserType":null,"coverImg":null,"praiseNum":0,"source":"求教作品","isPeep":0,"content":"海军是我大爷现在沙滩上，眺望着天际。","studentId":73,"duration":null,"path":"http://qiniu.5roo.com/lo6OI1qUHbw6Ag5Iow4O0ftwHk0w","peepPrice":19,"tPhoto":null,"answerContent":null,"answerPermission":null,"worksType":"视频","giftNum":0,"nickname":"xyxy","id":23,"tRealname":null,"createDate":1510985128000,"answerCoverImg":null,"answerDate":null,"photo":"http://image.5roo.com/uwo-file/fast/data/00/A1/ctcYLloPwu6ARXW1AAAXBjxZVsQ924.jpg","majorIds":"1,3","commentNum":0,"tIntro":null,"answerWorksType":null,"answerDuration":null,"tUserId":null,"isPraise":0,"status":0}}
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


        private CommentsBean comments;
        private HomewokBean homewok;
        private List<RewardUserListBean> rewardUserList;

        public CommentsBean getComments() {
            return comments;
        }

        public void setComments(CommentsBean comments) {
            this.comments = comments;
        }

        public HomewokBean getHomewok() {
            return homewok;
        }

        public void setHomewok(HomewokBean homewok) {
            this.homewok = homewok;
        }

        public List<RewardUserListBean> getRewardUserList() {
            return rewardUserList;
        }

        public void setRewardUserList(List<RewardUserListBean> rewardUserList) {
            this.rewardUserList = rewardUserList;
        }

        public static class CommentsBean {


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
            private List<?> navigatepageNums;

            public static class ListBean {

                private String nickname;

                private String toNickname;

                private String realname;

                private int replyNum;

                private String photo;

                private int isPraise;

                private int praiseNum;

                private int id;

                private int toId;

                private int userId;

                private String content;

                private long createDate;

                private int userType;

                private String replyName;

                private int replyId;

                public long getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(long createDate) {
                    this.createDate = createDate;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getPraiseNum() {
                    return praiseNum;
                }

                public void setPraiseNum(int praiseNum) {
                    this.praiseNum = praiseNum;
                }

                public int getIsPraise() {
                    return isPraise;
                }

                public void setIsPraise(int isPraise) {
                    this.isPraise = isPraise;
                }

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }

                public int getReplyNum() {
                    return replyNum;
                }

                public void setReplyNum(int replyNum) {
                    this.replyNum = replyNum;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getUserType() {
                    return userType;
                }

                public void setUserType(int userType) {
                    this.userType = userType;
                }

                public int getToId() {
                    return toId;
                }

                public void setToId(int toId) {
                    this.toId = toId;
                }

                public String getToNickname() {
                    return toNickname;
                }

                public void setToNickname(String toNickname) {
                    this.toNickname = toNickname;
                }

                public String getRealname() {
                    return realname;
                }

                public void setRealname(String realname) {
                    this.realname = realname;
                }

                public int getReplyId() {
                    return replyId;
                }

                public void setReplyId(int replyId) {
                    this.replyId = replyId;
                }

                public String getReplyName() {
                    return replyName;
                }

                public void setReplyName(String replyName) {
                    this.replyName = replyName;
                }
            }


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

            public List<?> getNavigatepageNums() {
                return navigatepageNums;
            }

            public void setNavigatepageNums(List<?> navigatepageNums) {
                this.navigatepageNums = navigatepageNums;
            }
        }

        public static class RewardUserListBean {

            private int userId;
            private String userName;
            private String userPhoto;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

        }

        public static class HomewokBean {


            private int tUserType;
            private String coverImg;
            private int praiseNum;
            private String source;
            private int isPeep;
            private String content;
            private int studentId;
            private String duration;
            private String path;
            private double peepPrice;
            private String tPhoto;
            private String answerContent;
            private int answerPermission;
            private String worksType;
            private int giftNum;
            private String nickname;
            private int id;
            private String tRealname;
            private long createDate;
            private String answerCoverImg;
            private long answerDate;
            private String photo;
            private String majorIds;
            private int commentNum;
            private String tIntro;
            private String answerWorksType;
            private String answerDuration;
            private int tUserId;
            private int isPraise;
            private int status;
            private String answerPath;
            private String picProperty;
            private String answerPicProperty;

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

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
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

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public double getPeepPrice() {
                return peepPrice;
            }

            public void setPeepPrice(double peepPrice) {
                this.peepPrice = peepPrice;
            }

            public String getTPhoto() {
                return tPhoto;
            }

            public void setTPhoto(String tPhoto) {
                this.tPhoto = tPhoto;
            }

            public String getAnswerContent() {
                return answerContent;
            }

            public void setAnswerContent(String answerContent) {
                this.answerContent = answerContent;
            }

            public int getAnswerPermission() {
                return answerPermission;
            }

            public void setAnswerPermission(int answerPermission) {
                this.answerPermission = answerPermission;
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

            public String getAnswerCoverImg() {
                return answerCoverImg;
            }

            public void setAnswerCoverImg(String answerCoverImg) {
                this.answerCoverImg = answerCoverImg;
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

            public String getMajorIds() {
                return majorIds;
            }

            public void setMajorIds(String majorIds) {
                this.majorIds = majorIds;
            }

            public int getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(int commentNum) {
                this.commentNum = commentNum;
            }

            public String getTIntro() {
                return tIntro;
            }

            public void setTIntro(String tIntro) {
                this.tIntro = tIntro;
            }

            public String getAnswerWorksType() {
                return answerWorksType;
            }

            public void setAnswerWorksType(String answerWorksType) {
                this.answerWorksType = answerWorksType;
            }

            public String getAnswerDuration() {
                return answerDuration;
            }

            public void setAnswerDuration(String answerDuration) {
                this.answerDuration = answerDuration;
            }

            public int getTUserId() {
                return tUserId;
            }

            public void setTUserId(int tUserId) {
                this.tUserId = tUserId;
            }

            public int getIsPraise() {
                return isPraise;
            }

            public void setIsPraise(int isPraise) {
                this.isPraise = isPraise;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getAnswerPath() {
                return answerPath;
            }

            public void setAnswerPath(String answerPath) {
                this.answerPath = answerPath;
            }

            public String getPicProperty() {
                return picProperty;
            }

            public void setPicProperty(String picProperty) {
                this.picProperty = picProperty;
            }

            public String getAnswerPicProperty() {
                return answerPicProperty;
            }

            public void setAnswerPicProperty(String answerPicProperty) {
                this.answerPicProperty = answerPicProperty;
            }
        }
    }
}
