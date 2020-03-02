package com.rafagao.dbook.common.handler;

import com.rafagao.dbook.common.exception.DBookUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author rafa gao
 */

@ControllerAdvice
public class DBookExceptionHandler {

    @ExceptionHandler({DBookUserException.class})
    public ModelAndView handleDBookUserException(){
        // 跳转到首页
        return new ModelAndView("redirect:"
                .concat("/dbook/index"));

    }
}
