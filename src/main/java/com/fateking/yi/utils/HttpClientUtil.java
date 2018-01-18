package com.fateking.yi.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.fateking.yi.config.HuobiConfig;
import com.fateking.yi.exception.IllegalArgumentException;
import com.fateking.yi.support.GlobalContext;
import com.fateking.yi.support.SpringObjectFactory;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;
import java.util.Map;

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
        if (params != null) {
            if (url.indexOf("?") > 0) {
                params.forEach((key, value) ->
                        stringBuilder.append(key)
                                .append("=")
                                .append(value)
                                .append("&")
                );
            } else {
                stringBuilder.append("?");
                params.forEach((key, value) ->
                        stringBuilder.append(key)
                                .append("=")
                                .append(value)
                                .append("&")
                );
            }
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        String token = GlobalContext.getToken();
        if (token != null) {
            httpGet.addHeader(SpringObjectFactory.getBean(HuobiConfig.class).getTokenKey(), token);
        }

        String responseStr = null;
        try {

            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            System.out.println("GET Response Status:: "
                    + httpResponse.getStatusLine().getStatusCode());

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
        String token = GlobalContext.getToken();
        if (token != null) {
            httpPost.addHeader(SpringObjectFactory.getBean(HuobiConfig.class).getTokenKey(), token);
        }
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        if (params != null) {
            params.forEach((key, value) -> {
                urlParameters.add(new BasicNameValuePair(key, value));
            });
        }

        HttpEntity postParams;
        String responseStr = null;
        try {
            postParams = new UrlEncodedFormEntity(urlParameters);
            httpPost.setEntity(postParams);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

            System.out.println("POST Response Status:: "
                    + httpResponse.getStatusLine().getStatusCode());

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