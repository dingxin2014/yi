package com.fateking.yi.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.fateking.yi.config.AccountConfig;
import com.fateking.yi.exception.IllegalArgumentException;
import com.fateking.yi.support.SpringObjectFactory;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dingxin
 */
@Slf4j
public class HttpClientUtil {

    public static <T> T get(String url, Map<String, String> params, Class<T> clazz) {
        if (url == null) {
            throw new IllegalArgumentException("url must not be null!");
        }
        StringBuilder stringBuilder = new StringBuilder(url);

        if (params == null) {
            params = Maps.newHashMap();
        }

        String accessKey = SpringObjectFactory.getBean(AccountConfig.class).getAccessKey();
        String privateKey = SpringObjectFactory.getBean(AccountConfig.class).getPrivateKey();
        Date now = new Date();
        String timeStamp = DateFormatUtils.format(now, "yyyy-MM-dd'T'HH:mm:ss");

        params.put("AccessKeyId", accessKey);
        params.put("SignatureMethod", "HmacSHA256");
        params.put("SignatureVersion", "2");
        params.put("Timestamp", timeStamp);

        List<String> list = params.keySet().stream().sorted().collect(Collectors.toList());

        if (url.indexOf("?") < 0) {
            stringBuilder.append("?");
        }

        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i)).append("=").append(params.get(list.get(i)));
            if (i != list.size() - 1) {
                stringBuilder.append("&");
            }
        }

        stringBuilder.append("=").append("Signature").append("=").append(HmacSHA256Util.sha256HMAC(stringBuilder.toString(), privateKey));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        httpGet.addHeader("Content-Type", "application/json");

        String responseStr = null;
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            httpClient.close();

            responseStr = response.toString();
            return JSON.parseObject(responseStr, clazz);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
            log.error("JSON IS >>> " + responseStr);
        }

        return null;
    }

    public static <T> T post(String url, Map<String, String> params, Class<T> clazz) {
        if (url == null) {
            throw new IllegalArgumentException("url must not be null!");
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        if (params != null) {
            params.forEach((key, value) -> urlParameters.add(new BasicNameValuePair(key, value)));
        }

        String accessKey = SpringObjectFactory.getBean(AccountConfig.class).getAccessKey();
        String privateKey = SpringObjectFactory.getBean(AccountConfig.class).getPrivateKey();
        Date now = new Date();
        String timeStamp = DateFormatUtils.format(now, "yyyy-MM-dd'T'HH:mm:ss");
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append("?").append("AccessKeyId").append(accessKey)
                .append("SignatureMethod").append("HmacSHA256")
                .append("SignatureVersion").append("2")
                .append("Timestamp").append(timeStamp);

        urlParameters.add(new BasicNameValuePair("AccessKeyId", accessKey));
        urlParameters.add(new BasicNameValuePair("SignatureMethod", "HmacSHA256"));
        urlParameters.add(new BasicNameValuePair("SignatureVersion", "2"));
        urlParameters.add(new BasicNameValuePair("Timestamp", timeStamp));
        urlParameters.add(new BasicNameValuePair("Signature", HmacSHA256Util.sha256HMAC(stringBuilder.toString(), privateKey)));

        HttpEntity postParams;
        String responseStr = null;
        try {
            postParams = new UrlEncodedFormEntity(urlParameters);
            httpPost.setEntity(postParams);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            httpClient.close();

            responseStr = response.toString();
            return JSON.parseObject(responseStr, clazz);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
            log.error("JSON IS >>> " + responseStr);
        }
        return null;
    }

}