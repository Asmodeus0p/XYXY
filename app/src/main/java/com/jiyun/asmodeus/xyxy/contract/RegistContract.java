package com.jiyun.asmodeus.xyxy.contract;

import com.jiyun.asmodeus.xyxy.model.entity.RegistBean;

public interface RegistContract {

    interface registPresenter{
        void GotoRegist(String moble,String phoneCode);
        void loadPhoneCode(String moble);
    }
    interface registView{
        void loadUserDatas(RegistBean registBean);
        void showPhoneDatas(String string);
    }
}
