package com.xjd.wechat;

/**
 * @author elvis.xu
 * @since 2017-08-15 17:47
 */
public interface ApiErrCodes {

	int SC_OK = 0;

	int SC_INVALID_CREDENTIAL = 40001;

	int SC_INVALID_OPENID = 40003;

	int SC_INVALID_AUTH_CODE = 40029;

	int SC_USED_AUTH_CODE = 40163;

	int SC_INVALID_IP = 40164;

	int SC_MISSING_AUTH_CODE = 41008;

	int SC_ACCESS_TOKEN_EXPIRED = 42001;

	int SC_FREQ_EXCEED_LIMIT = 45009;

}
