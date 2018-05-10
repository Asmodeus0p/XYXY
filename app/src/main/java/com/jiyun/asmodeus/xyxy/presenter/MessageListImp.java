package com.jiyun.asmodeus.xyxy.presenter;

import com.jiyun.asmodeus.xyxy.contract.MessageListContract;
import com.jiyun.asmodeus.xyxy.model.biz.MessageListService;
import com.jiyun.asmodeus.xyxy.model.entity.RegistBean;
import com.jiyun.asmodeus.xyxy.model.entity.SystemMessageListBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MessageListImp implements MessageListContract.messageListPresenter{

    private MessageListService model;
    private MessageListContract.messageListView view;

    public MessageListImp(MessageListContract.messageListView view) {
        this.view = view;
        model= RetrofitUtils.getInstance().getMessageListService();
    }

    @Override
    public void loadMessageList(Integer userId) {
        model.getMessageList(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SystemMessageListBean>() {
                    @Override
                    public void accept(SystemMessageListBean messageListBean) throws Exception {
                        view.loadMessageListDatas(messageListBean);
                    }
                });

    }
}
