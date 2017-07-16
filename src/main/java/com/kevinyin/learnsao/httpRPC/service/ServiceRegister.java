package com.kevinyin.learnsao.httpRPC.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevinyin on 2017/7/16.
 */
public class ServiceRegister {

    private static final Map<String,BaseService> serviceMap = new HashMap<String, BaseService>();

    public static BaseService getService(String serviceName){
        BaseService service = serviceMap.get(serviceName);
        if (service != null){
            return  service;
        }else {
            try {
                service = (BaseService) Class.forName(serviceName).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (service != null){
                serviceMap.put(serviceName,service);
                return service;
            }else {
                return null;
            }
        }
    }
}
