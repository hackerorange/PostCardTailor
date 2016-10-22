package com.soho.postCardTailor.dao;

import com.soho.postCardTailor.pojo.graphics.Size;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ISizeDAOTest {
    @Autowired
    ISizeDAO sizeDAO;

    @Test
    public void findAll() throws Exception {
        List<Size> all = sizeDAO.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void findBySize() throws Exception {
    }

    @Test
    public void doCreate() throws Exception {
    }

}