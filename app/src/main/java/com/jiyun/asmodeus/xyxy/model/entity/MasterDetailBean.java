
package com.jiyun.asmodeus.xyxy.model.entity;
import java.util.List;


public class MasterDetailBean {


    /**
     * code : 0
     * message : 成功
     * data : {"isAttention":0,"courses":[{"id":20,"page":1,"rows":20,"userId":"73","collegeIds":null,"majorIds":null,"title":"编剧","coverImg":"http://qiniu.5roo.com/b09eceab-9c29-4f60-a24e-35407371ca63.Y3JvcCwxMTk5LDkzOCwzMiw0MTQ.jpg","address":"北京","mobile":"18210050605","price":1000,"subscribeNum":2000,"createDate":1511255770000,"startDate":1511654400000,"endDate":1512000000000,"status":0,"pushDate":null}],"liveCourses":[{"id":20,"page":1,"rows":20,"userId":73,"collegeIds":null,"majorIds":"1,2","type":"讲堂","title":"表演课","coverImg":"http://image.5roo.com/uwo-file/fast/data/00/A1/ctcYLloPy6mADNklAAD0zQZR8HU038.jpg","content":"表演课程","price":300,"replay":500,"subscribeNum":1000,"createDate":1511255634000,"startDate":1511395200000,"endDate":1511568000000,"pushHome":1,"pushDate":1511232991000,"status":0}],"liveCount":4,"postsCount":9,"coachingCount":2,"attentionCount":0,"fansCount":0,"homewokPublishCount":0,"user":{"id":73,"page":1,"rows":20,"pid":null,"salt":"7190906468341220","nickname":"xyxy","realname":"xyxyx","photo":"http://image.5roo.com/uwo-file/fast/data/00/A1/ctcYLloPwu6ARXW1AAAXBjxZVsQ924.jpg","images":"http://image.5roo.com/uwo-file/fast/data/00/A0/ctcYLloKPceAVoKRAADVuf5SpPk702.png","intro":"韧性、精细、善思决定结果指数","details":"戒急矫躁，守正出奇","mobile":"15701662050","psw":"a7bc1f89c60639870606c340c6948fd35242b1f625b2cf00","email":null,"sex":2,"birthday":1510982382000,"country":null,"city":null,"userType":4,"post":null,"college":"1","major":"1,2,5","skilled":null,"ip":null,"lastTime":1511254309000,"createDate":1511247753000,"idcardFront":null,"idcardBack":null,"teachCard":null,"isauth":1,"identityAuthTime":null,"pushHome":1,"sortTime":1511247753000,"openid":null,"unionid":null,"qqUid":"6825E1716EA9B7C3BC2ADE09AD052F6E","sinaUid":null,"status":0},"praise":{"praiseCount":0,"isPraise":0}}
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
         * isAttention : 0
         * courses : [{"id":20,"page":1,"rows":20,"userId":"73","collegeIds":null,"majorIds":null,"title":"编剧","coverImg":"http://qiniu.5roo.com/b09eceab-9c29-4f60-a24e-35407371ca63.Y3JvcCwxMTk5LDkzOCwzMiw0MTQ.jpg","address":"北京","mobile":"18210050605","price":1000,"subscribeNum":2000,"createDate":1511255770000,"startDate":1511654400000,"endDate":1512000000000,"status":0,"pushDate":null}]
         * liveCourses : [{"id":20,"page":1,"rows":20,"userId":73,"collegeIds":null,"majorIds":"1,2","type":"讲堂","title":"表演课","coverImg":"http://image.5roo.com/uwo-file/fast/data/00/A1/ctcYLloPy6mADNklAAD0zQZR8HU038.jpg","content":"表演课程","price":300,"replay":500,"subscribeNum":1000,"createDate":1511255634000,"startDate":1511395200000,"endDate":1511568000000,"pushHome":1,"pushDate":1511232991000,"status":0}]
         * liveCount : 4
         * postsCount : 9
         * coachingCount : 2
         * attentionCount : 0
         * fansCount : 0
         * homewokPublishCount : 0
         * user : {"id":73,"page":1,"rows":20,"pid":null,"salt":"7190906468341220","nickname":"xyxy","realname":"xyxyx","photo":"http://image.5roo.com/uwo-file/fast/data/00/A1/ctcYLloPwu6ARXW1AAAXBjxZVsQ924.jpg","images":"http://image.5roo.com/uwo-file/fast/data/00/A0/ctcYLloKPceAVoKRAADVuf5SpPk702.png","intro":"韧性、精细、善思决定结果指数","details":"戒急矫躁，守正出奇","mobile":"15701662050","psw":"a7bc1f89c60639870606c340c6948fd35242b1f625b2cf00","email":null,"sex":2,"birthday":1510982382000,"country":null,"city":null,"userType":4,"post":null,"college":"1","major":"1,2,5","skilled":null,"ip":null,"lastTime":1511254309000,"createDate":1511247753000,"idcardFront":null,"idcardBack":null,"teachCard":null,"isauth":1,"identityAuthTime":null,"pushHome":1,"sortTime":1511247753000,"openid":null,"unionid":null,"qqUid":"6825E1716EA9B7C3BC2ADE09AD052F6E","sinaUid":null,"status":0}
         * praise : {"praiseCount":0,"isPraise":0}
         */

        private int isAttention;
        private int liveCount;
        private int postsCount;
        private int coachingCount;
        private int attentionCount;
        private int fansCount;
        private int homewokPublishCount;
        private double  price;
        private UserBean user;
        private PraiseBean praise;
        private List<CoursesBean> courses;
        private List<LiveCoursesBean> liveCourses;

        public int getIsAttention() {
            return isAttention;
        }

        public void setIsAttention(int isAttention) {
            this.isAttention = isAttention;
        }

        public int getLiveCount() {
            return liveCount;
        }

        public void setLiveCount(int liveCount) {
            this.liveCount = liveCount;
        }

        public int getPostsCount() {
            return postsCount;
        }

        public void setPostsCount(int postsCount) {
            this.postsCount = postsCount;
        }

        public int getCoachingCount() {
            return coachingCount;
        }

        public void setCoachingCount(int coachingCount) {
            this.coachingCount = coachingCount;
        }

        public int getAttentionCount() {
            return attentionCount;
        }

        public void setAttentionCount(int attentionCount) {
            this.attentionCount = attentionCount;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }

        public int getHomewokPublishCount() {
            return homewokPublishCount;
        }

        public void setHomewokPublishCount(int homewokPublishCount) {
            this.homewokPublishCount = homewokPublishCount;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public PraiseBean getPraise() {
            return praise;
        }

        public void setPraise(PraiseBean praise) {
            this.praise = praise;
        }

        public List<CoursesBean> getCourses() {
            return courses;
        }

        public void setCourses(List<CoursesBean> courses) {
            this.courses = courses;
        }

        public List<LiveCoursesBean> getLiveCourses() {
            return liveCourses;
        }

        public void setLiveCourses(List<LiveCoursesBean> liveCourses) {
            this.liveCourses = liveCourses;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public static class UserBean {
            /**
             * id : 73
             * page : 1
             * rows : 20
             * pid : null
             * salt : 7190906468341220
             * nickname : xyxy
             * realname : xyxyx
             * photo : http://image.5roo.com/uwo-file/fast/data/00/A1/ctcYLloPwu6ARXW1AAAXBjxZVsQ924.jpg
             * images : http://image.5roo.com/uwo-file/fast/data/00/A0/ctcYLloKPceAVoKRAADVuf5SpPk702.png
             * intro : 韧性、精细、善思决定结果指数
             * details : 戒急矫躁，守正出奇
             * mobile : 15701662050
             * psw : a7bc1f89c60639870606c340c6948fd35242b1f625b2cf00
             * email : null
             * sex : 2
             * birthday : 1510982382000
             * country : null
             * city : null
             * userType : 4
             * post : null
             * college : 1
             * major : 1,2,5
             * skilled : null
             * ip : null
             * lastTime : 1511254309000
             * createDate : 1511247753000
             * idcardFront : null
             * idcardBack : null
             * teachCard : null
             * isauth : 1
             * identityAuthTime : null
             * pushHome : 1
             * sortTime : 1511247753000
             * openid : null
             * unionid : null
             * qqUid : 6825E1716EA9B7C3BC2ADE09AD052F6E
             * sinaUid : null
             * status : 0
             */

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
            private String skilled;
            private Object ip;
            private long lastTime;
            private long createDate;
            private Object idcardFront;
            private Object idcardBack;
            private Object teachCard;
            private int isauth;
            private Object identityAuthTime;
            private int pushHome;
            private long sortTime;
            private Object openid;
            private Object unionid;
            private String qqUid;
            private Object sinaUid;
            private int status;
            private double price;

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

            public String getSkilled() {
                return skilled;
            }

            public void setSkilled(String skilled) {
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

            public Object getIdentityAuthTime() {
                return identityAuthTime;
            }

            public void setIdentityAuthTime(Object identityAuthTime) {
                this.identityAuthTime = identityAuthTime;
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

            public String getQqUid() {
                return qqUid;
            }

            public void setQqUid(String qqUid) {
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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }
        }

        public static class PraiseBean {
            /**
             * praiseCount : 0
             * isPraise : 0
             */

            private int praiseCount;
            private int isPraise;

            public int getPraiseCount() {
                return praiseCount;
            }

            public void setPraiseCount(int praiseCount) {
                this.praiseCount = praiseCount;
            }

            public int getIsPraise() {
                return isPraise;
            }

            public void setIsPraise(int isPraise) {
                this.isPraise = isPraise;
            }
        }

        public static class CoursesBean {
            /**
             * id : 20
             * page : 1
             * rows : 20
             * userId : 73
             * collegeIds : null
             * majorIds : null
             * title : 编剧
             * coverImg : http://qiniu.5roo.com/b09eceab-9c29-4f60-a24e-35407371ca63.Y3JvcCwxMTk5LDkzOCwzMiw0MTQ.jpg
             * address : 北京
             * mobile : 18210050605
             * price : 1000
             * subscribeNum : 2000
             * createDate : 1511255770000
             * startDate : 1511654400000
             * endDate : 1512000000000
             * status : 0
             * pushDate : null
             */

            private int id;
            private int page;
            private int rows;
            private String userId;
            private String collegeIds;
            private String majorIds;
            private String title;
            private String coverImg;
            private String address;
            private String mobile;
            private double price;
            private int subscribeNum;
            private String createDate;
            private String startDate;
            private String endDate;
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

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getCollegeIds() {
                return collegeIds;
            }

            public void setCollegeIds(String collegeIds) {
                this.collegeIds = collegeIds;
            }

            public String getMajorIds() {
                return majorIds;
            }

            public void setMajorIds(String majorIds) {
                this.majorIds = majorIds;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getSubscribeNum() {
                return subscribeNum;
            }

            public void setSubscribeNum(int subscribeNum) {
                this.subscribeNum = subscribeNum;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

        }

        public static class LiveCoursesBean {
            /**
             * id : 20
             * page : 1
             * rows : 20
             * userId : 73
             * collegeIds : null
             * majorIds : 1,2
             * type : 讲堂
             * title : 表演课
             * coverImg : http://image.5roo.com/uwo-file/fast/data/00/A1/ctcYLloPy6mADNklAAD0zQZR8HU038.jpg
             * content : 表演课程
             * price : 300
             * replay : 500
             * subscribeNum : 1000
             * createDate : 1511255634000
             * startDate : 1511395200000
             * endDate : 1511568000000
             * pushHome : 1
             * pushDate : 1511232991000
             * status : 0
             */

            private int id;
            private int page;
            private int rows;
            private int userId;
            private String collegeIds;
            private String majorIds;
            private String type;
            private String title;
            private String coverImg;
            private String content;
            private double price;
            private double replay;
            private int subscribeNum;
            private String createDate;
            private String startDate;
            private String endDate;
            private int pushHome;
            private long pushDate;
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

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getCollegeIds() {
                return collegeIds;
            }

            public void setCollegeIds(String collegeIds) {
                this.collegeIds = collegeIds;
            }

            public String getMajorIds() {
                return majorIds;
            }

            public void setMajorIds(String majorIds) {
                this.majorIds = majorIds;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getReplay() {
                return replay;
            }

            public void setReplay(double replay) {
                this.replay = replay;
            }

            public int getSubscribeNum() {
                return subscribeNum;
            }

            public void setSubscribeNum(int subscribeNum) {
                this.subscribeNum = subscribeNum;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public int getPushHome() {
                return pushHome;
            }

            public void setPushHome(int pushHome) {
                this.pushHome = pushHome;
            }

            public long getPushDate() {
                return pushDate;
            }

            public void setPushDate(long pushDate) {
                this.pushDate = pushDate;
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
