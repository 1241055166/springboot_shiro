package com.ybchen.springboot_shiro.exception;

import com.ybchen.springboot_shiro.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName：GlobalExceptiions
 * @Description：TODO
 * @Author：chenyb
 * @Date：2020/12/9 11:34 上午
 * @Versiion：1.0
 */
@ControllerAdvice
public class GlobalExceptiions {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handle(Exception ex) {
        logger.info("[ 全局异常 ] ===============》 {}", ex);
        if (ex instanceof CustomException) {
            CustomException customException = (CustomException) ex;
            return JsonData.buildError(customException.getCode(), customException.getMsg());
        }
        return JsonData.buildError("系统内部错误，请联系管理员！");
    }
}