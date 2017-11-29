package com.hooray.common.utils.api;

/**
 * Http请求返回统一格式-状态 Util
 *
 * @author yangfeng
 * @date 2017年10月31日 上午10:36:35
 * Email: yangfeng@hooray.cn
 */
public enum APIResultEnum {
    /**
     * 枚举类型
     */
    UNKOWM_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    FAIL(9999, "失败"),
    VALID_FAIL(10001, "参数验证失败[{0}]"),
    // OAuth2.0授权认证
    AUTH_ISNULL_APPID(14101, "AppId必填"),
    AUTH_INVALID_APPID(14102, "无效的APPId"),
    AUTH_INVALID_APPSECRET(14103, "无效的AppSecret"),
    AUTH_INVALID_REQUEST(14104, "无效的请求"),
    AUTH_INVALID_CODE(14105, "无效的code"),
    AUTH_SIGN(14106, "授权认证失败"),
    AUTH_INVALID_TOKEN(14107, "无效的Token"),
    SERVICE_CALL_FAIL(14108, "[{0}]服务调用失败"),
    MSG_SERVER_ISNULL(14109, "未发现PushServer服务"),
    AUTH_TOKEN_FAIL(14110, "生成Auth Token失败"),
    AUTH_TOKEN_EXPIRE(14111, "Token过期"),
    AUTH_EMPTY_TOKEN(14112, "Token不能为空"),
    // 用户
    SMS_FAIL(14201, "短信验证发送失败"),
    SMS_VCODE_EXPIRE(14202, "短信验证码已过期"),
    SMS_VCODE_ERROR(14203, "短信验证码有误"),
    USER_REG_FAIL(14401, "注册用户失败"),
    EMAIL_SEND_FAIL(14402, "发送邮件失败, 请重试"),
    USER_MAIL_FAIL(14403, "验证链接已失效或邮箱账号不存在"),
    USER_LOGIN_FAIL(14404, "用户名或密码错误"),
    EMAIL_ACC_INVALID(14405, "账号不存在"),
    RESET_PSD_FAIL(14406, "密码重置失败"),
    USER_TOKEN_FAIL(14407, "生成用户Token失败"),
    PHONE_FORMAT_ERROR(14408, "手机号码格式错误"),
    PHONE_REPEAT(14409, "手机号码已被注册"),
    EMAIL_FORMAT_ERROR(14410, "邮箱地址格式错误"),
    REGISTER_REPEAT(14411, "该邮箱地址已被注册"),
    // 应用
    APP_REGISTER_FAIL(14501, "添加TV应用失败"),
    APP_EDIT_FAIL(14502, "更新TV应用配置失败"),
    APP_REPEAT_REGISTER(14503, "重复登记应用"),
    APP_FIND_FAIL(14504, "获取应用信息失败"),
    APP_TYPE_NOT_FOUND(14505, "应用类型不存在"),
    APP_INFO_NO_EXIST(14506, "该用户没有登记应用"),
    OPERATION_AUTHORITY(14507, "沒有操作权限"),
    //推送模块
    MSG_UPDATE_STATE(18001, "消息状态未修改"),
    MSG_UPDATE_PUSH(18002, "消息已发送，不能修改"),
    MSG_UPDATE_USER(18003, "用户Id错误"),
    MSG_PUSH_FAILURE(18004, "消息推送失败"),
    MSG_PUSH_TOKEN_POWERS(18005, "用户权限不足"),
    MSG_PUSH_CID_POWERS(18005, "无此CID操作权限"),
    MSG_PUSH_TOKEN_FAIL(18006, "Authorization解析失败"),
    MSG_PUSH_URL(18007, "路径参数解析失败"),
    MSG_PUSH_CID(18008, "文件CID为空"),
    MSG_PUSH_CID_JSON(18009, "CID转换失败"),
    MSG_PUSH_CID_REDIS_JSON(18010, "redis中CID转换失败"),
    MSG_PUSH_CID_APPID(18011, "消息已保存,无连接用户"),
    MSG_PUSH_CID_LIST(18012, "消息已保存,cid列表为空"),
    MSG_PUSH_CHANNEL_OFF(18013, "通道已关闭，消息未发送。请在推送管理列表中查看"),
    MSG_PUSH_MSGCMS(18014, "admin-application服务调用失败"),
    MSG_PUSH_MSGINFO_ID(18015, "消息ID错误"),
    MSG_PUSH_MSGINFO_CALL_STATE(18016, "消息为无效消息，不能重发"),
    MSG_PUSH_MSGINFO_CALL_TIME(18017, "消息发送时间已经过期，不能重发"),
    MSG_PUSH_MSGINFO_MSGID(18018, "消息ID错误"),
    //文件上传
    UPLOAD_FAIL(14601, "文件上传失败"),
    UPLOAD_CREATE_TMP(14602, "创建临时文件异常"),
    UPLOAD_EMPTY_LINES(14603, "按行读取CID列表为空"),
    UPLOAD_FILE_TYPE(14604, "文件格式错误"),
    UPLOAD_CID_LENGTH(14605, "CID长度不足32位"),
    UPLOAD_CID_ERROR(14606, "部分CID不合格(有空格)"),
    UPLOAD_MAXSIZE(14607, "上传文件大小超过限制"),
    UPLOAD_EMPTY(14608, "上传的文件不能为空"),
    UPLOAD_IMG_TYPE(14609, "上传的文件不是图片类型，请重新上传"),
    UPLOAD_IMG_EMPTY(14610, "图片文件不能为空"),
    UPLOAD_IMG_FAIL(14611, "图片文件上传失败"),

    //文件下载
    DOWNLOAD_FILE_NOT_EXISTS(14800,"下载的文件不存在"),

    SYS_CONFIG_EMPTY(14701,"更新系统配置失败,查无此记录"),

    //框架错误码
    FWK_DUPLICATEKEY(15002,"数据库中已存在该记录"),

    //断路器
    HYSTRIX(19001, "断路器调用");


    private Integer code;

    private String msg;

    APIResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
