package com.soho.postCardTailor.business.impl;

import com.soho.postCardTailor.business.IPostCardBusiness;
import com.soho.postCardTailor.dao.ICropInfoDAO;
import com.soho.postCardTailor.dao.IPostCardDAO;
import com.soho.postCardTailor.pojo.PostCardUtil;
import com.soho.postCardTailor.pojo.order.Order;
import com.soho.postCardTailor.pojo.postCard.CropInfo;
import com.soho.postCardTailor.pojo.postCard.PostCard;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 明信片业务流程
 */
@Service
public class PostCardBusinessImpl implements IPostCardBusiness {
    @Autowired
    private IPostCardDAO postCardDAO;
    @Autowired
    private ICropInfoDAO cropInfoDAO;
    private Queue<PostCard> postCardQueue = new LinkedBlockingQueue<>();

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
        PostCard postCard = new PostCard(cropInfo.getPostCard().getId(), null, null, null, null, null, 1);//只修改一下状态码;
        postCardDAO.doUpdate(postCard);
        return cropInfoDAO.doUpdate(cropInfo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
    public boolean insert(String basePath, Integer orderId, FileItem fileItem) {
        File targetPath = new File(basePath, orderId.toString());//确定此文件需要保存的文件夹路径
        File thumbPath = new File(basePath, "thumb\\" + orderId.toString());//缩略图路径
        synchronized (this) {//当文件夹不存在的时候，创建新的文件夹
            if (!targetPath.exists()) {
                //noinspection ResultOfMethodCallIgnored
                targetPath.mkdirs();
            }
            if (!thumbPath.exists()) {
                //noinspection ResultOfMethodCallIgnored
                thumbPath.mkdirs();
            }
        }
        String fileName = UUID.randomUUID().toString() + ".jpg";
        File file = new File(targetPath, fileName);//文件路径+文件名称
        File thumbFile = new File(thumbPath, fileName);
        PostCard postCard = new PostCard(-1, new Order(orderId, null, null, null, null, null), file.getAbsolutePath(), thumbFile.getAbsolutePath(), fileItem.getName(), null, 0);//获取明信片最终信息
        CropInfo cropInfo = new CropInfo(postCard);//创建新的裁切信息
        postCardDAO.doCreate(postCard);//保存明信片信息
        cropInfoDAO.doCreate(cropInfo);//保存裁切信息
        try {
            fileItem.write(file);//保存原始文件
            if (PostCardUtil.getWidth(file.getAbsolutePath()) < PostCardUtil.getHeight(file.getAbsolutePath())) {
                PostCardUtil.rotate(file.getAbsolutePath(), file.getAbsolutePath(), 270);//原图是纵向的，逆时针旋转90°
            }
            System.out.println("===================缩略图文件路径=================================================================");
            System.out.println("缩略图路径为：" + thumbPath.getAbsolutePath());
            PostCardUtil.zoomImage(file.getAbsolutePath(), thumbPath.getAbsolutePath() + "\\" + fileName, 200, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
