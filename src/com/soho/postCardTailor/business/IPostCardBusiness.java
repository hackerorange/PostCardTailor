package com.soho.postCardTailor.business;

import com.soho.postCardTailor.exception.ThereIsPostCardNeedTailorException;
import com.soho.postCardTailor.pojo.postCard.CropInfo;
import com.soho.postCardTailor.pojo.postCard.PostCard;

import java.util.List;

/**
 * 明信片相关类
 */
public interface IPostCardBusiness {
    /**
     * 插入新的明信片
     *
     * @param postCard 要插入的明信片
     * @return 是否插入成功
     */
    boolean insert(PostCard postCard);

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
}
