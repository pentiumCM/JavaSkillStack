package indi.pentiumcm.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.http
 * @className: HttpClientUtil
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/31 22:01
 * @describe:
 */
public class HttpClientUtil {


    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private HttpClientUtil() {

    }

    /**
     * 发起POST请求
     *
     * @param url      url
     * @param paramMap 参数的Map格式
     */
    public static void sendPost(String url, Map<String, String> paramMap) {
        logger.info("开始发起POST请求，请求地址为{}，参数为{}", url, JSON.toJSON(paramMap));
        CloseableHttpResponse response = null;

        CloseableHttpClient httpClient;
        try {

            httpClient = HttpClients.createDefault();
            String encoding = "utf-8";
            //创建post请求对象
            HttpPost httpPost = new HttpPost(url);
            //装填请求参数
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            //设置参数到请求对象中
            httpPost.setEntity(new UrlEncodedFormEntity(list, encoding));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            response = httpClient.execute(httpPost);

        } catch (IOException e) {
            logger.error("POST请求发出失败，请求的地址为{}，参数为{}，错误信息为{}", url, JSON.toJSON(paramMap), e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error("POST请求response关闭异常，错误信息为{}", e.getMessage(), e);
            }
        }
    }

    /**
     * 发起GET请求
     *
     * @param urlParam url请求，包含参数
     */
    public static void sendGet(String urlParam) {
        logger.info("开始发起GET请求，请求地址为{}", urlParam);
        HttpGet httpGet = new HttpGet(urlParam);
        CloseableHttpResponse response = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            logger.error("GET请求发出成功，请求的地址为{}，返回状态为{}", urlParam, status);
        } catch (IOException e) {
            logger.error("GET请求发出失败，请求的地址为{}，错误信息为{}", urlParam, e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error("GET请求response关闭异常，错误信息为{}", e.getMessage(), e);
            }
        }
    }

    public static void main(String[] args) {
        String url = "https://jiashubing.cn/tencenttest";
        //需要传入的参数
        Map<String, String> map = new HashMap<>();
        map.put("code", "js");
        map.put("day", "0");
        map.put("city", "北京");
        map.put("dfc", "1");
        map.put("charset", "utf-8");
        sendPost(url, map);


        String urlParam = "https://jiashubing.cn/talk/document?fileid=1234ji贾树丙";
        sendGet(urlParam);
    }
}
