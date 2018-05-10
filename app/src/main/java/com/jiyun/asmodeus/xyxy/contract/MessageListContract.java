package com.jiyun.asmodeus.xyxy.contract;

import com.jiyun.asmodeus.xyxy.model.entity.SystemMessageListBean;

public interface MessageListContract {

    interface messageListPresenter{
        void loadMessageList(Integer userId);
    }
    interface messageListView{
        void loadMessageListDatas(SystemMessageListBean messageListBean);
    }
}
