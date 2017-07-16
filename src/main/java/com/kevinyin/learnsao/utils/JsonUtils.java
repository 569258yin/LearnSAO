package com.kevinyin.learnsao.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by kevinyin on 2017/7/16.
 */
public class JsonUtils {
    private static ObjectMapper mapper = new ObjectMapper();
    private static Logger logger = Logger.getLogger(JsonUtils.class);

    public static String object2String(Object object){
        try {
            return  mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error(e);
        }
        return null;
    }

    public static <T> T  string2object(String json,Class<T> tClass){
        try {
            return mapper.readValue(json,tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
