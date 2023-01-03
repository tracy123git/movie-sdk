package com.shouto.movie.sdk;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class SignUtil {


    public static MultiValueMap<String, Object> makeSignV2(MultiValueMap<String, Object> map) {
        MultiValueMap<String, Object> copyMap = new LinkedMultiValueMap<>();;
        ArrayList<String> keys = new ArrayList<String>();
        for (String s : map.keySet()) {
            keys.add(s);
        }
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        StringBuffer buffer = new StringBuffer();
        for (String key : keys) {
            buffer.append(key);
            buffer.append("=");
            buffer.append(map.get(key).get(0));
            buffer.append("&");
        }
        buffer.append("appSecret="+Constants.APPSECRET);

        map.add("sign", getMD5(buffer.toString()));
        return map;
    }

    public static String makeSign(HashMap<String, Object> map) {
        HashMap<String, Object> copyMap = new HashMap<String, Object>();
        ArrayList<String> keys = new ArrayList<String>();
        for (String s : map.keySet()) {
            keys.add(s);
        }
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        StringBuffer buffer = new StringBuffer();
        for (String key : keys) {
            buffer.append(key);
            buffer.append("=");
            buffer.append(map.get(key));
            buffer.append("&");
            copyMap.put(key, map.get(key));
        }
        buffer.append("appSecret="+Constants.APPSECRET);

        copyMap.put("sign", getMD5(buffer.toString()));
        return hashMapToJson(copyMap);
    }

    private static String hashMapToJson(HashMap map) {
        String string = "{";
        for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry e = (Map.Entry) it.next();
            string += "\"" + e.getKey() + "\":";
            string += "\"" + e.getValue() + "\",";
        }
        string = string.substring(0, string.lastIndexOf(","));
        string += "}";
        return string;
    }

    public static String getMD5(byte[] input) {
        if (input == null) {
            return "";
        }

        return bytesToHexString(MD5(input));
    }

    public static String getMD5(String input) {
        return getMD5(input.getBytes());
    }

    public static String bytesToHexString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        String table = "0123456789abcdef";
        StringBuilder ret = new StringBuilder(2 * bytes.length);

        for (byte c : bytes) {
            int b;
            b = 0x0f & (c >> 4);
            ret.append(table.charAt(b));
            b = 0x0f & c;
            ret.append(table.charAt(b));
        }

        return ret.toString();
    }

    public static byte[] MD5(byte[] input) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (md != null) {
            md.update(input);
            return md.digest();
        } else {
            return null;
        }
    }
}
