package com.shouto.movie.sdk;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

public class ShouToMovie {

    private static ShouToMovie shoutoMovie = null;

    public static ShouToMovie getInstance() {
        if (shoutoMovie == null) {
            shoutoMovie = new ShouToMovie();
        }

        return shoutoMovie;
    }

    /**
     * 查询可用的城市列表，该接口返回中国大陆地区地级市的列表。手动拉取
     */
    public String queryCitys() {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        String result = HttpClient.sendPost(Api.URL_QUERY_CITY,param);
        return result;
    }

    /**
     * 拉取影院列表，拉取频率每周1次。
     *
     * @param cityId 城市id
     */
    public String queryCinemas(Long cityId) {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        postData.add("cityId", cityId);
        String result = HttpClient.sendPost(Api.URL_QUERY_CINEMA, postData);
        return result;
    }

    /**
     * 拉取场次，建议半个小时拉取1次。
     *
     * @param cinemaId 影院id
     */
    public String queryShows(int cinemaId) {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        postData.add("cinemaId", cinemaId);
        String result = HttpClient.sendPost(Api.URL_QUERY_SCHEDULE_SHOW, postData);
        return result;
    }

    /**
     * 拉取座位图，实时拉取。
     *
     * @param showId 场次id
     */
    public String queryShowSeats(String showId) {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        postData.add("showId", showId);
        String result = HttpClient.sendPost(Api.URL_QUERY_SHOW_SEAT, postData);
        return result;
    }

    /**
     * @description:包含某电影的影院
     * @author: zhuangzhihui
     * @date: 2022-12-23 15:05
     * @param: [pageNo, pageSize, filmId, cityId]
     * @return: java.lang.String
     **/
    public String queryCinemaByFilm(Integer pageNo,Integer pageSize,Integer filmId,Integer cityId) {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        postData.add("page", pageNo==null?1:pageNo);
        postData.add("limit", pageSize==null?10:pageSize);
        postData.add("filmId", filmId);
        postData.add("cityId", cityId);
        postData.add("area", "思明区");//非必填
        postData.add("date", "2022-12-24");//非必填
//        postData.add("latitude", "24.48540661");//非必填
//        postData.add("longitude", "118.0964355");//非必填

        String result = HttpClient.sendPost(Api.URL_QUERY_CINEMA_BY_FILM, postData);
        return result;
    }


    /**
     * @description:包含某电影的日期
     * @author: zhuangzhihui
     * @date: 2022-12-23 15:05
     * @param: [pageNo, pageSize, filmId, cityId]
     * @return: java.lang.String
     **/
    public String queryShowDate(Integer filmId,Integer cityId) {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        postData.add("filmId", filmId);
        postData.add("cityId", cityId);

        String result = HttpClient.sendPost(Api.URL_QUERY_FILM_SHOW_DATE, postData);
        return result;
    }

    /**
     * @description:获取同步版本号
     * @author: zhuangzhihui 
     * @date: 2022-12-23 15:20
     * @param: []
     * @return: java.lang.String
     **/
    public String queryVersion() {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        String result = HttpClient.sendPost(Api.URL_QUERY_VERSION, postData);
        return result;
    }


    /**
     * 锁座接口，实时拉取。
     *
     * @param phone   下单手机号
     * @param seatIds 座位id,多个座位使用英文逗号,隔开
     */
    public String lockSeat(String phone, String seatIds) {
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("phone", phone);
        postData.put("seatId", seatIds);
        String result = HttpClient.sendPost(Api.URL_QUERY_LOCK_SEAT, postData);
        return result;
    }

    /**
     * 获取订单信息。
     * 支付完成需要最多轮询15分钟，前1分钟最好15秒一次 ，1-5分钟30秒一次，6-15分钟 1分钟轮询一次，15分钟之后停止轮询出票失败
     *
     * @param orderId 订单id
     */
    public String queryOrder(String orderId) {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        postData.add("thirdOrderId", orderId);
        String result = HttpClient.sendPost(Api.URL_QUERY_QUERY_ORDER, postData);
        return result;
    }

    public String createApiOrder(String orderId) {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        postData.add("thirdOrderId", orderId);
        String result = HttpClient.sendPost(Api.URL_QUERY_CREATE_API_ORDER, postData);
        return result;
    }


    /**
     * 热映影片列表。影片有场次
     */
    public String queryHotMovies() {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        String result = HttpClient.sendPost(Api.URL_QUERY_HOT_MOVIE, postData);
        return result;
    }

    /**
     * 待上映影片列表。影片无场次
     */
    public String queryWaitMovies() {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        String result = HttpClient.sendPost(Api.URL_QUERY_SOON_MOVIE, postData);
        return result;
    }


    /**
     * 查询城市区域。
     *
     * @param cityId 城市id
     */
    public String queryRegions(int cityId) {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        postData.add("cityId", cityId);
        String result = HttpClient.sendPost(Api.URL_QUERY_CITY_AREA, postData);
        return result;
    }

    /**
     * 查询账户信息。
     *
     */
    public String queryAccountInfo() {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        String result = HttpClient.sendPost(Api.URL_QUERY_ACCOUNT_INFO, postData);
        return result;
    }


//    public static void main(String[] args) {
//
//
//        String paiqi = shoutoMovie.getInstance().queryShows(341);
//        System.out.println("test="+paiqi);
//        String json = shoutoMovie.getInstance().queryShowSeats("MzE0I0BpbWFubUAjMTIzMjc0I0BpbWFubUAjMTYxMzcxMDIwMCNAaW1hbm1AIzEzI0BpbWFubUAjMjA=");
//        System.out.println(json);
//    }

    public static void main(String[] args) {


//        String json = ShouToMovie.getInstance().queryCitys();
//        String json = ShouToMovie.getInstance().queryHotMovies();
//        String json = ShouToMovie.getInstance().queryWaitMovies();
//        String json = ShouToMovie.getInstance().queryRegions(3643);
//        String json = ShouToMovie.getInstance().queryCinemas(3643L);
//        String json = ShouToMovie.getInstance().queryShows(888157092);
//        String json = ShouToMovie.getInstance().queryShowSeats("6807BF6E5BC0AB326B763C3C2DACE85F");
//        String json = ShouToMovie.getInstance().queryCinemaByFilm(1,10,130483,89);
//        String json = ShouToMovie.getInstance().queryShowDate(130483,89);
//        String json = ShouToMovie.getInstance().queryVersion();
        String json = ShouToMovie.getInstance().queryAccountInfo();
//        String json = ShouToMovie.getInstance().queryOrder("20210730222BB");
        System.out.println(json);

    }

}
