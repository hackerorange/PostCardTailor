package com.soho.postCardTailor.dao;

import com.soho.postCardTailor.pojo.graphics.Point;
import com.soho.postCardTailor.pojo.graphics.Rectangle;
import com.soho.postCardTailor.pojo.graphics.Size;
import com.soho.postCardTailor.pojo.postCard.CropInfo;
import com.soho.postCardTailor.pojo.postCard.PostCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by HackerOrange on 2016/10/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ICropInfoDAOTest {
    @Autowired
    ICropInfoDAO cropInfoDAO;
    @Autowired
    IPostCardDAO postCardDAO;

    @Test
    public void doCreate() throws Exception {
        CropInfo cropInfo = new CropInfo();
        cropInfo.setPostCard(new PostCard(54, null, null, null, null, null, null));
        cropInfoDAO.doCreate(cropInfo);
    }

    @Test
    public void doUpdate() throws Exception {
        CropInfo cropInfo = new CropInfo();
        PostCard postCard = postCardDAO.findById(54);
        cropInfo.setPostCard(postCard);
        Rectangle rectangle = new Rectangle();
        rectangle.setLocation(new Point(1, 1));
        rectangle.setSize(new Size(1, 1));
        cropInfo.setCropBox(rectangle);
        cropInfo.setPictureBox(rectangle);
        cropInfoDAO.doUpdate(cropInfo);
    }

    @Test
    public void findByPostCardId() throws Exception {
        CropInfo cropInfo = cropInfoDAO.findByPostCardId(54);
        System.out.println(cropInfo);
    }

}