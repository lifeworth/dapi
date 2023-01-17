package com.duzy.common;

/**
 * @author zhiyuandu
 * @date 2021/12/28-14:48
 * @description 常量
 **/
public class Constant {
    /**
     ip-api.com 批量请求的单次数量限制
     */
    public final static int MAX_BATCH_SIZE = 100;

    /**
     * token过期时间 （秒）
     */
    public static final long TOKEN_EXPIRE_SECOND = 24 * 60 * 60;
    public static int DEFAULT_PAGE_SIZE = 50;
    public static int DEFAULT_PAGE_SIZE_GROUP = 3000;
    public static int DEFAULT_PAGE_INDEX = 1;
    public static boolean DEFAULT_ORDER_ASC = false;
    public static int MAX_EXCEL_SIZE = 1048575;
    public static int REDIS_LIST_PUT_BATCH_SIZE = 10000;
    public static int STAR_ROCKS_FETCH_SIZE = 100000;


}
