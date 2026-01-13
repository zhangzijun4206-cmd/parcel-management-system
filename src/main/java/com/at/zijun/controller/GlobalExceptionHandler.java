package com.at.zijun.controller;

import com.at.zijun.common.BusinessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public String handleBusiness(BusinessException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error/business";
    }
}
