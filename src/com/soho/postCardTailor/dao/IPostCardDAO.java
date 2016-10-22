package com.soho.postCardTailor.dao;

import com.soho.postCardTailor.pojo.postCard.PostCard;

import java.util.List;

/**
 * 明信片DAO接口
 */
public interface IPostCardDAO {
    /**
     * 存储一个新的明信片
     *
     * @param postCard 明信片
     * @return 是否存储成功
     */
    boolean doCreate(PostCard postCard);

    /**
     * 更新明信片信息
     * @param postCard 更新后的明信片信息
     * @return 是否更新成功
     */
    boolean doUpdate(PostCard postCard);

    /**
     * 查找所有明信片
     *
     * @return 所有明信片
     */
    List<PostCard> findAll();

    /**
     * 根据订单ID获取所有明信片
     *
     * @param orderId 订单ID
     * @return 该订单所有明信片
     */
    List<PostCard> findAllByOrderId(Integer orderId);

    /**
     * 根据ID获取明信片
     *
     * @param id 明信片ID
     * @return 明信片
     */
    PostCard findById(Integer id);

    List<PostCard> findAllByStateId(Integer id);

    /**
     * 根据状态ID获取明信片总数
     *
     * @param stateId 状态ID
     * @return 处于此状态的明信片的个数
     */
    int getAllCountByState(Integer stateId);

}
