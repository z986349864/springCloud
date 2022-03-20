package com.huateng.config;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice(basePackages="com.huateng.web")
public class ValidatorException {
    /**
     * 实际上返回的是包装类，统一的返回信息
     * @param e
     * @return
     */
    @ExceptionHandler
    public String handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 1.校验
        Boolean fieldErrorUnobtainable = (e == null || e.getBindingResult() == null
                || CollectionUtils.isEmpty(e.getBindingResult().getAllErrors()) || e.getBindingResult().getAllErrors().get(0) == null);
        if (fieldErrorUnobtainable) {
            return "合法";
        }

        // 2.错误信息
        FieldError fieldError = (FieldError) e.getBindingResult().getAllErrors().get(0);
        String objectName = fieldError.getObjectName();
        String field = fieldError.getField();
        String defaultMessage = fieldError.getDefaultMessage();
        String errMsg = "参数" + objectName + '.' + field + "：" + defaultMessage;

        // 3.return
        return errMsg;
    }

    /**
     * 处理Hibernate Validator校验URL参数抛出异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public String handlerConstraintViolationException(ConstraintViolationException e) {
        // 1.校验
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return "合法";
        }

        // 2.错误信息
        ConstraintViolation<?> constraintViolation = constraintViolations.iterator().next();
        String name = constraintViolation.getPropertyPath().toString();
        String message = constraintViolation.getMessage();
        String errMsg = "参数" + name + "：" + message;

        // 3.return
        return errMsg;
    }

    /**
     * 处理Hibernate Validator校验URL参数抛出异常
     */
    @ExceptionHandler(Exception.class)
    public String handlerConstraintException(Exception e) {
        String message = e.getMessage();
        String localizedMessage = e.getLocalizedMessage();

        String errMsg = "参数" + localizedMessage + "：" + message;

        // 3.return
        return errMsg;
    }
}