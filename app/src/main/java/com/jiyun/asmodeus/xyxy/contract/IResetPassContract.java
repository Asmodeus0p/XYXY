package com.jiyun.asmodeus.xyxy.contract;

import com.jiyun.asmodeus.xyxy.model.entity.FindPassBean;

public interface IResetPassContract {

    interface ResetPassPresenter{
        void getResetPass(String mobile,String passwowrd);
    }
    interface ResetPassView{
        void IresetPass(FindPassBean loginRegsterSucessBean);
    }
}
