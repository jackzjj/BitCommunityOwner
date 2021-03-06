package com.bit.fuxingwuye.activities.houseManager;

import android.content.Context;

import com.bit.fuxingwuye.base.BaseEntity;
import com.bit.fuxingwuye.base.BaseRxPresenter;
import com.bit.fuxingwuye.base.ProprietorBean;
import com.bit.fuxingwuye.bean.RoomList;
import com.bit.fuxingwuye.constant.NetworkApi;
import com.bit.fuxingwuye.http.HttpResultFunc;
import com.bit.fuxingwuye.http.ProgressSubscriber;
import com.bit.fuxingwuye.http.RetrofitManager;
import com.bit.fuxingwuye.http.SubscriberOnNextListenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by 23 on 2018/3/1.
 */

public class ApplicationDetailsImpl extends BaseRxPresenter<ApplicationDetailsContract.View> implements ApplicationDetailsContract.Presenter{
    private Context context;
    @Inject
    public ApplicationDetailsImpl(Context context) {
        this.context = context;
    }

    /**
     * 同意认证接口
     * @param bean
     */
    @Override
    public void GetApplication(ProprietorBean.RecordsBean bean) {
        Observable observable = RetrofitManager.getInstace()
                .create(NetworkApi.class).audit(bean)
                .map(new HttpResultFunc<String>());
        Subscription rxSubscription = new ProgressSubscriber<>(new SubscriberOnNextListenter<BaseEntity<String>>() {
            @Override
            public void next(BaseEntity<String> o) {
                mView.ShowApplication();
            }

            @Override
            public void onError(Throwable e) {

            }

        },context,true);
        RetrofitManager.getInstace().toSubscribe(observable, (Subscriber) rxSubscription);
        addSubscrebe(rxSubscription);
    }

    /**
     * 驳回接口
     * @param bean
     */
    @Override
    public void DismissApplication(ProprietorBean.RecordsBean bean) {
        Observable observable = RetrofitManager.getInstace()
                .create(NetworkApi.class).audit(bean)
                .map(new HttpResultFunc<String>());
        Subscription rxSubscription = new ProgressSubscriber<>(new SubscriberOnNextListenter<BaseEntity<String>>() {
            @Override
            public void next(BaseEntity<String> o) {
                mView.ShowDismissApplication();
            }

            @Override
            public void onError(Throwable e) {

            }


        },context,true);
        RetrofitManager.getInstace().toSubscribe(observable, (Subscriber) rxSubscription);
        addSubscrebe(rxSubscription);
    }
}
