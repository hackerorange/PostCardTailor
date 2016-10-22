package com.soho.postCardTailor.business;

import com.soho.postCardTailor.exception.NoSuchOperatorException;
import com.soho.postCardTailor.exception.OperatorPasswordWrongException;
import com.soho.postCardTailor.pojo.Operator;

/**
 * 操作员Business
 */
public interface IOperatorBusiness {
    /**
     * 实现用户登录
     *
     * @param operator 要登录的用户信息
     * @return 登录成功返回数据库中的对象
     */
    Operator login(Operator operator) throws NoSuchOperatorException, OperatorPasswordWrongException;
}
