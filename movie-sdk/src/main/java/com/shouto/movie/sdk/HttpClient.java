package com.shouto.movie.sdk;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;


public class HttpClient {


    /**
     * @description:新的post请求接口
     * @author: zhuangzhihui
     * @date: 2022-12-23 14:19
     * @param: [url, param]
     * @return: java.lang.String
     **/
    public static String sendPost(String url,MultiValueMap<String, Object> param){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded");
        param.add("appKey", Constants.APPKEY);
        param.add("time", System.currentTimeMillis()/1000);
        param=SignUtil.makeSignV2(param);

        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(param, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                url,//获取资源的地址
                formEntity,
                String.class//返回类型设为String
        );
        String body = response.getBody();
        System.out.println(body);
        return null;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url      发送请求的 URL
     * @param postData 请求参数，请求参数应该是 json 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, HashMap<String, Object> postData) {
        postData.put("appKey", Constants.APPKEY);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String param = SignUtil.makeSign(postData);
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Content-Type", "Content-Type:application/x-www-form-urlencoded");
//            conn.setRequestProperty("user-agent",
//                    "QmmMovieApi/1.0.0");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static String sendGet(String url) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("user-agent",
                    "QmmMovieApi/1.0.0");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print("");
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}
