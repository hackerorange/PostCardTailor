package com.soho.postCardTailor.dao;

import com.soho.postCardTailor.pojo.Operator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试操作员DAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class IOperatorDAOTest {

    @Autowired
    private IOperatorDAO operatorDAO;

    @Test
    public void doCreate() throws Exception {

    }

    @Test
    public void doUpdate() throws Exception {
        Operator operator = new Operator(1, "orange", "orange");
        operatorDAO.doUpdate(operator);
    }

    @Test
    public void findByName() throws Exception {
        System.out.println(operatorDAO.findByName("admin").getName());
    }

}