package com.fateking.yi.utils;

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

    static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
    static String REFERER = "https://www.huobipro.com/zh-cn/xrp_usdt/exchange/";
    static String ORIGIN = "https://www.huobipro.com";
    static String ACCEPT_LANGUAGE = "zh-CN";
    static String ACCEPT = "application/json, text/plain, */*";
    static String ACCEPT_ENCODING = "gzip, deflate, br";
    static String TOKEN_KEY = "hb-pro-token";
    static String XRP_URL = "https://api.huobipro.com/v1/order/orders/?symbol=xrpusdt&size=11&states=partial-canceled,filled,canceled&quote=usdt&coin=xrp&account-id=1437644";

    public static String fetchXrp(String token) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(XRP_URL);
        httpGet.addHeader("User-Agent", USER_AGENT);
        httpGet.addHeader("referer", REFERER);
        httpGet.addHeader("accept-language", ACCEPT_LANGUAGE);
        httpGet.addHeader("origin", ORIGIN);
        httpGet.addHeader("accept", ACCEPT);
        httpGet.addHeader("accept-encoding", ACCEPT_ENCODING);
        httpGet.addHeader(TOKEN_KEY, token);

//        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//        urlParameters.add(new BasicNameValuePair("symbol", "xrpusdt"));
//        urlParameters.add(new BasicNameValuePair("size", "100"));
//        urlParameters.add(new BasicNameValuePair("states", "partial-canceled,filled,canceled"));
//        urlParameters.add(new BasicNameValuePair("quote", "usdt"));
//        urlParameters.add(new BasicNameValuePair("coin", "xrp"));
//        urlParameters.add(new BasicNameValuePair("account-id", "1437644"));

        HttpEntity postParams = null;
        try {

            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

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

            return response.toString();
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    public static void market() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://api.huobi.pro/market");
        httpPost.addHeader("Content-Type", "text/html;charset=UTF-8");

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

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
        market();
    }
}