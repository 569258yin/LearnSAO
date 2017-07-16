package com.kevinyin.learnsao.httpRPC.consumer;

import com.kevinyin.learnsao.httpRPC.model.JsonResult;
import com.kevinyin.learnsao.utils.JsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kevinyin on 2017/7/16.
 */
@Controller
@RequestMapping("/cosumer")
public class ServiceConsumer {

    @RequestMapping("")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        String service = "com.kevinyin.learnsao.sayhello";
        String format = "json";
        String arg1 = "hello";

        String url = "http://localhost:8080/provider.do?"+"service="+service +
                "&format="+format+"&arg1="+arg1;

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse res = httpClient.execute(httpGet);
        HttpEntity entity = res.getEntity();
        byte[] bytes = EntityUtils.toByteArray(entity);
        String result = new String(bytes,"utf-8");

        JsonResult jsonResult = JsonUtils.string2object(result,JsonResult.class);

        response.getWriter().write(jsonResult.getResult().toString());

    }

}
