package com.shouto.movie.sdk;

public class Api {

    public static String HOST = "";

    static {
        if (Constants.DEBUG) {
            HOST = "http://movieapi2-test.taototo.cn/";
        } else {
            HOST = "http://movieapi2-test.taototo.cn/";
        }
    }

    //城市列表api
    public static final String URL_QUERY_CITY = HOST + "movieapi/movie-info/get-city-list";
    //影院列表api
    public static final String URL_QUERY_CINEMA = HOST + "movieapi/movie-info/get-cinema-list";
    //具体影院的场次排期
    public static final String URL_QUERY_SCHEDULE_SHOW = HOST + "movieapi/movie-info/get-schedule-list";

    //包含某电影的影院
    public static final String URL_QUERY_CINEMA_BY_FILM = HOST + "movieapi/movie-info/get-show-list";
    //包含某电影的日期
    public static final String URL_QUERY_FILM_SHOW_DATE = HOST + "movieapi/movie-info/get-show-date";
    //某场次的座位
    public static final String URL_QUERY_SHOW_SEAT = HOST + "movieapi/movie-info/get-seat";
    public static final String URL_QUERY_LOCK_SEAT = HOST + "submitOrder";

    //秒出单下单
    public static final String URL_QUERY_CREATE_API_ORDER = HOST + "api/order/create";

    //秒出单下单
    public static final String URL_QUERY_CREATE_SOON_ORDER = HOST + "api/order/create-soon-order";
    //订单查询
    public static final String URL_QUERY_QUERY_ORDER = HOST + "api/order/query";
    //即将上映
    public static final String URL_QUERY_SOON_MOVIE = HOST + "movieapi/movie-info/get-soon-list";
    //正在热映
    public static final String URL_QUERY_HOT_MOVIE = HOST + "movieapi/movie-info/get-hot-list";
    //城市下区域列表api
    public static final String URL_QUERY_CITY_AREA = HOST + "movieapi/movie-info/get-city-area";
    //获取账户信息
    public static final String URL_QUERY_ACCOUNT_INFO = HOST + "api/user/info";

    public static final String URL_QUERY_VERSION =HOST+"movieapi/movie-info/get-version";

}
