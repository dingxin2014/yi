package com.fateking.yi.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.fateking.yi.config.AccountConfig;
import com.fateking.yi.exception.IllegalArgumentException;
import com.fateking.yi.utils.DateUtil;
import com.fateking.yi.utils.HmacSHA256Util;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class HuobiHttpClient {

    private static final String LANG = "zh-CN";
    private static final String GET_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String GET_MARKET_CONTENT_TYPE = "application/json";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36";
    private static final String OK = "ok";

    @Autowired
    AccountConfig accountConfig;

    private String accessKey;
    private String privateKey;

    @PostConstruct
    public void init() {
        this.accessKey = accountConfig.getAccessKey();
        this.privateKey = accountConfig.getPrivateKey();
    }

    public <T> T getMarket(String url, Map<String, String> params, Class<T> clazz) {
        if (url == null) {
            throw new IllegalArgumentException("url must not be null!");
        }
        StringBuilder stringBuilder = new StringBuilder(url);

        if (params == null) {
            params = Maps.newHashMap();
        }

        stringBuilder.append("?");

        params.forEach((key, value) -> {
            stringBuilder.append(key).append("=").append(value).append("&");
        });

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String finalUrl = stringBuilder.toString();
        HttpGet httpGet = new HttpGet(finalUrl);

        httpGet.addHeader("User-Agent", USER_AGENT);
        httpGet.addHeader("Content-Type", GET_MARKET_CONTENT_TYPE);
        httpGet.addHeader("Accept-Language", LANG);

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
            T resp = JSON.parseObject(responseStr, clazz);
            if (OK.equals(FieldUtils.readField(resp, "status", true))) {
                return resp;
            } else {
                log.error("ERROR RETURN! >>> " + responseStr);
            }
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
            log.error("JSON IS >>> " + responseStr);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }


    public <T> T get(String url, Map<String, String> params, Class<T> clazz) {
        if (url == null) {
            throw new IllegalArgumentException("url must not be null!");
        }
        StringBuilder stringBuilder = new StringBuilder(url);

        if (params == null) {
            params = Maps.newHashMap();
        }

        boolean isEncrypt = url.indexOf("v1") > -1;

        StringBuilder encryptSb = new StringBuilder();
        if (isEncrypt) {
            try {
                URI uri = new URI(url);
                encryptSb.append("GET\n")
                        .append(uri.getHost()).append("\n")
                        .append(uri.getPath()).append("\n");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        Date now = DateUtil.getUTCTime();
        String timestamp = DateFormatUtils.format(now, "yyyy-MM-dd'T'HH:mm:ss");

        params.put("AccessKeyId", accessKey);
        params.put("SignatureMethod", "HmacSHA256");
        params.put("SignatureVersion", "2");
        params.put("Timestamp", timestamp);

        List<String> list = params.keySet().stream().map(e -> encode(e)).sorted().collect(Collectors.toList());

        stringBuilder.append("?");

        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i)).append("=").append(params.get(decode(list.get(i))));
            if (isEncrypt) {
                String value = params.get(list.get(i));
                value = encode(value);
                encryptSb.append(list.get(i)).append("=").append(value);
            }
            if (i != list.size() - 1) {
                stringBuilder.append("&");
                encryptSb.append("&");
            }
        }
        String signature = HmacSHA256Util.sha256HMACBase64(encryptSb.toString(), privateKey);
        stringBuilder.append("&").append("Signature").append("=").append(signature);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String finalUrl = stringBuilder.toString();
        HttpGet httpGet = new HttpGet(finalUrl);

        httpGet.addHeader("User-Agent", USER_AGENT);
        httpGet.addHeader("Content-Type", GET_CONTENT_TYPE);
        httpGet.addHeader("Accept-Language", LANG);

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
            T resp = JSON.parseObject(responseStr, clazz);
            if (OK.equals(FieldUtils.readField(resp, "status", true))) {
                return resp;
            } else {
                log.error("ERROR RETURN! >>> " + responseStr);
            }
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
            log.error("JSON IS >>> " + responseStr);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public <T> T post(String url, Map<String, String> params, Class<T> clazz) {
        if (url == null) {
            throw new IllegalArgumentException("url must not be null!");
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        if (params != null) {
            params.forEach((key, value) -> urlParameters.add(new BasicNameValuePair(key, value)));
        }

        Date now = DateUtil.getUTCTime();
        String timestamp = DateFormatUtils.format(now, "yyyy-MM-dd'T'HH:mm:ss");

        StringBuilder encryptSb = new StringBuilder();
        try {
            URI uri = new URI(url);
            encryptSb.append("POST\n")
                    .append(uri.getHost()).append("\n")
                    .append(uri.getPath()).append("\n");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        encryptSb.append("AccessKeyId").append("=").append(accessKey)
                .append("SignatureMethod").append("=").append("HmacSHA256")
                .append("SignatureVersion").append("=").append("2")
                .append("Timestamp").append("=").append(timestamp);

        String signature = HmacSHA256Util.sha256HMACBase64(encryptSb.toString(), privateKey);

        urlParameters.add(new BasicNameValuePair("AccessKeyId", accessKey));
        urlParameters.add(new BasicNameValuePair("SignatureMethod", "HmacSHA256"));
        urlParameters.add(new BasicNameValuePair("SignatureVersion", "2"));
        urlParameters.add(new BasicNameValuePair("Timestamp", timestamp));
        urlParameters.add(new BasicNameValuePair("Signature", signature));

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
            T resp = JSON.parseObject(responseStr, clazz);
            if (OK.equals(FieldUtils.readField(resp, "status", true))) {
                return resp;
            } else {
                log.error("ERROR RETURN! >>> " + responseStr);
            }
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
            log.error("JSON IS >>> " + responseStr);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static final String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final String decode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
