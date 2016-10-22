package com.soho.postCardTailor.dao;

import com.soho.postCardTailor.pojo.graphics.Size;

import java.util.List;

/**
 * 尺寸DAO
 */
public interface ISizeDAO {
    /**
     * 查找所有尺寸
     *
     * @return 所有尺寸
     */
    List<Size> findAll();

    /**
     * 根据纸张尺寸，判断是否存在此尺寸
     *
     * @param size 要判断的尺寸
     * @return 存在则返回尺寸，不存在返回null
     */
    Size findBySize(Size size);

    /**
     * 创建一个新的尺寸
     *
     * @param size 新的尺寸
     * @return 判断是否存储成功
     */
    boolean doCreate(Size size);

}
