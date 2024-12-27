package com.whu.intercept;

import com.whu.constant.MessageConstant;
import com.whu.exception.UserException;
import com.whu.pojo.vo.R;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理用户异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = UserException.class)
    @ResponseBody
    public R userExceptionHandler(HttpServletRequest req, UserException e){
        log.error("发生异常: {}", e.getMessage());
        return R.error(e.getMessage());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public R exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常 : ",e);
        return R.error(MessageConstant.SYSTEM_ERROR);
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public R exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常 : ",e);
        return R.error(MessageConstant.UNKNOWN_ERROR);
    }
}
