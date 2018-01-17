package com.fateking.yi.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.fateking.yi.support.GlobalContext;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dingxin
 */
@Slf4j
public class HttpClientUtil {

    static String TOKEN_KEY = "hb-pro-token";

    public static <T> T get(String url, Class<T> clazz) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String token = GlobalContext.getToken();
        if (token != null) {
            httpGet.addHeader(TOKEN_KEY, token);
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

    public static void market() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://api.huobi.pro/market");
        httpPost.addHeader("Content-Type", "text/html;charset=UTF-8");

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//        urlParameters.add(new BasicNameValuePair("symbol", "xrpusdt"));
//        urlParameters.add(new BasicNameValuePair("size", "100"));
//        urlParameters.add(new BasicNameValuePair("states", "partial-canceled,filled,canceled"));
//        urlParameters.add(new BasicNameValuePair("quote", "usdt"));
//        urlParameters.add(new BasicNameValuePair("coin", "xrp"));
//        urlParameters.add(new BasicNameValuePair("account-id", "1437644"));

        HttpEntity postParams = null;
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

            System.err.println(">>" + response.toString());
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

    }

    public static void main(String[] args) {
//        get("https://api.huobi.pro/market/history/kline?symbol=xrpusdt&period=1min&size=100");
    }
}