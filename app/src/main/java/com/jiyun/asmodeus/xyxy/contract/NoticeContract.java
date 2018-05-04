package com.jiyun.asmodeus.xyxy.contract;

import com.jiyun.asmodeus.xyxy.model.entity.NoticeBean;

public interface NoticeContract {

    interface NoticePresenter{
        void laodNoticeDatas();
    }
    interface NoticaView{
        void laodNoticeDatas(NoticeBean noticeDatas);
    }
}
