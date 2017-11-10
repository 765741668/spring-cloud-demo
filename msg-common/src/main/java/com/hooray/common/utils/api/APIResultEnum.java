package com.hooray.common.utils.api;

/**
 * Http请求返回统一格式-状态 Util
 *
 * @author yangfeng
 * @date 2017年10月31日 上午10:36:35
 * Email: yangfeng@hooray.cn
 */
public enum APIResultEnum {

    UNKOWM_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    FAIL(9999, "失败"),
    VALID_FAIL(1, "验参失败[{0}]"),
    // OAuth2.0授权认证
    AUTH_ISNULL_APPID(4101, "AppId必填"),
    AUTH_INVALID_APPID(4102, "无效的APPId"),
    AUTH_INVALID_APPSECRET(4103, "无效的AppSecret"),
    AUTH_INVALID_REQUEST(4104, "无效的请求"),
    AUTH_INVALID_CODE(4105, "无效的code"),
    AUTH_DEFAULT(4106, "请求参数缺失"),
    AUTH_SIGN(4107, "验签失败"),
    AUTH_INVALID_TOKEN(4108, "无效的Token"),
    AUTH_SERVICE_FAIL(4109, "MicroService调用失败"),
    MSG_SERVER_ISNULL(4110, "未发现服务"),
    // 用户
    SMS_FAIL(4201, "短信验证发送失败"),
    SMS_VCODE_EXPIRE(4202, "短信验证码已过期"),
    SMS_VCODE_ERROR(4203, "短信验证码有误"),
    USER_REG_ERROR(4401, "用户信息注册有误"),
    USER_SEND_FAIL(4402, "发送邮件失败, 请重试"),
    USER_MAIL_FAIL(4402, "验证链接已失效或邮箱账号不存在"),
    USER_LOGIN_FAIL(4403, "登录失败"),
    INVALID_ACCOUNT(4404, "账号不存在"),
    LOGIN_SUCCESS(2101, "登录成功"),
    RESET_PSD_SUCCESS(2102, "密码重置成功"),
    RESET_PSD_FAIL(4405, "密码重置失败"),
    USER_TOKEN_FAIL(4406, "生成用户Token失败"),
    PHONE_VALID_FAIL(4407, "手机号码格式错误"),
    // 应用
    ERROR_SAVE_TVAPP(4501, "添加TV应用失败"),
    ERROR_AUTH(4502, "生成AppID等信息失败"),
    //推送模块
    MSG_UPDATE_STATE(8001, "消息状态未修改"),
    MSG_UPDATE_PUSH(8002, "消息已发送，不能修改"),
    MSG_UPDATE_USER(8003, "用户Id错误"),
    MSG_PUSH_FAILURE(8004, "消息推送失败"),
    MSG_PUSH_TOKEN_POWERS(8005, "用户权限不足"),
    MSG_PUSH_TOKEN_FAIL(8005, "Authorization解析失败"),
    MSG_PUSH_URL(8007, "路径参数解析失败"),
    //文件上传
    UPLOAD_SUCCESS(4600, "文件上传成功"),
    UPLOAD_FAIL(4601, "文件上传失败"),
    UPLOAD_CREATE_TMP(4602, "创建临时文件异常"),
    UPLOAD_EMPTY_LINES(4603, "按行读取CID列表为空"),
    UPLOAD_FILE_TYPE(4604, "文件格式错误"),
    UPLOAD_CID_LENGTH(4605, "CID长度不足32位"),
    UPLOAD_CID_ERROR(4606, "部分CID不合格(有空格)"),

    //框架错误码
    FWK_DUPLICATEKEY(5002,"数据库中已存在该记录"),

    //断路器
    HYSTRIX(9001, "断路器调用");


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
