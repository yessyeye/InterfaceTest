package com.example.test.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @Author slyart
 * @create 2021/6/8 2:37 下午
 */
public class HttpClient {
    private static Logger logger = Logger.getLogger(String.valueOf(HttpClient.class));

    /**
     * 带请求头的get请求
     *
     * @param url
     * @param header
     * @return
     * @throws IOException
     */
    public JSONObject doGet(String url, Map<String, String> header) throws IOException {
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //构建get请求对象
        HttpGet httpGet = new HttpGet(url);
        logger.info(url);
        if (header != null) {
            //处理请求头，将map转为string
            for (Map.Entry<String, String> entity : header.entrySet()) {
                //去掉头尾“”，不然key和value多出一组“”
                httpGet.setHeader(JSON.toJSONString(entity.getKey()).replace("\"", ""), JSON.toJSONString(entity.getValue()).replace("\"", ""));
            }
        } else {
            httpGet.setHeader("Content-Type", "application/json");
        }

        //执行请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        int StatusCode = httpResponse.getStatusLine().getStatusCode();
        String StatusDescription = httpResponse.getStatusLine().getReasonPhrase();
        logger.info("StatusCode: " + StatusCode);
        logger.info("StatusDescription: " + StatusDescription);
        Header headers[] = httpResponse.getAllHeaders();
        for (Header h : headers) {
            logger.info(h.getName() + ":" + h.getValue());
        }
        String body = null;
        if (httpResponse.getEntity() != null) {
            body = EntityUtils.toString(httpResponse.getEntity(), "utf-8"); // 将实体（内容）转换成一个字符串
            logger.info("Response content: " + body);
        }
        JSONObject ResultJson = new JSONObject();
        ResultJson.put("code", StatusCode);
        ResultJson.put("body", body);
        return ResultJson;
    }

    /**
     * 带参数和请求头的post请求
     *
     * @param url
     * @param header
     * @param params
     * @return
     * @throws Exception
     */
    public JSONObject httpPostForm(String url, Map<String, String> header, Map<String, String> params) throws Exception {
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URL url1 = new URL(url);
        URI uri = new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null);
        //创建httpPost请求对象
        HttpPost httpPost = new HttpPost(uri);
        // 遍历请求头
        for (Map.Entry<String, String> entry : header.entrySet()) {
            //去掉头尾“”，不然key和value多出一组“”
            httpPost.setHeader(JSON.toJSONString(entry.getKey()).replace("\"", ""), JSON.toJSONString(entry.getValue()).replace("\"", ""));
        }
        if (params != null) {
            // 遍历参数的Map集合，设置请求参数列表
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> data : params.entrySet()) {
                parameters.add(new BasicNameValuePair(data.getKey(), data.getValue()));
            }
            // 创建表单实体对象，将请求参数设置到表单实体当中
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(parameters);
            httpPost.setEntity(encodedFormEntity);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            int StatusCode = httpResponse.getStatusLine().getStatusCode();
            String StatusDescription = httpResponse.getStatusLine().getReasonPhrase();
            logger.info("StatusCode: " + StatusCode);
            logger.info("StatusDescription: " + StatusDescription);
            Header headers[] = httpResponse.getAllHeaders();
            for (Header h : headers) {
                logger.info(h.getName() + ":" + h.getValue());
            }
            String body = null;
            if (httpResponse.getEntity() != null) {
                body = EntityUtils.toString(httpResponse.getEntity(), "utf-8"); // 将实体（内容）转换成一个字符串
                logger.info("Response content: " + body);
            }
            JSONObject ResultJson = new JSONObject();
            ResultJson.put("code", StatusCode);
            ResultJson.put("body", body);
            return ResultJson;
        }
        return null;
    }

    /**
     * 发送 http post 请求，参数以原生字符串进行提交
     *
     * @param url
     * @param stringJson
     * @param headers
     * @return
     */
    public JSONObject httpPostRaw(String url, String stringJson, Map<String, String> headers) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);
        logger.info("url: " + url);
        //设置header
        httpost.setHeader("Content-type", "application/json");
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        StringEntity stringEntity = new StringEntity(stringJson);
        httpost.setEntity(stringEntity);
        CloseableHttpResponse httpResponse = httpClient.execute(httpost);
        int StatusCode = httpResponse.getStatusLine().getStatusCode();
        String StatusDescription = httpResponse.getStatusLine().getReasonPhrase();
        logger.info("StatusCode: " + StatusCode);
        logger.info("StatusDescription: " + StatusDescription);
        Header header[] = httpResponse.getAllHeaders();
        for (Header h : header) {
            logger.info(h.getName() + ":" + h.getValue());
        }
        String body = null;
        if (httpResponse.getEntity() != null) {
            body = EntityUtils.toString(httpResponse.getEntity(), "utf-8"); // 将实体（内容）转换成一个字符串
            logger.info("Response content: " + body);
        }
        JSONObject ResultJson = new JSONObject();
        ResultJson.put("code", StatusCode);
        ResultJson.put("body", body);
        return ResultJson;
    }
}
