package com.jiyun.asmodeus.xyxy.model.entity;

import java.util.List;

public class MasterFindBean {
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

            private int id;
            private int page;
            private int rows;
            private Object pid;
            private String salt;
            private String nickname;
            private String realname;
            private String photo;
            private String images;
            private String intro;
            private String details;
            private String mobile;
            private String psw;
            private Object email;
            private int sex;
            private long birthday;
            private Object country;
            private Object city;
            private int userType;
            private Object post;
            private String college;
            private String major;
            private Object skilled;
            private Object ip;
            private long lastTime;
            private long createDate;
            private Object idcardFront;
            private Object idcardBack;
            private Object teachCard;
            private int isauth;
            private int pushHome;
            private long sortTime;
            private Object openid;
            private Object unionid;
            private Object qqUid;
            private Object sinaUid;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getRows() {
                return rows;
            }

            public void setRows(int rows) {
                this.rows = rows;
            }

            public Object getPid() {
                return pid;
            }

            public void setPid(Object pid) {
                this.pid = pid;
            }

            public String getSalt() {
                return salt;
            }

            public void setSalt(String salt) {
                this.salt = salt;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPsw() {
                return psw;
            }

            public void setPsw(String psw) {
                this.psw = psw;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public Object getCountry() {
                return country;
            }

            public void setCountry(Object country) {
                this.country = country;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public Object getPost() {
                return post;
            }

            public void setPost(Object post) {
                this.post = post;
            }

            public String getCollege() {
                return college;
            }

            public void setCollege(String college) {
                this.college = college;
            }

            public String getMajor() {
                return major;
            }

            public void setMajor(String major) {
                this.major = major;
            }

            public Object getSkilled() {
                return skilled;
            }

            public void setSkilled(Object skilled) {
                this.skilled = skilled;
            }

            public Object getIp() {
                return ip;
            }

            public void setIp(Object ip) {
                this.ip = ip;
            }

            public long getLastTime() {
                return lastTime;
            }

            public void setLastTime(long lastTime) {
                this.lastTime = lastTime;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public Object getIdcardFront() {
                return idcardFront;
            }

            public void setIdcardFront(Object idcardFront) {
                this.idcardFront = idcardFront;
            }

            public Object getIdcardBack() {
                return idcardBack;
            }

            public void setIdcardBack(Object idcardBack) {
                this.idcardBack = idcardBack;
            }

            public Object getTeachCard() {
                return teachCard;
            }

            public void setTeachCard(Object teachCard) {
                this.teachCard = teachCard;
            }

            public int getIsauth() {
                return isauth;
            }

            public void setIsauth(int isauth) {
                this.isauth = isauth;
            }

            public int getPushHome() {
                return pushHome;
            }

            public void setPushHome(int pushHome) {
                this.pushHome = pushHome;
            }

            public long getSortTime() {
                return sortTime;
            }

            public void setSortTime(long sortTime) {
                this.sortTime = sortTime;
            }

            public Object getOpenid() {
                return openid;
            }

            public void setOpenid(Object openid) {
                this.openid = openid;
            }

            public Object getUnionid() {
                return unionid;
            }

            public void setUnionid(Object unionid) {
                this.unionid = unionid;
            }

            public Object getQqUid() {
                return qqUid;
            }

            public void setQqUid(Object qqUid) {
                this.qqUid = qqUid;
            }

            public Object getSinaUid() {
                return sinaUid;
            }

            public void setSinaUid(Object sinaUid) {
                this.sinaUid = sinaUid;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
