package com.kevinyin.learnsao.httpRPC.service;

import java.util.Map;

/**
 * 服务实现
 * Created by kevinyin on 2017/7/16.
 */
public class SayHelloService implements BaseService{
    public Object execute(Map<String, Object> args) {
        String[] helloArg = (String[]) args.get("arg1");

        if("hello".equals(helloArg[0])){
            return "hello";
        }else {
            return "byebye";
        }
    }
}
