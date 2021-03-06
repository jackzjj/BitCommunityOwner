package com.bit.fuxingwuye.activities.communityDetail;

import android.content.Context;

import com.bit.fuxingwuye.base.BaseEntity;
import com.bit.fuxingwuye.base.BaseRxPresenter;
import com.bit.fuxingwuye.bean.CommonBean;
import com.bit.fuxingwuye.bean.CommunityBean;
import com.bit.fuxingwuye.bean.InformationBean;
import com.bit.fuxingwuye.bean.ReplyBean;
import com.bit.fuxingwuye.bean.ZanBean;
import com.bit.fuxingwuye.constant.NetworkApi;
import com.bit.fuxingwuye.http.HttpResultFunc;
import com.bit.fuxingwuye.http.ProgressSubscriber;
import com.bit.fuxingwuye.http.RetrofitManager;
import com.bit.fuxingwuye.http.SubscriberOnNextListenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Dell on 2017/11/6.
 * Created time:2017/11/6 14:52
 */

public class CommunityDetailPresenterImpl extends BaseRxPresenter<CommunityDetailContract.View>
        implements CommunityDetailContract.Presenter{

    private Context context;

    @Inject
    public CommunityDetailPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getCommunity(CommonBean commonBean) {
        Observable observable = RetrofitManager.getInstace()
                .create(NetworkApi.class).getInfoDetail(commonBean)
                .map(new HttpResultFunc<CommunityBean>());
        Subscription rxSubscription = new ProgressSubscriber<>(new SubscriberOnNextListenter<BaseEntity<CommunityBean>>() {
            @Override
            public void next(BaseEntity<CommunityBean> o) {
                if(o.isSuccess()){
                    mView.showDetail(o.getData());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

        },context,true);
        RetrofitManager.getInstace().toSubscribe(observable, (Subscriber) rxSubscription);
        addSubscrebe(rxSubscription);
    }

    @Override
    public void delete(CommonBean commonBean) {
        Observable observable = RetrofitManager.getInstace()
                .create(NetworkApi.class).delete_info(commonBean)
                .map(new HttpResultFunc<String>());
        Subscription rxSubscription = new ProgressSubscriber<>(new SubscriberOnNextListenter<BaseEntity<String>>() {
            @Override
            public void next(BaseEntity<String> o) {
                if(o.isSuccess()){
                    mView.deleteSuccess();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

        },context,false);
        RetrofitManager.getInstace().toSubscribe(observable, (Subscriber) rxSubscription);
        addSubscrebe(rxSubscription);
    }

    @Override
    public void saveReply(ReplyBean replyBean) {
        Observable observable = RetrofitManager.getInstace()
                .create(NetworkApi.class).saveReply(replyBean)
                .map(new HttpResultFunc<CommunityBean>());
        Subscription rxSubscription = new ProgressSubscriber<>(new SubscriberOnNextListenter<BaseEntity<CommunityBean>>() {
            @Override
            public void next(BaseEntity<CommunityBean> o) {
                if(o.isSuccess()){
                    mView.refresh();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

        },context,false);
        RetrofitManager.getInstace().toSubscribe(observable, (Subscriber) rxSubscription);
        addSubscrebe(rxSubscription);
    }

    @Override
    public void zan(ZanBean zanBean) {
        Observable observable = RetrofitManager.getInstace()
                .create(NetworkApi.class).zan(zanBean)
                .map(new HttpResultFunc<InformationBean.Info>());
        Subscription rxSubscription = new ProgressSubscriber<>(new SubscriberOnNextListenter<BaseEntity<InformationBean.Info>>() {
            @Override
            public void next(BaseEntity<InformationBean.Info> o) {
                if (o.isSuccess()){
                    mView.refreshZan(o.getData().getZanNumber());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

        },context,false);
        RetrofitManager.getInstace().toSubscribe(observable, (Subscriber) rxSubscription);
        addSubscrebe(rxSubscription);
    }
}
