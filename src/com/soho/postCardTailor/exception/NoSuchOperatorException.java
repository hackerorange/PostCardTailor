package com.soho.postCardTailor.exception;

/**
 * 不存在此操作员异常类
 */
public class NoSuchOperatorException extends Exception {
    public NoSuchOperatorException() {
        super("当前用户不存在");
    }
}
