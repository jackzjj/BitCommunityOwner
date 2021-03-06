package com.bit.communityOwner.net;

/**
 * Created by zhangjiajie on 18/3/1.
 */

public class Url {

    /**
     * 测试环境
     */
    public static final String BASE_TEST_URL = "https://api.smcm.bitiot.com.cn";

    /**
     * 正式环境
     */
    public static final String BASE_URL = "https://api.smcm.bitiot.com.cn";

    /**
     * 获取oss token
     */
    public static final String V1_OSS_STS_TOKEN = "/v1/oss/sts-token";

    /**
     * 获取社区
     */
    public static final String V1_COMMUNITY_PAGE = "/v1/community/page";

    /**
     * 获取楼栋
     */
    public static final String V1_COMMUNITY_BUILDING_PAGE = "/v1/community/building/page";

    /**
     * 获取房间
     */
    public static final String V1_COMMUNITY_ROOM_PAGE = "/v1/community/room/page";

    /**
     * 用户反馈
     */
    public static final String V1_SYS_FEEDBACK_ADD = "/v1/sys/feedback/add";

    /**
     * 根据用户ID获取楼房列表
     */
    public static final String V1_ROOM_QUERY_BY_USER = "/v1/room/query-by-user";

    /**
     * 业主
     */
    public static final String V1_USER_ROOM_ATTESTATION = "v1/user/room/attestation";

    /**
     * 家属和租客
     */
    public static final String V1_USER_MEMBER_ADD = "v1/user/member/add";

    /**
     * 根据手机号获取验证码
     */
    public static final String V1_USER_GET_VERIFICATION_CODE = "/v1/user/getVerificationCode";

    /**
     * 用户注册
     */
    public static final String V1_USER_ADD = "/v1/user/add";

    /**
     * 用户登录
     */
    public static final String V1_USER_SIGNIN = "/v1/user/signIn";
    /**
     * 获取住房管理的请求房间数据
     */
    public static final String V1_USER_GETBYROOMID = "/v1/user/{roomId}/getByRoomId";
    /**
     * 关闭住房管理数据
     */
    public static final String V1_USER_CLOSEAPPLYBYID = "/v1/user/{id}/closedApplyById";
    /**
     * 申请或驳回
     */
    public static final String V1_USER_MEMBER_AUDIT = "/v1/user/member/audit";
    /**
     * 解绑
     */
    public static final String V1_USER_MEMBER_RELIEVE = "/v1/user/member/{id}/relieve";

    /**
     * 用户编辑
     */
    public static final String V1_USER_EDIT = "/v1/user/edit";
    /**
     * 在线咨询
     */
    public static final String V1_ONLINE = "/v1/user/property/" + "5a82adf3b06c97e0cd6c0f3d" + "/user-list";

    /**
     * 修改密码
     */
    public static final String V1_USER_CHANGE_PASSWORD = "/v1/user/changePassword";

    /**
     * 重置密码
     */
    public static final String V1_USER_RESET_PASSWORD = "/v1/user/resetPassword";

    /**
     * 公告列表
     */
  //  public static final String V1_PROPERTY_NOTICE_PAGE = "/v1/property/notice/{communityId}/page";
    /**
     * 公告列表
     */
    public static final String V1_PROPERTY_NOTICE_PAGE = "/v1/property/notice/page";

    /**
     * 公告详情
     */
    public static final String V1_NOTICE_DETAIL = "/v1/property/notice/%s/detail";

    /**
     * 重置密码
     */
    public static final String V1_USER_ADD_PASS_FLAG = "/v1/property/rpass/add";

    /**
     * 获取放行条列表
     */
    public static final String V1_PASSCODE_LIST = "/v1/property/rpass/page";


    /**
     * 获取公告列表数据
     */
    public static final String V1_NOTICE_LIST = "/v1/property/notice/page";

    /**
     * 查询虚拟卡
     */
    public static final String V1_CARD_LIST = "/v1/user/card/get/list";

    /**
     * 申请卡片
     */
    public static final String V1_ADD_CARD = "/v1/user/card/add";

}
