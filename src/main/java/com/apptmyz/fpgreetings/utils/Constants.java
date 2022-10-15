package com.apptmyz.fpgreetings.utils;

public class Constants {


	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String CONTENT_TYPE_CSV = "text/csv";
	public static final String CHARACTER_ENCODING_UTF_8 = "UTF-8";

	public static final String SHARE_APP_URL_ANDROID = "SHARE_APP_URL_ANDROID";
	public static final String SHARE_APP_URL_IOS = "SHARE_APP_URL_IOS";
	public static final String SHARE_APP_URL_PC = "SHARE_APP_URL_PC";
	
	public final static String ERROR_UNKNOWN = "Unknown";
	public final static String ERROR_URL_PARAMETER = "Missing request parameter(s)";
	public final static String ERROR_PARSING_REQUEST_DATA = "Error parsing request data ";
	public final static String ERROR_REQUEST_DATA_NOT_VALID = "Request data not valid";
	public final static String ERROR_IN_VALID_BANK_SWITCH = "Request data has invalid bankswitch";
	public final static String ERROR_IN_VALID_CB_TYPE = "Request data has invalid CBType";

	public final static String ERROR_URL_ACTION_PARAMETER = "Action missing in the request";
	public final static String ERROR_INVAILD_ACTION = "Invalid action";

	public final static String ERROR_RECORD_NOT_FOUND = "Record not found";
	public final static String ERROR_REMOVE_RECORD = "Problem in deleting record";

	public final static String ERROR_URL_PASSWORD_PARAMETER = "Password missing in the request";
	
	
	public static final long CORRECT_STATUS_CODE = 10000;
	public static final long ERROR_TRANSACTION_LIMIT_STATUS_CODE = 10001;
	public static final long INVALID_STATUS_CODE = 10002;
	public static final long SERVER_TIME_OUT_STATUS_CODE = 10003;
	public static final long EXCEPTION_IN_SERVER_STATUS_CODE = 10004;
	public static final long ERROR_INCOMPLETE_DATA_STATUS_CODE = 10005;
	public static final long ERROR_INVAILD_SESSION_STATUS_CODE = 10006;
	public static final long ERROR_AADHAAR_PHONE_NUMBER_MAP_STATUS_CODE = 10009;
	public static final long ERROR_LAST_USED_TRANSACTION_FAILED_STATUS_CODE = 10010;
	public static final long ERROR_INVAILD_ACTION_CODE = 10011;
	public static final long ERROR_BIOMETRIC_DATA_DID_NOT_MATCH = 10012;
	public static final long ERROR_REVERSAL_STATUS_CODE = 10013;
	public static final long CORRECT_REVERSAL_STATUS_CODE = 10014;
	public static final long ERROR_REFERENCE_ID_NOT_FOUND = 10015;
	
	public static final boolean TRUE = true;
	public static final boolean FALSE = false;
	
	public static final String REQUEST_COMPLETED = "Request Completed";
	public static final String REQUEST_INCOMPLETE = "Request Incomplete";
	public static final String ERRORS_EXCEPTION_IN_SERVER = "Exception in server";
	
	public static final String SECRETKEY = "birthdaywishes22";
}
