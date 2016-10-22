package com.soho.postCardTailor.exception;

/**
 * 当密码不正确的时候触发此异常
 */
public class OperatorPasswordWrongException extends Exception {
    public OperatorPasswordWrongException() {
        super("用户密码不正确");
    }
}
