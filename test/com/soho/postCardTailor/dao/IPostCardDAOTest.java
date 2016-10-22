package com.soho.postCardTailor.dao;

import com.soho.postCardTailor.pojo.order.Order;
import com.soho.postCardTailor.pojo.postCard.PostCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by HackerOrange on 2016/10/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class IPostCardDAOTest {
    @Autowired
    IPostCardDAO postCardDAO;

    @Test
    public void doCreate() throws Exception {
        PostCard postCard = new PostCard();
        postCard.setFileName("张三的好事情.jpg");
        postCard.setFilePath("D:/1231232123/sajffas-ksafdj");
        Order order = new Order();
        order.setId(1);
        postCard.setOrder(order);
        postCardDAO.doCreate(postCard);
    }

    @Test
    public void findAll() throws Exception {
        postCardDAO.findAll().forEach(System.out::println);
    }

    @Test
    public void findAllByOrderId() throws Exception {
        List<PostCard> postCardList=postCardDAO.findAllByStateId(1);
        System.out.println(postCardList.get(0));
    }

    @Test
    public void findById() throws Exception {
        PostCard postCard = postCardDAO.findById(1);
        System.out.println(postCard);
    }

}