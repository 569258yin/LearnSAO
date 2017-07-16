package com.kevinyin.learnsao.httpRPC.service;

import java.util.Map;

/**
 * Created by kevinyin on 2017/7/16.
 */
public interface BaseService {

    public Object execute(Map<String,Object> args);
}
