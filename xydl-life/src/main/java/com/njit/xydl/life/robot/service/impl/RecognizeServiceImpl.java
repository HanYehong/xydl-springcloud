package com.njit.xydl.life.robot.service.impl;

import com.njit.xydl.life.common.util.BaseImg64;
import com.njit.xydl.life.robot.service.RecognizeService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

/**
 * @author HanYehong
 * @date 2019/3/31 17:42
 */
@Service
public class RecognizeServiceImpl implements RecognizeService {

    /**
     * 请求地址
     */
    private static final String POST_URL =
            "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic?access_token=" + getAuth();

    @Override
    public String recognitionRobot(String imageUrl) throws IOException, URISyntaxException {

        return checkUrl(imageUrl);
    }

    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    private static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "7GKraLhijsKpacEe3Cf95GHa";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "6GF4aptRdUnTUTH7iwvmqLksZTGVi9Hb";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    private static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";

        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;

        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            JSONObject jsonObject = new JSONObject(result.toString());
            return jsonObject.getString("access_token");
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    /**
     * 识别本地图片的文字
     *
     * @param path 本地图片地址
     * @return 识别结果，为json格式
     * @throws URISyntaxException URI打开异常
     * @throws IOException        io流异常
     */
    private String checkFile(String path) throws URISyntaxException, IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new NullPointerException("图片不存在");
        }
        String image = BaseImg64.getImageStrFromPath(path);
        return post("image=" + image);
    }

    /**
     * @param url 图片url
     * @return 识别结果，为json格式
     */
    private String checkUrl(String url) throws IOException, URISyntaxException {
        return post("url=" + url);
    }

    /**
     * 通过传递参数：url和image进行文字识别
     *
     * @param param 区分是url还是image识别
     * @return 识别结果
     * @throws URISyntaxException URI打开异常
     * @throws IOException        IO流异常
     */
    private String post(String param) throws URISyntaxException, IOException {

        HttpPost post = new HttpPost();

        post.setURI(new URI(POST_URL));
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setEntity(new StringEntity(param));

        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == 200) {
            String str;
            try {
                // 读取服务器返回过来的json字符串数据
                str = EntityUtils.toString(response.getEntity());
                return str;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

}
