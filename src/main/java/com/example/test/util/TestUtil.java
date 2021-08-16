package com.example.test.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.test.entity.IManagement;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author slyart
 * @create 2021/7/21 4:21 下午
 */
public class TestUtil {
    private static Logger logger = Logger.getLogger(String.valueOf(TestUtil.class));
    //查找参数化${参数}
    private static Pattern pattern = Pattern.compile("\\$\\{(.*?)}");
    //去掉${}
    private static Pattern p = Pattern.compile("(\\$)|(\\{.+?\\})");

    public String beforetest(String data, String parameter) {
        String replaceData = data;
        Matcher matcher = pattern.matcher(data);
        String newData = null;
        while (matcher.find()) {
//            System.out.println(matcher.group());
//            System.out.println("----------");
//            System.out.println(matcher.group(1));
//            System.out.println(replaceData.replace(matcher.group(1), parameter));
            String oldData = replaceData.replace(matcher.group(1), parameter);
            newData = oldData.replace("$", "").replace("{", "").replace("}", "");
//            logger.info(newData);
        }
        return newData;
    }

    public String aftertest(String requestData, String parametric) {
        String[] parametricData = parametric.split("\\.");
        for (int i = 0; i < parametricData.length; i++) {
            JSONObject jsonData = JSON.parseObject(requestData);
            JSONObject jsonData1 = jsonData.getJSONObject(parametricData[i]);
            try {
                String data = jsonData1.getString(parametricData[i + 1]);
                return data;
            } catch (Exception e) {

            }
        }
        return null;
    }

    public static void main(String[] args) {
        TestUtil testUtil = new TestUtil();
        testUtil.aftertest("{\"code\":99999,\"message\":\"成功\",\"data\":{\"currentUserId\":\"265e8331-8865-42dd-a420-7041558ac8d2\",\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJQTEFURk9STSI6IjgiLCJuYmYiOjE2MjY4Mjg5MTUsIlVTRVIiOiIyNjVlODMzMS04ODY1LTQyZGQtYTQyMC03MDQxNTU4YWM4ZDIiLCJleHAiOjE2Mjc0MzM3MTUsIlRFTkFOVCI6Ijk3Zjg5YjY4LWYzZjktNDI3Yi04MWJhLTdiYWEyYmQ5YmUyMCIsIkNMSU5JQyI6IjI2NWU4MzMxLTg4NjUtNDJkZC1hNDIwLTcwNDE1NThhYzhkMiIsIkNPTVBBTlkiOmZhbHNlfQ.6AkJ4BIL37chvayOo2LhrdPuuqA5pOHAmaJO8KzcQ0Y\"}}", "data.token");
    }
}
