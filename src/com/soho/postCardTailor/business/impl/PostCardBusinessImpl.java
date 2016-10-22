package com.soho.postCardTailor.business.impl;

import com.soho.postCardTailor.business.IPostCardBusiness;
import com.soho.postCardTailor.dao.ICropInfoDAO;
import com.soho.postCardTailor.dao.IPostCardDAO;
import com.soho.postCardTailor.exception.ThereIsPostCardNeedTailorException;
import com.soho.postCardTailor.pojo.postCard.CropInfo;
import com.soho.postCardTailor.pojo.postCard.PostCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by HackerOrange on 2016/10/5.
 */
@Service
public class PostCardBusinessImpl implements IPostCardBusiness {
    @Autowired
    private IPostCardDAO postCardDAO;
    @Autowired
    private ICropInfoDAO cropInfoDAO;
    private Queue<PostCard> postCardQueue = new LinkedBlockingQueue<>();

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
    public boolean insert(PostCard postCard) {
        CropInfo cropInfo = new CropInfo(postCard);
        postCardDAO.doCreate(postCard);
        cropInfoDAO.doCreate(cropInfo);
        return true;
    }

    @Override
    public List<PostCard> getAll() {
        return postCardDAO.findAll();
    }

    @Override
    public List<PostCard> getAllByOrderId(Integer orderId) {
        return postCardDAO.findAllByOrderId(orderId);
    }

    @Override
    public PostCard getById(Integer id) {
        return postCardDAO.findById(id);
    }

    @Override
    public synchronized CropInfo getCropInfo() {
        if (postCardQueue.isEmpty()) {
            postCardQueue.addAll(postCardDAO.findAllByStateId(0));
            if (postCardQueue.isEmpty()) {
                return null;
            }
            System.out.println(postCardQueue.size());
        }
        PostCard postCard = postCardQueue.poll();
        CropInfo cropInfo = new CropInfo(postCard);
        System.out.println(cropInfo.getCropBox());
        return cropInfo;
    }

    @Override
    public boolean modifyCropInfo(CropInfo cropInfo) {
        PostCard postCard = new PostCard(cropInfo.getPostCard().getId(), null, null, null, null, 1);//只修改一下状态码;
        postCardDAO.doUpdate(postCard);
        return cropInfoDAO.doUpdate(cropInfo);
    }
}
