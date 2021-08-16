package com.example.test.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.test.entity.*;
import com.example.test.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * @Author slyart
 * @create 2021/7/2 11:14 上午
 */
@Component
public class RequestUtil{
    @Autowired
    private ServiceConfig_Service serviceConfig_service;
    @Autowired
    private TestCase_Service testCase_service;
    @Autowired
    private IManagement_Service iManagement_service;
    @Autowired
    private ResultData_Service resultData_service;
    @Autowired
    private TestReport_Service testReport_service;


    private static Logger logger = Logger.getLogger(String.valueOf(RequestUtil.class));

    public static RequestUtil requestUtil;

    @PostConstruct
    public void init() {
        requestUtil = this;
    }

    //单接口执行
    public String run(IManagement iManagement) throws IOException {
        //获取服务地址
        ServiceConfig res = requestUtil.serviceConfig_service.selectById(iManagement.getSid());
        HttpClient httpClient = new HttpClient();
        //拼接请求url
        String url = res.getServicePath() + iManagement.getInterfacePath();
        //获取前端传来的请求头
        String Headerdata = iManagement.getInterfaceHeader();
        Map Headermap = new HashMap();
        if (iManagement.getRequestType().equals("get")) {
            if (Headerdata == null) {
                String Getres = String.valueOf(httpClient.doGet(url, null));
                return Getres;
            } else {
                //判断逻辑，当只有一组key\value时直接赋值给Headermap，有多组key\value时以循环处理‘；’，然后赋值
                if (Headerdata.split(";") == null) {
                    String[] header_array = Headerdata.split(":");
                    Headermap.put(header_array[0], header_array[1]);
                } else {
                    String[] header_array = Headerdata.split(";");
                    for (String header : header_array) {
                        String[] key_array = header.split(":");
                        Headermap.put(key_array[0], key_array[1]);
                    }
                }
                String Getres = String.valueOf(httpClient.doGet(url, Headermap));
                return Getres;
            }
            //请求参数以raw格式，调用httpPostRaw
        } else if (iManagement.getRequestType().equals("post") && iManagement.getParameterType().equals("raw")) {
            Map Parametermap = new HashMap();
            //raw格式默认一个HeaderPostmap
            Map HeaderPostmap = new HashMap();
            HeaderPostmap.put("Content-Type", "application/json;charset=UTF-8");
//            String data = iManagement.getRequestParameter().replace("{", "").replace("}", "");
//            String[] header_array = data.split(",");
//            for (String header : header_array) {
//                String[] key_array = requestParameter.split(":");
//                System.out.println(key_array[0]);
//                System.out.println(key_array[1]);
//                HeaderPostmap.put(key_array[0], key_array[1]);
//            }
            String PostRawres = String.valueOf(httpClient.httpPostRaw(url, iManagement.getRequestParameter(), HeaderPostmap));
            return PostRawres;
        } else if (iManagement.getRequestType().equals("post") && iManagement.getParameterType().equals("form-data")) {
            //form-data格式


        }
        return null;
    }

    /**
     * 批量运行接口
     * 1、通过测试用例id去查询当前用例归属哪个服务
     * 2、通过服务查询服务id
     * 3、通过服务id获取服务地址
     * 4、拼接请求url
     * 5、根据接口id查询接口信息
     * 6、请求接口
     *
     * @return
     */
    public String RunAll(TestCase id) throws IOException {
        HttpClient httpClient = new HttpClient();
        TestUtil testUtil = new TestUtil();
        Date time = new Date();
        List<TestCase> res = requestUtil.testCase_service.selectIManagement(id);
        String Response = JSONObject.toJSONString(res);
        JSONArray array = JSONArray.parseArray(Response);
        String tokenData = null;
        //返回结果实体类
        ResultData resultData = new ResultData();
        for (int i = 0; i < array.size(); i++) {
            //获取服务id
            List<TestCase> TestCase = requestUtil.testCase_service.selectIManagement(id);
            String tc = JSONObject.toJSONString(TestCase);
            JSONArray tcArr = JSONArray.parseArray(tc);
            JSONObject imObj = tcArr.getJSONObject(i);
            logger.info("接口组：" + imObj.toString());
            String im = imObj.getString("iManagements");
            JSONArray imdata = JSONObject.parseArray(im);
            JSONObject idataObj = imdata.getJSONObject(0);
            Integer sid = idataObj.getInteger("sid");
            logger.info("服务id：" + sid.toString());
            //根据服务id获取服务地址
            List<ServiceConfig> path = requestUtil.serviceConfig_service.selectServicePath(sid);
            //拼接url（服务地址+api）
            String Upath = path.get(0).getServicePath();
            logger.info("请求地址：" + Upath);
            JSONObject data = (JSONObject) array.get(i);
            JSONArray iManagementsData = (JSONArray) data.get("iManagements");
            JSONObject testCaseData = iManagementsData.getJSONObject(0);
            Integer interfaceId = testCaseData.getInteger("id");
            String Headerdata = testCaseData.getString("interfaceHeader");
            String headerdata = null;
            String body = null;
            String interfacePath = testCaseData.getString("interfacePath");
            String RequestParameter = testCaseData.getString("requestParameter");
            List<IManagement> iManagements = requestUtil.iManagement_service.selectById(interfaceId);
            if ("post".equals(testCaseData.getString("requestType"))) {
                try {
                    //前置处理器
                    if (testCaseData.getInteger("processorId") == 1) {
                        if (interfacePath.contains("$")) {
                            //前置处理器处理url（未实现）
                            interfacePath = testUtil.beforetest(interfacePath, null);
                        } else if (Headerdata.contains("$")) {
                            //前置处理器处理请求头
                            headerdata = testUtil.beforetest(Headerdata, tokenData);
                            logger.info("前置条件请求头" + headerdata);
                        } else if (RequestParameter.contains("$")) {
                            //前置处理器处理请求参数（未实现）
                            testUtil.beforetest(RequestParameter, null);
                        }
                    }
                } catch (Exception e) {

                }
                //拼接url
                String url = Upath + testCaseData.getString("interfacePath");
                logger.info("前置地址" + url);
                if ("raw".equals(testCaseData.getString("parameterType"))) {
                    //raw格式默认一个HeaderPostmap
                    Map HeaderPostmap = new HashMap();
                    logger.info("headerdata的值：" + headerdata);
                    if (headerdata == null) {
                        JSONObject resultJson = httpClient.httpPostRaw(url, iManagements.get(0).getRequestParameter(), HeaderPostmap);
                        String code = resultJson.getString("code");
                        body = resultJson.getString("body");
                        logger.info("返回code：" + code);
                        logger.info("返回结果：" + body);
                        resultData.setResult(body);
                        resultData.setiManagementId(interfaceId);
                        resultData.setResultCode(code);
                        resultData.setTestCaseId(id.getId());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String dateTime = sdf.format(time);
                        resultData.setRunTime(dateTime);
                        requestUtil.resultData_service.add(resultData);
                    } else {
                        String[] header_array = headerdata.split(":");
                        HeaderPostmap.put(header_array[0], header_array[1]);
                        JSONObject resultJson = httpClient.httpPostRaw(url, iManagements.get(0).getRequestParameter(), HeaderPostmap);
                        String code = resultJson.getString("code");
                        body = resultJson.getString("body");
                        logger.info("返回结果" + body);
                        resultData.setResult(body);
                        resultData.setiManagementId(interfaceId);
                        resultData.setResultCode(code);
                        resultData.setTestCaseId(id.getId());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String dateTime = sdf.format(time);
                        resultData.setRunTime(dateTime);
                        requestUtil.resultData_service.add(resultData);
                    }
                    try {
                        //处理器判断，2为后置处理器
                        if (testCaseData.getInteger("processorId") == 2) {
                            //获取提取参数化格式
                            logger.info(testCaseData.getInteger("processorId").toString());
                            tokenData = testUtil.aftertest(body, testCaseData.getString("processorData"));
                        }
                    } catch (Exception e) {

                    }
                }
            } else if ("get".equals(testCaseData.getString("requestType"))) {
                try {
                    //前置处理器，1为前置处理器
                    if (testCaseData.getInteger("processorId") == 1) {
                        if (interfacePath.contains("$")) {
                            interfacePath = testUtil.beforetest(interfacePath, null);
                        } else if (Headerdata.contains("$")) {
                            Headerdata = testUtil.beforetest(Headerdata, tokenData);
                            logger.info(Headerdata);
                        } else if (RequestParameter.contains("$")) {
                            testUtil.beforetest(RequestParameter, null);
                        }
                    }
                } catch (Exception e) {

                }
                String url = Upath + interfacePath;
                logger.info("请求地址" + url);
                Map Headermap = new HashMap();
                if (Headerdata == null) {
                    JSONObject resultJson = httpClient.doGet(url, Headermap);
                    String code = resultJson.getString("code");
                    body = resultJson.getString("body");
                    resultData.setResult(body);
                    resultData.setiManagementId(interfaceId);
                    resultData.setResultCode(code);
                    resultData.setTestCaseId(id.getId());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateTime = sdf.format(time);
                    resultData.setRunTime(dateTime);
                    requestUtil.resultData_service.add(resultData);
                } else {
                    if (Headerdata.split(";") == null) {
                        String[] header_array = Headerdata.split(":");
                        Headermap.put(header_array[0], header_array[1]);
                    } else {
                        String[] header_array = Headerdata.split(";");
                        for (String header : header_array) {
                            String[] key_array = header.split(":");
                            Headermap.put(key_array[0], key_array[1]);
                        }
                    }
                    JSONObject resultJson = httpClient.doGet(url, Headermap);
                    String code = resultJson.getString("code");
                    body = resultJson.getString("body");
                    resultData.setResult(body);
                    resultData.setiManagementId(interfaceId);
                    resultData.setResultCode(code);
                    resultData.setTestCaseId(id.getId());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateTime = sdf.format(time);
                    resultData.setRunTime(dateTime);
                    requestUtil.resultData_service.add(resultData);
                }
            }
        }
        return "执行成功！";
    }

    /**
     * 批量运行测试用例
     *
     * @param id
     * @return
     * @throws IOException
     */
    public String RunAllTest(ArrayList<TestCase> id) throws IOException {
        for (TestCase Tid : id) {
            RunAll(Tid);
            TestReport testReport = new TestReport();
            testReport.setTestCaseId(Tid.getId());
            Date time = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateTime = sdf.format(time);
            testReport.setRunTime(dateTime);
            requestUtil.testReport_service.add(testReport);
        }
        return "执行成功！";
    }
}
