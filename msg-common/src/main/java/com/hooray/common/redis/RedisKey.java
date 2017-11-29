package com.hooray.common.redis;

/**
 * 描述
 *
 * @author Orochi-Yzh
 * @dateTime 2017/11/21 15:47
 * @updatetor
 */
public class RedisKey {

    // API token验证
    public final static String API_TOKEN = "api:token:";

    // Client Oauth2.0 token
    public final static String CLIENT_AUTHTOKEN = "client:token:";

    public static final String SEND_MSG_KEY = "msg:send:";

    public final static String MP_UR = "mp:ur:";

    public final static String HR_APP = "hr:app:";
    public final static String USER_APPID_LEN = "hr:app:position:";
    public final static String USER_CID="hr:cid:";
}
