package com.andforce.block.utils;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtils {

    public static String toStringSortByKey(Object json) {
        return JSONObject.toJSONString(json, SerializerFeature.SortField);
    }
}
