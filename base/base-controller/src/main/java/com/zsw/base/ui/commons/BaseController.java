package com.zsw.base.ui.commons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zsw.base.ui.utils.FormConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ZhangShaowei on 2017/5/9 10:32
 */

public class BaseController {

    /**
     *
     */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     *
     */
    protected FormConverter formConverter = new FormConverter();

    /**
     *
     */
    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    /**
     * @param ex exception
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    protected Object exceptionHandler(Exception ex) {
        ex.printStackTrace();
        return ex.getMessage();
    }

}