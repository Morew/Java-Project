package com.nustti.utils;

import java.util.HashMap;
import java.util.Map;

public class BaseApiUtils {
    /**
     * 返回错误
     * @param msg
     * @return
     */
    public Map<String,Object> setResultError(String msg){
        return setResult(BaseApiConstants.HTTP_500_CODE,msg,null);
    }
    public Map<String, Object> setResultParameterERROR(String msg){
        return setResult(BaseApiConstants.HTTP_400_CODE,msg,null);
    }


    public Map<String, Object> setResultSuccesssData(Object data){
        return setResult(BaseApiConstants.HTTP_200_CODE,BaseApiConstants.HTTP_SUCCESS_NAME,data);
    }
    public Map<String, Object> setResultSuccess() {
        return setResult(BaseApiConstants.HTTP_200_CODE,BaseApiConstants.HTTP_SUCCESS_NAME,null);
    }

    /**
     * 自定义返回
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public Map<String,Object> setResult(Integer code,String msg,Object data){
        Map<String,Object> result = new HashMap<>();
        result.put(BaseApiConstants.HTTP_CODE_NAME,code);
        result.put(BaseApiConstants.HTTP_200_NAME,msg);
        if(data!=null)
            result.put(BaseApiConstants.HTTP_DATA_NAME,data);
        return result;
    }
}
