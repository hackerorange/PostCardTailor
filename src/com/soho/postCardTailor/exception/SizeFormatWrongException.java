package com.soho.postCardTailor.exception;

/**
 * 当尺寸格式不正确的时候抛出此异常
 */
public class SizeFormatWrongException extends Exception {
    public SizeFormatWrongException() {
        super("尺寸格式不正确，应该为【长×宽】");
    }
}
