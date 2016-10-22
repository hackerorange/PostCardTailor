package com.soho.postCardTailor.exception;

/**
 * 没有需要裁切的明信片的时候，抛出此异常
 */
public class ThereIsPostCardNeedTailorException extends Exception {
    public ThereIsPostCardNeedTailorException() {
        super("没有需要裁切的明信片，请联系上传人员");
    }
}
