package com.jiyun.asmodeus.xyxy.model.utils;

import android.os.Environment;


public class Constant {


    public static final int MinpassLength = 6;
    static final int SuccessCode = 0;
    public static final int RECODE_AUTH_SUCCESS = 11;
    public static final int ConnectTimeout = 15;
    public static final int ReadTimeout = 20;
    public static final int WriteTimeout = 20;
    public static final int List_rows = 10;
    public static final String APP_VERSION = "1.0.0";
    public static final String CookieSP = "xyxy_cookiesp";
    public static final String CookieName = "xyxy_cookiename";
    public static final String AppToken = "xyxy_apptoken";
    public static final String UserId = "xyxy_user_id";
    public static final String VersionName = "xyxy_versionname";
    public static final String SoleName = "xyxy_solename";
    public static final String User_Agent = "xyxy_user_agent";
    public static final String CidName = "xyxy_cidname";
    public static final String NewMessage = "xyxy_newmessage";
    public static final String Jump_Phone = "jump_phone";
    public static final int ClickTime = 300;
    public static final String Parse_json_key = "application/json; charset=utf-8";
    public static final String Root_url = "https://www.univstar.com/";
    public static final String Weixinlogin_url = "/v1/m/user/login/wechat";
    public static final String QQlogin_url = "v1/m/user/login/qq";
    public static final String Authcode_url = "/v1/m/user/authcode";
    public static final String Verifyauthcode_url = "/v1/m/user/verify/authcode";
    public static final String UpDataUser_url = "/v1/m/user/updateUser";
    public static final String Register_url = "/v1/m/user/register/mobile";
    public static final String Valuable_ads_url = "/v1/m/artcircle/slideshow";
    public static final String Valuable_sort_listurl = "/v1/m/artcircle/category";
    public static final String Publish_redact_wok = "/v1/m/homewok/save";
    public static final String Default_price_url = "/v1/m/homewok/defaultPrice";
    public static final String Notice_WebView_Url = "http://share.univstar.com/view/course.html?courseid=%s";
    public static final String Live_WebView_Url = "http://share.univstar.com/view/live.html?id=%s";
    public static final String Approve_login = "approve_login";
    public static final String Approve_model = "approve_model";
    public static final int QQ_LOGIN = 2;
    public static final int WEIXIN_LOGIN = 1;
    public static final int WEIBO_LOGIN = 3;
    public static final int REGISTER_LOGIN = 4;
    public static final String User_icon = "icon";
    public static final String User_name = "name;";
    public static final String Teacher_price = "price";
    public static final String Order_No = "order_no";
    public static final String User_sex = "sex";
    public static final String User_pass = "pass";
    public static final String User_mobile = "mobile";
    public static final String Teacher_id = "teacher_id";
    public static final String Teacher_Title = "teacher_title";
    public static final String INPUT_TYPE = "input_type";
    public static final String Source_key = "source_key";
    public static final String Sort_Type = "sort_type";
    public static final String Major_Type = "major_type";
    public static final String College_Type = "college_type";
    public static final String Course_Id = "course_id";
    public static final String Wok_type = "wok_type";
    public static final String Work_Id = "work_id";
    public static final String Comment_Id = "comment_id";
    public static final String Comment_content = "comment_content";
    public static final String Comment_num = "comment_num";
    public static final String Valuable_Id = "valuable_id";
    public static final String Audio_key = "audio_key";
    public static final String Media_time = "media_time";
    public static final String INPUT_MAXL = "input_maxl";
    public static final String INPUT_TEXT = "input_text";
    public static final String Live_id = "live_id";
    public static final String Notice_id = "notice_id";
    public static final String Vido_path = "vido_path";
    public static final String SELLERID = "sellerId";
    public static final String RESERVATIONJOOD = "reservationJood";
    public static final String RESERVATIONTYPE = "reservationType";
    public static final String VIDEO_STORAGE_DIR = Environment.getExternalStorageDirectory() + "/xyxy_shortVideo/";
    public static final String Get_upload_url = "/v1/m/qiniu/getUploadToken";
    public static final String QINIUPUSH_KEY = "key";
    public static final String PUSH_IMAGE = "图片";
    public static final String PUSH_AUDIO = "音频";
    public static final String PUSH_VIDEO = "视频";
    public static final int PAGE_LOAD_INDEX = 10;
    public static final String QUIT = "1101";
    public static final String ZHIBOKE = "直播课";
    public static final int ZHIBOKE_ID = 1;
    public static final String TIYANKE = "体验课";
    public static final int TIYANKE_ID = 2;
    public static final String TOUTINGZUOYE = "偷听作业";
    public static final int TOUTINGZUOYE_ID = 3;
    public static final String YIKAOQUAN = "艺考圈作品";
    public static final String TIEZI = "帖子";
    public static final int TIEZI_ID = 4;
    public static final int NOTFAVORITE = 0;
    public static final int FAVORITE = 1;
    public static final int TEACHER_DAIFUDAO = 0;
    public static final String TEACHER_DAIFUDAO_STR = "待辅导作业";
    public static final int TEACHER_YIWANCHENG = 1;
    public static final String TEACHER_YIWANCHENG_STR = "已完成";
    public static final int TEACHER_YIGUOQI = 2;
    public static final String TEACHER_YIGUOQI_STR = "已过期";
    public static final int TEACHER_LIVE_DAIZHIBO = 0;
    public static final String TEACHER_LIVE_DAIZHIBO_STR = "待直播课程";
    public static final int TEACHER_LIVE_DAIBUZHI = 1;
    public static final String TEACHER_LIVE_DAIBUZHI_STR = "待布置作业";
    public static final int TEACHER_LIVE_YIQUXIAO = 2;
    public static final String TEACHER_LIVE_YIQUXIAO_STR = "已取消";
    public static final int TEACHER_LIVE_YIWANCHENG = 3;
    public static final String TEACHER_LIVE_YIWANCHENG_STR = "已完成";

    public static final String START_LIVE = "开始直播";

    public static final String START_WOK = "布置作业";

    public static final int Attention = 0;
    public static final int Attention_yiguanzhu = 1;
    public static final int Attention_xianghu = 2;

    public static final int Live_status_yugao = 0;
    public static final String Live_status_yugao_str = "预告";
    public static final int Live_status_zhibo = 1;
    public static final String Live_status_zhibo_str = "直播";
    public static final int Live_status_chongbo = 2;
    public static final String Live_status_chongbo_str = "重播";

    public static final int Subscribe_yuyue = 1;
    public static final int Subscribe_not = 0;


    public static final String Praise_valuable = "艺考圈作品";
    public static final String Praise_work = "学生作业";
    public static final String Praise_valuable_comment = "艺考圈评论";
    public static final String Praise_teacher = "老师";
    public static final String Praise_valuable_huifu = "艺考圈回复";
    public static final String Praise_work_comment = "作业评论";
    public static final String Praise_work_huifu = "作业回复";


    public static final String QIUJIOAZUOPIN = "求教作品";
    public static final String WEIXIN_PAY_TYPE = "wx";

    public static final String A_LI_PAY_TYPE = "alipay";

    public static final int MAX_HEIGHT = 236;
    public static final int AUDIO_MAX_HEIGHT = 132;

    public static final int Oride_Qiujiao = 1;

    public static final int Oride_Zuoye = 2;

    public static final int Oride_Sishu = 3;

    public static final int Oride_Toukan = 3;

    public static final String SHARE_CONTENT = "风里雨里，星愿艺考等你！";

    public static final String WORK_SHARE_URL = "http://share.univstar.com/share/work-detail.html?homewokId=%s";

    public static final String VALUABLE_SHARE_URL = "http://share.univstar.com/share/art-detail.html?artcircleId=%s";

    public static final String LIVE_SHARE_URL = "http://share.univstar.com/share/teacher-live-detail.html?id=%s";

    public static final String NOTICE_SHARE_URL = "http://share.univstar.com/share/trailer-detail.html?courseId=%s";

    public static final String MASTER_SHARE_URL = "http://share.univstar.com/share/teacher-home.html?teacherId=%s";

    public static final String MSG_ORDER = "MSG_ORDER";

    public static final String MSG_PRAISE = "MSG_PRAISE";

    public static final String MSG_COMMENTS = "MSG_COMMENTS";

    public static final String MSG_TEACHING = "MSG_TEACHING";

    public static final String MSG_HOMEWOK = "MSG_HOMEWOK";

    public static final String MSG_UNIVSTAR = "MSG_UNIVSTAR";

    public static final String MSG_ATTENTION = "MSG_ATTENTION";

    public static final String FORCED_LOGOFF = "FORCED_LOGOFF";

    public static final String LIVE_MSG = "LIVE_MSG";

    public static final String SYSTEM_MSG = "SYSTEM_MSG";

    public static final String NO = "N";

    public static final String YES = "Y";
}

