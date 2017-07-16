package com.kevinyin.learnsao.httpRPC.provider;

import com.kevinyin.learnsao.httpRPC.model.JsonResult;
import com.kevinyin.learnsao.httpRPC.service.BaseService;
import com.kevinyin.learnsao.httpRPC.service.ServiceRegister;
import com.kevinyin.learnsao.utils.JsonUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by kevinyin on 2017/7/16.
 */
public class ServiceProvider{



    @RequestMapping("")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        String serviceName = request.getParameter("service");
        String format = request.getParameter("format");

        Map parameters = request.getParameterMap();

        BaseService service = ServiceRegister.getService(serviceName);

        Object result = service.execute(parameters);


        JsonResult jsonResult = new JsonResult();
        jsonResult.setResultCode(200);
        jsonResult.setMessage("success");
        jsonResult.setResult(result);

        String json = JsonUtils.object2String(jsonResult);
        response.getWriter().write(json);
    }
}
