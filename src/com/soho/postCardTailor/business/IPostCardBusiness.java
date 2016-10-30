package com.soho.postCardTailor.business;

import com.soho.postCardTailor.pojo.postCard.CropInfo;
import com.soho.postCardTailor.pojo.postCard.PostCard;
import org.apache.commons.fileupload.FileItem;

import java.util.List;

/**
 * 明信片相关类
 */
public interface IPostCardBusiness {
    /**
     * 获取所有明信片
     *
     * @return 所有明信片
     */
    List<PostCard> getAll();

    /**
     * 根据订单编号获取所有明信片
     *
     * @param orderId 订单编号
     * @return 所有明信片
     */
    List<PostCard> getAllByOrderId(Integer orderId);

    /**
     * 根据明信片ID，获取明信片
     *
     * @param id 明信片编号
     * @return 明信片
     */
    PostCard getById(Integer id);

    /**
     * 获取下一个明信片信息
     * @return 查询到的裁切信息
     */
    CropInfo getCropInfo() ;

    boolean modifyCropInfo(CropInfo cropInfo);

    /**
     * 将明信片保存到数据库中
     * @param basePath 明信片需要保存的基准路径
     * @param orderId 这张明信片所属的订单编号
     * @param fileItem 明信片照片信息
     * @return 是否保存成功，成功返回True，失败返回False
     */
    boolean insert(String basePath,Integer orderId, FileItem fileItem);
}
