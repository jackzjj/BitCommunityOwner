package com.bit.fuxingwuye.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.bit.communityOwner.KeyString;
import com.bit.communityOwner.model.OssToken;
import com.bit.communityOwner.net.Api;
import com.bit.communityOwner.net.ResponseCallBack;
import com.bit.communityOwner.net.ServiceException;
import com.bit.communityOwner.util.LogUtil;
import com.bit.communityOwner.util.UploadUtils;
import com.bit.fuxingwuye.R;
import com.bit.fuxingwuye.activities.login.LoginActivity;
import com.bit.fuxingwuye.base.BaseActivity;
import com.bit.fuxingwuye.base.BaseApplication;
import com.bit.fuxingwuye.bean.GetUserRoomListBean;
import com.bit.fuxingwuye.constant.HttpConstants;
import com.bit.fuxingwuye.databinding.ActivitySplashBinding;
import com.bit.fuxingwuye.utils.ACache;
import com.bit.fuxingwuye.utils.OssManager;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import cn.qqtheme.framework.AppConfig;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding mBinding;
    ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        final String cid = aCache.getAsString(HttpConstants.COMMUNIYID);
        if (!TextUtils.isEmpty(cid)) {
            Api.getUserRoomsList(cid, new ResponseCallBack<List<GetUserRoomListBean>>() {
                @Override
                public void onSuccess(List<GetUserRoomListBean> data) {
                    if (data != null && !data.isEmpty()) {
                        aCache.put(cid, "1");
                    } else {
                        aCache.put(cid, "0");
                    }
                }
            });
        }
    }

    @Override
    protected void initEventAndData() {
        aCache = ACache.get(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        MobclickAgent.setDebugMode(true);

        if (aCache.getAsString(HttpConstants.TOKEN) != null && !"".equals(aCache.getAsString(HttpConstants.TOKEN))){
            initOssToken();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;
                if (aCache.getAsString(HttpConstants.TOKEN) != null && !"".equals(aCache.getAsString(HttpConstants.TOKEN))) {
                    intent = new Intent(SplashActivity.this, MainTabActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                if (getIntent().getBundleExtra(KeyString.EXTRA_BUNDLE) != null) {
                    intent.putExtra(KeyString.EXTRA_BUNDLE, getIntent().getBundleExtra(KeyString.EXTRA_BUNDLE));
                }
                startActivity(intent);
                finish();

            }
        }, 3000);
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void toastMsg(String msg) {

    }

    private void initOssToken() {
        Api.ossToken(new ResponseCallBack<OssToken>() {
            @Override
            public void onSuccess(OssToken data) {
                super.onSuccess(data);
                if (data!=null){
                    OssManager.getInstance().init(BaseApplication.getInstance(), data);
                    aCache.put(HttpConstants.OSSTOKEN, data);
                }
            }

            @Override
            public void onFailure(ServiceException e) {
                super.onFailure(e);
            }
        });
    }
}
