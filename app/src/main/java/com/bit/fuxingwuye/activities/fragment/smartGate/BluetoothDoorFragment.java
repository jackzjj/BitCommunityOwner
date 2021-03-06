package com.bit.fuxingwuye.activities.fragment.smartGate;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bit.fuxingwuye.Bluetooth.BluetoothApplication;
import com.bit.fuxingwuye.Bluetooth.bean.SearchBlueDeviceBean;
import com.bit.fuxingwuye.Bluetooth.mili.MiLiState;
import com.bit.fuxingwuye.Bluetooth.util.BluetoothUtils;
import com.bit.fuxingwuye.R;
import com.bit.fuxingwuye.activities.MainTabActivity;
import com.bit.fuxingwuye.activities.fragment.elevatorFrag.ElevatorFragment;
import com.bit.fuxingwuye.base.BaseApplication;
import com.bit.fuxingwuye.base.BaseFragment;
import com.bit.fuxingwuye.bean.DoorMILiBean;
import com.bit.fuxingwuye.bean.StoreDoorMILiBeanList;
import com.bit.fuxingwuye.utils.CustomDialog;
import com.bit.fuxingwuye.utils.DialogUtil;
import com.bit.fuxingwuye.utils.SensorManagerHelper;
import com.bit.fuxingwuye.utils.ToastUtil;
import com.bit.fuxingwuye.views.CircleProgressBar;
import com.smarthome.bleself.sdk.BluetoothApiAction;
import com.smarthome.bleself.sdk.IBluetoothApiInterface;

import java.util.ArrayList;

/**
 * Created by Dell on 2018/3/1.
 */

public class BluetoothDoorFragment extends BaseFragment implements View.OnClickListener {

    private ImageView iv_open;
    private Switch shake_switch;
    private CircleProgressBar circle_progress;
    private DoorMILiBean doorMILiBean;
    private String Tag = "BluetoothDoorFragment";
    private RelativeLayout rl_change_gate;
    private TextView tv_name;

    private boolean isNeedShake = true;
    private BluetoothNetUtils bluetoothNetUtils;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_blue_door;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initEventAndData() {
        iv_open = (ImageView) mView.findViewById(R.id.iv_open);
        circle_progress = (CircleProgressBar) mView.findViewById(R.id.loading_view);
        shake_switch = (Switch) mView.findViewById(R.id.shake_switch);
        rl_change_gate = (RelativeLayout) mView.findViewById(R.id.rl_change_gate);
        tv_name = (TextView) mView.findViewById(R.id.tv_name);

        iv_open.setOnClickListener(this);
        rl_change_gate.setOnClickListener(this);

        bluetoothNetUtils = new BluetoothNetUtils(getActivity());
        initListener();
        shake_switch.setChecked(false);
    }

    /**
     * 监听
     */
    private void initListener() {
        SensorManagerHelper sensorHelper = new SensorManagerHelper(getActivity());
        sensorHelper.setOnShakeListener(new SensorManagerHelper.OnShakeListener() {

            @Override
            public void onShake() {

                if(!isNeedShake){
                    return;
                }
                if (MainTabActivity.currentFragmentPosiont != 1 || FragmentDoor.currentDoorFragmentPositon != 0) {
                    return;
                }
                Log.e(Tag, "shake==");
                if (isNeedShake) {
                    circle_progress.start();
                    isNeedShake = false;
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            if (doorMILiBean == null || doorMILiBean.isFirst()) {
                                BaseApplication.getInstance().getBlueToothApp().scanBluetoothDevice(2000, new BluetoothApplication.CallBack() {
                                    @Override
                                    public void onCall(ArrayList<SearchBlueDeviceBean> searchBlueDeviceBeanList) {
                                        getMenJinMiLi(searchBlueDeviceBeanList, 2);
                                    }
                                });
                            }
                        }
                    }.start();


                }
            }
        });

        shake_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e(Tag, "isChecked==" + isChecked);
                isNeedShake = isChecked;
            }
        });
    }

    boolean isNeedClickAble = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_open:
                if (isNeedClickAble) {
                    circle_progress.start();

                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            if (doorMILiBean == null || doorMILiBean.isFirst()) {
                                BaseApplication.getInstance().getBlueToothApp().scanBluetoothDevice(2000, new BluetoothApplication.CallBack() {
                                    @Override
                                    public void onCall(ArrayList<SearchBlueDeviceBean> searchBlueDeviceBeanList) {
                                        getMenJinMiLi(searchBlueDeviceBeanList, 1);
                                    }
                                });
                            } else {
                                clickBlueMiLi(doorMILiBean.getMac(), doorMILiBean.getPin(), 1);
                            }
                        }
                    }.start();
                }

                break;
            case R.id.rl_change_gate:
                Intent intent = new Intent(mActivity, ChangeAccessActivity.class);
                if (doorMILiBean != null) {
                    intent.putExtra("doorMILiBean", doorMILiBean);
                }
                startActivityForResult(intent, 100);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == 10) {
                doorMILiBean = (DoorMILiBean) data.getSerializableExtra("doorMILiBean");
                tv_name.setText(doorMILiBean.getName());
            }
        }
    }

    /**
     * 判断获取的设备是否是米粒蓝牙
     *
     * @param searchBlueDeviceBeanList
     * @param type                     1:点击 2：摇一摇
     */
    private void getMenJinMiLi(final ArrayList<SearchBlueDeviceBean> searchBlueDeviceBeanList, final int type) {

        if (BaseApplication.getInstance().getBlueToothApp().isLocationEnalbe()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.showShort("请打开您的定位权限！");
                    circle_progress.stop();
                    isNeedClickAble = true;
                    if (type == 2) {
                        isNeedShake = true;
                    }
                }
            });
            return;
        }
        if (searchBlueDeviceBeanList.size() < 1) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.showShort("没有找到蓝牙设备");
                    circle_progress.stop();
                    isNeedClickAble = true;
                    if (type == 2) {
                        isNeedShake = true;
                    }
                }
            });
            return;
        }

        String[] doorMacArr = new String[searchBlueDeviceBeanList.size()];
//        for (int i = 0; i < searchBlueDeviceBeanList.size(); i++) {
//            String address = searchBlueDeviceBeanList.get(i).getBluetoothDevice().getAddress();
//            StringBuffer sb = new StringBuffer();
//            for (int j = 0; j < address.length(); j++) {
//                char c = address.charAt(j);
//                if (!(c + "").equals(":")) {
//                    sb.append(c);
//                }
//            }
//            doorMacArr[i] = sb.toString();
//            Log.e(Tag, " doorMacArr[i]=" + doorMacArr[i]);
//        }

        StoreDoorMILiBeanList bletoothDoorDate = bluetoothNetUtils.getBletoothDoorDate();
        if (bletoothDoorDate != null) {
            if (bletoothDoorDate.getDoorMILiBeans() != null) {
                if (bletoothDoorDate.getDoorMILiBeans().size() > 0) {
                    DoorMILiBean doorMILiBean = BluetoothUtils.getMaxRsic(searchBlueDeviceBeanList, bletoothDoorDate.getDoorMILiBeans());
                    if (doorMILiBean != null) {
                        clickBlueMiLi(doorMILiBean.getMac(), doorMILiBean.getPin(), type);
                    } else {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (type == 2) {
                                    isNeedShake = true;
                                }
                                circle_progress.stop();
                                isNeedClickAble = true;
                                ToastUtil.showShort("没有搜索到门的设备");
                                Log.e(Tag, "没有搜索到门的设备！");
                            }
                        });
                        // getMiLiNetDate(searchBlueDeviceBeanList, type, doorMacArr);
                    }
                } else {
                    getMiLiNetDate(searchBlueDeviceBeanList, type, doorMacArr);
                }
            } else {
                getMiLiNetDate(searchBlueDeviceBeanList, type, doorMacArr);
            }

        } else {
            getMiLiNetDate(searchBlueDeviceBeanList, type, doorMacArr);
        }
    }


    /**
     * 没有缓存时从缓存获取数据
     *
     * @param searchBlueDeviceBeanList
     * @param type
     * @param doorMacArr
     */
    private void getMiLiNetDate(final ArrayList<SearchBlueDeviceBean> searchBlueDeviceBeanList, final int type, String[] doorMacArr) {
        bluetoothNetUtils.getMiLiNetDate(doorMacArr, 1, new BluetoothNetUtils.OnBlutoothDoorCallBackListener() {
            @Override
            public void OnCallBack(int state, StoreDoorMILiBeanList storeDoorMILiBeanList) {
                if (state == 1) {
                    if (storeDoorMILiBeanList != null) {
                        if (storeDoorMILiBeanList.getDoorMILiBeans().size() > 0) {
                            DoorMILiBean doorMILiBean = BluetoothUtils.getMaxRsic(searchBlueDeviceBeanList, storeDoorMILiBeanList.getDoorMILiBeans());
                            if (doorMILiBean != null) {
                                clickBlueMiLi(doorMILiBean.getMac(), doorMILiBean.getPin(), type);
                            } else {
                                if (type == 2) {
                                    isNeedShake = true;
                                }
                                circle_progress.stop();
                                isNeedClickAble = true;
                                ToastUtil.showShort("没有搜索到门的设备");
                                Log.e(Tag, "没有搜索到门的设备！");
                            }
                        } else {
                            if (type == 2) {
                                isNeedShake = true;
                            }
                            circle_progress.stop();
                            isNeedClickAble = true;
                            ToastUtil.showShort("没有搜索到门的设备");
                            Log.e(Tag, "没有有用的蓝牙设备");
                        }
                    } else {
                        if (type == 2) {
                            isNeedShake = true;
                        }
                        ToastUtil.showShort("没有搜索到门的设备");
                        circle_progress.stop();
                        isNeedClickAble = true;
                        Log.e(Tag, "没有有用的蓝牙设备");
                    }
                } else if (state == 2) {
                    circle_progress.stop();
                    isNeedClickAble = true;
                    if (type == 2) {
                        isNeedShake = true;
                    }
                }
            }
        });
    }


    /**
     * 米粒的开门
     */
    private void clickBlueMiLi(final String destMac, final String destPin, final int type) {
        Log.e(Tag, "打开米粒门 mac=" + destMac + " destPin=" + destPin);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //F0:C7:7F:9D:66:37
                BluetoothApiAction.bluetoothActionUnlock(destMac, destPin, getActivity(), new IBluetoothApiInterface.IBluetoothApiCallback<Object>() {

                    @Override
                    public void onFailure(final String arg0) {
                        Log.e(Tag, "" + MiLiState.getCodeDesc(arg0));
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                circle_progress.stop();
                                isNeedClickAble = true;
                                if (type == 2) {
                                    isNeedShake = true;
                                }
                                Toast.makeText(getActivity(), "开门失败" + MiLiState.getCodeDesc(arg0), Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onSuccess(Object arg0) {
                        if (type == 2) {
                            isNeedShake = true;
                        }
                        succssAnimation();
                    }
                });
            }
        });

    }


    @Override
    public void toastMsg(String msg) {

    }

    public void succssAnimation() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final CustomDialog customDialog = DialogUtil.showFrameAnimDialog(mContext, R.drawable.anim_open_doors);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (customDialog != null && customDialog.isShowing()) {
                            customDialog.dismiss();
                            circle_progress.stop();
                            isNeedClickAble = true;
                        }
                    }
                }, 3000);

            }
        });
    }

}
