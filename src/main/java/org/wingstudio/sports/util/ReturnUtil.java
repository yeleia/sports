package org.wingstudio.sports.util;

import org.wingstudio.sports.constant.MsgConstant;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReturnUtil {
    public static Map<String,Object> ret(boolean code,String message){
        Map<String,Object> resultMap=new LinkedHashMap<>();
        if (code) {
            resultMap.put("status", MsgConstant.STATUS_SUCCESS);

        }else {
            resultMap.put("status",MsgConstant.STATUS_FAIL);
        }
        resultMap.put("message",message);
        return resultMap;
    }

}
