package com.soho.postCardTailor.dao;

import com.soho.postCardTailor.pojo.Operator;

/**
 * 操作员DAO接口
 */
public interface IOperatorDAO {
    Operator findById(Integer id);
    Operator findByName(String name);

    boolean doCreate(Operator operator);

    boolean doUpdate(Operator operator);
}
