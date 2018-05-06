package com.jiyun.asmodeus.xyxy.contract;

import com.jiyun.asmodeus.xyxy.model.entity.NoticeBean;
import com.jiyun.asmodeus.xyxy.model.entity.RegistBean;

public interface PhoneCodeContract {


    interface phonePresenter {
        void loadPhoneCode(String moble);
    }

    interface phoneView {
        void loadPhoneDatas(RegistBean registBean);
    }
}

