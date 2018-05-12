package com.jiyun.asmodeus.xyxy.contract;

import com.jiyun.asmodeus.xyxy.model.entity.FavoriteBean;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeBean;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeInfoBean;

public interface NoticeInfoContract {

    interface NoticeInfoPresenter{
        void laodNoticeDatas(int courseId);
    }
    interface NoticaInfoView{
        void laodNoticeDatas(NoticeInfoBean noticeInfoBean);
    }



}
