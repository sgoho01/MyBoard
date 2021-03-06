package com.ghsong.myboard.advice;

import com.ghsong.myboard.advice.exception.*;
import com.ghsong.myboard.config.response.CommonResult;
import com.ghsong.myboard.modules.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    private String getMessage(String code) {
        return getMessage(code, null);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("unknown.code")), getMessage("unknown.msg"));
    }

    @ExceptionHandler(value = CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("userNotFound.code")), getMessage("userNotFound.msg"));
    }

    @ExceptionHandler(value = CSignInFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult signInFailedException(HttpServletRequest request, CSignInFailedException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("signinFailed.code")), getMessage("signinFailed.msg"));
    }

    @ExceptionHandler(value = CAuthenticationEnrtyPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResult authenticationEnrtyPointException(HttpServletRequest request, CAuthenticationEnrtyPointException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("entryPointException.code")), getMessage("entryPointException.msg"));
    }

    @ExceptionHandler(value = CResourceNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResult resourceNotExistsException(HttpServletRequest request, CResourceNotExistsException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("resourceNotExists.code")), getMessage("resourceNotExists.msg"));
    }

    @ExceptionHandler(value = CNotOwnerException.class)
    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    protected CommonResult notOwnerException(HttpServletRequest request, CNotOwnerException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("notOwner.code")), getMessage("notOwner.msg"));
    }

    @ExceptionHandler(value = CForbiddenWorkException.class)
    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    protected CommonResult forbiddenWorkException(HttpServletRequest request, CForbiddenWorkException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("forbiddenWord.code")), getMessage("forbiddenWord.msg", new Object[]{e.getMessage()}));
    }

}
