package com.soho.postCardTailor.business.impl;

import com.soho.postCardTailor.business.IOperatorBusiness;
import com.soho.postCardTailor.dao.IOperatorDAO;
import com.soho.postCardTailor.exception.NoSuchOperatorException;
import com.soho.postCardTailor.exception.OperatorPasswordWrongException;
import com.soho.postCardTailor.pojo.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作员业务逻辑层实现类
 */
@Service("operatorBusiness")
public class OperatorBusinessImpl implements IOperatorBusiness {
    @Autowired
    private IOperatorDAO operatorDAO;

    @Override
    public Operator login(Operator operator) throws NoSuchOperatorException, OperatorPasswordWrongException {
        Operator tmpOperator = operatorDAO.findByName(operator.getName());
        System.out.println(tmpOperator);
        if (tmpOperator == null) {
            throw new NoSuchOperatorException();
        } else if (!tmpOperator.getPassword().equals(operator.getPassword())) {
            throw new OperatorPasswordWrongException();
        }
        return tmpOperator;
    }
}
