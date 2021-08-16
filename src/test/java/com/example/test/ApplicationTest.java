package com.example.test;

import com.example.test.entity.IManagement;
import com.example.test.entity.ServiceConfig;
import com.example.test.service.*;
import com.example.test.util.HttpClient;
import com.example.test.util.RequestUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @Author slyart
 * @create 2021/8/13 3:01 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)// 指定启动类
public class ApplicationTest extends AbstractTestNGSpringContextTests {
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

    public static ApplicationTest applicationTest;

    @PostConstruct
    public void init() {
        applicationTest = this;
    }

    //单接口执行
    public String run(IManagement iManagement) throws IOException {
        //获取服务地址
        ServiceConfig res = applicationTest.serviceConfig_service.selectById(iManagement.getSid());
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
}
